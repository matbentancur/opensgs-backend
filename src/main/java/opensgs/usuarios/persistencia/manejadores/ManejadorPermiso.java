package opensgs.usuarios.persistencia.manejadores;

import opensgs.usuarios.datatypes.DtPermiso;
import opensgs.enums.Elemento;
import opensgs.enums.Operacion;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.usuarios.logica.beans.Permiso;

public class ManejadorPermiso {

    private static ManejadorPermiso instance = null;

    private ManejadorPermiso() {
    }

    public static ManejadorPermiso getInstance() {
        if (instance == null) {
            instance = new ManejadorPermiso();
        }
        return instance;
    }

    public Permiso obtenerPermiso(DtPermiso dtPermiso) {
        return (Permiso) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtPermiso);
    }

    public Permiso obtenerPermiso(Elemento elemento, Operacion operacion) {
        return (Permiso) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new Permiso(), "elemento", elemento, "operacion", operacion);
    }

    public void crearPermiso(Permiso permiso) throws Exception {
        ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(permiso);
    }

    public void crearPermiso(Elemento elemento, Operacion operacion) throws Exception {
        Permiso permiso = this.obtenerPermiso(elemento, operacion);
        if (permiso == null) {
            permiso = new Permiso(elemento, operacion);
            this.crearPermiso(permiso);
        }
    }

}
