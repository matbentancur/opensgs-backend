package opensgs.logica.instalar;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import opensgs.datatypes.DtMensaje;
import opensgs.usuarios.enums.AlcancePerfil;
import opensgs.enums.Elemento;
import opensgs.enums.MensajeTipo;
import opensgs.enums.Operacion;
import opensgs.logica.fabricas.FabricaOpenSGSBean;
import opensgs.interfaces.IControladorOpenSGSBean;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.logica.beans.PlantillaAplicacion;
import opensgs.sistema.logica.beans.ServidorAplicaciones;
import opensgs.sistema.logica.beans.ServidorAutenticacion;
import opensgs.sistema.logica.beans.ServidorCorreo;
import opensgs.sistema.logica.beans.Sistema;
import opensgs.usuarios.logica.fabricas.FabricaUsuario;
import opensgs.usuarios.interfaces.IControladorUsuario;
import opensgs.usuarios.logica.beans.Perfil;
import opensgs.usuarios.logica.servicios.ServicioPermiso;
import opensgs.usuarios.persistencia.manejadores.ManejadorPermiso;

public abstract class InstalarSistema {

    public final ResourceBundle mensajes = ResourceBundle.getBundle("opensgs-aplicacion");
    public final IControladorOpenSGSBean iControladorOpenSGSBean;
    public final IControladorUsuario iControladorUsuario;
    public final List<String> listaErrores;

    public Sistema sistema;
    public ServidorAplicaciones servidorAplicaciones;
    public ServidorAutenticacion servidorAutenticacion;
    public ServidorCorreo servidorCorreo;
    public Perfil perfilSistema;
    public PlantillaAplicacion plantillaAplicacionDefecto;

    String inicializado;

    public InstalarSistema() {
        iControladorOpenSGSBean = FabricaOpenSGSBean.getIControladorOpenSGSBean();
        iControladorUsuario = FabricaUsuario.getIControladorUsuario();
        listaErrores = new ArrayList<>();
    }

