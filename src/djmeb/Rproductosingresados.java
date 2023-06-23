
package djmeb;

import java.time.LocalDate;

/**
 *
 * @author ediss
 */
public class Rproductosingresados {
   private int Rpi_cod;
   private int Rpi_Cod_bodeguero;
   private String Rpi_ruc_provedor;
   private LocalDate Rpi_fecha;
   private double Rpi_subtotal_ap;
   private double Rpi_total_ap;

    public Rproductosingresados() {
    }

    public Rproductosingresados(int Rpi_cod, int Rpi_Cod_bodeguero, String Rpi_ruc_provedor, LocalDate Rpi_fecha, double Rpi_subtotal_ap, double Rpi_total_ap) {
        this.Rpi_cod = Rpi_cod;
        this.Rpi_Cod_bodeguero = Rpi_Cod_bodeguero;
        this.Rpi_ruc_provedor = Rpi_ruc_provedor;
        this.Rpi_fecha = Rpi_fecha;
        this.Rpi_subtotal_ap = Rpi_subtotal_ap;
        this.Rpi_total_ap = Rpi_total_ap;
    }

    public int getRpi_cod() {
        return Rpi_cod;
    }

    public void setRpi_cod(int Rpi_cod) {
        this.Rpi_cod = Rpi_cod;
    }

    public int getRpi_Cod_bodeguero() {
        return Rpi_Cod_bodeguero;
    }

    public void setRpi_Cod_bodeguero(int Rpi_Cod_bodeguero) {
        this.Rpi_Cod_bodeguero = Rpi_Cod_bodeguero;
    }

    public String getRpi_ruc_provedor() {
        return Rpi_ruc_provedor;
    }

    public void setRpi_ruc_provedor(String Rpi_ruc_provedor) {
        this.Rpi_ruc_provedor = Rpi_ruc_provedor;
    }

    public LocalDate getRpi_fecha() {
        return Rpi_fecha;
    }

    public void setRpi_fecha(LocalDate Rpi_fecha) {
        this.Rpi_fecha = Rpi_fecha;
    }

    public double getRpi_subtotal_ap() {
        return Rpi_subtotal_ap;
    }

    public void setRpi_subtotal_ap(double Rpi_subtotal_ap) {
        this.Rpi_subtotal_ap = Rpi_subtotal_ap;
    }

    public double getRpi_total_ap() {
        return Rpi_total_ap;
    }

    public void setRpi_total_ap(double Rpi_total_ap) {
        this.Rpi_total_ap = Rpi_total_ap;
    }
   
   
   
}
