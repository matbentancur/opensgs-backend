package opensgs.sistema.interfaces;

import java.util.List;
import opensgs.sistema.datatypes.DtAplicacion;
import opensgs.usuarios.datatypes.DtSesion;

public interface IControladorAplicacion {

    public List<DtAplicacion> listarAplicacionesAbiertas();

    public List<DtAplicacion> listarAplicacionesCerradas();

    public List<DtAplicacion> listarAplicacionesProximas();
    
    public DtAplicacion obtenerDatosBasicosAplicacion(Long aplicacionId, DtSesion dtSesion);
    
    public List<DtAplicacion> listarAplicacionesActivas();

}
