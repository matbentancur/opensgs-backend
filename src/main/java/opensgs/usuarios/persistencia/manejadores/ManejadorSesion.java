package opensgs.usuarios.persistencia.manejadores;

import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.logica.beans.Sesion;

public class ManejadorSesion {

    private static ManejadorSesion instance = null;

    private ManejadorSesion() {
    }

    public static ManejadorSesion getInstance() {
        if (instance == null) {
            instance = new ManejadorSesion();
        }
        return instance;
    }

    public Sesion obtenerSesion(Long usuarioId) {
        return (Sesion) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new Sesion(), "usuario_id", usuarioId);
    }

    public Sesion obtenerSesion(DtSesion dtSesion) {
        return this.obtenerSesion(dtSesion.getDtUsuario().getId());
    }

    public void crearSesion(Sesion sesion) throws Exception {
        ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(sesion);
    }

    public void borrarSesion(Sesion sesion) throws Exception {
        ManejadorOpenSGSBean.getInstance().borrarOpenSGSBean(sesion);
    }

}
