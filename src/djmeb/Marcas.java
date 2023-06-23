package djmeb;

public class Marcas {
    private int Marca_cod;
    private String Marca_nombre;

    public Marcas(int Marca_cod, String Marca_nombre) {
        this.Marca_cod = Marca_cod;
        this.Marca_nombre = Marca_nombre;
    }

    public int getMarca_cod() {
        return Marca_cod;
    }

    public void setMarca_cod(int Marca_cod) {
        this.Marca_cod = Marca_cod;
    }

    public String getMarca_nombre() {
        return Marca_nombre;
    }

    public void setMarca_nombre(String Marca_nombre) {
        this.Marca_nombre = Marca_nombre;
    }

    
}
