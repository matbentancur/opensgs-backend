package opensgs.usuarios.logica.servicios;

import java.util.logging.Level;
import java.util.logging.Logger;
import opensgs.datatypes.DtMensaje;
import opensgs.enums.Elemento;
import opensgs.enums.Operacion;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.logica.servicios.ServicioOpenSGSBean;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.sistema.persistencia.manejadores.ManejadorAplicacion;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.logica.beans.PerfilAplicacion;
import opensgs.usuarios.logica.beans.Permiso;
import opensgs.usuarios.logica.beans.Usuario;
import opensgs.usuarios.persistencia.manejadores.ManejadorPermiso;
import opensgs.usuarios.persistencia.manejadores.ManejadorUsuario;

public class ServicioPermiso {

    private static ServicioPermiso instance = null;

    private ServicioPermiso() {
    }

    public static ServicioPermiso getInstance() {
        if (instance == null) {
            instance = new ServicioPermiso();
        }
        return instance;
    }

    //TODO
    public DtMensaje verificarPermiso(Operacion operacion, OpenSGSBean openSGSBean, DtSesion dtSesion) {
        try {
            Usuario usuario = ManejadorUsuario.getInstance().obtenerUsuario(dtSesion);
            if (usuario == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("Permiso.user.exists.error");
            }

            Permiso permiso = ManejadorPermiso.getInstance().obtenerPermiso(openSGSBean.getOpenSGSElemento(), operacion);
            Permiso permisoTotal = ManejadorPermiso.getInstance().obtenerPermiso(openSGSBean.getOpenSGSElemento(), Operacion.Todas);

            if (usuario.getPerfil().buscarPermiso(permisoTotal)) {
                return ServicioMensaje.getInstance().getMensajeOK();
            }

            if (usuario.getPerfil().buscarPermiso(permiso)) {
                return ServicioMensaje.getInstance().getMensajeOK();
            }

            if (this.verificacionEspecialPermisos(permisoTotal, usuario)) {
                return ServicioMensaje.getInstance().getMensajeOK();
            }

            if (this.verificacionEspecialPermisos(permiso, usuario)) {
                return ServicioMensaje.getInstance().getMensajeOK();
            }

            return ServicioMensaje.getInstance().getMensajeERROR("Permiso.denied.error");
        } catch (Exception ex) {
            Logger.getLogger(ServicioPermiso.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("Permiso.unexpected.error");
        }
    }

    public DtMensaje verificarPermiso(Operacion operacion, String className, DtSesion dtSesion) {
        OpenSGSBean openSGSBean = ServicioOpenSGSBean.getInstance().parseClassName(className);
        return this.verificarPermiso(operacion, openSGSBean, dtSesion);
    }

    public DtMensaje verificarPermisoAplicacion(Operacion operacion, OpenSGSBean openSGSBean, Long aplicacionId, DtSesion dtSesion) {
        try {
            
            Aplicacion aplicacion = ManejadorAplicacion.getInstance().obtenerAplicacion(aplicacionId);
            if (aplicacion == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("Permiso.application.exists.error");
            }
            
            Usuario usuario = ManejadorUsuario.getInstance().obtenerUsuario(dtSesion);
            if (usuario == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("Permiso.user.exists.error");
            }

            Permiso permiso = ManejadorPermiso.getInstance().obtenerPermiso(openSGSBean.getOpenSGSElemento(), operacion);
            Permiso permisoTotal = ManejadorPermiso.getInstance().obtenerPermiso(openSGSBean.getOpenSGSElemento(), Operacion.Todas);

//            boolean permisoGlobal = false;
//
//            if (usuario.getPerfil().buscarPermiso(permisoTotal)) {
//                permisoGlobal = true;
//
//            } else {
//                if (usuario.getPerfil().buscarPermiso(permiso)) {
//                    permisoGlobal = true;
//                }
//            }

            boolean permisoAplicacion = false;

            PerfilAplicacion perfilAplicacion = usuario.obtenerPerfilAplicacion(aplicacion);
            if (perfilAplicacion != null) {
                if (perfilAplicacion.getPerfil().buscarPermiso(permisoTotal)) {
                    permisoAplicacion = true;
                } else {
                    if (perfilAplicacion.getPerfil().buscarPermiso(permiso)) {
                        permisoAplicacion = true;
                    }
                }
            }
//
//            if (!permisoGlobal && !permisoAplicacion) {
//                return ServicioMensaje.getInstance().getMensajeERROR("Permiso.denied.error");
//            }

            if (!permisoAplicacion) {
                return ServicioMensaje.getInstance().getMensajeERROR("Permiso.denied.error");
            }

            return ServicioMensaje.getInstance().getMensajeOK();
        } catch (Exception ex) {
            Logger.getLogger(ServicioPermiso.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("Permiso.unexpected.error");
        }
    }

    public DtMensaje verificarPermisoAplicacion(Operacion operacion, String className, Long aplicacionId, DtSesion dtSesion) {
        OpenSGSBean openSGSBean = ServicioOpenSGSBean.getInstance().parseClassName(className);
        return this.verificarPermisoAplicacion(operacion, openSGSBean, aplicacionId, dtSesion);
    }

    public DtMensaje verificarPermisoAplicacion(Operacion operacion, OpenSGSBean openSGSBean, Aplicacion aplicacion, DtSesion dtSesion) {
        return this.verificarPermisoAplicacion(operacion, openSGSBean, aplicacion.getId(), dtSesion);
    }

    private boolean verificacionEspecialPermisos(Permiso permiso, Usuario usuario) {
        if (permiso.getElemento().equals(Elemento.Aplicacion) && permiso.getOperacion().equals(Operacion.Ver)) {
            if (usuario.buscarPermisoEnPerfilAplicacion(permiso)) {
                return true;
            }
        }
        return false;
    }

    public void crearPermisoOpenSGSBean(Elemento elemento) throws Exception {
        Permiso permisoVer = ManejadorPermiso.getInstance().obtenerPermiso(elemento, Operacion.Ver);
        if (permisoVer == null) {
            ManejadorPermiso.getInstance().crearPermiso(elemento, Operacion.Ver);
        }

        Permiso permisoTodas = ManejadorPermiso.getInstance().obtenerPermiso(elemento, Operacion.Todas);
        if (permisoTodas == null) {
            ManejadorPermiso.getInstance().crearPermiso(elemento, Operacion.Todas);
        }
    }

    public void crearPermisoOpenSGSManagedBean(Elemento elemento) throws Exception {
        Permiso permisoCrear = ManejadorPermiso.getInstance().obtenerPermiso(elemento, Operacion.Crear);
        if (permisoCrear == null) {
            ManejadorPermiso.getInstance().crearPermiso(elemento, Operacion.Crear);
        }

        Permiso permisoModificar = ManejadorPermiso.getInstance().obtenerPermiso(elemento, Operacion.Modificar);
        if (permisoModificar == null) {
            ManejadorPermiso.getInstance().crearPermiso(elemento, Operacion.Modificar);
        }

        Permiso permisoVer = ManejadorPermiso.getInstance().obtenerPermiso(elemento, Operacion.Ver);
        if (permisoVer == null) {
            ManejadorPermiso.getInstance().crearPermiso(elemento, Operacion.Ver);
        }

        Permiso permisoBorrar = ManejadorPermiso.getInstance().obtenerPermiso(elemento, Operacion.Borrar);
        if (permisoBorrar == null) {
            ManejadorPermiso.getInstance().crearPermiso(elemento, Operacion.Borrar);
        }

        Permiso permisoRestaurar = ManejadorPermiso.getInstance().obtenerPermiso(elemento, Operacion.Restaurar);
        if (permisoRestaurar == null) {
            ManejadorPermiso.getInstance().crearPermiso(elemento, Operacion.Restaurar);
        }

        Permiso permisoActivar = ManejadorPermiso.getInstance().obtenerPermiso(elemento, Operacion.Activar);
        if (permisoActivar == null) {
            ManejadorPermiso.getInstance().crearPermiso(elemento, Operacion.Activar);
        }

        Permiso permisoDesactivar = ManejadorPermiso.getInstance().obtenerPermiso(elemento, Operacion.Desactivar);
        if (permisoDesactivar == null) {
            ManejadorPermiso.getInstance().crearPermiso(elemento, Operacion.Desactivar);
        }

        Permiso permisoTodas = ManejadorPermiso.getInstance().obtenerPermiso(elemento, Operacion.Todas);
        if (permisoTodas == null) {
            ManejadorPermiso.getInstance().crearPermiso(elemento, Operacion.Todas);
        }
    }

}
