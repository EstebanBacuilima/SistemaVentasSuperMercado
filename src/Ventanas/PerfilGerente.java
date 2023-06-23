package Ventanas;

import static Ventanas.Menu.FotoPerfil;
import static Ventanas.Menu.NombreFoto;
import static Ventanas.Menu.Orig;
import static djmeb.validaciones.ValidarLetrNum;
import static djmeb.validaciones.ValidarLongitud;
import java.awt.HeadlessException;
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
import java.util.logging.Level;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class PerfilGerente extends javax.swing.JFrame {

    protected static String Imagen;
    protected static String Dest, Orig;

    public PerfilGerente() {
        initComponents();
        this.setLocationRelativeTo(null);
        CodEmpleado.setText(String.valueOf(codIncrementEmpleado()));
        CodEmpleado.setEnabled(false);
        CodPersona.setText(String.valueOf(codIncrementNormal()));
        CodPersona.setEnabled(false);
        Ruc.setEnabled(false);
        Cedula.setEnabled(false);
        mostrardatos();
        mostrardatosUser();
    }

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();

    // EMPIEZA
    void mostrardatos() {

        String sql = "";


            sql = "SELECT G.gerente_ruc ,P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, E.empleado_horario, E.empleado_salario, G.imagen_gerente FROM persona P, empleado E, gerente G WHERE P.persona_id = E.persona_id AND  E.empleado_codigo = G.empleado_codigo ";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Ruc.setText(rs.getString(1));
                Cedula.setText(rs.getString(2));
                Nombre.setText(rs.getString(3));
                Apellido.setText(rs.getString(4));
                EdadSpiner.setValue(Integer.parseInt(rs.getString(5)));
                Correo.setText(rs.getString(6));
                Direccion.setText(rs.getString(7));
                String genero = String.valueOf(rs.getString(8));
                if ("Masculino".equals(genero)) {
                    Hombre.setSelected(true);
                } else if ("Femenino".equals(genero)) {
                    Mujer.setSelected(true);
                }
                Telefono1.setText(rs.getString(9));
                Horario.setSelectedItem(rs.getString(10));
                Salario.setText(rs.getString(11));
                CodImagen.setText(rs.getString(12));
                Imagen = rs.getString(12);
                

                Orig = "src/Foto_Perfil/" + Imagen;

                ImageIcon icon = new ImageIcon(Orig);
                ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(PerfilGerente.getWidth(), PerfilGerente.getHeight(), Image.SCALE_DEFAULT));
                PerfilGerente.setText(null);
                PerfilGerente.setIcon(icono);
            }
            
            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No Encontrado");
            System.out.println("Error:" + ex);
        }

    }

    void mostrardatosUser() {

        String sql = "SELECT user_usuario, user_contrasena FROM regusuario WHERE  persona_cedula = '" + Cedula.getText() + "' AND user_estado = 'A'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Usuario.setText(rs.getString(1));
                Contraseña.setText(rs.getString(2));

            }

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }
    
