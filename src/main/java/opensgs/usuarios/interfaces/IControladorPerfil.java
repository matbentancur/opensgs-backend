package opensgs.usuarios.interfaces;

import java.util.List;
import opensgs.usuarios.datatypes.DtPerfil;
import opensgs.usuarios.datatypes.DtSesion;

public interface IControladorPerfil {

    public List<DtPerfil> listarPerfilesGlobales(DtSesion dtSesion);
    
    public List<DtPerfil> listarPerfilesAplicacion(DtSesion dtSesion);

}
