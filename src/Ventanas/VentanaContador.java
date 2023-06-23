package Ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.Timer;

public class VentanaContador extends javax.swing.JFrame {


    public VentanaContador() {
        initComponents();
        setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RProductos = new javax.swing.JButton();
        RVentas = new javax.swing.JButton();
        RClientes = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Pedidos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        RPedidos = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ReportePro.png"))); // NOI18N
        RProductos.setToolTipText("Reporte Productos");
        RProductos.setContentAreaFilled(false);
        RProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RProductosActionPerformed(evt);
            }
        });
        getContentPane().add(RProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 139, -1));

        RVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ReporteV.png"))); // NOI18N
        RVentas.setToolTipText("Reporte Ventas");
        RVentas.setContentAreaFilled(false);
        RVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RVentasActionPerformed(evt);
            }
        });
        getContentPane().add(RVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, 197, -1));

        RClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ReporteCli.png"))); // NOI18N
        RClientes.setToolTipText("Reporte Clientes");
        RClientes.setContentAreaFilled(false);
        RClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RClientesActionPerformed(evt);
            }
        });
        getContentPane().add(RClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 490, 146, 160));

        jLabel3.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel3.setText("Reporte de Productos");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 300, -1));

        jLabel4.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel4.setText("Reporte de Ventas");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 440, 260, -1));

        jLabel5.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel5.setText("Reporte de Clientes");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 630, 270, -1));

        fecha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        fecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fecha.setText("DIA - MES - AÑO");
        getContentPane().add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 640, -1, -1));

        jLabel7.setFont(new java.awt.Font("Agency FB", 1, 48)); // NOI18N
        jLabel7.setText("MENU");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 90, 70));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Pedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pedido.png"))); // NOI18N
        Pedidos.setToolTipText("Pedidos");
        Pedidos.setContentAreaFilled(false);
        Pedidos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Pedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PedidosActionPerformed(evt);
            }
        });
        jPanel1.add(Pedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 120, 110));

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel1.setText("Generar Pedidos");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 210, -1));

        RPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ReporteP.png"))); // NOI18N
        RPedidos.setToolTipText("Reporte Pedidos");
        RPedidos.setContentAreaFilled(false);
        RPedidos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RPedidosActionPerformed(evt);
            }
        });
        jPanel1.add(RPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, 150, 150));

        jLabel2.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel2.setText("Reporte de Pedidos");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 220, 240, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonSalir_1.png"))); // NOI18N
        jButton1.setToolTipText("Cerrar Sesion");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 70, 70));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 690));

        jLabel6.setBackground(new java.awt.Color(153, 255, 255));
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PedidosActionPerformed
        RegistroPedidos rp = new RegistroPedidos();
        rp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_PedidosActionPerformed

    private void RPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RPedidosActionPerformed
        ReportePedidos rpe = new ReportePedidos();
        rpe.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RPedidosActionPerformed

    private void RVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RVentasActionPerformed
        Reporteventas2 rpe = new Reporteventas2();
        rpe.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RVentasActionPerformed

    private void RClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RClientesActionPerformed
        ReportCliente rpe = new ReportCliente();
        rpe.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RClientesActionPerformed

    private void RProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RProductosActionPerformed
       ReporteProductos rpe = new ReporteProductos();
        rpe.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RProductosActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       //Fehca del sistema
        Date sistemaFech = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd MMMM yyyy");
        fecha.setText(formato.format(sistemaFech));

//       //Hora del sistema
//        Timer time = new Timer(100, new Menu.horas());
//        time.start();
        
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Login login = new Login();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    class horas implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Date sistemaHora = new Date();
            String pmAm = "hh:mm:ss a";
            // PONER SEGUNDOS "hh:mm:ss a";
            SimpleDateFormat formato = new SimpleDateFormat(pmAm);
            Calendar now = Calendar.getInstance();
            //hora.setText(String.format(formato.format(sistemaHora), now));
            
        }
    }
    
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
            java.util.logging.Logger.getLogger(VentanaContador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaContador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaContador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaContador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaContador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Pedidos;
    private javax.swing.JButton RClientes;
    private javax.swing.JButton RPedidos;
    private javax.swing.JButton RProductos;
    private javax.swing.JButton RVentas;
    private javax.swing.JLabel fecha;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
