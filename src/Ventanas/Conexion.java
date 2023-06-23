package Ventanas;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {

    Connection conexion = null;

    public Connection conectar() {

        String DB = "jdbc:postgresql://localhost:5432/SistemaVentas";
        String usuario = "postgres";
        String contraseña = "0702";

        try {

            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(DB, usuario, contraseña);
            java.sql.Statement st = conexion.createStatement();
            System.out.println("conexion establecida");

            //conexion = DriverManager.getConnection(DB, usuario,contraseña);
            //JOptionPane.showMessageDialog(null, "Conectado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO Conectado" + e);
        }

        return conexion;
    }

}
