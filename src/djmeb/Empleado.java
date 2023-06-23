package djmeb;

public abstract class Empleado extends Persona {

    private int código;
    private int salario;
    private int cod_empleado;
    private String horario;

    public Empleado() {
        super();
    }

    public Empleado(int código, int salario, String horario, int id, String cedula, String nombre, String apellido, int edad, String correo, String direccion, String genero, String telefono) {
        super(id, cedula, nombre, apellido, edad, correo, direccion, genero, telefono);
        this.código = código;
        this.salario = salario;
        this.horario = horario;
    }

    public Empleado(int código) {
        this.código = código;
    }

    public int getCódigo() {
        return código;
    }

    public void setCódigo(int código) {
        this.código = código;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public int getCod_empleado() {
        return cod_empleado;
    }

    public void setCod_empleado(int cod_empleado) {
        this.cod_empleado = cod_empleado;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Empleado{" + "salario=" + salario + ", cod_empleado=" + cod_empleado + ", horario=" + horario + '}';
    }

}