///////////////////////////// FOTO EN EL MENU ///////////////////////////////////////////
    
    public void DevolverFoto ( String foto)  {
        
        Imagen = CodImagen.getText();

        
        Orig = "src/Foto_Perfil/" + Imagen;
        ImageIcon icon = new ImageIcon(Orig);
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(FotoPerfil.getWidth(), FotoPerfil.getHeight(), Image.SCALE_DEFAULT));
        FotoPerfil.setText(null);
        FotoPerfil.setIcon(icono);
    }
    
    
    
    // AUTOINCREMENTARSE
    // ID persona
    public int codIncrementNormal() {

        int serie = 1;

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = cc.conectar();

        try {

            ps = cn.prepareStatement("SELECT persona_id FROM persona ");
            rs = ps.executeQuery();

            while (rs.next()) {
                serie = rs.getInt(1);
            }

        } catch (Exception e) {

            System.out.println("Error" + e.getMessage());
        }

        return serie;

    }

    // Codigo empleado
    public int codIncrementEmpleado() {

        int serie = 1;

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = cc.conectar();

        try {

            ps = cn.prepareStatement("SELECT MAX(empleado_codigo) FROM empleado");
            rs = ps.executeQuery();

            while (rs.next()) {
                serie = rs.getInt(1) + 1;

            }

        } catch (Exception e) {

            System.out.println("4 Error" + e.getMessage());
        }

        return serie;

    }

    public int codIncrement() {

        int serie = 1;

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = cc.conectar();

        try {

            ps = cn.prepareStatement("SELECT MAX(id_usuario) FROM regusuario");
            rs = ps.executeQuery();

            while (rs.next()) {
                serie = rs.getInt(1) + 1;
            }

        } catch (Exception e) {

            System.out.println("4 Error" + e.getMessage());
        }

        return serie;

    }

    public String eleccionGenero() {

        String genero = null;
        if (Hombre.isSelected()) {
            genero = "Masculino";
        }
        if (Mujer.isSelected()) {
            genero = "Femenino";
        }
        return genero;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
    String nombreI;
              
    public String SubirPefil () {
        
        
        JFileChooser file = new JFileChooser();
        file.showOpenDialog(this);
        File archivo = file.getSelectedFile();
        Dest = "src\\Foto_Perfil\\" + archivo.getName();
        Orig = archivo.getPath();
        Imagen = archivo.getName();
        nombreI = Imagen;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        ImageIcon icon = new ImageIcon(Orig);
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(PerfilGerente.getWidth(), PerfilGerente.getHeight(), Image.SCALE_DEFAULT));
        PerfilGerente.setText(null);
        PerfilGerente.setIcon(icono);
        
        try {

        Path Destino = Paths.get(Dest);
        Path Origen = Paths.get(Orig);
        Files.copy(Origen,Destino,StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            //Logger.getLogger(PerfilGerente.class.getName()).log(Level.SEVERE,null,e);
        }
        
        return nombreI;
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Cedula = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Apellido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        EdadSpiner = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        Correo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Direccion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Hombre = new javax.swing.JRadioButton();
        Mujer = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        Telefono1 = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        Salario = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        Horario = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        Ruc = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        ActulizarUser = new javax.swing.JButton();
        Usuario = new javax.swing.JTextField();
        PerfilGerente = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        CodPersona = new javax.swing.JTextField();
        Contraseña = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        CodEmpleado = new javax.swing.JTextField();
        CodImagen = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setText("GERENTE");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 240, 40));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel2.setText("RUC:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, 20));

        Cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CedulaActionPerformed(evt);
            }
        });
        Cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CedulaKeyTyped(evt);
            }
        });
        getContentPane().add(Cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 200, 23));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel4.setText("Nombre:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        Nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NombreKeyTyped(evt);
            }
        });
        getContentPane().add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 200, 23));

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel5.setText("Apellido:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        Apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ApellidoKeyTyped(evt);
            }
        });
        getContentPane().add(Apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 200, 23));

        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel6.setText("Edad:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        EdadSpiner.setModel(new javax.swing.SpinnerNumberModel(18, null, 60, 1));
        EdadSpiner.setOpaque(false);
        getContentPane().add(EdadSpiner, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 58, 23));

        jLabel7.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel7.setText("Correo:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        Correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CorreoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CorreoKeyTyped(evt);
            }
        });
        getContentPane().add(Correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 200, 23));

        jLabel8.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel8.setText("Direccion:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, -1));
        getContentPane().add(Direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, 200, 23));

        jLabel9.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel9.setText("Genero:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, -1));

        buttonGroup1.add(Hombre);
        Hombre.setText("Masculino");
        Hombre.setOpaque(false);
        getContentPane().add(Hombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, -1, -1));

        buttonGroup1.add(Mujer);
        Mujer.setText("Femenino");
        Mujer.setOpaque(false);
        getContentPane().add(Mujer, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 390, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel11.setText("Telefono:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, -1, -1));

        try {
            Telefono1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(Telefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 440, 200, 23));

        jLabel19.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel19.setText("Salario:");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, -1, 20));

        Salario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SalarioKeyTyped(evt);
            }
        });
        getContentPane().add(Salario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 490, 200, 23));

        jLabel12.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel12.setText("Horario:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, -1, 20));

        Horario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matutino", "Tiempo Completo" }));
        getContentPane().add(Horario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, 200, -1));

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel3.setText("Cedula:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 20));
        getContentPane().add(Ruc, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 200, 23));

        jLabel13.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel13.setText("Contraseña");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 460, 120, -1));

        ActulizarUser.setText("Actualizar");
        ActulizarUser.setToolTipText("Crear");
        ActulizarUser.setAutoscrolls(true);
        ActulizarUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ActulizarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActulizarUserActionPerformed(evt);
            }
        });
        getContentPane().add(ActulizarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 540, 120, 30));

        Usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(Usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 430, 190, 25));
        getContentPane().add(PerfilGerente, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 230, 210));

        jLabel14.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel14.setText("Usuario");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 390, 80, -1));

        CodPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodPersonaActionPerformed(evt);
            }
        });
        getContentPane().add(CodPersona, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 40, 40));
        getContentPane().add(Contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 490, 190, 25));

        jButton1.setText("Subir");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, -1, -1));

        CodEmpleado.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        getContentPane().add(CodEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 40, 40));

        CodImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodImagenActionPerformed(evt);
            }
        });
        getContentPane().add(CodImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, 90, 40));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 250, 230));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SalirPerGer.png"))); // NOI18N
        jButton3.setToolTipText("SALIR");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 620, 50, 50));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Gurdar.png"))); // NOI18N
        jButton2.setToolTipText("Guardar");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, 60, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CedulaActionPerformed

    private void CedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaKeyTyped
        ValidarLongitud(Cedula.getText(), 10, evt);
        ValidarLetrNum(Character.isLetter(evt.getKeyChar()), evt);
    }//GEN-LAST:event_CedulaKeyTyped

    private void NombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreKeyTyped
        ValidarLongitud(Nombre.getText(), 30, evt);
        ValidarLetrNum(Character.isDigit(evt.getKeyChar()), evt);
    }//GEN-LAST:event_NombreKeyTyped

    private void ApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ApellidoKeyTyped
        ValidarLongitud(Apellido.getText(), 30, evt);
        ValidarLetrNum(Character.isDigit(evt.getKeyChar()), evt);
    }//GEN-LAST:event_ApellidoKeyTyped

    private void CorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CorreoKeyReleased

    }//GEN-LAST:event_CorreoKeyReleased

    private void CorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CorreoKeyTyped

    }//GEN-LAST:event_CorreoKeyTyped

    private void SalarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SalarioKeyTyped
        ValidarLongitud(Salario.getText(), 8, evt);
        ValidarLetrNum(Character.isLetter(evt.getKeyChar()), evt);
    }//GEN-LAST:event_SalarioKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {

            int edad = Integer.valueOf(EdadSpiner.getValue().toString());
            int salario = Integer.valueOf(Salario.getText());

            PreparedStatement pst2 = cn.prepareStatement("UPDATE persona SET persona_nombre='" + Nombre.getText() + "', persona_apellido='" + Apellido.getText() + "',persona_edad='" + edad + "',persona_correo='" + Correo.getText() + "',persona_direccion='" + Direccion.getText() + "',persona_genero='" + eleccionGenero() + "',persona_telefono='" + Telefono1.getText() + "' WHERE persona_cedula='" + Cedula.getText() + "'");
            PreparedStatement pst3 = cn.prepareStatement("UPDATE empleado SET empleado_horario='" + Horario.getSelectedItem().toString() + "',empleado_salario='" + salario + "' WHERE persona_id = '2'");
            PreparedStatement pst4 = cn.prepareStatement("UPDATE gerente SET gerente_ruc ='" + Ruc.getText() + "', imagen_gerente ='" + CodImagen.getText() + "'  WHERE empleado_codigo ='1'");

            pst2.executeUpdate();
            pst3.executeUpdate();
            pst4.executeUpdate();
            JOptionPane.showMessageDialog(null, "Actualizado");

        } catch (HeadlessException | NumberFormatException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void UsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsuarioActionPerformed

    private void ActulizarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActulizarUserActionPerformed

        try {

            PreparedStatement pst = cn.prepareStatement("UPDATE regusuario SET user_usuario='"+ Usuario.getText() + "', user_contrasena='" + Contraseña.getText() + "' WHERE persona_cedula='" + Cedula.getText() + "'");
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Actualizado");

        } catch (HeadlessException | SQLException e) {
            System.out.println(e.getMessage());

        }

    }//GEN-LAST:event_ActulizarUserActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 
        String NombreGerente = Nombre.getText();
        Menu.NombreGerente.setText(NombreGerente);
         
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nuevaFot = SubirPefil();
        CodImagen.setText(nuevaFot);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void CodPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodPersonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodPersonaActionPerformed

    private void CodImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodImagenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodImagenActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PerfilGerente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ActulizarUser;
    private javax.swing.JTextField Apellido;
    private javax.swing.JTextField Cedula;
    private javax.swing.JTextField CodEmpleado;
    public static javax.swing.JTextField CodImagen;
    private javax.swing.JTextField CodPersona;
    private javax.swing.JTextField Contraseña;
    private javax.swing.JTextField Correo;
    private javax.swing.JTextField Direccion;
    private javax.swing.JSpinner EdadSpiner;
    private javax.swing.JRadioButton Hombre;
    private javax.swing.JComboBox<String> Horario;
    private javax.swing.JRadioButton Mujer;
    public static javax.swing.JTextField Nombre;
    private javax.swing.JLabel PerfilGerente;
    private javax.swing.JTextField Ruc;
    private javax.swing.JTextField Salario;
    private javax.swing.JFormattedTextField Telefono1;
    private javax.swing.JTextField Usuario;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
