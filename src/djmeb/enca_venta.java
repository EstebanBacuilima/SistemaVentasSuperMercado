package djmeb;

import java.time.LocalDate;

public class enca_venta {

    private int cod_ven;
    private int id_cliente;
    private int cod_cajero;
    private LocalDate fecha_compra;
    private double total;
    public enca_venta() {
    }

    public enca_venta(int cod_ven, int id_cliente, int cod_cajero, LocalDate fecha_compra, double total) {
        this.cod_ven = cod_ven;
        this.id_cliente = id_cliente;
        this.cod_cajero = cod_cajero;
        this.fecha_compra = fecha_compra;
        this.total = total;
    }

    public int getCod_ven() {
        return cod_ven;
    }

    public void setCod_ven(int cod_ven) {
        this.cod_ven = cod_ven;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getCod_cajero() {
        return cod_cajero;
    }

    public void setCod_cajero(int cod_cajero) {
        this.cod_cajero = cod_cajero;
    }

    public LocalDate getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(LocalDate fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


}



