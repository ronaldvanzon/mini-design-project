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
public abstract class NamedElement extends DefaultMutableTreeNode{
    protected String name;
    protected String description;
    
    public NamedElement(String name){
        super(name);
        setName(name);
    }
    
    public final void setName(String name){
        this.name = name;
        super.setUserObject(name);
    }
    
    public String getName(){
        return name;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public String getDescription(){
        return description;
    }

    public void remove() {
        this.removeAllChildren();
        super.removeFromParent();
    }
}
