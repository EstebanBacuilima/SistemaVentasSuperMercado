package Ventanas;

import djmeb.Productos;
import djmeb.enca_venta;
import djmeb.deta_venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VentanaCajero extends javax.swing.JFrame {

    LocalDate fechahoy = LocalDate.now();
    DefaultTableModel model = new DefaultTableModel();
    Conexion cc = new Conexion();
    Connection cn = cc.conectar();
    int cant, r;
    int filaselc = -1;
    double pre;
    double pagart, tocp, tiva;
    deta_venta veta = new deta_venta();
    enca_venta enca = new enca_venta();
    double ivaww;

    public VentanaCajero() {

        initComponents();
        setLocationRelativeTo(null);
        fecha.setText(fechahoy + "");
        cod_cajero.setEditable(false);
        num_caja.setEditable(false);
        Cod_Venta.setEditable(false);
        Cod_Venta.setText(String.valueOf(codIncrement2()));
        Iva_total.setText("0");
        Sub_Total.setText("0");

        cod_cajero.setText(Login.CodigoEmpe.getText());
        num_caja.setText(Login.NumeroCaja.getText());
        Stock.setEditable(false);
        Pro_precio.setEditable(false);
        Pro_Cod.setEditable(false);
        Pro_Nombre.setEditable(false);
        jTiva.setEditable(false);
    }

    void agregarpro() {

        if (Pro_Cod.getText().isEmpty() || Pro_Nombre.getText().isEmpty() || Pro_precio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No a seleccionado ningun producto");
        } else {
            model = (DefaultTableModel) tablaingrp.getModel();
            int cod = Integer.parseInt(Pro_Cod.getText());
            String nom = Pro_Nombre.getText();
            cant = Integer.valueOf(Pro_Cantidad.getValue().toString());
            pre = Double.parseDouble(Pro_precio.getText());
            tocp = cant * pre;
            String ivas = jTiva.getText();
            if ("Si".equals(ivas)) {

                ivaww = 0.12;

            } else if ("No".equals(ivas)) {
                ivaww = 0;
            }
            ArrayList listap = new ArrayList();
            listap.add(cod);
            listap.add(nom);
            listap.add(cant);
            listap.add(pre);
            listap.add(tocp);
            listap.add(ivaww);

            Object[] ob = new Object[6];
            ob[0] = listap.get(0);
            ob[1] = listap.get(1);
            ob[2] = listap.get(2);
            ob[3] = listap.get(3);
            ob[4] = listap.get(4);
            ob[5] = listap.get(5);

            model.addRow(ob);
            tablaingrp.setModel(model);
            calto();
            LimpiarPro();
        }
    }

    void calto() {

        DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
        separadoresPersonalizados.setDecimalSeparator('.');
        DecimalFormat formato1 = new DecimalFormat("#.00", separadoresPersonalizados);
        pagart = 0;
        tiva = 0;
        for (int i = 0; i < tablaingrp.getRowCount(); i++) {
            cant = Integer.parseInt(tablaingrp.getValueAt(i, 2).toString());
            pre = Double.parseDouble(tablaingrp.getValueAt(i, 3).toString());
            ivaww = Double.parseDouble(tablaingrp.getValueAt(i, 5).toString());
            pagart = pagart + (cant * pre);

            if (ivaww == 0.12) {
                tiva = tiva + ((cant * pre) * 0.12);
            } else if (ivaww == 0) {
                tiva = tiva;
            }
        }

        Sub_Total.setText(formato1.format(pagart));
        Iva_total.setText(formato1.format(tiva));
    }

    void caltotalconiva() {

        DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
        separadoresPersonalizados.setDecimalSeparator('.');
        DecimalFormat formato1 = new DecimalFormat("#.00", separadoresPersonalizados);
        double totaliva, subtotal, ttpro;
        totaliva = Double.parseDouble(Iva_total.getText());
        subtotal = Double.parseDouble(Sub_Total.getText());

        ttpro = totaliva + subtotal;
        Total_venta.setText(formato1.format(ttpro));

    }

    void LimpiarPro() {
        Pro_Cod.setText("");
        Pro_precio.setText("");
        Pro_Nombre.setText("");
        Stock.setText("");
        Pro_Cantidad.setValue(1);
        jTiva.setText("");
        //FotoProducto.setText("");
        FotoProducto.setIcon(null);
    }

    void Limpiarcliente() {
        Cli_Cedula.setText("");
        Cli_Nombre.setText("");
        cli_telefono.setText("");
        Cli_Direccion.setText("");
        Cli_Correo.setText("");
        Cli_Apellido.setText("");
    }

    void Limpiarcliente2() {
        id_cliente.setText("");
        Cli_Cedula.setText("");
        Cli_Nombre.setText("");
        cli_telefono.setText("");
        Cli_Direccion.setText("");
        Cli_Correo.setText("");
        Cli_Apellido.setText("");
    }

    public void guadarencavent() {
        Date fecha3 = java.sql.Date.valueOf(fecha.getText());
        System.out.println(fecha3);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String fechan = formato.format(fecha3);
        System.out.println(fechan);
        String fec[] = fechan.split("/");
        LocalDate fechancompra = LocalDate.of(Integer.parseInt(fec[0]), Integer.parseInt(fec[1]), Integer.parseInt(fec[2]));
        System.out.println(fechancompra);
        int codventa = Integer.parseInt(Cod_Venta.getText());
        int id_clientee = Integer.parseInt(id_cliente.getText());
        int cod_caje = Integer.parseInt(cod_cajero.getText());
        String totalapagar = Total_venta.getText();
        double total = Double.parseDouble(totalapagar);
        enca_venta enca = new enca_venta(codventa, id_clientee, cod_caje, fechancompra, total);
        PreparedStatement pstp = null;
        String sql = "INSERT INTO enca_venta (cod_ven,id_clientes,cod_cajer,fecha,total) VALUES (?,?,?,?,?)";
        try {
            pstp = cn.prepareStatement(sql);
            pstp.setInt(1, enca.getCod_ven());
            pstp.setInt(2, enca.getId_cliente());
            pstp.setInt(3, enca.getCod_cajero());
            pstp.setDate(4, java.sql.Date.valueOf(fechancompra));
            pstp.setDouble(5, enca.getTotal());
            pstp.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se guardo");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No Se guardo" + e);
        }
    }

    public int guardarpd(deta_venta cen) {
        PreparedStatement pst = null;
        String sql = "INSERT INTO detalle_venta (cod_det_ven,cod_pro,cantidad_pro,cod_venta) VALUES (?,?,?,?)";
        try {
            pst = cn.prepareStatement(sql);
            pst.setInt(1, cen.getCod_de_ven());
            pst.setInt(2, cen.getCod_pro());
            pst.setInt(3, cen.getCantida());
            pst.setInt(4, cen.getCod_deven());
            r = pst.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    public void guadarvent() {
        for (int i = 0; i < tablaingrp.getRowCount(); i++) {

            int codpro = Integer.parseInt(tablaingrp.getValueAt(i, 0).toString());
            int cant = Integer.parseInt(tablaingrp.getValueAt(i, 2).toString());
            int coddetalle = codIncrement();
            int codventa = Integer.parseInt(Cod_Venta.getText());
            veta.setCantida(cant);
            veta.setCod_de_ven(coddetalle);
            veta.setCod_deven(codventa);
            veta.setCod_pro(codpro);
            r = guardarpd(veta);

        }
        if (r == 0) {
            JOptionPane.showMessageDialog(this, "No se a ingresado ningun producto a la venta");

        } else if (r == 1) {
            JOptionPane.showMessageDialog(this, " Productos ingresados a la venta");

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Cli_Correo = new javax.swing.JTextField();
        Cli_Nombre = new javax.swing.JTextField();
        Cli_Direccion = new javax.swing.JTextField();
        Cli_Apellido = new javax.swing.JTextField();
        Cli_Cedula = new javax.swing.JTextField();
        Pro_Cod = new javax.swing.JTextField();
        Pro_Nombre = new javax.swing.JTextField();
        Pro_precio = new javax.swing.JTextField();
        Pro_Cantidad = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaingrp = new javax.swing.JTable();
        Sub_Total = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        Cod_Venta = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        Iva_total = new javax.swing.JTextField();
        Total_venta = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cli_telefono = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        consumi = new javax.swing.JCheckBox();
        fecha = new javax.swing.JLabel();
        id_cliente = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        num_caja = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        Stock = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        NombreFoto = new javax.swing.JTextField();
        FotoProducto = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jTiva = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        cod_cajero = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel2.setText("Nombre");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 70, 20));

        jLabel3.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel3.setText("Datos del Producto");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 170, 30));

        jLabel4.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel4.setText("Telefono");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 70, 20));

        jLabel5.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel5.setText("Correo");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 160, 80, 20));

        jLabel6.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel6.setText("Direccion");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 220, 80, 20));

        jLabel8.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel8.setText("Iva");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 70, 20));

        jLabel9.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel9.setText("Codigo ");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, 20));

        jLabel10.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel10.setText("Total a pagar");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 700, 110, 30));

        jLabel12.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel12.setText("Codigo ");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 70, 30));

        jLabel14.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel14.setText("Cedula");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 70, 20));
        getContentPane().add(Cli_Correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 160, 150, 30));
        getContentPane().add(Cli_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 150, 30));
        getContentPane().add(Cli_Direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 220, 150, 30));
        getContentPane().add(Cli_Apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 150, 30));
        getContentPane().add(Cli_Cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 150, 30));
        getContentPane().add(Pro_Cod, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 160, 30));
        getContentPane().add(Pro_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, 160, 30));

        Pro_precio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pro_precioActionPerformed(evt);
            }
        });
        getContentPane().add(Pro_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 440, 160, 30));

        Pro_Cantidad.setModel(new javax.swing.SpinnerNumberModel(1, null, 99, 1));
        getContentPane().add(Pro_Cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 530, 60, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LupaCaj.png"))); // NOI18N
        jButton1.setToolTipText("Buscar");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setDefaultCapable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 60, 40));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LupaCaj.png"))); // NOI18N
        jButton2.setToolTipText("Buscar");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setDefaultCapable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 50, 40));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LimpiarCajero.png"))); // NOI18N
        jButton3.setToolTipText("Limpiar");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 200, 40, 40));

        tablaingrp = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;

            }

        };
        tablaingrp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Cantidad", "Precio Unit", "Precio Total", "Iva"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaingrp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaingrpMouseClicked(evt);
            }
        });
        tablaingrp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaingrpKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaingrp);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, 710, 260));

        Sub_Total.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Sub_TotalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Sub_TotalKeyTyped(evt);
            }
        });
        getContentPane().add(Sub_Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 600, 120, 30));

        jLabel15.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel15.setText("Datos personales del cliente");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, 220, 30));

        Cod_Venta.setBackground(new java.awt.Color(230, 230, 230));
        Cod_Venta.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Cod_Venta.setBorder(null);
        getContentPane().add(Cod_Venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 10, 60, 30));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1200, 10));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 1210, 10));

        Iva_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Iva_totalActionPerformed(evt);
            }
        });
        Iva_total.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Iva_totalKeyTyped(evt);
            }
        });
        getContentPane().add(Iva_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 650, 120, 30));

        Total_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Total_ventaActionPerformed(evt);
            }
        });
        Total_venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Total_ventaKeyTyped(evt);
            }
        });
        getContentPane().add(Total_venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 700, 120, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/CalculadoraCaj.png"))); // NOI18N
        jButton4.setToolTipText("Calcular");
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setDefaultCapable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 620, 80, 70));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LimpiarCajero.png"))); // NOI18N
        jButton5.setToolTipText("Limpiar");
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setDefaultCapable(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 530, 40, 40));

        jLabel16.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel16.setText("Nombre");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 70, 20));

        jLabel17.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel17.setText("Imprimir");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 700, 70, 30));

        jLabel18.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel18.setText("IVA");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 650, 40, 20));

        jLabel20.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel20.setText("Calcular");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 700, -1, 20));

        cli_telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cli_telefonoActionPerformed(evt);
            }
        });
        getContentPane().add(cli_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, 150, 30));

        jLabel21.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel21.setText("Apellido");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 70, 20));

        jLabel22.setFont(new java.awt.Font("Agency FB", 1, 22)); // NOI18N
        jLabel22.setText("Registrar");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 230, -1, 30));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/RegistrClient.png"))); // NOI18N
        jButton7.setToolTipText("Registra");
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 100, 150, 130));

        jLabel24.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel24.setText("Añadir");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 700, -1, -1));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPriPOO(1).png"))); // NOI18N
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 80));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/EliminarProCaj.png"))); // NOI18N
        jButton8.setToolTipText("Eliminar");
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.setDefaultCapable(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 640, 60, 70));

        jLabel26.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel26.setText("Limpiar");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 570, -1, 20));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonModificarcajero.png"))); // NOI18N
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.setDefaultCapable(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 639, 90, 60));

        jLabel27.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel27.setText("Eliminar");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 700, -1, -1));

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ImprimirFacT.png"))); // NOI18N
        jButton10.setToolTipText("Generar");
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 620, 130, 90));

        consumi.setBackground(new java.awt.Color(230, 230, 230));
        consumi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consumi.setText("Consumidor final");
        consumi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                consumiMouseClicked(evt);
            }
        });
        consumi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consumiActionPerformed(evt);
            }
        });
        getContentPane().add(consumi, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 160, -1, -1));
        getContentPane().add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 50, 100, 20));

        id_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_clienteActionPerformed(evt);
            }
        });
        getContentPane().add(id_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 150, 30));

        jLabel28.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel28.setText("Stock");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 80, 30));

        num_caja.setBackground(new java.awt.Color(230, 230, 230));
        num_caja.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        num_caja.setBorder(null);
        num_caja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num_cajaActionPerformed(evt);
            }
        });
        getContentPane().add(num_caja, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 50, 30));

        jLabel29.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel29.setText("Codigo ");
        getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 70, 30));

        jLabel30.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel30.setText("Modificar");
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 700, -1, -1));

        jLabel31.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel31.setText("Fecha:");
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 50, 70, 20));

        jLabel32.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel32.setText("# CAJA");
        getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 50, 20));
        getContentPane().add(Stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 490, 160, 30));

        jLabel33.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel33.setText("Precio");
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 80, 20));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, 10, 480));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 430, 10));
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 570, 770, 10));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 570, 10, 180));

        jLabel19.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel19.setText("Sub Total");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 600, 70, 20));

        jLabel23.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel23.setText("Limpiar");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 220, -1, 60));

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconoScanner.png"))); // NOI18N
        jButton11.setToolTipText("Scanear");
        jButton11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jButton11.setBorderPainted(false);
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 320, 90, 60));
        getContentPane().add(NombreFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, 40, 30));
        getContentPane().add(FotoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, 130, 130));

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 90, 10, 180));
        getContentPane().add(jTiva, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 570, 80, -1));

        jLabel11.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel11.setText("Cantidad");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 70, 20));

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonSalir_1.png"))); // NOI18N
        jButton12.setToolTipText("Salir");
        jButton12.setBorder(null);
        jButton12.setBorderPainted(false);
        jButton12.setContentAreaFilled(false);
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 0, 90, 70));

        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel13.setText("Codigo Cajero");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 120, 20));

        cod_cajero.setBackground(new java.awt.Color(230, 230, 230));
        cod_cajero.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cod_cajero.setBorder(null);
        jPanel1.add(cod_cajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 120, 30));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/AgregarVenCaj.png"))); // NOI18N
        jButton6.setToolTipText("Cargar");
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 630, 80, 70));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 750));

        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 400, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Limpiarcliente();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //calto();

        if (tablaingrp.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No Existen Datos", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            //JOptionPane.showMessageDialog(null, "Si hay Datos a Exportar", "Información", JOptionPane.WARNING_MESSAGE);
            caltotalconiva();
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        LimpiarPro();

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        if (Stock.getText().equals("0")) {
            JOptionPane.showMessageDialog(this, "No hay Stock");
        } else {
            agregarpro();
        }


    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        productosbuscar b = new productosbuscar();
        b.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cli_telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cli_telefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cli_telefonoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Personasexistentebuscar per = new Personasexistentebuscar();
        per.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        ingresodecliente cli = new ingresodecliente();
        cli.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void Iva_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Iva_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Iva_totalActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int fila = tablaingrp.getSelectedRow();

        if (fila >= 0) {

            model.removeRow(tablaingrp.getSelectedRow());
            calto();

        } else {

            JOptionPane.showMessageDialog(this, "No selecionon Fila");

        }
    }//GEN-LAST:event_jButton8ActionPerformed
    public int codIncrement2() {
        int serie = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = cc.conectar();

        try {

            ps = cn.prepareStatement("SELECT MAX(cod_venta) FROM detalle_venta");
            rs = ps.executeQuery();

            while (rs.next()) {
                serie = rs.getInt(1) + 1;
                //CodEmpleado.setText(serie);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return serie;
    }

    public int codIncrement() {
        int serie = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = cc.conectar();

        try {

            ps = cn.prepareStatement("SELECT MAX(cod_det_ven) FROM detalle_venta");
            rs = ps.executeQuery();

            while (rs.next()) {
                serie = rs.getInt(1) + 1;
                //CodEmpleado.setText(serie);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return serie;
    }

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        if (tablaingrp.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No Existen Datos", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            //JOptionPane.showMessageDialog(null, "Si hay Datos a Exportar", "Información", JOptionPane.WARNING_MESSAGE);
            if (Cod_Venta.getText().isEmpty() || cod_cajero.getText().isEmpty() || Cli_Nombre.getText().isEmpty() || Cli_Apellido.getText().isEmpty() || Cli_Correo.getText().isEmpty() || Cli_Cedula.getText().isEmpty() || Total_venta.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se puede generar la compra");
            } else {

                guadarencavent();
                guadarvent();
                actualizarstock();
                //cerrar tabla
                ventanafactura tabalIN = new ventanafactura();
                tabalIN.setVisible(true);
//            dispose();
                // ENVIAR DATOS A OTRO FRAME
                ventanafactura.CedulaFac.setText(Cli_Cedula.getText());
                ventanafactura.CedulaFac.setEditable(false);
                ventanafactura.NombreApellFac.setText(Cli_Nombre.getText());
                ventanafactura.NombreApellFac.setEditable(false);
                ventanafactura.apellifac.setText(Cli_Apellido.getText());
                ventanafactura.apellifac.setEditable(false);
                ventanafactura.CorreoFacCli.setText(Cli_Correo.getText());
                ventanafactura.CorreoFacCli.setEditable(false);
                ventanafactura.DirrecionFacCli.setText(Cli_Direccion.getText());
                ventanafactura.DirrecionFacCli.setEditable(false);
                ventanafactura.telefono.setText(cli_telefono.getText());
                ventanafactura.telefono.setEditable(false);
                ventanafactura.fechafac.setText(fechahoy + "");
                ventanafactura.fechafac.setEditable(false);
                ventanafactura.subtotal.setText(Sub_Total.getText());
                ventanafactura.subtotal.setEditable(false);
                ventanafactura.ivafacd.setText(Iva_total.getText());
                ventanafactura.codcli.setText(id_cliente.getText());
                ventanafactura.codcajero.setText(cod_cajero.getText());
                ventanafactura.numcaja.setText(num_caja.getText());
                ventanafactura.cod_venta.setText(Cod_Venta.getText());
                ventanafactura.totalfac.setText(Total_venta.getText());
                ventanafactura.totalfac.setEditable(false);

                for (int i = 0; i < tablaingrp.getRowCount(); i++) {
                    String datos[] = new String[6];
                    datos[0] = tablaingrp.getValueAt(i, 0).toString();
                    datos[1] = tablaingrp.getValueAt(i, 1).toString();
                    datos[2] = tablaingrp.getValueAt(i, 2).toString();
                    datos[3] = tablaingrp.getValueAt(i, 3).toString();
                    datos[4] = tablaingrp.getValueAt(i, 4).toString();
                    //datos[5] = jTable1.getValueAt(i, 5).toString();
                    ventanafactura.modelota.addRow(datos);
                }

                DefaultTableModel dm = (DefaultTableModel) tablaingrp.getModel();
                int rowCount = dm.getRowCount();
                //Remove rows one by one from the end of the table
                for (int i = rowCount - 1; i >= 0; i--) {
                    dm.removeRow(i);
                }

                LimpiarPro();
                Limpiarcliente();
                id_cliente.setText("");
                Sub_Total.setText("");
                Iva_total.setText("");
                Total_venta.setText("");
                Cod_Venta.setEditable(false);
                Cod_Venta.setText(String.valueOf(codIncrement2()));
            }
        }


    }//GEN-LAST:event_jButton10ActionPerformed
    int idp, can;

    public Productos listarcod(int codi) {

        Productos p = new Productos();
        PreparedStatement pst = null;
        String sql = "SELECT * FROM registroproducto WHERE cod_pro='" + codi + "' AND pro_estado='1'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                p.setCod_producto(rs.getInt(1));
                p.setCant_existente(rs.getInt(8));

            }

        } catch (Exception e) {
        }

        return p;
    }

    void actualizarstock() {
        for (int i = 0; i < tablaingrp.getRowCount(); i++) {
            Productos p2 = new Productos();
            idp = Integer.parseInt(tablaingrp.getValueAt(i, 0).toString());
            can = Integer.parseInt(tablaingrp.getValueAt(i, 2).toString());
            p2 = listarcod(idp);
            int proa = p2.getCant_existente() - can;
            actualizarstock(proa, idp);

        }

    }

    public int actualizarstock(int canti, int codpro) {
        PreparedStatement pst = null;
        String sql = "update  registroproducto set  pro_cant_exi=? where  cod_pro=? ";
        try {
            pst = cn.prepareStatement(sql);
            pst.setInt(1, canti);
            pst.setInt(2, codpro);
            pst.executeUpdate();
        } catch (Exception e) {
        }
        return r;

    }


    private void consumiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consumiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_consumiActionPerformed
    public void cosumidorfinal() {

        String sql = "SELECT C.id_clientes, P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono,C.fecha_nac_cli  FROM persona P, clientes C WHERE P.persona_id = '1'  AND C.estado_cliente = 'A'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                id_cliente.setText("1");
                Cli_Cedula.setText(rs.getString(2));
                Cli_Nombre.setText(rs.getString(3));
                Cli_Apellido.setText(rs.getString(4));
                Cli_Correo.setText(rs.getString(6));
                Cli_Direccion.setText(rs.getString(7));
                cli_telefono.setText(rs.getString(9));

            }

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }
    private void consumiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consumiMouseClicked

        if (consumi.isSelected()) {

            cosumidorfinal();

        } else {
            Limpiarcliente2();
        }
    }//GEN-LAST:event_consumiMouseClicked
    int filas;
    private void Total_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Total_ventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Total_ventaActionPerformed

    private void Pro_precioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pro_precioActionPerformed
        // TODO, c add your handling code here:
    }//GEN-LAST:event_Pro_precioActionPerformed
    private void tablaingrpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaingrpMouseClicked
        int fila = tablaingrp.getSelectedRow();
        if (evt.getClickCount() == 2) {
            if (fila >= 0) {
                Pro_Cod.setText(tablaingrp.getValueAt(fila, 0).toString());
                Pro_Cod.setEditable(false);
                Pro_Nombre.setText(tablaingrp.getValueAt(fila, 1).toString());
                Pro_Nombre.setEditable(false);
                String can = String.valueOf(tablaingrp.getValueAt(fila, 2));
                Pro_Cantidad.setValue(Integer.parseInt(can));
                Pro_precio.setText(tablaingrp.getValueAt(fila, 3).toString());

            } else {
                JOptionPane.showMessageDialog(null, "No seleciono fila");
            }

        } else {

        }

    }//GEN-LAST:event_tablaingrpMouseClicked
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        if (Pro_Cod.getText().isEmpty() || Pro_Nombre.getText().isEmpty() || Pro_precio.getText().isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "No a seleccionado ningun producto");
            
        } else {

            Object[] pro = new Object[5];
            double preci;
            double valor = Double.parseDouble(Pro_precio.getText());
            int catid = Integer.valueOf(Pro_Cantidad.getValue().toString());
            preci = valor * catid;

            pro[0] = Integer.parseInt(Pro_Cod.getText());
            pro[1] = Pro_Nombre.getText();
            pro[2] = Integer.parseInt(Pro_Cantidad.getValue().toString());
            pro[3] = Double.parseDouble(Pro_precio.getText());
            pro[4] = preci;
            for (int i = 0; i < 5; i++) {
                model.setValueAt(pro[i], tablaingrp.getSelectedRow(), i);
                calto();

            }
            LimpiarPro();
        }

    }//GEN-LAST:event_jButton9ActionPerformed

    private void tablaingrpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaingrpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaingrpKeyPressed

    private void num_cajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num_cajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_num_cajaActionPerformed

    private void id_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_clienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_clienteActionPerformed

    private void Sub_TotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Sub_TotalKeyTyped


    }//GEN-LAST:event_Sub_TotalKeyTyped

    private void Iva_totalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Iva_totalKeyTyped

    }//GEN-LAST:event_Iva_totalKeyTyped

    private void Total_ventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Total_ventaKeyTyped

    }//GEN-LAST:event_Total_ventaKeyTyped

    private void Sub_TotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Sub_TotalKeyReleased
        String[] v = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."};
        char c = evt.getKeyChar();
        String s = "%" + c;
        s = s.replace("%", "");
        if (s.equals(v[0]) || s.equals(v[1]) || s.equals(v[2]) || s.equals(v[3]) || s.equals(v[4]) || s.equals(v[5])) {
        } else if (s.equals(v[6]) || s.equals(v[7]) || s.equals(v[8]) || s.equals(v[9]) || s.equals(v[10])) {
        } else {
            int k = Sub_Total.getText().length() - 1;
            String n = Sub_Total.getText().substring(0, k);
            Sub_Total.setText(n);
        }

    }//GEN-LAST:event_Sub_TotalKeyReleased

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        Scanneear sc = new Scanneear();
        sc.setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        Login login = new Login();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton12ActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaCajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaCajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaCajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaCajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new VentanaCajero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField Cli_Apellido;
    public static javax.swing.JTextField Cli_Cedula;
    public static javax.swing.JTextField Cli_Correo;
    public static javax.swing.JTextField Cli_Direccion;
    public static javax.swing.JTextField Cli_Nombre;
    public static javax.swing.JTextField Cod_Venta;
    public static javax.swing.JLabel FotoProducto;
    private javax.swing.JTextField Iva_total;
    public static javax.swing.JTextField NombreFoto;
    public static javax.swing.JSpinner Pro_Cantidad;
    public static javax.swing.JTextField Pro_Cod;
    public static javax.swing.JTextField Pro_Nombre;
    public static javax.swing.JTextField Pro_precio;
    public static javax.swing.JTextField Stock;
    private javax.swing.JTextField Sub_Total;
    private javax.swing.JTextField Total_venta;
    public static javax.swing.JTextField cli_telefono;
    public static javax.swing.JTextField cod_cajero;
    private javax.swing.JCheckBox consumi;
    private javax.swing.JLabel fecha;
    public static javax.swing.JTextField id_cliente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    public static javax.swing.JTextField jTiva;
    public static javax.swing.JTextField num_caja;
    private javax.swing.JTable tablaingrp;
    // End of variables declaration//GEN-END:variables

}
