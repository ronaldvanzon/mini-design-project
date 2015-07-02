package view.diagramPane;

/*
 * ListTransferHandler.java is used by the DropDemo example.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import model.BrowserModel;
import model.NamedElement;
import view.ClassElementDialog;
import view.MetaArchitect;

public class InternalFrameTransferHandler extends TransferHandler {
    private int[] indices = null;
    private int addIndex = -1; //Location where items were added
    private int addCount = 0;  //Number of items added.

    private MetaArchitect ma = null;   
    
    InternalFrameTransferHandler(MetaArchitect metaArchitect) {
        this.ma = metaArchitect;
    }
            
    public boolean canImport(TransferHandler.TransferSupport info) {
        // Check for String flavor
        if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }
        return true;
   }

    protected Transferable createTransferable(JComponent c) {
        return new StringSelection(exportString(c));
    }
    
    public int getSourceActions(JComponent c) {
        return TransferHandler.COPY_OR_MOVE;
    }
    
    public boolean importData(TransferHandler.TransferSupport info) {
        if (!info.isDrop()) {
            return false;
        }
        
        DiagramPane pane = (DiagramPane)info.getComponent();

        // Get the string that is being dropped.
        Transferable t = info.getTransferable();
        String data;
        try {
            data = (String)t.getTransferData(DataFlavor.stringFlavor);
        } 
        catch (Exception e) { return false; }
                                
        DropLocation dl = info.getDropLocation();
        Point dropPoint = dl.getDropPoint();
        
        if (data.equals("Class")) {
            NamedElement ne = ma.browser.createNewClass();
            pane.drawPanel.setNewClass(ne, dropPoint);
        } else if (data.equals("Package")) {
            NamedElement ne = ma.browser.createNewPackage();
            pane.drawPanel.setNewPackage(ne, dropPoint);
        }
        
        pane.repaint();
//        pane.drawPanel.repaint();
            
        return true;
    }
  
    
    
    protected void exportDone(JComponent c, Transferable data, int action) {
        cleanup(c, action == TransferHandler.MOVE);
    }

    //Bundle up the selected items in the list
    //as a single string, for export.
    protected String exportString(JComponent c) {
        
        JTree tree = (JTree)c;
        TreePath selectedPath = tree.getSelectionPath();
        MutableTreeNode nodeParent = (MutableTreeNode) selectedPath.getLastPathComponent();
              
        
        return nodeParent.toString();

    }
    
    //If the remove argument is true, the drop has been
    //successful and it's time to remove the selected items 
    //from the list. If the remove argument is false, it
    //was a Copy operation and the original list is left
    //intact.
    protected void cleanup(JComponent c, boolean remove) {
        if (remove && indices != null) {
            JList source = (JList)c;
            DefaultListModel model  = (DefaultListModel)source.getModel();
            //If we are moving items around in the same list, we
            //need to adjust the indices accordingly, since those
            //after the insertion point have moved.
            if (addCount > 0) {
                for (int i = 0; i < indices.length; i++) {
                    if (indices[i] > addIndex) {
                        indices[i] += addCount;
                    }
                }
            }
            for (int i = indices.length - 1; i >= 0; i--) {
                model.remove(indices[i]);
            }
        }
        indices = null;
        addCount = 0;
        addIndex = -1;
    }
}