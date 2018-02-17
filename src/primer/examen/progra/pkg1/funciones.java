/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primer.examen.progra.pkg1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Telematica-2-0
 */
public class funciones 
{
    /**
     * Devuelve si un elemento es Numero(true) o no(false)
     * @param element
     * @return
     */
    public boolean isNumeric(Object element) 
    {
        try 
        {
            for (int i = 0; i < element.toString().length(); i++)
                Integer.parseInt(String.valueOf(element.toString().charAt(i)));
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
        return true;
    }
        public String MD5(String dat) 
    {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(dat.getBytes());
           byte[] digest = md.digest();
           String md5 = DatatypeConverter.printHexBinary(digest).toUpperCase();
           return md5;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * Añade una fila a una tabla determinada.
     * @param table Tabla a insertar la fila
     * @param obj Arreglo con datos de fila, debe
     * ser constante el numero objetos con el numero de columnas.
     */
    public void addrow(JTable table, Object[] obj) 
    {
        DefaultTableModel tb = (DefaultTableModel) table.getModel();
        tb.addRow(obj);
    }

    /**
     * Inicializa una tabla con columnas.
     * @param cols
     * @return
     */
    @SuppressWarnings("UseOfObsoleteCollectionType")
    public JTable createTable(ArrayList cols) 
    {
        Vector<Object> VCOLS = new Vector<>(cols);
        Vector<Vector<String>> VROWS = new Vector<>();

        JTable table = new JTable(new DefaultTableModel(VROWS, VCOLS));
        return table;
    }    
}
