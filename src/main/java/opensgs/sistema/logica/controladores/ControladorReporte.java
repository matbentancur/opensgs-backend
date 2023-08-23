package opensgs.sistema.logica.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import opensgs.datatypes.DtMensaje;
import opensgs.enums.Alcance;
import opensgs.enums.Operacion;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.logica.servicios.ServicioOpenSGSBean;
import opensgs.logica.validators.ValidatorOpenSGSBean;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.datatypes.DtReporte;
import opensgs.sistema.interfaces.IControladorReporte;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.sistema.logica.beans.Reporte;
import opensgs.sistema.logica.servicios.ServicioActividad;
import opensgs.sistema.logica.servicios.ServicioNotificacion;
import opensgs.sistema.logica.servicios.ServicioReporte;
import opensgs.sistema.persistencia.manejadores.ManejadorAplicacion;
import opensgs.sistema.persistencia.manejadores.ManejadorReporte;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.logica.servicios.ServicioPermiso;
import opensgs.usuarios.logica.servicios.ServicioSesion;

public class ControladorReporte implements IControladorReporte {

    @Override
    public DtMensaje crearReporteAplicacion(DtReporte dtReporte, Long aplicacionId, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isCreable(dtReporte);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            Reporte reporte = (Reporte) ServicioOpenSGSBean.getInstance().getNewOpenSGSManagedBean(dtReporte);
            if (reporte == null) {
                return ServicioMensaje.getInstance().getMensajeERROR(dtReporte.getClassName() + ".create.error");
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Crear, new Reporte(), dtSesion);
            if (!dtMensaje.isExito()) {
                dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Crear, new Reporte(), aplicacionId, dtSesion);
                if (!dtMensaje.isExito()) {
                    return dtMensaje;
                }
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarCrear(reporte);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            reporte.setAlcance(Alcance.APLICACION);

            ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(reporte);

            ServicioActividad.getInstance().crearActividad(Operacion.Crear, reporte, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Crear, reporte, dtSesion);

            Aplicacion aplicacion = ManejadorAplicacion.getInstance().obtenerAplicacion(aplicacionId);
            aplicacion.agregarListOpenSGSBean(reporte);

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(aplicacion);

            ServicioActividad.getInstance().crearActividad(Operacion.Agregar, aplicacion, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Agregar, aplicacion, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtReporte.getClassName() + ".create.success", reporte.getId());
        } catch (Exception ex) {
            Logger.getLogger(ControladorAnuncio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServicioMensaje.getInstance().getMensajeERROR(dtReporte.getClassName() + ".create.error");
    }

    @Override
    public List<DtReporte> listarReportesSistema(boolean borrado, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Reporte(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<Reporte> listaReportes = ManejadorReporte.getInstance().listarReportesSistema(borrado);

        return (List<DtReporte>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaReportes);
    }

    @Override
    public List<DtReporte> listarReportesAplicacion(Long aplicacionId, boolean borrado, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Reporte(), dtSesion);
        if (!dtMensaje.isExito()) {
            dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Ver, new Reporte(), aplicacionId, dtSesion);
            if (!dtMensaje.isExito()) {
                return null;
            }
        }

        List<Reporte> listaReportes = new ArrayList<>();
        List<Reporte> listaReportesAplicacion = ManejadorReporte.getInstance().listarReportesAplicacion(aplicacionId, borrado);

//        if (!borrado) {
//            List<Reporte> listaReportesGlobales = ManejadorReporte.getInstance().listarReportesGlobalesActivos();
//            List<Reporte> listaReportesAplicaciones = ManejadorReporte.getInstance().listarReportesAplicacionesActivos();
//            listaReportes.addAll(listaReportesGlobales);
//            listaReportes.addAll(listaReportesAplicaciones);
//        }

        listaReportes.addAll(listaReportesAplicacion);

        return (List<DtReporte>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaReportes);
    }

    @Override
    public List<DtReporte> listarReportesActivos(DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Reporte(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<Reporte> listaReportes = ManejadorReporte.getInstance().listarReportesActivos();

        return (List<DtReporte>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaReportes);
    }

    public DataHandler descargarReporteCSV(Long id, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Reporte(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        return ServicioReporte.getInstance().descargarReporteCSV(id);
    }

    public DataHandler descargarReporteCSVAplicacion(Long id, Long aplicacionId, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Reporte(), dtSesion);
        if (!dtMensaje.isExito()) {
            dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Ver, new Reporte(), aplicacionId, dtSesion);
            if (!dtMensaje.isExito()) {
                return null;
            }
        }

        return ServicioReporte.getInstance().descargarReporteCSV(id);
    }

}
