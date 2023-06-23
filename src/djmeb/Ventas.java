package djmeb;

import java.time.LocalDate;

public class Ventas {
        private String cod_fac;
    private String cedula_cliente;
    private String nombre_cli;
    private String apellido_cli;
    private String direccion;
    private String correo_cli;
     private String fecha_compra;
     private double total;
    public Ventas() {
    }

    public Ventas(String cod_fac, String cedula_cliente, String nombre_cli, String apellido_cli, String direccion, String correo_cli, String fecha_compra, double total) {
        this.cod_fac = cod_fac;
        this.cedula_cliente = cedula_cliente;
        this.nombre_cli = nombre_cli;
        this.apellido_cli = apellido_cli;
        this.direccion = direccion;
        this.correo_cli = correo_cli;
        this.fecha_compra = fecha_compra;
        this.total = total;
    }

    public String getCod_fac() {
        return cod_fac;
    }

    public void setCod_fac(String cod_fac) {
        this.cod_fac = cod_fac;
    }

    public String getCedula_cliente() {
        return cedula_cliente;
    }

    public void setCedula_cliente(String cedula_cliente) {
        this.cedula_cliente = cedula_cliente;
    }

    public String getNombre_cli() {
        return nombre_cli;
    }

    public void setNombre_cli(String nombre_cli) {
        this.nombre_cli = nombre_cli;
    }

    public String getApellido_cli() {
        return apellido_cli;
    }

    public void setApellido_cli(String apellido_cli) {
        this.apellido_cli = apellido_cli;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo_cli() {
        return correo_cli;
    }

    public void setCorreo_cli(String correo_cli) {
        this.correo_cli = correo_cli;
    }

    public String getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(String fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
