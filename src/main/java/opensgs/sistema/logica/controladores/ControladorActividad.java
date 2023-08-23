package opensgs.sistema.logica.controladores;

import java.util.Date;
import java.util.List;
import opensgs.datatypes.DtMensaje;
import opensgs.enums.Elemento;
import opensgs.enums.Operacion;
import opensgs.logica.servicios.ServicioEnum;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.logica.servicios.ServicioOpenSGSBean;
import opensgs.sistema.datatypes.DtActividad;
import opensgs.sistema.interfaces.IControladorActividad;
import opensgs.sistema.logica.beans.Actividad;
import opensgs.sistema.persistencia.manejadores.ManejadorActividad;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.logica.servicios.ServicioPermiso;
import opensgs.usuarios.logica.servicios.ServicioSesion;

public class ControladorActividad implements IControladorActividad {

    @Override
    public List<DtActividad> listarActividadElemento(String elemento, Long identificador, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Actividad(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        Elemento e = ServicioEnum.getInstance().parseEnum(Elemento.class, elemento);

        List<Actividad> listaActividad = ManejadorActividad.getInstance().listarActividadElemento(e, identificador);

        return (List<DtActividad>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaActividad);
    }

    @Override
    public List<DtActividad> listarActividadElementoAplicacion(String elemento, Long identificador, Long aplicacionId, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Actividad(), dtSesion);
        if (!dtMensaje.isExito()) {
            dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Ver, new Actividad(), aplicacionId, dtSesion);
            if (!dtMensaje.isExito()) {
                return null;
            }
        }

        Elemento e = ServicioEnum.getInstance().parseEnum(Elemento.class, elemento);

        List<Actividad> listaActividad = ManejadorActividad.getInstance().listarActividadElemento(e, identificador);

        return (List<DtActividad>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaActividad);
    }

    @Override
    public List<DtActividad> listarActividadTemporal(String inicioString, String finString, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Actividad(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        Date inicio = ServicioFechaHora.getInstance().stringToDateTime(inicioString);
        Date fin = ServicioFechaHora.getInstance().stringToDateTime(finString);
        List<Actividad> listaActividad = ManejadorActividad.getInstance().listarActividadTemporal(inicio, fin);

        return (List<DtActividad>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaActividad);
    }

}
