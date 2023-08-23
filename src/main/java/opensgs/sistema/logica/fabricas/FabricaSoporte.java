package opensgs.sistema.logica.fabricas;

import opensgs.sistema.interfaces.IControladorSoporte;
import opensgs.sistema.logica.controladores.ControladorSoporte;

public class FabricaSoporte {
    
    private FabricaSoporte() {
    }
    
    public static FabricaSoporte getInstance() {
        return FabricaSoporteHolder.INSTANCE;
    }
    
    private static class FabricaSoporteHolder {
        private static final FabricaSoporte INSTANCE = new FabricaSoporte();
    }
    
    public static IControladorSoporte getIControladorSoporte() {
            return new ControladorSoporte();
    }
}
