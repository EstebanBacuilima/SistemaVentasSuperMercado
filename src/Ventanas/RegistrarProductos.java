package Ventanas;

import djmeb.Productos;
import static djmeb.validaciones.ValidarLongitud;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RegistrarProductos extends javax.swing.JFrame {

    protected static String Imagen;
    protected static String Dest, Orig;

    public RegistrarProductos() {

        initComponents();
        setLocationRelativeTo(null);
        verifica_codb.setVisible(false);

        bloqinicio();
        Calendar calendar = new GregorianCalendar();
        Fechaactual.setText("" + calendar.get(Calendar.DAY_OF_MONTH) + "-" + (1 + calendar.get(Calendar.MARCH)) + "-" + calendar.get(Calendar.YEAR));
        mostrardatosPro("", 1);
        Pro_cod.setText(String.valueOf(codIncrement_pro()));
        Pro_cod.setEditable(false);

        ruxcs.setEditable(false);

        Modificar_producto.setEnabled(false);

        Cod_bodeguero1.setText(VentanaBodeguero.Bod_codigoFF.getText());
        Cl_bodeguero1.setText(VentanaBodeguero.Bod_cedulaFF.getText());
    }

// Codigo autoincrementar 
    public int codIncrement_pro() {
        int serie = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = cc.conectar();
        try {
            ps = cn.prepareStatement("SELECT MAX(Cod_pro) FROM registroproducto");
            rs = ps.executeQuery();
            while (rs.next()) {
                serie = rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            System.out.println("4 Error" + e.getMessage());
        }
        return serie;
    }

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

        TablaPro.setModel(modelo);

        // Acumular la sentencia
        String sql = "";
        if (estado == 1) {
            if (codp.equals("")) {

                sql = "SELECT * FROM registroproducto WHERE Pro_estado='1' ORDER BY cod_pro";

            } else {

                sql = "SELECT * FROM registroproducto WHERE pro_nombre like '%" + codp + "%' and Pro_estado='1' ORDER BY cod_pro";
            }
        } else if (estado == 2) {
            if (codp.equals("")) {

                sql = "SELECT * FROM registroproducto WHERE Pro_estado='2' ORDER BY cod_pro ";

            } else {

                sql = "SELECT * FROM registroproducto WHERE pro_nombre like '%" + codp + "%' and Pro_estado='2' ORDER BY cod_pro ";
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
            TablaPro.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }

    // AUTOINCREMENTARSE
    public int validarcion(int num) {
        int v = 0;

        if (num == 1) {
            String cb = Pro_Barras.getText();
            if (Pro_cod.getText().isEmpty() || Pro_Barras.getText().isEmpty() || Pro_nom.getText().isEmpty() || Pro_cat.getText().isEmpty()
                    || Pro_mar.getText().isEmpty() || Pro_cat.getText().isEmpty() || Pro_imagen.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "LLene los campos correctamente");
                v = 2;
            } else if (cb.length() != 13) {
                verifica_codb.setVisible(true);
                JOptionPane.showMessageDialog(null, "codigo de barras incorrecto");
                v = 2;
            } else {
                v = 1;
            }

        } else if (num == 2) {

        }

        return v;
    }

    //Metodos de limpiar campos 
    void Limpiar_producto() {

        Pro_Barras.setText("");
        Pro_nom.setText("");
        Pro_cat.setText("");
        Pro_mar.setText("");
        Pro_medida.setText("");
        Pro_precio.setText("");
        Pro_iva.setSelected(false);
        Pro_cant_exist.setValue(0);
        Pro_cant_min.setValue(0);
        Pro_cant_max.setValue(0);
        Pro_cod.setText(String.valueOf(codIncrement_pro()));
        Imagenes_.setIcon(null);
        Pro_imagen.setText("");
        ruxcs.setText("");
        ruxcs.setEditable(false);
    }

    void bloqinicio() {
        Pro_cat.setEditable(false);
        Pro_mar.setEditable(false);
        Pro_medida.setEditable(false);
        Pro_cant_exist.setEnabled(false);
        Pro_imagen.setEditable(false);
        Fechaactual.setEditable(false);
        Cl_bodeguero1.setEditable(false);
        Cod_bodeguero1.setEditable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        Eliminar = new javax.swing.JMenuItem();
        Actualizar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Pro_nom = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        B_producto = new javax.swing.JTextField();
        Pro_cod = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Buscar_producto = new javax.swing.JButton();
        Modificar_producto = new javax.swing.JButton();
        Crear_Producto = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaPro = new javax.swing.JTable();
        Pro_cant_min = new javax.swing.JSpinner();
        Pro_cant_exist = new javax.swing.JSpinner();
        Pro_precio = new javax.swing.JTextField();
        Mostrar_Producto = new javax.swing.JButton();
        Limpiar_Producto = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        Pro_medida = new javax.swing.JTextField();
        Crear_NCategoria = new javax.swing.JButton();
        Crear_Nmarca = new javax.swing.JButton();
        Pro_mar = new javax.swing.JTextField();
        Pro_cat = new javax.swing.JTextField();
        Pro_iva = new javax.swing.JCheckBox();
        Inactivos = new javax.swing.JCheckBox();
        Pro_cant_max = new javax.swing.JSpinner();
        cargar_imagen = new javax.swing.JButton();
        Imagenes_ = new javax.swing.JLabel();
        Pro_imagen = new javax.swing.JTextField();
        Fechaactual = new javax.swing.JTextField();
        ruxcs = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        Cod_bodeguero1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        Cl_bodeguero1 = new javax.swing.JTextField();
        xd = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        iva_si = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Crear_Nmedida = new javax.swing.JButton();
        Pro_Barras = new javax.swing.JTextField();
        verifica_codb = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Eliminar);

        Actualizar.setText("Modificar");
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Actualizar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Pro_nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pro_nomActionPerformed(evt);
            }
        });
        Pro_nom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Pro_nomKeyTyped(evt);
            }
        });
        jPanel1.add(Pro_nom, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 200, 25));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonSalirCrud.png"))); // NOI18N
        jButton3.setToolTipText("Regresar");
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 640, 40, 40));

        B_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_productoActionPerformed(evt);
            }
        });
        B_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                B_productoKeyReleased(evt);
            }
        });
        jPanel1.add(B_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, 210, 30));

        Pro_cod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pro_codActionPerformed(evt);
            }
        });
        jPanel1.add(Pro_cod, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 200, 25));

        jLabel9.setFont(new java.awt.Font("Agency FB", 1, 20)); // NOI18N
        jLabel9.setText("RUC Proveedor");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 120, 25));

        Buscar_producto.setText("Buscar");
        Buscar_producto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Buscar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buscar_productoActionPerformed(evt);
            }
        });
        jPanel1.add(Buscar_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 160, -1, 30));

        Modificar_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Modificar 40 x 40.png"))); // NOI18N
        Modificar_producto.setToolTipText("Actualizar");
        Modificar_producto.setBorderPainted(false);
        Modificar_producto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Modificar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Modificar_productoActionPerformed(evt);
            }
        });
        jPanel1.add(Modificar_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 590, 50, 50));

        Crear_Producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Gurdar 40 x 40.png"))); // NOI18N
        Crear_Producto.setToolTipText("Guardar");
        Crear_Producto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Crear_Producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Crear_ProductoActionPerformed(evt);
            }
        });
        jPanel1.add(Crear_Producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 590, 50, -1));

        TablaPro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablaPro.setComponentPopupMenu(jPopupMenu1);
        TablaPro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TablaPro);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 770, 370));

        Pro_cant_min.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        Pro_cant_min.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        Pro_cant_min.setEditor(new javax.swing.JSpinner.NumberEditor(Pro_cant_min, ""));
        jPanel1.add(Pro_cant_min, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 460, 70, 25));

        Pro_cant_exist.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        Pro_cant_exist.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        jPanel1.add(Pro_cant_exist, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 70, 25));

        Pro_precio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pro_precioActionPerformed(evt);
            }
        });
        jPanel1.add(Pro_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 200, 25));

        Mostrar_Producto.setText("Mostrar");
        Mostrar_Producto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Mostrar_Producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mostrar_ProductoActionPerformed(evt);
            }
        });
        jPanel1.add(Mostrar_Producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 160, -1, 30));

        Limpiar_Producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LimpiarCajero.png"))); // NOI18N
        Limpiar_Producto.setToolTipText("Limpiar");
        Limpiar_Producto.setBorderPainted(false);
        Limpiar_Producto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Limpiar_Producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Limpiar_ProductoActionPerformed(evt);
            }
        });
        jPanel1.add(Limpiar_Producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 590, 50, 50));

        jLabel7.setFont(new java.awt.Font("Agency FB", 1, 64)); // NOI18N
        jLabel7.setText("Registro Productos");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 450, -1));

        Pro_medida.setBackground(new java.awt.Color(240, 240, 240));
        Pro_medida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Pro_medidaMouseClicked(evt);
            }
        });
        Pro_medida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pro_medidaActionPerformed(evt);
            }
        });
        Pro_medida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Pro_medidaKeyTyped(evt);
            }
        });
        jPanel1.add(Pro_medida, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 110, 25));

        Crear_NCategoria.setText("Buscar");
        Crear_NCategoria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Crear_NCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Crear_NCategoriaActionPerformed(evt);
            }
        });
        jPanel1.add(Crear_NCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 70, 25));

        Crear_Nmarca.setText("Buscar");
        Crear_Nmarca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Crear_Nmarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Crear_NmarcaActionPerformed(evt);
            }
        });
        jPanel1.add(Crear_Nmarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 70, 25));

        Pro_mar.setEditable(false);
        Pro_mar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Pro_marMouseClicked(evt);
            }
        });
        jPanel1.add(Pro_mar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 110, 25));

        Pro_cat.setEditable(false);
        Pro_cat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Pro_catMouseClicked(evt);
            }
        });
        Pro_cat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pro_catActionPerformed(evt);
            }
        });
        jPanel1.add(Pro_cat, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 110, 25));

        Pro_iva.setBackground(new java.awt.Color(153, 255, 255));
        Pro_iva.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        Pro_iva.setText("SI");
        Pro_iva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pro_ivaActionPerformed(evt);
            }
        });
        jPanel1.add(Pro_iva, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 70, 25));

        Inactivos.setBackground(new java.awt.Color(153, 255, 255));
        Inactivos.setText("INACTIVOS ");
        Inactivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InactivosMouseClicked(evt);
            }
        });
        Inactivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InactivosActionPerformed(evt);
            }
        });
        jPanel1.add(Inactivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 580, -1, -1));

        Pro_cant_max.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        Pro_cant_max.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        Pro_cant_max.setEditor(new javax.swing.JSpinner.NumberEditor(Pro_cant_max, ""));
        jPanel1.add(Pro_cant_max, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 490, 70, 25));

        cargar_imagen.setText("Cargar Imagen");
        cargar_imagen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cargar_imagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cargar_imagenMouseClicked(evt);
            }
        });
        cargar_imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargar_imagenActionPerformed(evt);
            }
        });
        jPanel1.add(cargar_imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 540, 120, -1));

        Imagenes_.setBackground(new java.awt.Color(0, 255, 255));
        Imagenes_.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(Imagenes_, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 430, 130, 130));
        jPanel1.add(Pro_imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 540, 100, 25));

        Fechaactual.setBackground(new java.awt.Color(153, 255, 255));
        Fechaactual.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        Fechaactual.setBorder(null);
        Fechaactual.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jPanel1.add(Fechaactual, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 10, 140, 30));

        ruxcs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ruxcsMouseClicked(evt);
            }
        });
        ruxcs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ruxcsActionPerformed(evt);
            }
        });
        jPanel1.add(ruxcs, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 200, 25));

        jButton1.setBackground(new java.awt.Color(0, 204, 204));
        jButton1.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jButton1.setText("ªªªªªª");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 40, 30));

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel30.setText("Bodeguero:");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 60, 70, 20));
        jPanel1.add(Cod_bodeguero1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 60, 80, 20));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setText("Cl:");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 60, 70, 20));
        jPanel1.add(Cl_bodeguero1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 60, 150, 20));
        jPanel1.add(xd, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1300, 20));

        jLabel13.setFont(new java.awt.Font("Agency FB", 1, 20)); // NOI18N
        jLabel13.setText("Codigo");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 120, 40));

        jLabel11.setFont(new java.awt.Font("Agency FB", 1, 20)); // NOI18N
        jLabel11.setText("Codigo Barras");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 120, 40));

        jLabel2.setFont(new java.awt.Font("Agency FB", 1, 20)); // NOI18N
        jLabel2.setText("Nombre");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 120, 25));

        jLabel3.setFont(new java.awt.Font("Agency FB", 1, 20)); // NOI18N
        jLabel3.setText("Categoria");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 120, 25));

        jLabel4.setFont(new java.awt.Font("Agency FB", 1, 20)); // NOI18N
        jLabel4.setText("Marca");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 120, 25));

        jLabel6.setFont(new java.awt.Font("Agency FB", 1, 20)); // NOI18N
        jLabel6.setText("Medida");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 120, 25));

        jLabel12.setFont(new java.awt.Font("Agency FB", 1, 20)); // NOI18N
        jLabel12.setText("Precio");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 120, 25));

        iva_si.setFont(new java.awt.Font("Agency FB", 1, 20)); // NOI18N
        iva_si.setText("Iva");
        jPanel1.add(iva_si, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 120, 25));

        jLabel8.setFont(new java.awt.Font("Agency FB", 1, 20)); // NOI18N
        jLabel8.setText("Cantidad existente");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, 140, 25));

        jLabel27.setFont(new java.awt.Font("Agency FB", 1, 20)); // NOI18N
        jLabel27.setText("Cantidad minima");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, 120, 25));

        jLabel10.setFont(new java.awt.Font("Agency FB", 1, 20)); // NOI18N
        jLabel10.setText("Cantidad maxima");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, 120, 25));

        Crear_Nmedida.setText("Buscar");
        Crear_Nmedida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Crear_Nmedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Crear_NmedidaActionPerformed(evt);
            }
        });
        jPanel1.add(Crear_Nmedida, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, 70, 25));

        Pro_Barras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pro_BarrasActionPerformed(evt);
            }
        });
        Pro_Barras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Pro_BarrasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Pro_BarrasKeyTyped(evt);
            }
        });
        jPanel1.add(Pro_Barras, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 200, 25));

        verifica_codb.setBackground(new java.awt.Color(153, 255, 255));
        verifica_codb.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        verifica_codb.setForeground(new java.awt.Color(255, 0, 0));
        verifica_codb.setText("  13 digitos*");
        verifica_codb.setBorder(null);
        verifica_codb.setCaretColor(new java.awt.Color(153, 255, 255));
        verifica_codb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifica_codbActionPerformed(evt);
            }
        });
        jPanel1.add(verifica_codb, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 163, 80, -1));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPriPOO(1).png"))); // NOI18N
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 10, 200, 60));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setText("Eliminar");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 640, 50, 20));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel14.setText("Modificar");
        jLabel14.setToolTipText("");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 640, 60, 20));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel15.setText("Guardar");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 640, -1, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Limpiar_ProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Limpiar_ProductoActionPerformed
        Limpiar_producto();
    }//GEN-LAST:event_Limpiar_ProductoActionPerformed

    private void Mostrar_ProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mostrar_ProductoActionPerformed
        mostrardatosPro("", 1);
        if (Inactivos.isSelected()) {
            mostrardatosPro("", 2);
        }
    }//GEN-LAST:event_Mostrar_ProductoActionPerformed

    private void Pro_precioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pro_precioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pro_precioActionPerformed

    private void TablaProMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProMouseClicked

        if (Inactivos.isSelected()) {
            Limpiar_producto();
        } else {

            int i = TablaPro.getSelectedRow();

            if (evt.getClickCount() == 1) {
                Limpiar_producto();
                Crear_Producto.setEnabled(true);
                Modificar_producto.setEnabled(false);

            } else if (evt.getClickCount() == 2) {

                if (i >= 0) {
                    Modificar_producto.setEnabled(false);
                    Crear_Producto.setEnabled(false);

                    Pro_cod.setText(String.valueOf(TablaPro.getValueAt(i, 0)));
                    Pro_cod.setEnabled(false);
                    Pro_Barras.setText(String.valueOf(TablaPro.getValueAt(i, 1)));
                    Pro_nom.setText(String.valueOf(TablaPro.getValueAt(i, 2)));
                    Pro_cat.setText(String.valueOf(TablaPro.getValueAt(i, 3)));
                    Pro_mar.setText(String.valueOf(TablaPro.getValueAt(i, 4)));
                    Pro_medida.setText(String.valueOf(TablaPro.getValueAt(i, 5)));
                    Pro_precio.setText(String.valueOf(TablaPro.getValueAt(i, 6)));
                    String cantiex = String.valueOf(TablaPro.getValueAt(i, 7));
                    Pro_cant_exist.setValue(Integer.parseInt(cantiex));
                    String cantimin = String.valueOf(TablaPro.getValueAt(i, 8));
                    Pro_cant_min.setValue(Integer.parseInt(cantimin));
                    String cantimax = String.valueOf(TablaPro.getValueAt(i, 9));
                    Pro_cant_max.setValue(Integer.parseInt(cantimax));

                    String ivas = String.valueOf(TablaPro.getValueAt(i, 10));
                    if ("Si".equals(ivas)) {
                        Pro_iva.setSelected(true);
                    } else {
                        Pro_iva.setSelected(false);
                    }

                    String Ima = String.valueOf(TablaPro.getValueAt(i, 11));
                    Pro_imagen.setText(Ima);
                    try {
                        Orig = "src/Imagenes_Productos/" + Pro_imagen.getText();
                        ImageIcon icon = new ImageIcon(Orig);
                        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(Imagenes_.getWidth(), Imagenes_.getHeight(), Image.SCALE_DEFAULT));
                        Imagenes_.setText(null);
                        Imagenes_.setIcon(icono);

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "La imagen no se encuentra en la base de datos \n  por favor vuelva a cargar la imagen de producto");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No seleciono fila");
                }
            } else {

            }
        }
    }//GEN-LAST:event_TablaProMouseClicked

    private void Crear_ProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Crear_ProductoActionPerformed
        CrearProducto();
    }//GEN-LAST:event_Crear_ProductoActionPerformed

    void CrearProducto() {
//        if(validarcion(1)=2) { 
//
//            JOptionPane.showMessageDialog(null, "LLene los campos corectamente");

        DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
        separadoresPersonalizados.setDecimalSeparator('.');
        DecimalFormat formato1 = new DecimalFormat("#.00", separadoresPersonalizados);

        if (validarcion(1) == 1) {
            
            verifica_codb.setVisible(false);
            PreparedStatement pst = null;
            String sql = "INSERT INTO registroproducto (Cod_Pro, Pro_cbarras, Pro_Nombre, Pro_Categoria, Pro_Marca,Pro_Medida, Pro_Precio, Pro_Cant_Exi, Pro_Cant_Min,Pro_Cant_Max, Pro_iva, Pro_estado, Pro_imagen,ruc_provedor) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            String ruc = ruxcs.getText();
            int cod = Integer.parseInt(Pro_cod.getText());
            String cbarras = Pro_Barras.getText();
            String nom = Pro_nom.getText();
            int categoria = Integer.parseInt(Pro_cat.getText());
            int marca = Integer.parseInt(Pro_mar.getText());
            int tipomedida = Integer.parseInt(Pro_medida.getText());

            //String tipomedida = Pro_medida.getText();
            //unimos los numeros ingresados con la unidad de medida
            
            //
            double precio = Double.parseDouble(Pro_precio.getText());
            String iva = "";
            if (Pro_iva.isSelected()) {
                iva = "Si";
            } else {
                iva = "No";
            }
            int cant_exis = Integer.valueOf(Pro_cant_exist.getValue().toString());
            int cant_mini = Integer.valueOf(Pro_cant_min.getValue().toString());
            int cant_max = Integer.valueOf(Pro_cant_max.getValue().toString());
            int estado = 1;
            String imagen = Imagen;

            
            
            //Creamos el objeto Producto
            Productos newproducto = new Productos(ruc, cod, cbarras, nom, categoria, marca, tipomedida, precio, cant_exis, cant_mini, cant_max, iva, estado, imagen);

            try {
                
                double precioV = newproducto.getPrecio();
                formato1.format(precioV);
                
                pst = cn.prepareStatement(sql);
                pst.setInt(1, newproducto.getCod_producto());
                pst.setString(2, newproducto.getCod_barras());
                pst.setString(3, newproducto.getNombre());
                pst.setInt(4, newproducto.getCategoria());
                pst.setInt(5, newproducto.getMarca());
                pst.setInt(6, newproducto.getMedida());
                pst.setDouble(7, precioV);
                pst.setInt(8, newproducto.getCant_existente());
                pst.setInt(9, newproducto.getCant_minima());
                pst.setInt(10, newproducto.getCant_maxima());
                pst.setString(11, newproducto.getIva());
                pst.setInt(12, newproducto.getEstado());
                pst.setString(13, newproducto.getImagenes());
                pst.setString(14, newproducto.getRuc());

                pst.executeUpdate();
                Limpiar_producto();
                JOptionPane.showMessageDialog(null, "Producto Guardado");
                mostrardatosPro("", 1);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al guardar" + e);
                Limpiar_producto();
            }
        }
    }

