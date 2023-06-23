package Ventanas;

import Reportes.ReporteClientes;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.DriverManager;

public class ReportCliente extends javax.swing.JFrame {

    public ReportCliente() {
        initComponents();
        mostrarcli("");
        setLocationRelativeTo(null);
        cbogenero.setEnabled(false);
        txtclien.setEnabled(false);

        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
    }

    public void mostrarcli(String cedula) {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };
        modelo.addColumn("Codigo");
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Edad");
        modelo.addColumn("Correo");
        modelo.addColumn("Direccion");
        modelo.addColumn("Genero");
        modelo.addColumn("Telefono");
        modelo.addColumn("Fecha Nacimiento");
        TablaClientes.setModel(modelo);

        // Acumular la sentencia
        String sql = "";

        //if (cedula.equals("")) {
        //P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono
        //FROM persona P, empleado E, registrocajeros C WHERE P.persona_id = E.persona_id AND  E.empleado_codigo = C.empleado_codigo AND C.cajero_estado = 'A'";
        sql = "SELECT C.id_clientes,P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, C.fecha_nac_cli FROM persona P, clientes C WHERE P.persona_id = C.persona_id";

        //} else {
        //sql = "SELECT SELECT C.id_clientes,P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, C.fecha_nac_cli FROM persona P, clientes C WHERE cli_cedula='" + cedula + "'" +" AND WHERE P.persona_id = C.persona_id";
        //}
        String[] datos = new String[10];

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
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);

                System.out.println(rs);
                modelo.addRow(datos);
            }

            TablaClientes.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }
    }

    public void buscar() {
        
        String genero = cbogenero.getSelectedItem().toString();
        String cedula = txtclien.getText();

        if (BusGenero.isSelected()) {
            
            DefaultTableModel modelo = new DefaultTableModel();
            String[] Titulos = {"Codigo", "Cedula", "Nombre", "Apellido", "Edad", "Correo", "Direccion", "Genero", "Telefono", "Fecha Nacimiento"};
            modelo.setColumnIdentifiers(Titulos);
            this.TablaClientes.setModel(modelo);
            
            try {

                String ConsultaSQL = "SELECT C.id_clientes,P.persona_cedula, P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, C.fecha_nac_cli FROM persona P, clientes C  WHERE P.persona_id = C.persona_id AND persona_genero='" + genero + "'";

                String[] registros = new String[10];

                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(ConsultaSQL);
                
                while (rs.next()) {
                    registros[0] = rs.getString("id_clientes");
                    registros[1] = rs.getString("persona_cedula");
                    registros[2] = rs.getString("persona_nombre");
                    registros[3] = rs.getString("persona_apellido");
                    registros[4] = rs.getString("persona_edad");
                    registros[5] = rs.getString("persona_correo");
                    registros[6] = rs.getString("persona_direccion");
                    registros[7] = rs.getString("persona_genero");
                    registros[8] = rs.getString("persona_telefono");
                    registros[9] = rs.getString("fecha_nac_cli");
                    modelo.addRow(registros);
                }
                TablaClientes.setModel(modelo);
            } catch (SQLException ex) {
                Logger.getLogger(ReportCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (jcliente.isSelected()) {

            DefaultTableModel modelo = new DefaultTableModel();
            String[] Titulos = {"Codigo", "Cedula", "Nombre", "Apellido", "Edad", "Correo", "Direccion", "Genero", "Telefono", "Fecha Nacimiento"};
            modelo.setColumnIdentifiers(Titulos);
            this.TablaClientes.setModel(modelo);
            try {

                String ConsultaSQL = "SELECT C.id_clientes,P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, C.fecha_nac_cli FROM persona P, clientes C  WHERE P.persona_id = C.persona_id AND persona_cedula='" + cedula + "'";

                String[] registros = new String[10];

                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(ConsultaSQL);
                while (rs.next()) {
                    registros[0] = rs.getString("id_clientes");
                    registros[1] = rs.getString("persona_cedula");
                    registros[2] = rs.getString("persona_nombre");
                    registros[3] = rs.getString("persona_apellido");
                    registros[4] = rs.getString("persona_edad");
                    registros[5] = rs.getString("persona_correo");
                    registros[6] = rs.getString("persona_direccion");
                    registros[7] = rs.getString("persona_genero");
                    registros[8] = rs.getString("persona_telefono");
                    registros[9] = rs.getString("fecha_nac_cli");
                    modelo.addRow(registros);

                }
                TablaClientes.setModel(modelo);
            } catch (SQLException ex) {
                Logger.getLogger(ReportCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (txtclien.equals("")) {
            
            DefaultTableModel modelo = new DefaultTableModel();
            String[] Titulos = {"Codigo", "Cedula", "Nombre", "Apellido", "Edad", "Correo", "Direccion", "Genero", "Telefono", "Fecha Nacimiento"};
            modelo.setColumnIdentifiers(Titulos);
            this.TablaClientes.setModel(modelo);
            
            try {

                String ConsultaSQL = "SELECT C.id_clientes,P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, C.fecha_nac_cli FROM persona P, clientes C  WHERE P.persona_id = C.persona_id AND persona_cedula='" + txtclien.getText() + "'";

                String[] registros = new String[10];

                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(ConsultaSQL);
                while (rs.next()) {
                    registros[0] = rs.getString("id_clientes");
                    registros[1] = rs.getString("persona_cedula");
                    registros[2] = rs.getString("persona_nombre");
                    registros[3] = rs.getString("persona_apellido");
                    registros[4] = rs.getString("persona_edad");
                    registros[5] = rs.getString("persona_correo");
                    registros[6] = rs.getString("persona_direccion");
                    registros[7] = rs.getString("persona_genero");
                    registros[8] = rs.getString("persona_telefono");
                    registros[9] = rs.getString("fecha_nac_cli");
                    modelo.addRow(registros);

                }
                TablaClientes.setModel(modelo);
            } catch (SQLException ex) {
                Logger.getLogger(ReportCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        txtclien.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupgenero = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jcliente = new javax.swing.JRadioButton();
        BusGenero = new javax.swing.JRadioButton();
        mostodo = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaClientes = new javax.swing.JTable();
        txtclien = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        cbogenero = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroupgenero.add(jcliente);
        jcliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcliente.setText("Cedula");
        jcliente.setOpaque(false);
        jcliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jclienteMouseClicked(evt);
            }
        });
        jcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jclienteActionPerformed(evt);
            }
        });
        jPanel1.add(jcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        buttonGroupgenero.add(BusGenero);
        BusGenero.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BusGenero.setText("Genero");
        BusGenero.setOpaque(false);
        BusGenero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BusGeneroMouseClicked(evt);
            }
        });
        jPanel1.add(BusGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        buttonGroupgenero.add(mostodo);
        mostodo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mostodo.setText("Mostrar todos");
        mostodo.setOpaque(false);
        mostodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostodoMouseClicked(evt);
            }
        });
        mostodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostodoActionPerformed(evt);
            }
        });
        jPanel1.add(mostodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        TablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(TablaClientes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 882, 240));

        txtclien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(txtclien, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 190, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LupaCaj.png"))); // NOI18N
        jButton1.setToolTipText("Buscar");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 50, 40));

        cbogenero.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbogenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Femenino", "Masculino" }));
        cbogenero.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbogeneroItemStateChanged(evt);
            }
        });
        jPanel1.add(cbogenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 190, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GenerReporteCompleto.png"))); // NOI18N
        jButton2.setToolTipText("Reporte Completo");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 450, 70, 70));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GeneReportGenero.png"))); // NOI18N
        jButton3.setToolTipText("Reporte por Genero");
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 460, 70, 70));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonSalirCrud.png"))); // NOI18N
        jButton4.setToolTipText("Regresar");
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 40, -1));

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 64)); // NOI18N
        jLabel1.setText("REPORTE CLIENTES");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 410, 80));

        jLabel2.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel2.setText("Reporte Completo");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 530, -1, -1));

        jLabel3.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel3.setText("Reporte por Genero");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 530, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jclienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jclienteActionPerformed

    private void mostodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mostodoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jclienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jclienteMouseClicked
        if (jcliente.isSelected()) {
            txtclien.setEnabled(true);
            cbogenero.setEnabled(false);
            jButton3.setEnabled(false);
            jButton2.setEnabled(false);
        }
    }//GEN-LAST:event_jclienteMouseClicked

    private void mostodoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mostodoMouseClicked
        if (mostodo.isSelected()) {
            mostrarcli("");
            txtclien.setEnabled(false);
            cbogenero.setEnabled(false);
            jButton3.setEnabled(false);
            jButton2.setEnabled(true);
        }
    }//GEN-LAST:event_mostodoMouseClicked

    private void BusGeneroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BusGeneroMouseClicked
        if (BusGenero.isSelected()) {
            txtclien.setEnabled(false);
            cbogenero.setEnabled(true);
            jButton2.setEnabled(false);
            jButton3.setEnabled(true);
        }
    }//GEN-LAST:event_BusGeneroMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String genero = cbogenero.getSelectedItem().toString();
        ReporteClientes.reportegenero(genero);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ReporteClientes.reporte();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbogeneroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbogeneroItemStateChanged

        if (cbogenero.getSelectedItem().toString() == "Masculino") {
            DefaultTableModel modelo = new DefaultTableModel();
            String[] Titulos = {"Codigo", "Cedula", "Nombre", "Apellido", "Edad", "Correo", "Direccion", "Genero", "Telefono", "Fecha Nacimiento"};
            modelo.setColumnIdentifiers(Titulos);
            this.TablaClientes.setModel(modelo);
            try {

                String ConsultaSQL = "SELECT C.id_clientes,P.persona_cedula, P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, C.fecha_nac_cli FROM persona P, clientes C  WHERE P.persona_id = C.persona_id AND persona_genero='Masculino'";

                String[] registros = new String[10];

                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(ConsultaSQL);
                while (rs.next()) {
                    registros[0] = rs.getString("id_clientes");
                    registros[1] = rs.getString("persona_cedula");
                    registros[2] = rs.getString("persona_nombre");
                    registros[3] = rs.getString("persona_apellido");
                    registros[4] = rs.getString("persona_edad");
                    registros[5] = rs.getString("persona_correo");
                    registros[6] = rs.getString("persona_direccion");
                    registros[7] = rs.getString("persona_genero");
                    registros[8] = rs.getString("persona_telefono");
                    registros[9] = rs.getString("fecha_nac_cli");
                    modelo.addRow(registros);
                }
                TablaClientes.setModel(modelo);
            } catch (SQLException ex) {
                Logger.getLogger(ReportCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            if (cbogenero.getSelectedItem().toString() == "Femenino") {
                DefaultTableModel modelo = new DefaultTableModel();
                String[] Titulos = {"Codigo", "Cedula", "Nombre", "Apellido", "Edad", "Correo", "Direccion", "Genero", "Telefono", "Fecha Nacimiento"};
                modelo.setColumnIdentifiers(Titulos);
                this.TablaClientes.setModel(modelo);
                try {

                    String ConsultaSQL = "SELECT C.id_clientes,P.persona_cedula, P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, C.fecha_nac_cli FROM persona P, clientes C  WHERE P.persona_id = C.persona_id AND persona_genero='Femenino'";

                    String[] registros = new String[10];

                    Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery(ConsultaSQL);
                    while (rs.next()) {
                        registros[0] = rs.getString("id_clientes");
                        registros[1] = rs.getString("persona_cedula");
                        registros[2] = rs.getString("persona_nombre");
                        registros[3] = rs.getString("persona_apellido");
                        registros[4] = rs.getString("persona_edad");
                        registros[5] = rs.getString("persona_correo");
                        registros[6] = rs.getString("persona_direccion");
                        registros[7] = rs.getString("persona_genero");
                        registros[8] = rs.getString("persona_telefono");
                        registros[9] = rs.getString("fecha_nac_cli");
                        modelo.addRow(registros);
                    }
                    TablaClientes.setModel(modelo);
                } catch (SQLException ex) {
                    Logger.getLogger(ReportCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (cbogenero.getSelectedItem().toString() == "Seleccione") {
                    mostrarcli("");
                } else {
                    
                }
            }

        }
     


    }//GEN-LAST:event_cbogeneroItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        VentanaContador rpe = new VentanaContador();
        rpe.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(ReportCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton BusGenero;
    private javax.swing.JTable TablaClientes;
    private javax.swing.ButtonGroup buttonGroupgenero;
    private javax.swing.JComboBox<String> cbogenero;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton jcliente;
    private javax.swing.JRadioButton mostodo;
    private javax.swing.JTextField txtclien;
    // End of variables declaration//GEN-END:variables

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();
}
