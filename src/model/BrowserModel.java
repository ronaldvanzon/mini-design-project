/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

/**
 *
 * @author lsmet
 */
public class BrowserModel extends DefaultTreeModel{
       
    public BrowserModel() {
        super(null);
        super.root = new DefaultMutableTreeNode("Models");
        
    }
    
    public Model createModel(String name, int index){
        //Note: Is this cast safe?
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) super.root;
        Model newModel = new Model(name);
        super.insertNodeInto(newModel, root, index);
        super.reload(root);
        return newModel;
    }
    
    public ArrayList<String> getTypeNames() {
        ArrayList<String> typeNames = new ArrayList<String>();
        
        //Traverse the tree to check what classes we currently have.        
        typeNames = getTypeNamesTraverse(super.root.children());
        
        return typeNames;
    }
    
    private ArrayList<String> getTypeNamesTraverse(Enumeration e) {       
        
        ArrayList<String> result = new ArrayList<String>();
        
        while (e.hasMoreElements())
        {
            Object o = e.nextElement();
            if (!(o instanceof Model)) {
                NamedElement element = (NamedElement) o;
                if (element instanceof Class)
                    result.add(element.getName());
                ArrayList<String> resultOfChildren = getTypeNamesTraverse(element.children());
                result.addAll(resultOfChildren);                
            }
            else if (o instanceof Model) {
                ArrayList<String> resultOfChildren = getTypeNamesTraverse( ((Model)o).children());
                result.addAll(resultOfChildren);    
            }
        }
        
        return result;
    }
    
    //TODO MORE COMMENTS
    public NamedElement createElement(String name, MutableTreeNode parent, int index, boolean element, boolean namespaceElement) throws Exception{
        //TODO: check parent is a Class
        NamedElement ne;
        if(element){
            ne = new Property(name);
        }
        else{
            if (namespaceElement) {
                ne = new Class(name);
            }
            else
                ne = new Package(name);
        }
        
        if (ne instanceof Package && parent instanceof Package)
        {
            Package p = (Package) parent;
            p.addElement(ne, index);
            this.reload(p);
        }
        else if (ne instanceof Class && parent instanceof Package)
        {
            Package p = (Package) parent;
            p.addElement(ne, index);
            this.reload(p);
        }
        else if (ne instanceof Class && parent instanceof Class)
        {
            Class p = (Class) parent;
            p.addElement(ne, index);
            this.reload(p);
        }
        else if (ne instanceof Property && parent instanceof Class)
        {
            Class p = (Class) parent;
            p.addElement(ne, index);
            this.reload(p);
        }
        else if(parent instanceof Model && !(ne instanceof Property) ){
            Model model = (Model) parent;
            model.addElement(ne, index);
            this.reload(model);
        }
        else{
            throw new Exception("Cannot insert child to the current parent");
        }
        return ne;
    }
    
}
