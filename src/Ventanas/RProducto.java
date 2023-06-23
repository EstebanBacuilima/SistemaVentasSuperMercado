package Ventanas;
import static Ventanas.RegistroPedidos.Cod_pro;
import static Ventanas.RegistroPedidos.Ivas;
import static Ventanas.RegistroPedidos.RUC_prov;
import static Ventanas.RegistroPedidos.nombre_pro;
import static Ventanas.RegistroPedidos.precio_pro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RProducto extends javax.swing.JFrame {

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();
    
    PreparedStatement ps = null;
    Statement st = null;
    ResultSet rs = null;
    
    public RProducto() {
        initComponents();
        //mostrardatosPro("");
        setLocationRelativeTo(null);
        jTextField1.setEditable(false);
        
        jTextField1.setText(RUC_prov.getText());
        //String ruc1 = jTextField1.getText();
        
        String ruc = jTextField1.getText();
        mostrardatosPro(ruc);
    }

    void mostrardatosPro(String ruc) {
        
        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        //DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Codigo Barras");
        modelo.addColumn("Nombre");
        modelo.addColumn("Categoria");
        modelo.addColumn("Marca");
        modelo.addColumn("Medida");
        modelo.addColumn("Precio");
        modelo.addColumn("Cant. existente ");
        modelo.addColumn("Cant. minima ");
        modelo.addColumn("Cant. maxima ");
        modelo.addColumn("Iva");
        modelo.addColumn("RUC");

        Table.setModel(modelo);

        // Acumular la sentencia
        String sql = "";
//        if (estado == 1) {
           //if (codp.equals("")) {

                sql = "SELECT * FROM registroproducto WHERE pro_estado= '1' AND ruc_provedor= '"+ ruc +"'";

            //} //else {

//                sql = "SELECT * FROM registroproducto WHERE pro_nombre LIKE '%" + codp + "%' ";
            //}
//        } else if (estado == 2) {
//            
//            if (codp.equals("")) {
//
//                sql = "SELECT * FROM registroproducto AND ruc_provedor='+"+ ruc+"'";
//
//            } else {
//
//                sql = "SELECT * FROM registroproducto WHERE cod_pro='" + codp + "' and pro_estado='2'";
//            }
//
//        }

        String[] datos = new String[13];

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
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);
                datos[11] = rs.getString(12);

                //System.out.println(rs);
                modelo.addRow(datos);
            }
            Table.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        Cod = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Ingresar Código o Nombre del Producto");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 350, 30));

        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 580, 180));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
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
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 80, 60));

        Cod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodActionPerformed(evt);
            }
        });
        Cod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CodKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CodKeyTyped(evt);
            }
        });
        jPanel1.add(Cod, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 250, 30));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 120, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("PROVEEDOR");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 300));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodActionPerformed

    private void CodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CodKeyPressed
       //mostrardatosPro(Cod.getText(),1);
    }//GEN-LAST:event_CodKeyPressed

    private void CodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CodKeyTyped

    }//GEN-LAST:event_CodKeyTyped

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
        

        int fila = Table.getSelectedRow();

        if (evt.getClickCount() == 2) {

            if (evt.getClickCount() == 2) {
                dispose();
                if (fila >= 0) {

                    Cod_pro.setText(Table.getValueAt(fila, 0).toString());
                    Cod_pro.setEditable(false);
                    nombre_pro.setText(Table.getValueAt(fila, 2).toString());
                    nombre_pro.setEditable(false);
                    precio_pro.setText(Table.getValueAt(fila, 6).toString());
                    precio_pro.setEditable(false);
                    Ivas.setText(Table.getValueAt(fila, 10).toString());
                    Ivas.setEditable(false);
                }

            }
        }
    }//GEN-LAST:event_TableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RProducto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Cod;
    private javax.swing.JTable Table;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
