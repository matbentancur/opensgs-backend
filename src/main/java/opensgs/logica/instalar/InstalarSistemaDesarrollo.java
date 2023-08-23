package opensgs.logica.instalar;

import java.util.Date;
import opensgs.datatypes.DtMensaje;
import opensgs.enums.MensajeTipo;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.enums.ServidorAutenticacionTipo;
import opensgs.sistema.enums.ServidorCorreoIdentificacion;
import opensgs.sistema.enums.ServidorCorreoSeguridad;
import opensgs.sistema.logica.beans.ServidorAplicaciones;
import opensgs.sistema.logica.beans.ServidorAutenticacion;
import opensgs.sistema.logica.beans.ServidorCorreo;
import opensgs.sistema.logica.beans.Sistema;
import opensgs.sistema.persistencia.manejadores.ManejadorSistema;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.enums.DocumentoTipo;
import opensgs.usuarios.logica.beans.Perfil;
import opensgs.usuarios.logica.beans.Usuario;
import opensgs.usuarios.persistencia.manejadores.ManejadorPerfil;

public class InstalarSistemaDesarrollo extends InstalarSistema {

    public DtMensaje inicializarDatos() throws Exception {

        DtMensaje dtMensaje = super.inicializarDatos();
        if (!dtMensaje.isExito()) {
            return dtMensaje;
        }

        this.crearServidorAplicaciones();
        this.crearServidorAutenticacion();
        this.crearServidorCorreo();
        this.crearSistema();
        this.crearUsuarioSistema();
        this.crearUsuarioAdministrador();
        this.crearUsuarioConvencional();

        //PRUEBA DE INICIO DE SESION
        DtSesion dtSesion = iControladorUsuario.iniciarSesion("sistema@opensgs.uy", "sistema@opensgs.uy");

        listaErrores.add("Los datos fueron inicializados");
        return new DtMensaje(true, listaErrores, MensajeTipo.OK);
    }

    private void crearSistema() throws Exception {
        Sistema s = ManejadorSistema.getInstance().obtenerSistema();
        if (s == null) {
            s = new Sistema();
            s.setActivo(true);
            s.setBorrado(false);
            s.setAdministrable(true);
            s.setNombre("OpenSGS");
            s.setUrl("https://opensgs.uy/opensgs-web");
            s.setMantenimiento(false);
            s.setCantidadMaximaUsuarios(1000);
            s.setFileUploadMaxSize(20);
            s.setCorreoSoporte("sistema@opensgs.uy");
            s.setFilesPath("/home/opensgs/desarrollo/");
            s.setServidorCorreo(this.getServidorCorreo());
            s.setServidorAplicaciones(this.getServidorAplicaciones());
            s.setServidorAutenticacion(this.getServidorAutenticacion());
            ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(s);
        }
        this.setSistema(s);
    }

