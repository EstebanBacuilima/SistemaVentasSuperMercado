package Ventanas;

import djmeb.*;
import static djmeb.validaciones.ValidarLetrNum;
import static djmeb.validaciones.ValidarLongitud;
import static djmeb.validaciones.VerificarEmail;
import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.table.TableRowSorter;

public class VentanaRegistroCajero extends javax.swing.JFrame {

    Date fechaactual = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("dd MMMM yyyy");

    private TableRowSorter Filto;

    public VentanaRegistroCajero() {
        
        //EdadSpiner.setValue(18);

        initComponents();
        mostrardatos("");
        this.setLocationRelativeTo(null);
        CodEmpleado.setText(String.valueOf(codIncrement()));
        CodEmpleado.setEnabled(false);
        CodigoPersona.setText(String.valueOf(codIncrementPersona()));
        CodigoPersona.setEnabled(false);
        Fechaactual.setText(formato.format(fechaactual));
//        AvisoCedula.setVisible(false);
        
        
        SpinnerNumberModel n = new SpinnerNumberModel();
        n.setMinimum(18);
        n.setMaximum(60);
        n.setValue(18);
        EdadSpiner.setModel(n);
        
        SpinnerNumberModel num = new SpinnerNumberModel();
        num.setMinimum(1);
        num.setMaximum(5);
        num.setValue(1);
        NumCaja.setModel(num);

    }

    // EMPIEZA
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
        modelo.addColumn("Telofono ");
        modelo.addColumn("Horario ");
        modelo.addColumn("Salario ");
        modelo.addColumn("Numero Caja ");
        jTable1.setModel(modelo);

        // Acumular la sentencia
        String sql = "";

        if (cedula.equals("")) {

            sql = "SELECT E.persona_id, C.cajero_codigo ,P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, E.empleado_horario, E.empleado_salario, C.cajero_num_caja FROM persona P, empleado E, registrocajeros C WHERE P.persona_id = E.persona_id AND  E.empleado_codigo = C.empleado_codigo AND C.cajero_estado = 'A' ORDER BY C.cajero_codigo";

        } else {

            sql = "SELECT E.persona_id, C.cajero_codigo ,P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, E.empleado_horario, E.empleado_salario, C.cajero_num_caja FROM persona P, empleado E, registrocajeros C WHERE P.persona_cedula='" + cedula + "'" + "AND P.persona_id = E.persona_id AND  E.empleado_codigo = C.empleado_codigo AND C.cajero_estado = 'A' ORDER BY C.cajero_codigo";

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
                datos[5] = rs.getString(6);
                String edad = rs.getString(7);
                datos[6] = edad;
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);
                String salario = rs.getString(12);
                datos[11] = salario;
                String numCaja = rs.getString(13);
                datos[12] = numCaja;

