package opensgs.sistema.logica.servicios;

import static java.util.regex.Matcher.quoteReplacement;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.sistema.logica.beans.PlantillaCorreo;
import opensgs.sistema.logica.beans.Sistema;
import opensgs.sistema.persistencia.manejadores.ManejadorSistema;
import opensgs.usuarios.logica.beans.Usuario;

public class ServicioPlantillaCorreo {

    private static ServicioPlantillaCorreo instance = null;
    private String sistemaNombre;
    private String sistemaUrl;
    private String sistemaCorreoSoporte;
    private String aplicacionNombre;
    private String aplicacionTitulo;
    private String aplicacionApertura;
    private String aplicacionCierre;
    private String aplicacionContacto;
    private String usuarioDocumentoTipo;
    private String usuarioDocumento;
    private String usuarioEmail;
    private String usuarioNombres;
    private String usuarioApellidos;
    private String usuarioTelefono;
    private String usuarioPerfilGlobal;

    private ServicioPlantillaCorreo() {
    }

    public static ServicioPlantillaCorreo getInstance() {
        if (instance == null) {
            instance = new ServicioPlantillaCorreo();
        }
        return instance;
    }

    public String getSistemaNombre() {
        return sistemaNombre;
    }

    public void setSistemaNombre(String sistemaNombre) {
        this.sistemaNombre = sistemaNombre;
    }

    public String getSistemaUrl() {
        return sistemaUrl;
    }

    public void setSistemaUrl(String sistemaUrl) {
        this.sistemaUrl = sistemaUrl;
    }

    public String getSistemaCorreoSoporte() {
        return sistemaCorreoSoporte;
    }

    public void setSistemaCorreoSoporte(String sistemaCorreoSoporte) {
        this.sistemaCorreoSoporte = sistemaCorreoSoporte;
    }

    public String getAplicacionNombre() {
        return aplicacionNombre;
    }

    public void setAplicacionNombre(String aplicacionNombre) {
        this.aplicacionNombre = aplicacionNombre;
    }

    public String getAplicacionTitulo() {
        return aplicacionTitulo;
    }

    public void setAplicacionTitulo(String aplicacionTitulo) {
        this.aplicacionTitulo = aplicacionTitulo;
    }

    public String getAplicacionApertura() {
        return aplicacionApertura;
    }

    public void setAplicacionApertura(String aplicacionApertura) {
        this.aplicacionApertura = aplicacionApertura;
    }

    public String getAplicacionCierre() {
        return aplicacionCierre;
    }

    public void setAplicacionCierre(String aplicacionCierre) {
        this.aplicacionCierre = aplicacionCierre;
    }

    public String getAplicacionContacto() {
        return aplicacionContacto;
    }

    public void setAplicacionContacto(String aplicacionContacto) {
        this.aplicacionContacto = aplicacionContacto;
    }

    public String getUsuarioDocumentoTipo() {
        return usuarioDocumentoTipo;
    }

    public void setUsuarioDocumentoTipo(String usuarioDocumentoTipo) {
        this.usuarioDocumentoTipo = usuarioDocumentoTipo;
    }

    public String getUsuarioDocumento() {
        return usuarioDocumento;
    }

