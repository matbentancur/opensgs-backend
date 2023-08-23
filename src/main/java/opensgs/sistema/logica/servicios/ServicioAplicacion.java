package opensgs.sistema.logica.servicios;

import java.util.Date;
import opensgs.datatypes.DtMensaje;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.logica.servicios.ServicioOpenSGSBean;
import opensgs.sistema.logica.beans.Aplicacion;

public class ServicioAplicacion {

    private static ServicioAplicacion instance = null;

    private ServicioAplicacion() {
    }

    public static ServicioAplicacion getInstance() {
        if (instance == null) {
            instance = new ServicioAplicacion();
        }
        return instance;
    }

    public DtMensaje estaAbierta(Aplicacion aplicacion) throws Exception {
        Date now = new Date();
        if (aplicacion.getApertura().before(now) && aplicacion.getCierre().after(now)) {
            return ServicioMensaje.getInstance().getMensajeOK();
        } else if (aplicacion.getCierre().before(now)) {
            return ServicioMensaje.getInstance().getMensajeERROR("Aplicacion.closed.error");
        } else if (aplicacion.getApertura().after(now)) {
            return ServicioMensaje.getInstance().getMensajeERROR("Aplicacion.coming.error");
        } else {
            return ServicioMensaje.getInstance().getMensajeERROR("Aplicacion.unexpected.error");
        }
    }
    
    public boolean estaBorrada(Aplicacion aplicacion) {
        return ServicioOpenSGSBean.getInstance().estaBorrado(aplicacion);
    }

    public boolean estaActiva(Aplicacion aplicacion) {
        return ServicioOpenSGSBean.getInstance().estaActivo(aplicacion);
    }

}
