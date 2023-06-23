package djmeb;

public class Proveedores  {
    
    private String Ruc_provedor;
    private String nom_empresa;
    private String direccion;
    private String telefono;
    private String Cedula_Responsable;
    private String nom_Res;
    private String correo;

    public Proveedores() {
    }

    public Proveedores(String Ruc_provedor, String nom_empresa, String direccion, String telefono, String Cedula_Responsable, String nom_Res, String correo) {
        this.Ruc_provedor = Ruc_provedor;
        this.nom_empresa = nom_empresa;
        this.direccion = direccion;
        this.telefono = telefono;
        this.Cedula_Responsable = Cedula_Responsable;
        this.nom_Res = nom_Res;
        this.correo = correo;
    }

    public String getRuc_provedor() {
        return Ruc_provedor;
    }

    public void setRuc_provedor(String Ruc_provedor) {
        this.Ruc_provedor = Ruc_provedor;
    }

    public String getNom_empresa() {
        return nom_empresa;
    }

    public void setNom_empresa(String nom_empresa) {
        this.nom_empresa = nom_empresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCedula_Responsable() {
        return Cedula_Responsable;
    }

    public void setCedula_Responsable(String Cedula_Responsable) {
        this.Cedula_Responsable = Cedula_Responsable;
    }

    public String getNom_Res() {
        return nom_Res;
    }

    public void setNom_Res(String nom_Res) {
        this.nom_Res = nom_Res;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    

    @Override
    public String toString() {
        return "Proveedores{" + "Ruc_provedor=" + Ruc_provedor + ", nom_empresa=" + nom_empresa + ", direccion=" + direccion + ", telefono=" + telefono + ", Cedula_Responsable=" + Cedula_Responsable + ", nom_Res=" + nom_Res + ", correo=" + correo + '}';
    }
    
    
    
    
}
