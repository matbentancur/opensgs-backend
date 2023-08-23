package opensgs.sistema.logica.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import opensgs.datatypes.DtMensaje;
import opensgs.enums.Alcance;
import opensgs.enums.Operacion;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.logica.servicios.ServicioOpenSGSBean;
import opensgs.logica.validators.ValidatorOpenSGSBean;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.datatypes.DtPreguntaFrecuente;
import opensgs.sistema.interfaces.IControladorPreguntaFrecuente;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.sistema.logica.beans.PreguntaFrecuente;
import opensgs.sistema.logica.servicios.ServicioActividad;
import opensgs.sistema.logica.servicios.ServicioNotificacion;
import opensgs.sistema.persistencia.manejadores.ManejadorAplicacion;
import opensgs.sistema.persistencia.manejadores.ManejadorPreguntaFrecuente;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.logica.servicios.ServicioPermiso;
import opensgs.usuarios.logica.servicios.ServicioSesion;

public class ControladorPreguntaFrecuente implements IControladorPreguntaFrecuente {

    @Override
    public DtMensaje crearPreguntaFrecuenteAplicacion(DtPreguntaFrecuente dtPreguntaFrecuente, Long aplicacionId, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isCreable(dtPreguntaFrecuente);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            PreguntaFrecuente preguntaFrecuente = (PreguntaFrecuente) ServicioOpenSGSBean.getInstance().getNewOpenSGSManagedBean(dtPreguntaFrecuente);
            if (preguntaFrecuente == null) {
                return ServicioMensaje.getInstance().getMensajeERROR(dtPreguntaFrecuente.getClassName() + ".create.error");
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Crear, new PreguntaFrecuente(), dtSesion);
            if (!dtMensaje.isExito()) {
                dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Crear, new PreguntaFrecuente(), aplicacionId, dtSesion);
                if (!dtMensaje.isExito()) {
                    return dtMensaje;
                }
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarCrear(preguntaFrecuente);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            preguntaFrecuente.setAlcance(Alcance.APLICACION);

            ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(preguntaFrecuente);

            ServicioActividad.getInstance().crearActividad(Operacion.Crear, preguntaFrecuente, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Crear, preguntaFrecuente, dtSesion);

            Aplicacion aplicacion = ManejadorAplicacion.getInstance().obtenerAplicacion(aplicacionId);
            aplicacion.agregarListOpenSGSBean(preguntaFrecuente);

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(aplicacion);

            ServicioActividad.getInstance().crearActividad(Operacion.Agregar, aplicacion, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Agregar, aplicacion, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtPreguntaFrecuente.getClassName() + ".create.success", preguntaFrecuente.getId());
        } catch (Exception ex) {
            Logger.getLogger(ControladorAnuncio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServicioMensaje.getInstance().getMensajeERROR(dtPreguntaFrecuente.getClassName() + ".create.error");
    }

    @Override
    public List<DtPreguntaFrecuente> listarPreguntasSistema(boolean borrado, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new PreguntaFrecuente(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<PreguntaFrecuente> listaPreguntaFrecuentes = ManejadorPreguntaFrecuente.getInstance().listarPreguntasSistema(borrado);

        return (List<DtPreguntaFrecuente>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaPreguntaFrecuentes);
    }

    @Override
    public List<DtPreguntaFrecuente> listarPreguntasAplicacion(Long aplicacionId, boolean borrado, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new PreguntaFrecuente(), dtSesion);
        if (!dtMensaje.isExito()) {
            dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Ver, new PreguntaFrecuente(), aplicacionId, dtSesion);
            if (!dtMensaje.isExito()) {
                return null;
            }
        }

        
        List<PreguntaFrecuente> listaPreguntas = new ArrayList<>();
        List<PreguntaFrecuente> listaPreguntasAplicacion = ManejadorPreguntaFrecuente.getInstance().listarAnunciosAplicacion(aplicacionId, borrado);

//        if (!borrado) {
//            List<PreguntaFrecuente> listaPreguntasGlobales = ManejadorPreguntaFrecuente.getInstance().listarPreguntasFrecuentesGlobalesActivas();
//            List<PreguntaFrecuente> listaPreguntasAplicaciones = ManejadorPreguntaFrecuente.getInstance().listarPreguntasFrecuentesAplicacionesActivas();
//            listaPreguntas.addAll(listaPreguntasGlobales);
//            listaPreguntas.addAll(listaPreguntasAplicaciones);
//        }

        listaPreguntas.addAll(listaPreguntasAplicacion);
        
        return (List<DtPreguntaFrecuente>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaPreguntas);
    }

    @Override
    public List<DtPreguntaFrecuente> listarPreguntasSistemaActivas() {

        List<PreguntaFrecuente> listaPreguntasFrecuentes = ManejadorPreguntaFrecuente.getInstance().listarPreguntasSistemaActivas();

        return (List<DtPreguntaFrecuente>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaPreguntasFrecuentes);
    }

    @Override
    public List<DtPreguntaFrecuente> listarPreguntasAplicacionActivas(Long aplicacionId, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new PreguntaFrecuente(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<PreguntaFrecuente> listaPreguntasAplicaciones = ManejadorPreguntaFrecuente.getInstance().listarPreguntasAplicacionesActivas();

        List<PreguntaFrecuente> listaPreguntasAplicacion = ManejadorPreguntaFrecuente.getInstance().listarPreguntasAplicacionActivas(aplicacionId);

        List<PreguntaFrecuente> listaPreguntas = new ArrayList<>();

        listaPreguntas.addAll(listaPreguntasAplicaciones);
        listaPreguntas.addAll(listaPreguntasAplicacion);

        return (List<DtPreguntaFrecuente>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaPreguntas);
    }

    @Override
    public List<DtPreguntaFrecuente> listarPreguntasFrecuentesActivas(DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new PreguntaFrecuente(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<PreguntaFrecuente> listaPreguntasFrecuentes = ManejadorPreguntaFrecuente.getInstance().listarPreguntasFrecuentesActivas();

        return (List<DtPreguntaFrecuente>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaPreguntasFrecuentes);
    }

}
