package opensgs.sistema.interfaces;

import java.util.List;
import opensgs.datatypes.DtMensaje;
import opensgs.sistema.datatypes.DtAnuncio;
import opensgs.usuarios.datatypes.DtSesion;

public interface IControladorAnuncio {

    public DtMensaje crearAnuncioAplicacion(DtAnuncio dtAnuncio, Long aplicacionId, DtSesion dtSesion);

    public List<DtAnuncio> listarAnunciosSistema(boolean borrado, DtSesion dtSesion);

    public List<DtAnuncio> listarAnunciosAplicacion(Long aplicacionId, boolean borrado, DtSesion dtSesion);

    public List<DtAnuncio> listarAnunciosSistemaVigentes();

    public List<DtAnuncio> listarAnunciosAplicacionVigentes(Long aplicacionId, DtSesion dtSesion);

    public List<DtAnuncio> listarAnunciosAplicacionesVigentes(DtSesion dtSesion);

}
