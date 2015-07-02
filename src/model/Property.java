/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author rzon
 */
public class Property extends NamedElement{

    protected int lowerValue;
    protected int higherValue;
    protected String type;
    
    public Property(String name) {
        super(name);
    }
    
    public void setLowervalue(int value) {
        this.lowerValue = value;
    }
    
    public int getLowervalue() {
        return this.lowerValue;
    }
    
    public void setHighervalue(int value) {
        this.higherValue = value;
    }
    
    public int getHighervalue() {
        return this.higherValue;
    }
    
    public void setType(String typeName) {
        this.type = typeName;
    }
    
    public String getType() {
        return this.type;
    }
    
}
