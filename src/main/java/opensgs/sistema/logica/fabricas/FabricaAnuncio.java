package opensgs.sistema.logica.fabricas;

import opensgs.sistema.interfaces.IControladorAnuncio;
import opensgs.sistema.logica.controladores.ControladorAnuncio;

public class FabricaAnuncio {
    
    private FabricaAnuncio() {
    }
    
    public static FabricaAnuncio getInstance() {
        return FabricaAnuncioHolder.INSTANCE;
    }
    
    private static class FabricaAnuncioHolder {
        private static final FabricaAnuncio INSTANCE = new FabricaAnuncio();
    }
    
    public static IControladorAnuncio getIControladorAnuncio() {
            return new ControladorAnuncio();
    }
}
