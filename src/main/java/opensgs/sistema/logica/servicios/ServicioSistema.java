package opensgs.sistema.logica.servicios;

import opensgs.sistema.logica.beans.Sistema;
import opensgs.sistema.persistencia.manejadores.ManejadorSistema;

public class ServicioSistema {

    private static ServicioSistema instance = null;

    private ServicioSistema() {
    }

    public static ServicioSistema getInstance() {
        if (instance == null) {
            instance = new ServicioSistema();
        }
        return instance;
    }

    public static String obtenerFilesPath() {
        Sistema sistema = ManejadorSistema.getInstance().obtenerSistema();
        return sistema.getFilesPath();
    }

}
