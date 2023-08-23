package opensgs.solicitudes.paginas.datatypes.cep;

import opensgs.sistema.datatypes.DtPaginaAplicacion;
import opensgs.solicitudes.datatypes.DtPaginaSolicitud;

public class DtDatosPersonalesCEP extends DtPaginaSolicitud {

    private String apellidos;
    private String nombres;
    private String documento;
    private String email;
    private String genero;
    private String fechaNacimiento;
    private String nacionalidad;
    private String celular;
    private String departamentoPaisResidencia;

    public DtDatosPersonalesCEP() {
    }

    public DtDatosPersonalesCEP(String apellidos, String nombres, String documento, String email, String genero, String fechaNacimiento, String nacionalidad, String celular, String departamentoPaisResidencia, DtPaginaAplicacion dtPaginaAplicacion, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(dtPaginaAplicacion, activo, borrado, administrable, modificado, id, creacion);
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.documento = documento;
        this.email = email;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.celular = celular;
        this.departamentoPaisResidencia = departamentoPaisResidencia;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDepartamentoPaisResidencia() {
        return departamentoPaisResidencia;
    }

    public void setDepartamentoPaisResidencia(String departamentoPaisResidencia) {
        this.departamentoPaisResidencia = departamentoPaisResidencia;
    }

    @Override
    public String getClassName() {
        return "DatosPersonalesCEP";
    }

    @Override
    public String toString() {
        return super.toString() + "DtDatosPersonalesCEP{" + "apellidos=" + apellidos + ", nombres=" + nombres + ", documento=" + documento + ", email=" + email + ", genero=" + genero + ", fechaNacimiento=" + fechaNacimiento + ", nacionalidad=" + nacionalidad + ", celular=" + celular + ", departamentoPaisResidencia=" + departamentoPaisResidencia + '}';
    }

}
