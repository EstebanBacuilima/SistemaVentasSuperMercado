package Ventanas;

import javax.swing.*;
import java.sql.*;
import Ventanas.*;
import static Ventanas.Menu.Imagen;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Statement;

public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);

        // HACER TRASNPARENTES LOS txtFields
        txtuser.setBackground(new java.awt.Color(0, 0, 0, 1));
        jPassword.setBackground(new java.awt.Color(0, 0, 0, 1));

    }

//    void mostrardatos() {
//
//        String sql = "SELECT R.empleado_codigo, R.persona_cedula FROM regusuario R WHERE user_estado = 'A' AND user_usuario = '" + txtuser.getText() + "'";
//
//        try {
//
//            Statement st = cn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            while (rs.next()) {
//
//                CodigoEmp.setText(rs.getString(1));
//                CedulaEm.setText(rs.getString(2));;
//
//            }
//        
//
//        } catch (SQLException ex) {
//
//            System.out.println("Error:" + ex);
//        }
//
//    }
    // NMERO DE CAJA
    String numeroCAJA;

    void mostrardatosNumCaja(String codigosEC) {

        //Trae el num de caja del empleado 
        //String sql = "SELECT C.cajero_num_caja FROM empleado E, registrocajeros C WHERE E.empleado_codigo ='" + codigosEC + "' AND C.empleado_codigo = '" + codigosEC + "' AND C.cajero_estado = 'A'";

        String sql = "SELECT C.cajero_num_caja FROM empleado E, registrocajeros C WHERE C.cajero_codigo = '" + codigosEC + "' AND C.cajero_estado = 'A'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                numeroCAJA = rs.getString(1);

            }

            NumeroCaja.setText(numeroCAJA);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No Encontrado");
            System.out.println("Error:" + ex);
        }

    }

    //GERENTEE
    String NombreGer;
    String imagenGer;

    protected static String Imagen;
    protected static String Dest, Orig;

    void mostrardatosGerente() {

        String sql = "SELECT P.persona_nombre, G.imagen_gerente FROM persona P, regusuario R, gerente G WHERE P.persona_id = '2' AND R.empleado_codigo='1' AND G.empleado_codigo ='1' AND R.user_estado = 'A'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                NombreGer = rs.getString(1);
                Imagen = rs.getString(2);

                Orig = "src/Foto_Perfil/" + Imagen;
//                ImageIcon icon = new ImageIcon(Orig);
//                ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(FotoPerfil.getWidth(), FotoPerfil.getHeight(), Image.SCALE_DEFAULT));
//                FotoPerfil.setText(null);
//                FotoPerfil.setIcon(icono);

            }

            Nombre_G.setText(NombreGer);
            Imagen_G.setText(Imagen);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error" + ex);
            System.out.println("Error:" + ex);
        }

    }

    void  EnviarDatos() {
        
        String codigos = null;

        String sql = "SELECT R.empleado_codigo, R.persona_cedula FROM regusuario R WHERE user_estado = 'A' AND user_usuario = '" + txtuser.getText() + "'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                codigos = rs.getString(1);

                CodigoEmpe.setText(rs.getString(1));
                CedulaEmp.setText(rs.getString(2));

            }

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

        mostrardatosNumCaja(codigos);
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtuser = new javax.swing.JTextField();
        jPassword = new javax.swing.JPasswordField();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Imagen_G = new javax.swing.JTextField();
        CodigoEmpe = new javax.swing.JTextField();
        CedulaEmp = new javax.swing.JTextField();
        NumeroCaja = new javax.swing.JTextField();
        Nombre_G = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel5.setText("Usuario:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, 50));

        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel6.setText("Contraseña:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, 70));

        txtuser.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtuser.setForeground(new java.awt.Color(153, 153, 153));
        txtuser.setText(" Ingrese usuario");
        txtuser.setBorder(null);
        txtuser.setDoubleBuffered(true);
        txtuser.setDragEnabled(true);
        txtuser.setOpaque(false);
        txtuser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtuserMousePressed(evt);
            }
        });
        txtuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtuserActionPerformed(evt);
            }
        });
        txtuser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtuserKeyTyped(evt);
            }
        });
        getContentPane().add(txtuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 340, 30));

        jPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPassword.setForeground(new java.awt.Color(153, 153, 153));
        jPassword.setText("  xxxxxx");
        jPassword.setBorder(null);
        jPassword.setOpaque(false);
        jPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPasswordMousePressed(evt);
            }
        });
        getContentPane().add(jPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 340, 30));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPriPOO(1).png"))); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 190, 70));

        jPanel1.setBackground(new java.awt.Color(86, 176, 218));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel1.setText("Login");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 460, 140, 40));

        jPanel2.setBackground(new java.awt.Color(86, 176, 218));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel3.setText("Olvide Contraseña");
        jPanel2.add(jLabel3);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 510, 200, 40));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/imageSalirLogin.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setDefaultCapable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 60, 50));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user1_opt (1).png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 150, 160));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 340, 2));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 340, 2));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondoceleste.jpg"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153), 2));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 590));
        getContentPane().add(Imagen_G, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 90, 40));
        getContentPane().add(CodigoEmpe, new org.netbeans.lib.awtextra.AbsoluteConstraints(329, 152, 90, 30));
        getContentPane().add(CedulaEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(329, 212, 90, 30));
        getContentPane().add(NumeroCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 92, 40, 40));
        getContentPane().add(Nombre_G, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 260, 110, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtuserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtuserActionPerformed

    private void txtuserMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtuserMousePressed

        if (txtuser.getText().equals(" Ingrese usuario")) {
            txtuser.setText("");
            txtuser.setForeground(Color.black);
        }

        if (String.valueOf(jPassword.getPassword()).isEmpty()) {
            jPassword.setText("  xxxxxx");
            jPassword.setForeground(Color.black);
        }

    }//GEN-LAST:event_txtuserMousePressed

    private void jPasswordMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordMousePressed

        if (String.valueOf(jPassword.getPassword()).equals("  xxxxxx")) {
            jPassword.setText("");
            jPassword.setForeground(Color.black);
        }

        if (txtuser.getText().isEmpty()) {
            txtuser.setText(" Ingrese usuario");
            txtuser.setForeground(Color.black);
        }


    }//GEN-LAST:event_jPasswordMousePressed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        String auxusuario = this.txtuser.getText();
        String auxpass = this.jPassword.getText();

       
