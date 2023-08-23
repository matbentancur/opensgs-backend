package opensgs.usuarios.logica.fabricas;

import opensgs.usuarios.interfaces.IControladorUsuario;
import opensgs.usuarios.logica.controladores.ControladorUsuario;

public class FabricaUsuario {
    
    private FabricaUsuario() {
    }
    
    public static FabricaUsuario getInstance() {
        return FabricaUsuarioHolder.INSTANCE;
    }
    
    private static class FabricaUsuarioHolder {
        private static final FabricaUsuario INSTANCE = new FabricaUsuario();
    }
    
    public static IControladorUsuario getIControladorUsuario() {
            return new ControladorUsuario();
    }
}
