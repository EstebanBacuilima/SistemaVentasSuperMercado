package Ventanas;

import static Ventanas.Scanneear.Imagen;
import static Ventanas.Scanneear.Orig;
import static Ventanas.VentanaCajero.NombreFoto;
import static Ventanas.VentanaCajero.Pro_Cod;
import static Ventanas.VentanaCajero.Pro_Nombre;
import static Ventanas.VentanaCajero.Pro_precio;
import static Ventanas.VentanaCajero.Stock;
import static Ventanas.VentanaCajero.jTiva;
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
//import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
//import org.jdesktop.swingx.autocomplete.TextComponentAdaptor;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.SpinnerNumberModel;

public class productosbuscar extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    PreparedStatement ps = null;
    Statement st = null;
    ResultSet rs = null;
    int can;

    public productosbuscar() {
        initComponents();
        //mostrardatos("");
        mostrardatosPro("", 1);
        this.setLocationRelativeTo(null);
        TraerDatos();

    }

//    public static String FechaActual() {
//
//        Date fecha = new Date();
//        SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
//        return formatFecha.format(fecha);
//
//    }
//
//////////////// BOTON AGREGAR ///////////////////
//    String fecha = new SimpleDateFormat("yyyy/MM/dd").format("Jchoser.getTxt()");
//    String[] dateParts = fecha.split("/");
//    String año = dateParts[0],
//            mes = dateParts[1],
//            dia = dateParts[2];
//
//    String AñosActual = FechaActual();
//
//    int dias = Integer.parseInt(dia);
//    int mesese = Integer.parseInt(mes);
//    int años = Integer.parseInt(año);
//    
//    if ( años < 2021) {
//        
//    JOptionPane.showMessageDialog(this, "Año Menor al Actual");
//    }else{
//     if (mesese < 10) {
//              JOptionPane.showMessageDialog(this, "Mes Menor al Actual");
//        } else {
//            if (dias < 10) {
//
//                JOptionPane.showMessageDialog(this, "Dia Menor al Actual");
//            } else {
//
//                // GUARDAR
//            }
//        }
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buscarpro = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buscarpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarproActionPerformed(evt);
            }
        });
        buscarpro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscarproKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarproKeyReleased(evt);
            }
        });
        getContentPane().add(buscarpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 230, 40));

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel1.setText("Ingrese el codigo o el nombre");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 360, -1));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonSalir_1.png"))); // NOI18N
        jButton2.setToolTipText("Salir");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 60, 60));

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 750, 200));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 360));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void mostrardatosPro(String codp, int estado) {

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
        modelo.addColumn("Imagen");

        jTable1.setModel(modelo);

        // Acumular la sentencia
        String sql = "";
        if (estado == 1) {
            if (codp.equals("")) {

                sql = "SELECT * FROM registroproducto WHERE Pro_estado='1'";

            } else {

                sql = "SELECT * FROM registroproducto  WHERE  pro_cbarras  LIKE '%" + buscarpro.getText() + "%' "
                        + "OR pro_nombre    LIKE '%" + buscarpro.getText() + "%'";

            }
        } else if (estado == 2) {
            if (codp.equals("")) {

                sql = "SELECT * FROM registroproducto";

            } else {

                sql = "SELECT * FROM registroproducto WHERE Cod_Pro='" + codp + "' and Pro_estado='2'";
            }

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
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);
                datos[11] = rs.getString(13);

                //System.out.println(rs);
                modelo.addRow(datos);
            }
            jTable1.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }

    void mostrardatosProd(String codp) {

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
        modelo.addColumn("Imagen");

        jTable1.setModel(modelo);

        // Acumular la sentencia
        String sql = "";

        if (codp.equals("")) {

            sql = "SELECT * FROM registroproducto WHERE pro_nombre = '" + buscarpro.getText() + "' AND Pro_estado='1'";

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
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);
                datos[11] = rs.getString(13);

                //System.out.println(rs);
                modelo.addRow(datos);
            }
            jTable1.setModel(modelo);

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
        String sql = "SELECT * FROM registroproducto";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                // Traer el campo especificado de la tabla de la Base
                datos = rs.getString("pro_nombre");
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

            buscarpro.setText(complete);
            buscarpro.setCaretPosition(last);
            buscarpro.moveCaretPosition(start);

        }
    }
    
    
