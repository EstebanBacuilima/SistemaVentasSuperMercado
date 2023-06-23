package Ventanas;

import java.util.ArrayList;
import static djmeb.validaciones.ValidarLetrNum;
import static djmeb.validaciones.ValidarLongitud;
import static djmeb.validaciones.VerificarEmail;
import djmeb.Proveedores;
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
import javax.swing.table.DefaultTableModel;

public class VentanaRegistroEmpProveedor extends javax.swing.JFrame {

    public VentanaRegistroEmpProveedor() {
        initComponents();
        this.setLocationRelativeTo(null);
        mostrardatos("");
    }

    public void Limpiar() {

        Ruc.setText("");
        Ruc.setEnabled(true);
        NomEmpresa.setText("");
        Direccion.setText("");
        CedulaRes.setText("");
        CedulaRes.setEnabled(true);
        NombreRes.setText("");
        Correo.setText("");
        Telefono1.setText("");

    }

    void mostrardatos(String Ruc) {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        modelo.addColumn("Ruc");
        modelo.addColumn("NOMBRE EMPRESA");
        modelo.addColumn("DIRECCION");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("CEDULA");
        modelo.addColumn("NOMBRE ");
        modelo.addColumn("CORREO");
        jTable1.setModel(modelo);

        // Acumular la sentencia
        String sql = "";

        if (Ruc.equals("")) {

            sql = "SELECT * FROM proveedores WHERE estado_provee ='A'";

        } else {

            sql = "SELECT * FROM proveedores WHERE Ruc_provee='" + Ruc + "'" + "AND estado_provee='A'";
        }

        String[] datos = new String[7];

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
                datos[6] = rs.getString(7);

                System.out.println(rs);
                modelo.addRow(datos);
            }

            jTable1.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        eliminar = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        CedulaRes = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        NombreRes = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Correo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Direccion = new javax.swing.JTextField();
        NomEmpresa = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        CrearCajero = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        ModificarCajero = new javax.swing.JButton();
        BuscarPro = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Ruc = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        rucBuscarpro = new javax.swing.JTextField();
        dateliminados = new javax.swing.JCheckBox();
        AvisoCorreo1 = new javax.swing.JLabel();
        Telefono1 = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();

        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(eliminar);

