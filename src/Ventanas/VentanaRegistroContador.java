package Ventanas;

import static djmeb.validaciones.ValidarLetrNum;
import static djmeb.validaciones.ValidarLongitud;
import static djmeb.validaciones.VerificarEmail;
import djmeb.Contador;
import djmeb.validaciones;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class VentanaRegistroContador extends javax.swing.JFrame {

    public VentanaRegistroContador() {
        initComponents();
        this.setLocationRelativeTo(null);
        mostrardatos("");
        CodCon.setText(String.valueOf(codIncrement()));
        CodCon.setEnabled(false);
        
        SpinnerNumberModel n = new SpinnerNumberModel();
        n.setMinimum(18);
        n.setMaximum(60);
        n.setValue(18);
        EdadSpinerCon.setModel(n);

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

            ps = cn.prepareStatement("SELECT MAX(contador_codigo) FROM registrocontador");
            rs = ps.executeQuery();

            while (rs.next()) {
                serie = rs.getInt(1) + 1;
            }

        } catch (Exception e) {

            System.out.println("4 Error" + e.getMessage());
        }

        return serie;

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
        modelo.addColumn("Correo");
        modelo.addColumn("Direccion");
        modelo.addColumn("Genero");
        modelo.addColumn("Telofono");
        modelo.addColumn("Horario");
        modelo.addColumn("Salario");
        modelo.addColumn("Bono");
        jTableContador.setModel(modelo);

        // Acumular la sentencia
        String sql = "";

        if (cedula.equals("")) {

            sql = "SELECT E.persona_id,C.contador_codigo ,P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, E.empleado_horario, E.empleado_salario, C.contador_bono FROM persona P, empleado E, registrocontador C WHERE P.persona_id = E.persona_id AND  E.empleado_codigo = C.empleado_codigo AND C.contador_estado = 'A' ORDER BY C.contador_codigo";

        } else {

            sql = "SELECT E.persona_id,C.contador_codigo ,P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, E.empleado_horario, E.empleado_salario, C.contador_bono FROM persona P, empleado E, registrocontador C WHERE P.persona_cedula='" + cedula + "'" + "AND P.persona_id = E.persona_id AND  E.empleado_codigo = C.empleado_codigo AND C.contador_estado = 'A' ORDER BY C.contador_codigo";
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
                String bono = rs.getString(11);
                datos[10] = bono;
                String salario = rs.getString(12);
                datos[11] = salario;
                datos[12] = rs.getString(13);

                modelo.addRow(datos);

            }

            jTableContador.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

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

        CodCon.setText(String.valueOf(codIncrement()));
        CodCon.setEnabled(false);
        CedulaCon.setText("");
        NombreCon.setText("");
        ApellidoCon.setText("");
        DireccionCon.setText("");
        CorreoCon.setText("");
        TelefonoCon.setText("");
        SalarioCon.setText("");
        EdadSpinerCon.setValue(18);
        GenCon.clearSelection();
        BonoCon.setText("");
        jCmbConta.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GenCon = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItemModiCont = new javax.swing.JMenuItem();
        jMenuItemElimiCont = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        CedulaCon = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        NombreCon = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ApellidoCon = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        EdadSpinerCon = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        CorreoCon = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        DireccionCon = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Hombre = new javax.swing.JRadioButton();
        Mujer = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        SalarioCon = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        BonoCon = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        CodCon = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableContador = new javax.swing.JTable();
        CrearContador = new javax.swing.JButton();
        EliminarCont = new javax.swing.JButton();
        ModificarCont = new javax.swing.JButton();
        BuscarCont = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        moscont = new javax.swing.JButton();
        jCmbConta = new javax.swing.JComboBox<>();
        CedulaBuscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        AvisoCorreo1 = new javax.swing.JLabel();
        dateliminados = new javax.swing.JCheckBox();
        TelefonoCon = new javax.swing.JFormattedTextField();
        CodigoPersona = new javax.swing.JTextField();

        jPopupMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPopupMenu1MouseClicked(evt);
            }
        });

        jMenuItemModiCont.setText("Modificar");
        jMenuItemModiCont.setToolTipText("");
        jMenuItemModiCont.setComponentPopupMenu(jPopupMenu1);
        jMenuItemModiCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemModiContActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemModiCont);

        jMenuItemElimiCont.setText("Eliminar");
        jMenuItemElimiCont.setComponentPopupMenu(jPopupMenu1);
        jMenuItemElimiCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemElimiContActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemElimiCont);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 70)); // NOI18N
        jLabel3.setText("Registro Contador");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel1.setText("Cedula:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        CedulaCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CedulaConActionPerformed(evt);
            }
        });
        CedulaCon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CedulaConKeyTyped(evt);
            }
        });
        jPanel1.add(CedulaCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 241, -1));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel4.setText("Nombre:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, -1, -1));

        NombreCon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NombreConKeyTyped(evt);
            }
        });
        jPanel1.add(NombreCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 241, -1));

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel5.setText("Apellido:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, -1));

        ApellidoCon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ApellidoConKeyTyped(evt);
            }
        });
        jPanel1.add(ApellidoCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 241, -1));

        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel6.setText("Edad:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        EdadSpinerCon.setModel(new javax.swing.SpinnerNumberModel(18, 18, null, 1));
        jPanel1.add(EdadSpinerCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 58, -1));

        jLabel7.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel7.setText("Correo:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, -1));

        CorreoCon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CorreoConKeyReleased(evt);
            }
        });
        jPanel1.add(CorreoCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 241, -1));

        jLabel8.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel8.setText("Direccion:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, -1, -1));
        jPanel1.add(DireccionCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, 241, -1));

        jLabel9.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel9.setText("Genero:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, -1, -1));

        Hombre.setBackground(new java.awt.Color(153, 255, 153));
        GenCon.add(Hombre);
        Hombre.setText("Hombre");
        jPanel1.add(Hombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 460, -1, -1));

        Mujer.setBackground(new java.awt.Color(153, 255, 153));
        GenCon.add(Mujer);
        Mujer.setText("Mujer");
        jPanel1.add(Mujer, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 460, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel10.setText("Salario:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 630, -1, -1));

        SalarioCon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SalarioConKeyTyped(evt);
            }
        });
        jPanel1.add(SalarioCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 640, 150, -1));

        jLabel11.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel11.setText("Telefono:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel12.setText("Bono:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 580, -1, -1));

        BonoCon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BonoConKeyTyped(evt);
            }
        });
        jPanel1.add(BonoCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 580, 241, -1));

        jLabel13.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel13.setText("Codigo:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        CodCon.setBackground(new java.awt.Color(0, 204, 102));
        CodCon.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jPanel1.add(CodCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 30, 30));

        jTableContador.setModel(new javax.swing.table.DefaultTableModel(
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
                "CedulaCon", "NombreCon", "ApellidoCon", "Edad", "CorreoCon", "DireccionCon", "Genero", "Telofono", "Bono", "SalarioCon", "Codigo Empleado"
            }
        ));
        jTableContador.setComponentPopupMenu(jPopupMenu1);
        jTableContador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableContadorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableContador);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 190, 740, 270));

        CrearContador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonCrear.png"))); // NOI18N
        CrearContador.setBorder(null);
        CrearContador.setBorderPainted(false);
        CrearContador.setContentAreaFilled(false);
        CrearContador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CrearContador.setDefaultCapable(false);
        CrearContador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearContadorActionPerformed(evt);
            }
        });
        jPanel1.add(CrearContador, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 560, 130, 130));

        EliminarCont.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonEliminar.png"))); // NOI18N
        EliminarCont.setBorder(null);
        EliminarCont.setBorderPainted(false);
        EliminarCont.setContentAreaFilled(false);
        EliminarCont.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EliminarCont.setDefaultCapable(false);
        EliminarCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarContActionPerformed(evt);
            }
        });
        jPanel1.add(EliminarCont, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 560, 129, 130));

        ModificarCont.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonModificar.png"))); // NOI18N
        ModificarCont.setBorder(null);
        ModificarCont.setBorderPainted(false);
        ModificarCont.setContentAreaFilled(false);
        ModificarCont.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ModificarCont.setDefaultCapable(false);
        ModificarCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarContActionPerformed(evt);
            }
        });
        jPanel1.add(ModificarCont, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 560, 130, 130));

        BuscarCont.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconoBuscar.png"))); // NOI18N
        BuscarCont.setBorderPainted(false);
        BuscarCont.setContentAreaFilled(false);
        BuscarCont.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BuscarCont.setDefaultCapable(false);
        BuscarCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarContActionPerformed(evt);
            }
        });
        jPanel1.add(BuscarCont, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 460, 50, 50));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonSalirCrud.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setDefaultCapable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 720, 60, 50));

        jLabel19.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel19.setText("Horario:");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 680, -1, -1));

        moscont.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IconoMostrar.png"))); // NOI18N
        moscont.setBorderPainted(false);
        moscont.setContentAreaFilled(false);
        moscont.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        moscont.setDefaultCapable(false);
        moscont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moscontActionPerformed(evt);
            }
        });
        jPanel1.add(moscont, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 120, 70, 60));

        jCmbConta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matutino", "Vespertino", "Nocturno" }));
        jPanel1.add(jCmbConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 680, 250, -1));

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
        jPanel1.add(CedulaBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 470, 200, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IconoLimpiar.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setDefaultCapable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 720, 50, 50));

        jLabel18.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel18.setText("CREAR");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 700, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel16.setText("MODIFICAR");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 700, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel2.setText("ELIMNAR");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 700, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel15.setText("MOSTRAR");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 100, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPriPOO(1).png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, 70));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BtoonContador1.png"))); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 200, 140));

        AvisoCorreo1.setForeground(new java.awt.Color(255, 0, 0));
        AvisoCorreo1.setText("Email Incorrecto *");
        jPanel1.add(AvisoCorreo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, 110, 20));

        dateliminados.setBackground(new java.awt.Color(153, 255, 153));
        dateliminados.setText("Ver Eliminados");
        dateliminados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateliminadosMouseClicked(evt);
            }
        });
        jPanel1.add(dateliminados, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 470, -1, -1));

        try {
            TelefonoCon.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(#) ##-#####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TelefonoCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TelefonoConActionPerformed(evt);
            }
        });
        jPanel1.add(TelefonoCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 520, 230, -1));
        jPanel1.add(CodigoPersona, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, 0, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1220, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void CedulaConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaConActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CedulaConActionPerformed

    private void CrearContadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearContadorActionPerformed

        if (CedulaCon.getText().isEmpty() || CodCon.getText().isEmpty() || NombreCon.getText().isEmpty() || ApellidoCon.getText().isEmpty()
                || VerificarEmail(CorreoCon.getText()) == CorreoCon.getText().isEmpty() || jCmbConta.getSelectedItem().equals(0) || DireccionCon.getText().isEmpty() || TelefonoCon.getText().isEmpty() || SalarioCon.getText().isEmpty() || BonoCon.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "LLene los campos corectamente");

        } else {

            if (validaciones.Cedula(CedulaCon.getText())) {

                PreparedStatement pstp = null;
                PreparedStatement pste = null;
                PreparedStatement pstc = null;

                String sqlp = "INSERT INTO persona(persona_id, persona_cedula , persona_nombre, persona_apellido, persona_edad, persona_correo, persona_direccion, persona_genero, persona_telefono) VALUES (?,?,?,?,?,?,?,?,?)";
                String sqle = "INSERT INTO empleado(empleado_codigo, persona_id, empleado_horario, empleado_salario) VALUES (?,?,?,?)";
                String sqlc = "INSERT INTO registrocontador (contador_codigo,empleado_codigo, contador_bono, contador_estado) VALUES (?,?,?,'A')";

                int id = codIncrementPersona();
                int codigo = codIncrementEmpleado();
                int cod_contador = codIncrement();
                String cedula = CedulaCon.getText();
                String nombre = NombreCon.getText();
                String apellido = ApellidoCon.getText();
                int edad = Integer.valueOf(EdadSpinerCon.getValue().toString());
                String correo = CorreoCon.getText();
                String direccion = DireccionCon.getText();
                String genero = eleccionGenero();
                String telefono = TelefonoCon.getText();
                int salario = Integer.valueOf(SalarioCon.getText());
                String horario = jCmbConta.getSelectedItem().toString();
                double bono = Double.valueOf(BonoCon.getText());

                Contador newCont = new Contador(bono, cod_contador, codigo, salario, horario, id, cedula, nombre, apellido, edad, correo, direccion, genero, telefono);

                try {

                    pstp = cn.prepareStatement(sqlp);
                    pste = cn.prepareStatement(sqle);
                    pstc = cn.prepareStatement(sqlc);

                    pstp.setInt(1, newCont.getId());
                    pstp.setString(2, newCont.getCedula());
                    pstp.setString(3, newCont.getNombre());
                    pstp.setString(4, newCont.getApellido());
                    pstp.setInt(5, newCont.getEdad());
                    pstp.setString(6, newCont.getCorreo());
                    pstp.setString(7, newCont.getDireccion());
                    pstp.setString(8, newCont.getGenero());
                    pstp.setString(9, newCont.getTelefono());

                    pste.setInt(1, newCont.getCódigo());
                    pste.setInt(2, newCont.getId());
                    pste.setString(3, newCont.getHorario());
                    pste.setInt(4, newCont.getSalario());

                    pstc.setInt(1, newCont.getCod_contador());
                    pstc.setInt(2, newCont.getCódigo());
                    pstc.setDouble(3, newCont.getBono());

                    pstp.executeUpdate();
                    pste.executeUpdate();
                    pstc.executeUpdate();
                    Limpiar();
                    mostrardatos("");

                    JOptionPane.showMessageDialog(null, "Cedula Correcta");
                    JOptionPane.showMessageDialog(null, "Se guardo");
                    Limpiar();

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(null, "Cedula Ya existente");
                    System.out.println("Error: " + e);
                    CedulaCon.setText("");
                }

            } else {

                JOptionPane.showMessageDialog(null, "Cedula Incorrecta");
            }

        }
    }//GEN-LAST:event_CrearContadorActionPerformed

    private void ModificarContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarContActionPerformed

        if (CedulaCon.getText().isEmpty() || CodCon.getText().isEmpty() || NombreCon.getText().isEmpty() || ApellidoCon.getText().isEmpty()
                || jCmbConta.getSelectedItem().equals(0) || DireccionCon.getText().isEmpty() || TelefonoCon.getText().isEmpty() || SalarioCon.getText().isEmpty() || BonoCon.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "LLene los campos corectamente");

        } else {

            CedulaCon.setEnabled(true);
            
            try {
                int edad = Integer.valueOf(EdadSpinerCon.getValue().toString());
                int salario = Integer.valueOf(SalarioCon.getText());
                double bono = Double.valueOf(BonoCon.getText());

                PreparedStatement pst2 = cn.prepareStatement("UPDATE persona SET persona_nombre='" + NombreCon.getText() + "', persona_apellido='" + ApellidoCon.getText() + "',persona_edad='" + edad + "',persona_correo='" + CorreoCon.getText() + "',persona_direccion='" + DireccionCon.getText() + "',persona_genero='" + eleccionGenero() + "',persona_telefono='" + TelefonoCon.getText() + "' WHERE persona_cedula='" + CedulaCon.getText() + "'");
                PreparedStatement pst3 = cn.prepareStatement("UPDATE empleado SET empleado_horario='" + jCmbConta.getSelectedItem().toString() + "',empleado_salario='" + salario + "' WHERE persona_id = '" + CodigoPersona.getText() + "'");
                PreparedStatement pst4 = cn.prepareStatement("UPDATE registrocontador SET contador_bono ='" + bono + " ' WHERE contador_codigo ='" + CodCon.getText() + "'");

                pst2.executeUpdate();
                pst3.executeUpdate();
                pst4.executeUpdate();
                mostrardatos("");
                Limpiar();
                JOptionPane.showMessageDialog(null, "Actualizado");

            } catch (Exception e) {
                System.out.println("" + e.getMessage());
                Limpiar();
            }

        }
    }//GEN-LAST:event_ModificarContActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Menu menuInicial = new Menu();
        menuInicial.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void moscontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moscontActionPerformed
        mostrardatos("");
    }//GEN-LAST:event_moscontActionPerformed

    private void EliminarContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarContActionPerformed

        int fila = jTableContador.getSelectedRow();

        if (fila >= 0) {

            String codigo = "";
            String cedula = "";
            codigo = jTableContador.getValueAt(fila, 1).toString();
            cedula = jTableContador.getValueAt(fila, 2).toString();

            int confirmar = JOptionPane.showConfirmDialog(null, "¿ Esta seguro que desea Eliminar el registro?");

            if (JOptionPane.OK_OPTION == confirmar) {

                try {

                    PreparedStatement pst = cn.prepareStatement("UPDATE registrocontador SET contador_estado='B' WHERE contador_codigo='" + codigo + "'");
                    PreparedStatement pstU = cn.prepareStatement("UPDATE regusuario SET user_estado='B' WHERE empleado_codigo='" + codigo + "' AND persona_cedula = '" + cedula + "'");
                    pst.executeUpdate();
                    pstU.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Se Elimino Correctamete" + "\n Usuario Eliminado");
                    Limpiar();
                    mostrardatos("");

                } catch (Exception e) {

                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "No Se Elimino" + e);

                }

            } else {
                JOptionPane.showMessageDialog(null, "No Se Elimino");
            }

        } else {
            JOptionPane.showMessageDialog(null, "No seleciono fila");
        }

    }//GEN-LAST:event_EliminarContActionPerformed

    private void CedulaBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CedulaBuscarActionPerformed

    private void BuscarContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarContActionPerformed
        mostrardatos(CedulaBuscar.getText());
    }//GEN-LAST:event_BuscarContActionPerformed

    private void jPopupMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPopupMenu1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPopupMenu1MouseClicked

    private void jTableContadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableContadorMouseClicked

        int fila = jTableContador.getSelectedRow();

        if (evt.getClickCount() == 2) {

            CrearContador.setEnabled(false);
            
            if (fila >= 0) {

                CodigoPersona.setText(jTableContador.getValueAt(fila, 0).toString());
                CodigoPersona.setEnabled(false);
                CodCon.setText(jTableContador.getValueAt(fila, 1).toString());
                CodCon.setEnabled(false);
                CedulaCon.setText(jTableContador.getValueAt(fila, 2).toString());
                CedulaCon.setEnabled(false);
                NombreCon.setText(jTableContador.getValueAt(fila, 3).toString());
                ApellidoCon.setText(jTableContador.getValueAt(fila, 4).toString());
                String edad = String.valueOf(jTableContador.getValueAt(fila, 5));
                EdadSpinerCon.setValue(Integer.parseInt(edad));
                CorreoCon.setText(jTableContador.getValueAt(fila, 6).toString());
                DireccionCon.setText(jTableContador.getValueAt(fila, 7).toString());
                String genero = String.valueOf(jTableContador.getValueAt(fila, 8).toString());
                if ("Masculino".equals(genero)) {
                    Hombre.setSelected(true);
                } else if ("Femenino".equals(genero)) {
                    Mujer.setSelected(true);
                }
                TelefonoCon.setText(jTableContador.getValueAt(fila, 9).toString());
                jCmbConta.setSelectedItem(jTableContador.getValueAt(jTableContador.getSelectedRow(), 10).toString());
                SalarioCon.setText(jTableContador.getValueAt(fila, 11).toString());
                double bono = Double.valueOf(jTableContador.getValueAt(fila, 12).toString());
                BonoCon.setText(Double.toString(bono));

            } else {
                JOptionPane.showMessageDialog(null, "No seleciono fila");
            }

        } else {

            Limpiar();
            CrearContador.setEnabled(true);
        }
    }//GEN-LAST:event_jTableContadorMouseClicked

    private void jMenuItemModiContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemModiContActionPerformed
        int fila = jTableContador.getSelectedRow();

        if (fila >= 0) {

            CodigoPersona.setText(jTableContador.getValueAt(fila, 0).toString());
            CodigoPersona.setEnabled(false);
            CodCon.setText(jTableContador.getValueAt(fila, 1).toString());
            CodCon.setEnabled(false);
            CedulaCon.setText(jTableContador.getValueAt(fila, 2).toString());
            CedulaCon.setEnabled(false);
            NombreCon.setText(jTableContador.getValueAt(fila, 3).toString());
            ApellidoCon.setText(jTableContador.getValueAt(fila, 4).toString());
            String edad = String.valueOf(jTableContador.getValueAt(fila, 5));
            EdadSpinerCon.setValue(Integer.parseInt(edad));
            CorreoCon.setText(jTableContador.getValueAt(fila, 6).toString());
            DireccionCon.setText(jTableContador.getValueAt(fila, 7).toString());
            String genero = String.valueOf(jTableContador.getValueAt(fila, 8).toString());
            if ("Masculino".equals(genero)) {
                Hombre.setSelected(true);
            } else if ("Femenino".equals(genero)) {
                Mujer.setSelected(true);
            }
            TelefonoCon.setText(jTableContador.getValueAt(fila, 9).toString());
            jCmbConta.setSelectedItem(jTableContador.getValueAt(jTableContador.getSelectedRow(), 10).toString());
            SalarioCon.setText(jTableContador.getValueAt(fila, 11).toString());
            double bono = Double.valueOf(jTableContador.getValueAt(fila, 12).toString());
            BonoCon.setText(Double.toString(bono));

        } else {
            JOptionPane.showMessageDialog(null, "No seleciono fila");
        }
    }//GEN-LAST:event_jMenuItemModiContActionPerformed

    private void jMenuItemElimiContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemElimiContActionPerformed

        int fila = jTableContador.getSelectedRow();

        if (fila >= 0) {

            String codigo = "";
            String cedula = "";
            codigo = jTableContador.getValueAt(fila, 1).toString();
            cedula = jTableContador.getValueAt(fila, 2).toString();

            int confirmar = JOptionPane.showConfirmDialog(null, "¿ Esta seguro que desea Eliminar el registro?");

            if (JOptionPane.OK_OPTION == confirmar) {

                try {

                    PreparedStatement pst = cn.prepareStatement("UPDATE registrocontador SET contador_estado='B' WHERE contador_codigo='" + codigo + "'");
                    PreparedStatement pstU = cn.prepareStatement("UPDATE regusuario SET user_estado='B' WHERE empleado_codigo='" + codigo + "' AND persona_cedula = '" + cedula + "'");
                    pst.executeUpdate();
                    pstU.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Se Elimino Correctamete" + "\n Usuario Eliminado");
                    Limpiar();
                    mostrardatos("");

                } catch (HeadlessException | SQLException e) {

                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "No Se Elimino" + e);

                }

            } else {
                JOptionPane.showMessageDialog(null, "No Se Elimino");
            }

        } else {
            JOptionPane.showMessageDialog(null, "No seleciono fila");
        }

    }//GEN-LAST:event_jMenuItemElimiContActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Limpiar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void CedulaBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaBuscarKeyReleased
        mostrardatos(CedulaBuscar.getText());
    }//GEN-LAST:event_CedulaBuscarKeyReleased

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
            modelo.addColumn("Correo");
            modelo.addColumn("Direccion");
            modelo.addColumn("Genero");
            modelo.addColumn("Telofono");
            modelo.addColumn("Horario");
            modelo.addColumn("Salario");
            modelo.addColumn("Bono");
            jTableContador.setModel(modelo);

            String sql = "";

            if (cedula.equals("")) {

                sql = "SELECT E.persona_id,C.contador_codigo ,P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, E.empleado_horario, E.empleado_salario, C.contador_bono FROM persona P, empleado E, registrocontador C WHERE P.persona_id = E.persona_id AND  E.empleado_codigo = C.empleado_codigo ORDER BY C.contador_codigo";

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
                    String bono = rs.getString(11);
                    datos[10] = bono;
                    String salario = rs.getString(12);
                    datos[11] = salario;
                    datos[12] = rs.getString(13);

                    modelo.addRow(datos);

                }

                jTableContador.setModel(modelo);

            } catch (SQLException ex) {

                System.out.println("Error:" + ex);
            }

        } else {
            mostrardatos("");
        }
    }//GEN-LAST:event_dateliminadosMouseClicked

    private void CedulaConKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaConKeyTyped
        ValidarLongitud(CedulaCon.getText(), 10, evt);
        ValidarLetrNum(Character.isLetter(evt.getKeyChar()), evt);
    }//GEN-LAST:event_CedulaConKeyTyped

    private void NombreConKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreConKeyTyped
        ValidarLongitud(NombreCon.getText(), 30, evt);
        ValidarLetrNum(Character.isDigit(evt.getKeyChar()), evt);
    }//GEN-LAST:event_NombreConKeyTyped

    private void ApellidoConKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ApellidoConKeyTyped
        ValidarLongitud(ApellidoCon.getText(), 30, evt);
        ValidarLetrNum(Character.isDigit(evt.getKeyChar()), evt);
    }//GEN-LAST:event_ApellidoConKeyTyped

    private void BonoConKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BonoConKeyTyped
        ValidarLongitud(BonoCon.getText(), 5, evt);
        ValidarLetrNum(Character.isLetter(evt.getKeyChar()), evt);
    }//GEN-LAST:event_BonoConKeyTyped

    private void SalarioConKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SalarioConKeyTyped
        ValidarLongitud(SalarioCon.getText(), 8, evt);
        ValidarLetrNum(Character.isLetter(evt.getKeyChar()), evt);
    }//GEN-LAST:event_SalarioConKeyTyped

    private void CorreoConKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CorreoConKeyReleased
        if (VerificarEmail(CorreoCon.getText())) {

            AvisoCorreo1.setVisible(false);

        } else {

            AvisoCorreo1.setVisible(true);
        }
    }//GEN-LAST:event_CorreoConKeyReleased

    private void CedulaBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaBuscarKeyTyped
        // Capturar tecla enter
        char cTeclaPresionada = evt.getKeyChar();

        // Click para aceptar el enter
        if (cTeclaPresionada == KeyEvent.VK_ENTER) {

            // Da clicl al boton al precionar ENTER
            BuscarCont.doClick();
        }
    }//GEN-LAST:event_CedulaBuscarKeyTyped

    private void TelefonoConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TelefonoConActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TelefonoConActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaRegistroContador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistroContador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistroContador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistroContador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaRegistroContador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ApellidoCon;
    private javax.swing.JLabel AvisoCorreo1;
    private javax.swing.JTextField BonoCon;
    private javax.swing.JButton BuscarCont;
    private javax.swing.JTextField CedulaBuscar;
    private javax.swing.JTextField CedulaCon;
    private javax.swing.JTextField CodCon;
    private javax.swing.JTextField CodigoPersona;
    private javax.swing.JTextField CorreoCon;
    private javax.swing.JButton CrearContador;
    private javax.swing.JTextField DireccionCon;
    private javax.swing.JSpinner EdadSpinerCon;
    private javax.swing.JButton EliminarCont;
    private javax.swing.ButtonGroup GenCon;
    private javax.swing.JRadioButton Hombre;
    private javax.swing.JButton ModificarCont;
    private javax.swing.JRadioButton Mujer;
    private javax.swing.JTextField NombreCon;
    private javax.swing.JTextField SalarioCon;
    private javax.swing.JFormattedTextField TelefonoCon;
    private javax.swing.JCheckBox dateliminados;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jCmbConta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItemElimiCont;
    private javax.swing.JMenuItem jMenuItemModiCont;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableContador;
    private javax.swing.JButton moscont;
    // End of variables declaration//GEN-END:variables
    Conexion cc = new Conexion();
    Connection cn = cc.conectar();
}
