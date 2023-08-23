package opensgs.sistema.logica.beans;

import opensgs.sistema.datatypes.DtSistema;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.enums.Elemento;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.datatypes.DtServidorAplicaciones;
import opensgs.sistema.datatypes.DtServidorAutenticacion;
import opensgs.sistema.datatypes.DtServidorCorreo;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "sistema",
        schema = "sistema"
)
public class Sistema extends OpenSGSManagedBean {

    @Column(unique = true, nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String nombre;

    @Column(nullable = false, columnDefinition = "varchar")
    @NotNull
    @NotBlank
    @Size(min = 1, max = 256)
    private String url;

    @Column(nullable = false)
    @NotNull
    private boolean mantenimiento;

    @Column(nullable = false)
    @NotNull
    @Positive
    @Max(100000)
    private Integer cantidadMaximaUsuarios;

    @Column(nullable = false)
    @NotNull
    @Positive
    @Max(500)
    private Integer fileUploadMaxSize;

    @Column(nullable = false, length = 128)
    @NotNull
    @Size(min = 5, max = 128)
    @Email
    private String correoSoporte;

    @Column(nullable = false, columnDefinition = "varchar")
    @NotNull
    @NotBlank
    @Size(min = 1, max = 256)
    private String filesPath;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @NotNull
    private ServidorCorreo servidorCorreo;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @NotNull
    private ServidorAplicaciones servidorAplicaciones;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @NotNull
    private ServidorAutenticacion servidorAutenticacion;

    public Sistema() {
    }

    public Sistema(DtOpenSGSBean dtOpenSGSBean) {
        this((DtSistema) dtOpenSGSBean);
    }

    public Sistema(DtSistema dtSistema) {
        super();
        this.nombre = dtSistema.getNombre();
        this.url = dtSistema.getUrl();
        this.mantenimiento = dtSistema.isMantenimiento();
        this.cantidadMaximaUsuarios = dtSistema.getCantidadMaximaUsuarios();
        this.fileUploadMaxSize = dtSistema.getFileUploadMaxSize();
        this.correoSoporte = dtSistema.getCorreoSoporte();
        this.filesPath = dtSistema.getFilesPath();
        this.setServidorCorreo(dtSistema.getDtServidorCorreo());
        this.setServidorAplicaciones(dtSistema.getDtServidorAplicaciones());
        this.setServidorAutenticacion(dtSistema.getDtServidorAutenticacion());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(boolean mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    public Integer getCantidadMaximaUsuarios() {
        return cantidadMaximaUsuarios;
    }

    public void setCantidadMaximaUsuarios(Integer cantidadMaximaUsuarios) {
        this.cantidadMaximaUsuarios = cantidadMaximaUsuarios;
    }

    public Integer getFileUploadMaxSize() {
        return fileUploadMaxSize;
    }

    public void setFileUploadMaxSize(Integer fileUploadMaxSize) {
        this.fileUploadMaxSize = fileUploadMaxSize;
    }

    public String getCorreoSoporte() {
        return correoSoporte;
    }

    public void setCorreoSoporte(String correSoporte) {
        this.correoSoporte = correSoporte;
    }

    public String getFilesPath() {
        return filesPath;
    }

    public void setFilesPath(String filesPath) {
        this.filesPath = filesPath;
    }

    public ServidorCorreo getServidorCorreo() {
        return servidorCorreo;
    }

    public void setServidorCorreo(ServidorCorreo servidorCorreo) {
        this.servidorCorreo = servidorCorreo;
    }

    private void setServidorCorreo(DtServidorCorreo dtServidorCorreo) {
        if (dtServidorCorreo != null) {
            this.servidorCorreo = (ServidorCorreo) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtServidorCorreo);
        }
    }

    public ServidorAplicaciones getServidorAplicaciones() {
        return servidorAplicaciones;
    }

    public void setServidorAplicaciones(ServidorAplicaciones servidorAplicaciones) {
        this.servidorAplicaciones = servidorAplicaciones;
    }

    private void setServidorAplicaciones(DtServidorAplicaciones dtServidorAplicaciones) {
        if (dtServidorAplicaciones != null) {
            this.servidorAplicaciones = (ServidorAplicaciones) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtServidorAplicaciones);
        }
    }

    public ServidorAutenticacion getServidorAutenticacion() {
        return servidorAutenticacion;
    }

    public void setServidorAutenticacion(ServidorAutenticacion servidorAutenticacion) {
        this.servidorAutenticacion = servidorAutenticacion;
    }

    private void setServidorAutenticacion(DtServidorAutenticacion dtServidorAutenticacion) {
        if (dtServidorAutenticacion != null) {
            this.servidorAutenticacion = (ServidorAutenticacion) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtServidorAutenticacion);
        }
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtSistema dtSistema = (DtSistema) dtOpenSGSManagedBean;
        this.url = dtSistema.getUrl();
        this.mantenimiento = dtSistema.isMantenimiento();
        this.cantidadMaximaUsuarios = dtSistema.getCantidadMaximaUsuarios();
        this.fileUploadMaxSize = dtSistema.getFileUploadMaxSize();
        this.correoSoporte = dtSistema.getCorreoSoporte();
        this.filesPath = dtSistema.getFilesPath();
        this.setServidorCorreo(dtSistema.getDtServidorCorreo());
        this.setServidorAplicaciones(dtSistema.getDtServidorAplicaciones());
        this.setServidorAutenticacion(dtSistema.getDtServidorAutenticacion());
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    public DtSistema getDtSistema() {
        return new DtSistema(
                nombre,
                url,
                mantenimiento,
                cantidadMaximaUsuarios,
                fileUploadMaxSize,
                correoSoporte,
                filesPath,
                this.getServidorCorreo().getDtServidorCorreo(),
                this.getServidorAplicaciones().getDtServidorAplicaciones(),
                this.getServidorAutenticacion().getDtServidorAutenticacion(),
                this.isActivo(),
                this.isBorrado(),
                this.isAdministrable(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getModificado()),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion())
        );
    }

    @Override
    public DtSistema getDtOpenSGSBean() {
        return this.getDtSistema();
    }

    @Override
    public DtSistema getDataType() {
        return this.getDtSistema();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.Sistema;
    }

    @Override
    public String toString() {
        return super.toString() + "Sistema{" + "nombre=" + nombre + ", url=" + url + ", mantenimiento=" + mantenimiento + ", cantidadMaximaUsuarios=" + cantidadMaximaUsuarios + ", fileUploadMaxSize=" + fileUploadMaxSize + ", correoSoporte=" + correoSoporte + ", filesPath=" + filesPath + ", servidorCorreo=" + servidorCorreo + ", servidorAplicaciones=" + servidorAplicaciones + ", servidorAutenticacion=" + servidorAutenticacion + '}';
    }

}
