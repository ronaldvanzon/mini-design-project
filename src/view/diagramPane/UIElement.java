/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.diagramPane;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author rzon
 */
public abstract class UIElement {
    
    public model.NamedElement element;
    //public BufferedImage image;
    
    final int HEIGHT_OF_TEXT = 15;
    final int OFFSET_OF_TEXT = 10;
    
    public int x;
    public int y;
    public int height;
    public int width;
    
    public void setPosition(Point p) {
        this.x = p.x;
        this.y = p.y;
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public boolean contains(Point p) {
        if ( ( (x <= p.x) && (p.x <= x + width) ) &&
             ( (y <= p.y) && (p.y <= y + height)) ) {
            return true;
        }
        else
            return false;
    }
    
    public abstract void draw(Graphics g);

    void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