    private void crearServidorAplicaciones() throws Exception {
        ServidorAplicaciones servidorAplicacionesLocal = (ServidorAplicaciones) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new ServidorAplicaciones(), "nombre", "localhost");
        if (servidorAplicacionesLocal == null) {
            servidorAplicacionesLocal = new ServidorAplicaciones();
            servidorAplicacionesLocal.setActivo(true);
            servidorAplicacionesLocal.setBorrado(false);
            servidorAplicacionesLocal.setAdministrable(false);
            servidorAplicacionesLocal.setNombre("localhost");
            servidorAplicacionesLocal.setServidor("localhost");
            servidorAplicacionesLocal.setPuerto(8080);
            servidorAplicacionesLocal.setUsuario("admin");
            servidorAplicacionesLocal.setPassword("admin");
            servidorAplicacionesLocal.setLogPath("/var/log/opensgs");
            ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(servidorAplicacionesLocal);
        }
        this.setServidorAplicaciones(servidorAplicacionesLocal);
    }

    private void crearServidorAutenticacion() throws Exception {
        ServidorAutenticacion servidorAutenticacionLocal = (ServidorAutenticacion) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new ServidorAutenticacion(), "nombre", "Servidor Local");
        if (servidorAutenticacionLocal == null) {
            servidorAutenticacionLocal = new ServidorAutenticacion();
            servidorAutenticacionLocal.setActivo(true);
            servidorAutenticacionLocal.setBorrado(false);
            servidorAutenticacionLocal.setAdministrable(false);
            servidorAutenticacionLocal.setNombre("Servidor Local");
            servidorAutenticacionLocal.setServidor("localhost");
            servidorAutenticacionLocal.setPuerto(8080);
            servidorAutenticacionLocal.setUsuario("opensgs");
            servidorAutenticacionLocal.setPassword("opensgs");
            servidorAutenticacionLocal.setTipo(ServidorAutenticacionTipo.LOCAL);
            ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(servidorAutenticacionLocal);
        }
        this.setServidorAutenticacion(servidorAutenticacionLocal);
    }

    private void crearServidorCorreo() throws Exception {
        ServidorCorreo servidorCorreoLocal = (ServidorCorreo) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new ServidorCorreo(), "nombre", "Servidor opensgs.uy");
        if (servidorCorreoLocal == null) {
            servidorCorreoLocal = new ServidorCorreo();
            servidorCorreoLocal.setActivo(true);
            servidorCorreoLocal.setBorrado(false);
            servidorCorreoLocal.setAdministrable(false);
            servidorCorreoLocal.setNombre("Servidor opensgs.uy");
            servidorCorreoLocal.setServidor("correo.opensgs.uy");
            servidorCorreoLocal.setPuerto(587);
            servidorCorreoLocal.setSeguridad(ServidorCorreoSeguridad.NINGUNA);
            servidorCorreoLocal.setIdentificacion(ServidorCorreoIdentificacion.PLAIN);
            servidorCorreoLocal.setDesdeCorreo("solicitudes@opensgs.uy");
            servidorCorreoLocal.setDesdeNombre("Sistema de Gestión de Solicitudes");
            servidorCorreoLocal.setUsuario("opensgs");
            servidorCorreoLocal.setPassword("opensgs");
            servidorCorreoLocal.encriptarPassword();
            ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(servidorCorreoLocal);
        }
    }

    private void crearUsuarioSistema() throws Exception {
        Usuario usuario = (Usuario) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new Usuario(), "email", "sistema@opensgs.uy");
        if (usuario == null) {
            usuario = new Usuario();
            usuario.setActivo(true);
            usuario.setBorrado(false);
            usuario.setDocumentoTipo(DocumentoTipo.DocumentoUruguayo);
            usuario.setDocumento("00000000");
            usuario.setEmail("sistema@opensgs.uy");
            usuario.setClave("sistema@opensgs.uy");
            usuario.cifrarPassword();
            usuario.setNombres("OpenSGS");
            usuario.setApellidos("Sistema");
            usuario.setTelefono("00000000");
            usuario.setCreacion(new Date());
            usuario.setAdministrable(false);
            usuario.setPerfil(this.getPerfilSistema());
            ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(usuario);
        }
    }

    private void crearUsuarioAdministrador() throws Exception {
        Usuario usuario = (Usuario) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new Usuario(), "email", "administrador@opensgs.uy");
        if (usuario == null) {
            usuario = new Usuario();
            usuario.setActivo(true);
            usuario.setBorrado(false);
            usuario.setDocumentoTipo(DocumentoTipo.DocumentoUruguayo);
            usuario.setDocumento("00000001");
            usuario.setEmail("administrador@opensgs.uy");
            usuario.setClave("administrador@opensgs.uy");
            usuario.cifrarPassword();
            usuario.setNombres("OpenSGS");
            usuario.setApellidos("Administrador");
            usuario.setTelefono("00000001");
            usuario.setCreacion(new Date());
            usuario.setAdministrable(false);

            Perfil perfilAdministrador = ManejadorPerfil.getInstance().obtenerPerfil("nombre", "Administrador");

            usuario.setPerfil(perfilAdministrador);
            ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(usuario);
        }
    }

    private void crearUsuarioConvencional() throws Exception {
        Usuario usuario = (Usuario) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new Usuario(), "email", "usuario@opensgs.uy");
        if (usuario == null) {
            usuario = new Usuario();
            usuario.setActivo(true);
            usuario.setBorrado(false);
            usuario.setDocumentoTipo(DocumentoTipo.DocumentoUruguayo);
            usuario.setDocumento("00000002");
            usuario.setEmail("usuario@opensgs.uy");
            usuario.setClave("usuario@opensgs.uy");
            usuario.cifrarPassword();
            usuario.setNombres("OpenSGS");
            usuario.setApellidos("Usuario");
            usuario.setTelefono("00000002");
            usuario.setCreacion(new Date());
            usuario.setAdministrable(false);

            Perfil perfilUsuario = ManejadorPerfil.getInstance().obtenerPerfil("nombre", "Usuario");

            usuario.setPerfil(perfilUsuario);
            ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(usuario);
        }
    }

}
