package Ventanas;

import static Ventanas.RegistrarProductos.Pro_mar;
import djmeb.Marcas;
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

public class Imarcas extends javax.swing.JFrame {

    /**
     * Creates new form Imarcas
     */
    public Imarcas() {
        initComponents();
        setLocationRelativeTo(null);
        mostrardatosMarca("", 1);
        TraerDatos();
        Marca_cod.setText(String.valueOf(codIncrement_marca()));
        Marca_cod.setEditable(false);
        Eliminar_marca.setEnabled(false);
        Modificar_marca.setEnabled(false);

    }

    public int codIncrement_marca() {

        int serie = 1;

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = cc.conectar();

        try {

            ps = cn.prepareStatement("SELECT MAX(Marca_cod) FROM registromarcas");
            rs = ps.executeQuery();

            while (rs.next()) {
                serie = rs.getInt(1) + 1;
                //CodEmpleado.setText(serie);
            }

        } catch (Exception e) {
            System.out.println("M4 Error" + e.getMessage());
        }

        return serie;

    }

    void mostrardatosMarca(String codm, int estado) {

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
        TablaMarca.setModel(modelo);

        // Acumular la sentencia
        String sqlm = "";
        if (estado == 1) {
            if (codm.equals("")) {

                sqlm = "SELECT * FROM registromarcas WHERE marca_estado='1'";

            } else {

                sqlm = "SELECT * FROM registromarcas WHERE Marca_nombre like '%" + codm + "%' and marca_estado = '1'";
            }
        } else if (estado == 2) {
            if (codm.equals("")) {

                sqlm = "SELECT * FROM registromarcas WHERE marca_estado='2'";

            } else {

                sqlm = "SELECT * FROM registromarcas WHERE Marca_nombre like'%" + codm + "%' and marca_estado = '2'";
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
            TablaMarca.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }

    public void Limpiar_marca() {
        Marca_nombre.setText("");
        Marca_cod.setText("");
        Marca_cod.setText(String.valueOf(codIncrement_marca()));
    }

/////////////////////////////////////////////////////////////////////////////
    // CREAR ARRAY LISTA ALMACENAR 
    ArrayList name = new ArrayList();
    String datos;

    private void TraerDatos() {

        // SENTECIA SELCIONAR LA TABLA
        String sql = "SELECT * FROM registromarcas WHERE marca_estado='1'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                // Traer el campo especificado de la tabla de la Base
                datos = rs.getString("Marca_Nombre");
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

            Marca_buscar.setText(complete);
            Marca_buscar.setCaretPosition(last);
            Marca_buscar.moveCaretPosition(start);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Marcas = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        Marca_nombre = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        Marca_buscar = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        TablaMarca = new javax.swing.JTable();
        Guardar_Marca = new javax.swing.JButton();
        Eliminar_marca = new javax.swing.JButton();
        Modificar_marca = new javax.swing.JButton();
        Marca_cod = new javax.swing.JTextField();
        Mostrar_marcas = new javax.swing.JButton();
        mar_inactivos = new javax.swing.JCheckBox();
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
        Marcas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel24.setText("Registro de Marcas");
        Marcas.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel25.setText("Nombre de Marca:");
        Marcas.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, 20));
        Marcas.add(Marca_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 180, 25));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel26.setText("Codigo de Marca:");
        Marcas.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        Marca_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Marca_buscarKeyReleased(evt);
            }
        });
        Marcas.add(Marca_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 150, 30));

        TablaMarca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablaMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaMarcaMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(TablaMarca);

        Marcas.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 400, 180));

        Guardar_Marca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Gurdar 40 x 40.png"))); // NOI18N
        Guardar_Marca.setToolTipText("Guardar");
        Guardar_Marca.setBorder(null);
        Guardar_Marca.setBorderPainted(false);
        Guardar_Marca.setContentAreaFilled(false);
        Guardar_Marca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Guardar_Marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Guardar_MarcaActionPerformed(evt);
            }
        });
        Marcas.add(Guardar_Marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 40, 50));

        Eliminar_marca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Eliminar 40 x 40.png"))); // NOI18N
        Eliminar_marca.setToolTipText("Eliminar");
        Eliminar_marca.setBorder(null);
        Eliminar_marca.setBorderPainted(false);
        Eliminar_marca.setContentAreaFilled(false);
        Eliminar_marca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Eliminar_marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Eliminar_marcaActionPerformed(evt);
            }
        });
        Marcas.add(Eliminar_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 70, 50));

        Modificar_marca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Modificar 40 x 40.png"))); // NOI18N
        Modificar_marca.setToolTipText("Modificar");
        Modificar_marca.setBorder(null);
        Modificar_marca.setBorderPainted(false);
        Modificar_marca.setContentAreaFilled(false);
        Modificar_marca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Modificar_marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Modificar_marcaActionPerformed(evt);
            }
        });
        Marcas.add(Modificar_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 161, 60, 50));
        Marcas.add(Marca_cod, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 70, 25));

        Mostrar_marcas.setBackground(new java.awt.Color(102, 204, 255));
        Mostrar_marcas.setFont(new java.awt.Font("Agency FB", 1, 16)); // NOI18N
        Mostrar_marcas.setText("Mostrar");
        Mostrar_marcas.setBorderPainted(false);
        Mostrar_marcas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Mostrar_marcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mostrar_marcasActionPerformed(evt);
            }
        });
        Marcas.add(Mostrar_marcas, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, -1, 30));

        mar_inactivos.setBackground(new java.awt.Color(153, 255, 255));
        mar_inactivos.setText("INACTIVOS ");
        mar_inactivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mar_inactivosMouseClicked(evt);
            }
        });
        mar_inactivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mar_inactivosActionPerformed(evt);
            }
        });
        Marcas.add(mar_inactivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 470, -1, -1));

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
        Marcas.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, -1));

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LimpiarCajero.png"))); // NOI18N
        jButton2.setToolTipText("Limpiar");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Marcas.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Guardar");
        Marcas.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 210, 60, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Modificar");
        Marcas.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 60, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Eliminar");
        Marcas.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 60, 20));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Lupa 23 x 23.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        Marcas.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 50, 30));

        getContentPane().add(Marcas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TablaMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMarcaMouseClicked
        if (mar_inactivos.isSelected()) {
            JOptionPane.showMessageDialog(null, "No puede realizar esta accion mientras se muestran los datos inactivos");
        } else {
            int i = TablaMarca.getSelectedRow();
            if (evt.getClickCount() == 2) {
                if (i >= 0) {
                    Guardar_Marca.setEnabled(false);
                    Modificar_marca.setEnabled(true);
                    Eliminar_marca.setEnabled(true);
                    Marca_cod.setText(String.valueOf(TablaMarca.getValueAt(i, 0)));
                    Marca_cod.setEnabled(false);
                    Marca_nombre.setText(String.valueOf(TablaMarca.getValueAt(i, 1)));

                } else {
                    JOptionPane.showMessageDialog(null, "No seleciono fila");
                }
            } else {
                Limpiar_marca();
            }
        }
    }//GEN-LAST:event_TablaMarcaMouseClicked

    private void Guardar_MarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Guardar_MarcaActionPerformed
        if ("".equals(Marca_nombre.getText())) {
            JOptionPane.showMessageDialog(null, "LLene el nombre");
        } else {
            PreparedStatement pstcat = null;
            String sqlcat = "INSERT INTO registromarcas (Marca_cod, Marca_Nombre, Marca_estado) VALUES (?, ?, 1)";
            int mar_codi = Integer.parseInt(Marca_cod.getText());
            String mar_nom = Marca_nombre.getText();

            Marcas newmarca = new Marcas(mar_codi, mar_nom);
            try {

                pstcat = cn.prepareStatement(sqlcat);
                pstcat.setInt(1, newmarca.getMarca_cod());
                pstcat.setString(2, newmarca.getMarca_nombre());

                pstcat.executeUpdate();
                Limpiar_marca();

                JOptionPane.showMessageDialog(null, "Marca Guardada");
                mostrardatosMarca("", 1);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al guardar" + e);
                Limpiar_marca();
                mostrardatosMarca("", 1);
            }
        }
    }//GEN-LAST:event_Guardar_MarcaActionPerformed

    private void Eliminar_marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Eliminar_marcaActionPerformed
        int fila = TablaMarca.getSelectedRow();
        String codm = "";
        //     String codpr = B_producto.getText();
        codm = TablaMarca.getValueAt(fila, 0).toString();

        try {

//            PreparedStatement pst = cn.prepareStatement("DELETE FROM registromarcas WHERE Marca_cod='" + codm + "'");
            PreparedStatement pst = cn.prepareStatement("UPDATE registromarcas SET Marca_estado='2' WHERE Marca_cod='" + Marca_cod.getText() + "'");
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se Elimino Correctamete");
            Modificar_marca.setEnabled(false);
            Eliminar_marca.setEnabled(false);
            Guardar_Marca.setEnabled(true);
            Limpiar_marca();
            mostrardatosMarca("", 1);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "No seleciono fila" + e);
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Se Elimino");
        }
    }//GEN-LAST:event_Eliminar_marcaActionPerformed

    private void Modificar_marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Modificar_marcaActionPerformed
        try {
            PreparedStatement pst = cn.prepareStatement("UPDATE registromarcas SET Marca_Nombre='" + Marca_nombre.getText() + "' WHERE Marca_cod='" + Marca_cod.getText() + "'");
            pst.executeUpdate();
            Guardar_Marca.setEnabled(true);
            Modificar_marca.setEnabled(false);
            Eliminar_marca.setEnabled(false);
            mostrardatosMarca("", 1);
            Limpiar_marca();
            JOptionPane.showMessageDialog(null, "Actualizado");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Limpiar_marca();
        }
    }//GEN-LAST:event_Modificar_marcaActionPerformed

    private void Mostrar_marcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mostrar_marcasActionPerformed
        if (mar_inactivos.isSelected()) {
            mostrardatosMarca("", 2);

        } else {
            mostrardatosMarca("", 1);
        }
    }//GEN-LAST:event_Mostrar_marcasActionPerformed

    private void mar_inactivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mar_inactivosMouseClicked

    }//GEN-LAST:event_mar_inactivosMouseClicked

    private void mar_inactivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mar_inactivosActionPerformed

        if (mar_inactivos.isSelected()) {
            mostrardatosMarca("", 2);
            Limpiar_marca();
            Guardar_Marca.setEnabled(false);
            Modificar_marca.setEnabled(false);
            Eliminar_marca.setEnabled(false);

        } else {
            mostrardatosMarca("", 1);
            Limpiar_marca();
            Guardar_Marca.setEnabled(true);

        }

    }//GEN-LAST:event_mar_inactivosActionPerformed

    private void Marca_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Marca_buscarKeyReleased
