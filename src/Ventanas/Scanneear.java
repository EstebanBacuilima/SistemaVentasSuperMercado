package Ventanas;

import static Ventanas.Login.Imagen;
import static Ventanas.VentanaCajero.Pro_Cod;
import static Ventanas.VentanaCajero.Pro_Nombre;
import static Ventanas.VentanaCajero.Pro_precio;
import static Ventanas.VentanaCajero.Stock;
import static Ventanas.VentanaCajero.jTiva;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class Scanneear extends javax.swing.JFrame {

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();

    public Scanneear() {
        initComponents();
        setLocationRelativeTo(null);
    }

    protected static String Imagen;
    protected static String Dest, Orig;

    String imagenGer;

    void mostrardatosPro(String codp) {

        // Acumular la sentencia
        String sql = "";

        sql = "SELECT * FROM registroproducto WHERE pro_cbarras  = '" + jTextField1.getText() + "' ";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Pro_Cod.setText(rs.getString(1));
                Pro_Cod.setEditable(false);
                Pro_Nombre.setText(rs.getString(3));
                Pro_precio.setText(rs.getString(7));
                Stock.setText(rs.getString(8));
                jTiva.setText(rs.getString(11));
                Imagen = rs.getString(13);

                Orig = "src/Imagenes_Productos/" + Imagen;
//                ImageIcon icon = new ImageIcon(Orig);
//                ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(FotoPerfil.getWidth(), FotoPerfil.getHeight(), Image.SCALE_DEFAULT));
//                FotoPerfil.setText(null);
//                FotoPerfil.setIcon(icono);

                Imagen_G.setText(Imagen);
                
                VentanaCajero.NombreFoto.setText(Imagen);

                Orig = "src/Imagenes_Productos/" + VentanaCajero.NombreFoto.getText();
                ImageIcon icon = new ImageIcon(Orig);
                ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(VentanaCajero.FotoProducto.getWidth(), VentanaCajero.FotoProducto.getHeight(), Image.SCALE_DEFAULT));
                VentanaCajero.FotoProducto.setText(null);
                VentanaCajero.FotoProducto.setIcon(icono);
            }

            //this.dispose();

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        Imagen_G = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 360, 40));
        getContentPane().add(Imagen_G, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 89, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ChekScanner.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 60, 40));

        jLabel2.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel2.setText("CODIGO DE BARRAS");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 250, -1));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 120));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        mostrardatosPro(jTextField1.getText());
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Scanneear.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Scanneear.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Scanneear.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Scanneear.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Scanneear().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Imagen_G;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
