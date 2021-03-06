/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.diagramPane;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Enumeration;

/**
 *
 * @author rzon
 */
public class PackageUIElement extends UIElement{
    
    final int TITLE_BAR_OFFSET = 40;
    private int property_offset = 0;
    final int TEXT_SPACING = 5;
    
    public PackageUIElement() {
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;  
        property_offset = OFFSET_OF_TEXT;
        
        //Draw rectangle for the entire class.
        Rectangle rect = new Rectangle(x, y, 100, TITLE_BAR_OFFSET);          
        Color color = new Color(227, 251, 235);
        g2.setPaint(color);
        g2.fill(rect);        
        g2.setPaint(Color.black);
        g2.draw(rect);
        
        g.drawString(element.getName(), x + OFFSET_OF_TEXT, y + HEIGHT_OF_TEXT);
              
        //Obtain all the properties of the class.
        model.Package p = (model.Package)this.element;
        Enumeration e = p.children();
        while (e.hasMoreElements()) {
            Object o = e.nextElement();            
            if (o instanceof model.Class) {
                //drawProperty(g, (Property) o);
                property_offset += OFFSET_OF_TEXT + TEXT_SPACING;
            }
        }       
                
        //Draw rectangle for the attributes of the class.        
        Rectangle rect2 = new Rectangle(x, y + TITLE_BAR_OFFSET, 150, property_offset + OFFSET_OF_TEXT);
        g2.setPaint(color);
        g2.fill(rect2);        
        g2.setPaint(Color.black);
        g2.draw(rect2);        
        
        property_offset = OFFSET_OF_TEXT;
        Enumeration e2 = p.children();
        while (e2.hasMoreElements()) {
            Object o = e2.nextElement();
            if (o instanceof model.Class) {
                drawClass(g, (model.Class) o);
            }
        }
        
        //Draw rectangle for the operations of the class.
        int propertyBar_offset = y + TITLE_BAR_OFFSET + property_offset + OFFSET_OF_TEXT;
        Rectangle rect3 = new Rectangle(x, propertyBar_offset, 150, 20);
        g2.setPaint(color);
        g2.fill(rect3);        
        g2.setPaint(Color.black);
        g2.draw(rect3);
        
        this.width = 150;
        this.height = TITLE_BAR_OFFSET + property_offset + OFFSET_OF_TEXT + 20;
        
    }
    
    public void drawClass(Graphics g, model.Class c) {
        Graphics2D g2 = (Graphics2D) g;  
        g2.setPaint(Color.black);
        g2.drawString(c.getName(), x + OFFSET_OF_TEXT, y + TITLE_BAR_OFFSET + property_offset + HEIGHT_OF_TEXT);
        property_offset += OFFSET_OF_TEXT + TEXT_SPACING;        
    }
    
}