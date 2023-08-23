package opensgs.sistema.logica.fabricas;

import opensgs.sistema.interfaces.IControladorConstanciaElementoTexto;
import opensgs.sistema.logica.controladores.ControladorConstanciaElementoTexto;

public class FabricaConstanciaElementoTexto {
    
    private FabricaConstanciaElementoTexto() {
    }
    
    public static FabricaConstanciaElementoTexto getInstance() {
        return FabricaConstanciaElementoTextoHolder.INSTANCE;
    }
    
    private static class FabricaConstanciaElementoTextoHolder {
        private static final FabricaConstanciaElementoTexto INSTANCE = new FabricaConstanciaElementoTexto();
    }
    
    public static IControladorConstanciaElementoTexto getIControladorConstanciaElementoTexto() {
            return new ControladorConstanciaElementoTexto();
    }
}
