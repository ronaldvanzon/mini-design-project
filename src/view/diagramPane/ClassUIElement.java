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
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.tree.DefaultMutableTreeNode;
import model.NamedElement;
import model.Class;
import model.Property;

/**
 *
 * @author rzon
 */
public class ClassUIElement extends UIElement{
    
    final int TITLE_BAR_OFFSET = 40;
    private int property_offset = 0;
    final int TEXT_SPACING = 5;
    
    public ClassUIElement() {
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;  
        property_offset = OFFSET_OF_TEXT;
        
        //Draw rectangle for the entire class.
        Rectangle rect = new Rectangle(x, y, 150, TITLE_BAR_OFFSET);          
        Color color = new Color(227, 251, 235);
        g2.setPaint(color);
        g2.fill(rect);        
        g2.setPaint(Color.black);
        g2.draw(rect);
        
        g.drawString(element.getName(), x + OFFSET_OF_TEXT, y + HEIGHT_OF_TEXT);
              
        //Obtain all the properties of the class.
        Class c = (Class)this.element;
        Enumeration e = c.children();
        while (e.hasMoreElements()) {
            Object o = e.nextElement();
            if (o instanceof Property) {
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
        Enumeration e2 = c.children();
        while (e2.hasMoreElements()) {
            Object o = e2.nextElement();
            if (o instanceof Property) {
                drawProperty(g, (Property) o);
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
    
    public void drawProperty(Graphics g, Property p) {
        Graphics2D g2 = (Graphics2D) g;  
        g2.setPaint(Color.black);
        g2.drawString(p.getName() + ": " + p.getType(), x + OFFSET_OF_TEXT, y + TITLE_BAR_OFFSET + property_offset + HEIGHT_OF_TEXT);
        property_offset += OFFSET_OF_TEXT + TEXT_SPACING;        
    }
    
}