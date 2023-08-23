package opensgs.interfaces;

import opensgs.datatypes.DtMensaje;
import java.util.List;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.usuarios.datatypes.DtSesion;

public interface IControladorOpenSGSBean {

    public DtOpenSGSBean obtenerOpenSGSBean(String className, Long id, DtSesion dtSesion);

    public DtOpenSGSBean obtenerOpenSGSBean(String className, String paramatro, String valor, DtSesion dtSesion);

    public DtOpenSGSBean obtenerOpenSGSBeanAplicacion(String className, Long id, Long aplicacionId, DtSesion dtSesion);

//    public DtOpenSGSBean obtenerOpenSGSBeanAplicacion(String className, String paramatro, String valor, Long aplicacionId, DtSesion dtSesion);
    public List<DtOpenSGSBean> listarOpenSGSBean(String className, DtSesion dtSesion);

    public List<DtOpenSGSManagedBean> listarOpenSGSManagedBean(String className, boolean borrado, DtSesion dtSesion);

//    public List<DtOpenSGSManagedBean> listarOpenSGSManagedBeanAplicacion(String className, boolean borrado, Long aplicacionId, DtSesion dtSesion);
    public List<DtOpenSGSManagedBean> listarOpenSGSManagedBean(String className, String paramatro, String valor, boolean borrado, DtSesion dtSesion);

    public List<DtOpenSGSManagedBean> listarOpenSGSManagedBean(String className, String paramatro, Long valor, boolean borrado, DtSesion dtSesion);

    public List<DtOpenSGSManagedBean> listarOpenSGSManagedBeanActivos(String className, DtSesion dtSesion);

    public List<DtOpenSGSManagedBean> listarOpenSGSManagedBeanActivos(String className, String paramatro, String valor, DtSesion dtSesion);

    public List<DtOpenSGSManagedBean> listarOpenSGSManagedBeanActivos(String className, String paramatro, Long valor, DtSesion dtSesion);

    public List<String> listarEnum(String enumName, DtSesion dtSesion);

    public DtMensaje crearOpenSGSManagedBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtSesion dtSesion);

    public DtMensaje modificarOpenSGSManagedBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtSesion dtSesion);

    public DtMensaje modificarOpenSGSManagedBeanAplicacion(DtOpenSGSManagedBean dtOpenSGSManagedBean, Long aplicacionId, DtSesion dtSesion);

    public DtMensaje borrarOpenSGSManagedBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtSesion dtSesion);

    public DtMensaje borrarOpenSGSManagedBeanAplicacion(DtOpenSGSManagedBean dtOpenSGSManagedBean, Long aplicacionId, DtSesion dtSesion);

    public DtMensaje restaurarOpenSGSManagedBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtSesion dtSesion);

    public DtMensaje restaurarOpenSGSManagedBeanAplicacion(DtOpenSGSManagedBean dtOpenSGSManagedBean, Long aplicacionId, DtSesion dtSesion);

    public DtMensaje desactivarOpenSGSManagedBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtSesion dtSesion);

    public DtMensaje desactivarOpenSGSManagedBeanAplicacion(DtOpenSGSManagedBean dtOpenSGSManagedBean, Long aplicacionId, DtSesion dtSesion);

    public DtMensaje activarOpenSGSManagedBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtSesion dtSesion);

    public DtMensaje activarOpenSGSManagedBeanAplicacion(DtOpenSGSManagedBean dtOpenSGSManagedBean, Long aplicacionId, DtSesion dtSesion);

    public DtMensaje agregarOpenSGSBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtOpenSGSBean elemento, DtSesion dtSesion);

    public DtMensaje quitarOpenSGSBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtOpenSGSBean elemento, DtSesion dtSesion);

}
