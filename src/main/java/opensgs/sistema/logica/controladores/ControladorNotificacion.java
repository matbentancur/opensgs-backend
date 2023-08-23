package opensgs.sistema.logica.controladores;

import java.util.ArrayList;
import java.util.List;
import opensgs.datatypes.DtMensaje;
import java.util.logging.Level;
import java.util.logging.Logger;
import opensgs.enums.Alcance;
import opensgs.enums.Operacion;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.logica.servicios.ServicioOpenSGSBean;
import opensgs.logica.validators.ValidatorOpenSGSBean;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.datatypes.DtNotificacion;
import opensgs.sistema.interfaces.IControladorNotificacion;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.sistema.logica.beans.Destinatario;
import opensgs.sistema.logica.beans.Notificacion;
import opensgs.sistema.logica.servicios.ServicioActividad;
import opensgs.sistema.logica.servicios.ServicioNotificacion;
import opensgs.sistema.persistencia.manejadores.ManejadorAplicacion;
import opensgs.sistema.persistencia.manejadores.ManejadorDestinatario;
import opensgs.sistema.persistencia.manejadores.ManejadorNotificacion;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.logica.servicios.ServicioPermiso;
import opensgs.usuarios.logica.servicios.ServicioSesion;

public class ControladorNotificacion implements IControladorNotificacion {

    @Override
    public DtMensaje crearNotificacionAplicacion(DtNotificacion dtNotificacion, Long aplicacionId, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isCreable(dtNotificacion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            Notificacion notificacion = (Notificacion) ServicioOpenSGSBean.getInstance().getNewOpenSGSManagedBean(dtNotificacion);
            if (notificacion == null) {
                return ServicioMensaje.getInstance().getMensajeERROR(dtNotificacion.getClassName() + ".create.error");
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Crear, new Notificacion(), dtSesion);
            if (!dtMensaje.isExito()) {
                dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Crear, new Notificacion(), aplicacionId, dtSesion);
                if (!dtMensaje.isExito()) {
                    return dtMensaje;
                }
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarCrear(notificacion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            notificacion.setAlcance(Alcance.APLICACION);

            ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(notificacion);

            ServicioActividad.getInstance().crearActividad(Operacion.Crear, notificacion, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Crear, notificacion, dtSesion);

            Aplicacion aplicacion = ManejadorAplicacion.getInstance().obtenerAplicacion(aplicacionId);
            aplicacion.agregarListOpenSGSBean(notificacion);

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(aplicacion);

            ServicioActividad.getInstance().crearActividad(Operacion.Agregar, aplicacion, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Agregar, aplicacion, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtNotificacion.getClassName() + ".create.success", notificacion.getId());
        } catch (Exception ex) {
            Logger.getLogger(ControladorAnuncio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServicioMensaje.getInstance().getMensajeERROR(dtNotificacion.getClassName() + ".create.error");
    }

    @Override
    public List<DtNotificacion> listarNotificacionesSistema(boolean borrado, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Notificacion(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<Notificacion> listaDtNotificaciones = ManejadorNotificacion.getInstance().listarNotificacionesSistema(borrado);

        return (List<DtNotificacion>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaDtNotificaciones);
    }

    @Override
    public List<DtNotificacion> listarNotificacionesAplicacion(Long aplicacionId, boolean borrado, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Notificacion(), dtSesion);
        if (!dtMensaje.isExito()) {
            dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Ver, new Notificacion(), aplicacionId, dtSesion);
            if (!dtMensaje.isExito()) {
                return null;
            }
        }

        List<Notificacion> listaNotificaciones = new ArrayList<>();
        List<Notificacion> listaNotificacionesAplicacion = ManejadorNotificacion.getInstance().listarNotificacionesAplicacion(aplicacionId, borrado);

//        if (!borrado) {
//            List<Notificacion> listaNotificacionesGlobales = ManejadorNotificacion.getInstance().listarNotificacionesGlobalesActivas();
//            List<Notificacion> listaNotificacionesAplicaciones = ManejadorNotificacion.getInstance().listarNotificacionesAplicacionesActivas();
//            listaNotificaciones.addAll(listaNotificacionesGlobales);
//            listaNotificaciones.addAll(listaNotificacionesAplicaciones);
//        }

        listaNotificaciones.addAll(listaNotificacionesAplicacion);

        return (List<DtNotificacion>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaNotificaciones);
    }

    @Override
    public DtMensaje agregarDestinatario(DtNotificacion dtNotificacion, String email, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isModificable(dtNotificacion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Modificar, new Notificacion(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            Notificacion notificacion = ManejadorNotificacion.getInstance().obtenerNotificacion(dtNotificacion);
            if (notificacion == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.notexists.error");
            }

            Destinatario destinatario = ManejadorDestinatario.getInstance().obtenerDestinatario(email);
            if (destinatario == null) {
                destinatario = new Destinatario(email);

                dtMensaje = ValidatorOpenSGSBean.getInstance().validarCrear(destinatario);
                if (!dtMensaje.isExito()) {
                    return dtMensaje;
                }

                ManejadorDestinatario.getInstance().crearDestinatario(destinatario);

                ServicioActividad.getInstance().crearActividad(Operacion.Crear, destinatario, dtSesion);
            }

            if (!notificacion.agregarListOpenSGSBean(destinatario)) {
                return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.add.error");
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarModificar(notificacion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            ManejadorNotificacion.getInstance().modificarNotificacion(notificacion);

            ServicioActividad.getInstance().crearActividad(Operacion.Agregar, notificacion, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Agregar, notificacion, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK("OpenSGSBean.add.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorNotificacion.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.add.error");
        }
    }

    @Override
    public List<DtNotificacion> listarNotificacionesActivas(DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Notificacion(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<Notificacion> listaNotificaciones = ManejadorNotificacion.getInstance().listarNotificacionesActivas();

        return (List<DtNotificacion>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaNotificaciones);
    }

}
