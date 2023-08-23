package opensgs.solicitudes.logica.controladores;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import opensgs.datatypes.DtMensaje;
import opensgs.enums.Operacion;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.logica.servicios.ServicioOpenSGSBean;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.datatypes.DtConstancia;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.sistema.logica.beans.Constancia;
import opensgs.sistema.logica.servicios.ServicioActividad;
import opensgs.sistema.logica.servicios.ServicioConstancia;
import opensgs.sistema.logica.servicios.ServicioNotificacion;
import opensgs.sistema.persistencia.manejadores.ManejadorAplicacion;
import opensgs.sistema.persistencia.manejadores.ManejadorConstancia;
import opensgs.solicitudes.datatypes.DtEstadoSolicitud;
import opensgs.solicitudes.datatypes.DtSolicitud;
import opensgs.solicitudes.interfaces.IControladorSolicitud;
import opensgs.solicitudes.logica.beans.Solicitud;
import opensgs.solicitudes.logica.servicios.ServicioSolicitud;
import opensgs.solicitudes.logica.validators.ValidatorSolicitud;
import opensgs.solicitudes.persistencia.manejadores.ManejadorSolicitud;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.logica.beans.Usuario;
import opensgs.usuarios.logica.servicios.ServicioPermiso;
import opensgs.usuarios.logica.servicios.ServicioSesion;
import opensgs.usuarios.persistencia.manejadores.ManejadorUsuario;

public class ControladorSolicitud implements IControladorSolicitud {

    @Override
    public DtSolicitud obtenerMiSolicitud(Long solicitudId, DtSesion dtSesion) {

        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.VerMisSolicitudes, new Solicitud(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        Solicitud solicitud = ManejadorSolicitud.getInstance().obtenerSolicitud(solicitudId);
        if (solicitud == null) {
            return null;
        }

        Usuario usuario = ManejadorUsuario.getInstance().obtenerUsuario(dtSesion);
        if (usuario == null) {
            return null;
        }

        dtMensaje = ValidatorSolicitud.getInstance().validarObtenerMiSolicitud(solicitud, usuario);
        if (!dtMensaje.isExito()) {
            return null;
        }

        return solicitud.getDtSolicitud();
    }

    @Override
    public List<DtSolicitud> listarMisSolicitudes(DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.VerMisSolicitudes, new Solicitud(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        Usuario usuario = ManejadorUsuario.getInstance().obtenerUsuario(dtSesion);

        List<Solicitud> listaSolicitudes = ManejadorSolicitud.getInstance().listarMisSolicitudes(usuario);

        return (List<DtSolicitud>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaSolicitudes);
    }

    @Override
    public List<DtConstancia> listarConstanciasSolicitud(Long solicitudId, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.VerMisConstancias, new Solicitud(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        Solicitud solicitud = ManejadorSolicitud.getInstance().obtenerSolicitud(solicitudId);
        if (solicitud == null) {
            return null;
        }

        Usuario usuario = ManejadorUsuario.getInstance().obtenerUsuario(dtSesion);
        if (usuario == null) {
            return null;
        }
        
        Aplicacion aplicacion = solicitud.getAplicacion();
        if (aplicacion == null) {
            return null;
        }

        List<Constancia> listaConstanciasAplicacion = ManejadorConstancia.getInstance().listarConstanciasActivas(aplicacion.getId());

        listaConstanciasAplicacion = ServicioSolicitud.getInstance().filtrarConstanciasEmitibles(solicitud, listaConstanciasAplicacion);
               
        return (List<DtConstancia>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaConstanciasAplicacion);

    }

    @Override
    public List<DtSolicitud> listarSolicitudesAplicacion(Long aplicacionId, boolean borrado, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }
       
        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Solicitud(), dtSesion);
        if (!dtMensaje.isExito()) {
            dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Ver, new Solicitud(), aplicacionId, dtSesion);
            if (!dtMensaje.isExito()) {
                return null;
            }
        }

        Aplicacion aplicacion = ManejadorAplicacion.getInstance().obtenerAplicacion(aplicacionId);

        List<Solicitud> listaSolicitudes = ManejadorSolicitud.getInstance().listarSolicitudesAplicacion(aplicacion, borrado);

        return (List<DtSolicitud>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaSolicitudes);
    }

