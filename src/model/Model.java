/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 
 * @author lsmet
 */
public class Model extends DefaultMutableTreeNode{
    private String name;
    private String description;
    
    public Model(String name){
        super(name);
        setName(name);
        description = "";
    }
    
    public final void setName(String name){
        this.name = name;
        super.setUserObject(name);
    }
    
    public String getName(){
        return name;
    }
    
    public final void setDescription(String description){
        this.description = description;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void addElement(NamedElement element, int index){
        super.insert(element, index);
    }
    
    public void remove(){
        this.removeAllChildren();
        super.removeFromParent();
    }
}
