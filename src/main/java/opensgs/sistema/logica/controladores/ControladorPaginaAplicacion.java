package opensgs.sistema.logica.controladores;

import java.util.List;
import opensgs.datatypes.DtMensaje;
import opensgs.enums.Operacion;
import opensgs.logica.servicios.ServicioOpenSGSBean;
import opensgs.sistema.datatypes.DtPaginaAplicacion;
import opensgs.sistema.interfaces.IControladorPaginaAplicacion;
import opensgs.sistema.logica.beans.PaginaAplicacion;
import opensgs.sistema.persistencia.manejadores.ManejadorPaginaAplicacion;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.logica.servicios.ServicioPermiso;
import opensgs.usuarios.logica.servicios.ServicioSesion;

public class ControladorPaginaAplicacion implements IControladorPaginaAplicacion {

    @Override
    public List<DtPaginaAplicacion> listarPaginasAplicacionPlantillaAplicacion(Long plantillaAplicacionId, boolean borrado, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new PaginaAplicacion(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<PaginaAplicacion> listaOpenSGSManagedBean = ManejadorPaginaAplicacion.getInstance().listarPaginasAplicacionPlantillaAplicacion(plantillaAplicacionId, borrado);

        return (List<DtPaginaAplicacion>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaOpenSGSManagedBean);
    }

    @Override
    public List<DtPaginaAplicacion> listarPaginasAplicacionPlantillaAplicacionActivas(Long plantillaAplicacionId, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<PaginaAplicacion> listaOpenSGSManagedBean = ManejadorPaginaAplicacion.getInstance().listarPaginasAplicacionPlantillaAplicacionActivas(plantillaAplicacionId);

        return (List<DtPaginaAplicacion>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaOpenSGSManagedBean);
    }

}
