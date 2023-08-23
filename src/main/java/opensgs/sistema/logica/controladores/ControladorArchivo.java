package opensgs.sistema.logica.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import opensgs.datatypes.DtMensaje;
import opensgs.enums.Operacion;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.logica.servicios.ServicioOpenSGSBean;
import opensgs.logica.validators.ValidatorOpenSGSBean;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.datatypes.DtArchivo;
import opensgs.sistema.enums.AlcanceArchivo;
import opensgs.sistema.interfaces.IControladorArchivo;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.sistema.logica.beans.Archivo;
import opensgs.sistema.logica.servicios.ServicioActividad;
import opensgs.sistema.logica.servicios.ServicioArchivo;
import opensgs.sistema.logica.servicios.ServicioNotificacion;
import opensgs.sistema.logica.validators.ValidatorArchivo;
import opensgs.sistema.persistencia.manejadores.ManejadorAplicacion;
import opensgs.sistema.persistencia.manejadores.ManejadorArchivo;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.logica.servicios.ServicioPermiso;
import opensgs.usuarios.logica.servicios.ServicioSesion;

public class ControladorArchivo implements IControladorArchivo {

    @Override
    public DtMensaje crearArchivo(DataHandler archivoSubir, DtArchivo dtArchivo, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isCreable(dtArchivo);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Crear, new Archivo(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioArchivo.getInstance().subirArchivoTemporal(archivoSubir, dtArchivo.getNombre());
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            Archivo archivo = new Archivo(dtArchivo);
            archivo = ServicioArchivo.getInstance().obtenerMetadatos(archivo.getNombre(), archivo);
            if (archivo == null) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtArchivo.getNombre());
                return ServicioMensaje.getInstance().getMensajeERROR("Archivo.create.error");
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarCrear(archivo);
            if (!dtMensaje.isExito()) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtArchivo.getNombre());
                return dtMensaje;
            }

            dtMensaje = ServicioArchivo.getInstance().moverArchivoTemporal(archivo.getNombre());
            if (!dtMensaje.isExito()) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtArchivo.getNombre());
                return dtMensaje;
            }

            ManejadorArchivo.getInstance().crearArchivo(archivo);

            archivo = ManejadorArchivo.getInstance().obtenerArchivo(dtArchivo.getNombre());

            ServicioActividad.getInstance().crearActividad(Operacion.Crear, archivo, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Crear, archivo, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK("Archivo.create.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServicioMensaje.getInstance().getMensajeERROR("Archivo.create.error");
    }

    @Override
    public DtMensaje crearArchivoAplicacion(DataHandler archivoSubir, DtArchivo dtArchivo, Long aplicacionId, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isCreable(dtArchivo);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Crear, new Archivo(), dtSesion);
            if (!dtMensaje.isExito()) {
                dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Crear, new Archivo(), aplicacionId, dtSesion);
                if (!dtMensaje.isExito()) {
                    return dtMensaje;
                }
            }

            dtMensaje = ServicioArchivo.getInstance().subirArchivoTemporal(archivoSubir, dtArchivo.getNombre());
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            Archivo archivo = new Archivo(dtArchivo);
            archivo = ServicioArchivo.getInstance().obtenerMetadatos(archivo.getNombre(), archivo);
            if (archivo == null) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtArchivo.getNombre());
                return ServicioMensaje.getInstance().getMensajeERROR(dtArchivo.getClassName() + ".create.error");
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarCrear(archivo);
            if (!dtMensaje.isExito()) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtArchivo.getNombre());
                return dtMensaje;
            }

            dtMensaje = ServicioArchivo.getInstance().moverArchivoTemporal(archivo.getNombre());
            if (!dtMensaje.isExito()) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtArchivo.getNombre());
                return dtMensaje;
            }

            archivo.setAlcanceArchivo(AlcanceArchivo.APLICACION);

            ManejadorArchivo.getInstance().crearArchivo(archivo);

            archivo = ManejadorArchivo.getInstance().obtenerArchivo(dtArchivo.getNombre());

            ServicioActividad.getInstance().crearActividad(Operacion.Crear, archivo, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Crear, archivo, dtSesion);

            Aplicacion aplicacion = ManejadorAplicacion.getInstance().obtenerAplicacion(aplicacionId);
            if (aplicacion == null) {
                return ServicioMensaje.getInstance().getMensajeERROR(dtArchivo.getClassName() + ".create.error");
            }

            aplicacion.agregarListOpenSGSBean(archivo);

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(aplicacion);

            ServicioActividad.getInstance().crearActividad(Operacion.Agregar, aplicacion, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Agregar, aplicacion, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtArchivo.getClassName() + ".create.success", archivo.getId());
        } catch (Exception ex) {
            Logger.getLogger(ControladorAnuncio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServicioMensaje.getInstance().getMensajeERROR(dtArchivo.getClassName() + ".create.error");
    }

    @Override
    public DtMensaje modificarArchivo(DataHandler archivoSubir, DtArchivo dtArchivo, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isModificable(dtArchivo);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Modificar, new Archivo(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioArchivo.getInstance().subirArchivoTemporal(archivoSubir, dtArchivo.getNombre());
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            Archivo archivo = ManejadorArchivo.getInstance().obtenerArchivo(dtArchivo);
            if (archivo == null) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtArchivo.getNombre());
                return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.notexists.error");
            }

            String nombreAnterior = archivo.getNombre();

            archivo.modificar(dtArchivo);

            archivo = ServicioArchivo.getInstance().obtenerMetadatos(archivo.getNombre(), archivo);
            if (archivo == null) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtArchivo.getNombre());
                return ServicioMensaje.getInstance().getMensajeERROR(dtArchivo.getClassName() + ".update.error");
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarModificar(archivo);
            if (!dtMensaje.isExito()) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtArchivo.getNombre());
                return dtMensaje;
            }

            dtMensaje = ServicioArchivo.getInstance().moverArchivoTemporal(archivo.getNombre());
            if (!dtMensaje.isExito()) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtArchivo.getNombre());
                return dtMensaje;
            }

            if (!nombreAnterior.equals(archivo.getNombre())) {
                ServicioArchivo.getInstance().borrarArchivo(archivo.getId());
            }

            ManejadorArchivo.getInstance().modificarArchivo(archivo);

            ServicioActividad.getInstance().crearActividad(Operacion.Modificar, archivo, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Modificar, archivo, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtArchivo.getClassName() + ".update.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorArchivo.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR(dtArchivo.getClassName() + ".update.error");
        }
    }

    @Override
    public DtMensaje modificarArchivoAplicacion(DataHandler archivoSubir, DtArchivo dtArchivo, Long aplicacionId, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isModificable(dtArchivo);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Modificar, new Archivo(), dtSesion);
            if (!dtMensaje.isExito()) {
                dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Modificar, new Archivo(), aplicacionId, dtSesion);
                if (!dtMensaje.isExito()) {
                    return dtMensaje;
                }
            }

            dtMensaje = ServicioArchivo.getInstance().subirArchivoTemporal(archivoSubir, dtArchivo.getNombre());
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            Archivo archivo = ManejadorArchivo.getInstance().obtenerArchivo(dtArchivo);
            if (archivo == null) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtArchivo.getNombre());
                return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.notexists.error");
            }

            String nombreAnterior = archivo.getNombre();

            archivo.modificar(dtArchivo);

            archivo = ServicioArchivo.getInstance().obtenerMetadatos(archivo.getNombre(), archivo);
            if (archivo == null) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtArchivo.getNombre());
                return ServicioMensaje.getInstance().getMensajeERROR("Archivo.create.error");
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarModificar(archivo);
            if (!dtMensaje.isExito()) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtArchivo.getNombre());
                return dtMensaje;
            }

            dtMensaje = ServicioArchivo.getInstance().moverArchivoTemporal(archivo.getNombre());
            if (!dtMensaje.isExito()) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtArchivo.getNombre());
                return dtMensaje;
            }

            if (!nombreAnterior.equals(archivo.getNombre())) {
                ServicioArchivo.getInstance().borrarArchivo(archivo.getId());
            }

            ManejadorArchivo.getInstance().modificarArchivo(archivo);

            ServicioActividad.getInstance().crearActividad(Operacion.Modificar, archivo, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Modificar, archivo, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtArchivo.getClassName() + ".update.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorArchivo.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR(dtArchivo.getClassName() + ".update.error");
        }
    }

    @Override
    public List<DtArchivo> listarArchivosSistema(boolean borrado, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Archivo(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<Archivo> listaAnuncios = ManejadorArchivo.getInstance().listarArchivosSistema(borrado);

        return (List<DtArchivo>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaAnuncios);
    }

    @Override
    public List<DtArchivo> listarArchivosAplicacion(Long aplicacionId, boolean borrado, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Archivo(), dtSesion);
        if (!dtMensaje.isExito()) {
            dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Ver, new Archivo(), aplicacionId, dtSesion);
            if (!dtMensaje.isExito()) {
                return null;
            }
        }

        List<Archivo> listaArchivos = new ArrayList<>();
        List<Archivo> listaArchivosAplicacion = ManejadorArchivo.getInstance().listarArchivosAplicacion(aplicacionId, borrado);

