package opensgs.logica.servicios;

import java.util.ArrayList;
import java.util.List;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.sistema.logica.beans.Actividad;
import opensgs.sistema.logica.beans.Anuncio;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.sistema.logica.beans.Archivo;
import opensgs.sistema.logica.beans.Constancia;
import opensgs.sistema.logica.beans.ConstanciaElementoImagen;
import opensgs.sistema.logica.beans.ConstanciaElementoTexto;
import opensgs.sistema.logica.beans.Destinatario;
import opensgs.sistema.logica.beans.Notificacion;
import opensgs.sistema.logica.beans.PaginaAplicacion;
import opensgs.sistema.logica.beans.PlantillaAplicacion;
import opensgs.sistema.logica.beans.PlantillaCorreo;
import opensgs.sistema.logica.beans.PreguntaFrecuente;
import opensgs.sistema.logica.beans.Reporte;
import opensgs.sistema.logica.beans.ServidorAplicaciones;
import opensgs.sistema.logica.beans.ServidorAutenticacion;
import opensgs.sistema.logica.beans.ServidorCorreo;
import opensgs.sistema.logica.beans.Sistema;
import opensgs.solicitudes.logica.beans.Solicitud;
import opensgs.usuarios.logica.beans.Perfil;
import opensgs.usuarios.logica.beans.PerfilAplicacion;
import opensgs.usuarios.logica.beans.Permiso;
import opensgs.usuarios.logica.beans.Sesion;
import opensgs.usuarios.logica.beans.Usuario;

public class ServicioOpenSGSBean {

    private static ServicioOpenSGSBean instance = null;

    private ServicioOpenSGSBean() {
    }

    public static ServicioOpenSGSBean getInstance() {
        if (instance == null) {
            instance = new ServicioOpenSGSBean();
        }
        return instance;
    }
    
    public boolean estaBorrado(OpenSGSManagedBean openSGSManagedBean) {
        return !openSGSManagedBean.isBorrado();
    }

    public boolean estaActivo(OpenSGSManagedBean openSGSManagedBean) {
        return openSGSManagedBean.isActivo();
    }

    public OpenSGSBean parseClassName(String className) {
        if (className.equals("Actividad")) {
            return new Actividad();
        } else if (className.equals("Anuncio")) {
            return new Anuncio();
        } else if (className.equals("Aplicacion")) {
            return new Aplicacion();
        } else if (className.equals("Archivo")) {
            return new Archivo();
        } else if (className.equals("Constancia")) {
            return new Constancia();
        } else if (className.equals("ConstanciaElementoImagen")) {
            return new ConstanciaElementoImagen();
        } else if (className.equals("ConstanciaElementoTexto")) {
            return new ConstanciaElementoTexto();
        } else if (className.equals("Destinatario")) {
            return new Destinatario();
        } else if (className.equals("Notificacion")) {
            return new Notificacion();
        } else if (className.equals("PaginaAplicacion")) {
            return new PaginaAplicacion();
        } else if (className.equals("PerfilAplicacion")) {
            return new PerfilAplicacion();
        } else if (className.equals("Perfil")) {
            return new Perfil();
        } else if (className.equals("Permiso")) {
            return new Permiso();
        } else if (className.equals("PlantillaAplicacion")) {
            return new PlantillaAplicacion();
        } else if (className.equals("PlantillaCorreo")) {
            return new PlantillaCorreo();
        } else if (className.equals("PreguntaFrecuente")) {
            return new PreguntaFrecuente();
        } else if (className.equals("Reporte")) {
            return new Reporte();
        } else if (className.equals("ServidorAplicaciones")) {
            return new ServidorAplicaciones();
        } else if (className.equals("ServidorAutenticacion")) {
            return new ServidorAutenticacion();
        } else if (className.equals("ServidorCorreo")) {
            return new ServidorCorreo();
        } else if (className.equals("Sesion")) {
            return new Sesion();
        } else if (className.equals("Sistema")) {
            return new Sistema();
        } else if (className.equals("Solicitud")) {
            return new Solicitud();
        } else if (className.equals("Usuario")) {
            return new Usuario();
        }
        return null;
    }

