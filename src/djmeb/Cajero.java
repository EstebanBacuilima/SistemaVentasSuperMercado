package djmeb;

import java.sql.PreparedStatement;

public class Cajero extends Empleado{
    
    private int num_caja;
    private int cod_cajero;

    public Cajero() {
        super();
    }

    public Cajero(int num_caja, int cod_cajero, int código, int salario, String horario, int id, String cedula, String nombre, String apellido, int edad, String correo, String direccion, String genero, String telefono) {
        super(código, salario, horario, id, cedula, nombre, apellido, edad, correo, direccion, genero, telefono);
        this.num_caja = num_caja;
        this.cod_cajero = cod_cajero;
    }
    
    
    public Cajero(int num_caja) {
        this.num_caja = num_caja;
    }

    public int getCod_cajero() {
        return cod_cajero;
    }

    public void setCod_cajero(int cod_cajero) {
        this.cod_cajero = cod_cajero;
    }


    public int getNum_caja() {
        return num_caja;
    }

    public void setNum_caja(int num_caja) {
        this.num_caja = num_caja;
    }

    @Override
    public String toString() {
        return "Cajero{" + "num_caja=" + num_caja + '}';
    }

}