//    void CrearProducto() {
//        if (Pro_cod.getText().isEmpty() || Pro_Barras.getText().isEmpty() || Pro_nom.getText().isEmpty() || Pro_cat.getText().isEmpty()
//                || Pro_mar.getText().isEmpty() || Pro_cat.getText().isEmpty()) {
//
//            JOptionPane.showMessageDialog(null, "LLene los campos corectamente");
//
//        } else {
////            try {
////
////                Path Destino = Paths.get(Dest);
////                Path Origen = Paths.get(Orig);
////                Files.copy(Origen, Destino, StandardCopyOption.REPLACE_EXISTING);
////
////            } catch (IOException e) {
////                Logger.getLogger(VentanaBodeguero.class.getName()).log(Level.SEVERE, null, e);
////            }
//            PreparedStatement pst = null;
//
//            String sql = "INSERT INTO registroproducto (Cod_Pro, Pro_cbarras, Pro_Nombre, Pro_Categoria, Pro_Marca,Pro_Medida, Pro_Precio, Pro_Cant_Exi, Pro_Cant_Min,Pro_Cant_Max, Pro_iva, Pro_estado, Pro_imagen,ruc_provedor) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//
//            String ruc = ruxcs.getText();
//            int cod = Integer.parseInt(Pro_cod.getText());
//            String cbarras = Pro_Barras.getText();
//            String nom = Pro_nom.getText();
//            int categoria = Integer.parseInt(Pro_cat.getText());
//            int marca = Integer.parseInt(Pro_mar.getText());
//            String med = Pro_medida.getText();
//            String tipomedida = Pro_tipomedida.getSelectedItem().toString();
//            //unimos los numeros ingresados con la unidad de medida
//            String medida = med + " /" + tipomedida;
//            double precio = Double.parseDouble(Pro_precio.getText());
//            String iva = "";
//            if (Pro_iva.isSelected()) {
//                iva = "Si";
//            } else {
//                iva = "No";
//            }
//            int cant_exis = Integer.valueOf(Pro_cant_exist.getValue().toString());
//            int cant_mini = Integer.valueOf(Pro_cant_min.getValue().toString());
//            int cant_max = Integer.valueOf(Pro_cant_max.getValue().toString());
//            int estado = 1;
//            String imagen = Imagen;
//
//            //Creamos el objeto Producto
//            Productos newproducto = new Productos(ruc, cod, cbarras, nom, categoria, marca, medida, precio, cant_exis, cant_mini, cant_max, iva, estado, imagen);
//
//            try {
//
//                pst = cn.prepareStatement(sql);
//                pst.setInt(1, newproducto.getCod_producto());
//                pst.setString(2, newproducto.getCod_barras());
//                pst.setString(3, newproducto.getNombre());
//                pst.setInt(4, newproducto.getCategoria());
//                pst.setInt(5, newproducto.getMarca());
//                pst.setString(6, newproducto.getMedida());
//                pst.setDouble(7, newproducto.getPrecio());
//                pst.setInt(8, newproducto.getCant_existente());
//                pst.setInt(9, newproducto.getCant_minima());
//                pst.setInt(10, newproducto.getCant_maxima());
//                pst.setString(11, newproducto.getIva());
//                pst.setInt(12, newproducto.getEstado());
//                pst.setString(13, newproducto.getImagenes());
//                pst.setString(14, newproducto.getRuc());
//
//                pst.executeUpdate();
//                Limpiar_producto();
//                JOptionPane.showMessageDialog(null, "Producto Guardado");
//                mostrardatosPro("", 1);
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "Error al guardar" + e);
//                Limpiar_producto();
//            }
//        }
//    }
    private void Modificar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Modificar_productoActionPerformed
        try {
            String nimagen;
            int cant_ex = Integer.valueOf(Pro_cant_exist.getValue().toString());
            int cant_mi = Integer.valueOf(Pro_cant_min.getValue().toString());
            int cant_ma = Integer.valueOf(Pro_cant_max.getValue().toString());
            int medida = Integer.valueOf(Pro_medida.getText().toString());

            //String medida = Pro_medida.getText();
            String iva = "";
            if (Pro_iva.isSelected()) {
                iva = "Si";
            } else {
                iva = "No";
            }
            nimagen = Pro_imagen.getText();

            PreparedStatement pst = cn.prepareStatement("UPDATE registroproducto SET Pro_cbarras='" + Pro_Barras.getText() + "', Pro_Nombre='" + Pro_nom.getText() + "', Pro_Categoria='" + Pro_cat.getText() + "',Pro_Marca='" + Pro_mar.getText() + "',Pro_Medida='" + medida + "',Pro_Precio='" + Pro_precio.getText() + "',Pro_Cant_Exi='" + cant_ex + "',Pro_Cant_Min='" + cant_mi + "',Pro_Cant_Max='" + cant_ma + "',Pro_iva='" + iva + "',Pro_imagen='" + nimagen + "' WHERE Cod_Pro='" + Pro_cod.getText() + "'");
            pst.executeUpdate();
            mostrardatosPro("", 1);
            Limpiar_producto();
            JOptionPane.showMessageDialog(null, "Actualizado");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Limpiar_producto();
        }
        //        String cod =B_producto.getText();
        //        Modificar(cod);
    }//GEN-LAST:event_Modificar_productoActionPerformed

    private void Buscar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buscar_productoActionPerformed

        mostrardatosPro(B_producto.getText(), 1);
        if (Inactivos.isSelected()) {
            mostrardatosPro(B_producto.getText(), 2);
        }

    }//GEN-LAST:event_Buscar_productoActionPerformed

    private void Pro_codActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pro_codActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pro_codActionPerformed

    private void B_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_productoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_productoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void Pro_nomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pro_nomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pro_nomActionPerformed

    private void Pro_medidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pro_medidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pro_medidaActionPerformed

    private void Pro_catActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pro_catActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pro_catActionPerformed

    private void Pro_ivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pro_ivaActionPerformed

    }//GEN-LAST:event_Pro_ivaActionPerformed

    private void Crear_NmarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Crear_NmarcaActionPerformed

        Tabla_marcas d = new Tabla_marcas();
        d.setLocation(450, 200);
        d.setVisible(true);
    }//GEN-LAST:event_Crear_NmarcaActionPerformed

    private void cargar_imagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargar_imagenActionPerformed

        String nomimagen = cargarimagen();
        Pro_imagen.setText(nomimagen);


    }//GEN-LAST:event_cargar_imagenActionPerformed
    public String cargarimagen() {
        JFileChooser file = new JFileChooser();
        file.showOpenDialog(this);
        File archivo = file.getSelectedFile();
        Dest = "src\\Imagenes_Productos\\" + archivo.getName();
        Orig = archivo.getPath();
        Imagen = archivo.getName();
        String nimagen = Imagen;
        ImageIcon icon = new ImageIcon(Orig);
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(Imagenes_.getWidth(), Imagenes_.getHeight(), Image.SCALE_DEFAULT));
        Imagenes_.setText(null);
        Imagenes_.setIcon(icono);

        try {

            Path Destino = Paths.get(Dest);
            Path Origen = Paths.get(Orig);
            Files.copy(Origen, Destino, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            Logger.getLogger(RegistrarProductos.class.getName()).log(Level.SEVERE, null, e);
        }
        return nimagen;
    }
    private void InactivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InactivosMouseClicked


    }//GEN-LAST:event_InactivosMouseClicked

    private void InactivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InactivosActionPerformed

        if (Inactivos.isSelected()) {
            mostrardatosPro("", 2);

        } else {
            mostrardatosPro("", 1);
        }

    }//GEN-LAST:event_InactivosActionPerformed

    private void cargar_imagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cargar_imagenMouseClicked

    }//GEN-LAST:event_cargar_imagenMouseClicked

    private void Pro_nomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Pro_nomKeyTyped
