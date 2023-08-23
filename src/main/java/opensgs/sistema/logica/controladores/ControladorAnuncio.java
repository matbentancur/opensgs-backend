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
import opensgs.sistema.datatypes.DtAnuncio;
import opensgs.sistema.interfaces.IControladorAnuncio;
import opensgs.sistema.logica.beans.Anuncio;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.sistema.logica.servicios.ServicioActividad;
import opensgs.sistema.logica.servicios.ServicioNotificacion;
import opensgs.sistema.persistencia.manejadores.ManejadorAnuncio;
import opensgs.sistema.persistencia.manejadores.ManejadorAplicacion;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.logica.servicios.ServicioPermiso;
import opensgs.usuarios.logica.servicios.ServicioSesion;

public class ControladorAnuncio implements IControladorAnuncio {

    @Override
    public DtMensaje crearAnuncioAplicacion(DtAnuncio dtAnuncio, Long aplicacionId, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isCreable(dtAnuncio);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Crear, new Anuncio(), dtSesion);
            if (!dtMensaje.isExito()) {
                dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Crear, new Anuncio(), aplicacionId, dtSesion);
                if (!dtMensaje.isExito()) {
                    return dtMensaje;
                }
            }

            Anuncio anuncio = (Anuncio) ServicioOpenSGSBean.getInstance().getNewOpenSGSManagedBean(dtAnuncio);
            if (anuncio == null) {
                return ServicioMensaje.getInstance().getMensajeERROR(dtAnuncio.getClassName() + ".create.error");
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarCrear(anuncio);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            anuncio.setAlcance(Alcance.APLICACION);

            ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(anuncio);

            ServicioActividad.getInstance().crearActividad(Operacion.Crear, anuncio, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Crear, anuncio, dtSesion);

            Aplicacion aplicacion = ManejadorAplicacion.getInstance().obtenerAplicacion(aplicacionId);
            if (aplicacion == null) {
                return ServicioMensaje.getInstance().getMensajeERROR(dtAnuncio.getClassName() + ".create.error");
            }

            aplicacion.agregarListOpenSGSBean(anuncio);

            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(aplicacion);

            ServicioActividad.getInstance().crearActividad(Operacion.Agregar, aplicacion, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Agregar, aplicacion, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK(dtAnuncio.getClassName() + ".create.success", anuncio.getId());
        } catch (Exception ex) {
            Logger.getLogger(ControladorAnuncio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ServicioMensaje.getInstance().getMensajeERROR(dtAnuncio.getClassName() + ".create.error");
    }

    @Override
    public List<DtAnuncio> listarAnunciosSistema(boolean borrado, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Anuncio(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<Anuncio> listaAnuncios = ManejadorAnuncio.getInstance().listarAnunciosSistema(borrado);

        return (List<DtAnuncio>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaAnuncios);
    }

    @Override
    public List<DtAnuncio> listarAnunciosAplicacion(Long aplicacionId, boolean borrado, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Anuncio(), dtSesion);
        if (!dtMensaje.isExito()) {
            dtMensaje = ServicioPermiso.getInstance().verificarPermisoAplicacion(Operacion.Ver, new Anuncio(), aplicacionId, dtSesion);
            if (!dtMensaje.isExito()) {
                return null;
            }
        }

        List<Anuncio> listaAnuncios = new ArrayList<>();
        List<Anuncio> listaAnunciosAplicacion = ManejadorAnuncio.getInstance().listarAnunciosAplicacion(aplicacionId, borrado);

//        if (!borrado) {
//            List<Anuncio> listaAnunciosGlobales = ManejadorAnuncio.getInstance().listarAnunciosGlobalesActivos();
//            List<Anuncio> listaAnunciosAplicaciones = ManejadorAnuncio.getInstance().listarAnunciosAplicacionesActivos();
//            listaAnuncios.addAll(listaAnunciosGlobales);
//            listaAnuncios.addAll(listaAnunciosAplicaciones);
//        }

        listaAnuncios.addAll(listaAnunciosAplicacion);

        return (List<DtAnuncio>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaAnuncios);
    }

    @Override
    public List<DtAnuncio> listarAnunciosSistemaVigentes() {
        List<Anuncio> listaAnuncios = ManejadorAnuncio.getInstance().listarAnunciosSistemaVigentes();

        return (List<DtAnuncio>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaAnuncios);
    }

    @Override
    public List<DtAnuncio> listarAnunciosAplicacionVigentes(Long aplicacionId, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<Anuncio> listaAnunciosAplicaciones = ManejadorAnuncio.getInstance().listarAnunciosAplicacionesVigentes();

        List<Anuncio> listaAnunciosAplicacion = ManejadorAnuncio.getInstance().listarAnunciosAplicacionVigentes(aplicacionId);

        List<Anuncio> listaAnuncios = new ArrayList<>();

        listaAnuncios.addAll(listaAnunciosAplicaciones);
        listaAnuncios.addAll(listaAnunciosAplicacion);

        return (List<DtAnuncio>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaAnuncios);
    }

    @Override
    public List<DtAnuncio> listarAnunciosAplicacionesVigentes(DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<Anuncio> listaAnuncios = ManejadorAnuncio.getInstance().listarAnunciosAplicacionesVigentes();

        return (List<DtAnuncio>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaAnuncios);
    }

}
