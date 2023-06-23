package Ventanas;

import Reportes.ReporProductos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class ReporteProductos extends javax.swing.JFrame {

    public ReporteProductos() {
        initComponents();
        mostrarproduc();
        sacarcategoria(cbocategoria);
        sacarmarca(cbomarca);
        AutoCompleteDecorator.decorate(cbocategoria);
        AutoCompleteDecorator.decorate(cbomarca);
        setLocationRelativeTo(null);
    }

    public void mostrarproduc() {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };
        modelo.addColumn("Cod.Barras");
        modelo.addColumn("Nombre");
        modelo.addColumn("Categoria");
        modelo.addColumn("Marca");
        modelo.addColumn("Medida");
        modelo.addColumn("Precio");
        modelo.addColumn("Cant.Existente");
        modelo.addColumn("Cant.Mínima");
        modelo.addColumn("Cant.Máxima");
        modelo.addColumn("IVA");
        tablaproduc.setModel(modelo);

        // Acumular la sentencia
        String sql = "";

        sql = "SELECT RP.pro_cbarras,RP.pro_nombre,RC.cat_nombre, RM.marca_nombre,RP.pro_medida,RP.pro_precio,RP.pro_cant_exi,RP.pro_cant_min,RP.pro_cant_max,RP.pro_iva "
                + "FROM registroproducto RP,registrocategorias RC,registromarcas RM "
                + "WHERE RP.pro_categoria= RC.cat_cod AND RP.pro_marca = RM.marca_cod "
                + "ORDER BY RP.pro_cbarras";

        String[] datos = new String[10];

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

                System.out.println(rs);
                modelo.addRow(datos);
            }

            tablaproduc.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void sacarcategoria(JComboBox jComboBoxEmpleados) {
        //JComboBox cbocategoria,JComboBox marca
        // Creamos la conexion
        java.sql.Connection conectar = null;
        // Sentencia para crear la consulta

        //if (moscateg.isSelected()) {
        //jComboBoxEmpleados.addItem("");
        String sql = "SELECT cat_nombre FROM registrocategorias ORDER BY cat_nombre";

        try {

            conectar = cc.conectar();
            PreparedStatement pst = conectar.prepareStatement(sql);
            ResultSet result = pst.executeQuery();

            // Llenar el combobox
            //jComboBoxEmpleados.addItem("Seleccione una opcion");
            buscar();

            while (result.next()) {
                jComboBoxEmpleados.addItem(result.getString("cat_nombre"));
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "1 Error" + e);
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   

    public void sacarmarca(JComboBox cbomarca) {
        java.sql.Connection conectar = null;

        String sql2 = "SELECT marca_nombre FROM registromarcas ORDER BY marca_nombre";

        try {

            conectar = cc.conectar();
            PreparedStatement pst = conectar.prepareStatement(sql2);
            ResultSet result = pst.executeQuery();

            // Llenar el combobox
            //cbomarca.addItem("Seleccione una opcion");
            buscar();

            while (result.next()) {
                cbomarca.addItem(result.getString("marca_nombre"));
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "3 Error" + e);

        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

    public void buscar() {

        String categoria = cbocategoria.getSelectedItem().toString();
        String marca = cbomarca.getSelectedItem().toString();

        if (moscateg.isSelected()) {
            DefaultTableModel modelo = new DefaultTableModel();
            String[] Titulos = {"Cod.Barras", "Nombre", "Categoria", "Marca", "Medida", "Precio", "Cant.Existente", "Cant.Mínima", "Cant.Máxima", "IVA"};
            modelo.setColumnIdentifiers(Titulos);
            this.tablaproduc.setModel(modelo);
            try {

                //String ConsultaSQL = "SELECT C.id_clientes,P.persona_cedula, P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, C.fecha_nac_cli FROM persona P, clientes C  WHERE P.persona_id = C.persona_id AND persona_genero='" + genero + "'";
                String ConsultaSQL = "SELECT RP.pro_cbarras,RP.pro_nombre,RC.cat_nombre, RM.marca_nombre,RP.pro_medida,RP.pro_precio,RP.pro_cant_exi,RP.pro_cant_min,RP.pro_cant_max,RP.pro_iva "
                        + "FROM registroproducto RP,registrocategorias RC,registromarcas RM "
                        + "WHERE RP.pro_categoria= RC.cat_cod AND RP.pro_marca = RM.marca_cod AND cat_nombre='" + categoria + "'"
                        + "ORDER BY RP.pro_cbarras";
                String[] registros = new String[10];

                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(ConsultaSQL);
                while (rs.next()) {
                    registros[0] = rs.getString("pro_cbarras");
                    registros[1] = rs.getString("pro_nombre");
                    registros[2] = rs.getString("cat_nombre");
                    registros[3] = rs.getString("marca_nombre");
                    registros[4] = rs.getString("pro_medida");
                    registros[5] = rs.getString("pro_precio");
                    registros[6] = rs.getString("pro_cant_exi");
                    registros[7] = rs.getString("pro_cant_min");
                    registros[8] = rs.getString("pro_cant_max");
                    registros[9] = rs.getString("pro_iva");
                    modelo.addRow(registros);
                }
                tablaproduc.setModel(modelo);
            } catch (SQLException ex) {
                Logger.getLogger(ReportCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (mosmarca.isSelected()) {
            DefaultTableModel modelo = new DefaultTableModel();
            String[] Titulos = {"Cod.Barras", "Nombre", "Categoria", "Marca", "Medida", "Precio", "Cant.Existente", "Cant.Mínima", "Cant.Máxima", "IVA"};
            modelo.setColumnIdentifiers(Titulos);
            this.tablaproduc.setModel(modelo);
            try {
                String ConsultaSQL = "SELECT RP.pro_cbarras,RP.pro_nombre,RC.cat_nombre, RM.marca_nombre,RP.pro_medida,RP.pro_precio,RP.pro_cant_exi,RP.pro_cant_min,RP.pro_cant_max,RP.pro_iva "
                        + "FROM registroproducto RP,registrocategorias RC,registromarcas RM "
                        + "WHERE RP.pro_categoria= RC.cat_cod AND RP.pro_marca = RM.marca_cod AND marca_nombre='" + marca + "'"
                        + "ORDER BY RP.cod_pro";
                String[] registros = new String[10];

                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(ConsultaSQL);
                while (rs.next()) {
                    registros[0] = rs.getString("pro_cbarras");
                    registros[1] = rs.getString("pro_nombre");
                    registros[2] = rs.getString("cat_nombre");
                    registros[3] = rs.getString("marca_nombre");
                    registros[4] = rs.getString("pro_medida");
                    registros[5] = rs.getString("pro_precio");
                    registros[6] = rs.getString("pro_cant_exi");
                    registros[7] = rs.getString("pro_cant_min");
                    registros[8] = rs.getString("pro_cant_max");
                    registros[9] = rs.getString("pro_iva");
                    modelo.addRow(registros);
                }
                tablaproduc.setModel(modelo);
            } catch (SQLException ex) {
                Logger.getLogger(ReportCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    void abusc() {
        DefaultTableModel modelo = new DefaultTableModel();
        String[] Titulos = {"Codigo Barras", "Nombre", "Categoria", "Marca", "Medida", "Precio", "Cant.Existente", "Cant.Mínima", "Cant.Máxima", "IVA"};
        modelo.setColumnIdentifiers(Titulos);
        this.tablaproduc.setModel(modelo);
        try {
            String ConsultaSQL = "SELECT RP.pro_cbarras,RP.pro_nombre,RC.cat_nombre, RM.marca_nombre,RP.pro_medida,RP.pro_precio,RP.pro_cant_exi,RP.pro_cant_min,RP.pro_cant_max,RP.pro_iva "
                    + "FROM registroproducto RP,registrocategorias RC,registromarcas RM "
                    + "WHERE RP.pro_categoria= RC.cat_cod AND RP.pro_marca = RM.marca_cod AND (pro_nombre LIKE '%" + txtcod.getText() + "%'" + " OR pro_cbarras LIKE '%" + txtcod.getText() + "%')"
                    + "ORDER BY RP.pro_cbarras";
            String[] registros = new String[10];

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(ConsultaSQL);
            while (rs.next()) {
                registros[0] = rs.getString("pro_cbarras");
                registros[1] = rs.getString("pro_nombre");
                registros[2] = rs.getString("cat_nombre");
                registros[3] = rs.getString("marca_nombre");
                registros[4] = rs.getString("pro_medida");
                registros[5] = rs.getString("pro_precio");
                registros[6] = rs.getString("pro_cant_exi");
                registros[7] = rs.getString("pro_cant_min");
                registros[8] = rs.getString("pro_cant_max");
                registros[9] = rs.getString("pro_iva");
                modelo.addRow(registros);
            }
            tablaproduc.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(ReportCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        //}
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngprod = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaproduc = new javax.swing.JTable();
        moscod = new javax.swing.JRadioButton();
        moscateg = new javax.swing.JRadioButton();
        mosmarca = new javax.swing.JRadioButton();
        mostodo = new javax.swing.JRadioButton();
        cbomarca = new javax.swing.JComboBox<>();
        txtcod = new javax.swing.JTextField();
        cbocategoria = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaproduc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaproduc);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 860, 240));

        moscod.setBackground(new java.awt.Color(204, 255, 255));
        btngprod.add(moscod);
        moscod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moscod.setText("Nombre");
        moscod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                moscodMouseClicked(evt);
            }
        });
        getContentPane().add(moscod, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        moscateg.setBackground(new java.awt.Color(204, 255, 255));
        btngprod.add(moscateg);
        moscateg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moscateg.setText("Categoría");
        moscateg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                moscategMouseClicked(evt);
            }
        });
        getContentPane().add(moscateg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        mosmarca.setBackground(new java.awt.Color(204, 255, 255));
        btngprod.add(mosmarca);
        mosmarca.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mosmarca.setText("Marca");
        mosmarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mosmarcaMouseClicked(evt);
            }
        });
        getContentPane().add(mosmarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        mostodo.setBackground(new java.awt.Color(204, 255, 255));
        btngprod.add(mostodo);
        mostodo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mostodo.setText("Mostrar todos");
        mostodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostodoMouseClicked(evt);
            }
        });
        getContentPane().add(mostodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        cbomarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        cbomarca.setEnabled(false);
        cbomarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbomarcaActionPerformed(evt);
            }
        });
        getContentPane().add(cbomarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 199, -1));

        txtcod.setEnabled(false);
        txtcod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcodKeyReleased(evt);
            }
        });
        getContentPane().add(txtcod, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 199, -1));

        cbocategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        cbocategoria.setEnabled(false);
        cbocategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbocategoriaActionPerformed(evt);
            }
        });
        getContentPane().add(cbocategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 200, 25));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GenerReporteCompleto.png"))); // NOI18N
        jButton1.setToolTipText("Reporte General");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 70, 70));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GenerReportCatego.png"))); // NOI18N
        jButton2.setToolTipText("Reporte por Categoría");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 440, 70, 70));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GenerReportMarcas.png"))); // NOI18N
        jButton3.setToolTipText("Reporte por Marca");
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 440, 70, 70));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonSalirCrud.png"))); // NOI18N
        jButton4.setToolTipText("Regresar");
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 40, 40));

        jLabel2.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel2.setText("Reporte por Marca");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 510, -1, -1));

        jLabel3.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel3.setText("Reporte por Categoría");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 510, -1, -1));

        jLabel4.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel4.setText("Reporte General");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 510, -1, -1));

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 64)); // NOI18N
        jLabel1.setText("REPORTE PRODUCTOS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 480, 80));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPriPOO(1).png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 210, 70));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mostodoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mostodoMouseClicked
        if (mostodo.isSelected()) {
            mostrarproduc();
            txtcod.setEnabled(false);
            cbocategoria.setEnabled(false);
            cbomarca.setEnabled(false);
            jButton1.setEnabled(true);
            jButton2.setEnabled(false);
            jButton3.setEnabled(false);
        }
    }//GEN-LAST:event_mostodoMouseClicked

    private void moscodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moscodMouseClicked
        if (moscod.isSelected()) {

            txtcod.setEnabled(true);
            cbocategoria.setEnabled(false);
            cbomarca.setEnabled(false);
            jButton1.setEnabled(false);
            jButton2.setEnabled(false);
            jButton3.setEnabled(false);
        }
    }//GEN-LAST:event_moscodMouseClicked

    private void moscategMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moscategMouseClicked
        if (moscateg.isSelected()) {
            mostrarproduc();
            txtcod.setEnabled(false);
            cbocategoria.setEnabled(true);
            cbomarca.setEnabled(false);
            jButton1.setEnabled(false);
            jButton2.setEnabled(true);
            jButton3.setEnabled(false);
        }
    }//GEN-LAST:event_moscategMouseClicked

    private void mosmarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mosmarcaMouseClicked
        if (mosmarca.isSelected()) {
            mostrarproduc();
            txtcod.setEnabled(false);
            cbocategoria.setEnabled(false);
            cbomarca.setEnabled(true);
            jButton1.setEnabled(false);
            jButton2.setEnabled(false);
            jButton3.setEnabled(true);
        }
    }//GEN-LAST:event_mosmarcaMouseClicked

    private void cbomarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbomarcaActionPerformed
        buscar();
    }//GEN-LAST:event_cbomarcaActionPerformed

    private void cbocategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbocategoriaActionPerformed
        buscar();
    }//GEN-LAST:event_cbocategoriaActionPerformed

    private void txtcodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodKeyReleased
        if (txtcod.equals("")) {
            mostrarproduc();
        } else {
            abusc();
        }
    }//GEN-LAST:event_txtcodKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ReporProductos.reportepro();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String cate = cbocategoria.getSelectedItem().toString();
        ReporProductos.reportecate(cate);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String marca = cbomarca.getSelectedItem().toString();
        ReporProductos.reportemarca(marca);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       VentanaContador vc = new VentanaContador();
        vc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(ReporteProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReporteProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btngprod;
    private javax.swing.JComboBox<String> cbocategoria;
    private javax.swing.JComboBox<String> cbomarca;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton moscateg;
    private javax.swing.JRadioButton moscod;
    private javax.swing.JRadioButton mosmarca;
    private javax.swing.JRadioButton mostodo;
    private javax.swing.JTable tablaproduc;
    private javax.swing.JTextField txtcod;
    // End of variables declaration//GEN-END:variables

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();
}
