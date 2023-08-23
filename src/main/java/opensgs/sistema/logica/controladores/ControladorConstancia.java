package opensgs.sistema.logica.controladores;

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
import opensgs.sistema.datatypes.DtConstancia;
import opensgs.sistema.enums.AlcanceArchivo;
import opensgs.sistema.interfaces.IControladorConstancia;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.sistema.logica.beans.Archivo;
import opensgs.sistema.logica.beans.Constancia;
import opensgs.sistema.logica.servicios.ServicioActividad;
import opensgs.sistema.logica.servicios.ServicioArchivo;
import opensgs.sistema.logica.servicios.ServicioConstancia;
import opensgs.sistema.logica.servicios.ServicioNotificacion;
import opensgs.sistema.persistencia.manejadores.ManejadorAplicacion;
import opensgs.sistema.persistencia.manejadores.ManejadorArchivo;
import opensgs.sistema.persistencia.manejadores.ManejadorConstancia;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.logica.beans.Usuario;
import opensgs.usuarios.logica.servicios.ServicioPermiso;
import opensgs.usuarios.logica.servicios.ServicioSesion;
import opensgs.usuarios.persistencia.manejadores.ManejadorUsuario;

public class ControladorConstancia implements IControladorConstancia {

    @Override
    public DtMensaje crearConstancia(DataHandler archivoSubir, DtConstancia dtConstancia, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isCreable(dtConstancia);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Crear, new Constancia(), dtSesion);
            if (!dtMensaje.isExito()) {
                dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Crear, new Constancia(), dtConstancia.getDtAplicacion().getId(), dtSesion);
                if (!dtMensaje.isExito()) {
                    return dtMensaje;
                }
            }

            Aplicacion aplicacion = ManejadorAplicacion.getInstance().obtenerAplicacion(dtConstancia.getDtAplicacion());
            if (aplicacion == null) {
                return ServicioMensaje.getInstance().getMensajeERROR(dtConstancia.getClassName() + ".create.error");
            }

            dtMensaje = ServicioArchivo.getInstance().subirArchivoTemporal(archivoSubir, dtConstancia.getDtArchivo().getNombre());
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            Constancia constancia = new Constancia(dtConstancia);

            Archivo archivo = new Archivo(dtConstancia.getDtArchivo());
            archivo = ServicioArchivo.getInstance().obtenerMetadatos(archivo.getNombre(), archivo);
            if (archivo == null) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtConstancia.getDtArchivo().getNombre());
                return ServicioMensaje.getInstance().getMensajeERROR(dtConstancia.getClassName() + ".create.error");
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarCrear(constancia);
            if (!dtMensaje.isExito()) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtConstancia.getDtArchivo().getNombre());
                return dtMensaje;
            }

            dtMensaje = ServicioArchivo.getInstance().moverArchivoTemporal(dtConstancia.getDtArchivo().getNombre());
            if (!dtMensaje.isExito()) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtConstancia.getDtArchivo().getNombre());
                return dtMensaje;
            }

            archivo.setTitulo(dtConstancia.getDtArchivo().getNombre());
            archivo.setAlcanceArchivo(AlcanceArchivo.CONSTANCIA);
            constancia.setArchivo(archivo);
            constancia.setAplicacion(aplicacion);

            ManejadorArchivo.getInstance().crearArchivo(archivo);
            ManejadorConstancia.getInstance().crearConstancia(constancia);

            constancia = ManejadorConstancia.getInstance().obtenerConstancia(dtConstancia.getNombre());

            ServicioActividad.getInstance().crearActividad(Operacion.Crear, constancia, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Crear, constancia, dtSesion);

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(aplicacion);

            ServicioActividad.getInstance().crearActividad(Operacion.Agregar, aplicacion, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Agregar, aplicacion, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtConstancia.getClassName() + ".create.success", archivo.getId());
        } catch (Exception ex) {
            Logger.getLogger(ControladorAnuncio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServicioMensaje.getInstance().getMensajeERROR(dtConstancia.getClassName() + ".create.error");
    }

    @Override
    public DtMensaje modificarConstancia(DataHandler archivoSubir, DtConstancia dtConstancia, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isModificable(dtConstancia);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Modificar, new Constancia(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioArchivo.getInstance().subirArchivoTemporal(archivoSubir, dtConstancia.getDtArchivo().getNombre());
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            Constancia constancia = ManejadorConstancia.getInstance().obtenerConstancia(dtConstancia);
            if (constancia == null) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtConstancia.getDtArchivo().getNombre());
                return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.notexists.error");
            }

            String nombreAnterior = constancia.getArchivo().getNombre();

            Archivo archivo = constancia.getArchivo();
            archivo = ServicioArchivo.getInstance().obtenerMetadatos(dtConstancia.getDtArchivo().getNombre(), archivo);
            if (archivo == null) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtConstancia.getDtArchivo().getNombre());
                return ServicioMensaje.getInstance().getMensajeERROR("Constancia.create.error");
            }

            archivo.setNombre(dtConstancia.getDtArchivo().getNombre());
            archivo.setTitulo(dtConstancia.getDtArchivo().getNombre());

            constancia.modificar(dtConstancia);
            constancia.setArchivo(archivo);

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarModificar(constancia);
            if (!dtMensaje.isExito()) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtConstancia.getDtArchivo().getNombre());
                return dtMensaje;
            }

            dtMensaje = ServicioArchivo.getInstance().moverArchivoTemporal(dtConstancia.getDtArchivo().getNombre());
            if (!dtMensaje.isExito()) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtConstancia.getDtArchivo().getNombre());
                return dtMensaje;
            }

            if (!nombreAnterior.equals(archivo.getNombre())) {
                ServicioArchivo.getInstance().borrarArchivo(archivo.getId());
            }

            ManejadorArchivo.getInstance().modificarArchivo(archivo);
            ManejadorConstancia.getInstance().modificarConstancia(constancia);

            ServicioActividad.getInstance().crearActividad(Operacion.Modificar, constancia, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Modificar, constancia, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtConstancia.getClassName() + ".update.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorConstancia.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR(dtConstancia.getClassName() + ".update.error");
        }
    }

    @Override
    public List<DtConstancia> listarConstancias(Long aplicacionId, boolean borrado, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Constancia(), dtSesion);
        if (!dtMensaje.isExito()) {
            dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Ver, new Constancia(), aplicacionId, dtSesion);
            if (!dtMensaje.isExito()) {
                return null;
            }
        }

        List<Constancia> listaConstanciasAplicacion = ManejadorConstancia.getInstance().listarConstancias(aplicacionId, borrado);

        return (List<DtConstancia>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaConstanciasAplicacion);
    }

