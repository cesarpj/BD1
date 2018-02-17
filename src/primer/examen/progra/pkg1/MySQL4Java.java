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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

/**
 *
 * @author Telematica-2-0
 */
public final class MySQL4Java {
java.util.Timer timer;// Variable tiempo
    private final SQL sql = new SQL();
    private static funciones fnc = new SQL();
    public JLabel lbl_error_msg = new JLabel();
    private Point initialClick;
    iFrame if_;
    JPanel Registro = new JPanel();

    public MySQL4Java() {
        this.fnc = new funciones();
        if_ = new iFrame(300, 250, 4, 40);

        if_.setUndecorated(true);

        Container c = if_.getContentPane();
        c.setBackground(new Color(40, 43, 48));

        initComponents();
        if_.finalice();
    }
    

    public void initComponents() {
        
        iButton btn = new iButton(" ACEPTAR ", 4, new Color(54, 57, 62), Color.white);
        btn.setForeground(Color.white);
        iTextField txt_username = new iTextField("", 10);

        JPasswordField txt_password = new JPasswordField();
        JLabel lbl_username = new JLabel();
        JLabel lbl_pass = new JLabel();

        JLabel lbl_Registrar = new JLabel();

        iButton btnRegistrar = new iButton("Registar ", 4, new Color(54, 57, 62), Color.white);
        btnRegistrar.setForeground(Color.white);

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
            public void mouseDragged(MouseEvent e) {
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

//        lbl_img.setBounds(if_.getRectangle(98, 150));
        lbl_username.setForeground(Color.WHITE);
        lbl_pass.setForeground(Color.WHITE);

        if_.AddObject(lbl_username, 80, 30, "Nombre");
        if_.AddObject(txt_username, 120, 30);
        if_.newLine();

        if_.AddObject(lbl_pass, 80, 30, "Password");
        if_.AddObject(txt_password, 120, 30);
        if_.newLine();

        //btn aceptar
        btn.setBounds(if_.getRectangle(200, 30));

        // btn registar 
        lbl_Registrar.setForeground(Color.WHITE);
        if_.AddObject(lbl_Registrar, 80, 30, "REGISTAR");
        if_.newLine();
        if_.add(btnRegistrar);
        btnRegistrar.setForeground(Color.white);
        btnRegistrar.setBounds(if_.getRectangle(200, 30));
        if_.newLine();

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                btnMouseClicked(txt_username.getText(), new String(txt_password.getPassword()));
            }

        });

        btnRegistrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if_.dispose();
                Registro regis =new Registro();
            }

        });

        lbl_error_msg.setBounds(if_.getRectangle(200, 25));
        if_.add(lbl_close);
        if_.add(lbl_move);
        if_.add(lbl_minimize);

        if_.add(lbl_username);
        if_.add(txt_username);
        if_.add(lbl_pass);
        if_.add(txt_password);

        if_.add(btn);
        if_.add(lbl_error_msg);
        if_.add(lbl_Registrar);
        if_.add(btnRegistrar);

        if_.setLocationRelativeTo(null);//Para que lo centre el login
        if_.pack();
    }
// boton de inicio para hacer login 
    public void btnMouseClicked(String nom, String pass) {
        if (nom.isEmpty() || pass.isEmpty()) {
            lbl_error_msg.setText("Empty user or Password");
            return;
        }
        ArrayList<Object> arr = new ArrayList<>();
        //arr.addAll(Arrays.asList(nom, pass));
        arr.addAll(Arrays.asList(nom, fnc.MD5(pass)));

        ResultSet rs = sql.SELECT(""
                + "SELECT `id`, `nombre`, `password` "
                + "FROM `Cesar_login1` "
                + "WHERE `nombre`=? AND `password`=?",
                arr);

        if (!sql.Exists(rs)) {
            lbl_error_msg.setText("Invalid User or Password");
        } else {
            try {
                rs.next();
                  lbl_error_msg.setForeground(Color.green);
            lbl_error_msg.setText("login correct.");
            Dashboard dashboard = new Dashboard(rs.getObject("nombre"));
            if_.setVisible(false);
                
                
            } catch (SQLException ex) {
               
            }

    }
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()
                -> {
            MySQL4Java mySQL4Java = new MySQL4Java();
        });

    }

}
