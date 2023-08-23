package opensgs.sistema.logica.fabricas;

import opensgs.sistema.interfaces.IControladorArchivo;
import opensgs.sistema.logica.controladores.ControladorArchivo;

public class FabricaArchivo {
    
    private FabricaArchivo() {
    }
    
    public static FabricaArchivo getInstance() {
        return FabricaArchivoHolder.INSTANCE;
    }
    
    private static class FabricaArchivoHolder {
        private static final FabricaArchivo INSTANCE = new FabricaArchivo();
    }
    
    public static IControladorArchivo getIControladorArchivo() {
            return new ControladorArchivo();
    }
}
