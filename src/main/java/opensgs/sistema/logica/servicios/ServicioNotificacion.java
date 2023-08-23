package opensgs.sistema.logica.servicios;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import opensgs.datatypes.DtMensaje;
import opensgs.enums.Elemento;
import opensgs.enums.Operacion;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.enums.CorreoContenidoTipo;
import opensgs.sistema.logica.beans.Notificacion;
import opensgs.sistema.logica.beans.PlantillaCorreo;
import opensgs.sistema.persistencia.manejadores.ManejadorNotificacion;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.logica.beans.Usuario;

public class ServicioNotificacion {
    
    private static ServicioNotificacion instance = null;
    
    private ServicioNotificacion() {
    }
    
    public static ServicioNotificacion getInstance() {
        if (instance == null) {
            instance = new ServicioNotificacion();
        }
        return instance;
    }
    
    //TODO
    public DtMensaje notificar(Operacion operacion, Elemento elemento, Usuario usuario) {
        try {
            List<Notificacion> listaNotificaciones = ManejadorNotificacion.getInstance().listarNotificacion(operacion, elemento);
            if(!listaNotificaciones.isEmpty()){
                for(Notificacion notificacion : listaNotificaciones){
                    PlantillaCorreo plantillaCorreo = notificacion.getPlantillaCorreo();
                    ServicioServidorCorreo.getInstance().enviarCorreo(notificacion.getDestinatarios(), plantillaCorreo.getAsunto(), plantillaCorreo.getTexto(), CorreoContenidoTipo.HTML);
                }
            }
            return ServicioMensaje.getInstance().getMensajeOK("Notificacion.send.success");
        } catch (Exception ex) {
            Logger.getLogger(ServicioNotificacion.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("Notificacion.send.error");
        }
    }
    
    public DtMensaje notificarSistema(Operacion operacion, Elemento elemento, Usuario usuario) {
        try {
            List<Notificacion> listaNotificaciones = ManejadorNotificacion.getInstance().listarNotificacionesSistemaActivas(operacion, elemento);
            if(!listaNotificaciones.isEmpty()){
                for(Notificacion notificacion : listaNotificaciones){
                    PlantillaCorreo plantillaCorreo = ServicioPlantillaCorreo.getInstance().procesarEtiquetas(notificacion.getPlantillaCorreo(), usuario);
                    ServicioServidorCorreo.getInstance().enviarCorreo(notificacion.getDestinatarios(), plantillaCorreo.getAsunto(), plantillaCorreo.getTexto(), CorreoContenidoTipo.HTML);
                }
            }
            return ServicioMensaje.getInstance().getMensajeOK("Notificacion.send.success");
        } catch (Exception ex) {
            Logger.getLogger(ServicioNotificacion.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("Notificacion.send.error");
        }
    }
    
    public DtMensaje notificarSistema(Operacion operacion, Elemento elemento, DtSesion dtSesion) {
        Usuario usuario = (Usuario) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtSesion.getDtUsuario());
        return this.notificarSistema(operacion, elemento, usuario);
    }
    
    public DtMensaje notificarSistema(Operacion operacion, OpenSGSBean openSGSBean, DtSesion dtSesion) {
        Usuario usuario = (Usuario) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtSesion.getDtUsuario());
        return this.notificarSistema(operacion, openSGSBean.getOpenSGSElemento(), usuario);
    }

}