//        if (mar_inactivos.isSelected()) {
//            mostrardatosMarca(Marca_buscar.getText(), 2);
//
//        } else {
//            mostrardatosMarca(Marca_buscar.getText(), 1);
//        }

        if (Marca_buscar.getText().isEmpty()) {

            mostrardatosMarca(Marca_buscar.getText(), 1);
             
        } else {

            switch (evt.getKeyCode()) {

                case KeyEvent.VK_BACK_SPACE:
                    break;

                case KeyEvent.VK_ENTER:
                    mostrardatosMarca(Marca_buscar.getText(), 1);
                    break;

                default:

                    EventQueue.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                            String txt = Marca_buscar.getText();
                            autoCompletar(txt);

                        }
                    });

            }
        }


    }//GEN-LAST:event_Marca_buscarKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Limpiar_marca();
        Guardar_Marca.setEnabled(true);
        Modificar_marca.setEnabled(false);
        Eliminar_marca.setEnabled(false);
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
            java.util.logging.Logger.getLogger(Imarcas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Imarcas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Imarcas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Imarcas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Imarcas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Eliminar_marca;
    private javax.swing.JButton Guardar_Marca;
    private javax.swing.JTextField Marca_buscar;
    private javax.swing.JTextField Marca_cod;
    private javax.swing.JTextField Marca_nombre;
    private javax.swing.JPanel Marcas;
    private javax.swing.JButton Modificar_marca;
    private javax.swing.JButton Mostrar_marcas;
    private javax.swing.JTable TablaMarca;
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
    private javax.swing.JCheckBox mar_inactivos;
    // End of variables declaration//GEN-END:variables
    Conexion cc = new Conexion();
    Connection cn = cc.conectar();

}
