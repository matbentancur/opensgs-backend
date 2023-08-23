package opensgs.usuarios.logica.beans;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.enums.Elemento;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.sistema.persistencia.manejadores.ManejadorAplicacion;
import opensgs.usuarios.datatypes.DtPerfilAplicacion;
import opensgs.usuarios.persistencia.manejadores.ManejadorPerfil;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "perfilAplicacion",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"perfil_id", "aplicacion_id"}
        ),
        schema = "usuarios"
)
public class PerfilAplicacion extends OpenSGSBean {

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @NotNull
    private Perfil perfil;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @NotNull
    private Aplicacion aplicacion;

    public PerfilAplicacion() {
    }

    public PerfilAplicacion(DtOpenSGSBean dtOpenSGSBean) {
        this((DtPerfilAplicacion) dtOpenSGSBean);
    }

    public PerfilAplicacion(DtPerfilAplicacion dtPerfilAplicacion) {
        this.perfil = ManejadorPerfil.getInstance().obtenerPerfil(dtPerfilAplicacion.getDtPerfil());
        this.aplicacion = ManejadorAplicacion.getInstance().obtenerAplicacion(dtPerfilAplicacion.getDtAplicacion());
    }

    public PerfilAplicacion(Perfil perfil, Aplicacion aplicacion) {
        this.perfil = perfil;
        this.aplicacion = aplicacion;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Aplicacion getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
    }

    public DtPerfilAplicacion getDtPerfilAplicacion() {
        return new DtPerfilAplicacion(
                perfil.getDtPerfil(),
                aplicacion.getDtAplicacion(),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion())
        );
    }

    public DtPerfilAplicacion getDtPerfilAplicacionSesion() {
        return new DtPerfilAplicacion(
                perfil.getDtPerfil(),
                aplicacion.getDtAplicacionSesion(),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion())
        );
    }

    @Override
    public DtPerfilAplicacion getDtOpenSGSBean() {
        return this.getDtPerfilAplicacion();
    }

    @Override
    public DtPerfilAplicacion getDataType() {
        return this.getDtPerfilAplicacion();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.PerfilAplicacion;
    }

    @Override
    public String toString() {
        return super.toString() + "PerfilAplicacion{" + "perfil=" + perfil + ", aplicacion=" + aplicacion + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.perfil);
        hash = 59 * hash + Objects.hashCode(this.aplicacion);
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
        final PerfilAplicacion other = (PerfilAplicacion) obj;
        if (!Objects.equals(this.perfil, other.perfil)) {
            return false;
        }
        return Objects.equals(this.aplicacion, other.aplicacion);
    }

}
