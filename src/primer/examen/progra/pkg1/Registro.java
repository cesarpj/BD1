/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primer.examen.progra.pkg1;

import iComponents.iButton;
import iComponents.iFrame;
import iComponents.iTextField;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

/**
 *
 * @author Telematica-2-0
 */
public final class Registro
{
    private final SQL sql = new SQL();
    public JLabel lbl_error_msg = new JLabel();
    private Point initialClick;
    iFrame if_;
   

    
    public Registro() {
        if_ = new iFrame(300,400,4,40);
        if_.setUndecorated(true);
        
        Container c = if_.getContentPane();
        c.setBackground(new Color(40, 43, 48));
        
        initComponents();
        if_.finalice();
    }

    public void initComponents() 
    {
        iButton btn = new iButton("Registrarse", 4, new Color(54,57,62), Color.white);
        btn.setForeground(Color.white);
        iButton btn2 = new iButton("Regresar", 4, new Color(54,57,62), Color.white);
        btn2.setForeground(Color.white);
        
        JLabel lbl_username = new JLabel();
        JTextField txt_username = new JTextField();
        JLabel lbl_pass = new JLabel();
        JPasswordField txt_password = new JPasswordField();
        JLabel lbl_nombre = new JLabel();
        JTextField txt_nombre = new JTextField();
        

        JLabel lbl_img = new JLabel();
        JLabel lbl_logo = new JLabel();

        IconFontSwing.register(FontAwesome.getIconFont());
        Icon icon = IconFontSwing.buildIcon(FontAwesome.TIMES, 20, new Color(240, 240, 240));
        JLabel lbl_close = new JLabel(icon);
        lbl_close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                System.exit(0);
            }
        });

        icon = IconFontSwing.buildIcon(FontAwesome.WINDOW_RESTORE, 15, new Color(240, 240, 240));
        JLabel lbl_move = new JLabel(icon);
        lbl_move.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                if_.getComponentAt(initialClick);
            }
        });
        lbl_move.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) 
            {
                // get location of Window
                int thisX = if_.getLocation().x;
                int thisY = if_.getLocation().y;

                // Determine how much the mouse moved since the initial click
                int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
                int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

                // Move window to this position
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                if_.setLocation(X, Y);
            }
        });
        
        IconFontSwing.register(FontAwesome.getIconFont());
        icon = IconFontSwing.buildIcon(FontAwesome.WINDOW_MINIMIZE, 15, new Color(240, 240, 240));
        JLabel lbl_minimize = new JLabel(icon);
        lbl_minimize.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if_.setState(JFrame.ICONIFIED);
            }
        });

        lbl_close.setBounds(255, 2, 40, 40);
        lbl_move.setBounds(230, 4, 40, 40);
        lbl_minimize.setBounds(200, 4, 40, 40);

        lbl_error_msg.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
        lbl_error_msg.setForeground(Color.red);


        lbl_logo.setForeground(Color.WHITE);
       ;
        lbl_logo.setBounds(if_.getRectangle(98, 40));

        lbl_username.setForeground(Color.WHITE);
        lbl_pass.setForeground(Color.WHITE);
        lbl_nombre.setForeground(Color.WHITE);
        
        if_.AddObject(lbl_username, 80, 30, "Nombre ");
        if_.AddObject(txt_username,120, 30);
        if_.newLine();
        
        if_.AddObject(lbl_pass, 80, 30, "Password");
        if_.AddObject(txt_password, 120, 30);
        if_.newLine();
        
        if_.AddObject(lbl_nombre, 80, 30, "Nombre a mostrar ");
        if_.AddObject(txt_nombre, 120, 30);
        if_.newLine();

        btn.setBounds(if_.getRectangle(200, 30));
        btn.addActionListener((a) -> {
            ArrayList<Object> objs = new ArrayList<>();
            objs.addAll(Arrays.asList
        (txt_username.getText(),
                txt_password.getText(),
                txt_nombre.getText()));
           
            
            
            
            boolean result = sql.exec("INSERT INTO "
                    + "Cesar_login1"
                    + "(`nombre`, `password`, `nombreMostrar`) "
                    + "VALUES (?,?,?)", objs);
            
            if (result) {
                JOptionPane.showMessageDialog(null, "Persona Agregada");
            } else { 
                JOptionPane.showMessageDialog(null, "No se puede agregar");
            }
        });
        
        btn2.setBounds(if_.getRectangle(200, 30));
        btn2.addActionListener((a) -> {
            if_.dispose();
            MySQL4Java mySQL4Java = new MySQL4Java();
            
        });

        lbl_error_msg.setBounds(if_.getRectangle(200, 25));

        if_.add(lbl_close);
        if_.add(lbl_move);
        if_.add(lbl_minimize);
        if_.add(lbl_username);
        if_.add(txt_username);
        if_.add(lbl_pass);
        if_.add(txt_password);
        if_.add(lbl_nombre);
        if_.add(txt_nombre);
        if_.add(btn);
        if_.add(btn2);
        if_.add(lbl_error_msg);
        if_.pack();
    }
}

