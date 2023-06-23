package Ventanas;

import djmeb.Clientes;
import djmeb.validaciones;
import static djmeb.validaciones.ValidarLetrNum;
import static djmeb.validaciones.VerificarEmail;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ingresodecliente extends javax.swing.JFrame {

    Date fecha;
    LocalDate fechaactual = LocalDate.now();

    public ingresodecliente() {
        initComponents();
        mostrardatos();
        comd.setText(String.valueOf(codIncrement()));
        comd.setEditable(false);
        setLocationRelativeTo(null);
        EdadCalculada.setEditable(false);

    }

    public void Limpiar() {

        comd.setText(String.valueOf(codIncrement()));
        comd.setEditable(false);
        cedulatxt.setText("");
        cedulatxt.setEnabled(true);
        nombretxt.setText("");
        apellidotxt.setText("");
        direcciontxa.setText("");
        cedulatxt.setText("");
        correotre.setText("");
        telefono.setText("");
        EdadCalculada.setText("");
        genero.clearSelection();
        jchofachanaci.setCalendar(null);

    }

    void mostrardatos() {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        //DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("N");
        modelo.addColumn("CEDULA");
        modelo.addColumn("NOMBRE ");
        modelo.addColumn("APELLIDO");
        modelo.addColumn("EDAD ");
        modelo.addColumn("CORREO");
        modelo.addColumn("DIRECCION");
        modelo.addColumn("GENERO ");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("FECHA NACIMIENTO");
        jTable1.setModel(modelo);

        // Acumular la sentencia
        String sql = "SELECT C.id_clientes, P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono,C.fecha_nac_cli  FROM persona P, clientes C WHERE P.persona_id = C.persona_id  AND C.estado_cliente = 'A'";

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

    public String eleccionGenero() {

        String genero = null;
        if (hom.isSelected()) {
            genero = "Masculino";
        }
        if (muje.isSelected()) {
            genero = "Femenino";
        }
        return genero;
    }

    public int codIncrementPersona() {

        int serie = 1;

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = cc.conectar();

        try {

            ps = cn.prepareStatement("SELECT MAX(persona_id) FROM persona");
            rs = ps.executeQuery();

            while (rs.next()) {
                serie = rs.getInt(1) + 1;
            }

        } catch (Exception e) {

            System.out.println("Error" + e.getMessage());
        }

        return serie;

    }

    public int codIncrement() {

        int serie = 1;
        //String dato = "CAJ0";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = cc.conectar();

        try {

            ps = cn.prepareStatement("SELECT MAX(id_clientes) FROM clientes");
            rs = ps.executeQuery();

            while (rs.next()) {

                serie = rs.getInt(1) + 1;
            }

        } catch (Exception e) {

            System.out.println("Error" + e.getMessage());
        }

        return serie;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        ACTUALIZAR = new javax.swing.JMenuItem();
        ELIMINAR = new javax.swing.JMenuItem();
        genero = new javax.swing.ButtonGroup();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nombretxt = new javax.swing.JTextField();
        cedulatxt = new javax.swing.JTextField();
        apellidotxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        correotre = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        direcciontxa = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        BUSCARCED = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        AvisoCorreo1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        hom = new javax.swing.JRadioButton();
        muje = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        telefono = new javax.swing.JFormattedTextField();
        jchofachanaci = new com.toedter.calendar.JDateChooser();
        jLabel31 = new javax.swing.JLabel();
        dateliminados = new javax.swing.JCheckBox();
        jLabel32 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        comd = new javax.swing.JTextField();
        EdadCalculada = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        ACTUALIZAR.setText("ACTUALIZAR");
        ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ACTUALIZARActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ACTUALIZAR);

        ELIMINAR.setText("ELIMINAR");
        ELIMINAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ELIMINARActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ELIMINAR);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel3.setText("Mostrar");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 140, -1, 20));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel4.setText("Nombre");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel5.setText("Apellido");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, 40));

        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel6.setText("Telefono");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, 40));

        nombretxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombretxtActionPerformed(evt);
            }
        });
        nombretxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombretxtKeyTyped(evt);
            }
        });
        getContentPane().add(nombretxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 200, 24));

        cedulatxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cedulatxtKeyTyped(evt);
            }
        });
        getContentPane().add(cedulatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 200, 24));

        apellidotxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidotxtKeyTyped(evt);
            }
        });
        getContentPane().add(apellidotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 200, 24));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonModificar.png"))); // NOI18N
        jButton1.setToolTipText("Modificar");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setDefaultCapable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 550, 120, 120));

        correotre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                correotreActionPerformed(evt);
            }
        });
        correotre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                correotreKeyReleased(evt);
            }
        });
        getContentPane().add(correotre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 460, 200, 24));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonEliminar.png"))); // NOI18N
        jButton3.setToolTipText("Eliminar");
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setDefaultCapable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 550, 110, 120));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonCrear.png"))); // NOI18N
        jButton2.setToolTipText("Agregar");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setDefaultCapable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 550, 110, 120));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, 810, 250));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonSalirCrud.png"))); // NOI18N
        jButton4.setToolTipText("Salir");
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setDefaultCapable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 670, 50, 50));

        direcciontxa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                direcciontxaKeyTyped(evt);
            }
        });
        getContentPane().add(direcciontxa, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 400, 200, 24));

        jLabel9.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel9.setText("Direccion");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, 20));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconoBuscar.png"))); // NOI18N
        jButton5.setToolTipText("Buscar");
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setDefaultCapable(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 50, 50));

        BUSCARCED.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUSCARCEDActionPerformed(evt);
            }
        });
        BUSCARCED.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BUSCARCEDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BUSCARCEDKeyReleased(evt);
            }
        });
        getContentPane().add(BUSCARCED, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 260, 30));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IconoMostrar.png"))); // NOI18N
        jButton6.setToolTipText("Mostrar");
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setDefaultCapable(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 160, 50, 60));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel2.setText("CLIENTES");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel10.setText("Crear");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 670, -1, 20));

        jLabel11.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        jLabel11.setText("Modificar");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 670, -1, 20));

        jLabel7.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel7.setText("Codigo");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 30));

        AvisoCorreo1.setForeground(new java.awt.Color(255, 0, 0));
        AvisoCorreo1.setText("Email Incorrecto *");
        getContentPane().add(AvisoCorreo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 430, 110, 30));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPriPOO(1).png"))); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 210, 70));

        hom.setBackground(new java.awt.Color(153, 204, 255));
        genero.add(hom);
        hom.setText("Masculino");
        hom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homActionPerformed(evt);
            }
        });
        getContentPane().add(hom, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 510, -1, -1));

        muje.setBackground(new java.awt.Color(153, 204, 255));
        genero.add(muje);
        muje.setText("Femenino");
        getContentPane().add(muje, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 510, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel14.setText("Correo");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, -1, 30));

        jLabel15.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel15.setText("Genero");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, -1, 20));

        try {
            telefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###) ###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonoActionPerformed(evt);
            }
        });
        getContentPane().add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 200, 24));

        jchofachanaci.setDateFormatString("yyyy MM dd");
        jchofachanaci.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jchofachanaciKeyTyped(evt);
            }
        });
        getContentPane().add(jchofachanaci, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 560, 140, 30));
        //jchofachanaci.getDateEditor().addPropertyChangeListener(new java.beans.PropertyChangeListener()){
            //public void propertyChange(java.beans.PropertyChangeEvent){
                //edad();
                //Edad_cel.setText(ed);
                //
                //
                //}
            //});

    jLabel31.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
    jLabel31.setText("Edad");
    getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 620, -1, 30));

    dateliminados.setBackground(new java.awt.Color(153, 204, 255));
    dateliminados.setText("Ver Eliminados");
    dateliminados.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            dateliminadosMouseClicked(evt);
        }
    });
    dateliminados.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            dateliminadosActionPerformed(evt);
        }
    });
    getContentPane().add(dateliminados, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 480, -1, 20));

    jLabel32.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
    jLabel32.setText("Fecha Nacimiento");
    getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 560, -1, 20));
    getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 660, 20, 20));

    jLabel8.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
    jLabel8.setText("Cedula");
    getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, 20));

    comd.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            comdActionPerformed(evt);
        }
    });
    getContentPane().add(comd, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 60, 30));
    getContentPane().add(EdadCalculada, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 620, 60, 30));

    jButton13.setBackground(new java.awt.Color(153, 204, 255));
    jButton13.setForeground(new java.awt.Color(153, 204, 255));
    jButton13.setBorderPainted(false);
    jButton13.setContentAreaFilled(false);
    jButton13.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton13ActionPerformed(evt);
        }
    });
    getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 620, 30, 30));

    jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ClientesIngreso.png"))); // NOI18N
    getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 130, 130));

    jLabel12.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
    jLabel12.setText("Eliminar");
    getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 660, -1, 30));

    jPanel1.setBackground(new java.awt.Color(153, 204, 255));
    getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 740));

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombretxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombretxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombretxtActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (EdadCalculada.getText().isEmpty() || cedulatxt.getText().isEmpty() || nombretxt.getText().isEmpty() || apellidotxt.getText().isEmpty() || telefono.getText().isEmpty() || VerificarEmail(correotre.getText()) == correotre.getText().isEmpty() || direcciontxa.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "LLene los campos corectamente");

        } else {
            try {
                fecha = jchofachanaci.getDate();
                System.out.println(fecha);
                SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
                String fechan = formato.format(fecha);
                System.out.println(fechan);
                String fec[] = fechan.split("/");
                LocalDate fechanacimiento = LocalDate.of(Integer.parseInt(fec[0]), Integer.parseInt(fec[1]), Integer.parseInt(fec[2]));
                System.out.println(fechanacimiento);

                String edadCal = EdadCalculada.getText().trim();
                int edad = Integer.parseInt(edadCal);

                PreparedStatement pst2 = cn.prepareStatement("UPDATE persona SET persona_nombre='" + nombretxt.getText() + "', persona_apellido='" + apellidotxt.getText() + "',persona_edad='" + edad + "',persona_correo='" + correotre.getText() + "',persona_direccion='" + direcciontxa.getText() + "',persona_genero='" + eleccionGenero() + "',persona_telefono='" + telefono.getText() + "' WHERE persona_cedula='" + cedulatxt.getText() + "'");
                PreparedStatement pst3 = cn.prepareStatement("UPDATE clientes SET fecha_nac_cli='" + fechanacimiento + "' WHERE id_clientes = '" + comd.getText() + "'");
                pst2.executeUpdate();
                pst3.executeUpdate();
                mostrardatos();
                Limpiar();
                JOptionPane.showMessageDialog(null, "Actualizado");

            } catch (Exception e) {
                System.out.println(e.getMessage());
                Limpiar();
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        mostrardatos();

        int fila = 0;

        for (int i = 0; fila < jTable1.getRowCount(); fila++) {

            jTable1.changeSelection(i, 0, false, false);

            comd.setText(jTable1.getValueAt(fila, 0).toString());
            cedulatxt.setText(jTable1.getValueAt(fila, 1).toString());
            cedulatxt.setEditable(false);
            nombretxt.setText(jTable1.getValueAt(fila, 2).toString());
            apellidotxt.setText(jTable1.getValueAt(fila, 3).toString());
            telefono.setText(jTable1.getValueAt(fila, 8).toString());
            direcciontxa.setText(jTable1.getValueAt(fila, 6).toString());
            correotre.setText(jTable1.getValueAt(fila, 5).toString());
            String genero = String.valueOf(jTable1.getValueAt(fila, 7).toString());
            EdadCalculada.setText(jTable1.getValueAt(fila, 4).toString());
//                String edad = String.valueOf(jTable1.getValueAt(fila, 4));
//                EdadSpiner.setValue(Integer.parseInt(edad));
            String fecha = jTable1.getValueAt(fila, 9).toString();
            //creamos el formato en el que deseamos mostrar la fecha
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            //creamos una variable tipo Date y la ponemos NULL
            Date fechaN = jchofachanaci.getDate();
            try {
                //parseamos de String a Date usando el formato
                fechaN = formatoDelTexto.parse(fecha);
                //seteamos o mostramos la fecha en el JDateChooser
                jchofachanaci.setDate(fechaN);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

            if ("Masculino".equals(genero)) {
                hom.setSelected(true);
            } else if ("Femenino".equals(genero)) {
                muje.setSelected(true);
            }
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        int fila = jTable1.getSelectedRow();

        if (fila >= 0) {

            String codigo = "";
            codigo = jTable1.getValueAt(fila, 0).toString();

            int confirmar = JOptionPane.showConfirmDialog(null, "Esta seguro que desea Eliminar el registro? ");

            if (JOptionPane.OK_OPTION == confirmar) {

                try {

                    PreparedStatement pst = cn.prepareStatement("UPDATE clientes SET estado_cliente='B' WHERE id_clientes='" + codigo + "'");
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Se Elimino Correctamete");
                    Limpiar();
                    mostrardatos();

                } catch (Exception e) {

                    System.out.println("6" + e.getMessage());
                }

            } else {
                JOptionPane.showMessageDialog(null, "No se elimino");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No seleciono fila");
        }

    }//GEN-LAST:event_jButton3ActionPerformed
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if ( cedulatxt.getText().isEmpty() || nombretxt.getText().isEmpty() || apellidotxt.getText().isEmpty() || telefono.getText().isEmpty() || VerificarEmail(correotre.getText()) == correotre.getText().isEmpty() || direcciontxa.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "LLene los campos corectamente");

        } else {

            Date dat = new Date();//Instancia la fecha del sistema
            if (jchofachanaci.getDate().getTime() >= dat.getTime()) {//Compara si la fecha seleccionada es memor o igual a la fecha actual
                JOptionPane.showMessageDialog(this, "Fecha Infrige la fecha Actual");

            } else {
                JOptionPane.showMessageDialog(this, "Fecha Correcta");

                jButton13.doClick();

                if (EdadCalculada.getText().isEmpty() || cedulatxt.getText().isEmpty() || nombretxt.getText().isEmpty() || apellidotxt.getText().isEmpty() || telefono.getText().isEmpty() || VerificarEmail(correotre.getText()) == correotre.getText().isEmpty() || direcciontxa.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "LLene los campos corectamente");

                } else {

                    if (validaciones.Cedula(cedulatxt.getText())) {

                        fecha = jchofachanaci.getDate();
                        System.out.println(fecha);
                        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
                        String fechan = formato.format(fecha);
                        System.out.println(fechan);
                        String fec[] = fechan.split("/");
                        LocalDate fechanacimiento = LocalDate.of(Integer.parseInt(fec[0]), Integer.parseInt(fec[1]), Integer.parseInt(fec[2]));
                        System.out.println(fechanacimiento);

                        PreparedStatement pst = null;
                        PreparedStatement pstp = null;

                        String sqlp = "INSERT INTO persona(persona_id, persona_cedula , persona_nombre, persona_apellido, persona_edad, persona_correo, persona_direccion, persona_genero, persona_telefono) VALUES (?,?,?,?,?,?,?,?,?)";
                        String sql = "INSERT INTO clientes (id_clientes,persona_id, fecha_nac_cli,estado_cliente) VALUES (?,?,?,'A')";

                        int id = codIncrement();
                        int cod_cliem = codIncrementPersona();
                        String cedula = cedulatxt.getText();
                        String nombre = nombretxt.getText();
                        String apellido1 = apellidotxt.getText();
                        String telefonotx = telefono.getText();//EdadCalculada 
                        String edadCal = EdadCalculada.getText().trim();
                        int edad = Integer.parseInt(edadCal);
                        //int edad = Integer.parseInt(EdadCalculada.getText().toString());
                        //int edad = Integer.valueOf(EdadSpiner.getValue().toString());
                        String correotx = correotre.getText();
                        String direcciontx = direcciontxa.getText();
                        String genero = eleccionGenero();

                        Clientes nuecli = new Clientes(id, fechanacimiento, cod_cliem, cedula, nombre, apellido1, edad, correotx, direcciontx, genero, telefonotx);

                        try {
                            pstp = cn.prepareStatement(sqlp);
                            pst = cn.prepareStatement(sql);

                            pstp.setInt(1, nuecli.getId());
                            pstp.setString(2, nuecli.getCedula());
                            pstp.setString(3, nuecli.getNombre());
                            pstp.setString(4, nuecli.getApellido());
                            pstp.setInt(5, nuecli.getEdad());
                            pstp.setString(6, nuecli.getCorreo());
                            pstp.setString(7, nuecli.getDireccion());
                            pstp.setString(8, nuecli.getGenero());
                            pstp.setString(9, nuecli.getTelefono());

                            pst.setInt(1, nuecli.getCod_cliem());
                            pst.setInt(2, nuecli.getId());
                            pst.setDate(3, java.sql.Date.valueOf(fechanacimiento));
                            pstp.executeUpdate();
                            pst.executeUpdate();
                            System.out.println(pst);
                            //JOptionPane.showMessageDialog(null, "Cedula Correcta");
                            JOptionPane.showMessageDialog(null, "Se guardo");
                            mostrardatos();
                            Limpiar();

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Cedula Existente");
                            //Limpiar();
                            cedulatxt.setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Cedula Incorrecta");
                    }
                }
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void BUSCARCEDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUSCARCEDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BUSCARCEDActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        mostrardatos();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int fila = jTable1.getSelectedRow();

        if (evt.getClickCount() == 2) {

            if (fila >= 0) {

                comd.setText(jTable1.getValueAt(fila, 0).toString());
                cedulatxt.setText(jTable1.getValueAt(fila, 1).toString());
                cedulatxt.setEditable(false);
                nombretxt.setText(jTable1.getValueAt(fila, 2).toString());
                apellidotxt.setText(jTable1.getValueAt(fila, 3).toString());
                telefono.setText(jTable1.getValueAt(fila, 8).toString());
                direcciontxa.setText(jTable1.getValueAt(fila, 6).toString());
                correotre.setText(jTable1.getValueAt(fila, 5).toString());
                String genero = String.valueOf(jTable1.getValueAt(fila, 7).toString());
                EdadCalculada.setText(jTable1.getValueAt(fila, 4).toString());
//                String edad = String.valueOf(jTable1.getValueAt(fila, 4));
//                EdadSpiner.setValue(Integer.parseInt(edad));
                String fecha = jTable1.getValueAt(fila, 9).toString();
                //creamos el formato en el que deseamos mostrar la fecha
                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
                //creamos una variable tipo Date y la ponemos NULL
                Date fechaN = jchofachanaci.getDate();
                try {
                    //parseamos de String a Date usando el formato
                    fechaN = formatoDelTexto.parse(fecha);
                    //seteamos o mostramos la fecha en el JDateChooser
                    jchofachanaci.setDate(fechaN);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                if ("Masculino".equals(genero)) {
                    hom.setSelected(true);
                } else if ("Femenino".equals(genero)) {
                    muje.setSelected(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No seleciono fila");
            }

        } else {

            Limpiar();
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACTUALIZARActionPerformed
        int fila = jTable1.getSelectedRow();

        if (fila >= 0) {

            comd.setText(jTable1.getValueAt(fila, 0).toString());
            cedulatxt.setText(jTable1.getValueAt(fila, 1).toString());
            cedulatxt.setEditable(false);
            nombretxt.setText(jTable1.getValueAt(fila, 2).toString());
            apellidotxt.setText(jTable1.getValueAt(fila, 3).toString());
            telefono.setText(jTable1.getValueAt(fila, 8).toString());
            direcciontxa.setText(jTable1.getValueAt(fila, 6).toString());
            correotre.setText(jTable1.getValueAt(fila, 5).toString());
            String genero = String.valueOf(jTable1.getValueAt(fila, 7).toString());
            EdadCalculada.setText(jTable1.getValueAt(fila, 4).toString());
//                String edad = String.valueOf(jTable1.getValueAt(fila, 4));
//                EdadSpiner.setValue(Integer.parseInt(edad));
            String fecha = jTable1.getValueAt(fila, 9).toString();
            //creamos el formato en el que deseamos mostrar la fecha
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            //creamos una variable tipo Date y la ponemos NULL
            Date fechaN = jchofachanaci.getDate();
            try {
                //parseamos de String a Date usando el formato
                fechaN = formatoDelTexto.parse(fecha);
                //seteamos o mostramos la fecha en el JDateChooser
                jchofachanaci.setDate(fechaN);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

            if ("Masculino".equals(genero)) {
                hom.setSelected(true);
            } else if ("Femenino".equals(genero)) {
                muje.setSelected(true);
            }

        } else {

            Limpiar();
        }

    }//GEN-LAST:event_ACTUALIZARActionPerformed

    private void ELIMINARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ELIMINARActionPerformed
        int fila = jTable1.getSelectedRow();

        if (fila >= 0) {

            String codigo = "";
            codigo = jTable1.getValueAt(fila, 0).toString();

            int confirmar = JOptionPane.showConfirmDialog(null, "Esta seguro que desea Eliminar el registro? ");

            if (JOptionPane.OK_OPTION == confirmar) {

                try {

                    PreparedStatement pst = cn.prepareStatement("UPDATE clientes SET estado_cliente='B' WHERE id_clientes='" + codigo + "'");
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Se Elimino Correctamete");
                    Limpiar();
                    mostrardatos();

                } catch (Exception e) {

                    System.out.println("6" + e.getMessage());
                }

            } else {
                JOptionPane.showMessageDialog(null, "No se elimino");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No seleciono fila");
        }

    }//GEN-LAST:event_ELIMINARActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void correotreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_correotreKeyReleased

        if (validaciones.VerificarEmail(correotre.getText())) {

            AvisoCorreo1.setVisible(false);

        } else {

            AvisoCorreo1.setVisible(true);
        }

    }//GEN-LAST:event_correotreKeyReleased

    private void apellidotxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidotxtKeyTyped
        validaciones.ValidarLongitud(apellidotxt.getText(), 30, evt);
        ValidarLetrNum(Character.isDigit(evt.getKeyChar()), evt);
    }//GEN-LAST:event_apellidotxtKeyTyped

    private void nombretxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombretxtKeyTyped
        validaciones.ValidarLongitud(nombretxt.getText(), 30, evt);
        ValidarLetrNum(Character.isDigit(evt.getKeyChar()), evt);
    }//GEN-LAST:event_nombretxtKeyTyped

    private void cedulatxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulatxtKeyTyped
        validaciones.ValidarLongitud(cedulatxt.getText(), 10, evt);
        ValidarLetrNum(Character.isLetter(evt.getKeyChar()), evt);
    }//GEN-LAST:event_cedulatxtKeyTyped

    private void direcciontxaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_direcciontxaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_direcciontxaKeyTyped

    private void homActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_homActionPerformed

    private void telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonoActionPerformed

    private void dateliminadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateliminadosMouseClicked

        if (dateliminados.isSelected()) {

            String cedula = "";

            DefaultTableModel modelo = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int columnas) {
                    return false;
                }
            };

            //DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("N");
            modelo.addColumn("CEDULA");
            modelo.addColumn("NOMBRE ");
            modelo.addColumn("APELLIDO");
            modelo.addColumn("EDAD ");
            modelo.addColumn("CORREO");
            modelo.addColumn("DIRECCION");
            modelo.addColumn("GENERO ");
            modelo.addColumn("TELEFONO");
            modelo.addColumn("FECHA NACIMIENTO");

            jTable1.setModel(modelo);

            String sql = "";

            sql = "SELECT C.id_clientes, P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono,C.fecha_nac_cli  FROM persona P, clientes C WHERE P.persona_id = C.persona_id ORDER BY C.id_clientes";

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

                    modelo.addRow(datos);
                }

                jTable1.setModel(modelo);

            } catch (SQLException ex) {

                System.out.println("Error:" + ex);
            }
        } else {
            mostrardatos();
        }
    }//GEN-LAST:event_dateliminadosMouseClicked

    private void dateliminadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateliminadosActionPerformed


    }//GEN-LAST:event_dateliminadosActionPerformed

    private void BUSCARCEDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BUSCARCEDKeyPressed

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
        modelo.addColumn("EDAD ");
        modelo.addColumn("CORREO");
        modelo.addColumn("DIRECCION");
        modelo.addColumn("GENERO ");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("FECHA NACIMIENTO");
        jTable1.setModel(modelo);

        String sql = "SELECT C.id_clientes, P.persona_cedula  , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono,C.fecha_nac_cli  FROM persona P, clientes C WHERE P.persona_cedula='" + BUSCARCED.getText() + "' AND P.persona_id = C.persona_id  AND C.estado_cliente = 'A'";
