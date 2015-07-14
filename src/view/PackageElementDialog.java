/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ModelController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import model.BrowserModel;
import model.NamedElement;
import model.Class;
import model.Package;

/**
 *
 * @author lsmet
 */
public class PackageElementDialog extends javax.swing.JDialog {
    
    //private NamedElement node;
    private model.Package packageElement = null;
    private ModelController modelController = null;
    
    private PackageTableModel ttm;

    
    /**
     * Creates new form elementDialog
     */
    public PackageElementDialog(java.awt.Frame parent, boolean modal, model.Package p, ModelController modelController) {
        super(parent, modal);
        initComponents();       
        
        this.modelController = modelController;
        
        this.packageElement = p;
        this.NameTxt.setText(p.getName());
        this.DescriptionArea.setText(p.getDescription());

        this.ttm = new PackageTableModel();
        
        for (int i = 0; i < p.getChildCount(); i++) {
            TreeNode n = p.getChildAt(i);
            if (n instanceof Class)
                this.ttm.AddClass((Class)n);
            else if (n instanceof Package)
                this.ttm.AddPackage((Package)n);
        }
        
        propertiesTable.setModel(ttm);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NameTxt = new javax.swing.JTextField();
        NameLbl = new javax.swing.JLabel();
        DescriptionLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DescriptionArea = new javax.swing.JTextArea();
        CloseBtn = new javax.swing.JButton();
        PropertiesLbl = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        propertiesTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        addPropertyButton = new javax.swing.JButton();
        deletePropertyButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        NameLbl.setText("Name");

        DescriptionLbl.setText("Description");

        DescriptionArea.setColumns(20);
        DescriptionArea.setRows(5);
        jScrollPane1.setViewportView(DescriptionArea);

        CloseBtn.setText("Close");
        CloseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseBtnActionPerformed(evt);
            }
        });

        PropertiesLbl.setText("Properties");

        propertiesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Description"
            }
        ));
        jScrollPane2.setViewportView(propertiesTable);

        jLabel1.setText("Direct Classes and Packages");

        addPropertyButton.setText("Add Class");
        addPropertyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClassButtonActionPerformed(evt);
            }
        });

        deletePropertyButton.setText("Delete child");
        deletePropertyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteClassButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(PropertiesLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(161, 161, 161))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(CloseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(NameLbl)
                                        .addGap(18, 18, 18)
                                        .addComponent(NameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(DescriptionLbl)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(26, 26, 26)
                                        .addComponent(addPropertyButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(deletePropertyButton))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(PropertiesLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NameLbl)
                    .addComponent(NameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DescriptionLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(addPropertyButton)
                    .addComponent(deletePropertyButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(CloseBtn)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CloseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseBtnActionPerformed

        modelController.changeNameOfPackage(packageElement, this.NameTxt.getText());
        modelController.changeDescriptionOfPackage(packageElement, this.DescriptionArea.getText());
        this.dispose();
    }//GEN-LAST:event_CloseBtnActionPerformed

    private void addClassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClassButtonActionPerformed
        //show name window
        String s = (String)JOptionPane.showInputDialog(
                            this,
                            "Enter class name:",
                            "Class name",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            "Class1");
        try{
            if ((s != null) && (s.length() > 0)) {
                //find selected property node
                MutableTreeNode nodeParent = (MutableTreeNode) packageElement;
                try {
                    //TODO: check whether parent is a Class, since a Property can only be added to a class.
                    //model.createElement(s, nodeParent, 0, true);
//                    NamedElement p = model.createElement(s, nodeParent, 0, false, true);
                    
                    model.Class c = modelController.addNewClass((model.Package)nodeParent, s);
                    
                    PackageTableModel tableModel = (PackageTableModel)propertiesTable.getModel();
                    tableModel.addRow(c);
                    tableModel.fireTableDataChanged();
                   
                    //create new object
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Model Exception",
                    JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(Browser.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            else{
                JOptionPane.showMessageDialog(this,
                    "Illegal name entered",
                    "Illegal name",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,
            "A parent node must be selected",
            "Model Exception",
            JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Browser.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_addClassButtonActionPerformed

    private void deleteClassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteClassButtonActionPerformed
        if (propertiesTable.getSelectedRow() != -1)
        {        
            packageElement.remove(propertiesTable.getSelectedRow());

            PackageTableModel model = (PackageTableModel)propertiesTable.getModel();
            model.RemoveClass(propertiesTable.getSelectedRow());
            model.fireTableDataChanged();
        }
    }//GEN-LAST:event_deleteClassButtonActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ElementDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ElementDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ElementDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ElementDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ElementDialog dialog = new ElementDialog(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseBtn;
    private javax.swing.JTextArea DescriptionArea;
    private javax.swing.JLabel DescriptionLbl;
    private javax.swing.JLabel NameLbl;
    private javax.swing.JTextField NameTxt;
    private javax.swing.JLabel PropertiesLbl;
    private javax.swing.JButton addPropertyButton;
    private javax.swing.JButton deletePropertyButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable propertiesTable;
    // End of variables declaration//GEN-END:variables
}
