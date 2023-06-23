package djmeb;

public class Categorias {
    private int Cat_cod;
    private String Cat_nombre;

    public Categorias(int Cat_cod, String Cat_nombre) {
        this.Cat_cod = Cat_cod;
        this.Cat_nombre = Cat_nombre;
    }

    public int getCat_cod() {
        return Cat_cod;
    }

    public void setCat_cod(int Cat_cod) {
        this.Cat_cod = Cat_cod;
    }

    public String getCat_nombre() {
        return Cat_nombre;
    }

    public void setCat_nombre(String Cat_nombre) {
        this.Cat_nombre = Cat_nombre;
    }

    
}
