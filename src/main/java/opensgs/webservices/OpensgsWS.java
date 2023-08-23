package opensgs.webservices;

import java.util.List;
import javax.activation.DataHandler;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.soap.MTOM;
import opensgs.datatypes.DtMensaje;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.sistema.datatypes.DtSoporte;
import opensgs.logica.fabricas.FabricaOpenSGSBean;
import opensgs.interfaces.IControladorOpenSGSBean;
import opensgs.sistema.logica.fabricas.FabricaSoporte;
import opensgs.sistema.datatypes.DtActividad;
import opensgs.sistema.datatypes.DtAnuncio;
import opensgs.sistema.datatypes.DtAplicacion;
import opensgs.sistema.datatypes.DtArchivo;
import opensgs.sistema.datatypes.DtConstancia;
import opensgs.sistema.datatypes.DtConstanciaElementoImagen;
import opensgs.sistema.datatypes.DtConstanciaElementoTexto;
import opensgs.sistema.datatypes.DtDestinatario;
import opensgs.sistema.datatypes.DtNotificacion;
import opensgs.sistema.datatypes.DtPlantillaCorreo;
import opensgs.sistema.datatypes.DtPreguntaFrecuente;
import opensgs.sistema.datatypes.DtReporte;
import opensgs.sistema.datatypes.DtServidorAplicaciones;
import opensgs.sistema.datatypes.DtServidorAutenticacion;
import opensgs.sistema.datatypes.DtServidorCorreo;
import opensgs.sistema.datatypes.DtSistema;
import opensgs.sistema.interfaces.IControladorActividad;
import opensgs.sistema.interfaces.IControladorAnuncio;
import opensgs.sistema.interfaces.IControladorAplicacion;
import opensgs.sistema.interfaces.IControladorArchivo;
import opensgs.sistema.interfaces.IControladorNotificacion;
import opensgs.sistema.interfaces.IControladorPreguntaFrecuente;
import opensgs.sistema.interfaces.IControladorReporte;
import opensgs.sistema.interfaces.IControladorServidorCorreo;
import opensgs.sistema.interfaces.IControladorSoporte;
import opensgs.sistema.logica.fabricas.FabricaActividad;
import opensgs.sistema.logica.fabricas.FabricaAnuncio;
import opensgs.sistema.logica.fabricas.FabricaAplicacion;
import opensgs.sistema.logica.fabricas.FabricaArchivo;
import opensgs.sistema.logica.fabricas.FabricaNotificacion;
import opensgs.sistema.logica.fabricas.FabricaPreguntaFrecuente;
import opensgs.sistema.logica.fabricas.FabricaReporte;
import opensgs.sistema.logica.fabricas.FabricaServidorCorreo;
import opensgs.sistema.datatypes.DtPaginaAplicacion;
import opensgs.sistema.datatypes.DtPlantillaAplicacion;
import opensgs.sistema.interfaces.IControladorConstancia;
import opensgs.sistema.interfaces.IControladorConstanciaElementoImagen;
import opensgs.sistema.interfaces.IControladorConstanciaElementoTexto;
import opensgs.sistema.interfaces.IControladorPaginaAplicacion;
import opensgs.sistema.logica.fabricas.FabricaConstancia;
import opensgs.sistema.logica.fabricas.FabricaConstanciaElementoImagen;
import opensgs.sistema.logica.fabricas.FabricaConstanciaElementoTexto;
import opensgs.sistema.logica.fabricas.FabricaPaginaAplicacion;
import opensgs.solicitudes.datatypes.DtEstadoSolicitud;
import opensgs.solicitudes.datatypes.DtPaginaSolicitud;
import opensgs.solicitudes.datatypes.DtSolicitud;
import opensgs.solicitudes.interfaces.IControladorPaginaSolicitud;
import opensgs.solicitudes.interfaces.IControladorSolicitud;
import opensgs.solicitudes.logica.fabricas.FabricaPaginaSolicitud;
import opensgs.solicitudes.logica.fabricas.FabricaSolicitud;
import opensgs.solicitudes.paginas.datatypes.cep.DtDatosPersonalesCEP;
import opensgs.solicitudes.paginas.datatypes.cep.DtDifusionCEP;
import opensgs.solicitudes.paginas.datatypes.cep.DtEducacionCEP;
import opensgs.solicitudes.paginas.datatypes.cep.DtSituacionLaboralCEP;
import opensgs.usuarios.datatypes.DtPerfil;
import opensgs.usuarios.datatypes.DtPermiso;
import opensgs.usuarios.datatypes.DtRegistrarUsuario;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.datatypes.DtUsuario;
import opensgs.usuarios.interfaces.IControladorPerfil;
import opensgs.usuarios.logica.fabricas.FabricaUsuario;
import opensgs.usuarios.interfaces.IControladorUsuario;
import opensgs.usuarios.logica.fabricas.FabricaPerfil;

@MTOM
@WebService(serviceName = "OpensgsWS")
//@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
//@SOAPBinding(style = Style.DOCUMENT)
public class OpensgsWS {