//        char c = evt.getKeyChar();
//        if ((c<'a'|| c>'z')&&(c<'A'|| c>'Z')) evt.consume();
    }//GEN-LAST:event_Pro_nomKeyTyped

    private void Crear_NCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Crear_NCategoriaActionPerformed
        String codp = "";
        Tabla_categorias c = new Tabla_categorias();
        c.setLocation(450, 200);
        c.setVisible(true);

    }//GEN-LAST:event_Crear_NCategoriaActionPerformed

    private void ruxcsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ruxcsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ruxcsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        RProveedor1 rp = new RProveedor1();
        rp.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Crear_NmedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Crear_NmedidaActionPerformed
        Tabla_medidas med = new Tabla_medidas();
        med.setLocation(450, 200);
        med.setVisible(true);
    }//GEN-LAST:event_Crear_NmedidaActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        if (Inactivos.isSelected()) {
            JOptionPane.showMessageDialog(null, "No se puede realizar dicha accion mientras se muestran los datos inactivos");
        } else {
            int fila = TablaPro.getSelectedRow();
            String codpr = "";
            codpr = TablaPro.getValueAt(fila, 0).toString();
            int estado = 2;
            try {

                PreparedStatement pst = cn.prepareStatement("UPDATE registroproducto SET Pro_estado='" + estado + "' WHERE Cod_Pro='" + codpr + "'");

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se Elimino Correctamente");
                Limpiar_producto();
                mostrardatosPro("", 1);

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "No seleciono fila" + e);
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_EliminarActionPerformed

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        if (Inactivos.isSelected()) {
            JOptionPane.showMessageDialog(null, "No se puede realizar dicha accion mientras  se muestran los datos inactivos");
        } else {

            int i = TablaPro.getSelectedRow();
            if (i >= 0) {
                Modificar_producto.setEnabled(true);
                Crear_Producto.setEnabled(false);

                Pro_cod.setText(String.valueOf(TablaPro.getValueAt(i, 0)));
                Pro_cod.setEnabled(false);
                Pro_Barras.setText(String.valueOf(TablaPro.getValueAt(i, 1)));
                Pro_nom.setText(String.valueOf(TablaPro.getValueAt(i, 2)));
                Pro_cat.setText(String.valueOf(TablaPro.getValueAt(i, 3)));
                Pro_mar.setText(String.valueOf(TablaPro.getValueAt(i, 4)));
                Pro_medida.setText(String.valueOf(TablaPro.getValueAt(i, 5)));
                Pro_precio.setText(String.valueOf(TablaPro.getValueAt(i, 6)));
                String cantiex = String.valueOf(TablaPro.getValueAt(i, 7));
                Pro_cant_exist.setValue(Integer.parseInt(cantiex));
                String cantimin = String.valueOf(TablaPro.getValueAt(i, 8));
                Pro_cant_min.setValue(Integer.parseInt(cantimin));
                String cantimax = String.valueOf(TablaPro.getValueAt(i, 9));
                Pro_cant_max.setValue(Integer.parseInt(cantimax));

                String ivas = String.valueOf(TablaPro.getValueAt(i, 10));
                if ("Si".equals(ivas)) {
                    Pro_iva.setSelected(true);
                } else {
                    Pro_iva.setSelected(false);
                }

                String Ima = String.valueOf(TablaPro.getValueAt(i, 11));
                Pro_imagen.setText(Ima);
                try {
                    Orig = "src/Imagenes_Productos/" + Pro_imagen.getText();
                    ImageIcon icon = new ImageIcon(Orig);
                    ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(Imagenes_.getWidth(), Imagenes_.getHeight(), Image.SCALE_DEFAULT));
                    Imagenes_.setText(null);
                    Imagenes_.setIcon(icono);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "La imagen no se encuentra en la base de datos \n  por favor vuelva a cargar la imagen del producto");
                }

            } else {
                JOptionPane.showMessageDialog(null, "No seleciono fila");

            }
        }
    }//GEN-LAST:event_ActualizarActionPerformed

    private void verifica_codbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verifica_codbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_verifica_codbActionPerformed

    private void Pro_medidaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Pro_medidaKeyTyped
