package view;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import model.Property;
import model.Model;
import model.Namespace;
import model.Class;
import model.Package;

/**
 *
 * @author vmikovsk
 */
public class BrowserTreeCellRenderer extends DefaultTreeCellRenderer{
    ImageIcon propertyElement, classElement, model, packageElement;

    public BrowserTreeCellRenderer() {
        propertyElement = new ImageIcon(BrowserTreeCellRenderer.class.getResource("/img/TreeProperty.png"));
        classElement = new ImageIcon(BrowserTreeCellRenderer.class.getResource("/img/TreeClass.png"));
        packageElement = new ImageIcon(BrowserTreeCellRenderer.class.getResource("/img/TreePackage.png"));
        model = new ImageIcon(BrowserTreeCellRenderer.class.getResource("/img/TreeModel.png"));
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value,
                        boolean sel, boolean expanded, boolean leaf, int row,
                        boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        
        if (value instanceof Class){
            setIcon(classElement);
        } 
        else if (value instanceof Package){
            setIcon(packageElement);
        }
        else if (value instanceof Property){
            setIcon(propertyElement);
        }
        else if (value instanceof Model){
            setIcon(model);
        }

        return this;
    }
}
