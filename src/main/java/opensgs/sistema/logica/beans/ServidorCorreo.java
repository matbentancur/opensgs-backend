package opensgs.sistema.logica.beans;

import opensgs.sistema.datatypes.DtServidorCorreo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.enums.Elemento;
import opensgs.logica.servicios.ServicioEnum;
import opensgs.sistema.enums.ServidorCorreoIdentificacion;
import opensgs.sistema.enums.ServidorCorreoSeguridad;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.servicios.ServicioFechaHora;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "servidorCorreo",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"nombre"}
        ),
        schema = "sistema"
)
public class ServidorCorreo extends Servidor {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    @NotNull
    private ServidorCorreoSeguridad seguridad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    @NotNull
    private ServidorCorreoIdentificacion identificacion;

    @Column(nullable = false, length = 128)
    @NotNull
    @Size(min = 5, max = 128)
    @Email
    private String desdeCorreo;

    @Column(nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String desdeNombre;

    public ServidorCorreo() {
    }

    public ServidorCorreo(DtOpenSGSBean dtOpenSGSBean) {
        this((DtServidorCorreo) dtOpenSGSBean);
    }

    public ServidorCorreo(DtServidorCorreo dtServidorCorreo) {
        super(dtServidorCorreo);
        this.seguridad = ServicioEnum.getInstance().parseEnum(ServidorCorreoSeguridad.class, dtServidorCorreo.getSeguridad());
        this.identificacion = ServicioEnum.getInstance().parseEnum(ServidorCorreoIdentificacion.class, dtServidorCorreo.getIdentificacion());
        this.desdeCorreo = dtServidorCorreo.getDesdeCorreo();
        this.desdeNombre = dtServidorCorreo.getDesdeNombre();
    }

    public ServidorCorreoSeguridad getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(ServidorCorreoSeguridad seguridad) {
        this.seguridad = seguridad;
    }

    public ServidorCorreoIdentificacion getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(ServidorCorreoIdentificacion identificacion) {
        this.identificacion = identificacion;
    }

    public String getDesdeCorreo() {
        return desdeCorreo;
    }

    public void setDesdeCorreo(String desdeCorreo) {
        this.desdeCorreo = desdeCorreo;
    }

    public String getDesdeNombre() {
        return desdeNombre;
    }

    public void setDesdeNombre(String desdeNombre) {
        this.desdeNombre = desdeNombre;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtServidorCorreo dtServidorCorreo = (DtServidorCorreo) dtOpenSGSManagedBean;
        super.modificar(dtServidorCorreo);
        this.seguridad = ServicioEnum.getInstance().parseEnum(ServidorCorreoSeguridad.class, dtServidorCorreo.getSeguridad());
        this.identificacion = ServicioEnum.getInstance().parseEnum(ServidorCorreoIdentificacion.class, dtServidorCorreo.getIdentificacion());
        this.desdeCorreo = dtServidorCorreo.getDesdeCorreo();
        this.desdeNombre = dtServidorCorreo.getDesdeNombre();
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    public DtServidorCorreo getDtServidorCorreo() {
        return new DtServidorCorreo(
                seguridad.toString(),
                identificacion.toString(),
                desdeCorreo, desdeNombre,
                this.getNombre(),
                this.getServidor(),
                this.getPuerto(),
                this.getUsuario(),
                this.getPassword(),
                this.getId(),
                this.isActivo(),
                this.isBorrado(),
                this.isAdministrable(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion()),
                ServicioFechaHora.getInstance().dateTimeToString(this.getModificado())
        );
    }

    @Override
    public DtServidorCorreo getDtOpenSGSBean() {
        return this.getDtServidorCorreo();
    }

    @Override
    public DtServidorCorreo getDataType() {
        return this.getDtServidorCorreo();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.ServidorCorreo;
    }

    @Override
    public String toString() {
        return super.toString() + "ServidorCorreo{" + "seguridad=" + seguridad + ", identificacion=" + identificacion + ", desdeCorreo=" + desdeCorreo + ", desdeNombre=" + desdeNombre + '}';
    }

}
