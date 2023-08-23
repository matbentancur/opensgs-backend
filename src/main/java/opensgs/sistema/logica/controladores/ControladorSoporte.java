package opensgs.sistema.logica.controladores;

import opensgs.datatypes.DtMensaje;
import java.util.logging.Level;
import java.util.logging.Logger;
import opensgs.sistema.datatypes.DtSoporte;
import opensgs.sistema.interfaces.IControladorSoporte;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.sistema.logica.beans.Soporte;
import opensgs.sistema.logica.servicios.ServicioServidorCorreo;
import opensgs.sistema.logica.validators.ValidatorSoporte;

public class ControladorSoporte implements IControladorSoporte {
    
    @Override
    public DtMensaje enviarSoporte(DtSoporte dtSoporte) {
        try {
            Soporte soporte = new Soporte(dtSoporte);
            
            DtMensaje dtMensaje = ValidatorSoporte.getInstance().validar(soporte);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }
            
            ServicioServidorCorreo.getInstance().enviarCorreoSoporte(soporte);
            
            return ServicioMensaje.getInstance().getMensajeOK("Soporte.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorSoporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServicioMensaje.getInstance().getMensajeERROR("Soporte.error");
    }
    
}
