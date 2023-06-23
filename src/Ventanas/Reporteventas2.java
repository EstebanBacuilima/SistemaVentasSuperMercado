package Ventanas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Reporteventas2 extends javax.swing.JFrame {

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();

    public Reporteventas2() {
        initComponents();
        mostrarEnca();
        setLocationRelativeTo(null);
        Seleccionar();
        
        Fecha1.setEnabled(false);
        FechaH.setEnabled(false);
        CodVenta.setEnabled(false);
        jComboBox1.setEnabled(false);
        
        
    }

    void mostrarEnca() {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        modelo.addColumn("Código Venta");
        modelo.addColumn("ID Clientas");
        modelo.addColumn("Código Cajero");
        modelo.addColumn("Fecha de Venta");
        modelo.addColumn("Total");

        jTable1.setModel(modelo);
        // Acumular la sentencia

        String sql = "SELECT * FROM enca_venta";

        String[] datos = new String[5];

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);

                //System.out.println(rs);
                modelo.addRow(datos);
            }
            jTable1.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }
    }
    
    

    void mostrarEnca2() {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        modelo.addColumn("Código Venta");
        modelo.addColumn("ID Clientas");
        modelo.addColumn("Código Cajero");
        modelo.addColumn("Fecha de Venta");
        modelo.addColumn("Total");

        jTable1.setModel(modelo);
        // Acumular la sentencia
        String sql = "SELECT * FROM enca_venta WHERE fecha BETWEEN '" + Fecha1.getDate() + "' AND '" + FechaH.getDate() + "'";

        String[] datos = new String[5];

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                modelo.addRow(datos);
            }
            jTable1.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }
    }

    void Seleccionar() {
        Botones.add(jRadioButton1);
        Botones.add(jRadioButton4);

    }

    
    String codigoF;

    void codCajero() {

        String sql = "SELECT * FROM enca_venta";


        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                codigoF = rs.getString(3);

            }

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Botones = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        CodVenta = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();
        Fecha1 = new com.toedter.calendar.JDateChooser();
        jSeparator10 = new javax.swing.JSeparator();
        FechaH = new com.toedter.calendar.JDateChooser();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton8.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 16)); // NOI18N
        jButton8.setText("Buscar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, -1, 20));

        jLabel15.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 48)); // NOI18N
        jLabel15.setText("REPORTE DE VENTAS");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 450, 60));

        jLabel16.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel16.setText("BUSCAR POR :");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 150, -1));

        jLabel23.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel23.setText("hasta");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 50, -1));

        CodVenta.setEnabled(false);
        CodVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CodVentaKeyPressed(evt);
            }
        });
        jPanel1.add(CodVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 210, 40, -1));

        jRadioButton1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jRadioButton1.setText("Fecha de compra:");
        jRadioButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton1.setOpaque(false);
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseClicked(evt);
            }
        });
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 20));

        jRadioButton4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jRadioButton4.setText("Ver Venta MAX/MIN");
        jRadioButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton4.setOpaque(false);
        jRadioButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton4MouseClicked(evt);
            }
        });
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 140, -1, 20));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Pedido", "Código Contador", "RUC Proveedor", "Fecha Emisión", "Fecha Entrega", "Situación"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 920, 370));

        jButton7.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 16)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonSalirCrud.png"))); // NOI18N
        jButton7.setToolTipText("Regresar");
        jButton7.setBorder(null);
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, -1, -1));
        jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 1060, 20));

        Fecha1.setDateFormatString("yyyy-MM-dd");
        Fecha1.setOpaque(false);
        jPanel1.add(Fecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 130, -1));
        jPanel1.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1270, 20));

        FechaH.setDateFormatString("yyyy-MM-dd");
        FechaH.setOpaque(false);
        jPanel1.add(FechaH, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, 140, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Venta", "Maximo", "Minimo" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 140, 130, -1));

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel1.setText("Código Venta :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 210, -1, -1));

        jButton1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/VerReportesEm.png"))); // NOI18N
        jButton1.setToolTipText("Reportes");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 510, -1, 70));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPriPOO(1).png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 10, 190, 60));

        jLabel2.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel2.setText("Reportes");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 580, -1, -1));

        jButton2.setText("Mostrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 210, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1088, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int fila = jTable1.getSelectedRow();

        if (evt.getClickCount() == 1) {

            if (fila >= 0) {

                CodVenta.setText(String.valueOf(jTable1.getValueAt(fila, 0)));

            } else {
                JOptionPane.showMessageDialog(null, "No seleciono fila");
            }

        } else {

            if (evt.getClickCount() == 2) {
                TablaVentasDet tb = new TablaVentasDet();
                tb.setVisible(true);
                TablaVentasDet.CodVentas.setText(String.valueOf(jTable1.getValueAt(fila, 0)));
            } else {
                CodVenta.setText("");
            }
            CodVenta.setText("");
        }

    }//GEN-LAST:event_jTable1MouseClicked


    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        VentanaContador vc = new VentanaContador();
        vc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        mostrarEnca2();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void CodVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CodVentaKeyPressed

