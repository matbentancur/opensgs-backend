package opensgs.usuarios.logica.fabricas;

import opensgs.usuarios.interfaces.IControladorPerfil;
import opensgs.usuarios.logica.controladores.ControladorPerfil;

public class FabricaPerfil {
    
    private FabricaPerfil() {
    }
    
    public static FabricaPerfil getInstance() {
        return FabricaPerfilHolder.INSTANCE;
    }
    
    private static class FabricaPerfilHolder {
        private static final FabricaPerfil INSTANCE = new FabricaPerfil();
    }
    
    public static IControladorPerfil getIControladorPerfil() {
            return new ControladorPerfil();
    }
}
