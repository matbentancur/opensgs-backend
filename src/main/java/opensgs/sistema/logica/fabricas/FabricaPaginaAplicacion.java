package opensgs.sistema.logica.fabricas;

import opensgs.sistema.interfaces.IControladorPaginaAplicacion;
import opensgs.sistema.logica.controladores.ControladorPaginaAplicacion;

public class FabricaPaginaAplicacion {
    
    private FabricaPaginaAplicacion() {
    }
    
    public static FabricaPaginaAplicacion getInstance() {
        return FabricaPaginaAplicacionHolder.INSTANCE;
    }
    
    private static class FabricaPaginaAplicacionHolder {
        private static final FabricaPaginaAplicacion INSTANCE = new FabricaPaginaAplicacion();
    }
    
    public static IControladorPaginaAplicacion getIControladorPaginaAplicacion() {
            return new ControladorPaginaAplicacion();
    }
}
