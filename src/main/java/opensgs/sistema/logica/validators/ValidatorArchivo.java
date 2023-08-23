package opensgs.sistema.logica.validators;

import java.util.ArrayList;
import java.util.List;
import opensgs.datatypes.DtMensaje;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.sistema.logica.beans.Archivo;

public class ValidatorArchivo {

    private static ValidatorArchivo instance = null;

    public static ValidatorArchivo getInstance() {
        if (instance == null) {
            instance = new ValidatorArchivo();
        }
        return instance;
    }

    //VALIDA QUE EL ARCHIVO NO ESTE BORRADO
    private boolean validarArchivoBorrado(Archivo archivo) {
        return archivo.isBorrado();
    }

    //VALIDA QUE EL ARCHIVO ESTE ACTIVO
    private boolean validarArchivoActivo(Archivo archivo) {
        return archivo.isActivo();
    }

    //VALIDA QUE EL ARCHIVO SEA PUBLICO
    private boolean validarArchivoPublico(Archivo archivo) {
        return archivo.esPublico();
    }

    public DtMensaje validarObtenerArchivoPublico(Archivo archivo) {
        try {
            List<String> listaErrores = new ArrayList<>();

            if (archivo != null) {

                if (this.validarArchivoBorrado(archivo)) {
                    listaErrores.add(ServicioMensaje.getInstance().getMensaje("Archivo.deleted.error"));
                }

                if (!this.validarArchivoActivo(archivo)) {
                    listaErrores.add(ServicioMensaje.getInstance().getMensaje("Archivo.disabled.error"));
                }

                if (!this.validarArchivoPublico(archivo)) {
                    listaErrores.add(ServicioMensaje.getInstance().getMensaje("Archivo.public.error"));
                }

            } else {
                listaErrores.add(ServicioMensaje.getInstance().getMensaje("Archivo.unexpected.error"));
            }

            if (listaErrores.isEmpty()) {
                return ServicioMensaje.getInstance().getMensajeOK();
            } else {
                return ServicioMensaje.getInstance().getMensajeERROR(listaErrores);
            }
        } catch (Exception e) {
            return ServicioMensaje.getInstance().getMensajeERROR("Archivo.unexpected.error");
        }

    }

}
