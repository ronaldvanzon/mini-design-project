/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import static java.util.Collections.list;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import model.Class;
import model.Property;

/**
 *
 * @author rzon
 */
public class ClassTableModel extends AbstractTableModel {
    private String[] columnNames = {"Name","Description","LowerValue","HigherValue"};
    private ArrayList<Property> data= new ArrayList<Property>();
    
    @Override
    public int getRowCount() {
        
       return data.size();
    }

    @Override
    public int getColumnCount() {        
        return columnNames.length;     
    }
    
    @Override
    public boolean isCellEditable(int i, int i1) {
        return true;
    }
    
    @Override
    public void setValueAt(Object o, int i, int i1) {
        String text = (String)o;
        Property p = data.get(i);
        if (i1 == 0) {
            p.setName(text);
        } else if (i1 == 1) {
            p.setDescription(text);
        } else if (i1 == 2) {
            p.setLowervalue(Integer.parseInt(text));
        } else if (i1 == 3) {
            p.setHighervalue(Integer.parseInt(text));
        }
    }    

    @Override
    public Object getValueAt(int i, int i1) {
        Property p = data.get(i);
        if(i1==0){
            return p.getName();
        }else if(i1==1){
            
            return p.getDescription();
        }else if(i1==2){
            
            return p.getLowervalue();
        }else if(i1==3){
            
            return p.getHighervalue();
        }
        return null;
    }
    
    public void initDataModel(Class node){
       /* for (int i = 0; i < node.getChildCount(); i++) {
            TreeNode n = node.getChildAt(i);
            DefaultMutableTreeNode ne = (DefaultMutableTreeNode)n;
            AddProperty((Property)ne);
        }  */
    }
    @Override
    public String getColumnName(int column) {
        return columnNames[column];         
    }
    public void AddProperty(Property p){
        data.add(p);
    }
    public void RemoveProperty(int index){        
        data.remove(index);
    }

    void addRow(Property property) {
        data.add(property);
    }
    
}
