package opensgs.sistema.persistencia.manejadores;

import opensgs.sistema.logica.beans.Sistema;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;


public class ManejadorSistema {
    
    private static ManejadorSistema instance = null;
    
    private ManejadorSistema() {
    }
    
    public static ManejadorSistema getInstance() {
        if (instance == null) {
            instance = new ManejadorSistema();
        }
        return instance;
    }
    
    public Sistema obtenerSistema() {
        return (Sistema) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new Sistema(), "id", 1L);
    }
    
}
