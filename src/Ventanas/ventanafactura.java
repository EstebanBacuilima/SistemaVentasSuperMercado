package Ventanas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ventanafactura extends javax.swing.JFrame {

    public static DefaultTableModel modelota;
    Date fecha;
    LocalDate fechaactual = LocalDate.now();
    int r;

    public ventanafactura() {
        initComponents();
        Trasparente();
        setLocationRelativeTo(null);

        CedulaFac.setEditable(false);
        NombreApellFac.setEditable(false);
        CorreoFacCli.setEditable(false);
        ventanafactura.DirrecionFacCli.setEditable(false);
        fechafac.setEditable(false);
        codfac.setEditable(false);
        //tabla
        modelota = new DefaultTableModel();
        modelota.addColumn("CODIGO");
        modelota.addColumn("NOMBRE");
        modelota.addColumn("CANTIDAD");
        modelota.addColumn("PRECIO U");
        modelota.addColumn("PRECIO TOTAL");
        tabla1.setModel(modelota);
        
        codfac.setText(VentanaCajero.Cod_Venta.getText());

    }

    void Trasparente() {

        CorreoFacCli.setBackground(new java.awt.Color(0, 0, 0, 1));
        telefono.setBackground(new java.awt.Color(0, 0, 0, 1));
        codfac.setBackground(new java.awt.Color(0, 0, 0, 1));
        CedulaFac.setBackground(new java.awt.Color(0, 0, 0, 1));
        codcli.setBackground(new java.awt.Color(0, 0, 0, 1));
        CedulaFac.setBackground(new java.awt.Color(0, 0, 0, 1));
        numcaja.setBackground(new java.awt.Color(0, 0, 0, 1));
        NombreApellFac.setBackground(new java.awt.Color(0, 0, 0, 1));
        apellifac.setBackground(new java.awt.Color(0, 0, 0, 1));
        DirrecionFacCli.setBackground(new java.awt.Color(0, 0, 0, 1));
        subtotal.setBackground(new java.awt.Color(0, 0, 0, 1));
        fechafac.setBackground(new java.awt.Color(0, 0, 0, 1));
        ivafacd.setBackground(new java.awt.Color(0, 0, 0, 1));
        totalfac.setBackground(new java.awt.Color(0, 0, 0, 1));
    }

    void limpiar() {
        codcli.setText("");
        cod_venta.setText("");
        codcajero.setText("");
        numcaja.setText("");
        CedulaFac.setText("");
        NombreApellFac.setText("");
        apellifac.setText("");
        telefono.setText("");
        DirrecionFacCli.setText("");
        CorreoFacCli.setText("");
        subtotal.setText("");
        ivafacd.setText("");
        totalfac.setText("");
        tabla1.setModel(new DefaultTableModel());
    }


    public int codIncrement() {
        int serie = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = cc.conectar();

        try {

            ps = cn.prepareStatement("SELECT MAX(cod_det_fac) FROM detalle_factura");
            rs = ps.executeQuery();

            while (rs.next()) {
                serie = rs.getInt(1) + 1;
                //CodEmpleado.setText(serie);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return serie;
    }

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tot = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        CorreoFacCli = new javax.swing.JTextField();
        CedulaFac = new javax.swing.JTextField();
        DirrecionFacCli = new javax.swing.JTextField();
        NombreApellFac = new javax.swing.JTextField();
        fechafac = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        codfac = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        apellifac = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        totalfac = new javax.swing.JTextField();
        telefono = new javax.swing.JTextField();
        subtotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        codcajero = new javax.swing.JTextField();
        numcaja = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        ivafacd = new javax.swing.JTextField();
        codcli = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cod_venta = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel1.setText("Apellido");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, 30));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Factura");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 180, 40));

        jLabel4.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel4.setText("Nº");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, -1, -1));

        jLabel5.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel5.setText("Nº Caja");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, -1, 30));

        jLabel7.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel7.setText("Correo");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, -1, 30));

        jLabel8.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel8.setText("SUB TOTAL");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 450, -1, 30));

        CorreoFacCli.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CorreoFacCli.setBorder(null);
        CorreoFacCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CorreoFacCliActionPerformed(evt);
            }
        });
        getContentPane().add(CorreoFacCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, 230, 30));

        CedulaFac.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CedulaFac.setBorder(null);
        CedulaFac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CedulaFacActionPerformed(evt);
            }
        });
        getContentPane().add(CedulaFac, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 180, 30));

        DirrecionFacCli.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        DirrecionFacCli.setBorder(null);
        DirrecionFacCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DirrecionFacCliActionPerformed(evt);
            }
        });
        getContentPane().add(DirrecionFacCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 510, 30));

        NombreApellFac.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NombreApellFac.setBorder(null);
        NombreApellFac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreApellFacActionPerformed(evt);
            }
        });
        getContentPane().add(NombreApellFac, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 180, 30));

        fechafac.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fechafac.setBorder(null);
        fechafac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechafacActionPerformed(evt);
            }
        });
        getContentPane().add(fechafac, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 520, 220, 40));

        jLabel9.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel9.setText("Fecha");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, -1, -1));

        codfac.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        codfac.setBorder(null);
        getContentPane().add(codfac, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 40, 40));

        jLabel3.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel3.setText("Nombre");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 50));

        apellifac.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        apellifac.setBorder(null);
        apellifac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellifacActionPerformed(evt);
            }
        });
        getContentPane().add(apellifac, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 180, 30));

        jScrollPane1.setViewportView(tabla1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 650, 170));

        totalfac.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        totalfac.setBorder(null);
        getContentPane().add(totalfac, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 530, 110, 30));

        telefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        telefono.setBorder(null);
        getContentPane().add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, 230, 30));

        subtotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        subtotal.setBorder(null);
        getContentPane().add(subtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 450, 110, 30));

        jLabel11.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel11.setText("IVA");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 490, -1, 30));
        getContentPane().add(codcajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 40, 40));

        numcaja.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numcaja.setBorder(null);
        getContentPane().add(numcaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 110, 40, 30));

        jLabel12.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel12.setText("Telefono");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 192, -1, 30));

        jLabel13.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel13.setText("Direccion");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, 30));

        jLabel14.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel14.setText("TOTAL");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 530, 60, 30));

        ivafacd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ivafacd.setBorder(null);
        getContentPane().add(ivafacd, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 490, 110, 30));

        codcli.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        codcli.setBorder(null);
        getContentPane().add(codcli, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, 40, 30));

        jLabel15.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel15.setText("Cedula");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 70, 30));
        getContentPane().add(cod_venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, 80, 30));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPriPOO(1).png"))); // NOI18N
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 10, 210, 60));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 580, 260, -1));

        jLabel6.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel6.setText("Codigo");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, -1, 30));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 440, -1, 140));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(658, 440, -1, 140));

        jPanel1.setBackground(new java.awt.Color(222, 222, 222));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ChekScanner.png"))); // NOI18N
        jButton1.setToolTipText("Cerrar");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 591, 50, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CorreoFacCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CorreoFacCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CorreoFacCliActionPerformed

    private void CedulaFacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaFacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CedulaFacActionPerformed

    private void DirrecionFacCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DirrecionFacCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DirrecionFacCliActionPerformed

    private void NombreApellFacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreApellFacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreApellFacActionPerformed

    private void fechafacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechafacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechafacActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        limpiar();
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void apellifacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellifacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellifacActionPerformed

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
            java.util.logging.Logger.getLogger(ventanafactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanafactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanafactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanafactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventanafactura().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField CedulaFac;
    public static javax.swing.JTextField CorreoFacCli;
    public static javax.swing.JTextField DirrecionFacCli;
    public static javax.swing.JTextField NombreApellFac;
    public static javax.swing.JTextField apellifac;
    public static javax.swing.JTextField cod_venta;
    public static javax.swing.JTextField codcajero;
    public static javax.swing.JTextField codcli;
    public static javax.swing.JTextField codfac;
    public static javax.swing.JTextField fechafac;
    protected static javax.swing.JTextField ivafacd;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    public static javax.swing.JTextField numcaja;
    public static javax.swing.JTextField subtotal;
    private javax.swing.JTable tabla1;
    public static javax.swing.JTextField telefono;
    private javax.swing.JLabel tot;
    public static javax.swing.JTextField totalfac;
    // End of variables declaration//GEN-END:variables
}
