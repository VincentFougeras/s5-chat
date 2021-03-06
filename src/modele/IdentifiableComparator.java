/**
 * Fichier IdentifiableComparator.java
 * @date 23 nov. 2017
 * @author Pierre POMERET-COQUOT
 *         pierre.pomeret@univ-tlse3.fr
 *         N° étudiant 20 40 32 63
 */
package modele;

import java.util.Comparator;

/**
 * Compare deux identifiables en fonction de leur identifiant unique
 */
public class IdentifiableComparator implements Comparator<Identifiable> {

	@Override
	public int compare(Identifiable arg0, Identifiable arg1) {
		return arg0.getIdentifiantUnique().compareTo(arg1.getIdentifiantUnique());
	}

}
