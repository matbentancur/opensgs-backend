package opensgs.sistema.persistencia.manejadores;

import java.util.ArrayList;
import java.util.List;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.datatypes.DtPaginaAplicacion;
import opensgs.sistema.logica.beans.PaginaAplicacion;

public class ManejadorPaginaAplicacion {

    private static ManejadorPaginaAplicacion instance = null;

    private ManejadorPaginaAplicacion() {
    }

    public static ManejadorPaginaAplicacion getInstance() {
        if (instance == null) {
            instance = new ManejadorPaginaAplicacion();
        }
        return instance;
    }

    public PaginaAplicacion obtenerPaginaAplicacion(DtPaginaAplicacion dtPaginaAplicacion) {
        return (PaginaAplicacion) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtPaginaAplicacion);
    }

    public PaginaAplicacion obtenerPaginaAplicacion(Long id) {
        return (PaginaAplicacion) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new PaginaAplicacion(), id);
    }
    
    public List<PaginaAplicacion> listarPaginasAplicacionPlantillaAplicacion(Long plantillaAplicacionId, boolean borrado) {
        List<PaginaAplicacion> listaPaginasAplicacion = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new PaginaAplicacion(), "borrado", borrado, "plantillaaplicacion_id", plantillaAplicacionId);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaPaginasAplicacion.add((PaginaAplicacion) openSGSManagedBean);
        }
        return listaPaginasAplicacion;
    }
    
    public List<PaginaAplicacion> listarPaginasAplicacionPlantillaAplicacionActivas(Long plantillaAplicacionId) {
        List<PaginaAplicacion> listaPaginasAplicacion = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new PaginaAplicacion(), "activo", true, "borrado", false, "plantillaaplicacion_id", plantillaAplicacionId);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaPaginasAplicacion.add((PaginaAplicacion) openSGSManagedBean);
        }
        return listaPaginasAplicacion;
    }

//    public List<PaginaAplicacion> listarPaginaAplicacionActivas() {
//        List<PaginaAplicacion> listaPaginasAplicacion = new ArrayList<>();
//
//        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new PaginaAplicacion(), "activo", true, "borrado", false);
//        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
//            listaPaginasAplicacion.add((PaginaAplicacion) openSGSManagedBean);
//        }
//        return listaPaginasAplicacion;
//    }

}