//    int fila = jTable1.getSelectedRow();
//
//        if (evt.getClickCount() == 2) {
//
//            if (evt.getClickCount() == 2) {
//             dispose();
//                if (fila >= 0) {
//
//                   Pro_Cod.setText(jTable1.getValueAt(fila, 0).toString());
//                    Pro_Cod.setEditable(false);
//                    Pro_Nombre.setText(jTable1.getValueAt(fila, 2).toString());
//                     Pro_precio.setText(jTable1.getValueAt(fila, 6).toString());
//                     Stock.setText(jTable1.getValueAt(fila, 7).toString());
//                   int m=Integer.parseInt( jTable1.getValueAt(fila, 7).toString());
//                     SpinnerNumberModel n=new SpinnerNumberModel();
//                    n.setMaximum(m);
//                     Pro_Cantidad.setModel(n);
//                }
//            }
//        }

    protected static String Imagen;
    protected static String Dest, Orig;

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int fila = jTable1.getSelectedRow();

        if (evt.getClickCount() == 2) {

            if (evt.getClickCount() == 2) {
                dispose();
                if (fila >= 0) {

                    Pro_Cod.setText(jTable1.getValueAt(fila, 0).toString());
                    Pro_Cod.setEditable(false);
                    Pro_Nombre.setText(jTable1.getValueAt(fila, 2).toString());
                    Pro_precio.setText(jTable1.getValueAt(fila, 6).toString());
                    Stock.setText(jTable1.getValueAt(fila, 7).toString());
                    int m=Integer.parseInt( jTable1.getValueAt(fila, 7).toString());
                    SpinnerNumberModel n=new SpinnerNumberModel();
                    n.setMaximum(m);
                    VentanaCajero.Pro_Cantidad.setModel(n);
                    jTiva.setText(jTable1.getValueAt(fila, 10).toString());
                    Imagen = (jTable1.getValueAt(fila, 11).toString());

                    Orig = "src/Imagenes_Productos/" + Imagen;
//                ImageIcon icon = new ImageIcon(Orig);
//                ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(FotoPerfil.getWidth(), FotoPerfil.getHeight(), Image.SCALE_DEFAULT));
//                FotoPerfil.setText(null);
//                FotoPerfil.setIcon(icono);

                    VentanaCajero.NombreFoto.setText(Imagen);

                    Orig = "src/Imagenes_Productos/" + VentanaCajero.NombreFoto.getText();
                    ImageIcon icon = new ImageIcon(Orig);
                    ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(VentanaCajero.FotoProducto.getWidth(), VentanaCajero.FotoProducto.getHeight(), Image.SCALE_DEFAULT));
                    VentanaCajero.FotoProducto.setText(null);
                    VentanaCajero.FotoProducto.setIcon(icono);

                }

            }
        }


    }//GEN-LAST:event_jTable1MouseClicked
    private void buscarproKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarproKeyPressed

        switch (evt.getKeyCode()) {

            case KeyEvent.VK_BACK_SPACE:
                break;

            case KeyEvent.VK_ENTER:
                mostrardatosProd(buscarpro.getText());
                break;

            default:

                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        String txt = buscarpro.getText();
                        autoCompletar(txt);

                    }
                });

        }


    }//GEN-LAST:event_buscarproKeyPressed

    private void buscarproKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarproKeyReleased

        if(buscarpro.equals("")){
            mostrardatosPro("", 1);
        }else{
            mostrardatosPro(String.valueOf(buscarpro.getText()),1);
        }
        

    }//GEN-LAST:event_buscarproKeyReleased

    private void buscarproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarproActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarproActionPerformed
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
            java.util.logging.Logger.getLogger(productosbuscar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(productosbuscar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(productosbuscar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(productosbuscar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new productosbuscar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscarpro;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();

}
