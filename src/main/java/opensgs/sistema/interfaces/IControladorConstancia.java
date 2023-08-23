package opensgs.sistema.interfaces;

import java.util.List;
import javax.activation.DataHandler;
import opensgs.datatypes.DtMensaje;
import opensgs.sistema.datatypes.DtConstancia;
import opensgs.usuarios.datatypes.DtSesion;

public interface IControladorConstancia {
   
    public DtMensaje crearConstancia(DataHandler archivoSubir, DtConstancia dtConstancia, DtSesion dtSesion);

    public DtMensaje modificarConstancia(DataHandler archivoSubir, DtConstancia dtConstancia, DtSesion dtSesion);
    
    public List<DtConstancia> listarConstancias(Long aplicacionId, boolean borrado, DtSesion dtSesion);

//    public List<DtConstancia> listarConstanciasActivas(DtSesion dtSesion);
    
    public DataHandler emitirConstancia(Long id, DtSesion dtSesion);
    
}