//        if (!borrado) {
//            List<Archivo> listaArchivosGlobales = ManejadorArchivo.getInstance().listarArchivosGlobalesActivos();
//            List<Archivo> listaArchivosAplicaciones = ManejadorArchivo.getInstance().listarArchivosAplicacionesActivos();
//            listaArchivos.addAll(listaArchivosGlobales);
//            listaArchivos.addAll(listaArchivosAplicaciones);
//        }

        listaArchivos.addAll(listaArchivosAplicacion);

        return (List<DtArchivo>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaArchivos);
    }

    public DataHandler descargarArchivo(Long id, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Archivo(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        Archivo archivo = ManejadorArchivo.getInstance().obtenerArchivo(id);
        if (archivo == null) {
            return null;
        }

        return ServicioArchivo.getInstance().descargarArchivo(archivo);
    }

    public DataHandler descargarArchivoAplicacion(Long id, Long aplicacionId, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Archivo(), dtSesion);
        if (!dtMensaje.isExito()) {
            dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Ver, new Archivo(), aplicacionId, dtSesion);
            if (!dtMensaje.isExito()) {
                return null;
            }
        }

        Archivo archivo = ManejadorArchivo.getInstance().obtenerArchivo(id);
        if (archivo == null) {
            return null;
        }

        return ServicioArchivo.getInstance().descargarArchivo(archivo);
    }

    public DataHandler descargarArchivoPublico(Long id) {

        Archivo archivo = ManejadorArchivo.getInstance().obtenerArchivo(id);
        if (archivo == null) {
            return null;
        }

        DtMensaje dtMensaje = ValidatorArchivo.getInstance().validarObtenerArchivoPublico(archivo);
        if (!dtMensaje.isExito()) {
            return null;
        }
        return ServicioArchivo.getInstance().descargarArchivo(archivo);
    }

    @Override
    public DtArchivo obtenerDtArchivoPublico(Long id) {
        Archivo archivo = ManejadorArchivo.getInstance().obtenerArchivo(id);
        if (!archivo.esPublico()) {
            return null;
        }
        return archivo.getDtArchivo();
    }

    @Override
    public List<DtArchivo> listarArchivosSistemaActivos() {
        List<Archivo> listaAnuncios = ManejadorArchivo.getInstance().listarArchivosSistemaActivos();

        return (List<DtArchivo>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaAnuncios);
    }

    @Override
    public List<DtArchivo> listarArchivosAplicacionActivos(Long aplicacionId, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<Archivo> listaArchivosGlobales = ManejadorArchivo.getInstance().listarArchivosGlobalesActivos();
        List<Archivo> listaArchivosAplicaciones = ManejadorArchivo.getInstance().listarArchivosAplicacionesActivos();
        List<Archivo> listaArchivosAplicacion = ManejadorArchivo.getInstance().listarArchivosAplicacionActivos(aplicacionId);

        List<Archivo> listaArchivos = new ArrayList<>();

        listaArchivos.addAll(listaArchivosGlobales);
        listaArchivos.addAll(listaArchivosAplicaciones);
        listaArchivos.addAll(listaArchivosAplicacion);

        return (List<DtArchivo>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaArchivos);
    }

    @Override
    public List<DtArchivo> listarArchivosActivos(DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Archivo(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<Archivo> listaArchivos = ManejadorArchivo.getInstance().listarArchivosActivos();

        return (List<DtArchivo>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaArchivos);
    }

}