    private IControladorOpenSGSBean iControladorOpenSGSBean;
    private IControladorUsuario iControladorUsuario;
    private IControladorPerfil iControladorPerfil;
    private IControladorActividad iControladorActividad;
    private IControladorSoporte iControladorSoporte;
    private IControladorPreguntaFrecuente iControladorPreguntaFrecuente;
    private IControladorNotificacion iControladorNotificacion;
    private IControladorAplicacion iControladorAplicacion;
    private IControladorAnuncio iControladorAnuncio;
    private IControladorServidorCorreo iControladorServidorCorreo;
    private IControladorReporte iControladorReporte;
    private IControladorArchivo iControladorArchivo;
    private IControladorPaginaAplicacion iControladorPaginaAplicacion;
    private IControladorSolicitud iControladorSolicitud;
    private IControladorPaginaSolicitud iControladorPaginaSolicitud;
    private IControladorConstancia iControladorConstancia;
    private IControladorConstanciaElementoImagen iControladorConstanciaElementoImagen;
    private IControladorConstanciaElementoTexto iControladorConstanciaElementoTexto;

    public OpensgsWS() {
        iControladorOpenSGSBean = FabricaOpenSGSBean.getIControladorOpenSGSBean();
        iControladorUsuario = FabricaUsuario.getIControladorUsuario();
        iControladorPerfil = FabricaPerfil.getIControladorPerfil();
        iControladorActividad = FabricaActividad.getIControladorActividad();
        iControladorSoporte = FabricaSoporte.getIControladorSoporte();
        iControladorPreguntaFrecuente = FabricaPreguntaFrecuente.getIControladorPreguntaFrecuente();
        iControladorNotificacion = FabricaNotificacion.getIControladorNotificacion();
        iControladorAplicacion = FabricaAplicacion.getIControladorAplicacion();
        iControladorAnuncio = FabricaAnuncio.getIControladorAnuncio();
        iControladorServidorCorreo = FabricaServidorCorreo.getIControladorServidorCorreo();
        iControladorReporte = FabricaReporte.getIControladorReporte();
        iControladorArchivo = FabricaArchivo.getIControladorArchivo();
        iControladorPaginaAplicacion = FabricaPaginaAplicacion.getIControladorPaginaAplicacion();
        iControladorSolicitud = FabricaSolicitud.getIControladorSolicitud();
        iControladorPaginaSolicitud = FabricaPaginaSolicitud.getIControladorPaginaSolicitud();
        iControladorConstancia = FabricaConstancia.getIControladorConstancia();
        iControladorConstanciaElementoImagen = FabricaConstanciaElementoImagen.getIControladorConstanciaElementoImagen();
        iControladorConstanciaElementoTexto = FabricaConstanciaElementoTexto.getIControladorConstanciaElementoTexto();
    }

    @WebMethod()
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    //DATATYPES USUARIOS
    @WebMethod()
    public DtPerfil getDtPerfil() {
        return new DtPerfil();
    }

    @WebMethod()
    public DtPermiso getDtPermiso() {
        return new DtPermiso();
    }

    @WebMethod()
    public DtSesion getDtSesion() {
        return new DtSesion();
    }

    @WebMethod()
    public DtUsuario getDtUsuario() {
        return new DtUsuario();
    }

    //DATATYPES SISTEMA
    @WebMethod()
    public DtActividad getDtActividad() {
        return new DtActividad();
    }

    @WebMethod()
    public DtAnuncio getDtAnuncio() {
        return new DtAnuncio();
    }

    @WebMethod()
    public DtAplicacion getDtAplicacion() {
        return new DtAplicacion();
    }

    @WebMethod()
    public DtArchivo getDtArchivo() {
        return new DtArchivo();
    }

    @WebMethod()
    public DtNotificacion getDtNotificacion() {
        return new DtNotificacion();
    }

    @WebMethod()
    public DtDestinatario getDtDestinatario() {
        return new DtDestinatario();
    }

    @WebMethod()
    public DtPlantillaCorreo getDtPlantillaCorreo() {
        return new DtPlantillaCorreo();
    }

    @WebMethod()
    public DtPreguntaFrecuente getDtPreguntaFrecuente() {
        return new DtPreguntaFrecuente();
    }

    @WebMethod()
    public DtReporte getDtReporte() {
        return new DtReporte();
    }

    @WebMethod()
    public DtServidorAplicaciones getDtServidorAplicaciones() {
        return new DtServidorAplicaciones();
    }

    @WebMethod()
    public DtServidorAutenticacion getDtServidorAutenticacion() {
        return new DtServidorAutenticacion();
    }

    @WebMethod()
    public DtServidorCorreo getDtServidorCorreo() {
        return new DtServidorCorreo();
    }

    @WebMethod()
    public DtSistema getDtSistema() {
        return new DtSistema();
    }

    @WebMethod()
    public DtSoporte getDtSoporte() {
        return new DtSoporte();
    }

    @WebMethod()
    public DtPaginaAplicacion getDtPaginaAplicacion() {
        return new DtPaginaAplicacion();
    }

    @WebMethod()
    public DtPlantillaAplicacion getDtPlantillaAplicacion() {
        return new DtPlantillaAplicacion();
    }

    @WebMethod()
    public DtConstancia getDtConstancia() {
        return new DtConstancia();
    }

    @WebMethod()
    public DtConstanciaElementoImagen getDtConstanciaElementoImagen() {
        return new DtConstanciaElementoImagen();
    }

    @WebMethod()
    public DtConstanciaElementoTexto getDtConstanciaElementoTexto() {
        return new DtConstanciaElementoTexto();
    }

    //DATATYPES SOLICITUDES
    @WebMethod()
    public DtSolicitud getDtSolicitud() {
        return new DtSolicitud();
    }

    //PAGINAS CEP
    @WebMethod()
    public DtDatosPersonalesCEP getDtDatosPersonalesCEP() {
        return new DtDatosPersonalesCEP();
    }

