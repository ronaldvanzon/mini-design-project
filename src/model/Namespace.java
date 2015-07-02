/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lsmet
 */
public abstract class Namespace extends NamedElement{

    public Namespace(String name) {
        super(name);
    }
    
    public void addElement(NamedElement element, int index){
        super.insert(element, index);
    }
    
}
