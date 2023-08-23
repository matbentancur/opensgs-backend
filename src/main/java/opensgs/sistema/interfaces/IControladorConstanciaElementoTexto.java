package opensgs.sistema.interfaces;

import java.util.List;
import opensgs.sistema.datatypes.DtConstanciaElementoTexto;
import opensgs.usuarios.datatypes.DtSesion;

public interface IControladorConstanciaElementoTexto {

    public List<DtConstanciaElementoTexto> listarConstanciaElementoTexto(Long constanciaId, boolean borrado, DtSesion dtSesion);
}