    @WebMethod()
    public DtDifusionCEP getDtDifusionCEP() {
        return new DtDifusionCEP();
    }

    @WebMethod()
    public DtEducacionCEP getDtEducacionCEP() {
        return new DtEducacionCEP();
    }

    @WebMethod()
    public DtSituacionLaboralCEP getDtSituacionLaboralCEP() {
        return new DtSituacionLaboralCEP();
    }

    //OPENSGSBEAN
    @WebMethod(operationName = "obtenerOpenSGSBean")
    public DtOpenSGSBean obtenerOpenSGSBean(String className, Long id, DtSesion dtSesion) {
        return iControladorOpenSGSBean.obtenerOpenSGSBean(className, id, dtSesion);
    }

    @WebMethod(operationName = "obtenerOpenSGSBeanAplicacion")
    public DtOpenSGSBean obtenerOpenSGSBeanAplicacion(String className, Long id, Long aplicacionId, DtSesion dtSesion) {
        return iControladorOpenSGSBean.obtenerOpenSGSBeanAplicacion(className, id, aplicacionId, dtSesion);
    }

    @WebMethod(operationName = "obtenerOpenSGSBeanParametroValor")
    public DtOpenSGSBean obtenerOpenSGSBeanParametroValor(String className, String parametro, String valor, DtSesion dtSesion) {
        return iControladorOpenSGSBean.obtenerOpenSGSBean(className, parametro, valor, dtSesion);
    }

    @WebMethod(operationName = "listarOpenSGSBean")
    public List<DtOpenSGSBean> listarOpenSGSBean(String className, DtSesion dtSesion) {
        return iControladorOpenSGSBean.listarOpenSGSBean(className, dtSesion);
    }

    @WebMethod(operationName = "listarOpenSGSManagedBean")
    public List<DtOpenSGSManagedBean> listarOpenSGSManagedBean(String className, boolean borrado, DtSesion dtSesion) {
        return iControladorOpenSGSBean.listarOpenSGSManagedBean(className, borrado, dtSesion);
    }

    @WebMethod(operationName = "listarOpenSGSManagedBeanParametroValor")
    public List<DtOpenSGSManagedBean> listarOpenSGSManagedBeanParametroValor(String className, String parametro, String valor, boolean borrado, DtSesion dtSesion) {
        return iControladorOpenSGSBean.listarOpenSGSManagedBean(className, parametro, valor, borrado, dtSesion);
    }

    @WebMethod(operationName = "listarOpenSGSManagedBeanParametroValorLong")
    public List<DtOpenSGSManagedBean> listarOpenSGSManagedBeanParametroValorLong(String className, String parametro, Long valor, boolean borrado, DtSesion dtSesion) {
        return iControladorOpenSGSBean.listarOpenSGSManagedBean(className, parametro, valor, borrado, dtSesion);
    }

    @WebMethod(operationName = "listarOpenSGSManagedBeanActivos")
    public List<DtOpenSGSManagedBean> listarOpenSGSManagedBeanActivos(String className, DtSesion dtSesion) {
        return iControladorOpenSGSBean.listarOpenSGSManagedBeanActivos(className, dtSesion);
    }

    @WebMethod(operationName = "listarOpenSGSManagedBeanActivosParametroValor")
    public List<DtOpenSGSManagedBean> listarOpenSGSManagedBeanActivosParametroValor(String className, String parametro, String valor, DtSesion dtSesion) {
        return iControladorOpenSGSBean.listarOpenSGSManagedBeanActivos(className, parametro, valor, dtSesion);
    }

    @WebMethod(operationName = "listarOpenSGSManagedBeanActivosParametroValorLong")
    public List<DtOpenSGSManagedBean> listarOpenSGSManagedBeanActivosParametroValorLong(String className, String parametro, Long valor, DtSesion dtSesion) {
        return iControladorOpenSGSBean.listarOpenSGSManagedBeanActivos(className, parametro, valor, dtSesion);
    }

    @WebMethod(operationName = "listarEnum")
    public List<String> listarEnum(String enumName, DtSesion dtSesion) {
        return iControladorOpenSGSBean.listarEnum(enumName, dtSesion);
    }

