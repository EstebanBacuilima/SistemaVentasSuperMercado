package djmeb;

public abstract class Persona {
    
    private int id;
    private String cedula;
    private String nombre;
    private String apellido;
    private int edad;
    private String correo;
    private String direccion;
    private String genero;
    private String telefono;

    public Persona() {
    }

    public Persona(String cedula, String nombre, String apellido, int edad, String correo, String direccion, String genero, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.correo = correo;
        this.direccion = direccion;
        this.genero = genero;
        this.telefono = telefono;
    }
    
     public Persona(int id, String cedula, String nombre, String apellido, int edad, String correo, String direccion, String genero, String telefono) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.correo = correo;
        this.direccion = direccion;
        this.genero = genero;
        this.telefono = telefono;
    }

     public Persona(String cedula, String nombre, String apellido, String correo, String direccion, String genero, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.direccion = direccion;
        this.genero = genero;
        this.telefono = telefono;
    }
    
    public Persona(String cedula, String nombre, String apellido, String correo, String direccion, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    


    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Persona{" + "cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", correo=" + correo + ", direccion=" + direccion + ", genero=" + genero + ", telefono=" + telefono + '}';
    }

    
    
    
}
