/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.diagramPane;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author rzon
 */
public abstract class UIElement {
    
    public model.NamedElement element;
    //public BufferedImage image;
    
    public int x;
    public int y;
    
    public abstract void draw(Graphics g);
}
