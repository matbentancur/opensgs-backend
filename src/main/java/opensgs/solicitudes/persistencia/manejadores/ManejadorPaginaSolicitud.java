package opensgs.solicitudes.persistencia.manejadores;

import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.solicitudes.datatypes.DtPaginaSolicitud;
import opensgs.solicitudes.logica.beans.PaginaSolicitud;
import opensgs.solicitudes.logica.servicios.ServicioPaginaSolicitud;

public class ManejadorPaginaSolicitud {

    private static ManejadorPaginaSolicitud instance = null;

    private ManejadorPaginaSolicitud() {
    }

    public static ManejadorPaginaSolicitud getInstance() {
        if (instance == null) {
            instance = new ManejadorPaginaSolicitud();
        }
        return instance;
    }

    public PaginaSolicitud obtenerPaginaSolicitud(DtPaginaSolicitud dtPaginaSolicitud) {
        PaginaSolicitud paginaSolicitud = ServicioPaginaSolicitud.getInstance().parseDtPaginaSolicitud(dtPaginaSolicitud);
        return (PaginaSolicitud) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(paginaSolicitud, "id", dtPaginaSolicitud.getId());
    }

    public PaginaSolicitud obtenerPaginaSolicitud(String className, Long id) {
        PaginaSolicitud paginaSolicitud = ServicioPaginaSolicitud.getInstance().parseClassName(className);
        return (PaginaSolicitud) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(paginaSolicitud, "id", id);
    }

}