    public OpenSGSManagedBean getNewOpenSGSManagedBean(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        if (dtOpenSGSManagedBean.getClassName().equals("Anuncio")) {
            return new Anuncio(dtOpenSGSManagedBean);
        } else if (dtOpenSGSManagedBean.getClassName().equals("Aplicacion")) {
            return new Aplicacion(dtOpenSGSManagedBean);
        } else if (dtOpenSGSManagedBean.getClassName().equals("Archivo")) {
            return new Archivo(dtOpenSGSManagedBean);
        } else if (dtOpenSGSManagedBean.getClassName().equals("Constancia")) {
            return new Constancia(dtOpenSGSManagedBean);
        } else if (dtOpenSGSManagedBean.getClassName().equals("ConstanciaElementoImagen")) {
            return new ConstanciaElementoImagen(dtOpenSGSManagedBean);
        } else if (dtOpenSGSManagedBean.getClassName().equals("ConstanciaElementoTexto")) {
            return new ConstanciaElementoTexto(dtOpenSGSManagedBean);
        } else if (dtOpenSGSManagedBean.getClassName().equals("Notificacion")) {
            return new Notificacion(dtOpenSGSManagedBean);
        } else if (dtOpenSGSManagedBean.getClassName().equals("PaginaAplicacion")) {
            return new PaginaAplicacion(dtOpenSGSManagedBean);
        } else if (dtOpenSGSManagedBean.getClassName().equals("Perfil")) {
            return new Perfil(dtOpenSGSManagedBean);
        } else if (dtOpenSGSManagedBean.getClassName().equals("PlantillaAplicacion")) {
            return new PlantillaAplicacion(dtOpenSGSManagedBean);
        } else if (dtOpenSGSManagedBean.getClassName().equals("PlantillaCorreo")) {
            return new PlantillaCorreo(dtOpenSGSManagedBean);
        } else if (dtOpenSGSManagedBean.getClassName().equals("PreguntaFrecuente")) {
            return new PreguntaFrecuente(dtOpenSGSManagedBean);
        } else if (dtOpenSGSManagedBean.getClassName().equals("Reporte")) {
            return new Reporte(dtOpenSGSManagedBean);
        } else if (dtOpenSGSManagedBean.getClassName().equals("ServidorAplicaciones")) {
            return new ServidorAplicaciones(dtOpenSGSManagedBean);
        } else if (dtOpenSGSManagedBean.getClassName().equals("ServidorAutenticacion")) {
            return new ServidorAutenticacion(dtOpenSGSManagedBean);
        } else if (dtOpenSGSManagedBean.getClassName().equals("ServidorCorreo")) {
            return new ServidorCorreo(dtOpenSGSManagedBean);
        } else if (dtOpenSGSManagedBean.getClassName().equals("Solicitud")) {
            return new Solicitud(dtOpenSGSManagedBean);
        } else if (dtOpenSGSManagedBean.getClassName().equals("Usuario")) {
            return new Usuario(dtOpenSGSManagedBean);
        }
        return null;
    }

    public OpenSGSBean parseDtOpenSGSBean(DtOpenSGSBean dtOpenSGSBean) {
        return this.parseClassName(dtOpenSGSBean.getClassName());
    }

    public List<? extends DtOpenSGSBean> listOpenSGSBeanToListDtOpenSGSBean(List<? extends OpenSGSBean> listaOpenSGSBean) {

        List<DtOpenSGSBean> listaDtOpenSGSBeans = new ArrayList<>();
        if (listaOpenSGSBean != null) {
            if (!listaOpenSGSBean.isEmpty()) {
                for (OpenSGSBean openSGSBean : listaOpenSGSBean) {
                    listaDtOpenSGSBeans.add(openSGSBean.getDtOpenSGSBean());
                }
            }
        }

        return listaDtOpenSGSBeans;
    }

}
