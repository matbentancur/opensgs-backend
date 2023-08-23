package opensgs.sistema.interfaces;

import java.util.List;
import opensgs.datatypes.DtMensaje;
import opensgs.sistema.datatypes.DtPreguntaFrecuente;
import opensgs.usuarios.datatypes.DtSesion;

public interface IControladorPreguntaFrecuente {

    public DtMensaje crearPreguntaFrecuenteAplicacion(DtPreguntaFrecuente dtPreguntaFrecuente, Long aplicacionId, DtSesion dtSesion);
    
    public List<DtPreguntaFrecuente> listarPreguntasSistema(boolean borrado, DtSesion dtSesion);

    public List<DtPreguntaFrecuente> listarPreguntasAplicacion(Long aplicacionId, boolean borrado, DtSesion dtSesion);
    
    public List<DtPreguntaFrecuente> listarPreguntasSistemaActivas();
    
    public List<DtPreguntaFrecuente> listarPreguntasAplicacionActivas(Long aplicacionId, DtSesion dtSesion);

    public List<DtPreguntaFrecuente> listarPreguntasFrecuentesActivas(DtSesion dtSesion);

}
