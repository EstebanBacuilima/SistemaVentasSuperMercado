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

public class Tabla_marcas extends javax.swing.JFrame {

    public Tabla_marcas() {
        initComponents();
        mostrardatosMarca("");
        setLocationRelativeTo(null);
        TraerDatos();
    }
    
     
     void mostrardatosMarca(String codm) {

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

        if (codm.equals("")) {

            sqlm = "SELECT * FROM registromarcas WHERE marca_estado = '1'";

        } else {

            sqlm = "SELECT * FROM registromarcas WHERE Marca_nombre='" + codm + "' and marca_estado = '1'";
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
        Marca_buscar = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        TablaMarca = new javax.swing.JTable();
        Añadir = new javax.swing.JButton();
        Mostrar_marcas = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Marcas.setBackground(new java.awt.Color(204, 255, 255));
        Marcas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel24.setText("Registro de Marcas");
        Marcas.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, 50));

        Marca_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Marca_buscarKeyReleased(evt);
            }
        });
        Marcas.add(Marca_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 200, 30));

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

        Marcas.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 290, 200));

        Añadir.setText("Añadir");
        Añadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AñadirActionPerformed(evt);
            }
        });
        Marcas.add(Añadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 330, -1, -1));

        Mostrar_marcas.setText("Mostrar");
        Mostrar_marcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mostrar_marcasActionPerformed(evt);
            }
        });
        Marcas.add(Mostrar_marcas, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, -1, 30));

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Marcas.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        getContentPane().add(Marcas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TablaMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMarcaMouseClicked

        int i = TablaMarca.getSelectedRow();
        if (evt.getClickCount() == 2) {
            if (i >= 0) {
                
                Pro_mar.setText(TablaMarca.getValueAt(i,0).toString());
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "No seleciono fila");
            }}else{

            }

    }//GEN-LAST:event_TablaMarcaMouseClicked

    private void AñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AñadirActionPerformed
       Imarcas c = new Imarcas();
        c.setLocation(450, 200);
        c.setVisible(true);
    }//GEN-LAST:event_AñadirActionPerformed

    private void Mostrar_marcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mostrar_marcasActionPerformed
        mostrardatosMarca("");
    }//GEN-LAST:event_Mostrar_marcasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Marca_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Marca_buscarKeyReleased
//        mostrardatosMarca(Marca_buscar.getText());

if (Marca_buscar.getText().isEmpty()) {

            mostrardatosMarca(Marca_buscar.getText());
             
        } else {

            switch (evt.getKeyCode()) {

                case KeyEvent.VK_BACK_SPACE:
                    break;

                case KeyEvent.VK_ENTER:
                    mostrardatosMarca(Marca_buscar.getText());
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
            java.util.logging.Logger.getLogger(Tabla_marcas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tabla_marcas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tabla_marcas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tabla_marcas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tabla_marcas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Añadir;
    private javax.swing.JTextField Marca_buscar;
    private javax.swing.JPanel Marcas;
    private javax.swing.JButton Mostrar_marcas;
    private javax.swing.JTable TablaMarca;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JScrollPane jScrollPane6;
    // End of variables declaration//GEN-END:variables
    Conexion cc = new Conexion();
    Connection cn = cc.conectar();

}
