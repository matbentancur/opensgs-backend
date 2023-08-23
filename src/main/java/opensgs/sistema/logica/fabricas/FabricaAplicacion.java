package opensgs.sistema.logica.fabricas;

import opensgs.sistema.interfaces.IControladorAplicacion;
import opensgs.sistema.logica.controladores.ControladorAplicacion;

public class FabricaAplicacion {
    
    private FabricaAplicacion() {
    }
    
    public static FabricaAplicacion getInstance() {
        return FabricaAplicacionHolder.INSTANCE;
    }
    
    private static class FabricaAplicacionHolder {
        private static final FabricaAplicacion INSTANCE = new FabricaAplicacion();
    }
    
    public static IControladorAplicacion getIControladorAplicacion() {
            return new ControladorAplicacion();
    }
}
