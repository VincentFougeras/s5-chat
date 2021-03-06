/**
 * Fichier SimpleControleurClient.java
 * @date 30 nov. 2017
 * @author Pierre POMERET-COQUOT
 *         pierre.pomeret@univ-tlse3.fr
 *         N° étudiant 20 40 32 63
 */
package communication.simple;

import java.io.IOException;
import java.net.Socket;

import communication.ComAdresse;
import communication.ComEtatDeConnexion;
import communication.ComException;
import communication.ComIdentification;
import communication.ControleurComClient;
import communication.ObservateurComClient;
import communication.simple.SimpleMessage.SimpleMessageConfirmationConnexion;
import communication.simple.SimpleMessage.SimpleMessageConnexion;

/**
 * Contrôleur client manipulant des SimpleMessages
 * @see SimpleMessage
 */
public class SimpleControleurClient extends AbstractSimpleControleur implements ControleurComClient<SimpleMessage> {
	
	public static int NB_ESSAIS = 5;
	
	private ComAdresse serveur;
	private Socket socket;
	private ComEtatDeConnexion etatCnx;
	private ObservateurComClient<SimpleMessage> observateur;
	
	private Thread threadEcouteServeur = null;
	
	private boolean enCoursDePrimitiveBloquante = false;
	private boolean reponseRecue = false;
	private SimpleMessage reponse = null;
	
	
	
	
	
	/**
	 * Constructeur
	 * @param observateur Objet à informer lors de la réception de message
	 */
	public SimpleControleurClient(ObservateurComClient<SimpleMessage> observateur){
		this.observateur = observateur;
		this.etatCnx = ComEtatDeConnexion.NON_CONNECTE;
	}

	
	
	
	@Override
	public void connecter(ComAdresse serveurDistant,
			ComIdentification identifiant) {
		
		this.serveur = serveurDistant;
		Thread thread = new Thread(new OuvrirConnexion(identifiant));
		thread.start();
	}
	

	@Override
	public ComEtatDeConnexion connecterBloquant(ComAdresse serveurDistant,
			ComIdentification identifiant) throws ComException {

		this.serveur = serveurDistant;
		OuvrirConnexion oo = new OuvrirConnexion(identifiant);
		return oo.ouvrirConnexion();
		
	}

	@Override
	public ComEtatDeConnexion getEtatDeConnexion() {
		return etatCnx;
	}

	@Override
	public void informer(SimpleMessage message) throws ComException {
		try {
			assert(message.getTypeMessage()==SimpleTypeMessage.INFORME);
			envoyerMessage(socket, message);
		} catch (AssertionError e){
			throw new ComException.TypeMessageException(e);
		} catch (IOException e) {
			throw new ComException(e);
		}
	}

	@Override
	public void demander(SimpleMessage question) {
		try {
			envoyerMessage(socket, question);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public SimpleMessage demanderBloquant(SimpleMessage question) throws ComException {
		enCoursDePrimitiveBloquante = true;
		reponseRecue = false;
		try {
			envoyerMessage(socket, question);
		} catch (IOException e) {
			throw new ComException(e);
		}
		while (!reponseRecue){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		enCoursDePrimitiveBloquante = false;
		return reponse;
		
	}

	
	@Override
	@SuppressWarnings("deprecation")
	public void deconnecter() {
		etatCnx = ComEtatDeConnexion.DECONNECTE;
		threadEcouteServeur.stop();
		fermerFlux();
	}
	
	
	
	/**
	 * Effectue le traitement lors d'un message 
	 * (i.e. reprendre une primitive bloquante ou informer l'observateur)
	 * @param message Le message reçu
	 */
	private void traiterMessageRecu(SimpleMessage message){
		if (enCoursDePrimitiveBloquante){
			reponse = message;
			reponseRecue = true;
		}
		else {
			observateur.ctrlCom_recevoir(message);
		}
	}
	
	
	
	
	
	/**
	 * Ouverture de connexion
	 * SimpleControleurClient
	 */
	private class OuvrirConnexion implements Runnable {
		
		private ComIdentification identite;
		
		public OuvrirConnexion(ComIdentification identite){
			this.identite = identite;
		}

		public ComEtatDeConnexion ouvrirConnexion() throws ComException {
			etatCnx = ComEtatDeConnexion.EN_COURS_DE_CONNEXION;
			
			// Etablissement de la connexion TCP
			try {
				socket = new Socket(serveur.getAdresse(), serveur.getPort());
			}
			catch (IOException e){
				etatCnx = ComEtatDeConnexion.NON_CONNECTE;
				throw new ComException("Echec création socket", e);
			}
			if (verbeux)
				System.out.println("Connexion TCP établie avec " + SimpleAdresse.distante(socket));
			
			try {
				
				ouvrirFlux(socket);
				
				// Envoi des identifiants	
				envoyerMessage(socket, new SimpleMessageConnexion(identite));
				
				// Réception de la confirmation de connexion
				SimpleMessageConfirmationConnexion msg = (SimpleMessageConfirmationConnexion) recevoirMessage(socket);
				if (msg.getSuccesConnexion())
					etatCnx = ComEtatDeConnexion.CONNECTE;
				else
					etatCnx = ComEtatDeConnexion.CONNEXION_REFUSEE;
				
				
			} catch (IOException e) {
				etatCnx = ComEtatDeConnexion.NON_CONNECTE;
				throw new ComException("Echec création flux de connexion", e);
			} catch (ClassCastException e) {
				etatCnx = ComEtatDeConnexion.NON_CONNECTE;
				throw new ComException.TypeMessageException("Réception message invalide", e);
			}
			
			threadEcouteServeur = new Thread(new EcouteServeur());
			threadEcouteServeur.start();
			
			return etatCnx;
		}
		
		@Override
		public void run() {
			boolean accuse;
			try {
				ComEtatDeConnexion etat = ouvrirConnexion();
				accuse = (etat == ComEtatDeConnexion.CONNECTE);
			}
			catch (ComException e){
				System.err.println("Erreur : " + e.getMessage());
				accuse = false;
			}
			observateur.ctrlCom_connexionEtablie(accuse);
		}
		
	}
	
	/**
	 * A l'écoute du serveur. 
	 * (il peut envoyer les nouveaux tickets et messages)
	 * SimpleControleurClient
	 */
	private class EcouteServeur implements Runnable {
		
		@Override
		public void run(){
			SimpleMessage message;
			while (getEtatDeConnexion() == ComEtatDeConnexion.CONNECTE){
				try {
					message = recevoirMessage(socket);
					traiterMessageRecu(message);
				} catch (IOException e) {
					e.printStackTrace();
					System.err.println("Erreur -> déconnexion.");
					deconnecter();
				}
			}
		}
	}
	
	
	
	

}
