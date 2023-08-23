package opensgs.sistema.logica.fabricas;

import opensgs.sistema.interfaces.IControladorPreguntaFrecuente;
import opensgs.sistema.logica.controladores.ControladorPreguntaFrecuente;

public class FabricaPreguntaFrecuente {
    
    private FabricaPreguntaFrecuente() {
    }
    
    public static FabricaPreguntaFrecuente getInstance() {
        return FabricaPreguntaFrecuenteHolder.INSTANCE;
    }
    
    private static class FabricaPreguntaFrecuenteHolder {
        private static final FabricaPreguntaFrecuente INSTANCE = new FabricaPreguntaFrecuente();
    }
    
    public static IControladorPreguntaFrecuente getIControladorPreguntaFrecuente() {
            return new ControladorPreguntaFrecuente();
    }
}
