
package djmeb;

public class Usuario  extends Empleado{
    
    private int ID_empleado;
    private String Ced_empleado;
    private String Usuario;
    private String contraseña;
    private String tipo;

    public Usuario() {
        super();
    }

    public Usuario(int ID_empleado, String Ced_empleado, String Usuario, String contraseña, String tipo, int código) {
        super(código);
        this.ID_empleado = ID_empleado;
        this.Ced_empleado = Ced_empleado;
        this.Usuario = Usuario;
        this.contraseña = contraseña;
        this.tipo = tipo;
    }

    
    public int getID_empleado() {
        return ID_empleado;
    }

    public void setID_empleado(int ID_empleado) {
        this.ID_empleado = ID_empleado;
    }
  
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getCed_empleado() {
        return Ced_empleado;
    }

    public void setCed_empleado(String Ced_empleado) {
        this.Ced_empleado = Ced_empleado;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
    
}
