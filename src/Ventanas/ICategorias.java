package Ventanas;

import static Ventanas.RegistrarProductos.Pro_cat;
import djmeb.Categorias;
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

public class ICategorias extends javax.swing.JFrame {

    public ICategorias() {
        initComponents();
        setLocationRelativeTo(null);
        mostrardatosCateg("", 1);
        TraerDatos();
        Cat_cod.setText(String.valueOf(codIncrement_categ()));
        Cat_cod.setEditable(false);
        Modificar_categoria.setEnabled(false);
        Eliminar_categoria.setEnabled(false);
    }

    public int codIncrement_categ() {

        int serie = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = cc.conectar();
        try {
            ps = cn.prepareStatement("SELECT MAX(cat_cod) FROM registrocategorias");
            rs = ps.executeQuery();
            while (rs.next()) {
                serie = rs.getInt(1) + 1;
            }
        } catch (Exception e) {
            System.out.println("C4 Error" + e.getMessage());
        }
        return serie;
    }

/////////////////////////////////////////////////////////////////////////////
    // CREAR ARRAY LISTA ALMACENAR 
    ArrayList name = new ArrayList();
    String datos;

    private void TraerDatos() {

        // SENTECIA SELCIONAR LA TABLA
        String sql = "SELECT * FROM registrocategorias WHERE cat_estado = '1'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                // Traer el campo especificado de la tabla de la Base
                datos = rs.getString("Cat_Nombre");
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

            Cat_buscar.setText(complete);
            Cat_buscar.setCaretPosition(last);
            Cat_buscar.moveCaretPosition(start);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Categoria = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        Cat_nombre = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        Cat_buscar = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaCateg = new javax.swing.JTable();
        Crear_categoria = new javax.swing.JButton();
        Eliminar_categoria = new javax.swing.JButton();
        Modificar_categoria = new javax.swing.JButton();
        Cat_cod = new javax.swing.JTextField();
        Mostrar_categorias = new javax.swing.JButton();
        inactivos = new javax.swing.JCheckBox();
        jLabel24 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Categoria.setBackground(new java.awt.Color(153, 255, 255));
        Categoria.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel21.setText("Registro de Categorias");
        Categoria.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, -1, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel22.setText("Buscar nombre:");
        Categoria.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, 30));
        Categoria.add(Cat_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 170, 25));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel23.setText("Codigo de Categoria:");
        Categoria.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, 20));

        Cat_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Cat_buscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Cat_buscarKeyReleased(evt);
            }
        });
        Categoria.add(Cat_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 232, 120, 30));

        TablaCateg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablaCateg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaCategMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TablaCateg);

        Categoria.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 470, 180));

        Crear_categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Gurdar 40 x 40.png"))); // NOI18N
        Crear_categoria.setToolTipText("Guardar");
        Crear_categoria.setBorder(null);
        Crear_categoria.setBorderPainted(false);
        Crear_categoria.setContentAreaFilled(false);
        Crear_categoria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Crear_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Crear_categoriaActionPerformed(evt);
            }
        });
        Categoria.add(Crear_categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 50, -1));

        Eliminar_categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Eliminar 40 x 40.png"))); // NOI18N
        Eliminar_categoria.setToolTipText("Eliminar");
        Eliminar_categoria.setBorder(null);
        Eliminar_categoria.setBorderPainted(false);
        Eliminar_categoria.setContentAreaFilled(false);
        Eliminar_categoria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Eliminar_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Eliminar_categoriaActionPerformed(evt);
            }
        });
        Categoria.add(Eliminar_categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 60, -1));

        Modificar_categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Modificar 40 x 40.png"))); // NOI18N
        Modificar_categoria.setToolTipText("Modificar");
        Modificar_categoria.setBorder(null);
        Modificar_categoria.setBorderPainted(false);
        Modificar_categoria.setContentAreaFilled(false);
        Modificar_categoria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Modificar_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Modificar_categoriaActionPerformed(evt);
            }
        });
        Categoria.add(Modificar_categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 40, 40));
        Categoria.add(Cat_cod, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 50, 25));

        Mostrar_categorias.setBackground(new java.awt.Color(102, 204, 255));
        Mostrar_categorias.setFont(new java.awt.Font("Agency FB", 1, 16)); // NOI18N
        Mostrar_categorias.setText("Mostrar");
        Mostrar_categorias.setBorderPainted(false);
        Mostrar_categorias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Mostrar_categorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mostrar_categoriasActionPerformed(evt);
            }
        });
        Categoria.add(Mostrar_categorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, 80, 30));

        inactivos.setBackground(new java.awt.Color(153, 255, 255));
        inactivos.setText("INACTIVOS ");
        inactivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inactivosMouseClicked(evt);
            }
        });
        inactivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inactivosActionPerformed(evt);
            }
        });
        Categoria.add(inactivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 450, -1, 40));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel24.setText("Nombre de Categoria:");
        Categoria.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonSalirCrud.png"))); // NOI18N
        jButton1.setToolTipText("volver");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Categoria.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 40, 40));

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
        Categoria.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Guardar");
        Categoria.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, -1, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Modificar");
        jLabel2.setToolTipText("");
        Categoria.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 60, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Eliminar");
        Categoria.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, 50, 20));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Lupa 23 x 23.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        Categoria.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 50, 30));

        getContentPane().add(Categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TablaCategMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaCategMouseClicked
        if (inactivos.isSelected()) {
            JOptionPane.showMessageDialog(null, "no se puede realizar la accion con datos inacctivos");
        } else {

            int i = TablaCateg.getSelectedRow();

            if (evt.getClickCount() == 2) {

                if (i >= 0) {
                    Crear_categoria.setEnabled(false);
                    Cat_cod.setText(String.valueOf(TablaCateg.getValueAt(i, 0)));
                    Cat_cod.setEnabled(false);
                    Cat_nombre.setText(String.valueOf(TablaCateg.getValueAt(i, 1)));
                    Modificar_categoria.setEnabled(true);
                    Eliminar_categoria.setEnabled(true);

                } else {
                    JOptionPane.showMessageDialog(null, "No seleciono fila");
                }
            } else {
                Limpiar_categoria();
            }
        }
    }//GEN-LAST:event_TablaCategMouseClicked

    private void Crear_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Crear_categoriaActionPerformed
        PreparedStatement pstcat = null;
        String sqlcat = "INSERT INTO registrocategorias (Cat_cod, Cat_Nombre, cat_estado) VALUES (?, ?, 1)";
        int cat_codi = Integer.parseInt(Cat_cod.getText());
        String cat_nom = Cat_nombre.getText();

        Categorias newcategoria = new Categorias(cat_codi, cat_nom);
        try {

            pstcat = cn.prepareStatement(sqlcat);
            pstcat.setInt(1, newcategoria.getCat_cod());
            pstcat.setString(2, newcategoria.getCat_nombre());

            pstcat.executeUpdate();
            Limpiar_categoria();
            JOptionPane.showMessageDialog(null, "Categoria Guardada");
            mostrardatosCateg("", 1);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar" + e);
            Limpiar_categoria();
        }

    }//GEN-LAST:event_Crear_categoriaActionPerformed

    void mostrardatosCateg(String codc, int estado) {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");

        TablaCateg.setModel(modelo);

        String sqlc = "";
        if (estado == 1) {
            if (codc.equals("")) {

                sqlc = "SELECT * FROM registrocategorias WHERE cat_estado = '1'";

            } else {

                sqlc = "SELECT * FROM registrocategorias WHERE cat_nombre like '%" + codc + "%' and cat_estado = '1'";
            }
        } else if (estado == 2) {
            if (codc.equals("")) {

                sqlc = "SELECT * FROM registrocategorias";

            } else {

                sqlc = "SELECT * FROM registrocategorias WHERE cat_nombre like'%" + codc + "%'";
            }
        }
        String[] datos = new String[2];

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqlc);

            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);

                modelo.addRow(datos);
            }
            TablaCateg.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }

    public void Limpiar_categoria() {
        Cat_nombre.setText("");
        Cat_cod.setText("");
        Cat_cod.setText(String.valueOf(codIncrement_categ()));

    }
    private void Eliminar_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Eliminar_categoriaActionPerformed
        int fila = TablaCateg.getSelectedRow();
        String codc = "";
        codc = TablaCateg.getValueAt(fila, 0).toString();
        try {
            Crear_categoria.setEnabled(true);
            Modificar_categoria.setEnabled(false);
            Eliminar_categoria.setEnabled(false);
            PreparedStatement pst = cn.prepareStatement("UPDATE registrocategorias SET cat_estado='2' WHERE cat_cod='" + Cat_cod.getText() + "'");
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se Elimino Correctamete");
            Limpiar_categoria();
            mostrardatosCateg("", 1);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "No se puede eliminar el registro");
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_Eliminar_categoriaActionPerformed

    private void Modificar_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Modificar_categoriaActionPerformed
        try {
            Crear_categoria.setEnabled(true);
            Modificar_categoria.setEnabled(false);
            Eliminar_categoria.setEnabled(false);
            PreparedStatement pst = cn.prepareStatement("UPDATE registrocategorias SET cat_nombre='" + Cat_nombre.getText() + "' WHERE cat_cod='" + Cat_cod.getText() + "'");
            pst.executeUpdate();
            mostrardatosCateg("", 1);
            Limpiar_categoria();
            JOptionPane.showMessageDialog(null, "Actualizado");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Limpiar_categoria();
        }
    }//GEN-LAST:event_Modificar_categoriaActionPerformed

    private void Mostrar_categoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mostrar_categoriasActionPerformed
        if (inactivos.isSelected()) {
            mostrardatosCateg("", 2);

        } else {
            mostrardatosCateg("", 1);
        }
    }//GEN-LAST:event_Mostrar_categoriasActionPerformed

    private void inactivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inactivosMouseClicked

    }//GEN-LAST:event_inactivosMouseClicked

    private void inactivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inactivosActionPerformed

        if (inactivos.isSelected()) {
            mostrardatosCateg("", 2);
            Crear_categoria.setEnabled(false);
            Modificar_categoria.setEnabled(false);
            Eliminar_categoria.setEnabled(false);
            Limpiar_categoria();
        } else {
            mostrardatosCateg("", 1);
            Crear_categoria.setEnabled(true);

            Limpiar_categoria();
        }

    }//GEN-LAST:event_inactivosActionPerformed

    private void Cat_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Cat_buscarKeyReleased
