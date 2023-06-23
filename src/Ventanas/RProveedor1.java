package Ventanas;

import static Ventanas.RegistrarProductos.ruxcs;
import static Ventanas.RegistroPedidos.RUC_prov;
import static Ventanas.RegistroPedidos.Ced_ger;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RProveedor1 extends javax.swing.JFrame {

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();

    PreparedStatement ps = null;
    Statement st = null;
    ResultSet rs = null;

    public RProveedor1() {
        initComponents();
        mostrardatos("");
        setLocationRelativeTo(null);
        TraerDatos();
    }

    void mostrardatos(String Ruc) {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        //DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("RUC");
        modelo.addColumn("NOMBRE EMPRESA");
        modelo.addColumn("DIRECCION");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("CEDULA");
        modelo.addColumn("NOMBRE ");
        modelo.addColumn("CORREO");
        Table.setModel(modelo);

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

            Table.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    // CREAR ARRAY LISTA ALMACENAR 
    ArrayList name = new ArrayList();
    String datos;

    private void TraerDatos() {

        // SENTECIA SELCIONAR LA TABLA
        String sql = "SELECT * FROM proveedores WHERE estado_provee ='A'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                // Traer el campo especificado de la tabla de la Base
                datos = rs.getString("ruc_provee");
                // Añadimos todo al Array
                name.add(datos);
            }

//            cn.close();
//            st.close();
//            rs.close();
        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }

    public void autoCompletar(String txt) {

        String complete = "";
        // Declaramos int al iniciar escribir y terminar de escribir 
        // length() la longitud de lo escrito
        int start = txt.length();
        int last = txt.length();

        int a;

        // Creamos un for que rrecore nuestro array buscando coincidencias 
        for (a = 0; a < name.size(); a++) {

            // startWiht = usa para verificar el prefijo de una cadena.
            if (name.get(a).toString().startsWith(txt)) {

                complete = name.get(a).toString();
                last = complete.length();

                break;
            }
        }

        if (last > start) {

            RUC.setText(complete);
            RUC.setCaretPosition(last);
            RUC.moveCaretPosition(start);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        RUC = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Ingresar RUC o Nombre del Proveedor");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 350, 30));

        RUC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RUCActionPerformed(evt);
            }
        });
        RUC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RUCKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                RUCKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                RUCKeyTyped(evt);
            }
        });
        getContentPane().add(RUC, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 50, 190, -1));

        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 580, 180));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonSalir_1.png"))); // NOI18N
        jButton2.setToolTipText("Salir");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 80, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RUCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RUCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RUCActionPerformed

    private void RUCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RUCKeyTyped

    }//GEN-LAST:event_RUCKeyTyped

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
        int fila = Table.getSelectedRow();

        if (evt.getClickCount() == 2) {

            if (evt.getClickCount() == 2) {
                dispose();
                if (fila >= 0) {

                    ruxcs.setText(Table.getValueAt(fila, 0).toString());
                    ruxcs.setEditable(false);
                }

            }
        }
    }//GEN-LAST:event_TableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void RUCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RUCKeyPressed
//        DefaultTableModel modelo = new DefaultTableModel() {
//
//            @Override
//            public boolean isCellEditable(int row, int columnas) {
//                return false;
//            }
//        };
//
//        //DefaultTableModel modelo = new DefaultTableModel();
//        modelo.addColumn("RUC");
//        modelo.addColumn("NOMBRE EMPRESA");
//        modelo.addColumn("DIRECCION");
//        modelo.addColumn("TELEFONO");
//        modelo.addColumn("CEDULA");
//        modelo.addColumn("NOMBRE ");
//        modelo.addColumn("CORREO");
//        Table.setModel(modelo);
//        
//        String sql = "";
//         sql = "SELECT * FROM proveedores WHERE ruc_provee LIKE '%" + RUC.getText() + "%' "
//        + "OR nombre_provee LIKE '%" + RUC.getText() + "%'";
//        String[] datos = new String[7];
//        try {
//            Statement st = cn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                datos[0] = rs.getString(1);
//                datos[1] = rs.getString(2);
//                datos[2] = rs.getString(3);
//                datos[3] = rs.getString(4);
//                datos[4] = rs.getString(5);
//                datos[5] = rs.getString(6);
//                datos[6] = rs.getString(7);
//                System.out.println(rs);
//                modelo.addRow(datos);
//            }
//            Table.setModel(modelo);
//        } catch (SQLException ex) {
//            System.out.println("Error:" + ex);
//        }

        switch (evt.getKeyCode()) {

            case KeyEvent.VK_BACK_SPACE:
                break;

            case KeyEvent.VK_ENTER:
                mostrardatos(RUC.getText());
                break;

            default:

                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        String txt = RUC.getText();
                        autoCompletar(txt);

                    }
                });

        }

    }//GEN-LAST:event_RUCKeyPressed

    private void RUCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RUCKeyReleased

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        //DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("RUC");
        modelo.addColumn("NOMBRE EMPRESA");
        modelo.addColumn("DIRECCION");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("CEDULA");
        modelo.addColumn("NOMBRE ");
        modelo.addColumn("CORREO");
        Table.setModel(modelo);

        String sql = "";
        sql = "SELECT * FROM proveedores WHERE ruc_provee LIKE '%" + RUC.getText() + "%' "
                + "OR nombre_provee LIKE '%" + RUC.getText() + "%'";
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
            Table.setModel(modelo);
        } catch (SQLException ex) {
            System.out.println("Error:" + ex);
        }
    }//GEN-LAST:event_RUCKeyReleased

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
            java.util.logging.Logger.getLogger(RProveedor1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RProveedor1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RProveedor1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RProveedor1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new RProveedor1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField RUC;
    private javax.swing.JTable Table;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
