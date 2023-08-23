package opensgs.sistema.logica.beans;

import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.enums.Elemento;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.logica.servicios.ServicioEnum;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.sistema.datatypes.DtConstancia;
import opensgs.sistema.enums.ConstanciaTipo;
import opensgs.sistema.persistencia.manejadores.ManejadorArchivo;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "constancia",
        schema = "sistema"
)
public class Constancia extends OpenSGSManagedBean {

    @Column(nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    @NotNull
    private ConstanciaTipo constanciaTipo;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    private Archivo archivo;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    private Aplicacion aplicacion;

    public Constancia() {
    }

    public Constancia(DtOpenSGSBean dtOpenSGSBean) {
        this((DtConstancia) dtOpenSGSBean);
    }

    public Constancia(DtConstancia dtConstancia) {
        super();
        this.nombre = dtConstancia.getNombre();
        this.constanciaTipo = ServicioEnum.getInstance().parseEnum(ConstanciaTipo.class, dtConstancia.getConstanciaTipo());
        this.archivo = ManejadorArchivo.getInstance().obtenerArchivo(dtConstancia.getDtArchivo());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ConstanciaTipo getConstanciaTipo() {
        return constanciaTipo;
    }

    public void setConstanciaTipo(ConstanciaTipo constanciaTipo) {
        this.constanciaTipo = constanciaTipo;
    }

    public Archivo getArchivo() {
        return archivo;
    }

    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }

    public Aplicacion getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtConstancia dtConstancia = (DtConstancia) dtOpenSGSManagedBean;
        this.nombre = dtConstancia.getNombre();
        this.constanciaTipo = ServicioEnum.getInstance().parseEnum(ConstanciaTipo.class, dtConstancia.getConstanciaTipo());
        this.archivo = ManejadorArchivo.getInstance().obtenerArchivo(dtConstancia.getDtArchivo());
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    public DtConstancia getDtConstancia() {
        return new DtConstancia(
                nombre,
                constanciaTipo.toString(),
                archivo.getDtArchivo(),
                aplicacion.getDtAplicacion(),
                this.isActivo(),
                this.isBorrado(),
                this.isAdministrable(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getModificado()),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion())
        );
    }

    @Override
    public DtConstancia getDtOpenSGSBean() {
        return this.getDtConstancia();
    }

    @Override
    public DtConstancia getDataType() {
        return this.getDtConstancia();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.Constancia;
    }

    @Override
    public String toString() {
        return super.toString() + "Constancia{" + "nombre=" + nombre + ", constanciaTipo=" + constanciaTipo + ", archivo=" + archivo + ", aplicacion=" + aplicacion + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.nombre);
        hash = 67 * hash + Objects.hashCode(this.constanciaTipo);
        hash = 67 * hash + Objects.hashCode(this.archivo);
        hash = 67 * hash + Objects.hashCode(this.aplicacion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Constancia other = (Constancia) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (this.constanciaTipo != other.constanciaTipo) {
            return false;
        }
        if (!Objects.equals(this.archivo, other.archivo)) {
            return false;
        }
        return Objects.equals(this.aplicacion, other.aplicacion);
    }

}
