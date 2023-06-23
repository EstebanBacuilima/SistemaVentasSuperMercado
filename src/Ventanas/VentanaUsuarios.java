package Ventanas;

import java.util.ArrayList;
import djmeb.*;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Ventanas.VentanaRegistroCajero.*;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VentanaUsuarios extends javax.swing.JFrame {

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();

    //     USUARIOS
    // Cajero = CAJ001
    // Contador = CON001
    // Bodeguero = BOD001
    
    public VentanaUsuarios() {
        initComponents();
        mostrardatos("");
        setLocationRelativeTo(null);
        CedulaBusc.setEditable(false);
        CodEmpleado.setEditable(false);

    }

    // COLOR CELDA
    void mostrardatos(String id) {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        modelo.addColumn("#ID");
        modelo.addColumn("#COD");
        modelo.addColumn("Cedula");
        modelo.addColumn("Tipo");
        modelo.addColumn("Usuario");
        modelo.addColumn("Contraseña");

        jTable2.setModel(modelo);

        // Acumular la sentencia
        String sql = "";

        if (id.equals("")) {

            sql = "SELECT * FROM regusuario WHERE user_estado = 'A' AND user_tipo != 'Gerente' ORDER BY id_usuario";

        } else {

            sql = "SELECT * FROM regusuario WHERE persona_cedula='" + id + "' AND user_estado = 'A' ORDER BY id_usuario";
        }

        String[] datos = new String[6];

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

                modelo.addRow(datos);
            }

            jTable2.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }

    public void ValidarExiste(String cedula) {

        try {
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM regusuario WHERE persona_cedula = '" + cedula + "'");
            if (rs.next()) {
                //Existe
                JOptionPane.showMessageDialog(null, "Existe");
                mostrardatos(cedula);
            } else {
                //No Existe
                JOptionPane.showMessageDialog(null, "No Existe");
                mostrardatos("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentanaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Codigo autoincrementar 
    public int codIncrement() {

        int serie = 1;

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = cc.conectar();

        try {

            ps = cn.prepareStatement("SELECT MAX(id_usuario) FROM regusuario");
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

        CedulaBusc.setText("");
        CedulaBusc.setEditable(false);
        CodEmpleado.setText("");
        CodEmpleado.setEditable(false);
        Usuario.setText("");
        Usuario.setEnabled(true);
        Contraseña.setText("");
        Tipo.setSelectedIndex(0);

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItemModi = new javax.swing.JMenuItem();
        jMenuItemDelet = new javax.swing.JMenuItem();
        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        Usuario = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        CrearCajero = new javax.swing.JButton();
        ModificarCajero = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        BuscaUsuario = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        Tipo = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        CedulaBusc = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        UserBuscar = new javax.swing.JTextField();
        CajeroTabla = new javax.swing.JButton();
        BodegueroTabla = new javax.swing.JButton();
        ContadorTabla = new javax.swing.JButton();
        dateliminados = new javax.swing.JCheckBox();
        jLabel28 = new javax.swing.JLabel();
        CodEmpleado = new javax.swing.JTextField();
        Contraseña = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();

        jMenuItemModi.setText("Modificar");
        jMenuItemModi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemModiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemModi);

        jMenuItemDelet.setText("Elliminar");
        jMenuItemDelet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDeletActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemDelet);

        jDialog1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jDialog1.setName("Registro"); // NOI18N
        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jDialog1.getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 86, 804, 411));
        jDialog1.getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 520, 140, -1));

        jButton4.setText("Buscar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 520, -1, -1));

        jButton7.setText("Regresar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("Cajeros");
        jDialog1.getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 24, -1, -1));

        jButton8.setText("Mostra Datos");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(627, 560, 220, -1));

        jButton9.setText("Agregar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 510, 140, 40));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jDialog1.getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 86, 804, 411));
        jDialog1.getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 520, 140, -1));

        jButton10.setText("Buscar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 520, -1, -1));

        jButton11.setText("Regresar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel5.setText("Cajeros");
        jDialog1.getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 24, -1, -1));

        jButton12.setText("Mostra Datos");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(627, 560, 220, -1));

        jButton13.setText("Agregar");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 510, 140, 40));
        jDialog1.getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 620));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel20.setText("Codigo:");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 90, -1));

        jLabel21.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel21.setText("Contraseña:");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 150, -1));
        getContentPane().add(Usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, 230, 24));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonSalirCrud.png"))); // NOI18N
        jButton2.setToolTipText("Atras");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 670, 60, 50));

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
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 540, 50, 50));

        CrearCajero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonCrear.png"))); // NOI18N
        CrearCajero.setToolTipText("Crear");
        CrearCajero.setBorderPainted(false);
        CrearCajero.setContentAreaFilled(false);
        CrearCajero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CrearCajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearCajeroActionPerformed(evt);
            }
        });
        getContentPane().add(CrearCajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 550, 120, 130));

        ModificarCajero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonModificar.png"))); // NOI18N
        ModificarCajero.setToolTipText("Modificar");
        ModificarCajero.setBorderPainted(false);
        ModificarCajero.setContentAreaFilled(false);
        ModificarCajero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ModificarCajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarCajeroActionPerformed(evt);
            }
        });
        getContentPane().add(ModificarCajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 550, 130, 120));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonEliminar.png"))); // NOI18N
        jButton1.setToolTipText("Eliminar");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 550, 120, 120));

        BuscaUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconoBuscar.png"))); // NOI18N
        BuscaUsuario.setToolTipText("Buscar");
        BuscaUsuario.setBorderPainted(false);
        BuscaUsuario.setContentAreaFilled(false);
        BuscaUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BuscaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscaUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(BuscaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 460, 60, 50));

        jLabel22.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel22.setText("Tipo:");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 60, 40));

        Tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione Tipo", "Cajero", "Bodeguero", "Contador" }));
        Tipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TipoItemStateChanged(evt);
            }
        });
        Tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoActionPerformed(evt);
            }
        });
        getContentPane().add(Tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 180, 20));

        jLabel23.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel23.setText("C.I Empleado:");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 180, 40));

        CedulaBusc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CedulaBuscActionPerformed(evt);
            }
        });
        getContentPane().add(CedulaBusc, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, 230, 23));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IconoMostrar.png"))); // NOI18N
        jButton6.setToolTipText("Mostrar");
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 170, 60, 70));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.setComponentPopupMenu(jPopupMenu1);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 240, 620, 220));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPriPOO(1).png"))); // NOI18N
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 100));

        jLabel24.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 70)); // NOI18N
        jLabel24.setText("Registro Usuarios");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/UserMenu.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, 120, 130));

        jLabel25.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel25.setText("CREAR");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 670, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel16.setText("MODIFICAR");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 670, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel7.setText("ELIMNAR");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 670, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel26.setText("Limpiar");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 590, -1, -1));

        jLabel27.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel27.setText("MOSTRAR");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 160, -1, -1));
        getContentPane().add(UserBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 470, 180, 30));

        CajeroTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/CajeroUser.png"))); // NOI18N
        CajeroTabla.setToolTipText("Cajeros");
        CajeroTabla.setBorderPainted(false);
        CajeroTabla.setContentAreaFilled(false);
        CajeroTabla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CajeroTabla.setEnabled(false);
        CajeroTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CajeroTablaActionPerformed(evt);
            }
        });
        getContentPane().add(CajeroTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 60, 60));

        BodegueroTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BodegueroUser.png"))); // NOI18N
        BodegueroTabla.setToolTipText("Bodeguero");
        BodegueroTabla.setBorderPainted(false);
        BodegueroTabla.setContentAreaFilled(false);
        BodegueroTabla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BodegueroTabla.setEnabled(false);
        BodegueroTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BodegueroTablaActionPerformed(evt);
            }
        });
        getContentPane().add(BodegueroTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, 60, 60));

        ContadorTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ContadorUser.png"))); // NOI18N
        ContadorTabla.setToolTipText("Contador");
        ContadorTabla.setBorderPainted(false);
        ContadorTabla.setContentAreaFilled(false);
        ContadorTabla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ContadorTabla.setEnabled(false);
        ContadorTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContadorTablaActionPerformed(evt);
            }
        });
        getContentPane().add(ContadorTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, 60, 60));

        dateliminados.setBackground(new java.awt.Color(204, 204, 204));
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
        getContentPane().add(dateliminados, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 470, -1, -1));

        jLabel28.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel28.setText("Usuario:");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 120, -1));

        CodEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CodEmpleadoKeyReleased(evt);
            }
        });
        getContentPane().add(CodEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, 230, 24));
        getContentPane().add(Contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 470, 230, 24));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 730));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Menu menuInicial = new Menu();
        menuInicial.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Limpiar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void CrearCajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearCajeroActionPerformed

        if (CedulaBusc.getText().isEmpty() || Tipo.getSelectedItem().toString().isEmpty() || Usuario.getText().isEmpty() || Contraseña.getText().isEmpty() || CodEmpleado.getText().isEmpty()) {

            JOptionPane.showMessageDialog(rootPane, "Complete los Campos ");

        } else {

            try {
                Statement stm = cn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT regusuario.empleado_codigo FROM regusuario WHERE regusuario.empleado_codigo = '" + CodEmpleado.getText() + "'");
                if (rs.next()) {
                    //Existe
                    JOptionPane.showMessageDialog(this, "Usuario ya Existente");
                    Limpiar();

                } else {
                    
                    //No Existe
                    //JOptionPane.showMessageDialog(null, "No Existe");
                    PreparedStatement pst = null;

                    String sql = "INSERT INTO regusuario (id_usuario, persona_cedula, user_tipo, user_usuario, user_contrasena,empleado_codigo, user_estado) VALUES ( ?, ?, ?, ?, ?,?, 'A')";

                    int ID = codIncrement();
                    String cedula = CedulaBusc.getText();
                    String codigoEm = CodEmpleado.getText();
                    int codigo = Integer.parseInt(codigoEm);
                    String tipo = Tipo.getSelectedItem().toString();
                    String usuario = Usuario.getText();
                    String contraseña = Contraseña.getText();

                    Usuario newUser = new Usuario(ID, cedula, usuario, contraseña, tipo, codigo);

                    try {

                        pst = cn.prepareStatement(sql);
                        pst.setInt(1, newUser.getID_empleado());
                        pst.setString(2, newUser.getCed_empleado());
                        pst.setString(3, newUser.getTipo());
                        pst.setString(4, newUser.getUsuario());
                        pst.setString(5, newUser.getContraseña());
                        pst.setInt(6, newUser.getCódigo());

                        pst.executeUpdate();

                        Limpiar();
                        mostrardatos("");

                        JOptionPane.showMessageDialog(null, "Se guardo");
                        Limpiar();

                    } catch (Exception e) {

                        JOptionPane.showMessageDialog(null, "No Se guardo" + e);
                        Limpiar();
                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(VentanaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_CrearCajeroActionPerformed

    private void ModificarCajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarCajeroActionPerformed

        if (CedulaBusc.getText().isEmpty() || Tipo.getSelectedItem().toString().isEmpty() || Usuario.getText().isEmpty() || Contraseña.getText().isEmpty() || CodEmpleado.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Complete los Campos ");
        } else {
            try {

                PreparedStatement pst = cn.prepareStatement("UPDATE regusuario SET user_contrasena='" + Contraseña.getText() + "' WHERE persona_cedula= '" + CedulaBusc.getText() + "'");
                pst.executeUpdate();
                mostrardatos("");
                Limpiar();
                JOptionPane.showMessageDialog(null, "Actualizado");

            } catch (Exception e) {
                System.out.println(e.getMessage());
                Limpiar();
            }
        }
    }//GEN-LAST:event_ModificarCajeroActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int fila = jTable2.getSelectedRow();

        if (fila >= 0) {

            String codigo = "";
            codigo = jTable2.getValueAt(fila, 0).toString();

            int confirmar = JOptionPane.showConfirmDialog(null, "Esta seguro que desea Eliminar el registro? ");

            if (JOptionPane.OK_OPTION == confirmar) {
                try {

                    PreparedStatement pst = cn.prepareStatement("UPDATE regusuario SET user_estado='B' WHERE id_usuario='" + codigo + "'");
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Se Elimino Correctamete");
                    Limpiar();
                    mostrardatos("");

                } catch (Exception e) {

                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "Error");
                }

            }
               
        }else{
            JOptionPane.showMessageDialog(null, "No seleciono fila");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BuscaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscaUsuarioActionPerformed

        String cedula = UserBuscar.getText();
        ValidarExiste(cedula);


    }//GEN-LAST:event_BuscaUsuarioActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int fila = jTable2.getSelectedRow();

        if (evt.getClickCount() == 2) {

            if (fila >= 0) {

                CodEmpleado.setText(jTable2.getValueAt(fila, 1).toString());
                CodEmpleado.setEditable(false);
                CedulaBusc.setText(jTable2.getValueAt(fila, 2).toString());
                CedulaBusc.setEditable(false);
                Tipo.setSelectedItem(jTable2.getValueAt(jTable2.getSelectedRow(), 3).toString());
                Usuario.setText(jTable2.getValueAt(fila, 4).toString());
                Usuario.setEditable(false);
                Contraseña.setText(jTable2.getValueAt(fila, 5).toString());
                CajeroTabla.setEnabled(false);
                BodegueroTabla.setEnabled(false);
                ContadorTabla.setEnabled(false);

            } else {
                JOptionPane.showMessageDialog(null, "No seleciono fila");
            }

        } else {

            Limpiar();

        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jMenuItemModiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemModiActionPerformed
        int fila = jTable2.getSelectedRow();

        if (fila >= 0) {

            CodEmpleado.setText(jTable2.getValueAt(fila, 1).toString());
            CodEmpleado.setEditable(false);
            CedulaBusc.setText(jTable2.getValueAt(fila, 2).toString());
            CedulaBusc.setEditable(false);
            Tipo.setSelectedItem(jTable2.getValueAt(jTable2.getSelectedRow(), 3).toString());
            Usuario.setText(jTable2.getValueAt(fila, 4).toString());
            Usuario.setEditable(false);
            Contraseña.setText(jTable2.getValueAt(fila, 5).toString());
            CajeroTabla.setEnabled(false);
            BodegueroTabla.setEnabled(false);
            ContadorTabla.setEnabled(false);

        } else {

            Limpiar();
        }
    }//GEN-LAST:event_jMenuItemModiActionPerformed

    private void jMenuItemDeletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeletActionPerformed
        int fila = jTable2.getSelectedRow();
        String codigo = "";
        codigo = jTable2.getValueAt(fila, 0).toString();

        int confirmar = JOptionPane.showConfirmDialog(null, "Esta seguro que desea Eliminar el registro? ");

        if (JOptionPane.OK_OPTION == confirmar) {

            try {

                PreparedStatement pst = cn.prepareStatement("UPDATE regusuario SET user_estado='B' WHERE id_usuario='" + codigo + "'");
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se Elimino Correctamete");
                Limpiar();
                mostrardatos("");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No seleciono fila");
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Se Elimino");
            }
        }
    }//GEN-LAST:event_jMenuItemDeletActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        mostrardatos("");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void CedulaBuscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaBuscActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CedulaBuscActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        mostrardatos(jTextField1.getText());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        VentanaUsuarios ventUser = new VentanaUsuarios();
        ventUser.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        mostrardatos("");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        mostrardatos(jTextField1.getText());
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        VentanaUsuarios ventUser = new VentanaUsuarios();
        ventUser.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        mostrardatos("");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void TipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TipoItemStateChanged

        // COMBO BOX ANIDADO 
        if (evt.getStateChange() == ItemEvent.SELECTED) {

            if (Tipo.getSelectedIndex() == 1) {

                //JOptionPane.showMessageDialog(null, "CAJEROS");
                CedulaBusc.setEditable(false);
                CajeroTabla.setEnabled(true);
                BodegueroTabla.setEnabled(false);
                ContadorTabla.setEnabled(false);
                //Usuario.setText("CAJ0");

            } else {
                if (Tipo.getSelectedIndex() == 2) {

                    //JOptionPane.showMessageDialog(null, "BODEGUEROS");
                    CedulaBusc.setEditable(false);
                    BodegueroTabla.setEnabled(true);
                    CajeroTabla.setEnabled(false);
                    ContadorTabla.setEnabled(false);
                    //Usuario.setText("BOD0");

                } else {
                    if (Tipo.getSelectedIndex() == 3) {

                        //JOptionPane.showMessageDialog(null, "CONTADOR");
                        CedulaBusc.setEditable(false);
                        ContadorTabla.setEnabled(true);
                        CajeroTabla.setEnabled(false);
                        BodegueroTabla.setEnabled(false);
                        //Usuario.setText("CON0");

                    } else {

                    }
                    if (Tipo.getSelectedIndex() == 0) {

                        CedulaBusc.setEditable(false);
                        ContadorTabla.setEnabled(false);
                        CajeroTabla.setEnabled(false);
                        BodegueroTabla.setEnabled(false);

                    } else {

                    }

                }
            }

        }


    }//GEN-LAST:event_TipoItemStateChanged


    private void TipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoActionPerformed

    }//GEN-LAST:event_TipoActionPerformed

    private void CajeroTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CajeroTablaActionPerformed
        TablaCajeros tab = new TablaCajeros();
        tab.setVisible(true);
    }//GEN-LAST:event_CajeroTablaActionPerformed

    private void dateliminadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateliminadosMouseClicked

        if (dateliminados.isSelected()) {

            String id = "";
            DefaultTableModel modelo = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int columnas) {
                    return false;
                }
            };

            modelo.addColumn("#ID");
            modelo.addColumn("#COD");
            modelo.addColumn("Cedula");
            modelo.addColumn("Tipo");
            modelo.addColumn("Usuario");
            modelo.addColumn("Contraseña");

            jTable2.setModel(modelo);

            // Acumular la sentencia
            String sql = "";

            if (id.equals("")) {

                sql = "SELECT * FROM regusuario AND user_tipo != 'Gerente' ORDER BY id_usuario";

            }

            String[] datos = new String[6];

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

                    modelo.addRow(datos);
                }

                jTable2.setModel(modelo);

            } catch (SQLException ex) {

                System.out.println("Error:" + ex);
            }
        } else {
            mostrardatos("");
        }
    }//GEN-LAST:event_dateliminadosMouseClicked

    private void dateliminadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateliminadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateliminadosActionPerformed

    private void BodegueroTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BodegueroTablaActionPerformed
        TablaBodeguero tab = new TablaBodeguero();
        tab.setVisible(true);
    }//GEN-LAST:event_BodegueroTablaActionPerformed

    private void ContadorTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContadorTablaActionPerformed
        TablaContador tab = new TablaContador();
        tab.setVisible(true);
    }//GEN-LAST:event_ContadorTablaActionPerformed

    private void CodEmpleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CodEmpleadoKeyReleased
//       validarUser();
    }//GEN-LAST:event_CodEmpleadoKeyReleased

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BodegueroTabla;
    private javax.swing.JButton BuscaUsuario;
    private javax.swing.JButton CajeroTabla;
    public static javax.swing.JTextField CedulaBusc;
    public static javax.swing.JTextField CodEmpleado;
    private javax.swing.JButton ContadorTabla;
    private javax.swing.JTextField Contraseña;
    private javax.swing.JButton CrearCajero;
    private javax.swing.JButton ModificarCajero;
    private javax.swing.JComboBox<String> Tipo;
    private javax.swing.JTextField UserBuscar;
    private javax.swing.JTextField Usuario;
    private javax.swing.JCheckBox dateliminados;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuItem jMenuItemDelet;
    private javax.swing.JMenuItem jMenuItemModi;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

}
