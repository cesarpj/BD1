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
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author jorge.vasquez
 */
public class iPanel extends JPanel implements ComponentInterfaz
{

    InitComponents ic = null;
    
    /**
     * NanosofotLayOut: By Lic. Isaac Vásquez
     *
     * @param horizontal: Cordenada X
     * @param vertical: Cordenada Y
     * @param w JFrame (Main) Width
     * @param h JFrame (Main) Height
     * @param margin Elements Margin (top-bottom) between them.
     */
    public iPanel(int horizontal, int vertical, int w, int h, int margin) 
    {   
        ic = new InitComponents(w, h, margin, 0, false);
        
        setBounds(horizontal, vertical, w, h);
        setLayout(ic.getLayOut());
    }
    
     /**
     * NanosofotLayOut: By Lic. Isaac Vásquez
     *
     * @param horizontal: Cordenada X
     * @param vertical: Cordenada Y
     * @param w JFrame (Main) Width
     * @param h JFrame (Main) Height
     * @param margin Elements Margin (top-bottom) between them.
     * @param InitMarginTop deja un espacio entre el JFrame y el primer componente.
     */
    public iPanel(int horizontal, int vertical, int w, int h, int margin, int InitMarginTop) 
    {
        ic = new InitComponents(w, h, margin, InitMarginTop, true);

        setBounds(horizontal, vertical, w, h);
        setLayout(ic.getLayOut());
    }
    
    @Override
    public Dimension setComponentDimension() {
        return ic.setComponentDimension();
    }

    @Override
    public LayoutManager getLayOut() {
        return ic.getLayOut();
    }

    @Override
    public int getXCenter(int element_width) {
        return ic.getXCenter(element_width);
    }

    @Override
    public int getYCenter(int element_height) {
        return ic.getYCenter(element_height);
    }

    @Override
    public Rectangle getRectangle(int width, int height) {
        return ic.getRectangle(width, height);
    }

    @Override
    public boolean getRowState() {
        return ic.getRowState();
    }

    @Override
    public void newLine() 
    {
        ic.newLine();
        for (Object[] element : this.getObject())
        {
            if (element[1] != null && element[2] != null)
            {
                add((Component) element[0]);
            }
        }
        ic.deleteObject();
    }

    @Override
    public void AddObject(Component component, int width, int height, String Text) {
        ic.AddObject(component, width, height, Text);
    }

    @Override
    public void AddObject(Component component, int width, int height) {
        ic.AddObject(component, width, height, "");
    }    
    
    @Override
    public Object[][] getObject() {
        return ic.getObject();
    }

    @Override
    public void deleteObject() {
        ic.deleteObject();
    }

    @Override
    public void finalice() {
        setVisible(true);
    }

    @Override
    public int getXRightAlignment(int element_width) {
        return ic.getXRightAlignment(element_width);
    }
    
    @Override
    public void AddObject(Component component, int width, int height, String Text, int Position) {
        ic.AddObject(component, width, height, Text, Position);
    }
    
    @Override
    public void border(int top, int left, int bottom, int right, Color c) 
    {
        setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, c));
    }
}
