package opensgs.sistema.interfaces;

import java.util.List;
import opensgs.datatypes.DtMensaje;
import opensgs.sistema.datatypes.DtNotificacion;
import opensgs.usuarios.datatypes.DtSesion;

public interface IControladorNotificacion {

    public DtMensaje crearNotificacionAplicacion(DtNotificacion dtNotificacion, Long aplicacionId, DtSesion dtSesion);

    public List<DtNotificacion> listarNotificacionesSistema(boolean borrado, DtSesion dtSesion);

    public List<DtNotificacion> listarNotificacionesAplicacion(Long aplicacionId, boolean borrado, DtSesion dtSesion);

    public DtMensaje agregarDestinatario(DtNotificacion dtNotificacion, String email, DtSesion dtSesion);

    public List<DtNotificacion> listarNotificacionesActivas(DtSesion dtSesion);

}
