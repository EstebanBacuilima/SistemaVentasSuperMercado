package Ventanas;

import Reportes.Excel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ReportePedidos extends javax.swing.JFrame {
    
    Conexion cc = new Conexion();
    Connection cn = cc.conectar();
    
    
    public ReportePedidos() {
        initComponents();
        mostrarEnca(1);
        setLocationRelativeTo(null);
        CodPedido.setEditable(false);
        Seleccionar();
    }

    
    
    void mostrarEnca(int pedido_codigo) {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };
        
        modelo.addColumn("Código Pedido");
        modelo.addColumn("Código Contador");
        modelo.addColumn("RUC Proveedor");
        modelo.addColumn("Fecha emisión");
        modelo.addColumn("Fecha entrega");
        modelo.addColumn("Total General");
        modelo.addColumn("Situación");

        jTable1.setModel(modelo);
        // Acumular la sentencia
        String sql = "";

        if (pedido_codigo > 0) {

            sql = "SELECT * FROM pedido";

        } else {
            
        }
        
        String[] datos = new String[7];

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


                //System.out.println(rs);
                modelo.addRow(datos);
            }
            jTable1.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }
    }
    
    void mostrarEnca2(int pedido_codigo) {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };
        
        modelo.addColumn("Código Pedido");
        modelo.addColumn("Código Contador");
        modelo.addColumn("RUC Proveedor");
        modelo.addColumn("Fecha emisión");
        modelo.addColumn("Fecha entrega");
        modelo.addColumn("Total General");
        modelo.addColumn("Situación");

        jTable1.setModel(modelo);
        // Acumular la sentencia
        String sql = "";

        if (pedido_codigo > 0) {

            sql = "SELECT * FROM pedido WHERE pedido_fecha_emision BETWEEN '" + Fecha1.getDate() +"' AND '" + Fecha2.getDate() + "'";

        } else {
            
        }
        
        String[] datos = new String[7];

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


                //System.out.println(rs);
                modelo.addRow(datos);
            }
            jTable1.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }
    }
    
    void mostrarEnca3(int pedido_codigo) {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };
        
        modelo.addColumn("Código Pedido");
        modelo.addColumn("Código Contador");
        modelo.addColumn("RUC Proveedor");
        modelo.addColumn("Fecha emisión");
        modelo.addColumn("Fecha entrega");
        modelo.addColumn("Total General");
        modelo.addColumn("Situación");

        jTable1.setModel(modelo);
        // Acumular la sentencia
        String sql = "";

        if (pedido_codigo > 0) {

            sql = "SELECT * FROM pedido WHERE pedido_fecha_entrega BETWEEN '" + Fecha3.getDate() +"' AND '" + Fecha4.getDate() + "'";

        } else {
            
        }
        
        String[] datos = new String[7];

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


                //System.out.println(rs);
                modelo.addRow(datos);
            }
            jTable1.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }
    }
    
    void Seleccionar(){
        Botones.add(jRadioButton1);
        Botones.add(jRadioButton2);
        Botones.add(jRadioButton3);
        Botones.add(jRadioButton4);
        
//        if (jRadioButton1.isSelected()){
//            Fecha3.setEnabled(false);
//            Fecha4.setEnabled(false);
//            RucP.setEnabled(false);
//            jComboBox1.setEnabled(false);
//        }
//        if (jRadioButton2.isSelected()){
//            Fecha1.setEnabled(false);
//            Fecha2.setEnabled(false);
//            RucP.setEnabled(false);
//            jComboBox1.setEnabled(false);
//        }
//        if (jRadioButton3.isSelected()){
//            Fecha1.setEnabled(false);
//            Fecha2.setEnabled(false);
//            Fecha3.setEnabled(false);
//            Fecha4.setEnabled(false);
//            jComboBox1.setEnabled(false);
//        }
//        if (jRadioButton4.isSelected()){
//            Fecha1.setEnabled(false);
//            Fecha2.setEnabled(false);
//            Fecha3.setEnabled(false);
//            Fecha4.setEnabled(false);
//            RucP.setEnabled(false);
//        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Botones = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        CodPedido = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        RucP = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        Fecha1 = new com.toedter.calendar.JDateChooser();
        Fecha2 = new com.toedter.calendar.JDateChooser();
        Fecha3 = new com.toedter.calendar.JDateChooser();
        Fecha4 = new com.toedter.calendar.JDateChooser();
        jButton9 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CodPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodPedidoActionPerformed(evt);
            }
        });
        jPanel1.add(CodPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 210, 40, 30));

        jLabel13.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel13.setText("hasta");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 50, -1));

        jLabel14.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel14.setText("Código Pedido :");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, -1, 30));

        jButton8.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 16)); // NOI18N
        jButton8.setText("Buscar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, -1, 20));

        jLabel15.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 48)); // NOI18N
        jLabel15.setText("REPORTE DE PEDIDOS");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 450, 60));

        jLabel16.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel16.setText("BUSCAR POR :");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 150, -1));

        jLabel23.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel23.setText("hasta");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 50, -1));

        RucP.setEnabled(false);
        RucP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RucPKeyPressed(evt);
            }
        });
        jPanel1.add(RucP, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 110, 150, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Pendiente", "Recibido", "No Recibidos" }));
        jComboBox1.setEnabled(false);
        jComboBox1.setOpaque(false);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 150, 140, -1));

        jRadioButton1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jRadioButton1.setText("Fecha Emisión :");
        jRadioButton1.setOpaque(false);
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseClicked(evt);
            }
        });
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 20));

        jRadioButton2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jRadioButton2.setText("Fecha entrega :");
        jRadioButton2.setOpaque(false);
        jRadioButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton2MouseClicked(evt);
            }
        });
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, 20));

        jRadioButton3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jRadioButton3.setText("RUC Proveedor :");
        jRadioButton3.setOpaque(false);
        jRadioButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton3MouseClicked(evt);
            }
        });
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 110, -1, 20));

        jRadioButton4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jRadioButton4.setText("Situación :");
        jRadioButton4.setOpaque(false);
        jRadioButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton4MouseClicked(evt);
            }
        });
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, -1, 20));

        Fecha1.setDateFormatString("yyyy-MM-dd");
        Fecha1.setEnabled(false);
        Fecha1.setOpaque(false);
        jPanel1.add(Fecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 140, -1));

        Fecha2.setDateFormatString("yyyy-MM-dd");
        Fecha2.setEnabled(false);
        Fecha2.setOpaque(false);
        jPanel1.add(Fecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 140, -1));

        Fecha3.setDateFormatString("yyyy-MM-dd");
        Fecha3.setEnabled(false);
        Fecha3.setOpaque(false);
        jPanel1.add(Fecha3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 140, -1));

        Fecha4.setDateFormatString("yyyy-MM-dd");
        Fecha4.setEnabled(false);
        Fecha4.setOpaque(false);
        jPanel1.add(Fecha4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 140, -1));

        jButton9.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 16)); // NOI18N
        jButton9.setText("Buscar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 160, -1, 20));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#Pedido", "Código Contador", "RUC Proveedor", "Fecha Emisión", "Fecha Entrega", "Total General", "Situación"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(5);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 840, 340));

        jButton7.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 16)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonSalirCrud.png"))); // NOI18N
        jButton7.setToolTipText("Regresar");
        jButton7.setBorder(null);
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 40, -1));
        jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 1270, 20));
        jPanel1.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1270, 20));

        jButton1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/VerReportesEm.png"))); // NOI18N
        jButton1.setToolTipText("Reporte");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 510, 50, 60));

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel1.setText("Reportes");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 570, -1, -1));
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 620));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1068, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int fila = jTable1.getSelectedRow();

        if (evt.getClickCount() == 1) {

            if (fila >= 0) {

                CodPedido.setText(String.valueOf(jTable1.getValueAt(fila, 0)));


            } else {
                JOptionPane.showMessageDialog(null, "No seleciono fila");
            }

        } else {

            if (evt.getClickCount() == 2) {
                TablaPedidosDet tb = new TablaPedidosDet();
                tb.setVisible(true);
            } else {
                CodPedido.setText("");
            }
            CodPedido.setText("");
        }

    }//GEN-LAST:event_jTable1MouseClicked

    
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        VentanaContador vc = new VentanaContador();
        vc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        Date dat = new Date();//Instancia la fecha del sistema
            if (Fecha1.getDate().getTime() >= Fecha2.getDate().getTime() ) {//Compara si la fecha seleccionada es memor o igual a la fecha actual
                JOptionPane.showMessageDialog(this, "Fecha Menor a la de Emision");
            }else{
                mostrarEnca2(1);
            }
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        mostrarEnca3(1);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void RucPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RucPKeyPressed
         DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };
        
        modelo.addColumn("Código Pedido");
        modelo.addColumn("Código Contador");
        modelo.addColumn("RUC Proveedor");
        modelo.addColumn("Fecha emisión");
        modelo.addColumn("Fecha entrega");
        modelo.addColumn("Total General");
        modelo.addColumn("Situación");

        jTable1.setModel(modelo);
        // Acumular la sentencia
        String sql = "";
        sql = "SELECT * FROM pedido WHERE ruc_provee LIKE '%" + RucP.getText() + "%'";
        
        String[] datos = new String[7];

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


                //System.out.println(rs);
                modelo.addRow(datos);
            }
            jTable1.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }
    }//GEN-LAST:event_RucPKeyPressed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        DefaultTableModel modelo = new DefaultTableModel() {

            
            
            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };
        
        modelo.addColumn("Código Pedido");
        modelo.addColumn("Código Contador");
        modelo.addColumn("RUC Proveedor");
        modelo.addColumn("Fecha emisión");
        modelo.addColumn("Fecha entrega");
        modelo.addColumn("Total General");
        modelo.addColumn("Situación");

        jTable1.setModel(modelo);
        // Acumular la sentencia
        String sql = "";//Seleccione
       
        if (jComboBox1.getSelectedItem().equals("Pendiente")) {

            sql = "SELECT * FROM pedido WHERE situacion = 'Pendiente'";

        } else if(jComboBox1.getSelectedItem().equals("Recibido")){
            
            sql = "SELECT * FROM pedido WHERE situacion = 'Recibido'";
        
        } else if(jComboBox1.getSelectedItem().equals("Seleccione")){
            sql = "SELECT * FROM pedido";
            
        } else if(jComboBox1.getSelectedItem().equals("No Recibidos")){
            
            sql = "SELECT * FROM pedido WHERE situacion = 'Pendiente*'";
        }

        String[] datos = new String[7];

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


                //System.out.println(rs);
                modelo.addRow(datos);
            }
            jTable1.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseClicked
            Fecha1.setEnabled(true);
            Fecha2.setEnabled(true);
            Fecha3.setEnabled(false);
            Fecha4.setEnabled(false);
            RucP.setEnabled(false);
            jComboBox1.setEnabled(false);
