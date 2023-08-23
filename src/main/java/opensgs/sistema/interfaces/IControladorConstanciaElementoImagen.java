package opensgs.sistema.interfaces;

import java.util.List;
import javax.activation.DataHandler;
import opensgs.datatypes.DtMensaje;
import opensgs.sistema.datatypes.DtConstanciaElementoImagen;
import opensgs.usuarios.datatypes.DtSesion;

public interface IControladorConstanciaElementoImagen {

    public DtMensaje crearConstanciaElementoImagen(DataHandler archivoSubir, DtConstanciaElementoImagen dtConstanciaElementoImagen, DtSesion dtSesion);

    public DtMensaje modificarConstanciaElementoImagen(DataHandler archivoSubir, DtConstanciaElementoImagen dtConstanciaElementoImagen, DtSesion dtSesion);

    public List<DtConstanciaElementoImagen> listarConstanciaElementoImagen(Long constanciaId, boolean borrado, DtSesion dtSesion);
}
