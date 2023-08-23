package opensgs.sistema.persistencia.manejadores;

import opensgs.sistema.datatypes.DtPlantillaCorreo;
import opensgs.sistema.logica.beans.PlantillaCorreo;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;

public class ManejadorPlantillaCorreo {

    private static ManejadorPlantillaCorreo instance = null;

    private ManejadorPlantillaCorreo() {
    }

    public static ManejadorPlantillaCorreo getInstance() {
        if (instance == null) {
            instance = new ManejadorPlantillaCorreo();
        }
        return instance;
    }

    public PlantillaCorreo obtenerPlantillaCorreo(DtPlantillaCorreo dtPlantillaCorreo) {
        return (PlantillaCorreo) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtPlantillaCorreo);
    }

}
