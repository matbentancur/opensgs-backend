package opensgs.sistema.logica.fabricas;

import opensgs.sistema.interfaces.IControladorNotificacion;
import opensgs.sistema.logica.controladores.ControladorNotificacion;

public class FabricaNotificacion {
    
    private FabricaNotificacion() {
    }
    
    public static FabricaNotificacion getInstance() {
        return FabricaNotificacionHolder.INSTANCE;
    }
    
    private static class FabricaNotificacionHolder {
        private static final FabricaNotificacion INSTANCE = new FabricaNotificacion();
    }
    
    public static IControladorNotificacion getIControladorNotificacion() {
            return new ControladorNotificacion();
    }
}
