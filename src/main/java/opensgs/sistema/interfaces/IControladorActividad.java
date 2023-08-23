package opensgs.sistema.interfaces;

import java.util.List;
import opensgs.sistema.datatypes.DtActividad;
import opensgs.usuarios.datatypes.DtSesion;

public interface IControladorActividad {

    public List<DtActividad> listarActividadElemento(String elemento, Long identificador, DtSesion dtSesion);

    public List<DtActividad> listarActividadElementoAplicacion(String elemento, Long identificador, Long aplicacionId, DtSesion dtSesion);

    public List<DtActividad> listarActividadTemporal(String inicioString, String finString, DtSesion dtSesion);

}
