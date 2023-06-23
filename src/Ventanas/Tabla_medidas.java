
package Ventanas;

import static Ventanas.RegistrarProductos.Pro_medida;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Tabla_medidas extends javax.swing.JFrame {


    public Tabla_medidas() {
        initComponents();
        mostrardatosMedida("");
        TraerDatos();
        setLocationRelativeTo(null);
    }

         
     void mostrardatosMedida(String codm) {

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

        if (codm.equals("")) {

            sqlm = "SELECT * FROM registromedidas WHERE medida_estado = '1'";

        } else {

            sqlm = "SELECT * FROM registromedidas WHERE Medida_nombre like '%" + codm + "%' and medida_estado = '1'";
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
        Medida_buscar = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        TablaMedidas = new javax.swing.JTable();
        Añadir = new javax.swing.JButton();
        Mostrar_medidas = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        Marcas.setBackground(new java.awt.Color(204, 255, 255));
        Marcas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel24.setText("Registro de Medidas");
        Marcas.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, 30));

        Medida_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Medida_buscarKeyReleased(evt);
            }
        });
        Marcas.add(Medida_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 150, 30));

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

        Marcas.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 320, 230));

        Añadir.setText("Añadir");
        Añadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AñadirActionPerformed(evt);
            }
        });
        Marcas.add(Añadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, -1, -1));

        Mostrar_medidas.setText("Mostrar");
        Mostrar_medidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mostrar_medidasActionPerformed(evt);
            }
        });
        Marcas.add(Mostrar_medidas, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, -1, 30));

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Marcas.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Marcas, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 403, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Marcas, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Medida_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Medida_buscarKeyReleased
        //mostrardatosMedida(Medida_buscar.getText());
        
        switch (evt.getKeyCode()) {

            case KeyEvent.VK_BACK_SPACE:
                break;

            case KeyEvent.VK_ENTER:
                mostrardatosMedida(Medida_buscar.getText());
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
    }//GEN-LAST:event_Medida_buscarKeyReleased

    private void TablaMedidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMedidasMouseClicked

        int i = TablaMedidas.getSelectedRow();
        if (evt.getClickCount() == 2) {
            if (i >= 0) {

                Pro_medida.setText(TablaMedidas.getValueAt(i,0).toString());
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "No seleciono fila");
            }}else{

            }
    }//GEN-LAST:event_TablaMedidasMouseClicked

    private void AñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AñadirActionPerformed
        Imarcas c = new Imarcas();
        c.setLocation(450, 200);
        c.setVisible(true);
    }//GEN-LAST:event_AñadirActionPerformed

    private void Mostrar_medidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mostrar_medidasActionPerformed
        mostrardatosMedida("");
    }//GEN-LAST:event_Mostrar_medidasActionPerformed

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
            java.util.logging.Logger.getLogger(Tabla_medidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tabla_medidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tabla_medidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tabla_medidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tabla_medidas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Añadir;
    private javax.swing.JPanel Marcas;
    private javax.swing.JTextField Medida_buscar;
    private javax.swing.JButton Mostrar_medidas;
    private javax.swing.JTable TablaMedidas;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JScrollPane jScrollPane6;
    // End of variables declaration//GEN-END:variables
    Conexion cc = new Conexion();
    Connection cn = cc.conectar();
}
