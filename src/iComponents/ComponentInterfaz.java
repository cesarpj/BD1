/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iComponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Rectangle;

/**
 *
 * @author jorge.vasquez
 */
public interface ComponentInterfaz {
    
    public int CENTER = 0;
    public int RIGHT = 1;
    
    
     /**
     * Retorna los valores inicializados en el constructor.
     * @return
     */
    public Dimension setComponentDimension();
    public LayoutManager getLayOut();
     /**
     * Recibe el ancho del elemento para posicionarlo con el ancho del componente
     * @param element_width
     * @return el centro del JFrame
     */
    public int getXCenter(int element_width);

     /**
     * Recibe el ancho del elemento para posicionarlo a la derecha del componente
     * @param element_width
     * @return el centro del JFrame
     */    
    public int getXRightAlignment(int element_width);
    
     /**
     * Recibe el ancho del elemento, calcula la longitud siguiente en Y del
     * JFrame
     * @param element_height
     * @return
     */
    public int getYCenter(int element_height);
    
    /**
     * Recibe el ancho y el largo del elemento y lo posiciona automáticamente
     * @param width El ancho del elemento (width)
     * @param height El largo del elemento (height)
     * @return
     */
    public Rectangle getRectangle(int width, int height);
 
    /**
     *
     * @return
     */
    public boolean getRowState();
    
    /**
     * termina una fila (row) lo que es similar al LN que ya conocemos.
     */
    public void newLine();
    
    /**
     * termina el iFrame mostrando y aplicando pack y setVisible
     */
    public void finalice();
    
    /**
     * -- Add a component to a Matrix of Object --
     * la misma es sólamente para ser usada en un posible NEWLINE.
     * (recordemos que NEWLINE: alineamiento horizontal de una fila).
     * @param component : Componente a agregar a la matriz de elementos
     * @param width : El ancho del elemento.
     * @param height : El largo del elemento.
     * @param Text : el texto del elemento de poder tener.
     * @param Position: CENTER, RIGHT, LEFT
     */
    public void AddObject(Component component, int width, int height, String Text, int Position);
    
    /**
     * -- Add a component to a Matrix of Object --
     * la misma es sólamente para ser usada en un posible NEWLINE.
     * Por defecto este método tiene la posición CENTER.
     * (recordemos que NEWLINE: alineamiento horizontal de una fila).
     * @param component : Componente a agregar a la matriz de elementos
     * @param width : El ancho del elemento.
     * @param height : El largo del elemento.
     * @param Text : el texto del elemento de poder tener.
     */
    public void AddObject(Component component, int width, int height, String Text);
    
    
    /**
     * -- Add a component to a Matrix of Object --
     * la misma es sólamente para ser usada en un posible NEWLINE.
     * Por defecto este método tiene la posición CENTER.
     * (recordemos que NEWLINE: alineamiento horizontal de una fila).
     * @param component : Componente a agregar a la matriz de elementos
     * @param width : El ancho del elemento.
     * @param height : El largo del elemento.
     */    
    public void AddObject(Component component, int width, int height);

    /**
     * retorna el objeto creado
     * @return
     */
    public Object[][] getObject();
    
    /**
     * Refactoriza el Objecto previamente creado para que no quede en memoria.
     */
    public void deleteObject();
    
    /**
     * createMatteBorder: Syntaxis
     * @param top px
     * @param left px
     * @param bottom px
     * @param right px
     * @param c: color of the component
     */
    public void border(int top, int left, int bottom, int right, Color c);

}