    public ResourceBundle getMensajes() {
        return mensajes;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public ServidorAplicaciones getServidorAplicaciones() {
        return servidorAplicaciones;
    }

    public void setServidorAplicaciones(ServidorAplicaciones servidorAplicaciones) {
        this.servidorAplicaciones = servidorAplicaciones;
    }

    public ServidorAutenticacion getServidorAutenticacion() {
        return servidorAutenticacion;
    }

    public void setServidorAutenticacion(ServidorAutenticacion servidorAutenticacion) {
        this.servidorAutenticacion = servidorAutenticacion;
    }

    public ServidorCorreo getServidorCorreo() {
        return servidorCorreo;
    }

    public void setServidorCorreo(ServidorCorreo servidorCorreo) {
        this.servidorCorreo = servidorCorreo;
    }

    public Perfil getPerfilSistema() {
        return perfilSistema;
    }

    public void setPerfilSistema(Perfil perfilSistema) {
        this.perfilSistema = perfilSistema;
    }

    public PlantillaAplicacion getPlantillaAplicacionDefecto() {
        return plantillaAplicacionDefecto;
    }

    public void setPlantillaAplicacionDefecto(PlantillaAplicacion plantillaAplicacionDefecto) {
        this.plantillaAplicacionDefecto = plantillaAplicacionDefecto;
    }

    public String getInicializado() {
        return inicializado;
    }

    public void setInicializado(String inicializado) {
        this.inicializado = inicializado;
    }

    public DtMensaje inicializarDatos() throws Exception {

        this.setInicializado(this.getMensajes().getString("opensgs.inicializado"));

        if (this.getInicializado().equals("true")) {
            return new DtMensaje(false, "Los datos ya fueron inicializados", MensajeTipo.ALERT);
        }

        this.crearPermisos();
        this.crearPerfilSistema();
        this.crearPerfilAdministrador();
        this.crearPerfilUsuario();

        return new DtMensaje(true, listaErrores, MensajeTipo.OK);
    }

    private void crearPermiso(Elemento elemento, Operacion operacion) throws Exception {
        ManejadorPermiso.getInstance().crearPermiso(elemento, operacion);
    }

    private void crearPermisoOpenSGSBean(Elemento elemento) throws Exception {
        ServicioPermiso.getInstance().crearPermisoOpenSGSBean(elemento);
    }

    private void crearPermisoOpenSGSManagedBean(Elemento elemento) throws Exception {
        ServicioPermiso.getInstance().crearPermisoOpenSGSManagedBean(elemento);
    }

    private void crearPermisos() throws Exception {
        //sistema
        this.crearPermisoOpenSGSBean(Elemento.Sistema);
        this.crearPermiso(Elemento.Sistema, Operacion.Modificar);
        //perfil
        this.crearPermisoOpenSGSManagedBean(Elemento.Perfil);
        //usuario
        this.crearPermisoOpenSGSManagedBean(Elemento.Usuario);
        this.crearPermiso(Elemento.Usuario, Operacion.VerMisDatos);
        this.crearPermiso(Elemento.Usuario, Operacion.ModificarMisDatos);
        this.crearPermiso(Elemento.Usuario, Operacion.ModificarMiContraseña);
        //permisos
        this.crearPermisoOpenSGSBean(Elemento.Permiso);
        //perfil aplicacion
        this.crearPermisoOpenSGSBean(Elemento.PerfilAplicacion);
        //actividad
        this.crearPermisoOpenSGSBean(Elemento.Actividad);
        //destinatarios
        this.crearPermisoOpenSGSBean(Elemento.Destinatario);
        //servidor de aplicaciones
        this.crearPermisoOpenSGSManagedBean(Elemento.ServidorAplicaciones);
        //servidor de autententicacion
        this.crearPermisoOpenSGSManagedBean(Elemento.ServidorAutenticacion);
        //servidor de correo
        this.crearPermisoOpenSGSManagedBean(Elemento.ServidorCorreo);
        //anuncio
        this.crearPermisoOpenSGSManagedBean(Elemento.Anuncio);
        //reporte
        this.crearPermisoOpenSGSManagedBean(Elemento.Reporte);
        //plantilla de correo
        this.crearPermisoOpenSGSManagedBean(Elemento.PlantillaCorreo);
        //notificacion
        this.crearPermisoOpenSGSManagedBean(Elemento.Notificacion);
        //pregunta frecuente
        this.crearPermisoOpenSGSManagedBean(Elemento.PreguntaFrecuente);
        //aplicacion
        this.crearPermisoOpenSGSManagedBean(Elemento.Aplicacion);
        //archivo
        this.crearPermisoOpenSGSManagedBean(Elemento.Archivo);
        //constancia
        this.crearPermisoOpenSGSManagedBean(Elemento.Constancia);
        this.crearPermisoOpenSGSManagedBean(Elemento.ConstanciaElementoImagen);
        this.crearPermisoOpenSGSManagedBean(Elemento.ConstanciaElementoTexto);
        //plantilla de aplicacion
        this.crearPermisoOpenSGSManagedBean(Elemento.PlantillaAplicacion);
        //pagina de aplicacion
        this.crearPermisoOpenSGSManagedBean(Elemento.PaginaAplicacion);
        //solicitud
        this.crearPermisoOpenSGSManagedBean(Elemento.Solicitud);
        this.crearPermiso(Elemento.Solicitud, Operacion.VerMisSolicitudes);
        this.crearPermiso(Elemento.Solicitud, Operacion.VerMisConstancias);
        this.crearPermiso(Elemento.Solicitud, Operacion.ModificarMiSolicitud);
        this.crearPermiso(Elemento.Solicitud, Operacion.EntregarMiSolicitud);
        this.crearPermiso(Elemento.Solicitud, Operacion.Entregar);
        this.crearPermiso(Elemento.Solicitud, Operacion.NoEntregar);
        this.crearPermiso(Elemento.Solicitud, Operacion.Financiar);
        this.crearPermiso(Elemento.Solicitud, Operacion.NoFinanciar);
        this.crearPermiso(Elemento.Solicitud, Operacion.Cursar);
        this.crearPermiso(Elemento.Solicitud, Operacion.NoCursar);
        this.crearPermiso(Elemento.Solicitud, Operacion.Aprobar);
        this.crearPermiso(Elemento.Solicitud, Operacion.NoAprobar);
    }

    private void crearPerfilSistema() throws Exception {
        boolean nuevo = false;

        Perfil perfilSistema = (Perfil) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new Perfil(), "nombre", "Sistema");
        if (perfilSistema == null) {
            nuevo = true;
            perfilSistema = new Perfil();
            perfilSistema.setNombre("Sistema");
            perfilSistema.setAlcancePerfil(AlcancePerfil.GLOBAL);
            perfilSistema.setAdministrable(false);
        }

        perfilSistema.agregarPermiso(Elemento.Sistema, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.Perfil, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.PerfilAplicacion, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.Usuario, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.Actividad, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.Permiso, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.ServidorAplicaciones, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.ServidorAutenticacion, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.ServidorCorreo, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.Anuncio, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.Reporte, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.PlantillaCorreo, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.Notificacion, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.PreguntaFrecuente, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.Aplicacion, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.Destinatario, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.Archivo, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.Constancia, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.ConstanciaElementoImagen, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.ConstanciaElementoTexto, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.PlantillaAplicacion, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.PaginaAplicacion, Operacion.Todas);
        perfilSistema.agregarPermiso(Elemento.Solicitud, Operacion.Todas);
        if (nuevo) {
            ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(perfilSistema);
        } else {
            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(perfilSistema);
        }
        this.setPerfilSistema(perfilSistema);
    }

