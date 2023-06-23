package Ventanas;

import Reportes.Excel;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteEmpleados extends javax.swing.JFrame {

    public ReporteEmpleados() {
        initComponents();
        this.setLocationRelativeTo(null);
        Gen_Report.setEnabled(false);

    }

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();

    DefaultTableModel modelo = new DefaultTableModel();

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    void mostrardatosVacio() {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        jTable1.setModel(modelo);

    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    void mostrardatos(String cedula) {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        modelo.addColumn("E#");
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Edad");
        modelo.addColumn("Correo ");
        modelo.addColumn("Direccion");
        modelo.addColumn("Genero ");
        modelo.addColumn("Telefono ");
        modelo.addColumn("Horario");
        modelo.addColumn("Salario ");
        jTable1.setModel(modelo);

        // Acumular la sentencia
        String sql = "";

        if (cedula.equals("")) {

            sql = " SELECT DISTINCT E.persona_id, P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, E.empleado_horario, E.empleado_salario FROM persona P, empleado E WHERE P.persona_id = E.persona_id ORDER BY E.persona_id ";

        }

        String[] datos = new String[11];

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
                String salario = rs.getString(11);
                datos[10] = salario;

                //System.out.println(rs);
                modelo.addRow(datos);
            }
            jTable1.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    void mostrardatosCajeros(String cedula) {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        modelo.addColumn("E#");
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Edad");
        modelo.addColumn("Correo ");
        modelo.addColumn("Direccion");
        modelo.addColumn("Genero ");
        modelo.addColumn("Telefono ");
        modelo.addColumn("Horario");
        modelo.addColumn("Salario ");
        jTable1.setModel(modelo);

        // Acumular la sentencia
        String sql = "";

        if (cedula.equals("")) {

            sql = " SELECT DISTINCT E.persona_id, P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, E.empleado_horario, E.empleado_salario FROM persona P, empleado E, registrocajeros C WHERE P.persona_id = E.persona_id AND E.empleado_codigo = C.empleado_codigo ORDER BY E.persona_id";

        } else {

            sql = " SELECT DISTINCT E.persona_id, P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, E.empleado_horario, E.empleado_salario FROM persona P, empleado E, registrocajeros C WHERE P.persona_cedula = '" + cedula + "' AND P.persona_id = E.persona_id AND E.empleado_codigo = C.empleado_codigo ORDER BY E.persona_id";

        }

        String[] datos = new String[11];

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
                String salario = rs.getString(11);
                datos[10] = salario;

                //System.out.println(rs);
                modelo.addRow(datos);
            }
            jTable1.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    void mostrardatosBodegueros(String cedula) {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        modelo.addColumn("E#");
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Edad");
        modelo.addColumn("Correo ");
        modelo.addColumn("Direccion");
        modelo.addColumn("Genero ");
        modelo.addColumn("Telefono ");
        modelo.addColumn("Horario");
        modelo.addColumn("Salario ");
        jTable1.setModel(modelo);

        // Acumular la sentencia
        String sql = "";

        if (cedula.equals("")) {

            sql = "SELECT DISTINCT E.persona_id, P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, E.empleado_horario, E.empleado_salario FROM persona P, empleado E, registrobodegueros B WHERE P.persona_id = E.persona_id AND E.empleado_codigo = B.empleado_codigo ORDER BY E.persona_id";

        }

        String[] datos = new String[11];

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
                String salario = rs.getString(11);
                datos[10] = salario;

                //System.out.println(rs);
                modelo.addRow(datos);
            }
            jTable1.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    void mostrardatosContadores(String cedula) {

        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int columnas) {
                return false;
            }
        };

        modelo.addColumn("E#");
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Edad");
        modelo.addColumn("Correo ");
        modelo.addColumn("Direccion");
        modelo.addColumn("Genero ");
        modelo.addColumn("Telefono ");
        modelo.addColumn("Horario");
        modelo.addColumn("Salario ");
        jTable1.setModel(modelo);

        // Acumular la sentencia
        String sql = "";

        if (cedula.equals("")) {

            sql = "SELECT DISTINCT E.persona_id, P.persona_cedula , P.persona_nombre, P.persona_apellido, P.persona_edad, P.persona_correo, P.persona_direccion, P.persona_genero, P.persona_telefono, E.empleado_horario, E.empleado_salario FROM persona P, empleado E, registrocontador CO WHERE P.persona_id = E.persona_id AND E.empleado_codigo = CO.empleado_codigo ORDER BY E.persona_id";

        }

        String[] datos = new String[11];

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
                String salario = rs.getString(11);
                datos[10] = salario;

                //System.out.println(rs);
                modelo.addRow(datos);
            }
            jTable1.setModel(modelo);

        } catch (SQLException ex) {

            System.out.println("Error:" + ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        FondoReport = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Tipo = new javax.swing.JComboBox<>();
        Gen_Report = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        SalariosMaxMin = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FondoReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoReportEmp.png"))); // NOI18N
        FondoReport.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        getContentPane().add(FondoReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 1020, 400));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 1018, 400));

        Tipo.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        Tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Empleados", "Cajeros", "Bodegueros", "Contadores" }));
        Tipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        Tipo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Tipo.setOpaque(false);
        Tipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TipoItemStateChanged(evt);
            }
        });
        Tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoActionPerformed(evt);
            }
        });
        getContentPane().add(Tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 280, 30));

        Gen_Report.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GenerarReportes.png"))); // NOI18N
        Gen_Report.setToolTipText("Generar");
        Gen_Report.setBorder(null);
        Gen_Report.setBorderPainted(false);
        Gen_Report.setContentAreaFilled(false);
        Gen_Report.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Gen_Report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Gen_ReportActionPerformed(evt);
            }
        });
        getContentPane().add(Gen_Report, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 550, 90, 50));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 48)); // NOI18N
        jLabel1.setText("Reportes");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-32300, -32734, 41, 16));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SalirPerGer.png"))); // NOI18N
        jButton1.setToolTipText("Salir");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 50, 50));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SalariosMaxMin.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        SalariosMaxMin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ver Salario", "Maximo", "Minimo" }));
        SalariosMaxMin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SalariosMaxMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalariosMaxMinActionPerformed(evt);
            }
        });
        jPanel1.add(SalariosMaxMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 80, 200, 30));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 64)); // NOI18N
        jLabel2.setText("REPORTES");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Gen_ReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Gen_ReportActionPerformed
        //Excel.reporte();

        if (Tipo.getSelectedItem().toString() == "Cajeros") {
            // CAJEROSS
            FondoReport.setVisible(false);
            Excel.reporteCajeros();

        } else {
            if (Tipo.getSelectedItem().toString() == "Bodegueros") {
                // BODEGUEROS
                FondoReport.setVisible(false);
                Excel.reporteBodequeros();
            } else {
                if (Tipo.getSelectedItem().toString() == "Contadores") {
                    // CONTADORES
                    FondoReport.setVisible(false);
                    Excel.reporteContador();
                } else {

                    if (Tipo.getSelectedItem().toString() == "Empleados") {
                        // EMPLEADOS
                        FondoReport.setVisible(false);
                        Excel.reporte();
                    } else {

                        if (Tipo.getSelectedIndex() == 0) {

                            mostrardatosVacio();
                            FondoReport.setVisible(true);
                            Gen_Report.setEnabled(false);

                        } else {

                        }

                    }

                }

            }
        }


    }//GEN-LAST:event_Gen_ReportActionPerformed

    private void TipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TipoActionPerformed

    private void TipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TipoItemStateChanged

        // COMBO BOX ANIDADO 
        if (evt.getStateChange() == ItemEvent.SELECTED) {

            if (Tipo.getSelectedIndex() == 2) {
                // CAJEROSS
                FondoReport.setVisible(false);
                mostrardatosCajeros("");
                Gen_Report.setEnabled(true);

            } else {
                if (Tipo.getSelectedIndex() == 3) {
                    // BODEGUEROS
                    mostrardatosBodegueros("");
                    FondoReport.setVisible(false);
                    Gen_Report.setEnabled(true);

                } else {
                    if (Tipo.getSelectedIndex() == 4) {
                        // CONTADORES
                        mostrardatosContadores("");
                        FondoReport.setVisible(false);
                        Gen_Report.setEnabled(true);
                    } else {

                        if (Tipo.getSelectedIndex() == 1) {
                            // EMPLEADOS
                            mostrardatos("");
                            FondoReport.setVisible(false);
                            Gen_Report.setEnabled(true);

                        } else {

                            if (Tipo.getSelectedIndex() == 0) {

                                mostrardatosVacio();
                                FondoReport.setVisible(true);
                                Gen_Report.setEnabled(false);

                            } else {

                            }

                        }

                    }

                }
            }

        }
    }//GEN-LAST:event_TipoItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void SalariosMaxMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalariosMaxMinActionPerformed

        String sql = "";
        
            if (SalariosMaxMin.getSelectedItem().equals("Maximo")) {

                sql = " SELECT MAX(empleado_salario) FROM empleado";

            } else if (SalariosMaxMin.getSelectedItem().equals("Minimo")) {
                
                sql = " SELECT MIN(empleado_salario) FROM empleado";
                
            } else if (SalariosMaxMin.getSelectedItem().equals("Seleccione Salario")) {
                
                mostrardatos("");
            }


            try {

                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {

                    //salario = ;
                    JOptionPane.showMessageDialog(this, "Salario: " + rs.getString(1));

                }
                
                

            } catch (SQLException ex) {

                System.out.println("Error:" + ex);
            }

        
    }//GEN-LAST:event_SalariosMaxMinActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReporteEmpleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FondoReport;
    private javax.swing.JButton Gen_Report;
    private javax.swing.JComboBox<String> SalariosMaxMin;
    private javax.swing.JComboBox<String> Tipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