//        DefaultTableModel modelo = new DefaultTableModel() {
//
//            @Override
//            public boolean isCellEditable(int row, int columnas) {
//                return false;
//            }
//        };
//
//        modelo.addColumn("Código Venta");
//        modelo.addColumn("ID Clientas");
//        modelo.addColumn("Código Cajero");
//        modelo.addColumn("Fecha de Venta");
//        modelo.addColumn("Total");
//
//        jTable1.setModel(modelo);
//
//        String sql = "SELECT * FROM enca_venta WHERE cod_ven  LIKE '%" + CodVenta.getText() + "%'";
//
//        String[] datos = new String[5];
//
//        try {
//
//            Statement st = cn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            while (rs.next()) {
//
//                datos[0] = rs.getString(1);
//                datos[1] = rs.getString(2);
//                datos[2] = rs.getString(3);
//                datos[3] = rs.getString(4);
//                datos[4] = rs.getString(5);
//
//                modelo.addRow(datos);
//            }
//            jTable1.setModel(modelo);
//
//        } catch (SQLException ex) {
//
//            System.out.println("Error:" + ex);
//        }
    }//GEN-LAST:event_CodVentaKeyPressed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseClicked
        Fecha1.setEnabled(true);
        FechaH.setEnabled(true);
        CodVenta.setEnabled(false);
        jComboBox1.setEnabled(false);

    }//GEN-LAST:event_jRadioButton1MouseClicked

    private void jRadioButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton4MouseClicked
        Fecha1.setEnabled(false);
        FechaH.setEnabled(false);
        CodVenta.setEnabled(false);
        jComboBox1.setEnabled(true);
    }//GEN-LAST:event_jRadioButton4MouseClicked



    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

        
        String sql = "";
        
            if (jComboBox1.getSelectedItem().equals("Maximo")) {

                sql = " SELECT MAX(total) FROM enca_venta";

            } else if (jComboBox1.getSelectedItem().equals("Minimo")) {
                
                sql = " SELECT MIN(total) FROM enca_venta";
                
            } else if (jComboBox1.getSelectedItem().equals("Seleccione Venta")) {
                
                mostrarEnca();
            }


            try {

                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {

                    //salario = ;
                    JOptionPane.showMessageDialog(this, "Venta : " + rs.getString(1));

                }
                
                

            } catch (SQLException ex) {

                System.out.println("Error:" + ex);
            }
            
//        String variable = (String) jComboBox1.getSelectedItem();
//        
//        
//        //int codigoF = Integer.parseInt(codigo);
//        DefaultTableModel modelo = new DefaultTableModel() {
//
//            @Override
//            public boolean isCellEditable(int row, int columnas) {
//                return false;
//            }
//        };
//
//        modelo.addColumn("Código Venta");
//        modelo.addColumn("ID Clientas");
//        modelo.addColumn("Código Cajero");
//        modelo.addColumn("Fecha de Venta");
//        modelo.addColumn("Total");
//
//        jTable1.setModel(modelo);
//
//        
//        String sql = "";
//
//        //if (jComboBox1.getSelectedItem().equals(variable)) {
//        
//        if (jComboBox1.getSelectedItem().equals("Seleccione")) {
//            
//            sql = "SELECT * FROM enca_venta";
//            
//        } else{
//            //E.cod_cajer = '" + codigoF + "' AND C.cajero_codigo = '" + codigoF + "' AND 
//
//            sql = "SELECT E.cod_ven, E.id_clientes, E.cod_cajer, E.fecha, E.total  FROM enca_venta E,registrocajeros C WHERE E.cajero_num_caja = '"+variable+"'";
//
//        }
////        } else if (jComboBox1.getSelectedItem().equals("2")) {
////            sql = "SELECT E.cod_ven, E.id_clientes, E.cod_cajer, E.fecha, E.total  FROM enca_venta E,registrocajeros C WHERE E.cod_cajer = '" + codigoF + "' AND C.cajero_codigo = '" + codigoF + "' AND C.cajero_num_caja= '2'";
////
////        } else if (jComboBox1.getSelectedItem().equals("3")) {
////            sql = "SELECT E.cod_ven, E.id_clientes, E.cod_cajer, E.fecha, E.total  FROM enca_venta E,registrocajeros C WHERE E.cod_cajer = '" + codigoF + "' AND C.cajero_codigo = '" + codigoF + "' AND C.cajero_num_caja= '3'";
////
////        } else if (jComboBox1.getSelectedItem().equals("4")) {
////            sql = "SELECT E.cod_ven, E.id_clientes, E.cod_cajer, E.fecha, E.total  FROM enca_venta E,registrocajeros C WHERE E.cod_cajer = '" + codigoF + "' AND C.cajero_codigo = '" + codigoF + "' AND C.cajero_num_caja= '4'";
////
////        } else if (jComboBox1.getSelectedItem().equals("5")) {
////            sql = "SELECT E.cod_ven, E.id_clientes, E.cod_cajer, E.fecha, E.total  FROM enca_venta E,registrocajeros C WHERE E.cod_cajer = '" + codigoF + "' AND C.cajero_codigo = '" + codigoF + "'  AND C.cajero_num_caja= '5'";
////        
////        } else if (jComboBox1.getSelectedItem().equals("Seleccione")) {
////            sql = "SELECT * FROM enca_venta";
////        } 
//        
////        }else if (jComboBox1.getSelectedItem().equals("Seleccione")) {
////            sql = "SELECT * FROM enca_venta";
////        } 
//
//        String[] datos = new String[5];
//
//        try {
//
//            Statement st = cn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            while (rs.next()) {
//
//                datos[0] = rs.getString(1);
//                datos[1] = rs.getString(2);
//                datos[2] = rs.getString(3);
//                datos[3] = rs.getString(4);
//                datos[4] = rs.getString(5);
//
//                modelo.addRow(datos);
//            }
//            jTable1.setModel(modelo);
//
//        } catch (SQLException ex) {
//
//            System.out.println("Error:" + ex);
//        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       ReporteVentas.reporteVentas();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       mostrarEnca();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Reporteventas2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reporteventas2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reporteventas2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reporteventas2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reporteventas2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Botones;
    public static javax.swing.JTextField CodVenta;
    private com.toedter.calendar.JDateChooser Fecha1;
    private com.toedter.calendar.JDateChooser FechaH;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
