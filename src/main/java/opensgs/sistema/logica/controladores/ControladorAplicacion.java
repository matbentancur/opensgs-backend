package opensgs.sistema.logica.controladores;

import java.util.List;
import opensgs.datatypes.DtMensaje;
import opensgs.logica.servicios.ServicioOpenSGSBean;
import opensgs.sistema.datatypes.DtAplicacion;
import opensgs.sistema.interfaces.IControladorAplicacion;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.sistema.persistencia.manejadores.ManejadorAplicacion;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.logica.servicios.ServicioSesion;

public class ControladorAplicacion implements IControladorAplicacion {

    @Override
    public List<DtAplicacion> listarAplicacionesAbiertas() {

        List<Aplicacion> listaAplicaciones = ManejadorAplicacion.getInstance().listarAplicacionesAbiertas();

        return (List<DtAplicacion>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaAplicaciones);
    }

    @Override
    public List<DtAplicacion> listarAplicacionesCerradas() {
        List<Aplicacion> listaAplicaciones = ManejadorAplicacion.getInstance().listarAplicacionesCerradas();

        return (List<DtAplicacion>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaAplicaciones);
    }

    @Override
    public List<DtAplicacion> listarAplicacionesProximas() {
        List<Aplicacion> listaAplicaciones = ManejadorAplicacion.getInstance().listarAplicacionesProximas();

        return (List<DtAplicacion>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaAplicaciones);
    }
    
    @Override
    public DtAplicacion obtenerDatosBasicosAplicacion(Long aplicacionId, DtSesion dtSesion) {

        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        Aplicacion aplicacion = ManejadorAplicacion.getInstance().obtenerAplicacion(aplicacionId);
        return aplicacion.getDatosBasicosAplicacion();
    }
    
    @Override
    public List<DtAplicacion> listarAplicacionesActivas() {

        List<Aplicacion> listaAplicaciones = ManejadorAplicacion.getInstance().listarAplicacionesActivas();

        return (List<DtAplicacion>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaAplicaciones);
    }

}
