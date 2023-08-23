package opensgs.sistema.logica.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.enums.Elemento;
import opensgs.logica.servicios.ServicioEnum;
import opensgs.sistema.datatypes.DtServidorAutenticacion;
import opensgs.sistema.enums.ServidorAutenticacionTipo;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.servicios.ServicioFechaHora;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "servidorAutenticacion",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"nombre"}
        ),
        schema = "sistema"
)
public class ServidorAutenticacion extends Servidor {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    @NotNull
    private ServidorAutenticacionTipo tipo;

    public ServidorAutenticacion() {
    }

    public ServidorAutenticacion(DtOpenSGSBean dtOpenSGSBean) {
        this((DtServidorAutenticacion) dtOpenSGSBean);
    }

    public ServidorAutenticacion(DtServidorAutenticacion dtServidorAutenticacion) {
        super(dtServidorAutenticacion);
        this.tipo = ServicioEnum.getInstance().parseEnum(ServidorAutenticacionTipo.class, dtServidorAutenticacion.getTipo());
    }

    public ServidorAutenticacionTipo getTipo() {
        return tipo;
    }

    public void setTipo(ServidorAutenticacionTipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtServidorAutenticacion dtServidorAutenticacion = (DtServidorAutenticacion) dtOpenSGSManagedBean;
        super.modificar(dtServidorAutenticacion);
        this.tipo = ServicioEnum.getInstance().parseEnum(ServidorAutenticacionTipo.class, dtServidorAutenticacion.getTipo());
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    public DtServidorAutenticacion getDtServidorAutenticacion() {
        return new DtServidorAutenticacion(
                tipo.toString(),
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
    public DtServidorAutenticacion getDtOpenSGSBean() {
        return this.getDtServidorAutenticacion();
    }

    @Override
    public DtServidorAutenticacion getDataType() {
        return this.getDtServidorAutenticacion();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.ServidorAutenticacion;
    }

    @Override
    public String toString() {
        return super.toString() + "ServidorAutenticacion{" + "tipo=" + tipo + '}';
    }

}