    @Override
    public DtMensaje crearSolicitud(Long aplicacionId, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Crear, new Solicitud(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            Aplicacion aplicacion = ManejadorAplicacion.getInstance().obtenerAplicacion(aplicacionId);
            dtMensaje = ValidatorSolicitud.getInstance().validarCrear(aplicacion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            Usuario usuario = ManejadorUsuario.getInstance().obtenerUsuario(dtSesion);
            if (usuario == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.create.error");
            }

            Solicitud solicitud = new Solicitud();
            solicitud.setUsuario(usuario);
            solicitud.setAplicacion(aplicacion);
            solicitud.acordar(true);

            ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(solicitud);

//            dtMensaje = ServicioSolicitud.getInstance().crearPaginasSolicitud(solicitud, aplicacion);
//            if (!dtMensaje.isExito()) {
//                return dtMensaje;
//            }
            ServicioActividad.getInstance().crearActividad(Operacion.Crear, solicitud, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Crear, solicitud, dtSesion);

            ServicioActividad.getInstance().crearActividad(Operacion.Acordar, solicitud, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Acordar, solicitud, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK("Solicitud.create.success", solicitud.getId());
        } catch (Exception ex) {
            Logger.getLogger(ControladorSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.create.error");
    }

    @Override
    public DtMensaje modificarSolicitud(Long solicitudId, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Modificar, new Solicitud(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            Solicitud solicitud = ManejadorSolicitud.getInstance().obtenerSolicitud(solicitudId);
            if (solicitud == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.update.error");
            }

            ServicioActividad.getInstance().crearActividad(Operacion.Modificar, solicitud, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Modificar, solicitud, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK("Solicitud.update.success", solicitud.getId());
        } catch (Exception ex) {
            Logger.getLogger(ControladorSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.update.error");
    }

    @Override
    public DtMensaje modificarEstadoSolicitud(DtEstadoSolicitud dtEstadoSolicitud, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Modificar, new Solicitud(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            Solicitud solicitud = ManejadorSolicitud.getInstance().obtenerSolicitud(dtEstadoSolicitud.getId());
            if (solicitud == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.update.error");
            }

            boolean modificado = false;

            if (dtEstadoSolicitud.isEntregado() != solicitud.isEntregado()) {
                this.entregarSolicitud(solicitud, dtEstadoSolicitud.isEntregado(), dtSesion);
                modificado = true;
            }

            if (dtEstadoSolicitud.isFinanciado() != solicitud.isFinanciado()) {
                this.financiarSolicitud(solicitud, dtEstadoSolicitud.isFinanciado(), dtSesion);
                modificado = true;
            }

            if (dtEstadoSolicitud.isCursado() != solicitud.isCursado()) {
                this.cursarSolicitud(solicitud, dtEstadoSolicitud.isCursado(), dtSesion);
                modificado = true;
            }

            if (dtEstadoSolicitud.isAprobado() != solicitud.isAprobado()) {
                this.aprobarSolicitud(solicitud, dtEstadoSolicitud.isAprobado(), dtSesion);
                modificado = true;
            }

            if (modificado) {
                ServicioActividad.getInstance().crearActividad(Operacion.Modificar, solicitud, dtSesion);

                ServicioNotificacion.getInstance().notificarSistema(Operacion.Modificar, solicitud, dtSesion);
            }

            return ServicioMensaje.getInstance().getMensajeOK("Solicitud.update.success", solicitud.getId());
        } catch (Exception ex) {
            Logger.getLogger(ControladorSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.update.error");
    }

    @Override
    public DtMensaje modificarMiSolicitud(Long solicitudId, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.ModificarMiSolicitud, new Solicitud(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            Solicitud solicitud = ManejadorSolicitud.getInstance().obtenerSolicitud(solicitudId);
            if (solicitud == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.update.error");
            }

            Usuario usuario = ManejadorUsuario.getInstance().obtenerUsuario(dtSesion);
            if (usuario == null) {
                return null;
            }

            dtMensaje = ValidatorSolicitud.getInstance().validarModificarMiSolicitud(solicitud, usuario);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            ServicioActividad.getInstance().crearActividad(Operacion.ModificarMiSolicitud, solicitud, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.ModificarMiSolicitud, solicitud, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK("Solicitud.update.success", solicitud.getId());
        } catch (Exception ex) {
            Logger.getLogger(ControladorSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.update.error");
    }

//    @Override
//    public DtMensaje acordarSolicitud(Long solicitudId, DtSesion dtSesion) {
//        try {
//            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
//            if (!dtMensaje.isExito()) {
//                return dtMensaje;
//            }
//
//            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Modificar, "Solicitud", dtSesion);
//            if (!dtMensaje.isExito()) {
//                return dtMensaje;
//            }
//
//            Solicitud solicitud = ManejadorSolicitud.getInstance().obtenerSolicitud(solicitudId);
//            if (solicitud == null) {
//                return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.agreement.error");
//            }
//
//            dtMensaje = ValidatorSolicitud.getInstance().validarAcordar(solicitud);
//            if (!dtMensaje.isExito()) {
//                return dtMensaje;
//            }
//
//            solicitud.acordar();
//
//            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(solicitud);
//
//            ServicioActividad.getInstance().crearActividad(Operacion.Acordar, solicitud, dtSesion);
//
//            ServicioNotificacion.getInstance().notificarSistema(Operacion.Acordar, solicitud, dtSesion);
//
//            return ServicioMensaje.getInstance().getMensajeOK("Solicitud.agreement.success", solicitud.getId());
//        } catch (Exception ex) {
//            Logger.getLogger(ControladorSolicitud.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.agreement.error");
//    }
    @Override
    public DtMensaje entregarMiSolicitud(Long solicitudId, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.EntregarMiSolicitud, new Solicitud(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            Solicitud solicitud = ManejadorSolicitud.getInstance().obtenerSolicitud(solicitudId);
            if (solicitud == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.deliver.error");
            }

            Usuario usuario = ManejadorUsuario.getInstance().obtenerUsuario(dtSesion);
            if (usuario == null) {
                return null;
            }

            dtMensaje = ValidatorSolicitud.getInstance().validarEntregarMiSolicitud(solicitud, usuario);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            solicitud.entregar(true);

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(solicitud);

            ServicioActividad.getInstance().crearActividad(Operacion.EntregarMiSolicitud, solicitud, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.EntregarMiSolicitud, solicitud, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK("Solicitud.deliver.success", solicitud.getId());
        } catch (Exception ex) {
            Logger.getLogger(ControladorSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.deliver.error");
    }

    private DtMensaje entregarSolicitud(Solicitud solicitud, boolean entregado, DtSesion dtSesion) {
        try {
            Operacion operacion = Operacion.NoEntregar;
            if (entregado) {
                operacion = Operacion.Entregar;
            }

            solicitud.entregar(entregado);

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(solicitud);

            ServicioActividad.getInstance().crearActividad(operacion, solicitud, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(operacion, solicitud, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK("Solicitud.deliver.success", solicitud.getId());
        } catch (Exception ex) {
            Logger.getLogger(ControladorSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.deliver.error");
    }

    private DtMensaje financiarSolicitud(Solicitud solicitud, boolean financiado, DtSesion dtSesion) {
        try {
            Operacion operacion = Operacion.NoFinanciar;
            if (financiado) {
                operacion = Operacion.Financiar;
            }

            solicitud.financiar(financiado);

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(solicitud);

            ServicioActividad.getInstance().crearActividad(operacion, solicitud, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(operacion, solicitud, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK("Solicitud.finance.success", solicitud.getId());
        } catch (Exception ex) {
            Logger.getLogger(ControladorSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.finance.error");
    }

    private DtMensaje cursarSolicitud(Solicitud solicitud, boolean cursado, DtSesion dtSesion) {
        try {
            Operacion operacion = Operacion.NoCursar;
            if (cursado) {
                operacion = Operacion.Cursar;
            }

            solicitud.cursar(cursado);

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(solicitud);

            ServicioActividad.getInstance().crearActividad(operacion, solicitud, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(operacion, solicitud, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK("Solicitud.complete.success", solicitud.getId());
        } catch (Exception ex) {
            Logger.getLogger(ControladorSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.complete.error");
    }

    private DtMensaje aprobarSolicitud(Solicitud solicitud, boolean aprobado, DtSesion dtSesion) {
        try {
            Operacion operacion = Operacion.NoAprobar;
            if (aprobado) {
                operacion = Operacion.Aprobar;
            }

            solicitud.aprobar(aprobado);

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(solicitud);

            ServicioActividad.getInstance().crearActividad(operacion, solicitud, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(operacion, solicitud, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK("Solicitud.approve.success", solicitud.getId());
        } catch (Exception ex) {
            Logger.getLogger(ControladorSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.approve.error");
    }

    @Override
    public DataHandler emitirConstanciaSolicitud(Long solicitudId, Long constanciaId, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.VerMisConstancias, new Solicitud(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        Solicitud solicitud = ManejadorSolicitud.getInstance().obtenerSolicitud(solicitudId);
        if (solicitud == null) {
            return null;
        }

        Constancia constancia = ManejadorConstancia.getInstance().obtenerConstancia(constanciaId);
        if (constancia == null) {
            return null;
        }

        Usuario usuario = ManejadorUsuario.getInstance().obtenerUsuario(dtSesion);
        if (usuario == null) {
            return null;
        }

        Aplicacion aplicacion = solicitud.getAplicacion();
        if (aplicacion == null) {
            return null;
        }
        
        dtMensaje = ValidatorSolicitud.getInstance().validarEmitirMiConstancia(solicitud, constancia, usuario);
        if (!dtMensaje.isExito()) {
            return null;
        }

        return ServicioConstancia.getInstance().emitirConstancia(constancia, usuario);
    }

}
