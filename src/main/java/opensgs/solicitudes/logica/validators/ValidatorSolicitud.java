package opensgs.solicitudes.logica.validators;

import java.util.ArrayList;
import java.util.List;
import opensgs.datatypes.DtMensaje;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.sistema.logica.beans.Constancia;
import opensgs.sistema.logica.servicios.ServicioAplicacion;
import opensgs.solicitudes.logica.beans.Solicitud;
import opensgs.solicitudes.logica.servicios.ServicioSolicitud;
import opensgs.usuarios.logica.beans.Usuario;

public class ValidatorSolicitud {

    private static ValidatorSolicitud instance = null;

    public static ValidatorSolicitud getInstance() {
        if (instance == null) {
            instance = new ValidatorSolicitud();
        }
        return instance;
    }

    //VALIDA QUE LA APLICACION NO ESTE BORRADA
    private boolean validarAplicacionBorrada(Aplicacion aplicacion) {
        return !ServicioAplicacion.getInstance().estaBorrada(aplicacion);
    }

    //VALIDA QUE LA APLICACION ESTE ACTIVA
    private boolean validarAplicacionActiva(Aplicacion aplicacion) {
        return ServicioAplicacion.getInstance().estaActiva(aplicacion);
    }

    //VALIDA QUE LA SOLICITUD NO ESTE BORRADA
    private boolean validarSolicitudBorrada(Solicitud solicitud) {
        return !ServicioSolicitud.getInstance().estaBorrada(solicitud);
    }

    //VALIDA QUE LA SOLICITUD ESTE ACTIVA
    private boolean validarSolicitudActiva(Solicitud solicitud) {
        return ServicioSolicitud.getInstance().estaActiva(solicitud);
    }

    //VALIDA QUE LA SOLICITUD NO ESTE ACORDADA
    private boolean validarSolicitudAcordada(Solicitud solicitud) {
        return !ServicioSolicitud.getInstance().estaAcordado(solicitud);
    }

    //VALIDA QUE LA SOLICITUD NO ESTE ENTREGADA
    private boolean validarSolicitudEntregada(Solicitud solicitud) {
        return !ServicioSolicitud.getInstance().estaEntregado(solicitud);
    }

    public DtMensaje validarCrear(Aplicacion aplicacion) {
        try {
            List<String> listaErrores = new ArrayList<>();

            if (aplicacion != null) {

                if (this.validarAplicacionBorrada(aplicacion)) {
                    listaErrores.add(ServicioMensaje.getInstance().getMensaje("Aplicacion.deleted.error"));
                }

                if (!this.validarAplicacionActiva(aplicacion)) {
                    listaErrores.add(ServicioMensaje.getInstance().getMensaje("Aplicacion.disabled.error"));
                }

                DtMensaje dtMensaje = ServicioAplicacion.getInstance().estaAbierta(aplicacion);
                if (!dtMensaje.isExito()) {
                    listaErrores.addAll(dtMensaje.getMensajes());
                }

            } else {
                listaErrores.add(ServicioMensaje.getInstance().getMensaje("Aplicacion.unexpected.error"));
            }

            if (listaErrores.isEmpty()) {
                return ServicioMensaje.getInstance().getMensajeOK();
            } else {
                return ServicioMensaje.getInstance().getMensajeERROR(listaErrores);
            }
        } catch (Exception e) {
            return ServicioMensaje.getInstance().getMensajeERROR("Aplicacion.unexpected.error");
        }

    }

    public DtMensaje validarModificarMiSolicitud(Solicitud solicitud, Usuario usuario) {
        try {
            List<String> listaErrores = new ArrayList<>();

            if (solicitud != null && usuario != null) {

                if (this.validarAplicacionBorrada(solicitud.getAplicacion())) {
                    listaErrores.add(ServicioMensaje.getInstance().getMensaje("Aplicacion.deleted.error"));
                }

                if (!this.validarAplicacionActiva(solicitud.getAplicacion())) {
                    listaErrores.add(ServicioMensaje.getInstance().getMensaje("Aplicacion.disabled.error"));
                }

                if (!this.validarSolicitudEntregada(solicitud)) {
                    listaErrores.add(ServicioMensaje.getInstance().getMensaje("Solicitud.delivered.error"));
                }

                DtMensaje dtMensaje = ServicioAplicacion.getInstance().estaAbierta(solicitud.getAplicacion());
                if (!dtMensaje.isExito()) {
                    listaErrores.addAll(dtMensaje.getMensajes());
                }
                
                dtMensaje = ServicioSolicitud.getInstance().esPropietario(solicitud, usuario);
                if (!dtMensaje.isExito()) {
                    listaErrores.addAll(dtMensaje.getMensajes());
                }

            } else {
                listaErrores.add(ServicioMensaje.getInstance().getMensaje("Solicitud.unexpected.error"));
            }

            if (listaErrores.isEmpty()) {
                return ServicioMensaje.getInstance().getMensajeOK();
            } else {
                return ServicioMensaje.getInstance().getMensajeERROR(listaErrores);
            }
        } catch (Exception e) {
            return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.unexpected.error");
        }

    }

