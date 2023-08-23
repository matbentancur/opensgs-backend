package opensgs.sistema.logica.fabricas;

import opensgs.sistema.interfaces.IControladorActividad;
import opensgs.sistema.logica.controladores.ControladorActividad;

public class FabricaActividad {
    
    private FabricaActividad() {
    }
    
    public static FabricaActividad getInstance() {
        return FabricaActividadHolder.INSTANCE;
    }
    
    private static class FabricaActividadHolder {
        private static final FabricaActividad INSTANCE = new FabricaActividad();
    }
    
    public static IControladorActividad getIControladorActividad() {
            return new ControladorActividad();
    }
}