//        }
    }//GEN-LAST:event_jRadioButton1MouseClicked

    private void jRadioButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton2MouseClicked
            Fecha1.setEnabled(false);
            Fecha2.setEnabled(false);
            Fecha3.setEnabled(true);
            Fecha4.setEnabled(true);
            RucP.setEnabled(false);
            jComboBox1.setEnabled(false);
    }//GEN-LAST:event_jRadioButton2MouseClicked

    private void jRadioButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton3MouseClicked
            Fecha1.setEnabled(false);
            Fecha2.setEnabled(false);
            Fecha3.setEnabled(false);
            Fecha4.setEnabled(false);
            RucP.setEnabled(true);
            jComboBox1.setEnabled(false);
    }//GEN-LAST:event_jRadioButton3MouseClicked

    private void jRadioButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton4MouseClicked
            Fecha1.setEnabled(false);
            Fecha2.setEnabled(false);
            Fecha3.setEnabled(false);
            Fecha4.setEnabled(false);
            RucP.setEnabled(false);
            jComboBox1.setEnabled(true);
    }//GEN-LAST:event_jRadioButton4MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Excel.reportePedidos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void CodPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodPedidoActionPerformed

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
            java.util.logging.Logger.getLogger(ReportePedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportePedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportePedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportePedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportePedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Botones;
    public static javax.swing.JTextField CodPedido;
    private com.toedter.calendar.JDateChooser Fecha1;
    private com.toedter.calendar.JDateChooser Fecha2;
    private com.toedter.calendar.JDateChooser Fecha3;
    private com.toedter.calendar.JDateChooser Fecha4;
    public static javax.swing.JTextField RucP;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
