package vue;

import controleur.ICtrlAdmin;
import modele.Groupe;
import modele.Utilisateur;

/**
 *
 * @author Vincent Fougeras
 */
@SuppressWarnings("serial")
public class AddUserPanel extends BasePanel {

    private ICtrlAdmin ctrlAdmin;
    
    /**
     * Creates new form AddPanel
     * 
     * @param ctrlAdmin
     */
    public AddUserPanel(ICtrlAdmin ctrlAdmin) {
        this.ctrlAdmin = ctrlAdmin;
        
        initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        prenomTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        nomTextField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        creerButton = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(32767, 0));
        annulerButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(600, 400));
        setLayout(new java.awt.BorderLayout());

        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 5, 0, 5, 0};
        jPanel1Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel1.setLayout(jPanel1Layout);

        jLabel2.setText("Nom");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Prénom");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(jLabel3, gridBagConstraints);

        prenomTextField.setPreferredSize(new java.awt.Dimension(200, 25));
        prenomTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prenomTextFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        jPanel1.add(prenomTextField, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Création d'un utilisateur");
        jLabel1.setAlignmentX(0.5F);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 9, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        nomTextField.setPreferredSize(new java.awt.Dimension(200, 25));
        nomTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomTextFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 12, 0);
        jPanel1.add(nomTextField, gridBagConstraints);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        creerButton.setText("Créer l'utilisateur");
        creerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creerButtonActionPerformed(evt);
            }
        });
        jPanel2.add(creerButton);
        jPanel2.add(filler1);

        annulerButton.setText("Annuler");
        annulerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerButtonActionPerformed(evt);
            }
        });
        jPanel2.add(annulerButton);

        add(jPanel2, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void prenomTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prenomTextFieldActionPerformed
    }//GEN-LAST:event_prenomTextFieldActionPerformed

    private void annulerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerButtonActionPerformed
        this.closeParentDialog();
    }//GEN-LAST:event_annulerButtonActionPerformed

    private void creerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creerButtonActionPerformed
        String nom = this.nomTextField.getText();
        String prenom = this.prenomTextField.getText();
        String nickname = nom;
        
        Utilisateur user = new Utilisateur("", nom, prenom, nickname);
        
        this.ctrlAdmin.insererUtilisateur(user);
               
        this.closeParentDialog();        
    }//GEN-LAST:event_creerButtonActionPerformed

    private void nomTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomTextFieldActionPerformed
    }//GEN-LAST:event_nomTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton annulerButton;
    private javax.swing.JButton creerButton;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nomTextField;
    private javax.swing.JTextField prenomTextField;
    // End of variables declaration//GEN-END:variables

}
