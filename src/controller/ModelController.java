/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import model.*;

/**
 *
 * @author rzon
 */
public class ModelController {   
    
    private MutableTreeNode currentSelectedNode = null;
    
    public ModelController() {
        
    }
    
    public void setCurrentSelectedNode(MutableTreeNode node) {
        currentSelectedNode = node;
    }
    
    public model.Class addNewClass(MutableTreeNode parent, String name) {
        
        model.Class c = new model.Class(name);
        if (parent instanceof model.Model)
            ((model.Model)parent).add(c);
        else if (parent instanceof model.Package)
            ((model.Package)parent).add(c);
        
        ModelCollection.getInstance().notifyObservers(null);
        
        return c;        
    }
    
    public model.Class addNewClass(String name) throws Exception {
        
        model.Class c = new model.Class(name);
        if (currentSelectedNode instanceof model.Package)
            ((model.Package)currentSelectedNode).add(c);
        else if (currentSelectedNode instanceof model.Model)
            ((model.Model)currentSelectedNode).add(c);
        else
            throw new Exception("The current selected node must be a model or package in order to add a new class.");

        ModelCollection.getInstance().notifyObservers(null);
        
        return c;
    }
    
    public model.Package addNewPackage(MutableTreeNode parent, String name) {
        
        model.Package p = new model.Package(name);
        if (parent instanceof model.Model)
            ((model.Model)parent).add(p);
        else if (parent instanceof model.Package)
            ((model.Package)parent).add(p);
        
        ModelCollection.getInstance().notifyObservers(null);
        
        return p;
    }    
    
    public model.Package addNewPackage(String name) throws Exception {
        
        model.Package p1 = new model.Package(name);
        if (currentSelectedNode instanceof model.Package)
            ((model.Package)currentSelectedNode).add(p1);
        else if (currentSelectedNode instanceof model.Model)
            ((model.Model)currentSelectedNode).add(p1);
        else
            throw new Exception("The current selected node must be a model or package in order to add a new package.");        
        
        ModelCollection.getInstance().notifyObservers(null);
        
        return p1;
    }
    
    public void changeNameOfClass(model.Class classElement, String name) {
        classElement.setName(name);
        ModelCollection.getInstance().notifyObservers(null);
    }
    
    public void changeDescriptionOfClass(model.Class classElement, String description) {
        classElement.setDescription(description);
        ModelCollection.getInstance().notifyObservers(null);
    }
    
    public void changeNameOfPackage(model.Package packageElement, String name) {
        packageElement.setName(name);
        ModelCollection.getInstance().notifyObservers(null);
    }
    
    public void changeDescriptionOfPackage(model.Package packageElement, String description) {
        packageElement.setDescription(description);
        ModelCollection.getInstance().notifyObservers(null);
    }    
    
    public model.Property addProperty(model.Class parent, String name) {
        
        Property p = new Property(name);
        parent.add(p);
        
        ModelCollection.getInstance().notifyObservers(null);
        
        return p;
    }
    
    public void removeProperty(model.Class parent, int propertyIndex) {
        parent.remove(propertyIndex);
        ModelCollection.getInstance().notifyObservers(null);
    }
    
    public void changeNameOfProperty(model.Property p, String name) {
        p.setName(name);
        ModelCollection.getInstance().notifyObservers(null);
    }
    
    public void changeDescriptionOfProperty(model.Property p, String descr) {
        p.setDescription(descr);
        ModelCollection.getInstance().notifyObservers(null);
    }    
    
    public void changeTypeOfProperty(model.Property p, String typeName) {
        p.setType(typeName);
        ModelCollection.getInstance().notifyObservers(null);
    }
    
    public void changeLowervalueOfProperty(model.Property p, int value) {
        p.setLowervalue(value);
        ModelCollection.getInstance().notifyObservers(null);
    }    
    
    public void changeHighervalueOfProperty(model.Property p, int value) {
        p.setHighervalue(value);
        ModelCollection.getInstance().notifyObservers(null);
    }      

    public void addNewModel(String s) {
        Model m = new Model(s);
        ModelCollection.getInstance().addNewModel(m);
        ModelCollection.getInstance().notifyObservers(null);
    }
    
    public void removeElement(NamedElement ne) {
        //First, remove all the children. Then remove this element.
        Enumeration e = ne.children();
        ArrayList<NamedElement> toBeRemoved = new ArrayList<>();
        while (e.hasMoreElements()) {
            Object o = e.nextElement();
            if (o instanceof NamedElement) {
                toBeRemoved.add((NamedElement)o);                
            }
        }
        
        for (int i = 0; i < toBeRemoved.size(); i++) {
            removeElement(toBeRemoved.get(i));
        }
        
        ne.remove();
        ModelCollection.getInstance().notifyObservers(ne);
    }
    
    public void removeModel(Model m) {
        //First, remove all the children. Then remove this model.
        Enumeration e = m.children();
        ArrayList<NamedElement> toBeRemoved = new ArrayList<>();
        while (e.hasMoreElements()) {
            Object o = e.nextElement();
            if (o instanceof NamedElement) {
                toBeRemoved.add((NamedElement)o);                
            }
        }
        
        for (int i = 0; i < toBeRemoved.size(); i++) {
            removeElement(toBeRemoved.get(i));
        }
        
        m.remove();
        ModelCollection.getInstance().notifyObservers(m);
    }
    
    public void removePackageElement(NamedElement parent, int propertyIndex) {
        //First get the child that will be deleted, then remove it and then notify everbody that it is deleted.
        NamedElement ne = (NamedElement)parent.getChildAt(propertyIndex);
        
        //First, remove all the children. Then remove this model.
        Enumeration e = ne.children();
        ArrayList<NamedElement> toBeRemoved = new ArrayList<>();
        while (e.hasMoreElements()) {
            Object o = e.nextElement();
            if (o instanceof NamedElement) {
                toBeRemoved.add((NamedElement)o);                
            }
        }
        
        for (int i = 0; i < toBeRemoved.size(); i++) {
            removeElement(toBeRemoved.get(i));
        }       
        
        parent.remove(propertyIndex);
        ModelCollection.getInstance().notifyObservers(ne);
    }
    
    public ArrayList<String> getTypeNames() {
        ArrayList<String> typeNames = new ArrayList<String>();
        
        //Traverse the tree to check what classes we currently have.
        typeNames = getTypeNamesTraverse(ModelCollection.getInstance().getRoot().children());
        
        return typeNames;
    }
    
    private ArrayList<String> getTypeNamesTraverse(Enumeration e) {       
        
        ArrayList<String> result = new ArrayList<String>();
        
        while (e.hasMoreElements())
        {
            Object o = e.nextElement();
            if (!(o instanceof Model)) {
                NamedElement element = (NamedElement) o;
                if (element instanceof model.Class)
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
    
    
}
