package opensgs.logica.servicios;

import java.util.List;
import java.util.ResourceBundle;
import opensgs.datatypes.DtMensaje;
import opensgs.enums.MensajeTipo;

public class ServicioMensaje {
    
    private static ServicioMensaje instance = null;
    private ResourceBundle mensajes = ResourceBundle.getBundle("mensajes");
    
    private ServicioMensaje() {
    }
    
    public static ServicioMensaje getInstance() {
        if (instance == null) {
            instance = new ServicioMensaje();
        }
        return instance;
    }

    private ResourceBundle getMensajes() {
        return mensajes;
    }
    
    public String getMensaje(String mensaje){
        return this.getMensajes().getString(mensaje);
    }
    
    public DtMensaje getMensajeOK(String texto) {
        String mensaje = this.getMensaje(texto);
        if(mensaje != null){
            return new DtMensaje(true, mensaje, MensajeTipo.OK);
        } else{
            return new DtMensaje(true, "No se pudo recuperar el mensaje", MensajeTipo.OK);
        }
    }
    
    public DtMensaje getMensajeOK(String texto, Long openSGSBeanId) {
        String mensaje = this.getMensaje(texto);
        if(mensaje != null){
            return new DtMensaje(true, mensaje, MensajeTipo.OK, openSGSBeanId);
        } else{
            return new DtMensaje(true, "No se pudo recuperar el mensaje", MensajeTipo.OK, openSGSBeanId);
        }
    }
    
    public DtMensaje getMensajeOK() {
        return new DtMensaje(true, "", MensajeTipo.OK);
    }
    
    public DtMensaje getMensajeOK(Long openSGSBeanId) {
        return new DtMensaje(true, "", MensajeTipo.OK, openSGSBeanId);
    }
    
    public DtMensaje getMensajeERROR(String texto) {
        String mensaje = this.getMensaje(texto);
        if(mensaje != null){
            return new DtMensaje(false, mensaje, MensajeTipo.ERROR);
        } else{
            return new DtMensaje(false, "No se pudo recuperar el mensaje", MensajeTipo.ERROR);
        }
    }

    public DtMensaje getMensajeERROR(List<String> mensajes) {
        return new DtMensaje(false, mensajes, MensajeTipo.ERROR);
    }

    public DtMensaje getMensajeALERT(String texto) {
        String mensaje = this.getMensaje(texto);
        if(mensaje != null){
            return new DtMensaje(false, mensaje, MensajeTipo.ALERT);
        } else{
            return new DtMensaje(false, "No se pudo recuperar el mensaje", MensajeTipo.ALERT);
        }
    }
    
}
