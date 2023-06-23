package djmeb;

import Ventanas.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.print.DocFlavor;

public class Productos {
    
    private String ruc;
    private int cod_producto;
    private String cod_barras;
    private String nombre;
    private int categoria;
    private int marca;
    private int medida;
    private double precio;
    private int cant_existente;
    private int cant_minima;
    private int cant_maxima;
    private String iva;
    private int estado;
    private String imagenes;

    public Productos() {
    }

//    public Productos(int cod_producto, String cod_barras, String nombre, int categoria, int marca, String medida, double precio, int cant_existente, int cant_minima, int cant_maxima, String iva, int estado, String imagenes) {
//        this.cod_producto = cod_producto;
//        this.cod_barras = cod_barras;
//        this.nombre = nombre;
//        this.categoria = categoria;
//        this.marca = marca;
//        this.medida = medida;
//        this.precio = precio;
//        this.cant_existente = cant_existente;
//        this.cant_minima = cant_minima;
//        this.cant_maxima = cant_maxima;
//        this.iva = iva;
//        this.estado = estado;
//        this.imagenes = imagenes;
//    }

    // NUEVO

    public Productos(String ruc, int cod_producto, String cod_barras, String nombre, int categoria, int marca, int medida, double precio, int cant_existente, int cant_minima, int cant_maxima, String iva, int estado, String imagenes) {
        this.ruc = ruc;
        this.cod_producto = cod_producto;
        this.cod_barras = cod_barras;
        this.nombre = nombre;
        this.categoria = categoria;
        this.marca = marca;
        this.medida = medida;
        this.precio = precio;
        this.cant_existente = cant_existente;
        this.cant_minima = cant_minima;
        this.cant_maxima = cant_maxima;
        this.iva = iva;
        this.estado = estado;
        this.imagenes = imagenes;
    }

    

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    
    

    public Productos(int cod_producto, int cant_existente) {
        this.cod_producto = cod_producto;
        this.cant_existente = cant_existente;
    }

    public int getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(int cod_producto) {
        this.cod_producto = cod_producto;
    }

    public String getCod_barras() {
        return cod_barras;
    }

    public void setCod_barras(String cod_barras) {
        this.cod_barras = cod_barras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getMarca() {
        return marca;
    }

    public void setMarca(int marca) {
        this.marca = marca;
    }

    public int getMedida() {
        return medida;
    }

    public void setMedida(int medida) {
        this.medida = medida;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCant_existente() {
        return cant_existente;
    }

    public void setCant_existente(int cant_existente) {
        this.cant_existente = cant_existente;
    }

    public int getCant_minima() {
        return cant_minima;
    }

    public void setCant_minima(int cant_minima) {
        this.cant_minima = cant_minima;
    }

    public int getCant_maxima() {
        return cant_maxima;
    }

    public void setCant_maxima(int cant_maxima) {
        this.cant_maxima = cant_maxima;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getImagenes() {
        return imagenes;
    }

    public void setImagenes(String imagenes) {
        this.imagenes = imagenes;
    }
    
   

    
    
}