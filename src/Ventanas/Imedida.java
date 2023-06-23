package Ventanas;

import djmeb.Medidas;
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

public class Imedida extends javax.swing.JFrame {

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();

    public Imedida() {
        initComponents();
        TraerDatos();
        setLocationRelativeTo(null);
        mostrardatosMedida("", 1);
        Medida_cod.setText(String.valueOf(codIncrement_medida()));
        Medida_cod.setEditable(false);
    }

    public int codIncrement_medida() {

        int serie = 1;

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = cc.conectar();

        try {

            ps = cn.prepareStatement("SELECT MAX(Medida_cod) FROM registromedidas");
            rs = ps.executeQuery();

            while (rs.next()) {
                serie = rs.getInt(1) + 1;
            }

        } catch (Exception e) {

            System.out.println("M4 Error" + e.getMessage());
        }

        return serie;

    }

    void mostrardatosMedida(String codm, int estado) {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        //DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");

//        modelo.addColumn("Iva");
        TablaMedidas.setModel(modelo);

        // Acumular la sentencia
        String sqlm = "";
        if (estado == 1) {
            if (codm.equals("")) {

                sqlm = "SELECT * FROM registromedidas WHERE medida_estado='1'";

            } else {

                sqlm = "SELECT * FROM registromedidas WHERE medida_nombre like '%" + codm + "%' and medida_estado = '1'";
            }
        } else if (estado == 2) {
            if (codm.equals("")) {

                sqlm = "SELECT * FROM registromedidas WHERE medida_estado='2'";

            } else {

                sqlm = "SELECT * FROM registromedidas WHERE medida_nombre like'%" + codm + "%'and medida_estado = '2'";
            }
        }

        String[] datos = new String[2];

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqlm);

            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);

                modelo.addRow(datos);
            }
            TablaMedidas.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }

    public void Limpiar_medida() {
        Medida_nombre.setText("");
        Medida_cod.setText("");
        Medida_cod.setText(String.valueOf(codIncrement_medida()));
    }

