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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author rzon
 */
public class PackageUIElement extends UIElement{
    
    public PackageUIElement() {
    }
    
    @Override
    public void draw(Graphics g) {        
        Graphics2D g2 = (Graphics2D) g;  
        
        //Draw rectangle for the entire package.
        Rectangle rect = new Rectangle(x, y + 30, 150, 80);              
        g2.setPaint(Color.yellow);
        g2.fill(rect);        
        g2.setPaint(Color.black);
        g2.draw(rect);
       
        //Draw rectangle for the name of the package.
        Rectangle rect2 = new Rectangle(x, y, 100, 30);
        g2.setPaint(Color.yellow);
        g2.fill(rect2);
        g2.setPaint(Color.black);
        g2.draw(rect2);
        
        g.drawString(element.getName(), 10, 10);      
    }
    
}