    private void crearPerfilAdministrador() throws Exception {
        boolean nuevo = false;

        Perfil perfilAdministrador = (Perfil) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new Perfil(), "nombre", "Administrador");
        if (perfilAdministrador == null) {
            nuevo = true;
            perfilAdministrador = new Perfil();
            perfilAdministrador.setNombre("Administrador");
            perfilAdministrador.setAlcancePerfil(AlcancePerfil.GLOBAL);
            perfilAdministrador.setAdministrable(false);
        }

        perfilAdministrador.agregarPermiso(Elemento.Perfil, Operacion.Todas);
        perfilAdministrador.agregarPermiso(Elemento.PerfilAplicacion, Operacion.Todas);
        perfilAdministrador.agregarPermiso(Elemento.Usuario, Operacion.Todas);
        perfilAdministrador.agregarPermiso(Elemento.Actividad, Operacion.Todas);
        perfilAdministrador.agregarPermiso(Elemento.Permiso, Operacion.Todas);
        perfilAdministrador.agregarPermiso(Elemento.Anuncio, Operacion.Todas);
        perfilAdministrador.agregarPermiso(Elemento.Reporte, Operacion.Todas);
        perfilAdministrador.agregarPermiso(Elemento.PlantillaCorreo, Operacion.Todas);
        perfilAdministrador.agregarPermiso(Elemento.Notificacion, Operacion.Todas);
        perfilAdministrador.agregarPermiso(Elemento.PreguntaFrecuente, Operacion.Todas);
        perfilAdministrador.agregarPermiso(Elemento.Aplicacion, Operacion.Todas);
        perfilAdministrador.agregarPermiso(Elemento.Destinatario, Operacion.Todas);
        perfilAdministrador.agregarPermiso(Elemento.Archivo, Operacion.Todas);
        perfilAdministrador.agregarPermiso(Elemento.Constancia, Operacion.Todas);
        perfilAdministrador.agregarPermiso(Elemento.ConstanciaElementoImagen, Operacion.Todas);
        perfilAdministrador.agregarPermiso(Elemento.ConstanciaElementoTexto, Operacion.Todas);
        perfilAdministrador.agregarPermiso(Elemento.PlantillaAplicacion, Operacion.Todas);
        perfilAdministrador.agregarPermiso(Elemento.PaginaAplicacion, Operacion.Todas);
        perfilAdministrador.agregarPermiso(Elemento.Solicitud, Operacion.Todas);
        if (nuevo) {
            ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(perfilAdministrador);
        } else {
            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(perfilAdministrador);
        }

    }

    private void crearPerfilUsuario() throws Exception {
        boolean nuevo = false;

        Perfil perfilUsuario = (Perfil) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new Perfil(), "nombre", "Usuario");
        if (perfilUsuario == null) {
            nuevo = true;
            perfilUsuario = new Perfil();
            perfilUsuario.setNombre("Usuario");
            perfilUsuario.setAlcancePerfil(AlcancePerfil.GLOBAL);
            perfilUsuario.setAdministrable(false);
        }

        perfilUsuario.agregarPermiso(Elemento.Usuario, Operacion.VerMisDatos);
        perfilUsuario.agregarPermiso(Elemento.Usuario, Operacion.ModificarMisDatos);
        perfilUsuario.agregarPermiso(Elemento.Usuario, Operacion.ModificarMiContraseña);
        //QUITAR Problema en evaluar pagina de PaginaSolicitudAction
        perfilUsuario.agregarPermiso(Elemento.Solicitud, Operacion.Ver);
        //
        perfilUsuario.agregarPermiso(Elemento.Solicitud, Operacion.VerMisSolicitudes);
        perfilUsuario.agregarPermiso(Elemento.Solicitud, Operacion.Crear);
        perfilUsuario.agregarPermiso(Elemento.Solicitud, Operacion.VerMisConstancias);
        perfilUsuario.agregarPermiso(Elemento.Solicitud, Operacion.ModificarMiSolicitud);
        perfilUsuario.agregarPermiso(Elemento.Solicitud, Operacion.EntregarMiSolicitud);
        if (nuevo) {
            ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(perfilUsuario);
        } else {
            ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(perfilUsuario);
        }
    }

}
