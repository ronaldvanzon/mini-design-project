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
import model.Package;
import model.Namespace;
/**
 *
 * @author rzon
 */
public class PackageTableModel extends AbstractTableModel {
    private String[] columnNames = {"Name","Description"};
    private ArrayList<Namespace> data= new ArrayList<Namespace>();
    
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
        Namespace n = data.get(i);
        if (n instanceof Class)
            return true;
        else
            return false;
    }
    
    @Override
    public void setValueAt(Object o, int i, int i1) {
        String text = (String)o;
        Namespace c = data.get(i);
        if (i1 == 0) {
            c.setName(text);
        } else {
            c.setDescription(text);
        } 
    }    

    @Override
    public Object getValueAt(int i, int i1) {
        Namespace c = data.get(i);
        if(i1==0){
            return c.getName();
        }else if(i1==1){
            
            return c.getDescription();
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
    
    public void AddClass(Class c){
        data.add(c);
    }
    
    public void RemoveClass(int index){        
        data.remove(index);
    }
    
    public void AddPackage(Package p){
        data.add(p);
    }
    
    public void RemovePackage(int index){        
        data.remove(index);
    }

    void addRow(Class c) {
        data.add(c);
    }

   
    
}
