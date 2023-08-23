package opensgs.solicitudes.interfaces;

import java.util.List;
import javax.activation.DataHandler;
import opensgs.datatypes.DtMensaje;
import opensgs.sistema.datatypes.DtConstancia;
import opensgs.solicitudes.datatypes.DtEstadoSolicitud;
import opensgs.solicitudes.datatypes.DtSolicitud;
import opensgs.usuarios.datatypes.DtSesion;

public interface IControladorSolicitud {

    public DtSolicitud obtenerMiSolicitud(Long solicitudId, DtSesion dtSesion);

    public List<DtSolicitud> listarMisSolicitudes(DtSesion dtSesion);

    public List<DtConstancia> listarConstanciasSolicitud(Long solicitudId, DtSesion dtSesion);

    public List<DtSolicitud> listarSolicitudesAplicacion(Long aplicacionId, boolean borrado, DtSesion dtSesion);

    public DtMensaje crearSolicitud(Long aplicacionId, DtSesion dtSesion);

    public DtMensaje modificarSolicitud(Long solicitudId, DtSesion dtSesion);
    
    public DtMensaje modificarEstadoSolicitud(DtEstadoSolicitud dtEstadoSolicitud, DtSesion dtSesion);
    
    public DtMensaje modificarMiSolicitud(Long solicitudId, DtSesion dtSesion);

//    public DtMensaje acordarSolicitud(Long solicitudId, DtSesion dtSesion);
    
    public DtMensaje entregarMiSolicitud(Long solicitudId, DtSesion dtSesion);
    
    public DataHandler emitirConstanciaSolicitud(Long solicitudId, Long constanciaId, DtSesion dtSesion);
    
}
