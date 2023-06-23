package Ventanas;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Menu extends javax.swing.JFrame {

    
    protected static String Imagen;
    protected static String Dest, Orig;
    
    

    public Menu() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        NombreGerente.setText(Login.Nombre_G.getText());
        NombreFoto.setText(Login.Imagen);

        
        Orig = "src/Foto_Perfil/" + NombreFoto.getText();
        ImageIcon icon = new ImageIcon(Orig);
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(FotoPerfil.getWidth(), FotoPerfil.getHeight(), Image.SCALE_DEFAULT));
        FotoPerfil.setText(null);
        FotoPerfil.setIcon(icono);
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        FotoPerfil = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Contador = new javax.swing.JButton();
        Bodeguero = new javax.swing.JButton();
        Proveedores = new javax.swing.JButton();
        User = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        NombreGerente = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        Cajero = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        hora = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        fecha = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        NombreFoto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 64)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 153, 0));
        jLabel2.setText("MENU");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 170, 70));

        FotoPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto_Perfil/minionFoto.jpg"))); // NOI18N
        getContentPane().add(FotoPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 110, 210, 210));

        jLabel3.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel3.setText("Bienvenido ");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 340, 140, 30));

        Contador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BtoonContador1.png"))); // NOI18N
        Contador.setBorderPainted(false);
        Contador.setContentAreaFilled(false);
        Contador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Contador.setDefaultCapable(false);
        Contador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContadorActionPerformed(evt);
            }
        });
        getContentPane().add(Contador, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 160, 130));

        Bodeguero.setForeground(new java.awt.Color(255, 255, 255));
        Bodeguero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BodMenu_1.png"))); // NOI18N
        Bodeguero.setBorderPainted(false);
        Bodeguero.setContentAreaFilled(false);
        Bodeguero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bodeguero.setDefaultCapable(false);
        Bodeguero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BodegueroActionPerformed(evt);
            }
        });
        getContentPane().add(Bodeguero, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 140, 120));

        Proveedores.setBackground(new java.awt.Color(204, 153, 255));
        Proveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BodMenu.png"))); // NOI18N
        Proveedores.setBorderPainted(false);
        Proveedores.setContentAreaFilled(false);
        Proveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Proveedores.setDefaultCapable(false);
        Proveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProveedoresActionPerformed(evt);
            }
        });
        getContentPane().add(Proveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, 140, 120));

        User.setBackground(new java.awt.Color(204, 204, 204));
        User.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/UserMenu.png"))); // NOI18N
        User.setBorderPainted(false);
        User.setContentAreaFilled(false);
        User.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        User.setDefaultCapable(false);
        User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserActionPerformed(evt);
            }
        });
        getContentPane().add(User, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 440, 120, 120));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 102));
        jLabel1.setText("CONTADOR");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 153, 102));
        jLabel6.setText("PROVEEDOR");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 400, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 204, 255));
        jLabel8.setText("BODEGUERO");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("USUARIO");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 560, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonSalir.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setDefaultCapable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 525, 70, 70));

        NombreGerente.setFont(new java.awt.Font("Agency FB", 1, 48)); // NOI18N
        NombreGerente.setText("GERENTE");
        getContentPane().add(NombreGerente, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 320, 150, 60));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPriPOO(1).png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 400, 210, 90));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 90, 50, 10));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/VerReportesEm.png"))); // NOI18N
        jButton3.setToolTipText("Reportes");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 490, 50, 60));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/VerPerfilGeren.png"))); // NOI18N
        jButton1.setToolTipText("Perfil");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 40, 50, 40));

        Cajero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/CajeroBot.png"))); // NOI18N
        Cajero.setBorderPainted(false);
        Cajero.setContentAreaFilled(false);
        Cajero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CajeroActionPerformed(evt);
            }
        });
        getContentPane().add(Cajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 120, 110));

        jLabel11.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 204, 0));
        jLabel11.setText("CAJEROS");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, -1, -1));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 30, 10, 60));

        hora.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        hora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hora.setText("HORA");
        getContentPane().add(hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jSeparator1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, 370, 530));

        fecha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        fecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fecha.setText("DIA - MES - AÑO");
        getContentPane().add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 580, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoPricipalMenu.png"))); // NOI18N
        jLabel5.setText("A");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 610));

        NombreFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreFotoActionPerformed(evt);
            }
        });
        getContentPane().add(NombreFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 460, 80, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BodegueroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BodegueroActionPerformed
        VentanaRegistrarBodeguero crudBodeguero = new VentanaRegistrarBodeguero();
        crudBodeguero.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BodegueroActionPerformed

    private void UserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserActionPerformed
        VentanaUsuarios crudUser= new VentanaUsuarios();
        crudUser.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_UserActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Login login = new Login();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProveedoresActionPerformed
        
        VentanaRegistroEmpProveedor cruProveedores = new VentanaRegistroEmpProveedor();
        cruProveedores.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ProveedoresActionPerformed

    private void CajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CajeroActionPerformed
        VentanaRegistroCajero crud = new VentanaRegistroCajero();
        crud.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_CajeroActionPerformed

    private void ContadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContadorActionPerformed
        VentanaRegistroContador crudContador = new VentanaRegistroContador();
        crudContador.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ContadorActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //Fehca del sistema
        Date sistemaFech = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd MMMM yyyy");
        fecha.setText(formato.format(sistemaFech));

        //Hora del sistema
        Timer tiempo = new Timer(100, new Menu.horas());
        tiempo.start();
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PerfilGerente crudContador = new PerfilGerente();
        crudContador.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void NombreFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreFotoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreFotoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ReporteEmpleados re = new ReporteEmpleados();
        re.setVisible(true); 
    }//GEN-LAST:event_jButton3ActionPerformed

    class horas implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Date sistemaHora = new Date();
            String pmAm = "hh:mm:ss a";
            // PONER SEGUNDOS "hh:mm:ss a";
            SimpleDateFormat formato = new SimpleDateFormat(pmAm);
            Calendar now = Calendar.getInstance();
            hora.setText(String.format(formato.format(sistemaHora), now));
            
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bodeguero;
    private javax.swing.JButton Cajero;
    private javax.swing.JButton Contador;
    public static javax.swing.JLabel FotoPerfil;
    public static javax.swing.JTextField NombreFoto;
    public static javax.swing.JLabel NombreGerente;
    private javax.swing.JButton Proveedores;
    private javax.swing.JButton User;
    private javax.swing.JLabel fecha;
    private javax.swing.JLabel hora;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