//        sql = "SELECT FROM clientes WHERE  persona_cedula  LIKE '%" + BUSCARCED.getText() + "%' "
//               + "OR persona_cedula  LIKE '%" + BUSCARCED.getText() + "%'";

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

                modelo.addRow(datos);
            }

            jTable1.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_BUSCARCEDKeyPressed

    private void comdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comdActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        try {

            //EdadSpiner.setValue(Integer.parseInt(edad));
            //EdadSpiner.setValue(Integer.parseInt(djmeb.MetodoGenericos.edad(jchofachanaci.getDate())));
            EdadCalculada.setText(djmeb.MetodoGenericos.edad(jchofachanaci.getDate()));

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void correotreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_correotreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_correotreActionPerformed

    private void jchofachanaciKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jchofachanaciKeyTyped

//        char cTeclaPresionada = evt.getKeyChar();
//
//        // Click para aceptar el enter
//        if (cTeclaPresionada == KeyEvent.VK) {
//
//            // Da clicl al boton al precionar ENTER
//            jButton13.doClick();
//        }
    }//GEN-LAST:event_jchofachanaciKeyTyped

    private void BUSCARCEDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BUSCARCEDKeyReleased
        if (BUSCARCED.getText().isEmpty()) {
            mostrardatos();
        }
    }//GEN-LAST:event_BUSCARCEDKeyReleased

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
            java.util.logging.Logger.getLogger(ingresodecliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ingresodecliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ingresodecliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ingresodecliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ingresodecliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ACTUALIZAR;
    private javax.swing.JLabel AvisoCorreo1;
    private javax.swing.JTextField BUSCARCED;
    private javax.swing.JMenuItem ELIMINAR;
    private javax.swing.JTextField EdadCalculada;
    public static javax.swing.JTextField apellidotxt;
    public static javax.swing.JTextField cedulatxt;
    private javax.swing.JTextField comd;
    public static javax.swing.JTextField correotre;
    private javax.swing.JCheckBox dateliminados;
    public static javax.swing.JTextField direcciontxa;
    public static javax.swing.ButtonGroup genero;
    private javax.swing.JRadioButton hom;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private com.toedter.calendar.JDateChooser jchofachanaci;
    private javax.swing.JRadioButton muje;
    public static javax.swing.JTextField nombretxt;
    private javax.swing.JFormattedTextField telefono;
    // End of variables declaration//GEN-END:variables

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();

}
