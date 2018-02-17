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
import javax.swing.JFrame;

/**
 *
 * @author Lic. Jorge Isaac Vásquez Valenciano
 */
public final class iFrame extends JFrame implements ComponentInterfaz
{
    InitComponents ic = null;
    
    /**
     * NanosofotLayOut: By Lic. Isaac Vásquez
     *
     * @param w JFrame (Main) Width
     * @param h JFrame (Main) Height
     * @param margin Elements Margin (top-bottom) between them.
     */
    public iFrame(int w, int h, int margin) 
    {   
        ic = new InitComponents(w, h, margin, 0, false);
        
        setPreferredSize(ic.setComponentDimension());
        setLayout(ic.getLayOut());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    /**
     * NanosofotLayOut: By Lic. Isaac Vásquez
     *
     * @param w JFrame (Main) Width
     * @param h JFrame (Main) Height
     * @param margin Elements Margin (top-bottom) between them.
     * @param Title
     */
    public iFrame(int w, int h, int margin, String Title) 
    {   
        ic = new InitComponents(w, h, margin, 0, false);

        
        setPreferredSize(ic.setComponentDimension());
        setLayout(ic.getLayOut());
        setTitle(Title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }
    
     /**
     * NanosofotLayOut: By Lic. Isaac Vásquez
     *
     * @param w JFrame (Main) Width
     * @param h JFrame (Main) Height
     * @param margin Elements Margin (top-bottom) between them.
     * @param InitMarginTop deja un espacio entre el JFrame y el primer componente.
     */
    public iFrame(int w, int h, int margin, int InitMarginTop) 
    {
        ic = new InitComponents(w, h, margin, InitMarginTop, true);

        
        setPreferredSize(ic.setComponentDimension());
        setLayout(ic.getLayOut());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

    }
    
    
     /**
     * NanosofotLayOut: By Lic. Isaac Vásquez
     *
     * @param w JFrame (Main) Width
     * @param h JFrame (Main) Height
     * @param margin Elements Margin (top-bottom) between them.
     * @param InitMarginTop deja un espacio entre el JFrame y el primer componente.
     * @param Title Titulo del JFrame
     */
    public iFrame(int w, int h, int margin, int InitMarginTop, String Title) 
    {
        ic = new InitComponents(w, h, margin, InitMarginTop, true);

        
        setPreferredSize(ic.setComponentDimension());
        setLayout(ic.getLayOut());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle(Title);

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
    public void finalice() {
        setVisible(true);
        pack();
    }

    @Override
    public void AddObject(Component component, int width, int height, String Text) {
        ic.AddObject(component, width, height, Text, CENTER);
    }

    @Override
    public void AddObject(Component component, int width, int height) {
        ic.AddObject(component, width, height, "", CENTER);
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
    public int getXRightAlignment(int element_width) {
        return ic.getXRightAlignment(element_width);
    }

    @Override
    public void AddObject(Component component, int width, int height, String Text, int Position) {
        ic.AddObject(component, width, height, Text, Position);
    }

    @Override
    public void border(int top, int left, int bottom, int right, Color c) {
        getRootPane().setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, c));
    }
}
