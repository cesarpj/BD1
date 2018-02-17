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
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author jorge.vasquez
 */
public class InitComponents implements ComponentInterfaz {

    /**
     * Lic. Vásquez Valenciano Jorge Isaac
     * Universidad Latina de Costa Rica
     * Variables utilizadas para la 
     * construcción del iFrame o iPanel
     **/
    private final int 
            componentWidth, 
            componentHeight, 
            marginBetweenElements, 
            firstMarginTop;
    
    /**
     * Lic. Vásquez Valenciano Jorge Isaac
     * Universidad Latina de Costa Rica
     * Variables utilizadas por el método getXCenter() y getYCenter().
     **/
    private int 
            getCenteredHeight, 
            getCenteredWidth, 
            tmpWidth, 
            tmpHeight;
    
    private int
            getRightWidth,
            tmpRightWidth;
    // ????
    private boolean isRow, firstObjectinMarginTop;
    private final boolean haveMarginTop;
    private int totalWidth, currentRow;
    
    /**
     * Lic. Vásquez Valenciano Jorge Isaac
     * Universidad Latina de Costa Rica
     * Variables necesarias para el manejo de objetos en matriz
     **/
    private Object[][] object = new Object[40][40];
    private int MatrixSize;
    
    public InitComponents(int w, int h, int margin, int objectMarginTop, boolean haveMarginTop) 
    {
        this.componentHeight = h;
        this.componentWidth = w;
        this.marginBetweenElements = margin;
        this.isRow = false;
        this.totalWidth = 0;
        this.currentRow = 0;
        this.firstMarginTop = objectMarginTop;
        this.haveMarginTop = haveMarginTop;
        this.firstObjectinMarginTop = haveMarginTop;
        this.MatrixSize = 0;
    }
    
    @Override
    public Dimension setComponentDimension() 
    {
        return new Dimension(this.componentWidth, this.componentHeight);
    }

    @Override
    public LayoutManager getLayOut() 
    {
        return null;
    }

    @Override
    public int getXCenter(int element_width) 
    {
        if (this.isRow) 
        {
            if (this.currentRow == 1) 
            {
                this.getCenteredWidth = ((this.componentWidth / 2) - (this.totalWidth / 2));
                this.tmpWidth = element_width;
            } else {
                this.getCenteredWidth += this.tmpWidth;// + this.object_margin;
                this.tmpWidth = element_width;
            }
            return this.getCenteredWidth;
        } 
        else
        {
            return (this.componentWidth / 2) - element_width / 2;
        }
    }
    
    @Override
    public int getXRightAlignment(int element_width) 
    {
         if (this.isRow) 
        {
            if (this.currentRow == 1) 
            {
                this.getRightWidth = (this.componentWidth - this.firstMarginTop) - element_width;
            } else {
                this.getRightWidth -= element_width;
            }
            System.out.println(this.getRightWidth);
            return this.getRightWidth;
        } 
        else
            return this.componentWidth - this.firstMarginTop;       
    }


    @Override
    public int getYCenter(int element_height) 
    {
        if (this.firstObjectinMarginTop && this.haveMarginTop)
        {
            this.firstObjectinMarginTop = false;
            this.getCenteredHeight += this.firstMarginTop;
        }
        if ((this.getRowState() && this.currentRow == 1) || !this.getRowState()) 
            this.getCenteredHeight += element_height + this.marginBetweenElements;

        // ya que hace una suma antes de retornar el cálculo, debemos arreglarlo.
        return this.getCenteredHeight - element_height;
    }

    @Override
    public Rectangle getRectangle(int width, int height) 
    {
        if (this.getRowState())
            throw new ClassFormatError("getRectangle(): You can´t cast this method inside a Row");
        
        return new Rectangle (
                this.getXCenter(width), 
                this.getYCenter(height), 
                width, 
                height
        );
    }

    @Override
    public boolean getRowState() 
    {
        return this.isRow;
    }

    @Override
    public void newLine() 
    {
        this.isRow = true;
        for (Object[] object1 : this.getObject()) 
        {
            if (object1[1] != null)
                this.totalWidth += (int) object1[1];
        }
        if (this.totalWidth > this.componentWidth )
            throw new ClassFormatError("setRow(): elements width is bigger than main component.("+ this.totalWidth + "/" + this.componentWidth+")");
        
        for (Object[] element : this.getObject()) 
        {
            if (element[1] != null && element[2] != null && element[4] != null)
            {
                this.currentRow++;
                Component comp = (Component) element[0];
                
                switch ((int)element[4])
                {
                    case CENTER:
                    default:
                        comp.setBounds(
                                this.getXCenter((int) element[1]),
                                this.getYCenter((int) element[2]),
                                (int) element[1],
                                (int) element[2]
                        );
                    break;
                    
                    case RIGHT:
                      comp.setBounds(
                            this.getXRightAlignment((int) element[1]),
                            this.getYCenter((int) element[2]),
                            (int) element[1],
                            (int) element[2]
                    );                      
                    break;
                }

                try 
                {
                    if (comp instanceof JLabel) 
                    {
                        JLabel jlbl = (JLabel) comp;
                        jlbl.setText(element[3].toString());
                    }
                    else if (comp instanceof JButton)
                    {
                        JButton jbtn = (JButton) comp;
                        jbtn.setText(element[3].toString());                    
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println(ex.getMessage());
                }
                this.tmpHeight = (int) element[2];
            }
        }

        this.isRow = false;
        this.totalWidth = 0;
        this.tmpWidth = 0;
        this.tmpHeight = 0;
        this.currentRow = 0;
        this.tmpHeight = 0;
    }

    @Override
    public void finalice() {}

    @Override
    public void AddObject(Component component, int width, int height, String Text, int Position) 
    {
        this.object[MatrixSize][0] = component;
        this.object[MatrixSize][1] = width;
        this.object[MatrixSize][2] = height;
        this.object[MatrixSize][3] = Text;
        this.object[MatrixSize][4] = Position;
        this.MatrixSize++;
    }
    
    @Override
    public void AddObject(Component component, int width, int height, String Text) 
    {
        this.AddObject(component, width, height, Text, CENTER);

    }

    @Override
    public Object[][] getObject() 
    {
        return this.object;
    }

    @Override
    public void deleteObject() 
    {
        this.object = new Object[40][40];
    }

    @Override
    public void AddObject(Component component, int width, int height) 
    {
        this.AddObject(component, width, height, "", CENTER);
    }

    @Override
    public void border(int top, int left, int bottom, int right, Color c) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
