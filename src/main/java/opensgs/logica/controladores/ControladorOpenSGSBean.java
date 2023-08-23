package opensgs.logica.controladores;

import opensgs.datatypes.DtMensaje;
import opensgs.enums.Operacion;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.interfaces.IControladorOpenSGSBean;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.logica.servicios.ServicioEnum;
import opensgs.sistema.logica.servicios.ServicioActividad;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.logica.servicios.ServicioOpenSGSBean;
import opensgs.logica.validators.ValidatorOpenSGSBean;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.logica.servicios.ServicioNotificacion;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.logica.servicios.ServicioPermiso;
import opensgs.usuarios.logica.servicios.ServicioSesion;

public class ControladorOpenSGSBean implements IControladorOpenSGSBean {

    //OPENSGSBEAN
    @Override
    public DtOpenSGSBean obtenerOpenSGSBean(String className, Long id, DtSesion dtSesion) {

        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, className, dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        OpenSGSBean bean = ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(className, id);
        return bean.getDtOpenSGSBean();
    }

    @Override
    public DtOpenSGSBean obtenerOpenSGSBean(String className, String parametro, String valor, DtSesion dtSesion) {

        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, className, dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        OpenSGSBean bean = ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(className, parametro, valor);
        return bean.getDtOpenSGSBean();
    }

    @Override
    public DtOpenSGSBean obtenerOpenSGSBeanAplicacion(String className, Long id, Long aplicacionId, DtSesion dtSesion) {

        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, className, dtSesion);
        if (!dtMensaje.isExito()) {
            dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Ver, className, aplicacionId, dtSesion);
            if (!dtMensaje.isExito()) {
                return null;
            }
        }

        OpenSGSBean bean = ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(className, id);
        return bean.getDtOpenSGSBean();
    }

//    @Override
//    public DtOpenSGSBean obtenerOpenSGSBeanAplicacion(String className, String parametro, String valor, Long aplicacionId, DtSesion dtSesion) {
//
//        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
//        if (!dtMensaje.isExito()) {
//            return null;
//        }
//
//        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, className, dtSesion);
//        if (!dtMensaje.isExito()) {
//
//            Aplicacion aplicacion = ManejadorAplicacion.getInstance().obtenerAplicacion(aplicacionId);
//            if (aplicacion == null) {
//                return null;
//            }
//
//            dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Ver, className, aplicacion, dtSesion);
//            if (!dtMensaje.isExito()) {
//                return null;
//            }
//        }
//
//        OpenSGSBean bean = ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(className, parametro, valor);
//        return bean.getDtOpenSGSBean();
//    }
    @Override
    public List<DtOpenSGSBean> listarOpenSGSBean(String className, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, className, dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<OpenSGSBean> listaOpenSGSBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSBean(className);

        return (List<DtOpenSGSBean>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaOpenSGSBean);
    }

    @Override
    public List<DtOpenSGSManagedBean> listarOpenSGSManagedBean(String className, boolean borrado, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, className, dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(className, "borrado", borrado);

        return (List<DtOpenSGSManagedBean>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaOpenSGSManagedBean);
    }

