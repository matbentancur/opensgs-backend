package opensgs.sistema.logica.fabricas;

import opensgs.sistema.interfaces.IControladorServidorCorreo;
import opensgs.sistema.logica.controladores.ControladorServidorCorreo;

public class FabricaServidorCorreo {
    
    private FabricaServidorCorreo() {
    }
    
    public static FabricaServidorCorreo getInstance() {
        return FabricaServidorCorreoHolder.INSTANCE;
    }
    
    private static class FabricaServidorCorreoHolder {
        private static final FabricaServidorCorreo INSTANCE = new FabricaServidorCorreo();
    }
    
    public static IControladorServidorCorreo getIControladorServidorCorreo() {
            return new ControladorServidorCorreo();
    }
}
