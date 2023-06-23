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


public class Tabla_categorias extends javax.swing.JFrame {

    public Tabla_categorias() {
        initComponents();
        mostrardatosCateg("");
        TraerDatos();
        setLocationRelativeTo(null);
   
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
        Cat_buscar = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaCateg = new javax.swing.JTable();
        Mostrar_categorias = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Categoria.setBackground(new java.awt.Color(204, 255, 255));
        Categoria.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel21.setText("Registro de Categorias");
        Categoria.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, 50));

        Cat_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cat_buscarActionPerformed(evt);
            }
        });
        Cat_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Cat_buscarKeyReleased(evt);
            }
        });
        Categoria.add(Cat_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 210, 30));

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

        Categoria.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 300, 260));

        Mostrar_categorias.setText("Mostrar");
        Mostrar_categorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mostrar_categoriasActionPerformed(evt);
            }
        });
        Categoria.add(Mostrar_categorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 80, 30));

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Categoria.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, -1, 30));

        jButton2.setText("Añadir ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Categoria.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, -1, 30));

        getContentPane().add(Categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TablaCategMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaCategMouseClicked
        int i = TablaCateg.getSelectedRow();
       
        if (evt.getClickCount() == 2) {
            
            if (i >= 0) {
                Pro_cat.setText(TablaCateg.getValueAt(i,0).toString());
                this.dispose();

            } else {
                JOptionPane.showMessageDialog(null, "No seleciono fila");
            }}else{
            
            }
    }//GEN-LAST:event_TablaCategMouseClicked

      void mostrardatosCateg(String codc) {

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

        if (codc.equals("")) {

            sqlc = "SELECT * FROM registrocategorias Where cat_estado = 1";

        } else {

            sqlc = "SELECT * FROM registrocategorias WHERE cat_nombre Like '%" + codc + "%' and cat_estado = '1'";
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
  
    private void Mostrar_categoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mostrar_categoriasActionPerformed
        mostrardatosCateg("");
    }//GEN-LAST:event_Mostrar_categoriasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Cat_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Cat_buscarKeyReleased
//        mostrardatosCateg(Cat_buscar.getText());
        if (Cat_buscar.getText().isEmpty()) {

            mostrardatosCateg(Cat_buscar.getText());
            
        } else {

            switch (evt.getKeyCode()) {

                case KeyEvent.VK_BACK_SPACE:
                    break;

                case KeyEvent.VK_ENTER:
                    mostrardatosCateg(Cat_buscar.getText());
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        ICategorias c = new ICategorias();
        c.setLocation(450, 200);
        c.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Cat_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cat_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cat_buscarActionPerformed

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
                new Tabla_categorias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Cat_buscar;
    private javax.swing.JPanel Categoria;
    private javax.swing.JButton Mostrar_categorias;
    private javax.swing.JTable TablaCateg;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JScrollPane jScrollPane5;
    // End of variables declaration//GEN-END:variables
          
    Conexion cc = new Conexion();
    Connection cn = cc.conectar();
}
