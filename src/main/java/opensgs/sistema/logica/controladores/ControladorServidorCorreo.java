package opensgs.sistema.logica.controladores;

import java.util.logging.Level;
import java.util.logging.Logger;
import opensgs.datatypes.DtMensaje;
import opensgs.enums.Operacion;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.sistema.interfaces.IControladorServidorCorreo;
import opensgs.sistema.logica.beans.ServidorCorreo;
import opensgs.sistema.logica.servicios.ServicioServidorCorreo;
import opensgs.sistema.persistencia.manejadores.ManejadorServidorCorreo;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.logica.servicios.ServicioPermiso;
import opensgs.usuarios.logica.servicios.ServicioSesion;

public class ControladorServidorCorreo implements IControladorServidorCorreo {

    @Override
    public DtMensaje probarEnvioCorreo(Long id, String email, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new ServidorCorreo(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            ServidorCorreo servidorCorreo = ManejadorServidorCorreo.getInstance().obtenerServidorCorreo(id);
            if (servidorCorreo == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.notexists.error");
            }
            
            ServicioServidorCorreo.getInstance().enviarCorreoPrueba(servidorCorreo, email);
            return ServicioMensaje.getInstance().getMensajeOK("ServidorCorreo.send.success");
            
        } catch (Exception ex) {
            Logger.getLogger(ControladorServidorCorreo.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("ServidorCorreo.send.error");
        }

    }

}
