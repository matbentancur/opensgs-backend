package opensgs.usuarios.datatypes;

import java.util.ArrayList;
import java.util.List;
import opensgs.datatypes.DtOpenSGSManagedBean;

public class DtUsuario extends DtOpenSGSManagedBean {

    private String documentoTipo;
    private String documento;
    private String email;
    private String nombres;
    private String apellidos;
    private String telefono;
    private DtPerfil dtPerfil;
    private List<DtPerfilAplicacion> dtPerfilesAplicacion = new ArrayList<>();

    public DtUsuario() {
    }

    public DtUsuario(String documentoTipo, String documento, String email, String nombres, String apellidos, String telefono, DtPerfil dtPerfil, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion, List<DtPerfilAplicacion> dtPerfilesAplicacion) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.documentoTipo = documentoTipo;
        this.documento = documento;
        this.email = email;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.dtPerfil = dtPerfil;
        this.dtPerfilesAplicacion = dtPerfilesAplicacion;
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

    public DtPerfil getDtPerfil() {
        return dtPerfil;
    }

    public void setDtPerfil(DtPerfil dtPerfil) {
        this.dtPerfil = dtPerfil;
    }

    public List<DtPerfilAplicacion> getDtPerfilesAplicacion() {
        return dtPerfilesAplicacion;
    }

    public void setDtPerfilesAplicacion(List<DtPerfilAplicacion> dtPerfilesAplicacion) {
        this.dtPerfilesAplicacion = dtPerfilesAplicacion;
    }

    @Override
    public String getClassName() {
        return "Usuario";
    }

    @Override
    public String toString() {
        return super.toString() + "DtUsuario{" + "documentoTipo=" + documentoTipo + ", documento=" + documento + ", email=" + email + ", nombres=" + nombres + ", apellidos=" + apellidos + ", telefono=" + telefono + ", dtPerfil=" + dtPerfil + ", dtPerfilesAplicacion=" + dtPerfilesAplicacion + '}';
    }

}
