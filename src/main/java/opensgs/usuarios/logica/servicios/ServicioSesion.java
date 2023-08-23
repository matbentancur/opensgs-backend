package opensgs.usuarios.logica.servicios;

import java.util.logging.Level;
import java.util.logging.Logger;
import opensgs.datatypes.DtMensaje;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.logica.beans.Sesion;
import opensgs.usuarios.persistencia.manejadores.ManejadorSesion;

public class ServicioSesion {
    
    private static ServicioSesion instance = null;
    
    private ServicioSesion() {
    }
    
    public static ServicioSesion getInstance() {
        if (instance == null) {
            instance = new ServicioSesion();
        }
        return instance;
    }
    
    public DtMensaje verificarSesion(DtSesion dtSesion) {
        try {
            Sesion sesion = ManejadorSesion.getInstance().obtenerSesion(dtSesion);
            if(sesion == null){
                return ServicioMensaje.getInstance().getMensajeERROR("Sesion.exists.error");
            }
            if (!dtSesion.getCodigo().equals(sesion.getCodigo())){
                return ServicioMensaje.getInstance().getMensajeERROR("Sesion.code.error");
            }
            return ServicioMensaje.getInstance().getMensajeOK("Sesion.exists.success");
        } catch (Exception ex) {
            Logger.getLogger(ServicioSesion.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("Sesion.exists.error");
        }
    }

}
