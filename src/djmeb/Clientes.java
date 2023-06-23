package djmeb;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


public class Clientes extends Persona {
    
    private int cod_cliem;
    private LocalDate fecha_nac_cli;
    public Clientes() {
        super();
    }

    public Clientes(int cod_cliem, LocalDate fecha_nac_cli) {
        this.cod_cliem = cod_cliem;
        this.fecha_nac_cli = fecha_nac_cli;
    }

    public Clientes(int cod_cliem, LocalDate fecha_nac_cli, int id, String cedula, String nombre, String apellido, int edad, String correo, String direccion, String genero, String telefono) {
        super(id, cedula, nombre, apellido, edad, correo, direccion, genero, telefono);
        this.cod_cliem = cod_cliem;
        this.fecha_nac_cli = fecha_nac_cli;
    }

    public int getCod_cliem() {
        return cod_cliem;
    }

    public void setCod_cliem(int cod_cliem) {
        this.cod_cliem = cod_cliem;
    }

    public LocalDate getFecha_nac_cli() {
        return fecha_nac_cli;
    }

    public void setFecha_nac_cli(LocalDate fecha_nac_cli) {
        this.fecha_nac_cli = fecha_nac_cli;
    }
    
    
    
}
