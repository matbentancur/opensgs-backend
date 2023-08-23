package opensgs.sistema.logica.validators;

import opensgs.datatypes.DtMensaje;
import opensgs.enums.MensajeTipo;
import opensgs.sistema.logica.beans.Sistema;
import opensgs.sistema.persistencia.manejadores.ManejadorSistema;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.usuarios.persistencia.manejadores.ManejadorUsuario;

public class ValidatorSistema {
    
    private static ValidatorSistema instance = null;
                
    private ValidatorSistema() {
    }
    
    public static ValidatorSistema getInstance() {
        if (instance == null) {
            instance = new ValidatorSistema();
        }
        return instance;
    }
    
    public DtMensaje validarSistema(){   
        //VALIDA SI NO PASO LA CANTIDAD DE USUARIOS MAXIMA DEFINIDA
        Integer cantidadUsuarios = ManejadorUsuario.getInstance().contarUsuarios();
        Sistema sistema = ManejadorSistema.getInstance().obtenerSistema();
        if (sistema.getCantidadMaximaUsuarios().compareTo(cantidadUsuarios) <= 0) {
            return ServicioMensaje.getInstance().getMensajeERROR("Usuario.register.quantity.exceeded");
        }
        return new DtMensaje(true, "", MensajeTipo.OK);
    }

}
    

