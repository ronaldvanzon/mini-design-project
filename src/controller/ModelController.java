/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.*;

/**
 *
 * @author rzon
 */
public class ModelController {   
    
    public ModelController() {
        
    }
    
    //public model.Class getCurrentClassUnderEdit() {
   //     return this.currentClassUnderEdit;
    //}
    
    public model.Class addNewClass(model.Package parent, String name) {
        
        model.Class c = new model.Class(name);
        model.Package p = (model.Package) parent;
        p.add(c);

        //currentClassUnderEdit = c;      
        
        return c;
    }
    
    //TODO: avoid having two copies of this method.
    public model.Class addNewClass(Model parent, String name) {
        
        model.Class c = new model.Class(name);
        parent.add(c);

        //currentClassUnderEdit = c;  
        
        return c;
    }
    
    public model.Package addNewPackage(model.Package parent, String name) {
        
        model.Package p1 = new model.Package(name);
        model.Package p2 = (model.Package) parent;
        p2.add(p1);
        
        return p1;
    }
    
    //TODO: avoid having two copies of this method.
    public model.Package addNewPackage(Model parent, String name) {
        
        model.Package p = new model.Package(name);
        parent.add(p);
        
        return p;
    }    
    
    public void changeNameOfClass(model.Class classElement, String name) {
        classElement.setName(name);
    }
    
    public void changeDescriptionOfClass(model.Class classElement, String description) {
        classElement.setDescription(description);
    }
    
    public void changeNameOfPackage(model.Package packageElement, String name) {
        packageElement.setName(name);
    }
    
    public void changeDescriptionOfPackage(model.Package packageElement, String description) {
        packageElement.setDescription(description);
    }    
    
    public model.Property addProperty(model.Class parent, String name) {
        
        Property p = new Property(name);
        parent.add(p);
        return p;
    }
    
    public void removeProperty(model.Class parent, int propertyIndex) {
        parent.remove(propertyIndex);
    }

    public void addNewModel(String s) {
        Model m = new Model(s);
        ModelCollection.getInstance().addNewModel(m);
    }
    
}
