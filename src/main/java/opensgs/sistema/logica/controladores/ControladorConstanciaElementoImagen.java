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
import opensgs.sistema.datatypes.DtConstanciaElementoImagen;
import opensgs.sistema.enums.AlcanceArchivo;
import opensgs.sistema.interfaces.IControladorConstanciaElementoImagen;
import opensgs.sistema.logica.beans.Archivo;
import opensgs.sistema.logica.beans.ConstanciaElementoImagen;
import opensgs.sistema.logica.servicios.ServicioActividad;
import opensgs.sistema.logica.servicios.ServicioArchivo;
import opensgs.sistema.logica.servicios.ServicioNotificacion;
import opensgs.sistema.persistencia.manejadores.ManejadorArchivo;
import opensgs.sistema.persistencia.manejadores.ManejadorConstanciaElementoImagen;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.logica.servicios.ServicioPermiso;
import opensgs.usuarios.logica.servicios.ServicioSesion;

public class ControladorConstanciaElementoImagen implements IControladorConstanciaElementoImagen {

    @Override
    public DtMensaje crearConstanciaElementoImagen(DataHandler archivoSubir, DtConstanciaElementoImagen dtConstanciaElementoImagen, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isCreable(dtConstanciaElementoImagen);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Crear, new ConstanciaElementoImagen(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioArchivo.getInstance().subirArchivoTemporal(archivoSubir, dtConstanciaElementoImagen.getNombre());
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            ConstanciaElementoImagen constanciaElementoImagen = new ConstanciaElementoImagen(dtConstanciaElementoImagen);
            Archivo archivo = new Archivo(dtConstanciaElementoImagen.getDtArchivo());
            archivo = ServicioArchivo.getInstance().obtenerMetadatos(constanciaElementoImagen.getNombre(), archivo);
            if (archivo == null) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtConstanciaElementoImagen.getNombre());
                return ServicioMensaje.getInstance().getMensajeERROR("ConstanciaElementoImagen.create.error");
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarCrear(constanciaElementoImagen);
            if (!dtMensaje.isExito()) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtConstanciaElementoImagen.getNombre());
                return dtMensaje;
            }

            dtMensaje = ServicioArchivo.getInstance().moverArchivoTemporal(dtConstanciaElementoImagen.getNombre());
            if (!dtMensaje.isExito()) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtConstanciaElementoImagen.getNombre());
                return dtMensaje;
            }

            archivo.setTitulo(dtConstanciaElementoImagen.getDtArchivo().getNombre());
            archivo.setAlcanceArchivo(AlcanceArchivo.CONSTANCIA_IMAGEN);
            constanciaElementoImagen.setArchivo(archivo);

            ManejadorArchivo.getInstance().crearArchivo(archivo);
            ManejadorConstanciaElementoImagen.getInstance().crearConstanciaElementoImagen(constanciaElementoImagen);

            constanciaElementoImagen = ManejadorConstanciaElementoImagen.getInstance().obtenerConstanciaElementoImagen(dtConstanciaElementoImagen.getNombre());

            ServicioActividad.getInstance().crearActividad(Operacion.Crear, constanciaElementoImagen, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Crear, constanciaElementoImagen, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK("Constancia.create.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorConstanciaElementoImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServicioMensaje.getInstance().getMensajeERROR("Constancia.create.error");
    }

    @Override
    public DtMensaje modificarConstanciaElementoImagen(DataHandler archivoSubir, DtConstanciaElementoImagen dtConstanciaElementoImagen, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isModificable(dtConstanciaElementoImagen);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Modificar, new ConstanciaElementoImagen(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioArchivo.getInstance().subirArchivoTemporal(archivoSubir, dtConstanciaElementoImagen.getDtArchivo().getNombre());
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            ConstanciaElementoImagen constanciaElementoImagen = ManejadorConstanciaElementoImagen.getInstance().obtenerConstanciaElementoImagen(dtConstanciaElementoImagen);
            if (constanciaElementoImagen == null) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtConstanciaElementoImagen.getDtArchivo().getNombre());
                return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.notexists.error");
            }

            String nombreAnterior = constanciaElementoImagen.getArchivo().getNombre();

            Archivo archivo = constanciaElementoImagen.getArchivo();
            archivo = ServicioArchivo.getInstance().obtenerMetadatos(dtConstanciaElementoImagen.getDtArchivo().getNombre(), archivo);
            if (archivo == null) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtConstanciaElementoImagen.getDtArchivo().getNombre());
                return ServicioMensaje.getInstance().getMensajeERROR("Constancia.create.error");
            }

            archivo.setNombre(dtConstanciaElementoImagen.getDtArchivo().getNombre());
            archivo.setTitulo(dtConstanciaElementoImagen.getDtArchivo().getNombre());

            constanciaElementoImagen.modificar(dtConstanciaElementoImagen);
            constanciaElementoImagen.setArchivo(archivo);

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarModificar(constanciaElementoImagen);
            if (!dtMensaje.isExito()) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtConstanciaElementoImagen.getDtArchivo().getNombre());
                return dtMensaje;
            }

            dtMensaje = ServicioArchivo.getInstance().moverArchivoTemporal(dtConstanciaElementoImagen.getDtArchivo().getNombre());
            if (!dtMensaje.isExito()) {
                ServicioArchivo.getInstance().borrarArchivoTemporal(dtConstanciaElementoImagen.getDtArchivo().getNombre());
                return dtMensaje;
            }

            if (!nombreAnterior.equals(archivo.getNombre())) {
                ServicioArchivo.getInstance().borrarArchivo(archivo.getId());
            }

            ManejadorArchivo.getInstance().modificarArchivo(archivo);
            ManejadorConstanciaElementoImagen.getInstance().modificarConstanciaElementoImagen(constanciaElementoImagen);

            ServicioActividad.getInstance().crearActividad(Operacion.Modificar, constanciaElementoImagen, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Modificar, constanciaElementoImagen, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtConstanciaElementoImagen.getClassName() + ".update.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorConstanciaElementoImagen.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR(dtConstanciaElementoImagen.getClassName() + ".update.error");
        }
    }

    @Override
    public List<DtConstanciaElementoImagen> listarConstanciaElementoImagen(Long constanciaId, boolean borrado, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<ConstanciaElementoImagen> listaConstanciaElementoImagen = ManejadorConstanciaElementoImagen.getInstance().listarConstanciaElementoImagen(constanciaId, borrado);

        return (List<DtConstanciaElementoImagen>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaConstanciaElementoImagen);
    }

}
