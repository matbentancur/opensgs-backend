package opensgs.logica.servicios;

import static java.util.regex.Matcher.quoteReplacement;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.sistema.logica.beans.Sistema;
import opensgs.sistema.persistencia.manejadores.ManejadorSistema;
import opensgs.usuarios.logica.beans.Usuario;

public class ServicioEtiquetasRemplazables {

    private static ServicioEtiquetasRemplazables instance = null;

    private ServicioEtiquetasRemplazables() {
    }

    public static ServicioEtiquetasRemplazables getInstance() {
        if (instance == null) {
            instance = new ServicioEtiquetasRemplazables();
        }
        return instance;
    }

    public String reemplazarEtiquetasSistema(String texto) {
        Sistema sistema = ManejadorSistema.getInstance().obtenerSistema();
        texto = this.reemplazar(texto, "Sistema.nombre", sistema.getNombre());
        texto = this.reemplazar(texto, "Sistema.url", sistema.getUrl());
        texto = this.reemplazar(texto, "Sistema.correSoporte", sistema.getCorreoSoporte());
        texto = this.reemplazar(texto, "Sistema.fecha", ServicioFechaHora.getInstance().getStringDate());
        texto = this.reemplazar(texto, "Sistema.fechaHora", ServicioFechaHora.getInstance().getStringDateTime());
        return texto;
    }

    public String reemplazarEtiquetasAplicacion(String texto, Aplicacion aplicacion) {
        String aperturaString = ServicioFechaHora.getInstance().dateTimeToString(aplicacion.getApertura());
        String cierreString = ServicioFechaHora.getInstance().dateTimeToString(aplicacion.getCierre());
        
        texto = this.reemplazar(texto, "Aplicacion.nombre", aplicacion.getNombre());
        texto = this.reemplazar(texto, "Aplicacion.titulo", aplicacion.getTitulo());
        texto = this.reemplazar(texto, "Aplicacion.apertura", aperturaString);
        texto = this.reemplazar(texto, "Aplicacion.cierre", cierreString);
        texto = this.reemplazar(texto, "Aplicacion.contacto", aplicacion.getContacto());
        return texto;
    }

    public String reemplazarEtiquetasUsuario(String texto, Usuario usuario) {
        texto = this.reemplazar(texto, "Usuario.documentoTipo", usuario.getDocumentoTipo().toString());
        texto = this.reemplazar(texto, "Usuario.documento", usuario.getDocumento());
        texto = this.reemplazar(texto, "Usuario.email", usuario.getEmail());
        texto = this.reemplazar(texto, "Usuario.nombres", usuario.getNombres());
        texto = this.reemplazar(texto, "Usuario.apellidos", usuario.getApellidos());
        texto = this.reemplazar(texto, "Usuario.telefono", usuario.getTelefono());
        texto = this.reemplazar(texto, "Usuario.perfilGlobal", usuario.getPerfil().getNombre());
        return texto;
    }

    private String reemplazar(String texto, String etiqueta, String reemplazo) {
        return texto.replaceAll("%\\{" + etiqueta + "\\}", quoteReplacement(reemplazo));
    }

}
