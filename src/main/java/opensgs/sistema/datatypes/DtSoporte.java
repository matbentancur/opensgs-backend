package opensgs.sistema.datatypes;

public class DtSoporte {

    private String email;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String descripcion;

    public DtSoporte() {
    }

    public DtSoporte(String email, String nombres, String apellidos, String telefono, String descripcion) {
        this.email = email;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.descripcion = descripcion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "DtSoporte{" + "email=" + email + ", nombres=" + nombres + ", apellidos=" + apellidos + ", telefono=" + telefono + ", descripcion=" + descripcion + '}';
    }

}
