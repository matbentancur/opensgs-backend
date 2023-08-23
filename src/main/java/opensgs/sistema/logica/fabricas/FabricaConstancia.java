package opensgs.sistema.logica.fabricas;

import opensgs.sistema.interfaces.IControladorConstancia;
import opensgs.sistema.logica.controladores.ControladorConstancia;

public class FabricaConstancia {
    
    private FabricaConstancia() {
    }
    
    public static FabricaConstancia getInstance() {
        return FabricaConstanciaHolder.INSTANCE;
    }
    
    private static class FabricaConstanciaHolder {
        private static final FabricaConstancia INSTANCE = new FabricaConstancia();
    }
    
    public static IControladorConstancia getIControladorConstancia() {
            return new ControladorConstancia();
    }
}