//        String codigos = null;
//
//        String sql = "SELECT R.empleado_codigo, R.persona_cedula FROM regusuario R WHERE user_estado = 'A' AND user_usuario = '" + txtuser.getText() + "'";
//
//        try {
//
//            Statement st = cn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            while (rs.next()) {
//
//                codigos = rs.getString(1);
//
//                CodigoEmpe.setText(rs.getString(1));
//                CedulaEmp.setText(rs.getString(2));
//
//            }
//
//        } catch (SQLException ex) {
//
//            System.out.println("Error:" + ex);
//        }
//
//        //mostrardatosGerente();
//        mostrardatosNumCaja(codigos);

        Acceder(auxusuario, auxpass);


    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        RecuperarContrasenna rc = new RecuperarContrasenna();
        rc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtuserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtuserKeyTyped

    }//GEN-LAST:event_txtuserKeyTyped

    int found;

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();

    public void Acceder(String usuario, String pass) {

        String cap = "";
        String sql = "SELECT * FROM regusuario WHERE User_Usuario='" + usuario + "'AND User_Contrasena='" + pass + "' AND user_estado = 'A'";// OR user_estado = 'C'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                cap = rs.getString("User_Tipo");
            }
            if (cap.equals("Cajero")) {
                EnviarDatos();
                VentanaCajero ve = new VentanaCajero();
                ve.setVisible(true);
                this.dispose();
            } else {
                if (cap.equals("Gerente")) {
                    mostrardatosGerente();
                    Menu vc = new Menu();
                    vc.setVisible(true);
                    this.dispose();
                } else {
                    if (cap.equals("Bodeguero")) {
                        EnviarDatos();
                        VentanaBodeguero vc = new VentanaBodeguero();
                        vc.setVisible(true);
                        this.dispose();

                    } else {
                        if (cap.equals("Contador")) {
                            EnviarDatos();
                            VentanaContador vc = new VentanaContador();
                            vc.setVisible(true);
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Usuario no registrado");
                        }
                    }
                }
            }
        } catch (SQLException ex) {

            System.out.println("Error " + ex);
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
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField CedulaEmp;
    public static javax.swing.JTextField CodigoEmpe;
    public static javax.swing.JTextField Imagen_G;
    public static javax.swing.JTextField Nombre_G;
    public static javax.swing.JTextField NumeroCaja;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public static javax.swing.JPasswordField jPassword;
    public static javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables
}