        jMenuItem1.setText("Modficar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusCycleRoot(false);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(204, 255, 153));
        jPanel1.setComponentPopupMenu(jPopupMenu1);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel1.setText("Ruc:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        CedulaRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CedulaResActionPerformed(evt);
            }
        });
        CedulaRes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CedulaResKeyTyped(evt);
            }
        });
        jPanel1.add(CedulaRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 410, 200, 30));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel4.setText("Nombre:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, -1, -1));

        NombreRes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NombreResKeyTyped(evt);
            }
        });
        jPanel1.add(NombreRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 460, 200, 30));

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel5.setText("C.I Responsable:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel7.setText("Correo:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, -1, -1));

        Correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CorreoKeyReleased(evt);
            }
        });
        jPanel1.add(Correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 520, 230, 30));

        jLabel8.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel8.setText("Direccion:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));
        jPanel1.add(Direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 200, 30));

        NomEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NomEmpresaKeyTyped(evt);
            }
        });
        jPanel1.add(NomEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 200, 30));

        jLabel11.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel11.setText("Telefono:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 22)); // NOI18N
        jLabel13.setText("Nombre Empresa:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        CrearCajero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonCrear.png"))); // NOI18N
        CrearCajero.setToolTipText("Crear");
        CrearCajero.setBorder(null);
        CrearCajero.setBorderPainted(false);
        CrearCajero.setContentAreaFilled(false);
        CrearCajero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CrearCajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearCajeroActionPerformed(evt);
            }
        });
        jPanel1.add(CrearCajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 560, 120, 130));

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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 560, 120, -1));

        ModificarCajero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonModificar.png"))); // NOI18N
        ModificarCajero.setToolTipText("Modificar");
        ModificarCajero.setBorder(null);
        ModificarCajero.setBorderPainted(false);
        ModificarCajero.setContentAreaFilled(false);
        ModificarCajero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ModificarCajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarCajeroActionPerformed(evt);
            }
        });
        jPanel1.add(ModificarCajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 560, 130, 120));

        BuscarPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconoBuscar.png"))); // NOI18N
        BuscarPro.setToolTipText("Buscar");
        BuscarPro.setBorderPainted(false);
        BuscarPro.setContentAreaFilled(false);
        BuscarPro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BuscarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarProActionPerformed(evt);
            }
        });
        jPanel1.add(BuscarPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 490, 50, 50));

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
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 690, 40, 50));

        Ruc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RucActionPerformed(evt);
            }
        });
        Ruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                RucKeyTyped(evt);
            }
        });
        jPanel1.add(Ruc, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 220, 30));

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
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 590, 60, 60));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IconoMostrar.png"))); // NOI18N
        jButton4.setToolTipText("Mostrar");
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 150, 80, 70));

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
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, 790, 270));

        rucBuscarpro.setBorder(null);
        rucBuscarpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rucBuscarproActionPerformed(evt);
            }
        });
        rucBuscarpro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rucBuscarproKeyTyped(evt);
            }
        });
        jPanel1.add(rucBuscarpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 500, 250, 30));

        dateliminados.setBackground(new java.awt.Color(204, 255, 153));
        dateliminados.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        dateliminados.setText("Ver Eliminados");
        dateliminados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateliminadosMouseClicked(evt);
            }
        });
        jPanel1.add(dateliminados, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 500, -1, -1));

        AvisoCorreo1.setForeground(new java.awt.Color(255, 0, 0));
        AvisoCorreo1.setText("Email Incorrecto *");
        jPanel1.add(AvisoCorreo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 500, 110, 20));

        try {
            Telefono1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ### ###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel1.add(Telefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, 200, 30));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPriPOO(1).png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 190, 70));

        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 64)); // NOI18N
        jLabel6.setText("Registro Empresas Proveedores");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BodMenu.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 20, 140, 90));

        jLabel15.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel15.setText("MOSTRAR");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 140, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel18.setText("CREAR");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 680, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel16.setText("MODIFICAR");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 680, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel3.setText("ELIMNAR");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 680, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 14)); // NOI18N
        jLabel26.setText("Limpiar");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 640, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1260, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Menu menuInicial = new Menu();
        menuInicial.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ModificarCajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarCajeroActionPerformed

        if (Ruc.getText().isEmpty() || NomEmpresa.getText().isEmpty() || Direccion.getText().isEmpty() || Telefono1.getText().isEmpty() || CedulaRes.getText().isEmpty() || NombreRes.getText().isEmpty() || Correo.getText().isEmpty() || VerificarEmail(Correo.getText()) == Correo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Complete los Campos ");

        } else {
            
            Ruc.setEditable(true);
            CedulaRes.setEditable(true);
            try {
                PreparedStatement pst = cn.prepareStatement("UPDATE proveedores SET Ruc_provee='" + Ruc.getText() + "',Nom_empr_provee='" + NomEmpresa.getText() + "',Direccion_provee='" + Direccion.getText() + "',telefono_provee='" + Telefono1.getText() + "',Ced_resp_provee='" + CedulaRes.getText() + "',nombre_provee='" + NombreRes.getText() + "',correo_provee='" + Correo.getText() + "' WHERE Ruc_provee='" + Ruc.getText() + "'");
                pst.executeUpdate();
                mostrardatos("");
                Limpiar();
                JOptionPane.showMessageDialog(null, "Actualizado");

            } catch (HeadlessException | SQLException e) {
                System.out.println(e.getMessage());
                Limpiar();
            }

        }

    }//GEN-LAST:event_ModificarCajeroActionPerformed

    private void CrearCajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearCajeroActionPerformed

        if (Ruc.getText().isEmpty() || NomEmpresa.getText().isEmpty() || Direccion.getText().isEmpty() || Telefono1.getText().isEmpty() || CedulaRes.getText().isEmpty() || NombreRes.getText().isEmpty() || Correo.getText().isEmpty() || VerificarEmail(Correo.getText()) == Correo.getText().isEmpty()) {

            JOptionPane.showMessageDialog(rootPane, "Complete los Campos ");

        } else {

            if (validaciones.Cedula(CedulaRes.getText())) {

                JOptionPane.showMessageDialog(null, "Cedula Correcta");

                PreparedStatement pst = null;
                String sql = "INSERT INTO proveedores (Ruc_provee, Nom_empr_provee, Direccion_provee,telefono_provee,Ced_resp_provee,nombre_provee,correo_provee,estado_provee) VALUES (?, ?, ?, ?,?,?,?,'A')";
                String Rucs = Ruc.getText();
                String nomEmpre = NomEmpresa.getText();
                String direccion = Direccion.getText();
                String telefono = Telefono1.getText();
                String CedulaRe = CedulaRes.getText();
                String nombreRE = NombreRes.getText();
                String correo = Correo.getText();

                Proveedores pro = new Proveedores(Rucs, nomEmpre, direccion, telefono, CedulaRe, nombreRE, correo);

                try {
                    pst = cn.prepareStatement(sql);
                    pst.setString(1, pro.getRuc_provedor());
                    pst.setString(2, pro.getNom_empresa());
                    pst.setString(3, pro.getDireccion());
                    pst.setString(4, pro.getTelefono());
                    pst.setString(5, pro.getCedula_Responsable());
                    pst.setString(6, pro.getNom_Res());
                    pst.setString(7, pro.getCorreo());
                    pst.executeUpdate();
                    mostrardatos("");
                    System.out.println(pst);
                    JOptionPane.showMessageDialog(null, "Se guardo");
                    Limpiar();

                } catch (HeadlessException | SQLException e) {

                    JOptionPane.showMessageDialog(null, "RUC O CEDULA YA REGISTRADO");
                    Ruc.setText("");
                }

            } else {

                JOptionPane.showMessageDialog(null, "Cedula Incorrecta");
            }

        }


    }//GEN-LAST:event_CrearCajeroActionPerformed

    private void CedulaResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaResActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CedulaResActionPerformed

    private void RucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RucActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int fila = jTable1.getSelectedRow();

        if (fila >= 0) {

            String Ruc = "";
            Ruc = jTable1.getValueAt(fila, 0).toString();

            int confirmar = JOptionPane.showConfirmDialog(null, "¿ Esta seguro que desea Eliminar el registro?");

            if (JOptionPane.OK_OPTION == confirmar) {

                try {

                    PreparedStatement pst = cn.prepareStatement("UPDATE proveedores SET estado_provee='B' WHERE Ruc_provee='" + Ruc + "'");
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Se Elimino Correctamete");
                    Limpiar();
                    mostrardatos("");

                } catch (Exception e) {

                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "Error");
                }

            } else {
                JOptionPane.showMessageDialog(null, "No se Elimino");
            }

        } else {
            JOptionPane.showMessageDialog(null, "No seleciono fila");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BuscarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarProActionPerformed

        mostrardatos(rucBuscarpro.getText());
    }//GEN-LAST:event_BuscarProActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Limpiar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        mostrardatos("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int fila = jTable1.getSelectedRow();

        if (evt.getClickCount() == 2) {

            CrearCajero.setEnabled(false);
            if (fila >= 0) {

                Ruc.setText(jTable1.getValueAt(fila, 0).toString());
                Ruc.setEditable(false);
                NomEmpresa.setText(jTable1.getValueAt(fila, 1).toString());
                Direccion.setText(jTable1.getValueAt(fila, 2).toString());
                Telefono1.setText(jTable1.getValueAt(fila, 3).toString());
                CedulaRes.setText(jTable1.getValueAt(fila, 4).toString());
                CedulaRes.setEditable(false);
                NombreRes.setText(jTable1.getValueAt(fila, 5).toString());
                Correo.setText(jTable1.getValueAt(fila, 6).toString());
            } else {
                JOptionPane.showMessageDialog(null, "No seleciono fila");
            }

        } else {

            Limpiar();
            CedulaRes.setEditable(true);
            CrearCajero.setEnabled(true);
            Ruc.setEditable(true);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void rucBuscarproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rucBuscarproActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rucBuscarproActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed

        int fila = jTable1.getSelectedRow();
        String Ruc = "";
        Ruc = jTable1.getValueAt(fila, 0).toString();

        int confirmar = JOptionPane.showConfirmDialog(null, "¿ Esta seguro que desea Eliminar el registro?");

        if (JOptionPane.OK_OPTION == confirmar) {

            try {

                PreparedStatement pst = cn.prepareStatement("UPDATE proveedores SET estado_provee='B' WHERE Ruc_provee='" + Ruc + "'");
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se Elimino Correctamete");
                Limpiar();
                mostrardatos("");

            } catch (HeadlessException | SQLException e) {

                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "eRROR" + e);
            }

        } else {
            JOptionPane.showMessageDialog(null, "No Se Elimino");
        }


    }//GEN-LAST:event_eliminarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        int fila = jTable1.getSelectedRow();
        String Rucs = "";
        Rucs = jTable1.getValueAt(fila, 1).toString();

        if (fila >= 0) {

            Ruc.setText(jTable1.getValueAt(fila, 0).toString());
            Ruc.setEditable(false);
            NomEmpresa.setText(jTable1.getValueAt(fila, 1).toString());
            Direccion.setText(jTable1.getValueAt(fila, 2).toString());
            Telefono1.setText(jTable1.getValueAt(fila, 3).toString());
            CedulaRes.setText(jTable1.getValueAt(fila, 4).toString());
            CedulaRes.setEditable(false);
            NombreRes.setText(jTable1.getValueAt(fila, 5).toString());
            Correo.setText(jTable1.getValueAt(fila, 6).toString());

        } else {
            JOptionPane.showMessageDialog(null, "No seleciono fila");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void dateliminadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateliminadosMouseClicked

        if (dateliminados.isSelected()) {

            String RUC = "";
            DefaultTableModel modelo = new DefaultTableModel();

            modelo.addColumn("Ruc");
            modelo.addColumn("NOMBRE EMPRESA");
            modelo.addColumn("DIRECCION");
            modelo.addColumn("TELEFONO");
            modelo.addColumn("CEDULA");
            modelo.addColumn("NOMBRE ");
            modelo.addColumn("CORREO");
            jTable1.setModel(modelo);

            // Acumular la sentencia
            String sql = "";

            if (RUC.equals("")) {

                sql = "SELECT * FROM proveedores ORDER BY ruc_provee";

            }

            String[] datos = new String[7];

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
                    datos[6] = rs.getString(7);

                    System.out.println(rs);
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

    private void RucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RucKeyTyped
        ValidarLongitud(Ruc.getText(), 13, evt);
        ValidarLetrNum(Character.isLetter(evt.getKeyChar()), evt);
    }//GEN-LAST:event_RucKeyTyped

    private void CedulaResKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaResKeyTyped
        ValidarLongitud(CedulaRes.getText(), 10, evt);
        ValidarLetrNum(Character.isLetter(evt.getKeyChar()), evt);
    }//GEN-LAST:event_CedulaResKeyTyped

    private void NomEmpresaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NomEmpresaKeyTyped
        ValidarLongitud(NomEmpresa.getText(), 30, evt);
        ValidarLetrNum(Character.isDigit(evt.getKeyChar()), evt);
    }//GEN-LAST:event_NomEmpresaKeyTyped

    private void NombreResKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreResKeyTyped
        ValidarLongitud(NombreRes.getText(), 30, evt);
        ValidarLetrNum(Character.isDigit(evt.getKeyChar()), evt);
    }//GEN-LAST:event_NombreResKeyTyped

    private void CorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CorreoKeyReleased

        if (VerificarEmail(Correo.getText())) {

            AvisoCorreo1.setVisible(false);

        } else {

            AvisoCorreo1.setVisible(true);
        }
    }//GEN-LAST:event_CorreoKeyReleased

    private void rucBuscarproKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rucBuscarproKeyTyped
        // Capturar tecla enter
        char cTeclaPresionada = evt.getKeyChar();

        // Click para aceptar el enter
        if (cTeclaPresionada == KeyEvent.VK_ENTER) {

            // Da clicl al boton al precionar ENTER
            BuscarPro.doClick();
        }
    }//GEN-LAST:event_rucBuscarproKeyTyped

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
            java.util.logging.Logger.getLogger(VentanaRegistroEmpProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistroEmpProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistroEmpProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistroEmpProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaRegistroEmpProveedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AvisoCorreo1;
    private javax.swing.JButton BuscarPro;
    private javax.swing.JTextField CedulaRes;
    private javax.swing.JTextField Correo;
    private javax.swing.JButton CrearCajero;
    private javax.swing.JTextField Direccion;
    private javax.swing.JButton ModificarCajero;
    private javax.swing.JTextField NomEmpresa;
    private javax.swing.JTextField NombreRes;
    private javax.swing.JTextField Ruc;
    private javax.swing.JFormattedTextField Telefono1;
    private javax.swing.JCheckBox dateliminados;
    private javax.swing.JMenuItem eliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField rucBuscarpro;
    // End of variables declaration//GEN-END:variables

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();
}
