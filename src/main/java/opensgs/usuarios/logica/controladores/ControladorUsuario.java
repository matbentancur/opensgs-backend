package opensgs.usuarios.logica.controladores;

import opensgs.datatypes.DtMensaje;
import opensgs.usuarios.datatypes.DtUsuario;
import opensgs.enums.Elemento;
import opensgs.enums.Operacion;
import opensgs.usuarios.logica.beans.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import opensgs.usuarios.interfaces.IControladorUsuario;
import opensgs.usuarios.persistencia.manejadores.ManejadorUsuario;
import opensgs.sistema.logica.servicios.ServicioActividad;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.sistema.logica.servicios.ServicioNotificacion;
import opensgs.sistema.logica.validators.ValidatorSistema;
import opensgs.usuarios.datatypes.DtRegistrarUsuario;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.logica.beans.Sesion;
import opensgs.usuarios.logica.servicios.ServicioAutenticacion;
import opensgs.logica.servicios.ServicioPassword;
import opensgs.logica.validators.ValidatorOpenSGSBean;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.sistema.persistencia.manejadores.ManejadorAplicacion;
import opensgs.usuarios.logica.beans.Perfil;
import opensgs.usuarios.logica.beans.PerfilAplicacion;
import opensgs.usuarios.logica.servicios.ServicioPermiso;
import opensgs.usuarios.logica.servicios.ServicioSesion;
import opensgs.usuarios.logica.validators.ValidatorUsuario;
import opensgs.usuarios.persistencia.manejadores.ManejadorPerfil;
import opensgs.usuarios.persistencia.manejadores.ManejadorPerfilAplicacion;
import opensgs.usuarios.persistencia.manejadores.ManejadorSesion;

public class ControladorUsuario implements IControladorUsuario {

    //USUARIO
    @Override
    public DtMensaje autenticarUsuario(String usuario, String password) {
        try {

            DtMensaje dtMensaje = ValidatorSistema.getInstance().validarSistema();
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            return ServicioAutenticacion.getInstance().autenticarUsuario(usuario, password);

        } catch (Exception ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("Usuario.autentication.password.error");
        }
    }