    @WebMethod(operationName = "crearOpenSGSManagedBean")
    public DtMensaje crearOpenSGSBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtSesion dtSesion) {
        return iControladorOpenSGSBean.crearOpenSGSManagedBean(dtOpenSGSManagedBean, dtSesion);
    }

    @WebMethod(operationName = "modificarOpenSGSManagedBean")
    public DtMensaje modificarOpenSGSBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtSesion dtSesion) {
        return iControladorOpenSGSBean.modificarOpenSGSManagedBean(dtOpenSGSManagedBean, dtSesion);
    }

    @WebMethod(operationName = "modificarOpenSGSManagedBeanAplicacion")
    public DtMensaje modificarOpenSGSManagedBeanAplicacion(DtOpenSGSManagedBean dtOpenSGSManagedBean, Long aplicacionId, DtSesion dtSesion) {
        return iControladorOpenSGSBean.modificarOpenSGSManagedBeanAplicacion(dtOpenSGSManagedBean, aplicacionId, dtSesion);
    }

    @WebMethod(operationName = "borrarOpenSGSManagedBean")
    public DtMensaje borrarOpenSGSBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtSesion dtSesion) {
        return iControladorOpenSGSBean.borrarOpenSGSManagedBean(dtOpenSGSManagedBean, dtSesion);
    }

    @WebMethod(operationName = "borrarOpenSGSManagedBeanAplicacion")
    public DtMensaje borrarOpenSGSManagedBeanAplicacion(DtOpenSGSManagedBean dtOpenSGSManagedBean, Long aplicacionId, DtSesion dtSesion) {
        return iControladorOpenSGSBean.borrarOpenSGSManagedBeanAplicacion(dtOpenSGSManagedBean, aplicacionId, dtSesion);
    }

    @WebMethod(operationName = "restaurarOpenSGSManagedBean")
    public DtMensaje restaurarOpenSGSBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtSesion dtSesion) {
        return iControladorOpenSGSBean.restaurarOpenSGSManagedBean(dtOpenSGSManagedBean, dtSesion);
    }

    @WebMethod(operationName = "restaurarOpenSGSManagedBeanAplicacion")
    public DtMensaje restaurarOpenSGSManagedBeanAplicacion(DtOpenSGSManagedBean dtOpenSGSManagedBean, Long aplicacionId, DtSesion dtSesion) {
        return iControladorOpenSGSBean.restaurarOpenSGSManagedBeanAplicacion(dtOpenSGSManagedBean, aplicacionId, dtSesion);
    }

    @WebMethod(operationName = "desactivarOpenSGSManagedBean")
    public DtMensaje desactivarOpenSGSBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtSesion dtSesion) {
        return iControladorOpenSGSBean.desactivarOpenSGSManagedBean(dtOpenSGSManagedBean, dtSesion);
    }

    @WebMethod(operationName = "desactivarOpenSGSManagedBeanAplicacion")
    public DtMensaje desactivarOpenSGSManagedBeanAplicacion(DtOpenSGSManagedBean dtOpenSGSManagedBean, Long aplicacionId, DtSesion dtSesion) {
        return iControladorOpenSGSBean.desactivarOpenSGSManagedBeanAplicacion(dtOpenSGSManagedBean, aplicacionId, dtSesion);
    }

    @WebMethod(operationName = "activarOpenSGSManagedBean")
    public DtMensaje activarOpenSGSBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtSesion dtSesion) {
        return iControladorOpenSGSBean.activarOpenSGSManagedBean(dtOpenSGSManagedBean, dtSesion);
    }

    @WebMethod(operationName = "activarOpenSGSManagedBeanAplicacion")
    public DtMensaje activarOpenSGSManagedBeanAplicacion(DtOpenSGSManagedBean dtOpenSGSManagedBean, Long aplicacionId, DtSesion dtSesion) {
        return iControladorOpenSGSBean.activarOpenSGSManagedBeanAplicacion(dtOpenSGSManagedBean, aplicacionId, dtSesion);
    }

    @WebMethod(operationName = "agregarOpenSGSBean")
    public DtMensaje agregarOpenSGSBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtOpenSGSBean elemento, DtSesion dtSesion) {
        return iControladorOpenSGSBean.agregarOpenSGSBean(dtOpenSGSManagedBean, elemento, dtSesion);
    }

    @WebMethod(operationName = "quitarOpenSGSBean")
    public DtMensaje quitarOpenSGSBean(DtOpenSGSManagedBean dtOpenSGSManagedBean, DtOpenSGSBean elemento, DtSesion dtSesion) {
        return iControladorOpenSGSBean.quitarOpenSGSBean(dtOpenSGSManagedBean, elemento, dtSesion);
    }

    //USUARIO
    @WebMethod(operationName = "autenticarUsuario")
    public DtMensaje autenticarUsuario(String usuario, String password) {
        return iControladorUsuario.autenticarUsuario(usuario, password);
    }

    @WebMethod(operationName = "iniciarSesion")
    public DtSesion iniciarSesion(String usuario, String password) {
        return iControladorUsuario.iniciarSesion(usuario, password);
    }

    @WebMethod(operationName = "cerrarSesion")
    public DtMensaje cerrarSesion(DtSesion dtSesion) {
        return iControladorUsuario.cerrarSesion(dtSesion);
    }

    @WebMethod(operationName = "obtenerMisDatos")
    public DtUsuario obtenerMisDatos(DtSesion dtSesion) {
        return iControladorUsuario.obtenerMisDatos(dtSesion);
    }

    @WebMethod(operationName = "modificarMisDatos")
    public DtMensaje modificarMisDatos(DtUsuario dtUsuario, DtSesion dtSesion) {
        return iControladorUsuario.modificarMisDatos(dtUsuario, dtSesion);
    }

    @WebMethod(operationName = "modificarMiClave")
    public DtMensaje modificarMiClave(String passwordActual, String passwordNuevo, DtSesion dtSesion) {
        return iControladorUsuario.modificarMiClave(passwordActual, passwordNuevo, dtSesion);
    }

    @WebMethod(operationName = "recuperarMiClave")
    public DtMensaje recuperarMiClave(String identificacion) {
        return iControladorUsuario.recuperarMiClave(identificacion);
    }

    @WebMethod(operationName = "registrarUsuario")
    public DtMensaje registrarUsuario(DtRegistrarUsuario dtRegistrarUsuario) {
        return iControladorUsuario.registrarUsuario(dtRegistrarUsuario);
    }

    @WebMethod(operationName = "agregarPerfilAplicacion")
    public DtMensaje agregarPerfilAplicacion(DtUsuario dtUsuario, Long perfilId, Long aplicacionId, DtSesion dtSesion) {
        return iControladorUsuario.agregarPerfilAplicacion(dtUsuario, perfilId, aplicacionId, dtSesion);
    }

    //PERFIL
    @WebMethod(operationName = "listarPerfilesGlobales")
    public List<DtPerfil> listarPerfilesGlobales(DtSesion dtSesion) {
        return iControladorPerfil.listarPerfilesGlobales(dtSesion);
    }

    @WebMethod(operationName = "listarPerfilesAplicacion")
    public List<DtPerfil> listarPerfilesAplicacion(DtSesion dtSesion) {
        return iControladorPerfil.listarPerfilesAplicacion(dtSesion);
    }

    //REPORTES
    @WebMethod(operationName = "crearReporteAplicacion")
    public DtMensaje crearReporteAplicacion(DtReporte dtReporte, Long aplicacionId, DtSesion dtSesion) {
        return iControladorReporte.crearReporteAplicacion(dtReporte, aplicacionId, dtSesion);
    }

    @WebMethod(operationName = "listarReportesSistema")
    public List<DtReporte> listarReportesSistema(boolean borrado, DtSesion dtSesion) {
        return iControladorReporte.listarReportesSistema(borrado, dtSesion);
    }

    @WebMethod(operationName = "listarReportesAplicacion")
    public List<DtReporte> listarReportesAplicacion(Long aplicacionId, boolean borrado, DtSesion dtSesion) {
        return iControladorReporte.listarReportesAplicacion(aplicacionId, borrado, dtSesion);
    }

    @WebMethod(operationName = "listarReportesActivos")
    public List<DtReporte> listarReportesActivos(DtSesion dtSesion) {
        return iControladorReporte.listarReportesActivos(dtSesion);
    }

    @XmlMimeType("application/octet-stream")
    @WebMethod(operationName = "descargarReporteCSV")
    public DataHandler descargarReporteCSV(Long id, DtSesion dtSesion) {
        return iControladorReporte.descargarReporteCSV(id, dtSesion);
    }

    @XmlMimeType("application/octet-stream")
    @WebMethod(operationName = "descargarReporteCSVAplicacion")
    public DataHandler descargarReporteCSVAplicacion(Long id, Long aplicacionId, DtSesion dtSesion) {
        return iControladorReporte.descargarReporteCSVAplicacion(id, aplicacionId, dtSesion);
    }

    //ACTIVIDAD
    @WebMethod(operationName = "listarActividadElemento")
    public List<DtActividad> listarActividadElemento(String elemento, Long identificador, DtSesion dtSesion) {
        return iControladorActividad.listarActividadElemento(elemento, identificador, dtSesion);
    }

    @WebMethod(operationName = "listarActividadElementoAplicacion")
    public List<DtActividad> listarActividadElementoAplicacion(String elemento, Long identificador, Long aplicacionId, DtSesion dtSesion) {
        return iControladorActividad.listarActividadElementoAplicacion(elemento, identificador, aplicacionId, dtSesion);
    }

    @WebMethod(operationName = "listarActividadTemporal")
    public List<DtActividad> listarActividadTemporal(String inicioString, String finString, DtSesion dtSesion) {
        return iControladorActividad.listarActividadTemporal(inicioString, finString, dtSesion);
    }

    //SOPORTE
    @WebMethod(operationName = "enviarSoporte")
    public DtMensaje enviarSoporte(DtSoporte dtSoporte) {
        return iControladorSoporte.enviarSoporte(dtSoporte);
    }

    //PREGUNTAS FRECUENTES
    @WebMethod(operationName = "crearPreguntaFrecuenteAplicacion")
    public DtMensaje crearPreguntaFrecuenteAplicacion(DtPreguntaFrecuente dtPreguntaFrecuente, Long aplicacionId, DtSesion dtSesion) {
        return iControladorPreguntaFrecuente.crearPreguntaFrecuenteAplicacion(dtPreguntaFrecuente, aplicacionId, dtSesion);
    }

    @WebMethod(operationName = "listarPreguntasSistema")
    public List<DtPreguntaFrecuente> listarPreguntasSistema(boolean borrado, DtSesion dtSesion) {
        return iControladorPreguntaFrecuente.listarPreguntasSistema(borrado, dtSesion);
    }

    @WebMethod(operationName = "listarPreguntasAplicacion")
    public List<DtPreguntaFrecuente> listarPreguntasAplicacion(Long aplicacionId, boolean borrado, DtSesion dtSesion) {
        return iControladorPreguntaFrecuente.listarPreguntasAplicacion(aplicacionId, borrado, dtSesion);
    }

    @WebMethod(operationName = "listarPreguntasSistemaActivas")
    public List<DtPreguntaFrecuente> listarPreguntasSistemaActivas() {
        return iControladorPreguntaFrecuente.listarPreguntasSistemaActivas();
    }

    @WebMethod(operationName = "listarPreguntasAplicacionActivas")
    public List<DtPreguntaFrecuente> listarPreguntasAplicacionActivas(Long aplicacionId, DtSesion dtSesion) {
        return iControladorPreguntaFrecuente.listarPreguntasAplicacionActivas(aplicacionId, dtSesion);
    }

    @WebMethod(operationName = "listarPreguntasFrecuentesActivas")
    public List<DtPreguntaFrecuente> listarPreguntasFrecuentesActivas(DtSesion dtSesion) {
        return iControladorPreguntaFrecuente.listarPreguntasFrecuentesActivas(dtSesion);
    }

    //NOTIFICACION
    @WebMethod(operationName = "crearNotificacionAplicacion")
    public DtMensaje crearNotificacionAplicacion(DtNotificacion dtNotificacion, Long aplicacionId, DtSesion dtSesion) {
        return iControladorNotificacion.crearNotificacionAplicacion(dtNotificacion, aplicacionId, dtSesion);
    }

    @WebMethod(operationName = "listarNotificacionesSistema")
    public List<DtNotificacion> listarNotificacionesSistema(boolean borrado, DtSesion dtSesion) {
        return iControladorNotificacion.listarNotificacionesSistema(borrado, dtSesion);
    }

    @WebMethod(operationName = "listarNotificacionesAplicacion")
    public List<DtNotificacion> listarNotificacionesAplicacion(Long aplicacionId, boolean borrado, DtSesion dtSesion) {
        return iControladorNotificacion.listarNotificacionesAplicacion(aplicacionId, borrado, dtSesion);
    }

    @WebMethod(operationName = "agregarDestinatario")
    public DtMensaje agregarDestinatario(DtNotificacion dtNotificacion, String email, DtSesion dtSesion) {
        return iControladorNotificacion.agregarDestinatario(dtNotificacion, email, dtSesion);
    }

    @WebMethod(operationName = "listarNotificacionesActivas")
    public List<DtNotificacion> listarNotificacionesActivas(DtSesion dtSesion) {
        return iControladorNotificacion.listarNotificacionesActivas(dtSesion);
    }

    //APLICACION
    @WebMethod(operationName = "listarAplicacionesAbiertas")
    public List<DtAplicacion> listarAplicacionesAbiertas() {
        return iControladorAplicacion.listarAplicacionesAbiertas();
    }

    @WebMethod(operationName = "listarAplicacionesCerradas")
    public List<DtAplicacion> listarAplicacionesCerradas() {
        return iControladorAplicacion.listarAplicacionesCerradas();
    }

    @WebMethod(operationName = "listarAplicacionesProximas")
    public List<DtAplicacion> listarAplicacionesProximas() {
        return iControladorAplicacion.listarAplicacionesProximas();
    }

    @WebMethod(operationName = "obtenerDatosBasicosAplicacion")
    public DtAplicacion obtenerDatosBasicosAplicacion(Long aplicacionId, DtSesion dtSesion) {
        return iControladorAplicacion.obtenerDatosBasicosAplicacion(aplicacionId, dtSesion);
    }

    @WebMethod(operationName = "listarAplicacionesActivas")
    public List<DtAplicacion> listarAplicacionesActivas() {
        return iControladorAplicacion.listarAplicacionesActivas();
    }

    //ANUNCIO
    @WebMethod(operationName = "crearAnuncioAplicacion")
    public DtMensaje crearAnuncioAplicacion(DtAnuncio dtAnuncio, Long aplicacionId, DtSesion dtSesion) {
        return iControladorAnuncio.crearAnuncioAplicacion(dtAnuncio, aplicacionId, dtSesion);
    }

    @WebMethod(operationName = "listarAnunciosSistema")
    public List<DtAnuncio> listarAnunciosSistema(boolean borrado, DtSesion dtSesion) {
        return iControladorAnuncio.listarAnunciosSistema(borrado, dtSesion);
    }

    @WebMethod(operationName = "listarAnunciosAplicacion")
    public List<DtAnuncio> listarAnunciosAplicacion(Long aplicacionId, boolean borrado, DtSesion dtSesion) {
        return iControladorAnuncio.listarAnunciosAplicacion(aplicacionId, borrado, dtSesion);
    }

    @WebMethod(operationName = "listarAnunciosSistemaVigentes")
    public List<DtAnuncio> listarAnunciosSistemaVigentes() {
        return iControladorAnuncio.listarAnunciosSistemaVigentes();
    }

    @WebMethod(operationName = "listarAnunciosAplicacionVigentes")
    public List<DtAnuncio> listarAnunciosAplicacionVigentes(Long aplicacionId, DtSesion dtSesion) {
        return iControladorAnuncio.listarAnunciosAplicacionVigentes(aplicacionId, dtSesion);
    }

    @WebMethod(operationName = "listarAnunciosActivos")
    public List<DtAnuncio> listarAnunciosActivos(DtSesion dtSesion) {
        return iControladorAnuncio.listarAnunciosAplicacionesVigentes(dtSesion);
    }

    //SERVIDOR CORREO
    @WebMethod(operationName = "probarEnvioCorreo")
    public DtMensaje probarEnvioCorreo(Long id, String email, DtSesion dtSesion) {
        return iControladorServidorCorreo.probarEnvioCorreo(id, email, dtSesion);
    }

    //ARCHIVO
    @WebMethod(operationName = "crearArchivo")
    public DtMensaje crearArchivo(@XmlMimeType("application/octet-stream") DataHandler archivoSubir, DtArchivo dtArchivo, DtSesion dtSesion) {
        return iControladorArchivo.crearArchivo(archivoSubir, dtArchivo, dtSesion);
    }

    @WebMethod(operationName = "crearArchivoAplicacion")
    public DtMensaje crearArchivoAplicacion(@XmlMimeType("application/octet-stream") DataHandler archivoSubir, DtArchivo dtArchivo, Long aplicacionId, DtSesion dtSesion) {
        return iControladorArchivo.crearArchivoAplicacion(archivoSubir, dtArchivo, aplicacionId, dtSesion);
    }

    @WebMethod(operationName = "modificarArchivo")
    public DtMensaje modificarArchivo(@XmlMimeType("application/octet-stream") DataHandler archivoSubir, DtArchivo dtArchivo, DtSesion dtSesion) {
        return iControladorArchivo.modificarArchivo(archivoSubir, dtArchivo, dtSesion);
    }

    @WebMethod(operationName = "modificarArchivoAplicacion")
    public DtMensaje modificarArchivoAplicacion(@XmlMimeType("application/octet-stream") DataHandler archivoSubir, DtArchivo dtArchivo, Long aplicacionId, DtSesion dtSesion) {
        return iControladorArchivo.modificarArchivoAplicacion(archivoSubir, dtArchivo, aplicacionId, dtSesion);
    }

    @WebMethod(operationName = "listarArchivosSistema")
    public List<DtArchivo> listarArchivosSistema(boolean borrado, DtSesion dtSesion) {
        return iControladorArchivo.listarArchivosSistema(borrado, dtSesion);
    }

    @WebMethod(operationName = "listarArchivosAplicacion")
    public List<DtArchivo> listarArchivosAplicacion(Long aplicacionId, boolean borrado, DtSesion dtSesion) {
        return iControladorArchivo.listarArchivosAplicacion(aplicacionId, borrado, dtSesion);
    }

    @XmlMimeType("application/octet-stream")
    @WebMethod(operationName = "descargarArchivo")
    public DataHandler descargarArchivo(Long id, DtSesion dtSesion) {
        return iControladorArchivo.descargarArchivo(id, dtSesion);
    }

    @XmlMimeType("application/octet-stream")
    @WebMethod(operationName = "descargarArchivoAplicacion")
    public DataHandler descargarArchivoAplicacion(Long id, Long aplicacionId, DtSesion dtSesion) {
        return iControladorArchivo.descargarArchivoAplicacion(id, aplicacionId, dtSesion);
    }

    @XmlMimeType("application/octet-stream")
    @WebMethod(operationName = "descargarArchivoPublico")
    public DataHandler descargarArchivoPublico(Long id) {
        return iControladorArchivo.descargarArchivoPublico(id);
    }

    @WebMethod(operationName = "obtenerDtArchivoPublico")
    public DtArchivo obtenerDtArchivoPublico(Long id) {
        return iControladorArchivo.obtenerDtArchivoPublico(id);
    }

    @WebMethod(operationName = "listarArchivosSistemaActivos")
    public List<DtArchivo> listarArchivosSistemaActivos() {
        return iControladorArchivo.listarArchivosSistemaActivos();
    }

    @WebMethod(operationName = "listarArchivosAplicacionActivos")
    public List<DtArchivo> listarArchivosAplicacionActivos(Long aplicacionId, DtSesion dtSesion) {
        return iControladorArchivo.listarArchivosAplicacionActivos(aplicacionId, dtSesion);
    }

    @WebMethod(operationName = "listarArchivosActivos")
    public List<DtArchivo> listarArchivosActivos(DtSesion dtSesion) {
        return iControladorArchivo.listarArchivosActivos(dtSesion);
    }

    //PAGINA APLICACION    
    @WebMethod(operationName = "listarPaginasAplicacionPlantillaAplicacion")
    public List<DtPaginaAplicacion> listarPaginasAplicacionPlantillaAplicacion(Long plantillaAplicacionIds, boolean borrado, DtSesion dtSesion) {
        return iControladorPaginaAplicacion.listarPaginasAplicacionPlantillaAplicacion(plantillaAplicacionIds, borrado, dtSesion);
    }

    @WebMethod(operationName = "listarPaginasAplicacionPlantillaAplicacionActivas")
    public List<DtPaginaAplicacion> listarPaginasAplicacionPlantillaAplicacionActivas(Long plantillaAplicacionIds, DtSesion dtSesion) {
        return iControladorPaginaAplicacion.listarPaginasAplicacionPlantillaAplicacionActivas(plantillaAplicacionIds, dtSesion);
    }

    //CONSTANCIA
    @WebMethod(operationName = "crearConstancia")
    public DtMensaje crearConstancia(@XmlMimeType("application/octet-stream") DataHandler archivoSubir, DtConstancia dtConstancia, DtSesion dtSesion) {
        return iControladorConstancia.crearConstancia(archivoSubir, dtConstancia, dtSesion);
    }

    @WebMethod(operationName = "modificarConstancia")
    public DtMensaje modificarConstancia(@XmlMimeType("application/octet-stream") DataHandler archivoSubir, DtConstancia dtConstancia, DtSesion dtSesion) {
        return iControladorConstancia.modificarConstancia(archivoSubir, dtConstancia, dtSesion);
    }

    @WebMethod(operationName = "listarConstancias")
    public List<DtConstancia> listarConstancias(Long aplicacionId, boolean borrado, DtSesion dtSesion) {
        return iControladorConstancia.listarConstancias(aplicacionId, borrado, dtSesion);
    }

    @XmlMimeType("application/octet-stream")
    @WebMethod(operationName = "emitirConstancia")
    public DataHandler emitirConstancia(Long id, DtSesion dtSesion) {
        return iControladorConstancia.emitirConstancia(id, dtSesion);
    }

    //CONSTANCIA ELEMENTO IMAGEN
    @WebMethod(operationName = "crearConstanciaElementoImagen")
    public DtMensaje crearConstanciaElementoImagen(@XmlMimeType("application/octet-stream") DataHandler archivoSubir, DtConstanciaElementoImagen dtConstanciaElementoImagen, DtSesion dtSesion) {
        return iControladorConstanciaElementoImagen.crearConstanciaElementoImagen(archivoSubir, dtConstanciaElementoImagen, dtSesion);
    }

    @WebMethod(operationName = "modificarConstanciaElementoImagen")
    public DtMensaje modificarConstanciaElementoImagen(@XmlMimeType("application/octet-stream") DataHandler archivoSubir, DtConstanciaElementoImagen dtConstanciaElementoImagen, DtSesion dtSesion) {
        return iControladorConstanciaElementoImagen.modificarConstanciaElementoImagen(archivoSubir, dtConstanciaElementoImagen, dtSesion);
    }

    @WebMethod(operationName = "listarConstanciaElementoImagen")
    public List<DtConstanciaElementoImagen> listarConstanciaElementoImagen(Long constanciaId, boolean borrado, DtSesion dtSesion) {
        return iControladorConstanciaElementoImagen.listarConstanciaElementoImagen(constanciaId, borrado, dtSesion);
    }

    //CONSTANCIA ELEMENTO TEXTO   
    @WebMethod(operationName = "listarConstanciaElementoTexto")
    public List<DtConstanciaElementoTexto> listarConstanciaElementoTexto(Long constanciaId, boolean borrado, DtSesion dtSesion) {
        return iControladorConstanciaElementoTexto.listarConstanciaElementoTexto(constanciaId, borrado, dtSesion);
    }

    //MIS SOLICITUDES
    @WebMethod(operationName = "obtenerMiSolicitud")
    public DtSolicitud obtenerMiSolicitud(Long solicitudId, DtSesion dtSesion) {
        return iControladorSolicitud.obtenerMiSolicitud(solicitudId, dtSesion);
    }

    @WebMethod(operationName = "listarMisSolicitudes")
    public List<DtSolicitud> listarMisSolicitudes(DtSesion dtSesion) {
        return iControladorSolicitud.listarMisSolicitudes(dtSesion);
    }

    @WebMethod(operationName = "listarConstanciasSolicitud")
    public List<DtConstancia> listarConstanciasSolicitud(Long solicitudId, DtSesion dtSesion) {
        return iControladorSolicitud.listarConstanciasSolicitud(solicitudId, dtSesion);
    }

    @WebMethod(operationName = "entregarMiSolicitud")
    public DtMensaje entregarMiSolicitud(Long solicitudId, DtSesion dtSesion) {
        return iControladorSolicitud.entregarMiSolicitud(solicitudId, dtSesion);
    }

    @WebMethod(operationName = "crearSolicitud")
    public DtMensaje crearSolicitud(Long aplicacionId, DtSesion dtSesion) {
        return iControladorSolicitud.crearSolicitud(aplicacionId, dtSesion);
    }

    @WebMethod(operationName = "modificarMiSolicitud")
    public DtMensaje modificarMiSolicitud(Long solicitudId, DtSesion dtSesion) {
        return iControladorSolicitud.modificarMiSolicitud(solicitudId, dtSesion);
    }

    @XmlMimeType("application/octet-stream")
    @WebMethod(operationName = "emitirConstanciaSolicitud")
    public DataHandler emitirConstanciaSolicitud(Long solicitudId, Long constanciaId, DtSesion dtSesion) {
        return iControladorSolicitud.emitirConstanciaSolicitud(solicitudId, constanciaId, dtSesion);
    }

    //SOLICITUD
    @WebMethod(operationName = "listarSolicitudesAplicacion")
    public List<DtSolicitud> listarSolicitudesAplicacion(Long aplicacionId, boolean borrado, DtSesion dtSesion) {
        return iControladorSolicitud.listarSolicitudesAplicacion(aplicacionId, borrado, dtSesion);
    }

    @WebMethod(operationName = "modificarSolicitud")
    public DtMensaje modificarSolicitud(Long solicitudId, DtSesion dtSesion) {
        return iControladorSolicitud.modificarSolicitud(solicitudId, dtSesion);
    }

    @WebMethod(operationName = "modificarEstadoSolicitud")
    public DtMensaje modificarEstadoSolicitud(DtEstadoSolicitud dtEstadoSolicitud, DtSesion dtSesion) {
        return iControladorSolicitud.modificarEstadoSolicitud(dtEstadoSolicitud, dtSesion);
    }

    //PAGINA SOLICITUD
    @WebMethod(operationName = "obtenerDtPaginaSolicitud")
    public DtPaginaSolicitud obtenerDtPaginaSolicitud(String className, Long id, DtSesion dtSesion) {
        return iControladorPaginaSolicitud.obtenerPaginaSolicitud(className, id, dtSesion);
    }

    @WebMethod(operationName = "crearPaginaSolicitud")
    public DtMensaje crearPaginaSolicitud(Long solicitudId, Long paginaAplicacionId, DtPaginaSolicitud dtPaginaSolicitud, DtSesion dtSesion) {
        return iControladorPaginaSolicitud.crearPaginaSolicitud(solicitudId, paginaAplicacionId, dtPaginaSolicitud, dtSesion);
    }

    @WebMethod(operationName = "modificarPaginaSolicitud")
    public DtMensaje modificarPaginaSolicitud(DtPaginaSolicitud dtPaginaSolicitud, DtSesion dtSesion) {
        return iControladorPaginaSolicitud.modificarPaginaSolicitud(dtPaginaSolicitud, dtSesion);
    }

}
