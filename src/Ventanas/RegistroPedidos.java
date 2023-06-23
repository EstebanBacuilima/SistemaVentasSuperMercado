package Ventanas;

import djmeb.Det_Pedido;
import djmeb.Pedidos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RegistroPedidos extends javax.swing.JFrame {

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();

    DefaultTableModel model = new DefaultTableModel();

    Det_Pedido detped = new Det_Pedido();
    //DecimalFormat formatter = new DecimalFormat("###,###.##");

    int CantidadP;
    double PrecioP;
    double TotalP;
    double TotalPag;
    double Ivac;
    double ToIv;
    double TotalGe;
    int r = 0;
    int filas;

    public RegistroPedidos() {

        initComponents();
        setLocationRelativeTo(null);
        cod_pedido.setText(String.valueOf(codIncrement()));
        cod_pedido.setEditable(false);
        Calendar dh = new GregorianCalendar();
        fecha_em.setCalendar(dh);
        fecha_em.setEnabled(false);

        Ivas.setEditable(true);
        Ivas.setEnabled(true);

        Cod_Cont.setText(Login.CodigoEmpe.getText());
        Ced_cont.setText(Login.CedulaEmp.getText());
        Cod_Cont.setEditable(false);
        Ced_cont.setEditable(false);

        RUC_prov.setEditable(false);
        Ced_ger.setEditable(false);

        Cod_pro.setEditable(false);

        jButton4.setEnabled(false);
    }

    public int codIncrement() {

        int serie = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = cc.conectar();

        try {

            ps = cn.prepareStatement("SELECT MAX(codigo_pedido) FROM det_pedidos");
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
    
    void Formato () {
        
        DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
        separadoresPersonalizados.setDecimalSeparator('.');
        DecimalFormat formato1 = new DecimalFormat("#.00", separadoresPersonalizados);
        
        formato1.format(precio_pro.getText());
    }

    void agregarprod() {

        DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
        separadoresPersonalizados.setDecimalSeparator('.');
        DecimalFormat formato1 = new DecimalFormat("#.00", separadoresPersonalizados);
        
        
        model = (DefaultTableModel) jTable1.getModel();

//        int CodigoPed = Integer.parseInt(cod_pedido.getText());
//        int CodigoDet = Integer.parseInt(Cod.getText());
    

        formato1.format(PrecioP);

        int CodigoP = Integer.parseInt(Cod_pro.getText());
        String NombreP = nombre_pro.getText();

        //formato1.format(precio_pro.getText());
        PrecioP = Double.parseDouble(precio_pro.getText());
        CantidadP = Integer.valueOf(cantidadspin.getValue().toString());
        TotalP = CantidadP * PrecioP;

        String ivat = Ivas.getText();
        double ivap;

        if (ivat.equals("Si")) {
            ivap = TotalP * 0.12;
            Ivac = ivap;
        } else if (ivat.equals("No")) {
            ivap = 0.00;
            Ivac = ivap;
        }

        ArrayList listap = new ArrayList();
//        listap.add(CodigoPed);
//        listap.add(CodigoDet);
        listap.add(CodigoP);
        listap.add(NombreP);
        listap.add(PrecioP);
        listap.add(CantidadP);
        listap.add(TotalP);
        listap.add(Ivac);
        listap.add(ivat);

        Object[] objectn = new Object[7];

        objectn[0] = listap.get(0);
        objectn[1] = listap.get(1);
        objectn[2] = listap.get(2);
        objectn[3] = listap.get(3);
        objectn[4] = listap.get(4);
        objectn[5] = listap.get(5);
        objectn[6] = listap.get(6);

        model.addRow(objectn);
        jTable1.setModel(model);

        calcularTotal();
        Limpiar();
    }

    void calcularTotal() {
        TotalPag = 0;
        Ivac = 0;
        double TIva = 0;
        double TotalG = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            CantidadP = Integer.parseInt(jTable1.getValueAt(i, 3).toString());
            PrecioP = Double.parseDouble(jTable1.getValueAt(i, 2).toString());
            TotalPag = TotalPag + (CantidadP * PrecioP);
            Ivac = Double.parseDouble(jTable1.getValueAt(i, 5).toString());
            TIva += Ivac;
            TotalG = TotalPag + TIva;
        }

        DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
        separadoresPersonalizados.setDecimalSeparator('.');
        DecimalFormat formato1 = new DecimalFormat("#.00", separadoresPersonalizados);

        totalt.setText("" + formato1.format(TotalPag));
        iva.setText("" + formato1.format(TIva));
        total_general.setText("" + TotalG);
        caltotalconiva();
    }

    void caltotalconiva() {
//        double total, ivat;
//        ivat = TotalPag * 0.12;
//        iva.setText(formatter.format(ivat));
//        total = TotalPag + ivat;
//        total_general.setText(formatter.format(total));
//        String ivat = Ivas.getText();
//        double total, ivap, ivato;
//        if(ivat.equals("Si")){
//            
//            ivap = TotalP * 0.12;
//            iva.setText(formatter.format(ivap));
//        }      
    }

    void guardarpro() {

        for (int i = 0; i < jTable1.getRowCount(); i++) {
//            int CodigoPedido = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
//            int CodigoDetalle = Integer.parseInt(jTable1.getValueAt(i, 1).toString());
            int CodigoProducto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            String NombreProducto = jTable1.getValueAt(i, 1).toString();
            double PrecioProducto = Double.parseDouble(jTable1.getValueAt(i, 2).toString());
            int CantidadProducto = Integer.parseInt(jTable1.getValueAt(i, 3).toString());
            double TotalProducto = Double.parseDouble(jTable1.getValueAt(i, 4).toString());
            double IvaCo = Double.parseDouble(jTable1.getValueAt(i, 5).toString());

            detped.setCod_producto(CodigoProducto);
            detped.setCantidad(CantidadProducto);
            detped.setPrecio_total(TotalProducto);
            detped.setIva_compra(IvaCo);

            r = guardarpd(detped);
            System.out.println("" + r);
        }
        if (r == 0) {
            JOptionPane.showMessageDialog(this, "NO SE INGRESO NINGUN DETALLE");
        } else if (r > 0) {
            JOptionPane.showMessageDialog(this, "PRODUCTO INGRESADO");
        }
        Borrar();

    }

    public int guardarpd(Det_Pedido detp) {

        PreparedStatement pst = null;

        String sqldp = "INSERT INTO det_pedidos (codigo_pedido, cod_pro,pedido_cantidad, pedido_precio_total, iva_compra) VALUES (?,?,?,?,?)";

        try {
            pst = cn.prepareStatement(sqldp);

//            pst.setInt(1, detp.getDetalle_cod());
            int Codi_p = Integer.parseInt(cod_pedido.getText());
            pst.setInt(1, Codi_p);
            pst.setInt(2, detp.getCod_producto());
            pst.setInt(3, detp.getCantidad());
            pst.setDouble(4, detp.getPrecio_total());
            pst.setDouble(5, detp.getIva_compra());

            r = pst.executeUpdate();
//            JOptionPane.showMessageDialog(this,"PRODUCTO INGRESADO");

        } catch (Exception e) {
        }
        return r;
    }

    public void Limpiar() {

        cod_pedido.setEditable(false);
        Cod_Cont.setEditable(false);
        RUC_prov.setEditable(false);
        Ced_ger.setEditable(false);
        Cod_pro.setText("");
        nombre_pro.setText("");
        precio_pro.setText("");
        Ivas.setText("");
        cantidadspin.setValue(1);
    }

    public void Limpiaralguardar() {

        cod_pedido.setText(String.valueOf(codIncrement()));
        Cod_Cont.setEditable(false);
        RUC_prov.setText("");
        Ced_ger.setText("");
        Cod_pro.setText("");
        nombre_pro.setText("");
        precio_pro.setText("");
        cantidadspin.setValue(1);
        fecha_entr.setDate(null);
        totalt.setText("");
        Ivas.setText("");
        iva.setText("");
        total_general.setText("");
    }

    void Borrar() {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            model.removeRow(i);
            i -= 1;
        }
    }

    void Guardarp() {

        PreparedStatement pste = null;

        String sqle = "INSERT INTO pedido (pedido_codigo,contador_codigo, ruc_provee, pedido_fecha_emision, pedido_fecha_entrega, total_general, situacion) VALUES (?,?,?,?,?,?,?)";

        String RUC_prove = RUC_prov.getText();
        String Situacion = "Pendiente";
        TotalGe = Double.parseDouble(total_general.getText());

        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

//                String fe = formato.format(fecha_em.getDate());
//                String fech[] = fe.split("-");
//                LocalDate fechaemision = LocalDate.of(Integer.parseInt(fech[0]), Integer.parseInt(fech[1]), Integer.parseInt(fech[2]));
//                
//                Date fechaem = fecha_em.getDate();
//                System.out.println(fechaem);
        String fechemi = formato.format(fecha_em.getDate());
        String fech[] = fechemi.split("/");
        LocalDate fechaemision = LocalDate.of(Integer.parseInt(fech[0]), Integer.parseInt(fech[1]), Integer.parseInt(fech[2]));

//                Date fechaen = fecha_entr.getDate();
//                System.out.println(fechaen);
        String fee = formato.format(fecha_entr.getDate());
        String feche[] = fee.split("/");
        LocalDate fechaentrega = LocalDate.of(Integer.parseInt(feche[0]), Integer.parseInt(feche[1]), Integer.parseInt(feche[2]));

//                Date dat=new Date();//Instancia la fecha del sistema
//                if(fecha_entr.getDate().getTime()>=dat.getTime()){//Compara si la fecha seleccionada es memor o igual a la fecha actual
//                JOptionPane.showMessageDialog(this, "Fecha antigua");
//                }
//                JOptionPane.showMessageDialog(this, "Fecha Correcta");
//                SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
//                String fechaemi = formato.format(fechaem);
//                String fech[] = fechaemi.split("/");
//                LocalDate fechaemision = LocalDate.of(Integer.parseInt(fech[0]), Integer.parseInt(fech[1]), Integer.parseInt(fech[2]));
//                fechaen = fecha_ent.getDate();
//                System.out.println(fechaen + "Error");
//                String fechentr = formato.format(fechaen);
//                System.out.println(fechentr);
//                String fec[] = fechentr.split("/");
//                LocalDate fechaentrega = LocalDate.of(Integer.parseInt(fec[0]), Integer.parseInt(fec[1]), Integer.parseInt(fec[2]));
//                System.out.println(fechaentrega);
//                String fechaent = formato.format(fechaen);
//                String fec[] = fechaent.split("/");
//                LocalDate fechaentrega = LocalDate.of(Integer.parseInt(fec[0]), Integer.parseInt(fec[1]), Integer.parseInt(fec[2]));
//        
        Pedidos pedido = new Pedidos(RUC_prove, fechaemision, fechaentrega, TotalGe, Situacion);

        try {
            pste = cn.prepareStatement(sqle);

            int Codi_p = Integer.parseInt(cod_pedido.getText());
            pste.setInt(1, Codi_p);
            int Codi_c = Integer.parseInt(Cod_Cont.getText());
            pste.setInt(2, Codi_c);
            pste.setString(3, pedido.getProveedor_ruc());
            pste.setDate(4, java.sql.Date.valueOf(fechaemision));
            pste.setDate(5, java.sql.Date.valueOf(fechaentrega));
            pste.setDouble(6, pedido.getTotal_general());
            pste.setString(7, Situacion);

            pste.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se guardo");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No Se guardo" + e);
//                        Limpiar();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Cod_Cont = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        RUC_prov = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Ced_ger = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Cod_pro = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        nombre_pro = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        precio_pro = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cantidadspin = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cod_pedido = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        totalt = new javax.swing.JTextField();
        Ced_cont = new javax.swing.JTextField();
        iva = new javax.swing.JTextField();
        Ivas = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        fecha_em = new com.toedter.calendar.JDateChooser();
        fecha_entr = new com.toedter.calendar.JDateChooser();
        total_general = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jButton7 = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 48)); // NOI18N
        jLabel15.setText("PEDIDOS");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 180, 60));

        jLabel10.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel10.setText("COD Contador :");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        Cod_Cont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cod_ContActionPerformed(evt);
            }
        });
        jPanel1.add(Cod_Cont, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 110, -1));

        jLabel11.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel11.setText("RUC Proveedor :");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        RUC_prov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RUC_provActionPerformed(evt);
            }
        });
        jPanel1.add(RUC_prov, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 140, -1));

        jButton1.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); // NOI18N
        jButton1.setText("Buscar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 70, 30));

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel3.setText("C.I Responsable :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, -1, -1));

        Ced_ger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ced_gerActionPerformed(evt);
            }
        });
        jPanel1.add(Ced_ger, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 140, -1));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel4.setText("COD Producto :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, 20));

        Cod_pro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cod_proActionPerformed(evt);
            }
        });
        jPanel1.add(Cod_pro, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 120, 30));

        jButton2.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); // NOI18N
        jButton2.setText("Buscar");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, -1, 30));

        jLabel13.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel13.setText("Nombre Producto : ");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, -1, 20));

        nombre_pro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombre_proActionPerformed(evt);
            }
        });
        jPanel1.add(nombre_pro, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 160, 30));

        jLabel14.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel14.setText("IVA : ");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 370, -1, 20));
        jPanel1.add(precio_pro, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, 170, 30));

        jLabel12.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel12.setText("Cantidad : ");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        cantidadspin.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jPanel1.add(cantidadspin, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 410, 60, 30));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel2.setText("Fecha de emisión : ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel5.setText("Fecha de entrega : ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, -1, -1));

        jButton3.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 16)); // NOI18N
        jButton3.setText("Agregar");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 540, -1, -1));

        jButton4.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 16)); // NOI18N
        jButton4.setText("Modificar");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 540, -1, -1));

        jButton5.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 16)); // NOI18N
        jButton5.setText("Eliminar");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 540, -1, -1));

        jButton6.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 16)); // NOI18N
        jButton6.setText("Guardar");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 540, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel1.setText("Código Pedido :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 110, -1, -1));

        cod_pedido.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        cod_pedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cod_pedidoActionPerformed(evt);
            }
        });
        jPanel1.add(cod_pedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 100, 40, 40));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Producto", "Nombre Producto", "Precio unitario", "Cantidad", "Total", "IVA Compra", "IVA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setGridColor(new java.awt.Color(204, 204, 204));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 170, 760, 290));

        totalt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totaltActionPerformed(evt);
            }
        });
        jPanel1.add(totalt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 490, 118, -1));

        Ced_cont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ced_contActionPerformed(evt);
            }
        });
        jPanel1.add(Ced_cont, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 140, -1));

        iva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ivaActionPerformed(evt);
            }
        });
        jPanel1.add(iva, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 520, 118, 30));
        jPanel1.add(Ivas, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 360, 60, 30));

        jLabel7.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel7.setText("Total : ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 490, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel8.setText("IVA : ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 520, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel9.setText("Total General : ");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 560, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel16.setText("Cédula Contador :");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, -1, -1));

        fecha_em.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(fecha_em, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 470, 150, -1));

        fecha_entr.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(fecha_entr, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 500, 150, -1));
        jPanel1.add(total_general, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 560, 118, 24));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setToolTipText("");
        jSeparator2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jSeparator2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 90, 190, 60));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator4.setToolTipText("");
        jSeparator4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jSeparator4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 480, 280, 120));

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 500, 20));

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 500, 20));

        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 500, 20));

        jButton7.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 16)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonSalirCrud.png"))); // NOI18N
        jButton7.setToolTipText("Regresar");
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 557, 40, 40));

        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1270, 20));

        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 500, 20));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPriPOO(1).png"))); // NOI18N
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 10, 200, 60));

        jLabel17.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel17.setText("Precio : ");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, -1, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Cod_ContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cod_ContActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cod_ContActionPerformed

    private void RUC_provActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RUC_provActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RUC_provActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        RProveedor rp = new RProveedor();
        rp.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Ced_gerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ced_gerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Ced_gerActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        RProducto rpr = new RProducto();
        rpr.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void nombre_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombre_proActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre_proActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Date dat = new Date();//Instancia la fecha del sistema

        if (Cod_Cont.getText().isEmpty() || nombre_pro.getText().isEmpty() || precio_pro.getText().isEmpty() || Ivas.getText().isEmpty() || Cod_pro.getText().isEmpty() || Ced_ger.getText().isEmpty() || RUC_prov.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "LLene los campos corectamente");

        } else {

            if (fecha_entr.getDate().getTime() < dat.getTime()) {//Compara si la fecha seleccionada es memor o igual a la fecha actual
                JOptionPane.showMessageDialog(this, "Fecha antigua");
            } else {
                //JOptionPane.showMessageDialog(this, "Fecha Correcta");
                agregarprod();
                Ivas.setText("");
                fecha_entr.setEnabled(false);
            }

        }
        //agregarprod();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (Cod_Cont.getText().isEmpty() || nombre_pro.getText().isEmpty() || precio_pro.getText().isEmpty() || Ivas.getText().isEmpty() || Cod_pro.getText().isEmpty() || Ced_ger.getText().isEmpty() || RUC_prov.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "LLene los campos corectamente");

        } else {

            fecha_entr.setEnabled(true);

            Object[] objectnu = new Object[6];
            double Pre = Double.parseDouble(precio_pro.getText());
            int Cant = Integer.valueOf(cantidadspin.getValue().toString());

//        TotalP = Cant * Pre;
//        
//        double ivap  = 0;
//        double ivaNuevo;
//        
//        if (Ivas.getText().equals("Si")) {
//            ivap = TotalP * 0.12;
//        } else if (Ivas.getText().equals("No")) {
//            ivap = 0.00;
//        }
//        
//        ivaNuevo = ivap;
//          calcularTotal();
            objectnu[0] = Integer.parseInt(Cod_pro.getText());
            objectnu[1] = nombre_pro.getText();
            objectnu[2] = Double.parseDouble(precio_pro.getText());
            objectnu[3] = Integer.valueOf(cantidadspin.getValue().toString());
            objectnu[4] = TotalP;
            //objectnu[5] = ivaNuevo;

//      jTable1.gets
            for (int i = 0; i < 5; i++) {
                model.setValueAt(objectnu[i], jTable1.getSelectedRow(), i);
                calcularTotal();
            }
            Limpiar();
            Cod_pro.setEnabled(true);
            nombre_pro.setEnabled(true);
            precio_pro.setEnabled(true);
            jButton3.setEnabled(true);
            fecha_entr.setEnabled(false);

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        int fila = jTable1.getSelectedRow();

        if (fila >= 0) {

            model.removeRow(jTable1.getSelectedRow());
            calcularTotal();

        } else {
            JOptionPane.showMessageDialog(this, "NO Selecciono Fila");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked

    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        if (jTable1.getRowCount() == 0) {

            JOptionPane.showMessageDialog(null, "No Existen Datos", "Advertencia", JOptionPane.WARNING_MESSAGE);
            //jButton6.setEnabled(false);

        } else {

            //JOptionPane.showMessageDialog(null, "Si hay Datos a Exportar", "Información", JOptionPane.WARNING_MESSAGE);
            //jButton6.setEnabled(true);
            Guardarp();
            guardarpro();
            Limpiaralguardar();
            fecha_entr.setEnabled(true);

        }


    }//GEN-LAST:event_jButton6ActionPerformed

    private void cod_pedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cod_pedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cod_pedidoActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int fila = jTable1.getSelectedRow();

//        DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
//        separadoresPersonalizados.setDecimalSeparator('.');
//        DecimalFormat formato1 = new DecimalFormat("#.00", separadoresPersonalizados);

        

        if (evt.getClickCount() == 2) {

            fecha_entr.setEnabled(true);
            jButton3.setEnabled(false);
            jButton4.setEnabled(true);
//            formato1.format(precio_pro.getText());

            if (fila >= 0) {

                Ivas.setEditable(true);
                Ivas.setEnabled(true);
                Cod_pro.setText(jTable1.getValueAt(fila, 0).toString());
                Cod_pro.setEnabled(false);
                nombre_pro.setText(jTable1.getValueAt(fila, 1).toString());
                nombre_pro.setEnabled(false);
                precio_pro.setText(jTable1.getValueAt(fila, 2).toString());
                precio_pro.setEnabled(false);
//                formato1.format(precio_pro.getText());
                String cantidad = String.valueOf(jTable1.getValueAt(fila, 3));
                cantidadspin.setValue(Integer.parseInt(cantidad));
                Ivas.setText(jTable1.getValueAt(fila, 6).toString());
                Ivas.setEnabled(false);

            } else {
                JOptionPane.showMessageDialog(null, "No seleciono fila");
            }
        } else {
            Limpiar();
            jButton3.setEnabled(true);
            jButton4.setEnabled(false);
            Cod_pro.setEnabled(true);
            nombre_pro.setEnabled(true);
            precio_pro.setEnabled(true);
            fecha_entr.setEnabled(false);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void totaltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totaltActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totaltActionPerformed

    private void Cod_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cod_proActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cod_proActionPerformed

    private void Ced_contActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ced_contActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Ced_contActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        VentanaContador vc = new VentanaContador();
        vc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void ivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ivaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ivaActionPerformed

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
            java.util.logging.Logger.getLogger(RegistroPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroPedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Ced_cont;
    public static javax.swing.JTextField Ced_ger;
    private javax.swing.JTextField Cod_Cont;
    public static javax.swing.JTextField Cod_pro;
    public static javax.swing.JTextField Ivas;
    public static javax.swing.JTextField RUC_prov;
    private javax.swing.JSpinner cantidadspin;
    private javax.swing.JTextField cod_pedido;
    private com.toedter.calendar.JDateChooser fecha_em;
    private com.toedter.calendar.JDateChooser fecha_entr;
    private javax.swing.JTextField iva;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    public static javax.swing.JTextField nombre_pro;
    public static javax.swing.JTextField precio_pro;
    private javax.swing.JTextField total_general;
    private javax.swing.JTextField totalt;
    // End of variables declaration//GEN-END:variables
}
