package opensgs.sistema.interfaces;

import java.util.List;
import opensgs.sistema.datatypes.DtPaginaAplicacion;
import opensgs.usuarios.datatypes.DtSesion;

public interface IControladorPaginaAplicacion {

    public List<DtPaginaAplicacion> listarPaginasAplicacionPlantillaAplicacion(Long plantillaAplicacionIds, boolean borrado, DtSesion dtSesion);
    public List<DtPaginaAplicacion> listarPaginasAplicacionPlantillaAplicacionActivas(Long plantillaAplicacionIds, DtSesion dtSesion);

}
