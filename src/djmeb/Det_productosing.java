package djmeb;

public class Det_productosing {

    private int cod_Rpi;
    private int dping_cod;
    private String dping_nombre;
    private double dping_preu;
    private int dping_cant;
    private int dping_cantingresada;
    private int dping_cantdefectuosa;
    private double dping_pret;

    public Det_productosing() {
    }

    public Det_productosing(int cod_Rpi, int dping_cod, String dping_nombre, double dping_preu, int dping_cant, int dping_cantingresada, int dping_cantdefectuosa, double dping_pret) {
        this.cod_Rpi = cod_Rpi;
        this.dping_cod = dping_cod;
        this.dping_nombre = dping_nombre;
        this.dping_preu = dping_preu;
        this.dping_cant = dping_cant;
        this.dping_cantingresada = dping_cantingresada;
        this.dping_cantdefectuosa = dping_cantdefectuosa;
        this.dping_pret = dping_pret;
    }

    public int getCod_Rpi() {
        return cod_Rpi;
    }

    public void setCod_Rpi(int cod_Rpi) {
        this.cod_Rpi = cod_Rpi;
    }

    public int getDping_cod() {
        return dping_cod;
    }

    public void setDping_cod(int dping_cod) {
        this.dping_cod = dping_cod;
    }

    public String getDping_nombre() {
        return dping_nombre;
    }

    public void setDping_nombre(String dping_nombre) {
        this.dping_nombre = dping_nombre;
    }

    public double getDping_preu() {
        return dping_preu;
    }

    public void setDping_preu(double dping_preu) {
        this.dping_preu = dping_preu;
    }

    public int getDping_cant() {
        return dping_cant;
    }

    public void setDping_cant(int dping_cant) {
        this.dping_cant = dping_cant;
    }

    public int getDping_cantingresada() {
        return dping_cantingresada;
    }

    public void setDping_cantingresada(int dping_cantingresada) {
        this.dping_cantingresada = dping_cantingresada;
    }

    public int getDping_cantdefectuosa() {
        return dping_cantdefectuosa;
    }

    public void setDping_cantdefectuosa(int dping_cantdefectuosa) {
        this.dping_cantdefectuosa = dping_cantdefectuosa;
    }

    public double getDping_pret() {
        return dping_pret;
    }

    public void setDping_pret(double dping_pret) {
        this.dping_pret = dping_pret;
    }

}

