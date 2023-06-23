package Ventanas;

import static djmeb.validaciones.ValidarLetrNum;
import static djmeb.validaciones.ValidarLongitud;
import static djmeb.validaciones.VerificarEmail;
import djmeb.Bodeguero;
import djmeb.validaciones;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class VentanaRegistrarBodeguero extends javax.swing.JFrame {

    public VentanaRegistrarBodeguero() {
        initComponents();
        mostrardatos("");
        CodEmpleado.setText(String.valueOf(codIncrement()));
        CodEmpleado.setEnabled(false);
        this.setLocationRelativeTo(null);
        
        SpinnerNumberModel n = new SpinnerNumberModel();
        n.setMinimum(18);
        n.setMaximum(60);
        n.setValue(18);
        EdadSpiner.setModel(n);

    }

    void mostrardatos(String cedula) {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        modelo.addColumn("E#");
        modelo.addColumn("C#");
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Edad");
        modelo.addColumn("Correo ");
        modelo.addColumn("Direccion");
        modelo.addColumn("Genero ");
        modelo.addColumn("Telefono ");
        modelo.addColumn("Horario");
        modelo.addColumn("Salario ");
        modelo.addColumn("Area");
        jTable1.setModel(modelo);

        // Acumular la sentencia
        String sql = "";

        if (cedula.equals("")) {

            sql = "SELECT E.persona_id, B.bodeguero_codigo ,P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, E.empleado_horario, E.empleado_salario, B.bodeguero_area FROM persona P, empleado E, registrobodegueros B WHERE P.persona_id = E.persona_id AND  E.empleado_codigo = B.empleado_codigo AND B.bodeguero_estado = 'A' ORDER BY B.bodeguero_codigo";

        } else {

            sql = "SELECT E.persona_id, B.bodeguero_codigo ,P.persona_cedula, P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, E.empleado_horario, E.empleado_salario, B.bodeguero_area FROM persona P, empleado E, registrobodegueros B WHERE P.persona_cedula='" + cedula + "'" + " AND P.persona_id = E.persona_id AND  E.empleado_codigo = B.empleado_codigo AND B.bodeguero_estado = 'A' ORDER BY B.bodeguero_codigo";

        }

        String[] datos = new String[13];

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                String edad = rs.getString(6);
                datos[5] = edad;
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);
                String salario = rs.getString(12);
                datos[11] = salario;
                datos[12] = rs.getString(13);

                //System.out.println(rs);
                modelo.addRow(datos);
            }
            jTable1.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }

    // ID persona
    public int codIncrementPersona() {

        int serie = 1;

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = cc.conectar();

        try {

            ps = cn.prepareStatement("SELECT MAX(persona_id) FROM persona");
            rs = ps.executeQuery();

            while (rs.next()) {
                serie = rs.getInt(1) + 1;
            }

        } catch (Exception e) {

            System.out.println("Error" + e.getMessage());
        }

        return serie;

    }

    // Codigo empleado
    public int codIncrementEmpleado() {

        int serie = 1;

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = cc.conectar();

        try {

            ps = cn.prepareStatement("SELECT MAX(empleado_codigo) FROM empleado");
            rs = ps.executeQuery();

            while (rs.next()) {
                serie = rs.getInt(1) + 1;

            }

        } catch (Exception e) {

            System.out.println("4 Error" + e.getMessage());
        }

        return serie;

    }

    // Codigo autoincrementar 
    public int codIncrement() {

        int serie = 1;

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = cc.conectar();

        try {

            ps = cn.prepareStatement("SELECT MAX(bodeguero_codigo) FROM registrobodegueros");
            rs = ps.executeQuery();

            while (rs.next()) {
                serie = rs.getInt(1) + 1;
                //CodEmpleado.setText(serie);
            }

        } catch (Exception e) {

            System.out.println("4 Error" + e.getMessage());
        }

        return serie;

    }

    public String eleccionGenero() {

        String genero = null;
        if (Hombre.isSelected()) {
            genero = "Masculino";
        }
        if (Mujer.isSelected()) {
            genero = "Femenino";
        }
        return genero;
    }

    public void Limpiar() {

        CodEmpleado.setText(String.valueOf(codIncrement()));
        CodEmpleado.setEnabled(false);
        Cedula.setText("");
        Cedula.setEnabled(true);
        Nombre.setText("");
        Apellido.setText("");
        Direccion.setText("");
        Correo.setText("");
        Telefono1.setText("");;
        Salario.setText("");
        EdadSpiner.setValue(18);
        Genero.clearSelection();
        Area.setSelectedIndex(0);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Genero = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        actualizar = new javax.swing.JMenuItem();
        Eliminar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Cedula = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Apellido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        EdadSpiner = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        Correo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Direccion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Hombre = new javax.swing.JRadioButton();
        Mujer = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        Salario = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        CodEmpleado = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        CrearBodeguero = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        ModificarBodeguero = new javax.swing.JButton();
        BuscarBodeguero = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        Area = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        CedulaBuscar = new javax.swing.JTextField();
        Horario = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        AvisoCorreo1 = new javax.swing.JLabel();
        dateliminados = new javax.swing.JCheckBox();
        Telefono1 = new javax.swing.JFormattedTextField();
        CodigoPersona = new javax.swing.JTextField();

        actualizar.setText("modificar");
        actualizar.setToolTipText("");
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(actualizar);

        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Eliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel1.setText("Cedula:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, 20));

        Cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CedulaActionPerformed(evt);
            }
        });
        Cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CedulaKeyTyped(evt);
            }
        });
        jPanel1.add(Cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 200, -1));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel4.setText("Nombre:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, -1));

        Nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NombreKeyTyped(evt);
            }
        });
        jPanel1.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 200, -1));

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel5.setText("Apellido:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, -1, -1));

        Apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ApellidoKeyTyped(evt);
            }
        });
        jPanel1.add(Apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 200, -1));

        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel6.setText("Edad:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, -1, -1));

        EdadSpiner.setModel(new javax.swing.SpinnerNumberModel(18, null, 60, 1));
        jPanel1.add(EdadSpiner, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 58, -1));

        jLabel7.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel7.setText("Correo:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, -1, -1));

        Correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CorreoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CorreoKeyTyped(evt);
            }
        });
        jPanel1.add(Correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 200, -1));

        jLabel8.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel8.setText("Direccion:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));
        jPanel1.add(Direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 380, 200, -1));

        jLabel9.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel9.setText("Genero:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, -1, -1));

        Hombre.setBackground(new java.awt.Color(153, 255, 255));
        Genero.add(Hombre);
        Hombre.setText("Hombre");
        jPanel1.add(Hombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 420, -1, -1));

        Mujer.setBackground(new java.awt.Color(153, 255, 255));
        Genero.add(Mujer);
        Mujer.setText("Mujer");
        jPanel1.add(Mujer, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel10.setText("Area:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 520, -1, 20));

        Salario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SalarioKeyTyped(evt);
            }
        });
        jPanel1.add(Salario, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 570, 200, -1));

        jLabel11.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel11.setText("Telefono:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel13.setText("Codigo:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, -1));

        CodEmpleado.setBackground(new java.awt.Color(0, 204, 255));
        CodEmpleado.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jPanel1.add(CodEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 40, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Cedula", "Nombre", "Apellido", "Edad", "Correo", "Direccion", "Genero", "Telofono", "Area", "Salario", "Codigo Empleado"
            }
        ));
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 810, 270));

        CrearBodeguero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonCrear.png"))); // NOI18N
        CrearBodeguero.setToolTipText("Añadir");
        CrearBodeguero.setBorder(null);
        CrearBodeguero.setBorderPainted(false);
        CrearBodeguero.setContentAreaFilled(false);
        CrearBodeguero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CrearBodeguero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearBodegueroActionPerformed(evt);
            }
        });
        jPanel1.add(CrearBodeguero, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 540, 130, 120));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonEliminar.png"))); // NOI18N
        jButton1.setToolTipText("Eliminar");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 540, 129, 120));

        ModificarBodeguero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonModificar.png"))); // NOI18N
        ModificarBodeguero.setToolTipText("Modificar");
        ModificarBodeguero.setBorder(null);
        ModificarBodeguero.setBorderPainted(false);
        ModificarBodeguero.setContentAreaFilled(false);
        ModificarBodeguero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ModificarBodeguero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarBodegueroActionPerformed(evt);
            }
        });
        jPanel1.add(ModificarBodeguero, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 540, 130, 120));

        BuscarBodeguero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconoBuscar.png"))); // NOI18N
        BuscarBodeguero.setBorderPainted(false);
        BuscarBodeguero.setContentAreaFilled(false);
        BuscarBodeguero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BuscarBodeguero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarBodegueroActionPerformed(evt);
            }
        });
        jPanel1.add(BuscarBodeguero, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 450, 70, 50));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonSalirCrud.png"))); // NOI18N
        jButton2.setToolTipText("Salir");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 650, 50, 50));

        jLabel12.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel12.setText("Horario:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 620, -1, 20));

        Area.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Bodega 1", "Bodega 2", "Bodega 3", "Bodega 4" }));
        jPanel1.add(Area, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 520, 200, -1));

        jLabel19.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel19.setText("Salario:");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 570, -1, 20));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IconoLimpiar.png"))); // NOI18N
        jButton3.setToolTipText("Limpiar");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 650, 50, 50));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IconoMostrar.png"))); // NOI18N
        jButton4.setToolTipText("Mostrar Datos");
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 100, 70, 60));

        CedulaBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CedulaBuscarActionPerformed(evt);
            }
        });
        CedulaBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CedulaBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CedulaBuscarKeyTyped(evt);
            }
        });
        jPanel1.add(CedulaBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 460, 200, 30));

        Horario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matutino", "Vespertino", "Nocturno" }));
        jPanel1.add(Horario, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 620, 200, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPriPOO(1).png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, 70));

        jLabel20.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 70)); // NOI18N
        jLabel20.setText("Registro Bodeguero");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BodMenu_1.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, 130, 130));

        jLabel15.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel15.setText("MOSTRAR");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 80, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel18.setText("CREAR");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 660, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel16.setText("MODIFICAR");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 660, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel3.setText("ELIMNAR");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 660, -1, -1));

        AvisoCorreo1.setForeground(new java.awt.Color(255, 0, 0));
        AvisoCorreo1.setText("Email Incorrecto *");
        jPanel1.add(AvisoCorreo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 310, 110, 20));

        dateliminados.setBackground(new java.awt.Color(153, 255, 255));
        dateliminados.setText("Ver Eliminados");
        dateliminados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateliminadosMouseClicked(evt);
            }
        });
        dateliminados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateliminadosActionPerformed(evt);
            }
        });
        jPanel1.add(dateliminados, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 460, -1, -1));

        try {
            Telefono1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#### - ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel1.add(Telefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 470, 200, -1));
        jPanel1.add(CodigoPersona, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, -1, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1248, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CedulaActionPerformed

    private void CrearBodegueroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearBodegueroActionPerformed
        //CrearBodeguero();

        if (  Cedula.getText().isEmpty() || CodEmpleado.getText().isEmpty() || Nombre.getText().isEmpty() || Apellido.getText().isEmpty()
                || Horario.getSelectedItem().equals(0) || Horario.getSelectedItem().equals(0) || Direccion.getText().isEmpty() || Telefono1.getText().isEmpty() || Salario.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "LLene los campos corectamente");

        } else {

            if (validaciones.Cedula(Cedula.getText())) {

                PreparedStatement pstp = null;
                PreparedStatement pste = null;
                PreparedStatement pstb = null;

                String sqlp = "INSERT INTO persona(persona_id, persona_cedula , persona_nombre, persona_apellido, persona_edad, persona_correo, persona_direccion, persona_genero, persona_telefono) VALUES (?,?,?,?,?,?,?,?,?)";
                String sqle = "INSERT INTO empleado(empleado_codigo, persona_id, empleado_horario, empleado_salario) VALUES (?,?,?,?)";
                String sqlc = "INSERT INTO registrobodegueros (bodeguero_codigo,empleado_codigo, bodeguero_area, bodeguero_estado) VALUES (?,?,?,'A')";

                int id = codIncrementPersona();
                int codigo = codIncrementEmpleado();
                int cod_bodeguero = codIncrement();
                String cedula = Cedula.getText();
                String nombre = Nombre.getText();
                String apellido = Apellido.getText();
                int edad = Integer.valueOf(EdadSpiner.getValue().toString());
                String correo = Correo.getText();
                String direccion = Direccion.getText();
                String genero = eleccionGenero();
                String telefono = Telefono1.getText();
                String area = Area.getSelectedItem().toString();
                int salario = Integer.valueOf(Salario.getText());
                String horario = Horario.getSelectedItem().toString();

                Bodeguero newBodeguero = new Bodeguero(area, cod_bodeguero, codigo, salario, horario, id, cedula, nombre, apellido, edad, correo, direccion, genero, telefono);

                try {

                    pstp = cn.prepareStatement(sqlp);
                    pste = cn.prepareStatement(sqle);
                    pstb = cn.prepareStatement(sqlc);

                    pstp.setInt(1, newBodeguero.getId());
                    pstp.setString(2, newBodeguero.getCedula());
                    pstp.setString(3, newBodeguero.getNombre());
                    pstp.setString(4, newBodeguero.getApellido());
                    pstp.setInt(5, newBodeguero.getEdad());
                    pstp.setString(6, newBodeguero.getCorreo());
                    pstp.setString(7, newBodeguero.getDireccion());
                    pstp.setString(8, newBodeguero.getGenero());
                    pstp.setString(9, newBodeguero.getTelefono());

                    pste.setInt(1, newBodeguero.getCódigo());
                    pste.setInt(2, newBodeguero.getId());
                    pste.setString(3, newBodeguero.getHorario());
                    pste.setInt(4, newBodeguero.getSalario());

                    pstb.setInt(1, newBodeguero.getCod_bodeguero());
                    pstb.setInt(2, newBodeguero.getCódigo());
                    pstb.setString(3, newBodeguero.getArea_Trabajo());

                    pstp.executeUpdate();
                    pste.executeUpdate();
                    pstb.executeUpdate();
                    Limpiar();
                    mostrardatos("");

                    JOptionPane.showMessageDialog(null, "Cedula Correcta");
                    JOptionPane.showMessageDialog(null, "Se guardo");
                    Limpiar();

                } catch (HeadlessException | SQLException e) {

                    JOptionPane.showMessageDialog(null, "Cedula Existente");
                    System.out.println("Error: " + e );
                    Cedula.setText("");

                }

            } else {
                JOptionPane.showMessageDialog(null, "Cedula Incorrecta");
            }

        }
    }//GEN-LAST:event_CrearBodegueroActionPerformed

    private void ModificarBodegueroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarBodegueroActionPerformed

        if (Cedula.getText().isEmpty() || CodEmpleado.getText().isEmpty() || Nombre.getText().isEmpty() || Apellido.getText().isEmpty()
                || Horario.getSelectedItem().equals(0) || Horario.getSelectedItem().equals(0) || Direccion.getText().isEmpty() || Telefono1.getText().isEmpty() || Salario.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "LLene los campos corectamente");

        } else {

            try {

                int edad = Integer.valueOf(EdadSpiner.getValue().toString());
                int salario = Integer.valueOf(Salario.getText());

                PreparedStatement pst2 = cn.prepareStatement("UPDATE persona SET persona_nombre='" + Nombre.getText() + "', persona_apellido='" + Apellido.getText() + "',persona_edad='" + edad + "',persona_correo='" + Correo.getText() + "',persona_direccion='" + Direccion.getText() + "',persona_genero='" + eleccionGenero() + "',persona_telefono='" + Telefono1.getText() + "' WHERE persona_cedula='" + Cedula.getText() + "'");
                PreparedStatement pst3 = cn.prepareStatement("UPDATE empleado SET empleado_horario='" + Horario.getSelectedItem().toString() + "',empleado_salario='" + salario + "' WHERE persona_id = '" + CodigoPersona.getText() + "'");
                PreparedStatement pst4 = cn.prepareStatement("UPDATE registrobodegueros SET bodeguero_area ='" + Area.getSelectedItem().toString() + " ' WHERE bodeguero_codigo ='" + CodEmpleado.getText() + "'");

                pst2.executeUpdate();
                pst3.executeUpdate();
                pst4.executeUpdate();
                mostrardatos("");
                Limpiar();
                JOptionPane.showMessageDialog(null, "Actualizado");

            } catch (Exception e) {
                System.out.println(e.getMessage());
                Limpiar();
            }
        }


    }//GEN-LAST:event_ModificarBodegueroActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Menu menuInicial = new Menu();
        menuInicial.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int fila = jTable1.getSelectedRow();

        if (fila >= 0) {

            String codigo = "";
            String cedula = "";
            codigo = jTable1.getValueAt(fila, 1).toString();
            cedula = jTable1.getValueAt(fila, 2).toString();

            int confirmar = JOptionPane.showConfirmDialog(null, "Esta seguro que desea Eliminar el registro? ");

            if (JOptionPane.OK_OPTION == confirmar) {

                try {
                    PreparedStatement pst = cn.prepareStatement("UPDATE registrobodegueros SET bodeguero_estado='B' WHERE bodeguero_codigo='" + codigo + "'");
                    PreparedStatement pstU = cn.prepareStatement("UPDATE regusuario SET user_estado='B' WHERE empleado_codigo='" + codigo + "' AND persona_cedula = '" + cedula + "'");
                    
                    pst.executeUpdate();
                    pstU.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Se Elimino Correctamete" + "\n Usuario Eliminado");
                    Limpiar();
                    mostrardatos("");

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(null, "Error: " + e);
                    System.out.println(e.getMessage());

                }

            } else {
                JOptionPane.showMessageDialog(null, "No se Elimino");
            }

        } else {
            JOptionPane.showMessageDialog(null, "No seleciono fila");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Limpiar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        mostrardatos("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void CedulaBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CedulaBuscarActionPerformed

    private void BuscarBodegueroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarBodegueroActionPerformed

        mostrardatos(CedulaBuscar.getText());

        int fila = 0;

        for (int i = 0; fila < jTable1.getRowCount(); fila++) {

            jTable1.changeSelection(i, 0, false, false);

            CodigoPersona.setText(jTable1.getValueAt(fila, 0).toString());
            CodigoPersona.setEnabled(false);
            CodEmpleado.setText(jTable1.getValueAt(fila, 1).toString());
            CodEmpleado.setEnabled(false);
            Cedula.setText(jTable1.getValueAt(fila, 2).toString());
            Cedula.setEnabled(false);
            Nombre.setText(jTable1.getValueAt(fila, 3).toString());
            Apellido.setText(jTable1.getValueAt(fila, 4).toString());
            String edad = String.valueOf(jTable1.getValueAt(fila, 5));
            EdadSpiner.setValue(Integer.parseInt(edad));
            Correo.setText(jTable1.getValueAt(fila, 6).toString());
            Direccion.setText(jTable1.getValueAt(fila, 7).toString());
            String genero = String.valueOf(jTable1.getValueAt(fila, 8).toString());
            if ("Masculino".equals(genero)) {
                Hombre.setSelected(true);
            } else if ("Femenino".equals(genero)) {
                Mujer.setSelected(true);
            }
            Telefono1.setText(jTable1.getValueAt(fila, 9).toString());
            Horario.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(), 10).toString());
            Salario.setText(jTable1.getValueAt(fila, 11).toString());
            Area.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(), 12).toString());

        }
    }//GEN-LAST:event_BuscarBodegueroActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int fila = jTable1.getSelectedRow();

        if (evt.getClickCount() == 2) {

            CrearBodeguero.setEnabled(false);
            if (fila >= 0) {

                CodigoPersona.setText(jTable1.getValueAt(fila, 0).toString());
                CodigoPersona.setEnabled(false);
                CodEmpleado.setText(jTable1.getValueAt(fila, 1).toString());
                CodEmpleado.setEnabled(false);
                Cedula.setText(jTable1.getValueAt(fila, 2).toString());
                Cedula.setEnabled(false);
                Nombre.setText(jTable1.getValueAt(fila, 3).toString());
                Apellido.setText(jTable1.getValueAt(fila, 4).toString());
                String edad = String.valueOf(jTable1.getValueAt(fila, 5));
                EdadSpiner.setValue(Integer.parseInt(edad));
                Correo.setText(jTable1.getValueAt(fila, 6).toString());
                Direccion.setText(jTable1.getValueAt(fila, 7).toString());
                String genero = String.valueOf(jTable1.getValueAt(fila, 8).toString());
                if ("Masculino".equals(genero)) {
                    Hombre.setSelected(true);
                } else if ("Femenino".equals(genero)) {
                    Mujer.setSelected(true);
                }
                Telefono1.setText(jTable1.getValueAt(fila, 9).toString());
                Horario.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(), 10).toString());
                Salario.setText(jTable1.getValueAt(fila, 11).toString());
                Area.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(), 12).toString());

            } else {
                JOptionPane.showMessageDialog(null, "No seleciono fila");
            }
        } else {

            Limpiar();
            CrearBodeguero.setEnabled(true);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        int fila = jTable1.getSelectedRow();

        if (fila >= 0) {

            CodigoPersona.setText(jTable1.getValueAt(fila, 0).toString());
            CodigoPersona.setEnabled(false);
            CodEmpleado.setText(jTable1.getValueAt(fila, 1).toString());
            CodEmpleado.setEnabled(false);
            Cedula.setText(jTable1.getValueAt(fila, 2).toString());
            Cedula.setEnabled(false);
            Nombre.setText(jTable1.getValueAt(fila, 3).toString());
            Apellido.setText(jTable1.getValueAt(fila, 4).toString());
            String edad = String.valueOf(jTable1.getValueAt(fila, 5));
            EdadSpiner.setValue(Integer.parseInt(edad));
            Correo.setText(jTable1.getValueAt(fila, 6).toString());
            Direccion.setText(jTable1.getValueAt(fila, 7).toString());
            String genero = String.valueOf(jTable1.getValueAt(fila, 8).toString());
            if ("Masculino".equals(genero)) {
                Hombre.setSelected(true);
            } else if ("Femenino".equals(genero)) {
                Mujer.setSelected(true);
            }
            Telefono1.setText(jTable1.getValueAt(fila, 9).toString());
            Horario.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(), 10).toString());
            Salario.setText(jTable1.getValueAt(fila, 11).toString());
            Area.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(), 12).toString());

        } else {
            JOptionPane.showMessageDialog(null, "No seleciono fila");
        }
    }//GEN-LAST:event_actualizarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed

         int fila = jTable1.getSelectedRow();

        if (fila >= 0) {

            String codigo = "";
            String cedula = "";
            codigo = jTable1.getValueAt(fila, 1).toString();
            cedula = jTable1.getValueAt(fila, 2).toString();

            int confirmar = JOptionPane.showConfirmDialog(null, "Esta seguro que desea Eliminar el registro? ");

            if (JOptionPane.OK_OPTION == confirmar) {

                try {
                    PreparedStatement pst = cn.prepareStatement("UPDATE registrobodegueros SET bodeguero_estado='B' WHERE bodeguero_codigo='" + codigo + "'");
                    PreparedStatement pstU = cn.prepareStatement("UPDATE regusuario SET user_estado='B' WHERE empleado_codigo='" + codigo + "' AND persona_cedula = '" + cedula + "'");
                    
                    pst.executeUpdate();
                    pstU.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Se Elimino Correctamete" + "\n Usuario Eliminado");
                    Limpiar();
                    mostrardatos("");

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(null, "Error: " + e);
                    System.out.println(e.getMessage());

                }

            } else {
                JOptionPane.showMessageDialog(null, "No se Elimino");
            }

        } else {
            JOptionPane.showMessageDialog(null, "No seleciono fila");
        }
        
    }//GEN-LAST:event_EliminarActionPerformed

    private void dateliminadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateliminadosMouseClicked

        if (dateliminados.isSelected()) {

            String cedula = "";
            DefaultTableModel modelo = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int columnas) {
                    return false;
                }
            };

            modelo.addColumn("E#");
            modelo.addColumn("C#");
            modelo.addColumn("Cedula");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Edad");
            modelo.addColumn("Correo ");
            modelo.addColumn("Direccion");
            modelo.addColumn("Genero ");
            modelo.addColumn("Telefono ");
            modelo.addColumn("Horario");
            modelo.addColumn("Salario ");
            modelo.addColumn("Area");
            jTable1.setModel(modelo);

            // Acumular la sentencia
            String sql = "";

            if (cedula.equals("")) {

                sql = "SELECT E.persona_id, B.bodeguero_codigo ,P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, E.empleado_horario, E.empleado_salario, B.bodeguero_area FROM persona P, empleado E, registrobodegueros B WHERE P.persona_id = E.persona_id AND  E.empleado_codigo = B.empleado_codigo ORDER BY B.bodeguero_codigo";

            }

            String[] datos = new String[13];

            try {

                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {

                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2);
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    datos[4] = rs.getString(5);
                    String edad = rs.getString(6);
                    datos[5] = edad;
                    datos[6] = rs.getString(7);
                    datos[7] = rs.getString(8);
                    datos[8] = rs.getString(9);
                    datos[9] = rs.getString(10);
                    datos[10] = rs.getString(11);
                    String salario = rs.getString(12);
                    datos[11] = salario;
                    datos[12] = rs.getString(13);
                    //System.out.println(rs);
                    modelo.addRow(datos);
                }
                jTable1.setModel(modelo);

            } catch (SQLException ex) {

                System.out.println("Error:" + ex);
            }

        } else {
            mostrardatos("");
        }
    }//GEN-LAST:event_dateliminadosMouseClicked

    private void CedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaKeyTyped
        ValidarLongitud(Cedula.getText(), 10, evt);
        ValidarLetrNum(Character.isLetter(evt.getKeyChar()), evt);
    }//GEN-LAST:event_CedulaKeyTyped

    private void NombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreKeyTyped
        ValidarLongitud(Nombre.getText(), 30, evt);
        ValidarLetrNum(Character.isDigit(evt.getKeyChar()), evt);
    }//GEN-LAST:event_NombreKeyTyped

    private void ApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ApellidoKeyTyped
        ValidarLongitud(Apellido.getText(), 30, evt);
        ValidarLetrNum(Character.isDigit(evt.getKeyChar()), evt);
    }//GEN-LAST:event_ApellidoKeyTyped

    private void CorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CorreoKeyTyped

    }//GEN-LAST:event_CorreoKeyTyped

    private void SalarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SalarioKeyTyped
        ValidarLongitud(Salario.getText(), 8, evt);
        ValidarLetrNum(Character.isLetter(evt.getKeyChar()), evt);
    }//GEN-LAST:event_SalarioKeyTyped

    private void CorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CorreoKeyReleased
        if (VerificarEmail(Correo.getText())) {

            AvisoCorreo1.setVisible(false);

        } else {

            AvisoCorreo1.setVisible(true);
        }
    }//GEN-LAST:event_CorreoKeyReleased

    private void CedulaBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaBuscarKeyTyped
        // Capturar tecla enter
        char cTeclaPresionada = evt.getKeyChar();

        // Click para aceptar el enter
        if (cTeclaPresionada == KeyEvent.VK_ENTER) {

            // Da clicl al boton al precionar ENTER
            BuscarBodeguero.doClick();
        }
    }//GEN-LAST:event_CedulaBuscarKeyTyped

    private void dateliminadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateliminadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateliminadosActionPerformed

    private void CedulaBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaBuscarKeyReleased
        if (CedulaBuscar.getText().isEmpty()) {
            mostrardatos("");
            Limpiar();
        } else {
            mostrardatos(CedulaBuscar.getText());
        }
    }//GEN-LAST:event_CedulaBuscarKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistrarBodeguero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistrarBodeguero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistrarBodeguero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistrarBodeguero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaRegistrarBodeguero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Apellido;
    private javax.swing.JComboBox<String> Area;
    private javax.swing.JLabel AvisoCorreo1;
    private javax.swing.JButton BuscarBodeguero;
    private javax.swing.JTextField Cedula;
    private javax.swing.JTextField CedulaBuscar;
    private javax.swing.JTextField CodEmpleado;
    private javax.swing.JTextField CodigoPersona;
    private javax.swing.JTextField Correo;
    private javax.swing.JButton CrearBodeguero;
    private javax.swing.JTextField Direccion;
    private javax.swing.JSpinner EdadSpiner;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.ButtonGroup Genero;
    private javax.swing.JRadioButton Hombre;
    private javax.swing.JComboBox<String> Horario;
    private javax.swing.JButton ModificarBodeguero;
    private javax.swing.JRadioButton Mujer;
    private javax.swing.JTextField Nombre;
    private javax.swing.JTextField Salario;
    private javax.swing.JFormattedTextField Telefono1;
    private javax.swing.JMenuItem actualizar;
    private javax.swing.JCheckBox dateliminados;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();

}
