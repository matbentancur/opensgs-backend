package opensgs.sistema.logica.beans;

import opensgs.sistema.datatypes.DtServidorAplicaciones;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.enums.Elemento;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.servicios.ServicioFechaHora;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "servidorAplicaciones",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"nombre"}
        ),
        schema = "sistema"
)
public class ServidorAplicaciones extends Servidor {

    @Column(nullable = false, columnDefinition = "varchar")
    @NotNull
    @NotBlank
    @Size(min = 1, max = 256)
    private String logPath;

    public ServidorAplicaciones() {
    }

    public ServidorAplicaciones(DtOpenSGSBean dtOpenSGSBean) {
        this((DtServidorAplicaciones) dtOpenSGSBean);
    }

    public ServidorAplicaciones(DtServidorAplicaciones dtServidorAplicaciones) {
        super(dtServidorAplicaciones);
        this.logPath = dtServidorAplicaciones.getLogPath();
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtServidorAplicaciones dtServidorAplicaciones = (DtServidorAplicaciones) dtOpenSGSManagedBean;
        super.modificar(dtServidorAplicaciones);
        this.logPath = dtServidorAplicaciones.getLogPath();
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    public DtServidorAplicaciones getDtServidorAplicaciones() {
        return new DtServidorAplicaciones(
                logPath,
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
    public DtServidorAplicaciones getDtOpenSGSBean() {
        return this.getDtServidorAplicaciones();
    }

    @Override
    public DtServidorAplicaciones getDataType() {
        return this.getDtServidorAplicaciones();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.ServidorAplicaciones;
    }

    @Override
    public String toString() {
        return super.toString() + "ServidorAplicaciones{" + "logPath=" + logPath + '}';
    }

}
