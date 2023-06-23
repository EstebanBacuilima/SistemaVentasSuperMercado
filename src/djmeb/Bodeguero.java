package djmeb;

public class Bodeguero extends Empleado {

    private String Area_Trabajo;
    private int cod_bodeguero;

    public Bodeguero() {
    }

    public Bodeguero(String Area_Trabajo, int cod_bodeguero, int código, int salario, String horario, int id, String cedula, String nombre, String apellido, int edad, String correo, String direccion, String genero, String telefono) {
        super(código, salario, horario, id, cedula, nombre, apellido, edad, correo, direccion, genero, telefono);
        this.Area_Trabajo = Area_Trabajo;
        this.cod_bodeguero = cod_bodeguero;
    }


    public int getCod_bodeguero() {
        return cod_bodeguero;
    }

    public void setCod_bodeguero(int cod_bodeguero) {
        this.cod_bodeguero = cod_bodeguero;
    }

    public String getArea_Trabajo() {
        return Area_Trabajo;
    }

    public void setArea_Trabajo(String Area_Trabajo) {
        this.Area_Trabajo = Area_Trabajo;
    }

}