//        char c = evt.getKeyChar();
//        if (c<'0'|| c>'9' && c !='x') evt.consume();
    }//GEN-LAST:event_Pro_medidaKeyTyped

    private void B_productoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B_productoKeyReleased
        if (Inactivos.isSelected()) {
            mostrardatosPro(B_producto.getText(), 2);
        } else {
            mostrardatosPro(B_producto.getText(), 1);
        }
    }//GEN-LAST:event_B_productoKeyReleased

    private void Pro_BarrasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Pro_BarrasKeyTyped

        ValidarLongitud(Pro_Barras.getText(), 13, evt);
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }

    }//GEN-LAST:event_Pro_BarrasKeyTyped

    private void Pro_BarrasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Pro_BarrasKeyReleased
        char c = evt.getKeyChar();

        if (Pro_Barras.getText().length() == 13) {

            verifica_codb.setVisible(false);

        } else {

            verifica_codb.setVisible(true);
        }
    }//GEN-LAST:event_Pro_BarrasKeyReleased

    private void Pro_BarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pro_BarrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pro_BarrasActionPerformed

    private void ruxcsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ruxcsMouseClicked
 
        if (evt.getClickCount() == 1) {
            
            jButton1.doClick();
            
        } else {

        }
    }//GEN-LAST:event_ruxcsMouseClicked

    private void Pro_catMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pro_catMouseClicked
        if (evt.getClickCount() == 1) {
            
            Crear_NCategoria.doClick();
            
        } else {

        }
    }//GEN-LAST:event_Pro_catMouseClicked

    private void Pro_marMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pro_marMouseClicked
       if (evt.getClickCount() == 1) {
            
            Crear_Nmarca.doClick();
            
        } else {

        }
    }//GEN-LAST:event_Pro_marMouseClicked

    private void Pro_medidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pro_medidaMouseClicked
        if (evt.getClickCount() == 1) {
            
            Crear_Nmedida.doClick();
            
        } else {

        }
    }//GEN-LAST:event_Pro_medidaMouseClicked

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
            java.util.logging.Logger.getLogger(RegistrarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Actualizar;
    private javax.swing.JTextField B_producto;
    private javax.swing.JButton Buscar_producto;
    private javax.swing.JTextField Cl_bodeguero1;
    private javax.swing.JTextField Cod_bodeguero1;
    private javax.swing.JButton Crear_NCategoria;
    private javax.swing.JButton Crear_Nmarca;
    private javax.swing.JButton Crear_Nmedida;
    private javax.swing.JButton Crear_Producto;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JTextField Fechaactual;
    private javax.swing.JLabel Imagenes_;
    private javax.swing.JCheckBox Inactivos;
    private javax.swing.JButton Limpiar_Producto;
    private javax.swing.JButton Modificar_producto;
    private javax.swing.JButton Mostrar_Producto;
    private javax.swing.JTextField Pro_Barras;
    private javax.swing.JSpinner Pro_cant_exist;
    private javax.swing.JSpinner Pro_cant_max;
    private javax.swing.JSpinner Pro_cant_min;
    public static javax.swing.JTextField Pro_cat;
    private javax.swing.JTextField Pro_cod;
    private javax.swing.JTextField Pro_imagen;
    private javax.swing.JCheckBox Pro_iva;
    public static javax.swing.JTextField Pro_mar;
    public static javax.swing.JTextField Pro_medida;
    private javax.swing.JTextField Pro_nom;
    private javax.swing.JTextField Pro_precio;
    private javax.swing.JTable TablaPro;
    private javax.swing.JButton cargar_imagen;
    private javax.swing.JLabel iva_si;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTextField ruxcs;
    private javax.swing.JTextField verifica_codb;
    private javax.swing.JSeparator xd;
    // End of variables declaration//GEN-END:variables
    Conexion cc = new Conexion();
    Connection cn = cc.conectar();

}
