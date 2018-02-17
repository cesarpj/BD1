/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primer.examen.progra.pkg1;

import iComponents.iButton;
import iComponents.iFrame;
import iComponents.iPanel;
import iComponents.iTable;
import java.awt.AWTEventMulticaster;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

/**
 *
 * @author jorge.vasquez
 */
public class Dashboard extends JFrame {
//   public ULatinaLayOut ul, ultmp;

    public JLabel lbl_error_msg = new JLabel();

    private final SQL sql = new SQL();
    private Point initialClick;
    private iFrame if_ = null;
    private final funciones FNC;

    private JLabel lbl_username = new JLabel();
    // form principal 

    Dashboard(Object object) {

//         this.ul = new ULatinaLayOut(800, 600, 4);
////        this.sql = new SQL();
        this.FNC = new funciones();

//        setTitle("Dashboard Module");
//        setLayout(ul.getLayOut());
//        setPreferredSize(ul.setComponentDimension());
//        setResizable(false);
//        initComponents();
        if_ = new iFrame(800, 600, 4);
        if_.setUndecorated(true);
        if_.getContentPane().setBackground(Color.WHITE);
        initComponents();

        if_.border(1, 1, 1, 1, new Color(236, 236, 236));

        lbl_username.setText("User: " + object.toString());
        if_.finalice();
    }

    private void initComponents() {
        Header();
        LeftPanel();
    }

