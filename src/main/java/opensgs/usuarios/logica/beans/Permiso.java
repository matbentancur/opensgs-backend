package opensgs.usuarios.logica.beans;

import java.util.Objects;
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
import opensgs.usuarios.datatypes.DtPermiso;
import opensgs.enums.Elemento;
import opensgs.enums.Operacion;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.servicios.ServicioEnum;
import opensgs.logica.servicios.ServicioFechaHora;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "permiso",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"elemento", "operacion"}
        ),
        schema = "usuarios"
)
public class Permiso extends OpenSGSBean {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    @NotNull
    private Elemento elemento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    @NotNull
    private Operacion operacion;

    public Permiso() {
    }

    public Permiso(DtOpenSGSBean dtOpenSGSBean) {
        this((DtPermiso) dtOpenSGSBean);
    }

    public Permiso(DtPermiso dtPermiso) {
        super();
        this.elemento = ServicioEnum.getInstance().parseEnum(Elemento.class, dtPermiso.getElemento());
        this.operacion = ServicioEnum.getInstance().parseEnum(Operacion.class, dtPermiso.getOperacion());
    }

    public Permiso(Elemento elemento, Operacion operacion) {
        this.elemento = elemento;
        this.operacion = operacion;
    }

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

    public DtPermiso getDtPermiso() {
        return new DtPermiso(
                elemento.toString(),
                operacion.toString(),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion())
        );
    }

    @Override
    public DtPermiso getDtOpenSGSBean() {
        return this.getDtPermiso();
    }

    @Override
    public DtPermiso getDataType() {
        return this.getDtPermiso();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.Permiso;
    }

    @Override
    public String toString() {
        return super.toString() + "Permiso{" + "elemento=" + elemento + ", operacion=" + operacion + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.elemento);
        hash = 79 * hash + Objects.hashCode(this.operacion);
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
        final Permiso other = (Permiso) obj;
        if (this.elemento != other.elemento) {
            return false;
        }
        if (this.operacion != other.operacion) {
            return false;
        }
        return true;
    }

}
