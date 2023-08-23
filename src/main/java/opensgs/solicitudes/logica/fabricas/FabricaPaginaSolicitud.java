package opensgs.solicitudes.logica.fabricas;

import opensgs.solicitudes.interfaces.IControladorPaginaSolicitud;
import opensgs.solicitudes.logica.controladores.ControladorPaginaSolicitud;

public class FabricaPaginaSolicitud {
    
    private FabricaPaginaSolicitud() {
    }
    
    public static FabricaPaginaSolicitud getInstance() {
        return FabricaPaginaSolicitudHolder.INSTANCE;
    }
    
    private static class FabricaPaginaSolicitudHolder {
        private static final FabricaPaginaSolicitud INSTANCE = new FabricaPaginaSolicitud();
    }
    
    public static IControladorPaginaSolicitud getIControladorPaginaSolicitud() {
            return new ControladorPaginaSolicitud();
    }
}
