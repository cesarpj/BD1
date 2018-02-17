/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JTextField;

/**
 *
 * @author jorge.vasquez
 */
public class iTextField extends JTextField 
{
    private Shape shape;
    private final int radius;
    private final Color backgroundColor, fontColor;

    /**
     * Crea un boton más personalizado
     *
     * @param hoverText : el texto a mostrar
     * @param borderRadius : similar a CSS
     */
    public iTextField(String hoverText, int borderRadius) 
    {
        super(hoverText);
        this.radius = borderRadius;
        this.backgroundColor = Color.WHITE;
        this.fontColor = new Color(73, 80, 87);
        setOpaque(false);
        setForeground(fontColor);
    }


    @Override
    public void paint(Graphics g) 
    {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            
        g2d.setColor(this.backgroundColor);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), this.radius, this.radius);

        g2d.setColor(new Color(206,212,218));
        g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, this.radius, this.radius);
  
         if (shape == null || !shape.getBounds().equals(getBounds())) 
         {
             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, this.radius, this.radius);
         }
         
        // Draw the text in the center
        g2d.setColor(this.fontColor);        
        super.paintComponent(g2d);
    }
}
