package vue;

import controleur.CtrlClient;
import controleur.ICtrlClient;
import java.util.Observable;
import java.util.Observer;

import modele.Groupe;

/**
 *
 * @author Vincent Fougeras
 */
@SuppressWarnings("serial")
public class AddTicketPanel extends BasePanel implements Observer {

    private ICtrlClient ctrlClient;
    private Groupe[] groupes;
    
    /**
     * Creates new form AddPanel
     */
    public AddTicketPanel(ICtrlClient ctrlClient) {
        this.ctrlClient = ctrlClient;
        this.groupes = ctrlClient.getGroupes().toArray(new Groupe[0]);
        
        // Il faut s'ajouter soi même au ctrlVue pour être notifié
        ((CtrlClient)this.ctrlClient).addObserver(this);
        
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
        groupeComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        titleTextField = new javax.swing.JTextField();
        contentTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        creerButton = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(32767, 0));
        annulerButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(600, 400));
        setLayout(new java.awt.BorderLayout());

        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 5, 0};
        jPanel1Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel1.setLayout(jPanel1Layout);

        jLabel2.setText("Destinataire");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(jLabel2, gridBagConstraints);

        groupeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(this.groupes));
        groupeComboBox.setPreferredSize(new java.awt.Dimension(200, 25));
        groupeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupeComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        jPanel1.add(groupeComboBox, gridBagConstraints);

        jLabel3.setText("Titre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        titleTextField.setPreferredSize(new java.awt.Dimension(200, 25));
        titleTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleTextFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        jPanel1.add(titleTextField, gridBagConstraints);

        contentTextArea.setColumns(20);
        contentTextArea.setLineWrap(true);
        contentTextArea.setRows(5);
        contentTextArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        contentTextArea.setMaximumSize(new java.awt.Dimension(142, 92));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(contentTextArea, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Création d'un ticket");
        jLabel1.setAlignmentX(0.5F);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 9, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel5.setText("Contenu");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        creerButton.setText("Créer le ticket");
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

    private void titleTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleTextFieldActionPerformed
    }//GEN-LAST:event_titleTextFieldActionPerformed

    private void groupeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupeComboBoxActionPerformed
    }//GEN-LAST:event_groupeComboBoxActionPerformed

    private void annulerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerButtonActionPerformed
        this.closeParentDialog();
    }//GEN-LAST:event_annulerButtonActionPerformed

    private void creerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creerButtonActionPerformed
        Groupe groupe = (Groupe) this.groupeComboBox.getSelectedItem();
        String title = this.titleTextField.getText();
        String content = this.contentTextArea.getText();
        
        this.ctrlClient.addTicket(groupe, title, content);
               
        this.closeParentDialog();        
    }//GEN-LAST:event_creerButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton annulerButton;
    private javax.swing.JTextArea contentTextArea;
    private javax.swing.JButton creerButton;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JComboBox<Groupe> groupeComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField titleTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object o1) {
        // Mettre à jour la liste des groupes existants
        this.groupes = ctrlClient.getGroupes().toArray(new Groupe[0]);
        groupeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(this.groupes));
    }
}
