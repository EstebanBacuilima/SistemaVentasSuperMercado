package Ventanas;

import djmeb.Det_productosing;
import djmeb.Productos;
import djmeb.Rproductosingresados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class IngresoProdPedi extends javax.swing.JFrame {

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();
    ArrayList<Productos> ListaProductos = new ArrayList();
    DefaultTableModel modelop = new DefaultTableModel();
    int idp, cant, r;
    //variables para calculos de los productos que se ingresan
    double tapagar, preu, iv, tconiva;
    //variable para almacenar la cantidad de los productos del detalle de pedido
    int canti;
    Productos pd = new Productos();
    Det_productosing dpin = new Det_productosing();
    Rproductosingresados Rpi = new Rproductosingresados();

    public IngresoProdPedi() {
        initComponents();
        setLocationRelativeTo(null);
        Calendar calendar = new GregorianCalendar();
        Ing_fechaa.setText("" + calendar.get(Calendar.DAY_OF_MONTH) + "-" + (1 + calendar.get(Calendar.MARCH)) + "-" + calendar.get(Calendar.YEAR));
        mostrardatosPedi("");
        inavilitaringreso();
        Cod_Rpi.setText(codIncrement_proIng());

        Ing_fechaa.setBackground(new java.awt.Color(0, 0, 0, 1));
        Cod_bodeguero.setBackground(new java.awt.Color(0, 0, 0, 1));
        Cl_bodeguero.setBackground(new java.awt.Color(0, 0, 0, 1));
        Cod_Rpi.setBackground(new java.awt.Color(0, 0, 0, 1));
        Cod_bodeguero.setText(VentanaBodeguero.Bod_codigoFF.getText());
        Cl_bodeguero.setText(VentanaBodeguero.Bod_cedulaFF.getText());
    }

    public String codIncrement_proIng() {
        int serie = 1;

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = cc.conectar();

        try {

            ps = cn.prepareStatement("SELECT MAX(rpi_codigo) FROM registroproing");
            rs = ps.executeQuery();

            while (rs.next()) {
                serie = rs.getInt(1) + 1;
            }

        } catch (Exception e) {
            System.out.println("4 Error" + e.getMessage());
        }

        String numID = String.valueOf(serie);
        return numID;

    }

    void mostrardatosDetPedi(String codc) {

        DefaultTableModel modelo2 = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        modelo2.addColumn("Pedido");
        modelo2.addColumn("Codigo Producto");
        modelo2.addColumn("Nombre ");
        modelo2.addColumn("P. Unit");
        modelo2.addColumn(" Cant ");
        modelo2.addColumn(" iva ");
        modelo2.addColumn("P. Total");

        Tabla_Det_Ped.setModel(modelo2);

        // Acumular la sentencia
        String sqlc = "SELECT codigo_pedido, det_pedidos.cod_pro, Pro_nombre,Pro_precio, pedido_cantidad, Pro_iva ,pedido_precio_total "
                + "FROM det_pedidos, registroproducto "
                + "WHERE codigo_pedido='" + codc + "' and det_pedidos.cod_pro = registroproducto.cod_pro ";

        String[] datos = new String[7];

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqlc);

            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);

                modelo2.addRow(datos);
            }
            Tabla_Det_Ped.setModel(modelo2);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }

    void mostrardatosPedi(String codc) {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        //DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo ");
        modelo.addColumn("Contador");
        modelo.addColumn("Ruc provedor");
        modelo.addColumn("Fecha de emision");
        modelo.addColumn("Fecha de Entrega");
        modelo.addColumn("Situación");

        Tabla_pedido.setModel(modelo);

        // Acumular la sentencia
        String sqlc = "";
        String fecha = Ing_fechaa.getText();
        String fec[] = fecha.split("-");
        LocalDate fechaa = LocalDate.of(Integer.parseInt(fec[2]), Integer.parseInt(fec[1]), Integer.parseInt(fec[0]));
        System.out.println("" + fechaa);
        String f = String.valueOf(fechaa);
        if (codc.equals("")) {

            sqlc = "SELECT * FROM pedido Where situacion = 'Pendiente'  and   pedido_fecha_entrega = '" + f + "' ";

        } else {

            sqlc = "SELECT * FROM pedido WHERE Ruc_provee like '%" + codc + "%' and situacion = 'Pendiente' and   pedido_fecha_entrega = '" + f + "'";
        }

        String[] datos = new String[7];

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqlc);

            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(7);

                modelo.addRow(datos);
            }
            Tabla_pedido.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }

    void agregarproductosIng() {
        modelop = (DefaultTableModel) Tabla_Ingre_Pro.getModel();
        DecimalFormat formato = new DecimalFormat("#.##");
        double total;
        int codp = Integer.parseInt(Inp_cod.getText());
        String nomp = Inp_nombre.getText();
        preu = Double.parseDouble(Inp_preu.getText());
        int cantidad = Integer.parseInt(Inp_cantinicial.getText());
        cant = Integer.valueOf(Inp_cantingresada.getValue().toString());
        int cantdef = Integer.valueOf(Inp_cantdefec.getValue().toString());
        iv = Double.parseDouble(Inp_iva.getText());
        formato.format(iv);

        total = cant * preu;

        formato.format(total);
        ArrayList lista = new ArrayList();

        lista.add(codp);
        lista.add(nomp);
        lista.add(preu);
        lista.add(cantidad);
        lista.add(cant);
        lista.add(cantdef);
        lista.add(iv);
        lista.add(total);

        Object[] ob = new Object[8];

        ob[0] = lista.get(0);
        ob[1] = lista.get(1);
        ob[2] = lista.get(2);
        ob[3] = lista.get(3);
        ob[4] = lista.get(4);
        ob[5] = lista.get(5);
        ob[6] = lista.get(6);
        ob[7] = lista.get(7);

        modelop.addRow(ob);

        Tabla_Ingre_Pro.setModel(modelop);
        calcularTotalap();
        Limpiar_ingreso();
    }

    void calcularTotalap() {
        tapagar = 0;
        tconiva = 0;
        for (int i = 0; i < Tabla_Ingre_Pro.getRowCount(); i++) {
            preu = Double.parseDouble(Tabla_Ingre_Pro.getValueAt(i, 2).toString());
            cant = Integer.parseInt(Tabla_Ingre_Pro.getValueAt(i, 4).toString());
            iv = Double.parseDouble(Tabla_Ingre_Pro.getValueAt(i, 6).toString());
            tapagar = tapagar + (cant * preu);
            if (iv == 0) {

                tconiva = tconiva;
            } else if (iv == 0.12) {
                tconiva = tconiva + ((preu * cant) * 0.12);
            }

        }

        DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
        separadoresPersonalizados.setDecimalSeparator('.');
        DecimalFormat formato1 = new DecimalFormat("#.00", separadoresPersonalizados);
//        Total_.setText(String.valueOf(formato.format(totalpagar)));

        Sub_Total.setText(String.valueOf(formato1.format(tconiva)));
        Inp_precio.setText(String.valueOf(formato1.format(tapagar)));

    }

    void guardarregistroPI() {

        for (int i = 0; i < Tabla_Ingre_Pro.getRowCount(); i++) {
            int cod_Rpi = Integer.parseInt(Cod_Rpi.getText());

            int codp = Integer.parseInt(Tabla_Ingre_Pro.getValueAt(i, 0).toString());
            String nomp = Tabla_Ingre_Pro.getValueAt(i, 1).toString();
            double precu = Double.parseDouble(Tabla_Ingre_Pro.getValueAt(i, 2).toString());
            int canti = Integer.parseInt(Tabla_Ingre_Pro.getValueAt(i, 3).toString());
            int cantingresa = Integer.parseInt(Tabla_Ingre_Pro.getValueAt(i, 4).toString());
            int cantidefect = Integer.parseInt(Tabla_Ingre_Pro.getValueAt(i, 5).toString());
            double pret = Double.parseDouble(Tabla_Ingre_Pro.getValueAt(i, 7).toString());

            dpin.setCod_Rpi(cod_Rpi);
            dpin.setDping_cod(codp);
            dpin.setDping_nombre(nomp);
            dpin.setDping_preu(precu);
            dpin.setDping_cant(canti);
            dpin.setDping_cantingresada(cantingresa);
            dpin.setDping_cantdefectuosa(cantidefect);
            dpin.setDping_pret(pret);

            r = GuardarProI(dpin);
        }
        if (r == 0) {
            JOptionPane.showMessageDialog(this, "NO SE INGRESO NINGUN PRODUCTO");
        } else if (r == 1) {
            JOptionPane.showMessageDialog(this, "PRODUCTOS INGRESADOS");
        }
    }

    public int GuardarProI(Det_productosing di) {
        PreparedStatement pst = null;

        String sqlip = "INSERT INTO det_registroproing (drpi_rpi_cod, drpi_pro_cod, drpi_pro_cant,drpi_pro_cant_ing, drpi_pro_cant_def, drpi_pro_ptotal)values(?,?,?,?,?,?)";

        try {
            pst = cn.prepareStatement(sqlip);
            pst.setInt(1, di.getCod_Rpi());
            pst.setInt(2, di.getDping_cod());
            pst.setInt(3, di.getDping_cant());
            pst.setInt(4, di.getDping_cantingresada());;
            pst.setInt(5, di.getDping_cantdefectuosa());;
            pst.setDouble(6, di.getDping_pret());

            r = pst.executeUpdate();

        } catch (Exception e) {
            System.out.println("mandar " + e);
        }
        return r;
    }

    void guardardatosRpi() {

        int codr = Integer.parseInt(Cod_Rpi.getText());
        int codbo = Integer.parseInt(Cod_bodeguero.getText());
        String rucpv = Ruc_proveedor.getText();
        String fech = Ing_fechaa.getText();
        String fec[] = fech.split("-");
        LocalDate fecha = LocalDate.of(Integer.parseInt(fec[2]), Integer.parseInt(fec[1]), Integer.parseInt(fec[0]));
        System.out.println("" + fecha);
        double sbt = Double.parseDouble(Inp_precio.getText());
        double tot = Double.parseDouble(Total_.getText());
        Rpi.setRpi_cod(codr);
        Rpi.setRpi_Cod_bodeguero(codbo);
        Rpi.setRpi_ruc_provedor(rucpv);
        Rpi.setRpi_fecha(fecha);
        Rpi.setRpi_subtotal_ap(sbt);
        Rpi.setRpi_total_ap(tot);

        r = GuardarRegistroProI(Rpi);

    }

    public int GuardarRegistroProI(Rproductosingresados pr) {
        PreparedStatement pst = null;

        String sqlip = "INSERT INTO registroproing (rpi_codigo, rpi_cod_bodeguero, rpi_ruc_provedor,rpi_fecha_ingreso, rpi_subtotal_pagar, rpi_total_pagar)values(?,?,?,?,?,?)";

        try {

            LocalDate fsita = pr.getRpi_fecha();

            pst = cn.prepareStatement(sqlip);
            pst.setInt(1, pr.getRpi_cod());
            pst.setInt(2, pr.getRpi_Cod_bodeguero());
            pst.setString(3, pr.getRpi_ruc_provedor());
            pst.setDate(4, java.sql.Date.valueOf(fsita));
            pst.setDouble(5, pr.getRpi_subtotal_ap());
            pst.setDouble(6, pr.getRpi_total_ap());

            r = pst.executeUpdate();

        } catch (Exception e) {
            System.out.println("mandar " + e);
        }
        return r;
    }

     public int ActualizarCantpro(int cant, int cod) {
        PreparedStatement pst = null;
        String sqlacp = "UPDATE registroproducto set Pro_cant_exi =? WHERE Cod_pro = ?";
        try {
            pst = cn.prepareStatement(sqlacp);
            pst.setInt(1, cant);
            pst.setInt(2, cod);
            pst.executeUpdate();
        } catch (Exception e) {
        }

        return r;
    }

    public Productos listarcod(int cod){
        
        System.out.println("Si" + cod);
        
        Productos p = new Productos();
        PreparedStatement pst = null;
        String sql = "SELECT cod_pro , pro_cant_exi FROM registroproducto  WHERE Cod_Pro='" + cod+ "'and Pro_estado='1'";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            
            while (rs.next()) {
            
                p.setCod_producto(rs.getInt(1));
                p.setCant_existente(rs.getInt(2));
            
            
            }
        } catch (Exception e) {
        }
        return p;
    }

    public void Limpiar_ingreso() {
        Inp_cod.setText("");
        Inp_nombre.setText("");
        Inp_preu.setText("");
        Inp_iva.setText("");
        Inp_cantinicial.setText("");
        SpinnerNumberModel in = new SpinnerNumberModel();
        in.setValue(0);
        Inp_cantingresada.setModel(in);
        Inp_cantdefec.setModel(in);
        Inp_cantingresada.setEnabled(false);
        Inp_cantdefec.setEnabled(false);

    }

    public void inavilitaringreso() {
        Inp_cod.setEditable(false);
        Inp_nombre.setEditable(false);
        Inp_preu.setEditable(false);
        Inp_iva.setEditable(false);
        Inp_cantinicial.setEditable(false);
        Inp_cantingresada.setEnabled(false);
        Inp_cantdefec.setEnabled(false);
        Inp_Cargar.setEnabled(false);
        Inp_Eliminar.setEnabled(false);
        Inp_Modificar.setEnabled(false);
        Ing_limpiar.setEnabled(false);
        Guardartodo.setEnabled(false);
        Cod_bodeguero.setEditable(false);
        Cl_bodeguero.setEditable(false);
        Ing_fechaa.setEditable(false);
        Cod_Rpi.setEditable(false);
        Ruc_proveedor.setEditable(false);
        Sub_Total.setEditable(false);
        Total_.setEditable(false);
        Inp_precio.setEditable(false);
        cod_pedido.setEditable(false);
    }

    public void habilitaringreso() {
        Inp_cod.setEditable(false);
        Inp_nombre.setEditable(false);
        Inp_preu.setEditable(false);
        Inp_iva.setEditable(false);
        Inp_cantinicial.setEditable(false);
        Inp_cantingresada.setEnabled(true);
        Inp_cantdefec.setEnabled(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        Eliminar_ = new javax.swing.JMenuItem();
        Modifi = new javax.swing.JMenuItem();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Pedido_buscarp = new javax.swing.JTextField();
        Buscar_pedido = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_pedido = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla_Det_Ped = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        Inp_preu = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        Inp_cod = new javax.swing.JTextField();
        Inp_nombre = new javax.swing.JTextField();
        Guardartodo = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        Inp_Cargar = new javax.swing.JButton();
        Inp_Modificar = new javax.swing.JButton();
        xddd = new javax.swing.JSeparator();
        xd = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        Tabla_Ingre_Pro = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        Inp_precio = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        Inp_Eliminar = new javax.swing.JButton();
        Ing_limpiar = new javax.swing.JButton();
        Inp_cantingresada = new javax.swing.JSpinner();
        Ing_fechaa = new javax.swing.JTextField();
        Cod_Rpi = new javax.swing.JTextField();
        Ruc_proveedor = new javax.swing.JTextField();
        Cod_bodeguero = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        Inp_cantdefec = new javax.swing.JSpinner();
        Inp_cantinicial = new javax.swing.JTextField();
        Sub_Total = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Inp_iva = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        Total_ = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        Cl_bodeguero = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        cod_pedido = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel32 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        Eliminar_.setText("Eliminar");
        Eliminar_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Eliminar_ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Eliminar_);

        Modifi.setText("jMenuItem1");
        jPopupMenu1.add(Modifi);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel12.setText("Ingreso de productos");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(153, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Pedido_buscarp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pedido_buscarpActionPerformed(evt);
            }
        });
        Pedido_buscarp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Pedido_buscarpKeyReleased(evt);
            }
        });
        jPanel3.add(Pedido_buscarp, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 150, -1));

        Buscar_pedido.setBackground(new java.awt.Color(153, 204, 255));
        Buscar_pedido.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        Buscar_pedido.setText("Mostrar");
        Buscar_pedido.setToolTipText("Mostrar");
        Buscar_pedido.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        Buscar_pedido.setBorderPainted(false);
        Buscar_pedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Buscar_pedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buscar_pedidoActionPerformed(evt);
            }
        });
        jPanel3.add(Buscar_pedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(523, 130, 60, 30));

        Tabla_pedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        Tabla_pedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla_pedidoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla_pedido);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 570, 140));

        Tabla_Det_Ped.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        Tabla_Det_Ped.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla_Det_PedMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Tabla_Det_Ped);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 570, 170));

        jLabel13.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel13.setText("Fecha:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 60, -1, 30));
        jPanel3.add(Inp_preu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 150, 60, 30));

        jLabel14.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel14.setText("Ruc Provedor");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 120, -1, 20));

        jLabel15.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel15.setText("Nombre producto");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 180, -1, 30));

        Inp_cod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Inp_codActionPerformed(evt);
            }
        });
        jPanel3.add(Inp_cod, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 150, 190, -1));
        jPanel3.add(Inp_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 180, 190, -1));

        Guardartodo.setBackground(new java.awt.Color(204, 255, 255));
        Guardartodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Gurdar 40 x 40.png"))); // NOI18N
        Guardartodo.setToolTipText("Guardar");
        Guardartodo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Guardartodo.setBorderPainted(false);
        Guardartodo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Guardartodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardartodoActionPerformed(evt);
            }
        });
        jPanel3.add(Guardartodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 610, 70, 50));

        jLabel16.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel16.setText("Ingresados");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 250, -1, 20));

        jButton4.setBackground(new java.awt.Color(204, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pendiente 40 x 40.png"))); // NOI18N
        jButton4.setToolTipText("Pendiente");
        jButton4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.setBorderPainted(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 580, 60, 60));

        Inp_Cargar.setBackground(new java.awt.Color(204, 255, 255));
        Inp_Cargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cargar 40 x 40.png"))); // NOI18N
        Inp_Cargar.setToolTipText("Cargar");
        Inp_Cargar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Inp_Cargar.setBorderPainted(false);
        Inp_Cargar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Inp_Cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Inp_CargarActionPerformed(evt);
            }
        });
        jPanel3.add(Inp_Cargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 280, 60, 60));

        Inp_Modificar.setBackground(new java.awt.Color(204, 255, 255));
        Inp_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Modificar 40 x 40.png"))); // NOI18N
        Inp_Modificar.setToolTipText("Actualizar");
        Inp_Modificar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Inp_Modificar.setBorderPainted(false);
        Inp_Modificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Inp_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Inp_ModificarActionPerformed(evt);
            }
        });
        jPanel3.add(Inp_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 280, 60, 60));

        xddd.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(xddd, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 20, 580));
        jPanel3.add(xd, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1290, 20));

        Tabla_Ingre_Pro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod ", "Nombre", "Precio unit", "Cantidad", "Ingresados", "Defectuosos", "iva", "Precio total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla_Ingre_Pro.setComponentPopupMenu(jPopupMenu1);
        Tabla_Ingre_Pro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla_Ingre_ProMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(Tabla_Ingre_Pro);

        jPanel3.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 390, 640, 120));

        jLabel18.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel18.setText("Cl:");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 70, 30));
        jPanel3.add(Inp_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 520, 100, -1));

        jLabel20.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel20.setText("iva 12%");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 550, -1, 20));

        Inp_Eliminar.setText("Eliminar");
        Inp_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Inp_EliminarActionPerformed(evt);
            }
        });
        jPanel3.add(Inp_Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 400, 70, 20));

        Ing_limpiar.setBackground(new java.awt.Color(204, 255, 255));
        Ing_limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LimpiarCajero.png"))); // NOI18N
        Ing_limpiar.setToolTipText("Limpiar");
        Ing_limpiar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Ing_limpiar.setBorderPainted(false);
        Ing_limpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Ing_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ing_limpiarActionPerformed(evt);
            }
        });
        jPanel3.add(Ing_limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 330, 50, 50));

        Inp_cantingresada.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                Inp_cantingresadaStateChanged(evt);
            }
        });
        jPanel3.add(Inp_cantingresada, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 250, 60, -1));

        Ing_fechaa.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        Ing_fechaa.setBorder(null);
        jPanel3.add(Ing_fechaa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 60, 130, 30));

        Cod_Rpi.setBorder(null);
        jPanel3.add(Cod_Rpi, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 80, 30));
        jPanel3.add(Ruc_proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 120, 190, -1));

        Cod_bodeguero.setBorder(null);
        jPanel3.add(Cod_bodeguero, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 80, 30));

        jLabel21.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel21.setText("Cantidad Recibida");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 220, 120, 20));

        jLabel22.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel22.setText("Iva");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 190, -1, 20));

        Inp_cantdefec.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                Inp_cantdefecStateChanged(evt);
            }
        });
        jPanel3.add(Inp_cantdefec, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 280, 60, -1));
        jPanel3.add(Inp_cantinicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 220, 60, -1));
        jPanel3.add(Sub_Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 550, 100, -1));

        jLabel23.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel23.setText("Valor Total");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 580, -1, 20));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonSalirCrud.png"))); // NOI18N
        jButton1.setToolTipText("Volver");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, -1, -1));
        jPanel3.add(Inp_iva, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 190, 60, -1));

        jLabel24.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel24.setText("Defectuosos");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 280, -1, 20));

        jButton2.setBackground(new java.awt.Color(204, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/CalculadoraCaj.png"))); // NOI18N
        jButton2.setToolTipText("Calcular");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.setBorderPainted(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 520, 70, 70));

        jLabel25.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel25.setText("sub total");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 520, -1, 20));
        jPanel3.add(Total_, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 580, 100, -1));

        jLabel27.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel27.setText("Cod producto");
        jPanel3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 150, -1, 30));

        jLabel28.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel28.setText("Precio unitario");
        jPanel3.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 150, -1, 30));

        jLabel29.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel29.setText("Pedido");
        jPanel3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 60, 20));

        Cl_bodeguero.setBorder(null);
        jPanel3.add(Cl_bodeguero, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 150, 30));

        jLabel30.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel30.setText("Bodeguero");
        jPanel3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 70, 30));

        jLabel19.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel19.setText("Pedido");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 110, -1, 30));

        jLabel26.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel26.setText("Productos del Pedido");
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 180, 20));

        jLabel31.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel31.setText("Ingreso Nro");
        jPanel3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, -1, 30));
        jPanel3.add(cod_pedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 110, 60, 30));

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 48)); // NOI18N
        jLabel1.setText("RECIBIR LOS PEDIDOS");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, -1, -1));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 610, -1));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPriPOO(1).png"))); // NOI18N
        jPanel3.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 20, 200, 60));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Pendiente");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 640, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Calular");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 590, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("Actualizar");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 340, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setText("Cargar");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 340, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Pedido_buscarpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pedido_buscarpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pedido_buscarpActionPerformed

    private void Buscar_pedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buscar_pedidoActionPerformed

        mostrardatosPedi("");
    }//GEN-LAST:event_Buscar_pedidoActionPerformed

    private void Tabla_pedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_pedidoMouseClicked
        int i = Tabla_pedido.getSelectedRow();
        if (evt.getClickCount() == 2) {
            if (i >= 0) {
                Limpiar_ingreso();
                String codp = (Tabla_pedido.getValueAt(i, 0).toString());
                Ruc_proveedor.setText(Tabla_pedido.getValueAt(i, 2).toString());
                cod_pedido.setText(Tabla_pedido.getValueAt(i, 0).toString());
                mostrardatosDetPedi(codp);

            } else {
                JOptionPane.showMessageDialog(null, "No seleciono fila");
            }
        } else {

        }
    }//GEN-LAST:event_Tabla_pedidoMouseClicked

    private void Inp_codActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Inp_codActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Inp_codActionPerformed

    private void GuardartodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardartodoActionPerformed
        if(Inp_precio.getText().equals("")){
           JOptionPane.showMessageDialog(this, "Debe ingrsar los datos");
       }else{
        
        guardardatosRpi();
        guardarregistroPI();
        ActualizarCantidadexi();   
        try {
            
            String codp = cod_pedido.getText();
            
            PreparedStatement pst = cn.prepareStatement("UPDATE pedido SET situacion= 'Recibido' WHERE pedido_codigo='" +codp + "'");
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Productos del pedido Recibidos con exito");
            Tabla_pedido.setEnabled(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       }
        
    }//GEN-LAST:event_GuardartodoActionPerformed
    void ActualizarCantidadexi(){
        for (int i = 0; i < modelop.getRowCount(); i++) {
            Productos act = new Productos();
            idp = Integer.parseInt(Tabla_Ingre_Pro.getValueAt(i, 0).toString());
            cant = Integer.parseInt(Tabla_Ingre_Pro.getValueAt(i, 4).toString());
            act = listarcod(idp);
            int pa = act.getCant_existente()+cant;
            
            ActualizarCantpro(pa, idp);
        }
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (Tabla_pedido.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No Existen Datos", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            //JOptionPane.showMessageDialog(null, "Si hay Datos a Exportar", "Información", JOptionPane.WARNING_MESSAGE);
            try {
                int i = Tabla_pedido.getSelectedRow();
                String codp = (Tabla_pedido.getValueAt(i, 0).toString());

                PreparedStatement pst = cn.prepareStatement("UPDATE pedido SET situacion= 'Pendiente*' WHERE pedido_codigo='" + codp + "'");
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Pedido No recibido");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            DefaultTableModel dm = (DefaultTableModel) Tabla_Det_Ped.getModel();
            int rowCount = dm.getRowCount();
            //Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                dm.removeRow(i);
            }

            Inp_precio.setText("");
            Sub_Total.setText("");
            Total_.setText("");
            Ruc_proveedor.setText("");
            cod_pedido.setText("");

            mostrardatosPedi("");
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void Inp_CargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Inp_CargarActionPerformed
        if ("".equals(Inp_cod.getText()) || "".equals(Inp_nombre.getText()) || "".equals(Inp_preu.getText()) || "".equals(Inp_iva.getText())) {
            JOptionPane.showMessageDialog(null, "Pro favor llene todos los campos");

        } else {
            mostrardatosPedi(cod_pedido.getText());
            Tabla_pedido.setEnabled(false);

            if (Tabla_Det_Ped.getSelectedRow() >= 0) {
                DefaultTableModel modeeli = (DefaultTableModel) Tabla_Det_Ped.getModel();
                modeeli.removeRow(Tabla_Det_Ped.getSelectedRow());

            }
            calcularTotalap();
            agregarproductosIng();
        }
    }//GEN-LAST:event_Inp_CargarActionPerformed

    private void Inp_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Inp_ModificarActionPerformed
        Object[] objectnu = new Object[8];
        double PreT;

        double Pre = Double.parseDouble(Inp_preu.getText());
        int Cant = Integer.valueOf(Inp_cantingresada.getValue().toString());

        PreT = Pre * Cant;

        objectnu[0] = Integer.parseInt(Inp_cod.getText());
        objectnu[1] = Inp_nombre.getText();
        objectnu[2] = Double.parseDouble(Inp_preu.getText());
        objectnu[3] = Inp_cantinicial.getText();
        objectnu[4] = Integer.valueOf(Inp_cantingresada.getValue().toString());
        objectnu[5] = Integer.valueOf(Inp_cantdefec.getValue().toString());
        objectnu[6] = Inp_iva.getText();
        objectnu[7] = PreT;

        for (int i = 0; i < 8; i++) {
            modelop.setValueAt(objectnu[i], Tabla_Ingre_Pro.getSelectedRow(), i);
            calcularTotalap();
        }
        Inp_Cargar.setEnabled(true);
        Ing_limpiar.setEnabled(true);
        Limpiar_ingreso();
    }//GEN-LAST:event_Inp_ModificarActionPerformed

    private void Tabla_Ingre_ProMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_Ingre_ProMouseClicked
        int fila = Tabla_Ingre_Pro.getSelectedRow();

        if (evt.getClickCount() == 2) {

            if (fila >= 0) {
                Inp_Modificar.setEnabled(true);
                SpinnerNumberModel ne = new SpinnerNumberModel();
                SpinnerNumberModel ne2 = new SpinnerNumberModel();

                Inp_cod.setText(Tabla_Ingre_Pro.getValueAt(fila, 0).toString());
                Inp_nombre.setText(Tabla_Ingre_Pro.getValueAt(fila, 1).toString());
                Inp_preu.setText(Tabla_Ingre_Pro.getValueAt(fila, 2).toString());
                int cantidad = Integer.parseInt(Tabla_Ingre_Pro.getValueAt(fila, 3).toString());
                Inp_cantinicial.setText(String.valueOf(cantidad));
                int canti = Integer.parseInt(Tabla_Ingre_Pro.getValueAt(fila, 4).toString());
                ne.setMaximum(cantidad);
                ne.setMinimum(0);
                ne.setStepSize(1);
                ne.setValue(canti);
                Inp_cantingresada.setModel(ne);
                Inp_cantingresada.setEnabled(true);
                int cantide = Integer.parseInt(Tabla_Ingre_Pro.getValueAt(fila, 5).toString());
                ne2.setMaximum(cantidad);
                ne2.setMinimum(0);
                ne2.setStepSize(1);
                ne2.setValue(cantide);
                Inp_cantdefec.setModel(ne2);
                Inp_iva.setText(Tabla_Ingre_Pro.getValueAt(fila, 6).toString());
                Inp_Cargar.setEnabled(false);
                Ing_limpiar.setEnabled(false);
                habilitaringreso();
            } else {
                JOptionPane.showMessageDialog(null, "No seleciono fila");
            }
        } else {
            //Limpiar();
        }
    }//GEN-LAST:event_Tabla_Ingre_ProMouseClicked

    private void Ing_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ing_limpiarActionPerformed
        Limpiar_ingreso();
    }//GEN-LAST:event_Ing_limpiarActionPerformed

    private void Inp_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Inp_EliminarActionPerformed

    }//GEN-LAST:event_Inp_EliminarActionPerformed

    private void Inp_cantdefecStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_Inp_cantdefecStateChanged


    }//GEN-LAST:event_Inp_cantdefecStateChanged

    private void Inp_cantingresadaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_Inp_cantingresadaStateChanged

        int c = Integer.parseInt(Inp_cantinicial.getText());
        int ci = Integer.parseInt(Inp_cantingresada.getValue().toString());
        int cd = c - ci;
        Inp_cantdefec.setValue(cd);

    }//GEN-LAST:event_Inp_cantingresadaStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Eliminar_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Eliminar_ActionPerformed
        modelop.removeRow(Tabla_Ingre_Pro.getSelectedRow());
        calcularTotalap();
    }//GEN-LAST:event_Eliminar_ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (Tabla_Ingre_Pro.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No Existen Datos", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            double sbt = Double.parseDouble(Sub_Total.getText());
            double tot = Double.parseDouble(Inp_precio.getText());

            double totalpagar = sbt + tot;

            DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
            separadoresPersonalizados.setDecimalSeparator('.');
            DecimalFormat formato1 = new DecimalFormat("#.00", separadoresPersonalizados);

            Total_.setText(String.valueOf(formato1.format(totalpagar)));
            Guardartodo.setEnabled(true);

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Tabla_Det_PedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_Det_PedMouseClicked
        int i = Tabla_Det_Ped.getSelectedRow();
        if (evt.getClickCount() == 2) {
            if (i >= 0) {

                Inp_Cargar.setEnabled(true);
                Ing_limpiar.setEnabled(true);
                Inp_cod.setText(Tabla_Det_Ped.getValueAt(i, 1).toString());
                Inp_cod.setEditable(false);
                Inp_nombre.setText(Tabla_Det_Ped.getValueAt(i, 2).toString());
                Inp_nombre.setEditable(false);
                Inp_preu.setText(Tabla_Det_Ped.getValueAt(i, 3).toString());
                Inp_preu.setEditable(false);

                int cantidad = Integer.parseInt(Tabla_Det_Ped.getValueAt(i, 4).toString());
                Inp_cantinicial.setText(String.valueOf(cantidad));
                SpinnerNumberModel n1 = new SpinnerNumberModel();
                n1.setMaximum(cantidad);
                n1.setMinimum(0);
                n1.setStepSize(1);
                n1.setValue(cantidad);
                SpinnerNumberModel n2 = new SpinnerNumberModel();
                n2.setMaximum(cantidad);
                n2.setMinimum(0);
                n2.setStepSize(1);
                Inp_cantingresada.setModel(n1);
                Inp_cantdefec.setModel(n2);

                String ivas =  Tabla_Det_Ped.getValueAt(i, 5).toString();
                System.out.println(""+ivas);
                if ("Si".equals(ivas)){
                    Inp_iva.setText("0.12");
                }else if ("No".equals(ivas)){
                    Inp_iva.setText("0");
                }
                Inp_precio.setText(Tabla_Det_Ped.getValueAt(i, 6).toString());

                habilitaringreso();
            } else {
                JOptionPane.showMessageDialog(null, "No seleciono fila");
            }
        } else {

        }
    }//GEN-LAST:event_Tabla_Det_PedMouseClicked

    private void Pedido_buscarpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Pedido_buscarpKeyReleased
        mostrardatosPedi(Pedido_buscarp.getText());
    }//GEN-LAST:event_Pedido_buscarpKeyReleased

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
            java.util.logging.Logger.getLogger(IngresoProdPedi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IngresoProdPedi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IngresoProdPedi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IngresoProdPedi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IngresoProdPedi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar_pedido;
    private javax.swing.JTextField Cl_bodeguero;
    private javax.swing.JTextField Cod_Rpi;
    private javax.swing.JTextField Cod_bodeguero;
    private javax.swing.JMenuItem Eliminar_;
    private javax.swing.JButton Guardartodo;
    private javax.swing.JTextField Ing_fechaa;
    private javax.swing.JButton Ing_limpiar;
    private javax.swing.JButton Inp_Cargar;
    private javax.swing.JButton Inp_Eliminar;
    private javax.swing.JButton Inp_Modificar;
    private javax.swing.JSpinner Inp_cantdefec;
    private javax.swing.JSpinner Inp_cantingresada;
    private javax.swing.JTextField Inp_cantinicial;
    private javax.swing.JTextField Inp_cod;
    private javax.swing.JTextField Inp_iva;
    private javax.swing.JTextField Inp_nombre;
    private javax.swing.JTextField Inp_precio;
    private javax.swing.JTextField Inp_preu;
    private javax.swing.JMenuItem Modifi;
    private javax.swing.JTextField Pedido_buscarp;
    private javax.swing.JTextField Ruc_proveedor;
    private javax.swing.JTextField Sub_Total;
    private javax.swing.JTable Tabla_Det_Ped;
    private javax.swing.JTable Tabla_Ingre_Pro;
    private javax.swing.JTable Tabla_pedido;
    private javax.swing.JTextField Total_;
    private javax.swing.JTextField cod_pedido;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator xd;
    private javax.swing.JSeparator xddd;
    // End of variables declaration//GEN-END:variables
}