/////////////////////////////////////////////////////////////////////////////
    // CREAR ARRAY LISTA ALMACENAR 
    ArrayList name = new ArrayList();
    String datos;

    private void TraerDatos() {

        // SENTECIA SELCIONAR LA TABLA
        String sql = "SELECT * FROM registromedidas WHERE medida_estado='1'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                // Traer el campo especificado de la tabla de la Base
                datos = rs.getString("medida_unidad");
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

            Medida_buscar.setText(complete);
            Medida_buscar.setCaretPosition(last);
            Medida_buscar.moveCaretPosition(start);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Marcas = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        Medida_nombre = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        Medida_buscar = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        TablaMedidas = new javax.swing.JTable();
        Guardar_Medida = new javax.swing.JButton();
        Eliminar_medida = new javax.swing.JButton();
        Modificar_medida = new javax.swing.JButton();
        Medida_cod = new javax.swing.JTextField();
        Mostrar_medidas = new javax.swing.JButton();
        med_Inactivos = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Marcas.setBackground(new java.awt.Color(153, 255, 255));
        Marcas.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        Marcas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel24.setText("Registro de Medidas");
        Marcas.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel25.setText("Tipo de medida:");
        Marcas.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, 20));
        Marcas.add(Medida_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 180, 25));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel26.setText("Codigo de Medida:");
        Marcas.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, 20));

        Medida_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Medida_buscarKeyReleased(evt);
            }
        });
        Marcas.add(Medida_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 150, 30));

        TablaMedidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablaMedidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaMedidasMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(TablaMedidas);

        Marcas.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 450, 200));

        Guardar_Medida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Gurdar 40 x 40.png"))); // NOI18N
        Guardar_Medida.setToolTipText("Guardar");
        Guardar_Medida.setBorder(null);
        Guardar_Medida.setBorderPainted(false);
        Guardar_Medida.setContentAreaFilled(false);
        Guardar_Medida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Guardar_Medida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Guardar_MedidaActionPerformed(evt);
            }
        });
        Marcas.add(Guardar_Medida, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, -1, -1));

        Eliminar_medida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Eliminar 40 x 40.png"))); // NOI18N
        Eliminar_medida.setToolTipText("Eliminar");
        Eliminar_medida.setBorder(null);
        Eliminar_medida.setBorderPainted(false);
        Eliminar_medida.setContentAreaFilled(false);
        Eliminar_medida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Eliminar_medida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Eliminar_medidaActionPerformed(evt);
            }
        });
        Marcas.add(Eliminar_medida, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, -1, -1));

        Modificar_medida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Modificar 40 x 40.png"))); // NOI18N
        Modificar_medida.setToolTipText("Modificar");
        Modificar_medida.setBorder(null);
        Modificar_medida.setBorderPainted(false);
        Modificar_medida.setContentAreaFilled(false);
        Modificar_medida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Modificar_medida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Modificar_medidaActionPerformed(evt);
            }
        });
        Marcas.add(Modificar_medida, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, -1, -1));
        Marcas.add(Medida_cod, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 50, 25));

        Mostrar_medidas.setBackground(new java.awt.Color(102, 204, 255));
        Mostrar_medidas.setFont(new java.awt.Font("Agency FB", 1, 16)); // NOI18N
        Mostrar_medidas.setText("Mostrar");
        Mostrar_medidas.setBorderPainted(false);
        Mostrar_medidas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Mostrar_medidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mostrar_medidasActionPerformed(evt);
            }
        });
        Marcas.add(Mostrar_medidas, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, -1, 40));

        med_Inactivos.setBackground(new java.awt.Color(153, 255, 255));
        med_Inactivos.setText("INACTIVOS ");
        med_Inactivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                med_InactivosMouseClicked(evt);
            }
        });
        med_Inactivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                med_InactivosActionPerformed(evt);
            }
        });
        Marcas.add(med_Inactivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 500, 110, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonSalirCrud.png"))); // NOI18N
        jButton1.setToolTipText("Volver");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Marcas.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, -1, -1));

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LimpiarCajero.png"))); // NOI18N
        jButton2.setToolTipText("Limpiar");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Marcas.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Guardar");
        Marcas.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, -1, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Modificar");
        jLabel2.setToolTipText("");
        Marcas.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 60, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Eliminar");
        Marcas.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, 50, 20));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Lupa 23 x 23.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        Marcas.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 50, 30));

        getContentPane().add(Marcas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -9, 490, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Medida_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Medida_buscarKeyReleased
//        if (med_Inactivos.isSelected()) {
//            mostrardatosMedida(Medida_buscar.getText(), 2);
//
//        } else {
//            mostrardatosMedida(Medida_buscar.getText(), 1);
//        }

        if (Medida_buscar.getText().isEmpty()) {
            
            mostrardatosMedida(Medida_buscar.getText(), 1);
            
        } else {
            
            switch (evt.getKeyCode()) {

            case KeyEvent.VK_BACK_SPACE:
                break;

            case KeyEvent.VK_ENTER:
                mostrardatosMedida(Medida_buscar.getText(), 1);
                break;

            default:

                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        String txt = Medida_buscar.getText();
                        autoCompletar(txt);

                    }
                });

        }
        }
        


    }//GEN-LAST:event_Medida_buscarKeyReleased

    private void TablaMedidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMedidasMouseClicked
        if (med_Inactivos.isSelected()) {
            JOptionPane.showMessageDialog(null, "No puede realizar esta accion mientras se muestran los datos inactivos");
        } else {
            int i = TablaMedidas.getSelectedRow();
            if (evt.getClickCount() == 2) {
                if (i >= 0) {
                    Guardar_Medida.setEnabled(false);
                    Medida_cod.setText(String.valueOf(TablaMedidas.getValueAt(i, 0)));
                    Medida_cod.setEnabled(false);
                    Medida_nombre.setText(String.valueOf(TablaMedidas.getValueAt(i, 1)));

                } else {
                    JOptionPane.showMessageDialog(null, "No seleciono fila");
                }
            } else {
                Limpiar_medida();
            }
        }
    }//GEN-LAST:event_TablaMedidasMouseClicked

    private void Guardar_MedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Guardar_MedidaActionPerformed
        if ("".equals(Medida_nombre.getText())) {
            JOptionPane.showMessageDialog(null, "LLene el nombre");
        } else {
            PreparedStatement pstcat = null;
            String sqlcat = "INSERT INTO registromedidas (Medida_cod, medida_unidad, Medida_estado) VALUES (?, ?, 1)";
            int med_codi = Integer.parseInt(Medida_cod.getText());
            String med_nom = Medida_nombre.getText();

            Medidas newmarca = new Medidas(med_codi, med_nom);
            try {

                pstcat = cn.prepareStatement(sqlcat);
                pstcat.setInt(1, newmarca.getMedida_cod());
                pstcat.setString(2, newmarca.getMedida_nombre());

                pstcat.executeUpdate();
                Limpiar_medida();

                JOptionPane.showMessageDialog(null, "Marca Guardada");
                mostrardatosMedida("", 1);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al guardar" + e);
                Limpiar_medida();
                mostrardatosMedida("", 1);
            }
        }
    }//GEN-LAST:event_Guardar_MedidaActionPerformed

    private void Eliminar_medidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Eliminar_medidaActionPerformed
        int fila = TablaMedidas.getSelectedRow();
        String codm = "";
        //     String codpr = B_producto.getText();
        codm = TablaMedidas.getValueAt(fila, 0).toString();

        try {

            //            PreparedStatement pst = cn.prepareStatement("DELETE FROM registromarcas WHERE Marca_cod='" + codm + "'");
            PreparedStatement pst = cn.prepareStatement("UPDATE registromedidas SET medida_estado='2' WHERE Medida_cod='" + Medida_cod.getText() + "'");
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se Elimino Correctamete");
            Limpiar_medida();
            mostrardatosMedida("", 1);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "No seleciono fila" + e);
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Se Elimino");
        }
    }//GEN-LAST:event_Eliminar_medidaActionPerformed

    private void Modificar_medidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Modificar_medidaActionPerformed
        try {
            PreparedStatement pst = cn.prepareStatement("UPDATE registromedidas SET medida_unidad='" + Medida_nombre.getText() + "' WHERE Medida_cod='" + Medida_cod.getText() + "'");
            pst.executeUpdate();
            Guardar_Medida.setEnabled(true);
            mostrardatosMedida("", 1);
            Limpiar_medida();
            JOptionPane.showMessageDialog(null, "Actualizado");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Limpiar_medida();
        }
    }//GEN-LAST:event_Modificar_medidaActionPerformed

    private void Mostrar_medidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mostrar_medidasActionPerformed
        if (med_Inactivos.isSelected()) {
            mostrardatosMedida("", 2);

        } else {
            mostrardatosMedida("", 1);
        }
    }//GEN-LAST:event_Mostrar_medidasActionPerformed

    private void med_InactivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_med_InactivosMouseClicked

    }//GEN-LAST:event_med_InactivosMouseClicked

    private void med_InactivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_med_InactivosActionPerformed

        if (med_Inactivos.isSelected()) {
            mostrardatosMedida("", 2);
            Guardar_Medida.setEnabled(false);
            Modificar_medida.setEnabled(false);
            Eliminar_medida.setEnabled(false);
            Limpiar_medida();
        } else {
            mostrardatosMedida("", 1);
            Guardar_Medida.setEnabled(true);

            Limpiar_medida();
        }
    }//GEN-LAST:event_med_InactivosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Limpiar_medida();
        Guardar_Medida.setEnabled(true);
        Modificar_medida.setEnabled(false);
        Eliminar_medida.setEnabled(false);

    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Imedida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Imedida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Imedida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Imedida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Imedida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Eliminar_medida;
    private javax.swing.JButton Guardar_Medida;
    private javax.swing.JPanel Marcas;
    private javax.swing.JTextField Medida_buscar;
    private javax.swing.JTextField Medida_cod;
    private javax.swing.JTextField Medida_nombre;
    private javax.swing.JButton Modificar_medida;
    private javax.swing.JButton Mostrar_medidas;
    private javax.swing.JTable TablaMedidas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JCheckBox med_Inactivos;
    // End of variables declaration//GEN-END:variables
}
