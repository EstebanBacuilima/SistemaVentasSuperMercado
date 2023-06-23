package djmeb;

public class Contador extends Empleado{
    
    private double bono;
    private int cod_contador;

    public Contador() {
        super();
    }

    public Contador(double bono, int cod_contador, int código, int salario, String horario, int id, String cedula, String nombre, String apellido, int edad, String correo, String direccion, String genero, String telefono) {
        super(código, salario, horario, id, cedula, nombre, apellido, edad, correo, direccion, genero, telefono);
        this.bono = bono;
        this.cod_contador = cod_contador;
    }

    public int getCod_contador() {
        return cod_contador;
    }

    public void setCod_contador(int cod_contador) {
        this.cod_contador = cod_contador;
    }

    public Contador(double bono) {
        this.bono = bono;
    }

    public double getBono() {
        return bono;
    }

    public void setBono(double bono) {
        this.bono = bono;
    }

    
}
