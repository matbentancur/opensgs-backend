package opensgs.solicitudes.persistencia.manejadores;

import java.util.ArrayList;
import java.util.List;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.solicitudes.datatypes.DtSolicitud;
import opensgs.solicitudes.logica.beans.Solicitud;
import opensgs.usuarios.logica.beans.Usuario;

public class ManejadorSolicitud {

    private static ManejadorSolicitud instance = null;

    private ManejadorSolicitud() {
    }

    public static ManejadorSolicitud getInstance() {
        if (instance == null) {
            instance = new ManejadorSolicitud();
        }
        return instance;
    }
    
    public Solicitud obtenerSolicitud(DtSolicitud dtSolicitud) {
        return (Solicitud) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtSolicitud);
    }
    
    public Solicitud obtenerSolicitud(Long id) {
        return (Solicitud) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new Solicitud(), id);
    }

    public List<Solicitud> listarMisSolicitudes(Usuario usuario) {
        List<Solicitud> listaSolicitudes = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new Solicitud(), "borrado", false, "activo", true, "usuario_id", usuario.getId());
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaSolicitudes.add((Solicitud) openSGSManagedBean);
        }
        return listaSolicitudes;
    }

    public List<Solicitud> listarSolicitudesAplicacion(Aplicacion aplicacion, boolean borrado) {
        List<Solicitud> listaSolicitudes = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new Solicitud(), "borrado", borrado, "aplicacion_id", aplicacion.getId());
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaSolicitudes.add((Solicitud) openSGSManagedBean);
        }
        return listaSolicitudes;
    }

}