//        if (inactivos.isSelected()) {
//            mostrardatosCateg(Cat_buscar.getText(), 2);
//
//        } else {
//            mostrardatosCateg(Cat_buscar.getText(), 1);
//        }

        if (Cat_buscar.getText().isEmpty()) {

            mostrardatosCateg("", 1);

        } else {

            switch (evt.getKeyCode()) {

                case KeyEvent.VK_BACK_SPACE:
                    break;

                case KeyEvent.VK_ENTER:
                    mostrardatosCateg(Cat_buscar.getText(), 1);
                    break;

                default:

                    EventQueue.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                            String txt = Cat_buscar.getText();
                            autoCompletar(txt);

                        }
                    });

            }
        }
    }//GEN-LAST:event_Cat_buscarKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Limpiar_categoria();
        Crear_categoria.setEnabled(true);
        Modificar_categoria.setEnabled(false);
        Eliminar_categoria.setEnabled(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Cat_buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Cat_buscarKeyPressed

//        if (Cat_buscar.getText().isEmpty()) {
//
//            mostrardatosCateg("", 1);
//            
//        } else {
//
//            switch (evt.getKeyCode()) {
//
//                case KeyEvent.VK_BACK_SPACE:
//                    break;
//
//                case KeyEvent.VK_ENTER:
//                    mostrardatosCateg(Cat_buscar.getText(), 1);
//                    break;
//
//                default:
//
//                    EventQueue.invokeLater(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            String txt = Cat_buscar.getText();
//                            autoCompletar(txt);
//
//                        }
//                    });
//
//            }
//        }

    }//GEN-LAST:event_Cat_buscarKeyPressed

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
            java.util.logging.Logger.getLogger(Categorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Categorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Categorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Categorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ICategorias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Cat_buscar;
    private javax.swing.JTextField Cat_cod;
    private javax.swing.JTextField Cat_nombre;
    private javax.swing.JPanel Categoria;
    private javax.swing.JButton Crear_categoria;
    private javax.swing.JButton Eliminar_categoria;
    private javax.swing.JButton Modificar_categoria;
    private javax.swing.JButton Mostrar_categorias;
    private javax.swing.JTable TablaCateg;
    private javax.swing.JCheckBox inactivos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane5;
    // End of variables declaration//GEN-END:variables

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();
}
