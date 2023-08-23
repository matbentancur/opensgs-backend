package opensgs.solicitudes.datatypes;

import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.sistema.datatypes.DtPaginaAplicacion;

public abstract class DtPaginaSolicitud extends DtOpenSGSManagedBean {

    private DtPaginaAplicacion dtPaginaAplicacion;

    public DtPaginaSolicitud() {
    }

    public DtPaginaSolicitud(DtPaginaAplicacion dtPaginaAplicacion, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.dtPaginaAplicacion = dtPaginaAplicacion;
    }

    public DtPaginaAplicacion getDtPaginaAplicacion() {
        return dtPaginaAplicacion;
    }

    public void setDtPaginaAplicacion(DtPaginaAplicacion dtPaginaAplicacion) {
        this.dtPaginaAplicacion = dtPaginaAplicacion;
    }

    @Override
    public String toString() {
        return "DtPaginaSolicitud{" + "dtPaginaAplicacion=" + dtPaginaAplicacion + '}';
    }

}