//    @Override
//    public List<DtConstancia> listarConstanciasActivas(DtSesion dtSesion) {
//        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
//        if (!dtMensaje.isExito()) {
//            return null;
//        }
//
//        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Constancia(), dtSesion);
//        if (!dtMensaje.isExito()) {
//            return null;
//        }
//
//        List<Constancia> listaConstancias = ManejadorConstancia.getInstance().listarConstanciasActivas(Long.MIN_VALUE);
//
//        return (List<DtConstancia>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaConstancias);
//    }
    @Override
    public DataHandler emitirConstancia(Long id, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        Constancia constancia = ManejadorConstancia.getInstance().obtenerConstancia(id);
        if (constancia == null) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Constancia(), dtSesion);
        if (!dtMensaje.isExito()) {
            dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Ver, new Constancia(), constancia.getAplicacion(), dtSesion);
            if (!dtMensaje.isExito()) {
                return null;
            }
        }

        Usuario usuario = ManejadorUsuario.getInstance().obtenerUsuario(dtSesion);

        return ServicioConstancia.getInstance().emitirConstancia(constancia, usuario);
    }

//    @Override
//    public DataHandler emitirConstanciaAplicacion(Long id, Long aplicacionId, DtSesion dtSesion) {
//        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
//        if (!dtMensaje.isExito()) {
//            return null;
//        }
//       
//        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Constancia(), dtSesion);
//        if (!dtMensaje.isExito()) {
//            dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Ver, new Constancia(), aplicacionId, dtSesion);
//            if (!dtMensaje.isExito()) {
//                return null;
//            }
//        }
//
//        Constancia constancia = ManejadorConstancia.getInstance().obtenerConstancia(id);
//        if (constancia == null) {
//            return null;
//        }
//
//        Usuario usuario = ManejadorUsuario.getInstance().obtenerUsuario(dtSesion);
//
//        Aplicacion aplicacion = ManejadorAplicacion.getInstance().obtenerAplicacion(aplicacionId);
//
//        return ServicioConstancia.getInstance().emitirConstancia(constancia, aplicacion, usuario);
//    }
}
