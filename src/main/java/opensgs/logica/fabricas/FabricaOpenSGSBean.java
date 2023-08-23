package opensgs.logica.fabricas;

import opensgs.interfaces.IControladorOpenSGSBean;
import opensgs.logica.controladores.ControladorOpenSGSBean;

public class FabricaOpenSGSBean {
    
    private FabricaOpenSGSBean() {
    }
    
    public static FabricaOpenSGSBean getInstance() {
        return FabricaOpenSGSBeanHolder.INSTANCE;
    }
    
    private static class FabricaOpenSGSBeanHolder {
        private static final FabricaOpenSGSBean INSTANCE = new FabricaOpenSGSBean();
    }
    
    public static IControladorOpenSGSBean getIControladorOpenSGSBean() {
            return new ControladorOpenSGSBean();
    }
}
