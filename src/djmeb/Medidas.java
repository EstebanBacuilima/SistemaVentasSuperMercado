package djmeb;

public class Medidas {
    private int Medida_cod;
    private String Medida_nombre;

    public Medidas() {
    }

    public Medidas(int Medida_cod, String Medida_nombre) {
        this.Medida_cod = Medida_cod;
        this.Medida_nombre = Medida_nombre;
    }

    public int getMedida_cod() {
        return Medida_cod;
    }

    public void setMedida_cod(int Medida_cod) {
        this.Medida_cod = Medida_cod;
    }

    public String getMedida_nombre() {
        return Medida_nombre;
    }

    public void setMedida_nombre(String Medida_nombre) {
        this.Medida_nombre = Medida_nombre;
    }
    
    
    
    
}
