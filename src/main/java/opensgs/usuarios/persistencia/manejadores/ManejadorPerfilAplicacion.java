package opensgs.usuarios.persistencia.manejadores;

import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.usuarios.datatypes.DtPerfilAplicacion;
import opensgs.usuarios.logica.beans.Perfil;
import opensgs.usuarios.logica.beans.PerfilAplicacion;

public class ManejadorPerfilAplicacion {

    private static ManejadorPerfilAplicacion instance = null;

    private ManejadorPerfilAplicacion() {
    }

    public static ManejadorPerfilAplicacion getInstance() {
        if (instance == null) {
            instance = new ManejadorPerfilAplicacion();
        }
        return instance;
    }

    public PerfilAplicacion obtenerPerfilAplicacion(DtPerfilAplicacion dtPerfilAplicacion) {
        return (PerfilAplicacion) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtPerfilAplicacion);
    }

    public PerfilAplicacion obtenerPerfilAplicacion(Perfil perfil, Aplicacion aplicacion) {
        return (PerfilAplicacion) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new PerfilAplicacion(), "perfil_id", perfil.getId(), "aplicacion_id", aplicacion.getId());
    }

    public void crearPerfilAplicacion(PerfilAplicacion perfilAplicacion) throws Exception {
        ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(perfilAplicacion);
    }

    public void crearPerfilAplicacion(Perfil perfil, Aplicacion aplicacion) throws Exception {
        PerfilAplicacion perfilAplicacion = new PerfilAplicacion(perfil, aplicacion);
        this.crearPerfilAplicacion(perfilAplicacion);
    }

}
