package opensgs.sistema.interfaces;

import java.util.List;
import javax.activation.DataHandler;
import opensgs.datatypes.DtMensaje;
import opensgs.sistema.datatypes.DtArchivo;
import opensgs.usuarios.datatypes.DtSesion;

public interface IControladorArchivo {

    public DtMensaje crearArchivo(DataHandler archivoSubir, DtArchivo dtArchivo, DtSesion dtSesion);

    public DtMensaje crearArchivoAplicacion(DataHandler archivoSubir, DtArchivo dtArchivo, Long aplicacionId, DtSesion dtSesion);

    public DtMensaje modificarArchivo(DataHandler archivoSubir, DtArchivo dtArchivo, DtSesion dtSesion);

    public DtMensaje modificarArchivoAplicacion(DataHandler archivoSubir, DtArchivo dtArchivo, Long aplicacionId, DtSesion dtSesion);

    public List<DtArchivo> listarArchivosSistema(boolean borrado, DtSesion dtSesion);

    public List<DtArchivo> listarArchivosAplicacion(Long aplicacionId, boolean borrado, DtSesion dtSesion);

    public DataHandler descargarArchivo(Long id, DtSesion dtSesion);

    public DataHandler descargarArchivoAplicacion(Long id, Long aplicacionId, DtSesion dtSesion);

    public DataHandler descargarArchivoPublico(Long id);

    public DtArchivo obtenerDtArchivoPublico(Long id);

    public List<DtArchivo> listarArchivosSistemaActivos();

    public List<DtArchivo> listarArchivosAplicacionActivos(Long aplicacionId, DtSesion dtSesion);

    public List<DtArchivo> listarArchivosActivos(DtSesion dtSesion);

}
