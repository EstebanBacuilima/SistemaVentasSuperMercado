package Ventanas;

import static djmeb.validaciones.ValidarLongitud;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TablaContador extends javax.swing.JFrame {
    
    Conexion cc = new Conexion();
    Connection cn = cc.conectar();

    public TablaContador() {
        initComponents();
        setLocationRelativeTo(null);
        mostrardatos("");
         
    }

   void mostrardatos(String cedula) {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        modelo.addColumn("Codigo");
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Edad");
        modelo.addColumn("Correo");
        modelo.addColumn("Direccion");
        modelo.addColumn("Genero");
        modelo.addColumn("Telofono");
        modelo.addColumn("Bono ");
        modelo.addColumn("Salario");
        modelo.addColumn("Horario");
        jTable1.setModel(modelo);

        // Acumular la sentencia
        String sql = "";

        if (cedula.equals("")) {


            sql = "SELECT C.contador_codigo ,P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, E.empleado_horario, E.empleado_salario, C.contador_bono FROM persona P, empleado E, registrocontador C WHERE P.persona_id = E.persona_id AND  E.empleado_codigo = C.empleado_codigo AND C.contador_estado = 'A'";

        } else {

            sql = "SELECT C.contador_codigo ,P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, E.empleado_horario, E.empleado_salario, C.contador_bono FROM persona P, empleado E, registrocontador C WHERE P.persona_cedula LIKE '%" + cedula + "%'" + "AND P.persona_id = E.persona_id AND  E.empleado_codigo = C.empleado_codigo AND C.contador_estado = 'A'";
        }

        String[] datos = new String[12];

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                String edad = rs.getString(5);
                datos[4] = edad;
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                String bono = rs.getString(12);
                datos[9] = bono;
                String salario = rs.getString(11);
                datos[10] = salario;
                datos[11] = rs.getString(10);

                modelo.addRow(datos);

            }

            jTable1.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }
   
   public void ValidarExiste(String cedula) {

        try {
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM persona WHERE persona_cedula = '" + cedula + "'");
            if (rs.next()) {
                //Existe
                JOptionPane.showMessageDialog(null, "Existe");
                mostrardatos(cedula);
            } else {
                //No Existe
                JOptionPane.showMessageDialog(null, "No Existe");
                mostrardatos("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentanaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jLabel3 = new javax.swing.JLabel();
        CedulaBuscar = new javax.swing.JTextField();
        BuscarBUtton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();

        jMenuItem1.setText("Selecionar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 70)); // NOI18N
        jLabel3.setText("Registro Contadores");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, 90));

        CedulaBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CedulaBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CedulaBuscarKeyTyped(evt);
            }
        });
        getContentPane().add(CedulaBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 240, 30));

        BuscarBUtton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconoBuscar.png"))); // NOI18N
        BuscarBUtton.setToolTipText("Buscar");
        BuscarBUtton.setBorderPainted(false);
        BuscarBUtton.setContentAreaFilled(false);
        BuscarBUtton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BuscarBUtton.setDefaultCapable(false);
        BuscarBUtton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarBUttonActionPerformed(evt);
            }
        });
        getContentPane().add(BuscarBUtton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 60, 50));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 960, 270));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CedulaBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaBuscarKeyReleased

        if (CedulaBuscar.getText().isEmpty()) {
            mostrardatos("");
        }else {
            mostrardatos(CedulaBuscar.getText());
        }
        
    }//GEN-LAST:event_CedulaBuscarKeyReleased

    private void CedulaBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaBuscarKeyTyped
        // Capturar tecla enter

        ValidarLongitud(CedulaBuscar.getText(), 20, evt);
        char cTeclaPresionada = evt.getKeyChar();

        // Click para aceptar el enter
        if (cTeclaPresionada == KeyEvent.VK_ENTER) {

            // Da clicl al boton al precionar ENTER
            BuscarBUtton.doClick();
            mostrardatos(CedulaBuscar.getText());
            ValidarExiste(CedulaBuscar.getText());
        }
    }//GEN-LAST:event_CedulaBuscarKeyTyped

    private void BuscarBUttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarBUttonActionPerformed
        mostrardatos(CedulaBuscar.getText());
        ValidarExiste(CedulaBuscar.getText());
    }//GEN-LAST:event_BuscarBUttonActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       int fila = jTable1.getSelectedRow();

        if (evt.getClickCount() == 2) {

            if (fila >= 0) {

                String Cedula = (jTable1.getValueAt(fila, 1).toString());
                VentanaUsuarios.CedulaBusc.setText(Cedula);
                String codigo = (jTable1.getValueAt(fila, 0).toString());
                VentanaUsuarios.CodEmpleado.setText(codigo);
                
                this.dispose();

            } else {
                JOptionPane.showMessageDialog(null, "No seleciono fila");
            }

        } else {

            VentanaUsuarios.CedulaBusc.setText("");
            CedulaBuscar.setText("");
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       int fila = jTable1.getSelectedRow();

        if (fila >= 0) {

            if (fila >= 0) {

                String Cedula = (jTable1.getValueAt(fila, 1).toString());
                VentanaUsuarios.CedulaBusc.setText(Cedula);
                String codigo = (jTable1.getValueAt(fila, 0).toString());
                VentanaUsuarios.CodEmpleado.setText(codigo);
                this.dispose();

            } else {
                JOptionPane.showMessageDialog(null, "No seleciono fila");
            }

        } else {

            JOptionPane.showMessageDialog(null, "No seleciono fila");
            VentanaUsuarios.CedulaBusc.setText("");
            CedulaBuscar.setText("");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TablaContador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BuscarBUtton;
    private javax.swing.JTextField CedulaBuscar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
