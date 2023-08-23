package opensgs.sistema.logica.fabricas;

import opensgs.sistema.interfaces.IControladorReporte;
import opensgs.sistema.logica.controladores.ControladorReporte;

public class FabricaReporte {
    
    private FabricaReporte() {
    }
    
    public static FabricaReporte getInstance() {
        return FabricaReporteHolder.INSTANCE;
    }
    
    private static class FabricaReporteHolder {
        private static final FabricaReporte INSTANCE = new FabricaReporte();
    }
    
    public static IControladorReporte getIControladorReporte() {
            return new ControladorReporte();
    }
}
