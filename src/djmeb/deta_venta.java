package djmeb;

import Ventanas.Conexion;
import java.sql.Connection;

public class deta_venta {
    private int cod_de_ven;
    private int cod_pro;
    private int cantida;
    private int cod_deven;

    public deta_venta() {
    }

    public deta_venta(int cod_de_ven, int cod_pro, int cantida, int cod_deven) {
        this.cod_de_ven = cod_de_ven;
        this.cod_pro = cod_pro;
        this.cantida = cantida;
        this.cod_deven = cod_deven;
    }

    public int getCod_de_ven() {
        return cod_de_ven;
    }

    public void setCod_de_ven(int cod_de_ven) {
        this.cod_de_ven = cod_de_ven;
    }

    public int getCod_pro() {
        return cod_pro;
    }

    public void setCod_pro(int cod_pro) {
        this.cod_pro = cod_pro;
    }

    public int getCantida() {
        return cantida;
    }

    public void setCantida(int cantida) {
        this.cantida = cantida;
    }

    public int getCod_deven() {
        return cod_deven;
    }

    public void setCod_deven(int cod_deven) {
        this.cod_deven = cod_deven;
    }

}
