package opensgs.solicitudes.interfaces;

import opensgs.datatypes.DtMensaje;
import opensgs.solicitudes.datatypes.DtPaginaSolicitud;
import opensgs.usuarios.datatypes.DtSesion;

public interface IControladorPaginaSolicitud {
   
    public DtPaginaSolicitud obtenerPaginaSolicitud(String className, Long id, DtSesion dtSesion);
    
    public DtMensaje crearPaginaSolicitud(Long solicitudId, Long paginaAplicacionId, DtPaginaSolicitud dtPaginaSolicitud, DtSesion dtSesion);
    
    public DtMensaje modificarPaginaSolicitud(DtPaginaSolicitud dtPaginaSolicitud, DtSesion dtSesion);

}