    public DtMensaje validarEntregarMiSolicitud(Solicitud solicitud, Usuario usuario) {
        try {
            List<String> listaErrores = new ArrayList<>();

            if (solicitud != null && usuario != null) {

                if (this.validarAplicacionBorrada(solicitud.getAplicacion())) {
                    listaErrores.add(ServicioMensaje.getInstance().getMensaje("Aplicacion.deleted.error"));
                }

                if (!this.validarAplicacionActiva(solicitud.getAplicacion())) {
                    listaErrores.add(ServicioMensaje.getInstance().getMensaje("Aplicacion.disabled.error"));
                }

                if (!this.validarSolicitudEntregada(solicitud)) {
                    listaErrores.add(ServicioMensaje.getInstance().getMensaje("Solicitud.delivered.error"));
                }

                DtMensaje dtMensaje = ServicioAplicacion.getInstance().estaAbierta(solicitud.getAplicacion());
                if (!dtMensaje.isExito()) {
                    listaErrores.addAll(dtMensaje.getMensajes());
                }
                
                dtMensaje = ServicioSolicitud.getInstance().esPropietario(solicitud, usuario);
                if (!dtMensaje.isExito()) {
                    listaErrores.addAll(dtMensaje.getMensajes());
                }

            } else {
                listaErrores.add(ServicioMensaje.getInstance().getMensaje("Solicitud.unexpected.error"));
            }

            if (listaErrores.isEmpty()) {
                return ServicioMensaje.getInstance().getMensajeOK();
            } else {
                return ServicioMensaje.getInstance().getMensajeERROR(listaErrores);
            }
        } catch (Exception e) {
            return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.unexpected.error");
        }

    }

    public DtMensaje validarObtenerMiSolicitud(Solicitud solicitud, Usuario usuario) {
        try {
            List<String> listaErrores = new ArrayList<>();

            if (solicitud != null && usuario != null) {

                if (this.validarSolicitudBorrada(solicitud)) {
                    listaErrores.add(ServicioMensaje.getInstance().getMensaje("Solicitud.deleted.error"));
                }

                if (!this.validarSolicitudActiva(solicitud)) {
                    listaErrores.add(ServicioMensaje.getInstance().getMensaje("Solicitud.disabled.error"));
                }

                DtMensaje dtMensaje = ServicioSolicitud.getInstance().esPropietario(solicitud, usuario);
                if (!dtMensaje.isExito()) {
                    listaErrores.addAll(dtMensaje.getMensajes());
                }

            } else {
                listaErrores.add(ServicioMensaje.getInstance().getMensaje("Solicitud.unexpected.error"));
            }

            if (listaErrores.isEmpty()) {
                return ServicioMensaje.getInstance().getMensajeOK();
            } else {
                return ServicioMensaje.getInstance().getMensajeERROR(listaErrores);
            }
        } catch (Exception e) {
            return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.unexpected.error");
        }

    }

    public DtMensaje validarEmitirMiConstancia(Solicitud solicitud, Constancia constancia, Usuario usuario) {
        try {
            List<String> listaErrores = new ArrayList<>();

            if (solicitud != null && constancia != null && usuario != null) {

                if (this.validarSolicitudBorrada(solicitud)) {
                    listaErrores.add(ServicioMensaje.getInstance().getMensaje("Solicitud.deleted.error"));
                }

                if (!this.validarSolicitudActiva(solicitud)) {
                    listaErrores.add(ServicioMensaje.getInstance().getMensaje("Solicitud.disabled.error"));
                }

                DtMensaje dtMensaje = ServicioSolicitud.getInstance().esPropietario(solicitud, usuario);
                if (!dtMensaje.isExito()) {
                    listaErrores.addAll(dtMensaje.getMensajes());
                }

                if (!ServicioSolicitud.getInstance().esConstanciaEmitible(solicitud, constancia)) {
                    listaErrores.add(ServicioMensaje.getInstance().getMensaje("Solicitud.certificate.error"));
                }

            } else {
                listaErrores.add(ServicioMensaje.getInstance().getMensaje("Solicitud.unexpected.error"));
            }

            if (listaErrores.isEmpty()) {
                return ServicioMensaje.getInstance().getMensajeOK();
            } else {
                return ServicioMensaje.getInstance().getMensajeERROR(listaErrores);
            }
        } catch (Exception e) {
            return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.unexpected.error");
        }

    }

}
