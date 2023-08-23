package opensgs.usuarios.logica.servicios;

import java.util.logging.Level;
import java.util.logging.Logger;
import opensgs.datatypes.DtMensaje;
import opensgs.sistema.enums.ServidorAutenticacionTipo;
import opensgs.sistema.logica.beans.ServidorAutenticacion;
import opensgs.sistema.logica.beans.Sistema;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.sistema.persistencia.manejadores.ManejadorSistema;
import opensgs.usuarios.logica.validators.ValidatorUsuario;

public class ServicioAutenticacion {
    
    private static ServicioAutenticacion instance = null;
    
    private ServicioAutenticacion() {
    }
    
    public static ServicioAutenticacion getInstance() {
        if (instance == null) {
            instance = new ServicioAutenticacion();
        }
        return instance;
    }

    public DtMensaje autenticarUsuario(String usuario, String password) {
        try {
            
            Sistema sistema = ManejadorSistema.getInstance().obtenerSistema();
            ServidorAutenticacion servidorAutenticacion = sistema.getServidorAutenticacion();
            
            if(servidorAutenticacion == null){
                return ServicioMensaje.getInstance().getMensajeERROR("ServicioAutenticacion.autentication.configuration.error");
            }
            
            if (servidorAutenticacion.getTipo().equals(ServidorAutenticacionTipo.LOCAL)){
                return this.autenticarUsuarioLocal(usuario, password);
            }
            return ServicioMensaje.getInstance().getMensajeERROR("ServicioAutenticacion.autentication.unexpected.error");
        } catch (Exception ex) {
            Logger.getLogger(ServicioAutenticacion.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("ServicioAutenticacion.autentication.unexpected.error");
        }
    }
    
    private DtMensaje autenticarUsuarioLocal(String usuario, String password){
        try {
            
            DtMensaje dtMensaje = ValidatorUsuario.getInstance().validarAutenticarUsuarioLocal(usuario, password);
            if(!dtMensaje.isExito()){
                return dtMensaje;
            }
            
            return ServicioMensaje.getInstance().getMensajeOK("Usuario.autentication.password.success");
        } catch (Exception ex) {
            Logger.getLogger(ServicioAutenticacion.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("Usuario.autentication.password.error");
        }
    }

}
