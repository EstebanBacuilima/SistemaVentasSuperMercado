package Ventanas;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class RecuperarContrasenna extends javax.swing.JFrame {

    public RecuperarContrasenna() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtusuario.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtnuevacontra.setBackground(new java.awt.Color(0, 0, 0, 1));
    
    }

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();

    // USUARIOS
    // Cajero = CAJ001
    // Contador = CON001
    // Bodeguero = BOD001
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtnuevacontra = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel2.setText("Usuario:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 90, 50));

        txtusuario.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        txtusuario.setForeground(new java.awt.Color(204, 204, 204));
        txtusuario.setText("  Usuario");
        txtusuario.setBorder(null);
        txtusuario.setOpaque(false);
        txtusuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtusuarioMousePressed(evt);
            }
        });
        getContentPane().add(txtusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 250, 30));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel4.setText("Nueva Contraseña:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 40));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonSalirCrud.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setDefaultCapable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, 60, 50));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPriPOO(1).png"))); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 230, 70));

        jPanel1.setBackground(new java.awt.Color(113, 193, 205));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("GUARDAR CAMBIOS");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 190, 30));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 250, 2));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 250, 2));

        txtnuevacontra.setForeground(new java.awt.Color(204, 204, 204));
        txtnuevacontra.setText("  Nueva contraseña");
        txtnuevacontra.setToolTipText("");
        txtnuevacontra.setBorder(null);
        txtnuevacontra.setOpaque(false);
        txtnuevacontra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtnuevacontraMousePressed(evt);
            }
        });
        getContentPane().add(txtnuevacontra, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 250, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondoceleste1.jpg"))); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204), 2));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void Limpiar() {

        txtusuario.setText("  Usuario");
        txtusuario.setForeground(Color.gray);
        txtnuevacontra.setText("  Nueva contraseña");
        txtnuevacontra.setForeground(Color.gray);
    }

    public void Modificar() {

        int n = JOptionPane.showConfirmDialog(null, "Esta seguro de Actualizar ?", "WARNING", JOptionPane.YES_NO_OPTION);

        if (n == JOptionPane.YES_OPTION) {

            try {

                PreparedStatement pst = cn.prepareStatement("UPDATE regusuario SET user_contrasena='" + txtnuevacontra.getText() + "' WHERE user_usuario='" + txtusuario.getText() + "'");
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Actualizado");
                Limpiar();

                Login inicio = new Login();
                inicio.setVisible(true);
                this.dispose();

            } catch (Exception e) {

                System.out.println(e.getMessage());

            }

        } else {
            Limpiar();
        }
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Login inicio = new Login();
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        String usuario = txtusuario.getText();

        String cap = "";
        String sql = "SELECT * FROM regusuario WHERE User_Usuario='" + usuario + "' AND user_estado = 'A'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                cap = rs.getString("User_Tipo");
            }
            if (cap.equals("Cajero")) {
                Modificar();
                Limpiar();
            } else {
                if (cap.equals("Gerente")) {
                    Modificar();
                    Limpiar();
                } else {
                    if (cap.equals("Bodeguero")) {
                        Modificar();
                        Limpiar();
                    } else {
                        if (cap.equals("Contador")) {
                            Modificar();
                            Limpiar();
                        } else {
                            JOptionPane.showMessageDialog(null, "Usuario no registrado");
                            Limpiar();
                        }
                    }
                }
            }
        } catch (SQLException ex) {

            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jPanel1MouseClicked

    private void txtusuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtusuarioMousePressed
        if (txtusuario.getText().equals("  Usuario")) {
            txtusuario.setText("");
            txtusuario.setForeground(Color.BLACK);
        }

        if (String.valueOf(txtnuevacontra.getText()).isEmpty()) {
            txtnuevacontra.setText("  Nueva contraseña");
            txtnuevacontra.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtusuarioMousePressed

    private void txtnuevacontraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnuevacontraMousePressed
       if (String.valueOf(txtnuevacontra.getPassword()).equals("  Nueva contraseña")) {
            txtnuevacontra.setText("");
            txtnuevacontra.setForeground(Color.black);
        }

        if (txtusuario.getText().isEmpty()) {
            txtusuario.setText("  Usuario");
            txtusuario.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtnuevacontraMousePressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecuperarContrasenna().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField txtnuevacontra;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
