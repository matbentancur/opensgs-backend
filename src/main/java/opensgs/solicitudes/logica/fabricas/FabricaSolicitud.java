package opensgs.solicitudes.logica.fabricas;

import opensgs.solicitudes.interfaces.IControladorSolicitud;
import opensgs.solicitudes.logica.controladores.ControladorSolicitud;

public class FabricaSolicitud {
    
    private FabricaSolicitud() {
    }
    
    public static FabricaSolicitud getInstance() {
        return FabricaSolicitudHolder.INSTANCE;
    }
    
    private static class FabricaSolicitudHolder {
        private static final FabricaSolicitud INSTANCE = new FabricaSolicitud();
    }
    
    public static IControladorSolicitud getIControladorSolicitud() {
            return new ControladorSolicitud();
    }
}
