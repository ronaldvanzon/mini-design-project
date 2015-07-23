/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.diagramPane;

import controller.ModelController;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.tree.MutableTreeNode;
import model.ModelCollection;
import model.NamedElement;
import model.Property;
import view.ClassElementDialog;
import view.PackageElementDialog;
import view.PropertyElementDialog;

import util.*;

/**
 *
 * @author rzon
 */
public class PanelDiagramPane extends javax.swing.JPanel implements Observer {

    private ArrayList<UIElement> drawableElements = new ArrayList<UIElement>(); 
    
    private volatile int screenX = 0;
    private volatile int screenY = 0;
    private volatile int myX = 0;
    private volatile int myY = 0;
    
    private UIElement currentDraggedElement = null;
    private int currentDraggedElementIndex = 0;
       
    /**
     * Creates new form PanelDiagramPane
     */
    public PanelDiagramPane(Rectangle r, ModelController modelController) throws IOException {
        initComponents();
        //setPreferredSize(new Dimension(1000, 1000));
        //Rectangle r2 = new Rectangle(new Dimension(1000, 1000));
        this.setBounds(r);
        
        ModelCollection.getInstance().addObserver(this);
        
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() >= 2) {

                    UIElement selectedElement = getElementFromMouseClick(e.getPoint());
                    if (selectedElement != null) {
                        MutableTreeNode selectedNode = selectedElement.element;
                        if(selectedNode instanceof NamedElement){
                            if (selectedNode instanceof Property) {
                                PropertyElementDialog jd = new PropertyElementDialog(null, false, (Property)selectedNode, modelController);
                                jd.setVisible(true);
                                //TODO dialog asigned to an element
                                ModelCollection.getInstance().reload();
                                System.out.println("Model reloaded1, now repainting....");
                                revalidate();
                                repaint();
                            } else if (selectedNode instanceof model.Class) {
                               
                                ClassElementDialog jd = new ClassElementDialog(null, false, (model.Class)selectedNode, modelController); //TODO: reference to modelController?
                                //ClassElementDialog jd = new ClassElementDialog(null, false, (model.Class)selectedNode, model);
                                jd.setVisible(true);
                                //TODO dialog asigned    to an element                       
                                ModelCollection.getInstance().reload();
                                System.out.println("Model reloaded,2 now repainting....");
                                revalidate();
                                repaint();
                            } else if (selectedNode instanceof model.Package) {                                                  
                                PackageElementDialog jd = new PackageElementDialog(null, false, (model.Package)selectedNode, modelController);
                                jd.setVisible(true);
                                //TODO dialog asigned to an element                       
                                ModelCollection.getInstance().reload();  
                                System.out.println("Model reloaded3, now repainting....");
                                //ma.revalidate();
                                //ma.repaint();
                                revalidate();
                                repaint();
                            }
                        }
                    }
                    
                    //ma.revalidate();
                    //ma.repaint();
                }
            }           

            @Override
            public void mousePressed(MouseEvent e) {
                screenX = e.getXOnScreen();
                screenY = e.getYOnScreen();
              
                currentDraggedElement = getElementFromMouseClick(e.getPoint());
                currentDraggedElementIndex = getElementIndexFromMouseClick(e.getPoint());                             
                
                if (currentDraggedElement != null) {
                    myX = currentDraggedElement.x;
                    myY = currentDraggedElement.y;  
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }

        });
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {                
                int deltaX = e.getXOnScreen() - screenX;
                int deltaY = e.getYOnScreen() - screenY;               

                if (currentDraggedElement != null) {
                    drawableElements.get(currentDraggedElementIndex).setLocation(myX + deltaX, myY + deltaY);
                    repaint();                    
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) { }

        });        
        
        
    }
    
    //private static final int PREF_W = 200;
   // private static final int PREF_H = PREF_W;

   /* @Override
    public Dimension getPreferredSize() {
       return new Dimension(PREF_W, PREF_H);
    }*/

    @Override
    protected void paintComponent(Graphics g) {
       super.paintComponent(g);

       //ModelCollection.getInstance().getChild(screenX, WIDTH)
       
       for (int i = 0; i < drawableElements.size(); i++) {
           UIElement e = drawableElements.get(i);
           e.draw(g);
       }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private UIElement getElementFromMouseClick(Point p) {
        //Check all UIElements and see which is the first one to include our mouseclick.
        for (int i = 0; i < drawableElements.size(); i++) {
            UIElement e = drawableElements.get(i);
            if (e.contains(p))
                return e;
        }
        return null;
    }
    
    private int getElementIndexFromMouseClick(Point p) {
        //Check all UIElements and see which is the first one to include our mouseclick.
        for (int i = 0; i < drawableElements.size(); i++) {
            UIElement e = drawableElements.get(i);
            if (e.contains(p))
                return i;
        }
        return -1;
    }    
    
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        
    }//GEN-LAST:event_formMouseClicked

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    void setNewPackage(NamedElement ne, Point dropPoint) {
        PackageUIElement e = new PackageUIElement();
        e.element = ne;
        e.setPosition(dropPoint);
        drawableElements.add(e);
        repaint();
    }

    void setNewClass(NamedElement ne, Point dropPoint) {
        ClassUIElement e = new ClassUIElement();
        e.element = ne;
        e.setPosition(dropPoint);
        drawableElements.add(e);
        repaint();
    }

    @Override
    public void update(Object deletedObject) {
        //Check if some elements were removed. If so, then we must also remove the UIElement copies here.
        if (deletedObject instanceof model.NamedElement) {
            for (int i = drawableElements.size() - 1; i >= 0; i--) {
                if (drawableElements.get(i).element == deletedObject) {
                    drawableElements.remove(i);
                    i--;
                }
            }
        }
        
        repaint();
    }
}