    public void setUsuarioDocumento(String usuarioDocumento) {
        this.usuarioDocumento = usuarioDocumento;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public String getUsuarioNombres() {
        return usuarioNombres;
    }

    public void setUsuarioNombres(String usuarioNombres) {
        this.usuarioNombres = usuarioNombres;
    }

    public String getUsuarioApellidos() {
        return usuarioApellidos;
    }

    public void setUsuarioApellidos(String usuarioApellidos) {
        this.usuarioApellidos = usuarioApellidos;
    }

    public String getUsuarioTelefono() {
        return usuarioTelefono;
    }

    public void setUsuarioTelefono(String usuarioTelefono) {
        this.usuarioTelefono = usuarioTelefono;
    }

    public String getUsuarioPerfilGlobal() {
        return usuarioPerfilGlobal;
    }

    public void setUsuarioPerfilGlobal(String usuarioPerfilGlobal) {
        this.usuarioPerfilGlobal = usuarioPerfilGlobal;
    }

    public PlantillaCorreo procesarEtiquetas(PlantillaCorreo plantillaCorreo, Aplicacion aplicacion, Usuario usuario) {
        Sistema sistema = ManejadorSistema.getInstance().obtenerSistema();
        this.leerSistema(sistema);
        this.leerAplicacion(aplicacion);
        this.leerUsuario(usuario);
        String texto = plantillaCorreo.getTexto();
        texto = this.reemplazarEtiquetasSistema(texto);
        texto = this.reemplazarEtiquetasAplicacion(texto);
        texto = this.reemplazarEtiquetasUsuario(texto);
        plantillaCorreo.setTexto(texto);
        return plantillaCorreo;
    }

    public PlantillaCorreo procesarEtiquetas(PlantillaCorreo plantillaCorreo, Usuario usuario) {
        Sistema sistema = ManejadorSistema.getInstance().obtenerSistema();
        this.leerSistema(sistema);
        this.leerUsuario(usuario);
        String texto = plantillaCorreo.getTexto();
        texto = this.reemplazarEtiquetasSistema(texto);
        texto = this.reemplazarEtiquetasUsuario(texto);
        plantillaCorreo.setTexto(texto);
        return plantillaCorreo;
    }

    private void leerSistema(Sistema sistema) {
        this.setSistemaNombre(sistema.getNombre());
        this.setSistemaUrl(sistema.getUrl());
        this.setSistemaCorreoSoporte(sistema.getCorreoSoporte());
    }

    private void leerAplicacion(Aplicacion aplicacion) {
        this.setAplicacionNombre(aplicacion.getNombre());
        this.setAplicacionTitulo(aplicacion.getTitulo());
        this.setAplicacionApertura(ServicioFechaHora.getInstance().dateTimeToString(aplicacion.getApertura()));
        this.setAplicacionCierre(ServicioFechaHora.getInstance().dateTimeToString(aplicacion.getCierre()));
        this.setAplicacionContacto(aplicacion.getContacto());
    }

    private void leerUsuario(Usuario usuario) {
        this.setUsuarioDocumentoTipo(usuario.getDocumentoTipo().toString());
        this.setUsuarioDocumento(usuario.getDocumento());
        this.setUsuarioEmail(usuario.getEmail());
        this.setUsuarioNombres(usuario.getNombres());
        this.setUsuarioApellidos(usuario.getApellidos());
        this.setUsuarioTelefono(usuario.getTelefono());
        this.setUsuarioPerfilGlobal(usuario.getPerfil().getNombre());
    }

    private String reemplazarEtiquetasSistema(String texto) {
        texto = this.reemplazar(texto, "Sistema.nombre", this.getSistemaNombre());
        texto = this.reemplazar(texto, "Sistema.url", this.getSistemaUrl());
        texto = this.reemplazar(texto, "Sistema.correSoporte", this.getSistemaCorreoSoporte());
        return texto;
    }

    private String reemplazarEtiquetasAplicacion(String texto) {
        texto = this.reemplazar(texto, "Aplicacion.nombre", this.getAplicacionNombre());
        texto = this.reemplazar(texto, "Aplicacion.titulo", this.getAplicacionTitulo());
        texto = this.reemplazar(texto, "Aplicacion.apertura", this.getAplicacionApertura());
        texto = this.reemplazar(texto, "Aplicacion.cierre", this.getAplicacionCierre());
        texto = this.reemplazar(texto, "Aplicacion.contacto", this.getAplicacionContacto());
        return texto;
    }

    private String reemplazarEtiquetasUsuario(String texto) {
        texto = this.reemplazar(texto, "Usuario.documentoTipo", this.getUsuarioDocumentoTipo());
        texto = this.reemplazar(texto, "Usuario.documento", this.getUsuarioDocumento());
        texto = this.reemplazar(texto, "Usuario.email", this.getUsuarioEmail());
        texto = this.reemplazar(texto, "Usuario.nombres", this.getUsuarioNombres());
        texto = this.reemplazar(texto, "Usuario.apellidos", this.getUsuarioApellidos());
        texto = this.reemplazar(texto, "Usuario.telefono", this.getUsuarioTelefono());
        texto = this.reemplazar(texto, "Usuario.perfilGlobal", this.getUsuarioPerfilGlobal());
        return texto;
    }

    private String reemplazar(String texto, String etiqueta, String reemplazo) {
        return texto.replaceAll("%\\{" + etiqueta + "\\}", quoteReplacement(reemplazo));
    }

}
