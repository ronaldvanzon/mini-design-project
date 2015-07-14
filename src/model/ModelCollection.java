/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author rzon
 */
public class ModelCollection extends DefaultTreeModel {
    
    //Singleton pattern.
    private static final ModelCollection instance = new ModelCollection();
    
    public static ModelCollection getInstance() {
        return instance;
    }
    
    private ModelCollection() {
        super(new DefaultMutableTreeNode("Models"));
        //super(null);
        //super.root = new DefaultMutableTreeNode("Models");        
    }
    
    public void addNewModel(Model m) {        
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) super.root;        
        root.insert(m, 0);        
        super.reload(root);
    }
}
