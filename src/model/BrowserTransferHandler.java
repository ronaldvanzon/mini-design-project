/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.time.Clock;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.COPY_OR_MOVE;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author vmikovsk
 * 
 * Based on code: http://www.coderanch.com/t/346509/GUI/java/JTree-drag-drop-tree-Java
 */
public class BrowserTransferHandler extends TransferHandler {

    // The types that this transferHandler can transfer. Only one available:
    //DefaultMutableTreeNode
    DataFlavor[] nodesFlavors = new DataFlavor[1];
    
    public BrowserTransferHandler() {
        //Create the compatible flavor for drag and drop
        try {
            String mimeType = DataFlavor.javaJVMLocalObjectMimeType +
                ";class=\"" +
                javax.swing.tree.DefaultMutableTreeNode[].class.getName() +
                "\"";
            nodesFlavors[0] = new DataFlavor(mimeType);
        } catch(ClassNotFoundException e) {
            Logger.getLogger(BrowserTransferHandler.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /**
     * First call inside the transferHandler. It creates an transferable with 
     * the objects selected to be moved
     * @param c
     * @return 
     */
    @Override
    protected Transferable createTransferable(JComponent c) {
        JTree tree = (JTree)c;
        //It is possible to move only one node per drag and drop
        TreePath path = tree.getSelectionPath();
        if(path != null) {
            // Make up a node array with the nodes selected for transfer
            DefaultMutableTreeNode[] nodes = new DefaultMutableTreeNode[1];
            nodes[0] = (DefaultMutableTreeNode) path.getLastPathComponent();
            return new NodesTransferable(nodes,nodesFlavors);
        }
        return null;
    }

    /**
     * Second call, check if the tree supports to import the transferable object
     * @param support
     * @return 
     */
    @Override
    public boolean canImport(TransferHandler.TransferSupport support) {
        //Check if the tree supports drop operations
        if(!support.isDrop()) {
            return false;
        }
        //highlight the position of the drop position
        support.setShowDropLocation(true);
        //Check if the data to import is the correct type
        if( ! support.isDataFlavorSupported(nodesFlavors[0])) {
            return false;
        }
        //Get the drop location to check
        JTree.DropLocation dl = (JTree.DropLocation)support.getDropLocation();
        JTree tree = (JTree)support.getComponent();
        TreePath destPath = dl.getPath();
        TreePath sourcePath = tree.getSelectionPath();
        // Do not allow a drop on the drag source selections.
        if ( sourcePath.equals(destPath) ){
            return false;
        }
        // Do not allow a drop inside an element contained in the source
        if ( sourcePath.isDescendant(destPath) ){
            return false;
        }
        // Do not allow to move NamedElements next to Models, they should be 
        //always inside a Model
        if ( sourcePath.getPathCount() > 2 && //moving a NamedElement
             destPath.getPathCount() < 2 ){ // to a Model
            return false;
        }
        // Do not allow to move a Model inside a NamedElement or other Model
        if ( sourcePath.getPathCount() <= 2 && //moving a Model
             destPath.getPathCount() >= 2 ){ // to a NamedElement or Model
            return false;
        }
        // Do not allow to move a Node into a ConcreteElement
        if ( destPath.getLastPathComponent() instanceof Property)
            return false;
        // else always allow to move a node of the tree
        return true;
    }
    
    /**
     * Third call, update the references(positions) of the nodes dragged and dropped
     * @param support
     * @return 
     */
    @Override
    public boolean importData(TransferHandler.TransferSupport support) {
        if( ! canImport(support) ) {
            return false;
        }
        // Extract transfer data, the nodes
        DefaultMutableTreeNode[] nodes = null;
        try {
            Transferable t = support.getTransferable();
            nodes = (DefaultMutableTreeNode[])t.getTransferData(nodesFlavors[0]);
        } catch(UnsupportedFlavorException ufe) {
            System.out.println("UnsupportedFlavor: " + ufe.getMessage());
        } catch(java.io.IOException ioe) {
            System.out.println("I/O error: " + ioe.getMessage());
        }
        // Get drop location info.
        JTree.DropLocation dl =
                (JTree.DropLocation)support.getDropLocation();
        int childIndex = dl.getChildIndex();
        TreePath dest = dl.getPath();
        DefaultMutableTreeNode newParent = (DefaultMutableTreeNode)dest.getLastPathComponent();
        JTree tree = (JTree)support.getComponent();
        DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
        // Configure for drop mode.
        int index = childIndex;    // DropMode.INSERT
        if(childIndex == -1) {     // DropMode.ON
            index = newParent.getChildCount();
        }
        // Move data inside the model.
        for(int i = 0; i < nodes.length; i++) {
            if ( newParent.equals(nodes[i].getParent()) && childIndex == -1)
                index--;
            //Remove the newParent connection
            model.removeNodeFromParent(nodes[i]);
            //Add conection to the new newParent
            model.insertNodeInto(nodes[i], newParent, index);
        }
        return true;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        JTree tree = (JTree)source;
        DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
        //Reload the model
        model.reload();
    }

    @Override
    public int getSourceActions(JComponent c) {
        return MOVE;
    }
}

/**
 * Class only used inside BrowserTransferHandler for the nodes being dragged
 * @author vmikovsk
 */
class NodesTransferable implements Transferable {
    DefaultMutableTreeNode[] nodes;
    DataFlavor[] flavors;

    public NodesTransferable(DefaultMutableTreeNode[] nodes, DataFlavor[] flavors) {
        this.nodes = nodes;
        this.flavors = flavors;
     }

    @Override
    public Object getTransferData(DataFlavor flavor)
                             throws UnsupportedFlavorException {
        //if(!isDataFlavorSupported(flavor))
           // throw new UnsupportedFlavorException(flavor);
        return nodes;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return flavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return Arrays.asList(flavors).contains(flavor);
    }
}
