package djmeb;

public class Det_Pedido {
    private int detalle_cod;
    private int codigo_pedido;
    private int cod_producto;
    private int cantidad;
    private double precio_total;
    private double iva_compra;
    
    public Det_Pedido() {
        
    }

    public Det_Pedido(int cod_producto, int cantidad, double precio_total, double iva_compra) {
        this.cod_producto = cod_producto;
        this.cantidad = cantidad;
        this.precio_total = precio_total;
        this.iva_compra = iva_compra;
    }

//    public int getDetalle_cod() {
//        return detalle_cod;
//    }
//
//    public void setDetalle_cod(int detalle_cod) {
//        this.detalle_cod = detalle_cod;
//    }
//
//    public int getCodigo_pedido() {
//        return codigo_pedido;
//    }
//
//    public void setCodigo_pedido(int codigo_pedido) {
//        this.codigo_pedido = codigo_pedido;
//    }

    public int getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(int cod_producto) {
        this.cod_producto = cod_producto;
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(double precio_total) {
        this.precio_total = precio_total;
    }

    public double getIva_compra() {
        return iva_compra;
    }

    public void setIva_compra(double iva_compra) {
        this.iva_compra = iva_compra;
    }
    
    
}
