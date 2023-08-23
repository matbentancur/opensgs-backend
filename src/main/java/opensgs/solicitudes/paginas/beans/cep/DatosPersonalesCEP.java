package opensgs.solicitudes.paginas.beans.cep;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.enums.Elemento;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.solicitudes.datatypes.DtPaginaSolicitud;
import opensgs.solicitudes.logica.beans.PaginaSolicitud;
import opensgs.solicitudes.paginas.datatypes.cep.DtDatosPersonalesCEP;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "datosPersonalesCEP",
        schema = "cep"
)
public class DatosPersonalesCEP extends PaginaSolicitud {

    @Column(nullable = true, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 2, max = 128)
    private String apellidos;

    @Column(nullable = true, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 2, max = 128)
    private String nombres;

    @Column(nullable = true, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 7, max = 128)
    private String documento;

    @Column(nullable = true, length = 128)
    @NotNull
    @Size(min = 5, max = 128)
    @Email
    private String email;

    @Column(nullable = true, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String genero;

    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date fechaNacimiento;

    @Column(nullable = true, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String nacionalidad;

    @Column(nullable = true, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 8, max = 128)
    private String celular;

    @Column(nullable = true, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String departamentoPaisResidencia;

    public DatosPersonalesCEP() {
    }

    public DatosPersonalesCEP(DtPaginaSolicitud dtPaginaSolicitud) {
        this((DtDatosPersonalesCEP) dtPaginaSolicitud);
    }

    public DatosPersonalesCEP(DtDatosPersonalesCEP dtDatosPersonalesCEP) {
        super();
        this.apellidos = dtDatosPersonalesCEP.getApellidos();
        this.nombres = dtDatosPersonalesCEP.getNombres();
        this.documento = dtDatosPersonalesCEP.getDocumento();
        this.email = dtDatosPersonalesCEP.getEmail();
        this.genero = dtDatosPersonalesCEP.getGenero();
        this.fechaNacimiento = ServicioFechaHora.getInstance().stringToDate(dtDatosPersonalesCEP.getFechaNacimiento());
        this.nacionalidad = dtDatosPersonalesCEP.getNacionalidad();
        this.celular = dtDatosPersonalesCEP.getCelular();
        this.departamentoPaisResidencia = dtDatosPersonalesCEP.getDepartamentoPaisResidencia();
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
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

    public DtDatosPersonalesCEP getDtDatosPersonalesCEP() {
        String fechaNacimientoString = ServicioFechaHora.getInstance().dateToString(fechaNacimiento);
        return new DtDatosPersonalesCEP(
                apellidos,
                nombres,
                documento,
                email,
                genero,
                fechaNacimientoString,
                nacionalidad,
                celular,
                departamentoPaisResidencia,
                this.getPaginaAplicacion().getDtPaginaAplicacion(),
                this.isActivo(),
                this.isBorrado(),
                this.isAdministrable(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getModificado()),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion())
        );
    }

    @Override
    public DtDatosPersonalesCEP getDtOpenSGSBean() {
        return this.getDtDatosPersonalesCEP();
    }

    @Override
    public DtDatosPersonalesCEP getDataType() {
        return this.getDtDatosPersonalesCEP();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.PaginaSolicitud;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtDatosPersonalesCEP dtDatosPersonalesCEP = (DtDatosPersonalesCEP) dtOpenSGSManagedBean;
        this.apellidos = dtDatosPersonalesCEP.getApellidos();
        this.nombres = dtDatosPersonalesCEP.getNombres();
        this.documento = dtDatosPersonalesCEP.getDocumento();
        this.email = dtDatosPersonalesCEP.getEmail();
        this.genero = dtDatosPersonalesCEP.getGenero();
        this.fechaNacimiento = ServicioFechaHora.getInstance().stringToDate(dtDatosPersonalesCEP.getFechaNacimiento());
        this.nacionalidad = dtDatosPersonalesCEP.getNacionalidad();
        this.celular = dtDatosPersonalesCEP.getCelular();
        this.departamentoPaisResidencia = dtDatosPersonalesCEP.getDepartamentoPaisResidencia();
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

}
