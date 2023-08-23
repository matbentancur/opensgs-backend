package opensgs.sistema.persistencia.manejadores;

import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.logica.beans.ServidorCorreo;

public class ManejadorServidorCorreo {

    private static ManejadorServidorCorreo instance = null;

    private ManejadorServidorCorreo() {
    }

    public static ManejadorServidorCorreo getInstance() {
        if (instance == null) {
            instance = new ManejadorServidorCorreo();
        }
        return instance;
    }

    public ServidorCorreo obtenerServidorCorreo(Long id) {
        return (ServidorCorreo) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new ServidorCorreo(), id);
    }
}
