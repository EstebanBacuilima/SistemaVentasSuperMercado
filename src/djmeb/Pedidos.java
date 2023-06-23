package djmeb;

import java.time.LocalDate;

public class Pedidos {
    private int codi_pedido;
    private int contador_codigo;
    private String proveedor_ruc;
    private LocalDate pedido_fecha_emisión;
    private LocalDate pedido_fecha_entrega;
    private double total_general;
    private String situacion;

    public Pedidos(String proveedor_ruc, LocalDate pedido_fecha_emisión, LocalDate pedido_fecha_entrega, double total_general, String situacion) {
        this.proveedor_ruc = proveedor_ruc;
        this.pedido_fecha_emisión = pedido_fecha_emisión;
        this.pedido_fecha_entrega = pedido_fecha_entrega;
        this.total_general = total_general;
        this.situacion = situacion;
    }

    
    public Pedidos() {
    }

    public Pedidos(int codi_pedido, int contador_codigo, String proveedor_ruc, LocalDate pedido_fecha_emisión, LocalDate pedido_fecha_entrega) {
        this.codi_pedido = codi_pedido;
        this.contador_codigo = contador_codigo;
        this.proveedor_ruc = proveedor_ruc;
        this.pedido_fecha_emisión = pedido_fecha_emisión;
        this.pedido_fecha_entrega = pedido_fecha_entrega;
    }

    public int getCodi_pedido() {
        return codi_pedido;
    }

    public void setCodi_pedido(int codi_pedido) {
        this.codi_pedido = codi_pedido;
    }

    public int getContador_codigo() {
        return contador_codigo;
    }

    public void setContador_codigo(int contador_codigo) {
        this.contador_codigo = contador_codigo;
    }

    public String getProveedor_ruc() {
        return proveedor_ruc;
    }

    public void setProveedor_ruc(String proveedor_ruc) {
        this.proveedor_ruc = proveedor_ruc;
    }

    public LocalDate getPedido_fecha_emisión() {
        return pedido_fecha_emisión;
    }

    public void setPedido_fecha_emisión(LocalDate pedido_fecha_emisión) {
        this.pedido_fecha_emisión = pedido_fecha_emisión;
    }

    public LocalDate getPedido_fecha_entrega() {
        return pedido_fecha_entrega;
    }

    public void setPedido_fecha_entrega(LocalDate pedido_fecha_entrega) {
        this.pedido_fecha_entrega = pedido_fecha_entrega;
    }

    public double getTotal_general() {
        return total_general;
    }

    public void setTotal_general(double total_general) {
        this.total_general = total_general;
    }
    
    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }
}
