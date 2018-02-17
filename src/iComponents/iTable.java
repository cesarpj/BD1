/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iComponents;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jorge.vasquez
 */
public class iTable extends JTable 
{
    
    private final ArrayList<String> columnas;
    
    @SuppressWarnings("UseOfObsoleteCollectionType")
    public iTable(ArrayList<String> cols) 
    {
        super(new DefaultTableModel(cols.toArray(), 0));
        this.columnas = cols;
        setRowSorter(new TableRowSorter<>(getModel()));
        
    }
    
    /**
     * Añade una fila a una tabla determinada.
     * @param obj Arreglo con datos de fila, debe
     * ser constante el numero objetos con el numero de columnas.
     */
    public void addrow(Object[] obj) 
    {
        DefaultTableModel tb = (DefaultTableModel) getModel();
        tb.addRow(obj);
    }
    
}
