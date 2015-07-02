/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import model.BrowserModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import model.BrowserTransferHandler;
import model.Model;
import model.NamedElement;
import model.Class;
import model.Property;
import model.Package;

/**
 *
 * @author vmikovsk
 */
public class Browser extends javax.swing.JInternalFrame {

    /**
     * Creates new form Browser
     */
    public Browser() {
        initComponents();
       
        populateTree();
    }
    
    public javax.swing.JTree getTree() {
        return tree;
    }
    
    private void populateTree(){
        tree.setRootVisible(false);
        BrowserModel model = new BrowserModel();
        tree.setModel(model);
        tree.setTransferHandler(new BrowserTransferHandler());
        tree.setCellRenderer(new BrowserTreeCellRenderer());
        
        //Create a default model.
        BrowserModel bm = (BrowserModel) tree.getModel();
        bm.createModel("Model", 0);
        DefaultMutableTreeNode firstLeaf = ((DefaultMutableTreeNode)tree.getModel().getRoot()).getFirstLeaf();
        tree.setSelectionPath(new TreePath(firstLeaf.getPath()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tree = new javax.swing.JTree();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        newModelButton = new javax.swing.JMenu();
        newPackageButton = new javax.swing.JMenu();
        newClassButton = new javax.swing.JMenu();
        deleteButton = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Model browser");

        jPanel1.setName(""); // NOI18N

        tree.setDragEnabled(true);
        tree.setDropMode(javax.swing.DropMode.ON_OR_INSERT);
        tree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tree);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
        );

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        jMenuBar1.setName(""); // NOI18N

        newModelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/NewModelIcon.png"))); // NOI18N
        newModelButton.setToolTipText("New model");
        newModelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newModelButtonMouseClicked(evt);
            }
        });
        jMenuBar1.add(newModelButton);

        newPackageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/NewPackageIcon.png"))); // NOI18N
        newPackageButton.setText("Add Package");
        newPackageButton.setPreferredSize(new java.awt.Dimension(41, 32));
        newPackageButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newPackageButtonMouseClicked(evt);
            }
        });
        jMenuBar1.add(newPackageButton);

        newClassButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/NewNamespaceIcon.png"))); // NOI18N
        newClassButton.setToolTipText("New Class");
        newClassButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newClassButtonMouseClicked(evt);
            }
        });
        jMenuBar1.add(newClassButton);

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/DeleteElementIcon.png"))); // NOI18N
        deleteButton.setToolTipText("Delete");
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteButtonMouseClicked(evt);
            }
        });
        jMenuBar1.add(deleteButton);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newModelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newModelButtonMouseClicked
        //show name window
        String s = (String)JOptionPane.showInputDialog(
                            this,
                            "Enter model name:",
                            "Model name",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            "Model1");
        //TODO: check if name exists
        if ((s != null) && (s.length() > 0)) {
            //create new object
            BrowserModel bm = (BrowserModel) tree.getModel();
            bm.createModel(s, 0);
        }
        else{
            JOptionPane.showMessageDialog(this,
                "Illegal name entered",
                "Illegal name",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_newModelButtonMouseClicked

    private void newClassButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newClassButtonMouseClicked
       createNewClass();
    }//GEN-LAST:event_newClassButtonMouseClicked

    public NamedElement createNewClass() {
        //show name window
        String s = "Class1";
        try{
            //find selected element node
            TreePath selectedPath = tree.getSelectionPath();
            MutableTreeNode nodeParent = (MutableTreeNode) selectedPath.getLastPathComponent();
            BrowserModel model = (BrowserModel) tree.getModel();
            try {
                NamedElement ne = model.createElement(s, nodeParent, 0, false, true);
                ClassElementDialog jd = new ClassElementDialog(null, closable, ne, model);
                jd.setVisible(true);
                model.reload();
                //create new object
                tree.setSelectionPath(selectedPath);
                
                return ne;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Model Exception",
                JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(Browser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,
            "A parent node must be selected",
            "Model Exception",
            JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Browser.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return null;
    }
    
    private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseClicked
        TreePath selectedPath = tree.getSelectionPath();
        if ( selectedPath != null ){

            MutableTreeNode selectedNode = (MutableTreeNode) selectedPath.getLastPathComponent();
            BrowserModel model = (BrowserModel) tree.getModel();
            //show name window
            int response = JOptionPane.showConfirmDialog(this, 
                                "You are going to delete node "+selectedNode.toString(), 
                                "Delete confirmation", 
                                JOptionPane.OK_CANCEL_OPTION);
            if ( response != 0 ){ // user Cancels
                return;
            }
            try {
                if(selectedNode instanceof Model){
                    Model modelNode = (Model) selectedNode;
                    modelNode.remove();
                }
                else if(selectedNode instanceof NamedElement){
                    NamedElement namedElement = (NamedElement) selectedNode;
                    namedElement.remove();
                }
                model.reload();
                //create new object
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Model Exception",
                JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(Browser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_deleteButtonMouseClicked

    private void treeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeMouseClicked
        if (evt.getClickCount() >= 2) {
            TreePath selectedPath = tree.getSelectionPath();
            if(selectedPath != null){
                MutableTreeNode selectedNode = (MutableTreeNode) selectedPath.getLastPathComponent();
                if(selectedNode instanceof NamedElement){
                    if (selectedNode instanceof Property) {
                        BrowserModel model = (BrowserModel) tree.getModel();
                        PropertyElementDialog jd = new PropertyElementDialog(null, closable, (Property)selectedNode, model);
                        jd.setVisible(true);
                        //TODO dialog asigned to an element
                        model.reload();
                    } else if (selectedNode instanceof Class) {
                        BrowserModel model = (BrowserModel) tree.getModel();                        
                        ClassElementDialog jd = new ClassElementDialog(null, closable, (Class)selectedNode, model);
                        jd.setVisible(true);
                        //TODO dialog asigned to an element                       
                        model.reload();
                    } else if (selectedNode instanceof Package) {
                        BrowserModel model = (BrowserModel) tree.getModel();                        
                        PackageElementDialog jd = new PackageElementDialog(null, closable, (Package)selectedNode, model);
                        jd.setVisible(true);
                        //TODO dialog asigned to an element                       
                        model.reload();         
                    }
                }
                else if(selectedNode instanceof Model){
                    //show properties window
                    ModelDialog jd = new ModelDialog(null, closable, (Model)selectedNode);
                    jd.setVisible(true);
                    //TODO dialog asigned to an element
                    BrowserModel model = (BrowserModel) tree.getModel();
                    model.reload();
                }
            }
        }
    }//GEN-LAST:event_treeMouseClicked

    private void newPackageButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newPackageButtonMouseClicked
        createNewPackage();
    }//GEN-LAST:event_newPackageButtonMouseClicked

    public NamedElement createNewPackage()
    {
        String s = "Package1";
        try{
            //find selected element node
            TreePath selectedPath = tree.getSelectionPath();
            MutableTreeNode nodeParent = (MutableTreeNode) selectedPath.getLastPathComponent();
            BrowserModel model = (BrowserModel) tree.getModel();
            try {
                NamedElement ne = model.createElement(s, nodeParent, 0, false, false);
                PackageElementDialog jd = new PackageElementDialog(null, closable, ne, model);
                jd.setVisible(true);
                model.reload();
                //create new object
                
                tree.setSelectionPath(selectedPath);
                return ne;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Model Exception",
                JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(Browser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,
            "A parent node must be selected",
            "Model Exception",
            JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Browser.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return null;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Browser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Browser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Browser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Browser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Browser().setVisible(true);
            }
        });
    }

    //private ClassDialogBox classDialogBox;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu deleteButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu newClassButton;
    private javax.swing.JMenu newModelButton;
    private javax.swing.JMenu newPackageButton;
    private javax.swing.JTree tree;
    // End of variables declaration//GEN-END:variables
}
