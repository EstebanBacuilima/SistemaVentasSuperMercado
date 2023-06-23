package djmeb;


public class Gerente extends Empleado{
    
    private String ruc;

    public Gerente(String ruc, int código, int salario, String horario, int id, String cedula, String nombre, String apellido, int edad, String correo, String direccion, String genero, String telefono) {
        super(código, salario, horario, id, cedula, nombre, apellido, edad, correo, direccion, genero, telefono);
        this.ruc = ruc;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    
    
    
    
}
