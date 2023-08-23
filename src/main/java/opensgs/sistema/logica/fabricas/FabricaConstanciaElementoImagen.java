package opensgs.sistema.logica.fabricas;

import opensgs.sistema.interfaces.IControladorConstanciaElementoImagen;
import opensgs.sistema.logica.controladores.ControladorConstanciaElementoImagen;

public class FabricaConstanciaElementoImagen {
    
    private FabricaConstanciaElementoImagen() {
    }
    
    public static FabricaConstanciaElementoImagen getInstance() {
        return FabricaConstanciaElementoImagenHolder.INSTANCE;
    }
    
    private static class FabricaConstanciaElementoImagenHolder {
        private static final FabricaConstanciaElementoImagen INSTANCE = new FabricaConstanciaElementoImagen();
    }
    
    public static IControladorConstanciaElementoImagen getIControladorConstanciaElementoImagen() {
            return new ControladorConstanciaElementoImagen();
    }
}