    /*
    public void btn_update_mouseClicked() 
    {
        JLabel lbl_change = new JLabel();
        JTextField txt_change = new JTextField();
        JLabel lbl_nombre = new JLabel();
        JTextField txt_nombrenuevo = new JTextField();
        JButton btn_updateSQL = new JButton();

        ultmp = new ULatinaLayOut(300, 300, 4);
        JFrame tmpFrame = new JFrame();
        tmpFrame.setLayout(ultmp.getLayOut());
        tmpFrame.setSize(ultmp.setComponentDimension());

        Object[][] obj
                = {
                    {lbl_change, 80, 30, "User ID"},
                    {txt_change, 140, 30}
                };
        ultmp.setRow(obj);

        Object[][] obj1
                = {
                    {lbl_nombre, 80, 30, "Nuevo nombre"},
                    {txt_nombrenuevo, 140, 30}
                };
        ultmp.setRow(obj1);

        btn_updateSQL.setText("Actualizar Usuario");
        btn_updateSQL.setBounds(ultmp.getRectangle(220, 30));
        btn_updateSQL.addActionListener((a) -> {
            ArrayList<Object> objs = new ArrayList<>();
            objs.addAll(Arrays.asList(txt_nombrenuevo.getText(), txt_change.getText()));
            boolean result = sql.exec("UPDATE isaac_login SET username=? WHERE userid=?", objs);
            if (result) {
                JOptionPane.showMessageDialog(null, "Usuario modificado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar");
            }
        });

        tmpFrame.add(lbl_change);
        tmpFrame.add(txt_change);
        tmpFrame.add(lbl_nombre);
        tmpFrame.add(txt_nombrenuevo);
        tmpFrame.add(btn_updateSQL);

        tmpFrame.setVisible(true);
    }
     */
    private void Header() {
        IconFontSwing.register(FontAwesome.getIconFont());
        Icon icon;

        iPanel ip = new iPanel(200, 0, 600, 50, 4, 0);
        ip.setBackground(Color.WHITE);
        ip.border(0, 0, 1, 0, new Color(236, 236, 236));

        icon = IconFontSwing.buildIcon(FontAwesome.TIMES, 16, new Color(200, 200, 200));
        JLabel lbl_close = new JLabel(icon);
        lbl_close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                System.exit(0);
            }
        });

        icon = IconFontSwing.buildIcon(FontAwesome.WINDOW_RESTORE, 11, new Color(200, 200, 200));
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
        icon = IconFontSwing.buildIcon(FontAwesome.WINDOW_MINIMIZE, 11, new Color(200, 200, 200));
        JLabel lbl_minimize = new JLabel(icon);
        lbl_minimize.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if_.setState(JFrame.ICONIFIED);
            }
        });

        ip.AddObject(lbl_close, 40, 40, "", iPanel.RIGHT);
        ip.AddObject(lbl_move, 40, 40, "", iPanel.RIGHT);
        ip.AddObject(lbl_minimize, 40, 40, "", iPanel.RIGHT);
        ip.newLine();
        if_.add(ip);
    }

    private void LeftPanel() {

        iPanel topMenu = new iPanel(0, 0, 200, 50, 0, 14);
        ULatinaLayOut nsPanel = new ULatinaLayOut(160, 500, 4);
        topMenu.setLayout(nsPanel.getLayOut());
        topMenu.setSize(nsPanel.setComponentDimension());
        topMenu.setBackground(new Color(48, 44, 43));

        // insertar el tiempo 
        //
        topMenu.setBackground(new Color(30, 30, 30));
        topMenu.border(0, 0, 1, 0, Color.BLACK);

        lbl_username.setForeground(Color.white);
        lbl_username.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        lbl_username.setBounds(topMenu.getRectangle(180, 20));
        lbl_username.setText("USERNAME");
        topMenu.add(lbl_username);

        if_.add(topMenu);

        iPanel Menu = new iPanel(0, 50, 200, 600, 4, 10);
        Menu.setBackground(new Color(40, 40, 40));
// me tiene que tirar tosos los usurios  en el panel 
        iButton btn_usuarios = new iButton("Usuarios", 4, new Color(54, 57, 62), Color.white);
        btn_usuarios.setBounds(Menu.getRectangle(180, 30));

        btn_usuarios.addActionListener((ae) -> {
            btnUsuarios_mouseClicked();
            if_.repaint();
        });

        iButton btn_crearTabla = new iButton("Crear Tabla", 4, new Color(54, 57, 62), Color.white);
        btn_crearTabla.setBounds(Menu.getRectangle(180, 30));

        btn_crearTabla.addActionListener((ae) -> {
//            btn_update_mouseClicked();
        });
        iButton btn_cargarTabla = new iButton("Cargar Tabla", 4, new Color(54, 57, 62), Color.white);
        btn_cargarTabla.setBounds(Menu.getRectangle(180, 30));

        btn_cargarTabla.addActionListener((ae) -> {
            btn_CargarTabla();
        });
        iButton btn_MostarNuevaTabla = new iButton("Mostar Tabla Nueva", 4, new Color(54, 57, 62), Color.white);
        btn_MostarNuevaTabla.setBounds(Menu.getRectangle(180, 30));

        btn_MostarNuevaTabla.addActionListener((ae) -> {
            //btn_update_mouseClicked();
        });

        iButton btn_CualquierTabla = new iButton("Mostrar cualquier Tabla", 4, new Color(54, 57, 62), Color.white);
        btn_CualquierTabla.setBounds(Menu.getRectangle(180, 30));

        btn_CualquierTabla.addActionListener((ae) -> {
            //btn_update_mouseClicked();
        });

        iButton btn_EditarFila = new iButton("Editar fila Cualquier Tabla ", 4, new Color(54, 57, 62), Color.white);
        btn_EditarFila.setBounds(Menu.getRectangle(180, 30));

        btn_EditarFila.addActionListener((ae) -> {
            //btn_update_mouseClicked();
        });

        iButton btn_EliminarFila = new iButton("Eliminar Fila Cualquier Tabla", 4, new Color(54, 57, 62), Color.white);
        btn_EliminarFila.setBounds(Menu.getRectangle(180, 30));

        btn_EliminarFila.addActionListener((ae) -> {
            //btn_update_mouseClicked();
        });

        iButton btn_Chat = new iButton("Ir a Chat", 4, new Color(54, 57, 62), Color.white);
        btn_Chat.setBounds(Menu.getRectangle(180, 30));

        btn_Chat.addActionListener((ae) -> {
            //btn_update_mouseClicked();
        });
        iButton btn_salir = new iButton("SALIR", 4, new Color(54, 57, 62), Color.RED);
        btn_salir.setBounds(Menu.getRectangle(180, 30));

        btn_salir.addActionListener((ae) -> {
           btn_Salir();
        });
        Menu.add(btn_usuarios); 
        Menu.add(btn_crearTabla);
        Menu.add(btn_cargarTabla);
        Menu.add(btn_MostarNuevaTabla);
        Menu.add(btn_CualquierTabla);
        Menu.add(btn_EditarFila);
        Menu.add(btn_EliminarFila);

        Menu.add(btn_salir);

        Menu.add(btn_Chat);

        if_.add(Menu);
    }

    public void btnUsuarios_mouseClicked() {
        ArrayList<String> cols = new ArrayList<>(Arrays.asList("id", "nombre", "password", "nombreMostar"));
        iPanel ip = new iPanel(215, 90, 570, 460, 4);

        iTable table = new iTable(cols);
        ip.setBackground(Color.yellow);

        ResultSet rs = sql.SELECT("SELECT `id`, `nombre`, `password`,`nombreMostrar`"
                + " FROM `Cesar_login1`",
                new ArrayList<>()
        );

        if (sql.Exists(rs)) {
            try {
                while (rs.next()) {
                    Object[] result = {
                        rs.getObject("id"),
                        rs.getObject("nombre"),
                        rs.getObject("password"),
                        rs.getObject("nombreMostrar")
                    };
                    FNC.addrow(table, result);

//                    Object[] row = new Object[rs.getMetaData().getColumnCount()];
//                    for (int i = 1; i < rs.getMetaData().getColumnCount(); i++)
//                        row[i - 1] = rs.getObject(i);
//                    
//                    table.addrow(row);
                }
            } catch (SQLException ex) {
                System.out.println("no object fetch'd");
            }
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 570, 460);
        //ip.add(btn_filter);
        ip.add(scrollPane);
        ip.finalice();
        if_.add(ip);

        /*
        JButton btn_filter = new JButton();
        btn_filter.setText("Filtrar");
        
        btn_filter.setBounds(0, 0, 100, 30);
        btn_filter.addActionListener((ae) -> 
        {
            JFrame tmpFrame = new JFrame();

            tmpFrame.setLayout(new GridLayout(2,3));
            tmpFrame.setSize(ultmp.setComponentDimension());
            for (String columna : cols) 
            {            
                JCheckBox checkCol = new JCheckBox();
                checkCol.setText(columna);
                
                tmpFrame.add(checkCol);
            }
            tmpFrame.setVisible(true);        
        });
         */
    }

    // Crear tablas sin el heidi pero que si salga al heidi
    public void btn_Tablas() {
        
        // hacer un jfram con cuantas columnas y 
//        un txt y un label y tipo de dato 
//                se tiene que hacer un creat table a la bad 
               
        ArrayList<String> cols = new ArrayList<>(Arrays.asList("id", "nombre", "apellido", "edad"));
        iPanel ip = new iPanel(215, 90, 570, 460, 4);

        iTable table = new iTable(cols);
        ip.setBackground(Color.yellow);

        ResultSet rs = sql.SELECT("Create Table `Cesar_Prueba`(`id`int AUTO_INCREMENT,"
                + "`nombre` VARCHAR (30),"
                + " `apellido` VARCHAR (30 ), "
                + "`edad` VARCHAR (30),"
                + "Primary key (`id` ))",
                new ArrayList<>()
        );

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 570, 460);
        //ip.add(btn_filter);
        ip.add(scrollPane);
        ip.finalice();
        if_.add(ip);

    }

    // Cargarr los valores a la nueva tabla 
    public void btn_CargarTabla() {

        iButton btn = new iButton("Cargar Tabla", 4, new Color(54, 57, 62), Color.white);
        btn.setForeground(Color.white);
        iButton btn2 = new iButton("Regresar", 4, new Color(54, 57, 62), Color.white);
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

        lbl_logo.setForeground(Color.WHITE);
        ;
        lbl_logo.setBounds(if_.getRectangle(98, 40));

        lbl_username.setForeground(Color.WHITE);
        lbl_pass.setForeground(Color.WHITE);
        lbl_nombre.setForeground(Color.WHITE);

        if_.AddObject(lbl_username, 80, 30, "Nombre ");
        if_.AddObject(txt_username, 120, 30);
        if_.newLine();

        if_.AddObject(lbl_pass, 80, 30, "apellido");
        if_.AddObject(txt_password, 120, 30);
        if_.newLine();

        if_.AddObject(lbl_nombre, 80, 30, "edad ");
        if_.AddObject(txt_nombre, 120, 30);
        if_.newLine();

        btn.setBounds(if_.getRectangle(200, 30));
        btn.addActionListener((a) -> {
            ArrayList<Object> objs = new ArrayList<>();
            objs.addAll(Arrays.asList(txt_username.getText(),
                    txt_password.getText(),
                    txt_nombre.getText()));

            boolean result = sql.exec("INSERT INTO "
                    + "Cesar_Prueba"
                    + "(`nombre`, `apellido`, `edad`) "
                    + "VALUES (?,?,?)", objs);

            if (result) {
                JOptionPane.showMessageDialog(null, "Persona Agregada");
            } else {
                JOptionPane.showMessageDialog(null, "No se puede agregar");
            }
        });

        btn2.setBounds(if_.getRectangle(200, 30));
        btn2.addActionListener((a) -> {
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

    public void btn_MostrarNuevaTabla() {
               
                    // **HACER UN IF ELSE SI NO ESTA CREADA LA TABLA NO PUEDE MOSTAR 
                    // SI ESTA SI QUE LA MUESTRE 

        ArrayList<String> cols = new ArrayList<>(Arrays.asList("ID", "Edad", "Apellido", "Eda"));
        iPanel ip = new iPanel(215, 90, 570, 460, 4);

        iTable table = new iTable(cols);
        ip.setBackground(Color.yellow);

        ResultSet rs = sql.SELECT("SELECT `id`, `nombre`, `apellido`,`edad`"
                + " FROM `Cesar_Prueba`",
                new ArrayList<>()
        );

        if (sql.Exists(rs)) {
            try {
                while (rs.next()) {
                    Object[] result = {
                        rs.getObject("id"),
                        rs.getObject("nombre"),
                        rs.getObject("apellido"),
                        rs.getObject("edad")
                    };
                    FNC.addrow(table, result);
             
//                    Object[] row = new Object[rs.getMetaData().getColumnCount()];
//                    for (int i = 1; i < rs.getMetaData().getColumnCount(); i++)
//                        row[i - 1] = rs.getObject(i);
//                    
//                    table.addrow(row);
                }
            } catch (SQLException ex) {
                System.out.println("no object fetch'd");
            }
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 570, 460);
        //ip.add(btn_filter);
        ip.add(scrollPane);
        ip.finalice();
        if_.add(ip);

        /*
        JButton btn_filter = new JButton();
        btn_filter.setText("Filtrar");
        
        btn_filter.setBounds(0, 0, 100, 30);
        btn_filter.addActionListener((ae) -> 
        {
            JFrame tmpFrame = new JFrame();

            tmpFrame.setLayout(new GridLayout(2,3));
            tmpFrame.setSize(ultmp.setComponentDimension());
            for (String columna : cols) 
            {            
                JCheckBox checkCol = new JCheckBox();
                checkCol.setText(columna);
                
                tmpFrame.add(checkCol);
            }
            tmpFrame.setVisible(true);        
        });
         */
    }

    public void btn_CualquerTabla() {

        //**REVISAR CODIGO
        // que muestre cualquier tabla de la base de datos por el nombre de la base de datos 
        
        // ** que el muestreo sea con un cbox que muestre toda las tablas , que se escoja cual se quiera mostar 
        
        
        // show tables;
        // hacer el cbox , cuando se sda en el boton aceptar 
        // cuando se da aceptar tire la tabala como mostar usuarios 
        ArrayList<String> cols = new ArrayList<>(Arrays.asList("id", "nombre", "password", "nombreMostar"));
        iPanel ip = new iPanel(215, 90, 570, 460, 4);

        iTable table = new iTable(cols);
        ip.setBackground(Color.yellow);

        ResultSet rs = sql.SELECT("SELECT `id`, `nombre`, `password`,`nombreMostrar`"
                + " FROM `Cesar_login1`",
                new ArrayList<>()
        );

        if (sql.Exists(rs)) {
            try {
                while (rs.next()) {
                    Object[] result = {
                        rs.getObject("id"),
                        rs.getObject("nombre"),
                        rs.getObject("password"),
                        rs.getObject("nombreMostrar")
                    };
                    FNC.addrow(table, result);

//                    Object[] row = new Object[rs.getMetaData().getColumnCount()];
//                    for (int i = 1; i < rs.getMetaData().getColumnCount(); i++)
//                        row[i - 1] = rs.getObject(i);
//                    
//                    table.addrow(row);
                }
            } catch (SQLException ex) {
                System.out.println("no object fetch'd");
            }
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 570, 460);
        //ip.add(btn_filter);
        ip.add(scrollPane);
        ip.finalice();
        if_.add(ip);

        /*
        JButton btn_filter = new JButton();
        btn_filter.setText("Filtrar");
        
        btn_filter.setBounds(0, 0, 100, 30);
        btn_filter.addActionListener((ae) -> 
        {
            JFrame tmpFrame = new JFrame();

            tmpFrame.setLayout(new GridLayout(2,3));
            tmpFrame.setSize(ultmp.setComponentDimension());
            for (String columna : cols) 
            {            
                JCheckBox checkCol = new JCheckBox();
                checkCol.setText(columna);
                
                tmpFrame.add(checkCol);
            }
            tmpFrame.setVisible(true);        
        });
         */
    }

    // editar las tablas 
    public void btn_EditarFila() {
   // ** que el sea sea con un cbox que muestre toda las tablas  d el abase , que se escoja cual se quiera editar 
   // y con el id , se edite la fila 
        //** que edite cualquier fila de cualquier tabla de 
        //de la BD , por el acceso debe ser con el nombre de la tabla **//
        
//        get.row id
//                
//                label
                        
                        
                        
        
//     JLabel lbl_anomestaciones = new JLabel();
//        JTextField txt_nuevasamonestaciones = new JTextField();
//               
//               
//        JLabel lbl_change = new JLabel();
//        JTextField txt_change = new JTextField();
//        
//        
//       JLabel lbl_nombre = new JLabel();
//        JTextField txt_nombrenuevo = new JTextField();
//        
//        JLabel lbl_password = new JLabel();
//        JTextField txt_nuevapassword = new JTextField();
//
// 
//
//        JLabel lbl_nombreMostrar = new JLabel();
//        JTextField txt_nuevoNombreMostrar = new JTextField();
//
//        JButton btn_updateSQL = new JButton();
//
//        ultmp = new ULatinaLayOut(500, 500, 6);
//        JFrame tmpFrame = new JFrame();
//        tmpFrame.setLayout(ultmp.getLayOut());
//        tmpFrame.setSize(ultmp.setComponentDimension());
//
//        Object[][] obj
//                = {
//                    {lbl_change, 200, 30, "ID "},
//                    {txt_change, 140, 30}
//                };
//        ultmp.setRow(obj);
//     
/////////////////////////////////////////////////////
//        Object[][] obj1
//                = {
//                    {lbl_nombre, 200, 30, "Nuevo Nombre "},
//                    {txt_nombrenuevo, 140, 30}
//                };
//        ultmp.setRow(obj1);
////////////////////////////////////////////////
//        Object[][] obj2
//                = {
//                    {lbl_password, 200, 30, "Nueva Password "},
//                    {txt_nombrenuevo, 140, 30}
//                };
//        ultmp.setRow(obj2);
//        
//         Object[][] obj4
//                = {
//                    {lbl_nombreMostrar, 200, 30, "Nuevo nombre mostrar  "},
//                    {txt_nuevoNombreMostrar, 140, 30}
//                };
//        ultmp.setRow(obj4);
//        
//        
//            
//
//
//        btn_updateSQL.setText("Actualizar Persona");
//        btn_updateSQL.setBounds(ultmp.getRectangle(220, 30));
//        btn_updateSQL.addActionListener((a) -> {
//            
//            
//            ArrayList<Object> objs = new ArrayList<>();
//            objs.addAll(Arrays.asList(txt_nombrenuevo.getText(), txt_change.getText(),
//                    txt_nuevapassword.getText(),txt_change.getText(),
//                    txt_nuevoNombreMostrar.getText(),txt_change.getText()
//                    ));
//            boolean result = sql.exec("UPDATE Cesar_login1 SET id =?"
//                    + " WHERE nombre=?"
//                    + " WHERE password=?  +"
//                    + "WHERE  nombreMostrar=?", objs);
//
//            if (result) {
//                JOptionPane.showMessageDialog(null, "Persona modificado");
//            } else {
//                JOptionPane.showMessageDialog(null, "Error al modificar");
//            }
//        });
//
//        tmpFrame.add(lbl_change);
//        tmpFrame.add(txt_change);
//
//        tmpFrame.add(lbl_nombre);
//        tmpFrame.add(txt_nombrenuevo);
//
//        tmpFrame.add(lbl_password);
//        tmpFrame.add(txt_nuevapassword);
//     
//        
//        tmpFrame.add(lbl_nombreMostrar);
//        tmpFrame.add (txt_nuevoNombreMostrar);
//
//        tmpFrame.add(btn_updateSQL);
//
//        tmpFrame.setVisible(true);
//        
    }

    public void btn_EliminaFila() {
        
        //   // ** que el elimine  sea con un cbox que muestre toda las tablas , que se escoja cual se quiera eliminar 
        // que se elimine la fila con el id 
        //** que elimine cualquier fila de cualquier tabla de 
        //de la BD , por el acceso debe ser con el nombre de la tabla **//
        // que elimine la final por el id 
    }

//public boolean eliminar(Persona objPersona) throws Exception{
//boolean rpta = false;
//Connection con = null;
//PreparedStatement ps = null;
//try {
//if (objPersona != null) {
//con = DBManager.getConnection();
//String sql = "DELETE FROM Persona WHERE id=?;";
//ps = con.prepareStatement(sql);
//ps.setInt(1,objPersona.getId());
//rpta = ps.executeUpdate() == 1;
//}
//} catch (Exception e) {
//e.printStackTrace();
//} finally {
//try {
//ps.close();
//con.close();
//} catch (Exception e) {
//e.printStackTrace();
//}
//}
//return rpta;
    public void btn_Salir() {
if_.dispose();
MySQL4Java mySQL4Java = new MySQL4Java();


    }

}