    @Override
    public DtSesion iniciarSesion(String usuario, String password) {
        try {

            DtMensaje dtMensaje = ServicioAutenticacion.getInstance().autenticarUsuario(usuario, password);
            if (!dtMensaje.isExito()) {
                return null;
            }
            Usuario u = ManejadorUsuario.getInstance().obtenerUsuario("email", usuario);
            Sesion sesion = ManejadorSesion.getInstance().obtenerSesion(u.getId());
            if (sesion != null) {
                ManejadorSesion.getInstance().borrarSesion(sesion);
            }
            sesion = new Sesion(u);
            ManejadorSesion.getInstance().crearSesion(sesion);

            ServicioActividad.getInstance().crearActividad(Operacion.IniciarSesion, sesion, "login", u);

            return sesion.getDtSesion();
        } catch (Exception ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public DtMensaje cerrarSesion(DtSesion dtSesion) {
        try {
            //EL USUARIO NO EXISTE O NO HAY SESION
            Sesion sesion = ManejadorSesion.getInstance().obtenerSesion(dtSesion);
            if (sesion == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("Sesion.logout.error");
            }

            if (!sesion.getCodigo().equals(dtSesion.getCodigo())) {
                return ServicioMensaje.getInstance().getMensajeERROR("Sesion.logout.code.error");
            }

            Usuario usuario = ManejadorUsuario.getInstance().obtenerUsuario(dtSesion);
            if (usuario == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("Sesion.logout.error");
            }
            ManejadorSesion.getInstance().borrarSesion(sesion);
            ServicioActividad.getInstance().crearActividad(Operacion.CerrarSesion, sesion, "logout", usuario);

            return ServicioMensaje.getInstance().getMensajeOK("Sesion.logout.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("Sesion.logout.error");
        }
    }

    @Override
    public DtUsuario obtenerMisDatos(DtSesion dtSesion) {

        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.VerMisDatos, new Usuario(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        Usuario usuario = ManejadorUsuario.getInstance().obtenerUsuario(dtSesion);
        if (usuario == null) {
            return null;
        }

        return usuario.getDtUsuario();
    }

    @Override
    public DtMensaje modificarMisDatos(DtUsuario dtUsuario, DtSesion dtSesion) {
        try {

            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.ModificarMisDatos, new Usuario(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            Usuario usuario = ManejadorUsuario.getInstance().obtenerUsuario(dtSesion);
            if (!usuario.modificarMisDatos(dtUsuario)) {
                return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.update.error");
            }

            dtMensaje = ValidatorUsuario.getInstance().validarModificarMisDatos(usuario);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            ManejadorUsuario.getInstance().modificarUsuario(usuario);

            ServicioActividad.getInstance().crearActividad(Operacion.Modificar, usuario, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK("Usuario.mydata.update.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("Usuario.mydata.update.error");
        }
    }

    @Override
    public DtMensaje modificarMiClave(String passwordActual, String passwordNuevo, DtSesion dtSesion) {
        try {

            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.ModificarMiContraseña, new Usuario(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            Usuario usuario = ManejadorUsuario.getInstance().obtenerUsuario(dtSesion);

            dtMensaje = ValidatorUsuario.getInstance().validarModificarMiClave(usuario, passwordActual, passwordNuevo);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            usuario.modificarMiClave(passwordNuevo);
            ManejadorUsuario.getInstance().modificarUsuario(usuario);

            ServicioActividad.getInstance().crearActividad(Operacion.ModificarContraseña, usuario, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK("Password.change.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("Password.change.error");
        }
    }

    @Override
    public DtMensaje recuperarMiClave(String identificacion) {
        try {
            Usuario usuario = ManejadorUsuario.getInstance().obtenerUsuario("email", identificacion);
            if (usuario == null) {
                usuario = ManejadorUsuario.getInstance().obtenerUsuario("documento", identificacion);
                if (usuario == null) {
                    return ServicioMensaje.getInstance().getMensajeERROR("Usuario.rememberPassword.error");
                }
            }

            ServicioPassword passwordUtils = new ServicioPassword();
            String randomPassword = passwordUtils.getRandomPassword(8);
            usuario.setClave(randomPassword);
            ManejadorUsuario.getInstance().modificarUsuario(usuario);

            ServicioActividad.getInstance().crearActividad(Operacion.RecuperarContraseña, usuario, usuario.getDtUsuario());

            ServicioNotificacion.getInstance().notificar(Operacion.RecuperarContraseña, Elemento.Usuario, usuario);

            return ServicioMensaje.getInstance().getMensajeOK("Usuario.rememberPassword.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("Usuario.rememberPassword.unexpected.error");
        }
    }

    @Override
    public DtMensaje registrarUsuario(DtRegistrarUsuario dtRegistrarUsuario) {
        try {
            Usuario usuario = new Usuario(dtRegistrarUsuario);

            DtMensaje dtMensaje = ValidatorSistema.getInstance().validarSistema();
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorUsuario.getInstance().validarRegistrarUsuario(usuario);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            usuario.cifrarPassword();
            ManejadorUsuario.getInstance().crearUsuario(usuario);

            ServicioActividad.getInstance().crearActividad(Operacion.Registrar, usuario, usuario.getDtUsuario());

            ServicioNotificacion.getInstance().notificar(Operacion.Registrar, Elemento.Usuario, usuario);

            return ServicioMensaje.getInstance().getMensajeOK("Usuario.register.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("Usuario.register.error");
        }
    }
    
    @Override
    public DtMensaje agregarPerfilAplicacion(DtUsuario dtUsuario, Long perfilId, Long aplicacionId, DtSesion dtSesion) {
        try {
            DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().isModificable(dtUsuario);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Modificar, new Usuario(), dtSesion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            Usuario usuario = ManejadorUsuario.getInstance().obtenerUsuario(dtUsuario);
            if (usuario == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.notexists.error");
            }

            Perfil perfil = ManejadorPerfil.getInstance().obtenerPerfil(perfilId);
            if (perfil == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.notexists.error");
            }

            Aplicacion aplicacion = ManejadorAplicacion.getInstance().obtenerAplicacion(aplicacionId);
            if (aplicacion == null) {
                return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.notexists.error");
            }
            
            dtMensaje = ValidatorUsuario.getInstance().validarAgregarPerfilAplicacion(usuario, aplicacion);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            PerfilAplicacion perfilAplicacion = ManejadorPerfilAplicacion.getInstance().obtenerPerfilAplicacion(perfil, aplicacion);
            if (perfilAplicacion == null) {
                perfilAplicacion = new PerfilAplicacion(perfil, aplicacion);

                dtMensaje = ValidatorOpenSGSBean.getInstance().validarCrear(perfilAplicacion);
                if (!dtMensaje.isExito()) {
                    return dtMensaje;
                }

                ManejadorPerfilAplicacion.getInstance().crearPerfilAplicacion(perfilAplicacion);

                ServicioActividad.getInstance().crearActividad(Operacion.Crear, perfilAplicacion, dtSesion);
            }

            if (!usuario.agregarListOpenSGSBean(perfilAplicacion)) {
                return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.add.error");
            }

            dtMensaje = ValidatorOpenSGSBean.getInstance().validarModificar(usuario);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }

            ManejadorUsuario.getInstance().modificarUsuario(usuario);

            ServicioActividad.getInstance().crearActividad(Operacion.Modificar, usuario, dtSesion);

            ServicioNotificacion.getInstance().notificarSistema(Operacion.Modificar, usuario, dtSesion);

            return ServicioMensaje.getInstance().getMensajeOK("OpenSGSBean.add.success");
        } catch (Exception ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.add.error");
        }
    }

}