                modelo.addRow(datos);
            }
            jTable1.setModel(modelo);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No Encontrado");
            System.out.println("Error:" + ex);
        }

    }

    // AUTOINCREMENTARSE
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

    // Codigo Cajero
    public int codIncrement() {

        int serie = 1;
        //String dato = "CAJ0";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = cc.conectar();

        try {

            ps = cn.prepareStatement("SELECT MAX(cajero_codigo) FROM registrocajeros");
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

    
    public void Limpiar() {

        CodEmpleado.setText(String.valueOf(codIncrement()));
        CodEmpleado.setEnabled(false);
        Cedula.setText("");
        Cedula.setEnabled(true);
        Nombre.setText("");
        Apellido.setText("");
        Direccion.setText("");
        Correo.setText("");
        TelefonoFormat.setText("");
        Salario.setText("");
        EdadSpiner.setValue(18);
        Genero.clearSelection();
        NumCaja.setValue(1);
        Horarios.setSelectedIndex(0);
    }

    public void Filtro() {

        int ColumnaTablas = 2;

        Filto.setRowFilter(RowFilter.regexFilter(CedulaBuscar.getText(), ColumnaTablas));

    }

////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Genero = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItemAtualizar = new javax.swing.JMenuItem();
        jMenuItemEliminar = new javax.swing.JMenuItem();
        jDialog1 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        NumCaja = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        Cedula = new javax.swing.JTextField();
        Salario = new javax.swing.JTextField();
        CodEmpleado = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        Horarios = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        CedulaBuscar = new javax.swing.JTextField();
        BuscarCajero = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        Hombre = new javax.swing.JRadioButton();
        Mujer = new javax.swing.JRadioButton();
        Direccion = new javax.swing.JTextField();
        Correo = new javax.swing.JTextField();
        EdadSpiner = new javax.swing.JSpinner();
        Apellido = new javax.swing.JTextField();
        Nombre = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        ModificarCajero = new javax.swing.JButton();
        CrearCajero = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        TelefonoFormat = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        dateliminados = new javax.swing.JCheckBox();
        Fechaactual = new javax.swing.JLabel();
        CodigoPersona = new javax.swing.JTextField();
        AvisoCorreo = new javax.swing.JLabel();

        jMenuItemAtualizar.setText("Modificar");
        jMenuItemAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAtualizarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemAtualizar);

        jMenuItemEliminar.setText("Eliminar");
        jMenuItemEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemEliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 210, 820, 270));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel1.setText("Cedula:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel4.setText("Nombre:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel5.setText("Apellido:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel6.setText("Edad:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel7.setText("Correo:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel8.setText("Direccion:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel10.setText("Telefono:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel12.setText("Salario:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel13.setText("Horario:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 670, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel11.setText("N de Caja:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, -1, -1));

        NumCaja.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));
        jPanel1.add(NumCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 570, 53, -1));

        jLabel9.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel9.setText("Genero:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, -1, -1));

        Cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CedulaKeyTyped(evt);
            }
        });
        jPanel1.add(Cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 240, 25));

        Salario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalarioActionPerformed(evt);
            }
        });
        Salario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SalarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SalarioKeyTyped(evt);
            }
        });
        jPanel1.add(Salario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 620, 250, -1));

        CodEmpleado.setBackground(new java.awt.Color(255, 204, 0));
        CodEmpleado.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        CodEmpleado.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        CodEmpleado.setSelectionColor(new java.awt.Color(255, 255, 0));
        CodEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodEmpleadoActionPerformed(evt);
            }
        });
        CodEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CodEmpleadoKeyReleased(evt);
            }
        });
        jPanel1.add(CodEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 50, 30));

        jLabel19.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel19.setText("Codigo:");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        Horarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Matutino", "Vespertino", "Nocturno" }));
        Horarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HorariosActionPerformed(evt);
            }
        });
        jPanel1.add(Horarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 670, 250, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IconoLimpiar.png"))); // NOI18N
        jButton3.setToolTipText("Limpiar");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setDefaultCapable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 720, 50, 50));

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
        jPanel1.add(CedulaBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 490, 190, 30));

        BuscarCajero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconoBuscar.png"))); // NOI18N
        BuscarCajero.setToolTipText("Buscar");
        BuscarCajero.setBorderPainted(false);
        BuscarCajero.setContentAreaFilled(false);
        BuscarCajero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BuscarCajero.setDefaultCapable(false);
        BuscarCajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarCajeroActionPerformed(evt);
            }
        });
        jPanel1.add(BuscarCajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 480, 50, 50));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IconoMostrar.png"))); // NOI18N
        jButton4.setToolTipText("Mostrar");
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setDefaultCapable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 140, 60, 60));

        Hombre.setBackground(new java.awt.Color(255, 255, 153));
        Genero.add(Hombre);
        Hombre.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        Hombre.setText("Masculino");
        Hombre.setFocusPainted(false);
        Hombre.setFocusable(false);
        Hombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HombreActionPerformed(evt);
            }
        });
        jPanel1.add(Hombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 460, -1, -1));

        Mujer.setBackground(new java.awt.Color(255, 255, 153));
        Genero.add(Mujer);
        Mujer.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        Mujer.setText("Femenino");
        Mujer.setFocusPainted(false);
        Mujer.setFocusable(false);
        jPanel1.add(Mujer, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 460, -1, -1));

        Direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DireccionKeyReleased(evt);
            }
        });
        jPanel1.add(Direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 400, 241, -1));

        Correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CorreoActionPerformed(evt);
            }
        });
        Correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CorreoKeyReleased(evt);
            }
        });
        jPanel1.add(Correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 241, -1));

        EdadSpiner.setModel(new javax.swing.SpinnerNumberModel(18, 18, 100, 1));
        EdadSpiner.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EdadSpinerKeyTyped(evt);
            }
        });
        jPanel1.add(EdadSpiner, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 70, -1));

        Apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ApellidoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ApellidoKeyTyped(evt);
            }
        });
        jPanel1.add(Apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 241, -1));

        Nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NombreKeyTyped(evt);
            }
        });
        jPanel1.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 241, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonEliminar.png"))); // NOI18N
        jButton1.setToolTipText("Eliminar");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setDefaultCapable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 570, 120, 120));

        ModificarCajero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonModificar.png"))); // NOI18N
        ModificarCajero.setToolTipText("Modificar");
        ModificarCajero.setBorderPainted(false);
        ModificarCajero.setContentAreaFilled(false);
        ModificarCajero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ModificarCajero.setDefaultCapable(false);
        ModificarCajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarCajeroActionPerformed(evt);
            }
        });
        jPanel1.add(ModificarCajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 570, 120, 120));

        CrearCajero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonCrear.png"))); // NOI18N
        CrearCajero.setToolTipText("Crear");
        CrearCajero.setBorderPainted(false);
        CrearCajero.setContentAreaFilled(false);
        CrearCajero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CrearCajero.setDefaultCapable(false);
        CrearCajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearCajeroActionPerformed(evt);
            }
        });
        jPanel1.add(CrearCajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 570, 120, 120));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonSalirCrud.png"))); // NOI18N
        jButton2.setToolTipText("Regresar");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setDefaultCapable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 720, 50, 50));

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 70)); // NOI18N
        jLabel3.setText("Registro Cajero");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, 90));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPriPOO(1).png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, 70));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel2.setText("ELIMNAR");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 700, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel15.setText("MOSTRAR");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 120, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel16.setText("MODIFICAR");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 700, -1, -1));

        try {
            TelefonoFormat.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###) ###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel1.add(TelefonoFormat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 520, 240, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/CajeroBot.png"))); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 10, 110, 120));

        jLabel18.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel18.setText("CREAR");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 700, -1, -1));

        dateliminados.setBackground(new java.awt.Color(255, 255, 153));
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
        jPanel1.add(dateliminados, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 490, -1, -1));

        Fechaactual.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jPanel1.add(Fechaactual, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 740, 150, 30));
        jPanel1.add(CodigoPersona, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 200, 0, 10));

        AvisoCorreo.setForeground(new java.awt.Color(255, 0, 0));
        AvisoCorreo.setText("Email Incorrecto *");
        jPanel1.add(AvisoCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, 110, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 780));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ModificarCajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarCajeroActionPerformed

        if (Cedula.getText().isEmpty() || CodEmpleado.getText().isEmpty() || Nombre.getText().isEmpty() || Apellido.getText().isEmpty()
                || VerificarEmail(Correo.getText()) == Correo.getText().isEmpty() || Horarios.getSelectedItem().equals(0) || Direccion.getText().isEmpty() || TelefonoFormat.getText().isEmpty() || Salario.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "LLene los campos correctamente");

        } else {
            try {

                int edad = Integer.valueOf(EdadSpiner.getValue().toString());
                int numcaja = Integer.valueOf(NumCaja.getValue().toString());
                int salario = Integer.valueOf(Salario.getText());

                PreparedStatement pst2 = cn.prepareStatement("UPDATE persona SET persona_nombre='" + Nombre.getText() + "', persona_apellido='" + Apellido.getText() + "',persona_edad='" + edad + "',persona_correo='" + Correo.getText() + "',persona_direccion='" + Direccion.getText() + "',persona_genero='" + eleccionGenero() + "',persona_telefono='" + TelefonoFormat.getText() + "' WHERE persona_cedula='" + Cedula.getText() + "'");
                PreparedStatement pst3 = cn.prepareStatement("UPDATE empleado SET empleado_horario='" + Horarios.getSelectedItem().toString() + "',empleado_salario='" + salario + "' WHERE persona_id = '" + CodigoPersona.getText() + "'");
                PreparedStatement pst4 = cn.prepareStatement("UPDATE registrocajeros SET cajero_num_caja ='" + numcaja + " ' WHERE cajero_codigo ='" + CodEmpleado.getText() + "'");

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
    }//GEN-LAST:event_ModificarCajeroActionPerformed

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


    private void CrearCajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearCajeroActionPerformed

        if (Cedula.getText().isEmpty() || CodEmpleado.getText().isEmpty() || Nombre.getText().isEmpty() || Apellido.getText().isEmpty()
                || VerificarEmail(Correo.getText()) == Correo.getText().isEmpty() || Horarios.getSelectedItem().equals(0) || Direccion.getText().isEmpty() || TelefonoFormat.getText().isEmpty() || Salario.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "LLene los campos correctamente");

        } else {

            if (validaciones.Cedula(Cedula.getText())) {

                PreparedStatement pstp = null;
                PreparedStatement pste = null;
                PreparedStatement pstc = null;

                String sqlp = "INSERT INTO persona(persona_id, persona_cedula , persona_nombre, persona_apellido, persona_edad, persona_correo, persona_direccion, persona_genero, persona_telefono) VALUES (?,?,?,?,?,?,?,?,?)";
                String sqle = "INSERT INTO empleado(empleado_codigo, persona_id, empleado_horario, empleado_salario) VALUES (?,?,?,?)";
                String sqlc = "INSERT INTO registrocajeros (cajero_codigo,empleado_codigo, cajero_num_caja, cajero_estado) VALUES (?,?,?,'A')";

                int id = codIncrementPersona();
                int código = codIncrementEmpleado();
                int cod_cajero = codIncrement();
                String cedula = Cedula.getText();
                String nombre = Nombre.getText();
                String apellido = Apellido.getText();
                int edad = Integer.valueOf(EdadSpiner.getValue().toString());
                String correo = Correo.getText();
                String direccion = Direccion.getText();
                String genero = eleccionGenero();
                String telefono = TelefonoFormat.getText();
                int num_caja = Integer.valueOf(NumCaja.getValue().toString());
                int salario = Integer.valueOf(Salario.getText());
                String horario = Horarios.getSelectedItem().toString();

                Cajero newCajero = new Cajero(num_caja, cod_cajero, código, salario, horario, id, cedula, nombre, apellido, edad, correo, direccion, genero, telefono);

                try {

                    pstp = cn.prepareStatement(sqlp);
                    pste = cn.prepareStatement(sqle);
                    pstc = cn.prepareStatement(sqlc);

                    pstp.setInt(1, newCajero.getId());
                    pstp.setString(2, newCajero.getCedula());
                    pstp.setString(3, newCajero.getNombre());
                    pstp.setString(4, newCajero.getApellido());
                    pstp.setInt(5, newCajero.getEdad());
                    pstp.setString(6, newCajero.getCorreo());
                    pstp.setString(7, newCajero.getDireccion());
                    pstp.setString(8, newCajero.getGenero());
                    pstp.setString(9, newCajero.getTelefono());

                    pste.setInt(1, newCajero.getCódigo());
                    pste.setInt(2, newCajero.getId());
                    pste.setString(3, newCajero.getHorario());
                    pste.setInt(4, newCajero.getSalario());

                    pstc.setInt(1, newCajero.getCod_cajero());
                    pstc.setInt(2, newCajero.getCódigo());
                    pstc.setInt(3, newCajero.getNum_caja());

                    pstp.executeUpdate();
                    pste.executeUpdate();
                    pstc.executeUpdate();
                    Limpiar();
                    mostrardatos("");

                    JOptionPane.showMessageDialog(null, "Cedula Correcta");
                    JOptionPane.showMessageDialog(null, "Se guardo");
                    Limpiar();

                } catch (HeadlessException | SQLException e) {

                    JOptionPane.showMessageDialog(null, "No Se guardo");
                    JOptionPane.showMessageDialog(null, "Cedula Existente");
                    System.out.println("Error: " + e);
                    Cedula.setText("");
                    //Cedula.setEnabled(true);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Cedula Incorrecta");
                //AvisoCedula.setVisible(true);
            }

        }


    }//GEN-LAST:event_CrearCajeroActionPerformed

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
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea Eliminar el registro?"+ "\n Este registro esta relacionado con Usuarios");

            if (JOptionPane.OK_OPTION == confirmar) {

                try {

                    PreparedStatement pst = cn.prepareStatement("UPDATE registrocajeros SET cajero_estado='B' WHERE cajero_codigo='" + codigo + "'");
                    PreparedStatement pstU = cn.prepareStatement("UPDATE regusuario SET user_estado='B' WHERE empleado_codigo='" + codigo + "' AND persona_cedula = '" + cedula + "'");
                    pst.executeUpdate();
                    pstU.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Se Elimino Correctamente" + "\n Usuario Eliminado");
                    Limpiar();
                    mostrardatos("");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro : " + e);
                    System.out.println("6" + e.getMessage());
                }

            } else {
                JOptionPane.showMessageDialog(null, "No se elimino");
            }

        } else {
            JOptionPane.showMessageDialog(null, "No seleciono Fila");
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void BuscarCajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarCajeroActionPerformed

        mostrardatos(CedulaBuscar.getText());

        int fila = 0;

        for (int i = 0; fila < jTable1.getRowCount(); fila++) {

            jTable1.changeSelection(i, 0, false, false);

            CodigoPersona.setText(String.valueOf(jTable1.getValueAt(fila, 0)));
            CodigoPersona.setEnabled(false);
            CodEmpleado.setText(String.valueOf(jTable1.getValueAt(fila, 1)));
            CodEmpleado.setEnabled(false);
            Cedula.setText(String.valueOf(jTable1.getValueAt(fila, 2)));
            Cedula.setEnabled(false);
            Nombre.setText(jTable1.getValueAt(fila, 3).toString());
            Apellido.setText(jTable1.getValueAt(fila, 4).toString());
            String edad = String.valueOf(jTable1.getValueAt(fila, 5));
            System.out.println("Edad:" + edad);
            EdadSpiner.setValue(Integer.parseInt(edad));
            Correo.setText(jTable1.getValueAt(fila, 6).toString());
            Direccion.setText(jTable1.getValueAt(fila, 7).toString());
            String genero = String.valueOf(jTable1.getValueAt(fila, 8).toString());
            if ("Masculino".equals(genero)) {
                Hombre.setSelected(true);
            } else if ("Femenino".equals(genero)) {
                Mujer.setSelected(true);
            }
            TelefonoFormat.setText(jTable1.getValueAt(fila, 9).toString());
            Horarios.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(), 10).toString());
            Salario.setText(jTable1.getValueAt(fila, 11).toString());
            String numCaja = String.valueOf(jTable1.getValueAt(fila, 12).toString());
            NumCaja.setValue(Integer.parseInt(numCaja));
        }

    }//GEN-LAST:event_BuscarCajeroActionPerformed

    private void CedulaBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CedulaBuscarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Limpiar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void SalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SalarioActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        mostrardatos("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenuItemAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAtualizarActionPerformed

        int fila = jTable1.getSelectedRow();

        if (fila >= 0) {

//            AvisoCedula.setVisible(false);
            CodigoPersona.setText(String.valueOf(jTable1.getValueAt(fila, 0)));
            CodigoPersona.setEnabled(false);
            CodEmpleado.setText(String.valueOf(jTable1.getValueAt(fila, 1)));
            CodEmpleado.setEnabled(false);
            Cedula.setText(String.valueOf(jTable1.getValueAt(fila, 2)));
            Cedula.setEnabled(false);
            Nombre.setText(jTable1.getValueAt(fila, 3).toString());
            Apellido.setText(jTable1.getValueAt(fila, 4).toString());
            String edad = String.valueOf(jTable1.getValueAt(fila, 5));
            System.out.println("Edad:" + edad);
            EdadSpiner.setValue(Integer.parseInt(edad));
            Correo.setText(jTable1.getValueAt(fila, 6).toString());
            Direccion.setText(jTable1.getValueAt(fila, 7).toString());
            String genero = String.valueOf(jTable1.getValueAt(fila, 8).toString());
            if ("Masculino".equals(genero)) {
                Hombre.setSelected(true);
            } else if ("Femenino".equals(genero)) {
                Mujer.setSelected(true);
            }
            TelefonoFormat.setText(jTable1.getValueAt(fila, 9).toString());
            Horarios.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(), 10).toString());
            Salario.setText(jTable1.getValueAt(fila, 11).toString());
            String numCaja = String.valueOf(jTable1.getValueAt(fila, 12).toString());
            NumCaja.setValue(Integer.parseInt(numCaja));;
        } else {
            JOptionPane.showMessageDialog(null, "No seleciono fila");
        }

    }//GEN-LAST:event_jMenuItemAtualizarActionPerformed


    private void jMenuItemEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEliminarActionPerformed

      int fila = jTable1.getSelectedRow();

        if (fila >= 0) {

            String codigo = "";
            String cedula = "";
            codigo = jTable1.getValueAt(fila, 1).toString();
            cedula = jTable1.getValueAt(fila, 2).toString();
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea Eliminar el registro?");

            if (JOptionPane.OK_OPTION == confirmar) {

                try {

                    PreparedStatement pst = cn.prepareStatement("UPDATE registrocajeros SET cajero_estado='B' WHERE cajero_codigo='" + codigo + "'");
                    PreparedStatement pstU = cn.prepareStatement("UPDATE regusuario SET user_estado='B' WHERE empleado_codigo='" + codigo + "' AND persona_cedula = '" + cedula + "'");
                    pst.executeUpdate();
                    pstU.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Se Elimino Correctamente" + "\n Usuario Eliminado");
                    Limpiar();
                    mostrardatos("");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro : " + e);
                    System.out.println("6" + e.getMessage());
                }

            } else {
                JOptionPane.showMessageDialog(null, "No se elimino");
            }

        } else {
            JOptionPane.showMessageDialog(null, "No seleciono Fila");
        }

    }//GEN-LAST:event_jMenuItemEliminarActionPerformed

    private void CodEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodEmpleadoActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int fila = jTable1.getSelectedRow();

        if (evt.getClickCount() == 2) {

            CrearCajero.setEnabled(false);
            if (fila >= 0) {

//                AvisoCedula.setVisible(false);
                CodigoPersona.setText(String.valueOf(jTable1.getValueAt(fila, 0)));
                CodigoPersona.setEnabled(false);
                CodEmpleado.setText(String.valueOf(jTable1.getValueAt(fila, 1)));
                CodEmpleado.setEnabled(false);
                Cedula.setText(String.valueOf(jTable1.getValueAt(fila, 2)));
                Cedula.setEnabled(false);
                Nombre.setText(jTable1.getValueAt(fila, 3).toString());
                Apellido.setText(jTable1.getValueAt(fila, 4).toString());
                String edad = String.valueOf(jTable1.getValueAt(fila, 5));
                System.out.println("Edad:" + edad);
                EdadSpiner.setValue(Integer.parseInt(edad));
                Correo.setText(jTable1.getValueAt(fila, 6).toString());
                Direccion.setText(jTable1.getValueAt(fila, 7).toString());
                String genero = String.valueOf(jTable1.getValueAt(fila, 8).toString());
                if ("Masculino".equals(genero)) {
                    Hombre.setSelected(true);
                } else if ("Femenino".equals(genero)) {
                    Mujer.setSelected(true);
                }
                TelefonoFormat.setText(jTable1.getValueAt(fila, 9).toString());
                Horarios.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(), 10).toString());
                Salario.setText(jTable1.getValueAt(fila, 11).toString());
                String numCaja = String.valueOf(jTable1.getValueAt(fila, 12).toString());
                NumCaja.setValue(Integer.parseInt(numCaja));

            } else {
                JOptionPane.showMessageDialog(null, "No seleciono fila");
            }

        } else {

            Limpiar();
            CrearCajero.setEnabled(true);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void HorariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HorariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HorariosActionPerformed

    TableRowSorter trs;

    private void CedulaBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaBuscarKeyTyped

//        
//        CedulaBuscar.addKeyListener(new KeyAdapter() {
//
//            
//            public void keyReleased(final KeyEvent e) {
//                
//                String cadena = (CedulaBuscar.getText());
//                CedulaBuscar.setText(cadena);
//                Filtro();
//            }
//  
//        });
//         
//        Filto = new TableRowSorter(jTable1.getModel());
//        jTable1.setRowSorter(Filto);
        // Capturar tecla enter
        char cTeclaPresionada = evt.getKeyChar();

        // Click para aceptar el enter
        if (cTeclaPresionada == KeyEvent.VK_ENTER) {

            // Da clicl al boton al precionar ENTER
            BuscarCajero.doClick();
        }


    }//GEN-LAST:event_CedulaBuscarKeyTyped

    private void CorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CorreoKeyReleased

        if (VerificarEmail(Correo.getText())) {

            AvisoCorreo.setVisible(false);

        } else {

            AvisoCorreo.setVisible(true);
        }

    }//GEN-LAST:event_CorreoKeyReleased

    private void HombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HombreActionPerformed

    private void CedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaKeyReleased

    }//GEN-LAST:event_CedulaKeyReleased

    private void CorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CorreoActionPerformed

    private void CedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaKeyTyped
        
        validaciones validar = new validaciones();
        
        ValidarLongitud(Cedula.getText(), 10, evt);
        ValidarLetrNum(Character.isLetter(evt.getKeyChar()), evt);
        //validar.ValidarLetrNum1(evt);
    }//GEN-LAST:event_CedulaKeyTyped

    private void NombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreKeyTyped
        ValidarLongitud(Nombre.getText(), 30, evt);
        ValidarLetrNum(Character.isDigit(evt.getKeyChar()), evt);
    }//GEN-LAST:event_NombreKeyTyped

    private void ApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ApellidoKeyTyped
        ValidarLongitud(Apellido.getText(), 30, evt);
        ValidarLetrNum(Character.isDigit(evt.getKeyChar()), evt);
    }//GEN-LAST:event_ApellidoKeyTyped

    private void SalarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SalarioKeyTyped
        ValidarLongitud(Salario.getText(), 8, evt);
        ValidarLetrNum(Character.isLetter(evt.getKeyChar()), evt);
    }//GEN-LAST:event_SalarioKeyTyped

    private void CodEmpleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CodEmpleadoKeyReleased

    }//GEN-LAST:event_CodEmpleadoKeyReleased

    private void NombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreKeyReleased

    }//GEN-LAST:event_NombreKeyReleased

    private void ApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ApellidoKeyReleased

    }//GEN-LAST:event_ApellidoKeyReleased

    private void DireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DireccionKeyReleased

    }//GEN-LAST:event_DireccionKeyReleased

    private void SalarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SalarioKeyReleased

    }//GEN-LAST:event_SalarioKeyReleased

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased

    }//GEN-LAST:event_jTable1KeyReleased

    private void CedulaBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaBuscarKeyReleased

        if (CedulaBuscar.getText().isEmpty()) {
            mostrardatos("");
            Limpiar();
        } else {
            mostrardatos(CedulaBuscar.getText());
        }

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
            modelo.addColumn("Correo ");
            modelo.addColumn("Direccion");
            modelo.addColumn("Genero ");
            modelo.addColumn("Telofono ");
            modelo.addColumn("Horario ");
            modelo.addColumn("Salario ");
            modelo.addColumn("Numero Caja ");
            jTable1.setModel(modelo);

            // Acumular la sentencia
            String sql = "";

            if (cedula.equals("")) {

                sql = "SELECT E.persona_id, C.cajero_codigo ,P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, E.empleado_horario, E.empleado_salario, C.cajero_num_caja FROM persona P, empleado E, registrocajeros C WHERE P.persona_id = E.persona_id AND  E.empleado_codigo = C.empleado_codigo ORDER BY C.cajero_codigo";

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
                    datos[5] = rs.getString(6);
                    String edad = rs.getString(7);
                    datos[6] = edad;
                    datos[7] = rs.getString(8);
                    datos[8] = rs.getString(9);
                    datos[9] = rs.getString(10);
                    datos[10] = rs.getString(11);
                    String salario = rs.getString(12);
                    datos[11] = salario;
                    String numCaja = rs.getString(13);
                    datos[12] = numCaja;

                    modelo.addRow(datos);
                }
                jTable1.setModel(modelo);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "No Encontrado");
                System.out.println("Error:" + ex);
            }

        } else {
            mostrardatos("");
        }

    }//GEN-LAST:event_dateliminadosMouseClicked

    private void dateliminadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateliminadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateliminadosActionPerformed

    ///// VALIDACIONESS

    private void EdadSpinerKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdadSpinerKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_EdadSpinerKeyTyped

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaRegistroCajero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Apellido;
    private javax.swing.JLabel AvisoCorreo;
    private javax.swing.JButton BuscarCajero;
    private javax.swing.JTextField Cedula;
    private javax.swing.JTextField CedulaBuscar;
    private javax.swing.JTextField CodEmpleado;
    private javax.swing.JTextField CodigoPersona;
    private javax.swing.JTextField Correo;
    private javax.swing.JButton CrearCajero;
    private javax.swing.JTextField Direccion;
    private javax.swing.JSpinner EdadSpiner;
    private javax.swing.JLabel Fechaactual;
    public static javax.swing.ButtonGroup Genero;
    private javax.swing.JRadioButton Hombre;
    private javax.swing.JComboBox<String> Horarios;
    private javax.swing.JButton ModificarCajero;
    private javax.swing.JRadioButton Mujer;
    private javax.swing.JTextField Nombre;
    private javax.swing.JSpinner NumCaja;
    private javax.swing.JTextField Salario;
    private javax.swing.JFormattedTextField TelefonoFormat;
    private javax.swing.JCheckBox dateliminados;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JDialog jDialog1;
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
    private javax.swing.JMenuItem jMenuItemAtualizar;
    private javax.swing.JMenuItem jMenuItemEliminar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();
}
