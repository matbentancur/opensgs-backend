package opensgs.solicitudes.logica.controladores;

import opensgs.datatypes.DtMensaje;
import opensgs.enums.Operacion;
import java.util.logging.Level;
import java.util.logging.Logger;
import opensgs.sistema.logica.servicios.ServicioActividad;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.logica.beans.PaginaAplicacion;
import opensgs.sistema.logica.servicios.ServicioNotificacion;
import opensgs.sistema.persistencia.manejadores.ManejadorPaginaAplicacion;
import opensgs.solicitudes.datatypes.DtPaginaSolicitud;
import opensgs.solicitudes.interfaces.IControladorPaginaSolicitud;
import opensgs.solicitudes.logica.beans.PaginaSolicitud;
import opensgs.solicitudes.logica.beans.Solicitud;
import opensgs.solicitudes.logica.servicios.ServicioPaginaSolicitud;
import opensgs.solicitudes.persistencia.manejadores.ManejadorPaginaSolicitud;
import opensgs.solicitudes.persistencia.manejadores.ManejadorSolicitud;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.logica.servicios.ServicioPermiso;
import opensgs.usuarios.logica.servicios.ServicioSesion;

public class ControladorPaginaSolicitud implements IControladorPaginaSolicitud {

    @Override
    public DtPaginaSolicitud obtenerPaginaSolicitud(String className, Long id, DtSesion dtSesion) {

        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Solicitud(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        PaginaSolicitud paginaSolicitud = ManejadorPaginaSolicitud.getInstance().obtenerPaginaSolicitud(className, id);
        return (DtPaginaSolicitud) paginaSolicitud.getDtOpenSGSBean();
    }
    
    @Override
    public DtMensaje crearPaginaSolicitud(Long solicitudId, Long paginaAplicacionId, DtPaginaSolicitud dtPaginaSolicitud, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Crear, new Solicitud(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            Solicitud solicitud = ManejadorSolicitud.getInstance().obtenerSolicitud(solicitudId);
            if (solicitud == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("PaginaSolicitud.create.error");
            }

            PaginaAplicacion paginaAplicacion = ManejadorPaginaAplicacion.getInstance().obtenerPaginaAplicacion(paginaAplicacionId);
            if (paginaAplicacion == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("PaginaSolicitud.create.error");
            }

            PaginaSolicitud paginaSolicitud = ServicioPaginaSolicitud.getInstance().getNewPaginaSolicitud(dtPaginaSolicitud);
            if (paginaSolicitud == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("PaginaSolicitud.create.error");
            }
            
            paginaSolicitud.setPaginaAplicacion(paginaAplicacion);

            ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(paginaSolicitud);
            
            solicitud.agregarListOpenSGSBean(paginaSolicitud);
            
            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(solicitud);
            
            ServicioActividad.getInstance().crearActividad(Operacion.Crear, paginaSolicitud, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Crear, paginaSolicitud, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK("PaginaSolicitud.create.success", solicitud.getId());
        } catch (Exception ex) {
            Logger.getLogger(ControladorSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServicioMensaje.getInstance().getMensajeERROR("PaginaSolicitud.create.error");
    }

    @Override
    public DtMensaje modificarPaginaSolicitud(DtPaginaSolicitud dtPaginaSolicitud, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Modificar, new Solicitud(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            PaginaSolicitud paginaSolicitud = ManejadorPaginaSolicitud.getInstance().obtenerPaginaSolicitud(dtPaginaSolicitud);
            if (paginaSolicitud == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("PaginaSolicitud.update.error");
            }

            paginaSolicitud.modificar(dtPaginaSolicitud);

//            dtMensaje = ValidatorOpenSGSBean.getInstance().validarModificar(bean);
//            if (!dtMensaje.isExito()) {
//                return dtMensaje;
//            }

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(paginaSolicitud);

            ServicioActividad.getInstance().crearActividad(Operacion.Modificar, paginaSolicitud, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Modificar, paginaSolicitud, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK("PaginaSolicitud.update.success", paginaSolicitud.getId());
        } catch (Exception ex) {
            Logger.getLogger(ControladorPaginaSolicitud.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("PaginaSolicitud.update.error");
        }
    }

}
