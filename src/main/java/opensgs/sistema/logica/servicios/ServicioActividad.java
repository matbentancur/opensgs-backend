package opensgs.sistema.logica.servicios;

import java.util.logging.Level;
import java.util.logging.Logger;
import opensgs.datatypes.DtMensaje;
import opensgs.enums.Operacion;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.logica.beans.Actividad;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.datatypes.DtUsuario;
import opensgs.usuarios.logica.beans.Usuario;

public class ServicioActividad {

    private static ServicioActividad instance = null;

    private ServicioActividad() {
    }

    public static ServicioActividad getInstance() {
        if (instance == null) {
            instance = new ServicioActividad();
        }
        return instance;
    }

    public DtMensaje crearActividad(Operacion operacion, OpenSGSBean openSGSBean, Usuario usuario) {
        try {
            Actividad actividad = new Actividad(operacion, openSGSBean, usuario);
            ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(actividad);
            return ServicioMensaje.getInstance().getMensajeOK("Actividad.create.success");
        } catch (Exception ex) {
            Logger.getLogger(ServicioActividad.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("Actividad.create.error");
        }
    }

    public DtMensaje crearActividad(Operacion operacion, OpenSGSBean openSGSBean, String descripcion, Usuario usuario) {
        try {
            Actividad actividad = new Actividad(operacion, openSGSBean, descripcion, usuario);
            ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(actividad);
            return ServicioMensaje.getInstance().getMensajeOK("Actividad.create.success");
        } catch (Exception ex) {
            Logger.getLogger(ServicioActividad.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("Actividad.create.error");
        }
    }

    public DtMensaje crearActividad(Operacion operacion, OpenSGSBean openSGSBean, DtUsuario dtUsuario) {
        Usuario usuario = (Usuario) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtUsuario);
        return this.crearActividad(operacion, openSGSBean, usuario);
    }

    public DtMensaje crearActividad(Operacion operacion, OpenSGSBean openSGSBean, DtSesion dtSesion) {
        return this.crearActividad(operacion, openSGSBean, dtSesion.getDtUsuario());
    }

}
