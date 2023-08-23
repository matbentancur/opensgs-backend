package opensgs.solicitudes.logica.beans;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.sistema.logica.beans.PaginaAplicacion;
import opensgs.sistema.persistencia.manejadores.ManejadorPaginaAplicacion;
import opensgs.solicitudes.datatypes.DtPaginaSolicitud;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(
        name = "paginaSolicitud",
        schema = "solicitudes"
)
public abstract class PaginaSolicitud extends OpenSGSManagedBean {

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    private PaginaAplicacion paginaAplicacion;

    public PaginaSolicitud() {
    }

    public PaginaSolicitud(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        this((DtPaginaSolicitud) dtOpenSGSManagedBean);
    }

    public PaginaSolicitud(DtPaginaSolicitud dtPaginaSolicitud) {
        super();
        this.paginaAplicacion = ManejadorPaginaAplicacion.getInstance().obtenerPaginaAplicacion(dtPaginaSolicitud.getDtPaginaAplicacion());
    }

    public PaginaAplicacion getPaginaAplicacion() {
        return paginaAplicacion;
    }

    public void setPaginaAplicacion(PaginaAplicacion paginaAplicacion) {
        this.paginaAplicacion = paginaAplicacion;
    }

    @Override
    public String toString() {
        return "PaginaSolicitud{" + "paginaAplicacion=" + paginaAplicacion + '}';
    }
}