//    @Override
//    public List<DtOpenSGSManagedBean> listarOpenSGSManagedBeanAplicacion(String className, boolean borrado, Long aplicacionId, DtSesion dtSesion) {
//        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
//        if (!dtMensaje.isExito()) {
//            return null;
//        }
//
//        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, className, dtSesion);
//        if (!dtMensaje.isExito()) {
//
//            Aplicacion aplicacion = ManejadorAplicacion.getInstance().obtenerAplicacion(aplicacionId);
//            if (aplicacion == null) {
//                return null;
//            }
//
//            dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Ver, className, aplicacion, dtSesion);
//            if (!dtMensaje.isExito()) {
//                return null;
//            }
//        }
//
//        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(className, "borrado", borrado);
//
//        return (List<DtOpenSGSManagedBean>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaOpenSGSManagedBean);
//    }
    @Override
    public List<DtOpenSGSManagedBean> listarOpenSGSManagedBean(String className, String paramatro, String valor, boolean borrado, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, className, dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(className, "borrado", borrado, paramatro, valor);

        return (List<DtOpenSGSManagedBean>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaOpenSGSManagedBean);
    }

    @Override
    public List<DtOpenSGSManagedBean> listarOpenSGSManagedBean(String className, String paramatro, Long valor, boolean borrado, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, className, dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(className, "borrado", borrado, paramatro, valor);

        return (List<DtOpenSGSManagedBean>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaOpenSGSManagedBean);
    }

    @Override
    public List<DtOpenSGSManagedBean> listarOpenSGSManagedBeanActivos(String className, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, className, dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(className, "activo", true, "borrado", false);

        return (List<DtOpenSGSManagedBean>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaOpenSGSManagedBean);
    }

    @Override
    public List<DtOpenSGSManagedBean> listarOpenSGSManagedBeanActivos(String className, String paramatro, String valor, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, className, dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(className, "activo", true, "borrado", false, paramatro, valor);

        return (List<DtOpenSGSManagedBean>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaOpenSGSManagedBean);
    }

    @Override
    public List<DtOpenSGSManagedBean> listarOpenSGSManagedBeanActivos(String className, String paramatro, Long valor, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, className, dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(className, "activo", true, "borrado", false, paramatro, valor);

        return (List<DtOpenSGSManagedBean>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaOpenSGSManagedBean);
    }

    @Override
    public List<String> listarEnum(String enumName, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        return ServicioEnum.getInstance().getListEnum(enumName);
    }

    @Override
    public DtMensaje crearOpenSGSManagedBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isCreable(dtOpenSGSManagedBean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            OpenSGSManagedBean openSGSManagedBean = ServicioOpenSGSBean.getInstance().getNewOpenSGSManagedBean(dtOpenSGSManagedBean);
            if (openSGSManagedBean == null) {
                return ServicioMensaje.getInstance().getMensajeERROR(dtOpenSGSManagedBean.getClassName() + ".create.error");
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Crear, openSGSManagedBean, dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarCrear(openSGSManagedBean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(openSGSManagedBean);

            ServicioActividad.getInstance().crearActividad(Operacion.Crear, openSGSManagedBean, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Crear, openSGSManagedBean, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtOpenSGSManagedBean.getClassName() + ".create.success", openSGSManagedBean.getId());
        } catch (Exception ex) {
            Logger.getLogger(ControladorOpenSGSBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServicioMensaje.getInstance().getMensajeERROR(dtOpenSGSManagedBean.getClassName() + ".create.error");
    }

    @Override
    public DtMensaje modificarOpenSGSManagedBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isModificable(dtOpenSGSManagedBean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Modificar, dtOpenSGSManagedBean.getClassName(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            OpenSGSManagedBean bean = (OpenSGSManagedBean) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtOpenSGSManagedBean);
            if (bean == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.notexists.error");
            }

            bean.modificar(dtOpenSGSManagedBean);

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarModificar(bean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(bean);

            ServicioActividad.getInstance().crearActividad(Operacion.Modificar, bean, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Modificar, bean, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtOpenSGSManagedBean.getClassName() + ".update.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorOpenSGSBean.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR(dtOpenSGSManagedBean.getClassName() + ".update.error");
        }
    }

    @Override
    public DtMensaje modificarOpenSGSManagedBeanAplicacion(DtOpenSGSManagedBean dtOpenSGSManagedBean, Long aplicacionId, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isModificable(dtOpenSGSManagedBean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }
            
            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Modificar, dtOpenSGSManagedBean.getClassName(), dtSesion);
            if (!dtMensaje.isExito()) {
                dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Modificar, dtOpenSGSManagedBean.getClassName(), aplicacionId, dtSesion);
                if (!dtMensaje.isExito()) {
                    return dtMensaje;
                }
            }

            OpenSGSManagedBean bean = (OpenSGSManagedBean) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtOpenSGSManagedBean);
            if (bean == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.notexists.error");
            }

            bean.modificar(dtOpenSGSManagedBean);

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarModificar(bean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(bean);

            ServicioActividad.getInstance().crearActividad(Operacion.Modificar, bean, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Modificar, bean, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtOpenSGSManagedBean.getClassName() + ".update.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorOpenSGSBean.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR(dtOpenSGSManagedBean.getClassName() + ".update.error");
        }
    }

    @Override
    public DtMensaje borrarOpenSGSManagedBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isDeletable(dtOpenSGSManagedBean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Borrar, dtOpenSGSManagedBean.getClassName(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            OpenSGSManagedBean bean = (OpenSGSManagedBean) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtOpenSGSManagedBean);
            if (bean == null) {
                return ServicioMensaje.getInstance().getMensajeERROR(dtOpenSGSManagedBean.getClassName() + ".delete.error");
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarBorrar(bean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            bean.borrar();

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(bean);

            ServicioActividad.getInstance().crearActividad(Operacion.Borrar, bean, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Borrar, bean, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtOpenSGSManagedBean.getClassName() + ".delete.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorOpenSGSBean.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR(dtOpenSGSManagedBean.getClassName() + ".delete.error");
        }
    }

    @Override
    public DtMensaje borrarOpenSGSManagedBeanAplicacion(DtOpenSGSManagedBean dtOpenSGSManagedBean, Long aplicacionId, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isDeletable(dtOpenSGSManagedBean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Borrar, dtOpenSGSManagedBean.getClassName(), dtSesion);
            if (!dtMensaje.isExito()) {
                dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Borrar, dtOpenSGSManagedBean.getClassName(), aplicacionId, dtSesion);
                if (!dtMensaje.isExito()) {
                    return dtMensaje;
                }
            }

            OpenSGSManagedBean bean = (OpenSGSManagedBean) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtOpenSGSManagedBean);
            if (bean == null) {
                return ServicioMensaje.getInstance().getMensajeERROR(dtOpenSGSManagedBean.getClassName() + ".delete.error");
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarBorrar(bean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            bean.borrar();

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(bean);

            ServicioActividad.getInstance().crearActividad(Operacion.Borrar, bean, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Borrar, bean, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtOpenSGSManagedBean.getClassName() + ".delete.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorOpenSGSBean.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR(dtOpenSGSManagedBean.getClassName() + ".delete.error");
        }
    }

    @Override
    public DtMensaje restaurarOpenSGSManagedBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isRestorable(dtOpenSGSManagedBean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Restaurar, dtOpenSGSManagedBean.getClassName(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            OpenSGSManagedBean bean = (OpenSGSManagedBean) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtOpenSGSManagedBean);
            if (bean == null) {
                return ServicioMensaje.getInstance().getMensajeERROR(dtOpenSGSManagedBean.getClassName() + ".restore.error");
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarRestaurar(bean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            bean.restaurar();

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(bean);

            ServicioActividad.getInstance().crearActividad(Operacion.Restaurar, bean, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Restaurar, bean, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtOpenSGSManagedBean.getClassName() + ".restore.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorOpenSGSBean.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR(dtOpenSGSManagedBean.getClassName() + ".restore.error");
        }
    }

    @Override
    public DtMensaje restaurarOpenSGSManagedBeanAplicacion(DtOpenSGSManagedBean dtOpenSGSManagedBean, Long aplicacionId, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isRestorable(dtOpenSGSManagedBean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Restaurar, dtOpenSGSManagedBean.getClassName(), dtSesion);
            if (!dtMensaje.isExito()) {
                dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Restaurar, dtOpenSGSManagedBean.getClassName(), aplicacionId, dtSesion);
                if (!dtMensaje.isExito()) {
                    return dtMensaje;
                }
            }

            OpenSGSManagedBean bean = (OpenSGSManagedBean) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtOpenSGSManagedBean);
            if (bean == null) {
                return ServicioMensaje.getInstance().getMensajeERROR(dtOpenSGSManagedBean.getClassName() + ".restore.error");
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarRestaurar(bean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            bean.restaurar();

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(bean);

            ServicioActividad.getInstance().crearActividad(Operacion.Restaurar, bean, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Restaurar, bean, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtOpenSGSManagedBean.getClassName() + ".restore.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorOpenSGSBean.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR(dtOpenSGSManagedBean.getClassName() + ".restore.error");
        }
    }

    @Override
    public DtMensaje desactivarOpenSGSManagedBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isDeactivatable(dtOpenSGSManagedBean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Desactivar, dtOpenSGSManagedBean.getClassName(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            OpenSGSManagedBean bean = (OpenSGSManagedBean) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtOpenSGSManagedBean);
            if (bean == null) {
                return ServicioMensaje.getInstance().getMensajeERROR(dtOpenSGSManagedBean.getClassName() + ".disable.error");
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarDesactivar(bean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            bean.desactivar();

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(bean);

            ServicioActividad.getInstance().crearActividad(Operacion.Desactivar, bean, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Desactivar, bean, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtOpenSGSManagedBean.getClassName() + ".disable.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorOpenSGSBean.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR(dtOpenSGSManagedBean.getClassName() + ".disable.error");
        }
    }

    @Override
    public DtMensaje desactivarOpenSGSManagedBeanAplicacion(DtOpenSGSManagedBean dtOpenSGSManagedBean, Long aplicacionId, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isDeactivatable(dtOpenSGSManagedBean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Desactivar, dtOpenSGSManagedBean.getClassName(), dtSesion);
            if (!dtMensaje.isExito()) {
                dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Desactivar, dtOpenSGSManagedBean.getClassName(), aplicacionId, dtSesion);
                if (!dtMensaje.isExito()) {
                    return dtMensaje;
                }
            }

            OpenSGSManagedBean bean = (OpenSGSManagedBean) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtOpenSGSManagedBean);
            if (bean == null) {
                return ServicioMensaje.getInstance().getMensajeERROR(dtOpenSGSManagedBean.getClassName() + ".disable.error");
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarDesactivar(bean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            bean.desactivar();

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(bean);

            ServicioActividad.getInstance().crearActividad(Operacion.Desactivar, bean, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Desactivar, bean, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtOpenSGSManagedBean.getClassName() + ".disable.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorOpenSGSBean.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR(dtOpenSGSManagedBean.getClassName() + ".disable.error");
        }
    }

    @Override
    public DtMensaje activarOpenSGSManagedBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isActivable(dtOpenSGSManagedBean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Activar, dtOpenSGSManagedBean.getClassName(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            OpenSGSManagedBean bean = (OpenSGSManagedBean) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtOpenSGSManagedBean);
            if (bean == null) {
                return ServicioMensaje.getInstance().getMensajeERROR(dtOpenSGSManagedBean.getClassName() + ".enable.error");
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarActivar(bean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            bean.activar();

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(bean);

            ServicioActividad.getInstance().crearActividad(Operacion.Activar, bean, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Activar, bean, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtOpenSGSManagedBean.getClassName() + ".enable.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorOpenSGSBean.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR(dtOpenSGSManagedBean.getClassName() + ".enable.error");
        }
    }

    @Override
    public DtMensaje activarOpenSGSManagedBeanAplicacion(DtOpenSGSManagedBean dtOpenSGSManagedBean, Long aplicacionId, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isActivable(dtOpenSGSManagedBean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Activar, dtOpenSGSManagedBean.getClassName(), dtSesion);
            if (!dtMensaje.isExito()) {
                dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Activar, dtOpenSGSManagedBean.getClassName(), aplicacionId, dtSesion);
                if (!dtMensaje.isExito()) {
                    return dtMensaje;
                }
            }

            OpenSGSManagedBean bean = (OpenSGSManagedBean) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtOpenSGSManagedBean);
            if (bean == null) {
                return ServicioMensaje.getInstance().getMensajeERROR(dtOpenSGSManagedBean.getClassName() + ".enable.error");
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarActivar(bean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            bean.activar();

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(bean);

            ServicioActividad.getInstance().crearActividad(Operacion.Activar, bean, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Activar, bean, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtOpenSGSManagedBean.getClassName() + ".enable.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorOpenSGSBean.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR(dtOpenSGSManagedBean.getClassName() + ".enable.error");
        }
    }

    @Override
    public DtMensaje agregarOpenSGSBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtOpenSGSBean elemento, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isModificable(dtOpenSGSManagedBean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Modificar, dtOpenSGSManagedBean.getClassName(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            OpenSGSManagedBean bean = (OpenSGSManagedBean) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtOpenSGSManagedBean);
            if (bean == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.notexists.error");
            }

            OpenSGSBean beanElemento = ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(elemento);
            if (beanElemento == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.notexists.error");
            }

            if (!bean.agregarListOpenSGSBean(beanElemento)) {
                return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.add.error");
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarModificar(bean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(bean);

            ServicioActividad.getInstance().crearActividad(Operacion.Agregar, bean, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Agregar, bean, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK("OpenSGSBean.add.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorOpenSGSBean.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.add.success");
        }
    }

    @Override
    public DtMensaje quitarOpenSGSBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtOpenSGSBean elemento, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isModificable(dtOpenSGSManagedBean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Modificar, dtOpenSGSManagedBean.getClassName(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            OpenSGSManagedBean bean = (OpenSGSManagedBean) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtOpenSGSManagedBean);
            if (bean == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.notexists.error");
            }

            OpenSGSBean beanElemento = ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(elemento);
            if (beanElemento == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.notexists.error");
            }

            if (!bean.quitarListOpenSGSBean(beanElemento)) {
                return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.remove.error");
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarModificar(bean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(bean);

            ServicioActividad.getInstance().crearActividad(Operacion.Quitar, bean, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Quitar, bean, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK("OpenSGSBean.remove.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorOpenSGSBean.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.remove.error");
        }
    }

}

//            OpenSGSBean openSGSBean = (OpenSGSBean) Class.forName(dtOpenSGSBean.getClassName()).newInstance();
//            Class[] argumentos = new Class[1];
//            argumentos[0] = dtOpenSGSBean.getClass();
//            dtOpenSGSBean.getClass().
//            OpenSGSBean openSGSBean = (OpenSGSBean) Class.forName(dtOpenSGSBean.getClassName()).getDeclaredConstructor(argumentos).newInstance(dtOpenSGSBean);
//            OpenSGSBean openSGSBean = (OpenSGSBean) OpenSGSBean.class.getDeclaredConstructor(argumentos).newInstance(dtOpenSGSBean);
