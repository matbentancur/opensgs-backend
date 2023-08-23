package opensgs.usuarios.datatypes;

public final class DtRegistrarUsuario {
    
    private String documentoTipo;
    private String documento;
    private String email;
    private String clave;
    private String nombres;
    private String apellidos;
    private String telefono;

    public DtRegistrarUsuario() {
    }

    public DtRegistrarUsuario(String documentoTipo, String documento, String email, String clave, String nombres, String apellidos, String telefono) {
        this.documentoTipo = documentoTipo;
        this.documento = documento;
        this.email = email;
        this.clave = clave;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }

    public String getDocumentoTipo() {
        return documentoTipo;
    }

    public void setDocumentoTipo(String documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    @Override
    public String toString() {
        return "DtRegistrarUsuario{" + "documentoTipo=" + documentoTipo + ", documento=" + documento + ", email=" + email + ", clave=" + clave + ", nombres=" + nombres + ", apellidos=" + apellidos + ", telefono=" + telefono + '}';
    }
 
}
