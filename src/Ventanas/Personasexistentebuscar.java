package Ventanas;

import static Ventanas.VentanaCajero.Cli_Apellido;
import static Ventanas.VentanaCajero.Cli_Cedula;
import static Ventanas.VentanaCajero.Cli_Correo;
import static Ventanas.VentanaCajero.Cli_Direccion;
import static Ventanas.VentanaCajero.Cli_Nombre;
import static Ventanas.VentanaCajero.cli_telefono;
import static Ventanas.VentanaCajero.id_cliente;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Personasexistentebuscar extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
 
    PreparedStatement ps = null;
    Statement st = null;
    ResultSet rs = null;

    public Personasexistentebuscar() {
        initComponents();
        mostrardatos("");
        setLocationRelativeTo(null);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cedula = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 80, 60));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Ingrese la cedula o nombre del cliente");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 460, 30));

        cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cedulaKeyPressed(evt);
            }
        });
        jPanel1.add(cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 250, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 690, 170));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
        void mostrardatos(String cedula) {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        modelo.addColumn("N");
        modelo.addColumn("CEDULA");
        modelo.addColumn("NOMBRE ");
        modelo.addColumn("APELLIDO");
        modelo.addColumn("Edad ");
        modelo.addColumn("CORREO");
        modelo.addColumn("DIRECCION");
        modelo.addColumn("Genero ");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("Fecha N ");

        jTable1.setModel(modelo);

        // Acumular la sentencia
        String sql = "";

        if (cedula.equals("")) {

            //  sql = "SELECT * FROM ";
            sql = "SELECT C.id_clientes, P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono,C.fecha_nac_cli  FROM persona P, clientes C WHERE P.persona_id = C.persona_id  AND C.estado_cliente = 'A'";

        } 

        String[] datos = new String[10];

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
                datos[9] = rs.getString(10);

                System.out.println(rs);
                modelo.addRow(datos);
            }

            jTable1.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }



    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed


    }//GEN-LAST:event_jTable1KeyPressed

    private void cedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaKeyPressed
        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        modelo.addColumn("N");
        modelo.addColumn("CEDULA");
        modelo.addColumn("NOMBRE ");
        modelo.addColumn("APELLIDO");
        modelo.addColumn("Edad ");
        modelo.addColumn("CORREO");
        modelo.addColumn("DIRECCION");
        modelo.addColumn("Genero ");
        modelo.addColumn("TELEFONO");
        jTable1.setModel(modelo);

        String sql = "";
         //  sql = "SELECT C.id_clientes, P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono,C.fecha_nac_cli  FROM persona P, clientes C WHERE P.persona_id = C.persona_id  AND C.estado_cliente = 'A'";
        sql = "SELECT * FROM persona WHERE  persona_cedula  LIKE '%" + cedula.getText() + "%' "
                + "OR persona_nombre   LIKE '%" + cedula.getText() + "%'";



        String[] datos = new String[10];

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

                System.out.println(rs);
                modelo.addRow(datos);
            }

            jTable1.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }
        
    }//GEN-LAST:event_cedulaKeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int fila = jTable1.getSelectedRow();

        if (evt.getClickCount() == 2) {

            if (evt.getClickCount() == 2) {
                dispose();

                if (fila >= 0) {
                    
                    id_cliente.setText(jTable1.getValueAt(fila, 0).toString());
                    id_cliente.setEditable(false);
                    Cli_Cedula.setText(jTable1.getValueAt(fila, 1).toString());
                    Cli_Cedula.setEditable(false);
                    Cli_Nombre.setText(jTable1.getValueAt(fila, 2).toString());
                    Cli_Nombre.setEditable(false);
                    Cli_Apellido.setText(jTable1.getValueAt(fila, 3).toString());
                    Cli_Apellido.setEditable(false);
                    Cli_Direccion.setText(jTable1.getValueAt(fila, 6).toString());
                    Cli_Direccion.setEditable(false);
                    Cli_Correo.setText(jTable1.getValueAt(fila, 5).toString());
                    Cli_Correo.setEditable(false);
                    cli_telefono.setText(jTable1.getValueAt(fila, 8).toString());
                    cli_telefono.setEditable(false);

                }

            }

        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Personasexistentebuscar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cedula;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    Conexion cc = new Conexion();
    Connection cn = cc.conectar();

}
