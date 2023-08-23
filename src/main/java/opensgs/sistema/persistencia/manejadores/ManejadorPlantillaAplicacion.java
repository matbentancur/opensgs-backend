package opensgs.sistema.persistencia.manejadores;

import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.datatypes.DtPlantillaAplicacion;
import opensgs.sistema.logica.beans.PlantillaAplicacion;

public class ManejadorPlantillaAplicacion {

    private static ManejadorPlantillaAplicacion instance = null;

    private ManejadorPlantillaAplicacion() {
    }

    public static ManejadorPlantillaAplicacion getInstance() {
        if (instance == null) {
            instance = new ManejadorPlantillaAplicacion();
        }
        return instance;
    }

    public PlantillaAplicacion obtenerPlantillaAplicacion(DtPlantillaAplicacion dtPlantillaAplicacion) {
        return (PlantillaAplicacion) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtPlantillaAplicacion);
    }
    
    public PlantillaAplicacion obtenerPlantillaAplicacion(Long id) {
        return (PlantillaAplicacion) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new PlantillaAplicacion(), "id", id);
    }

}
