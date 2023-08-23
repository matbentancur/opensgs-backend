package opensgs.sistema.logica.servicios;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.sistema.enums.CorreoContenidoTipo;
import opensgs.sistema.enums.ServidorCorreoIdentificacion;
import opensgs.sistema.enums.ServidorCorreoSeguridad;
import opensgs.sistema.logica.beans.Destinatario;
import opensgs.sistema.logica.beans.ServidorCorreo;
import opensgs.sistema.logica.beans.Sistema;
import opensgs.sistema.logica.beans.Soporte;
import opensgs.sistema.logica.utils.SMTPAuthenticator;
import opensgs.sistema.persistencia.manejadores.ManejadorSistema;

public class ServicioServidorCorreo {

    private static ServicioServidorCorreo instance = null;
    private Session smtpSesion;
    private String smtpServidor;
    private String smtpPuerto;
    private ServidorCorreoSeguridad smtpSeguridad;
    private ServidorCorreoIdentificacion smtpIdentificacion;
    private String smtpUsuario;
    private String smtpPassword;
    private String smtpDesdeCorreo;
    private String smtpDesdeNombre;

    private ServicioServidorCorreo() {
    }

    public static ServicioServidorCorreo getInstance() {
        if (instance == null) {
            instance = new ServicioServidorCorreo();
        }
        return instance;
    }

    public Session getSmtpSesion() {
        return smtpSesion;
    }

    public void setSmtpSesion(Session smtpSesion) {
        this.smtpSesion = smtpSesion;
    }

    public String getSmtpServidor() {
        return smtpServidor;
    }

    public void setSmtpServidor(String smtpServidor) {
        this.smtpServidor = smtpServidor;
    }

    public String getSmtpPuerto() {
        return smtpPuerto;
    }

    public void setSmtpPuerto(String smtpPuerto) {
        this.smtpPuerto = smtpPuerto;
    }

    public ServidorCorreoSeguridad getSmtpSeguridad() {
        return smtpSeguridad;
    }

    public void setSmtpSeguridad(ServidorCorreoSeguridad smtpSeguridad) {
        this.smtpSeguridad = smtpSeguridad;
    }

    public ServidorCorreoIdentificacion getSmtpIdentificacion() {
        return smtpIdentificacion;
    }

    public void setSmtpIdentificacion(ServidorCorreoIdentificacion smtpIdentificacion) {
        this.smtpIdentificacion = smtpIdentificacion;
    }

    public String getSmtpUsuario() {
        return smtpUsuario;
    }

    public void setSmtpUsuario(String smtpUsuario) {
        this.smtpUsuario = smtpUsuario;
    }

    public String getSmtpPassword() {
        return smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }

    public String getSmtpDesdeCorreo() {
        return smtpDesdeCorreo;
    }

    public void setSmtpDesdeCorreo(String smtpDesdeCorreo) {
        this.smtpDesdeCorreo = smtpDesdeCorreo;
    }

    public String getSmtpDesdeNombre() {
        return smtpDesdeNombre;
    }

    public void setSmtpDesdeNombre(String smtpDesdeNombre) {
        this.smtpDesdeNombre = smtpDesdeNombre;
    }

    private ServidorCorreo obtenerServidorCorreo() {
        Sistema sistema = ManejadorSistema.getInstance().obtenerSistema();
        return sistema.getServidorCorreo();
    }

    private void leerServidorCorreo(ServidorCorreo servidorCorreo) {
        servidorCorreo.desencriptarPassword();
        this.setSmtpServidor(servidorCorreo.getServidor());
        this.setSmtpPuerto(servidorCorreo.getPuerto().toString());
        this.setSmtpSeguridad(servidorCorreo.getSeguridad());
        this.setSmtpIdentificacion(servidorCorreo.getIdentificacion());
        this.setSmtpUsuario(servidorCorreo.getUsuario());
        this.setSmtpPassword(servidorCorreo.getPassword());
        this.setSmtpDesdeCorreo(servidorCorreo.getDesdeCorreo());
        this.setSmtpDesdeNombre(servidorCorreo.getDesdeNombre());
    }

    private void iniciarSesionSMTP() {
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", "true");
        mailProperties.put("mail.smtp.host", this.getSmtpServidor());
        mailProperties.put("mail.smtp.port", this.getSmtpPuerto());
        mailProperties.put("mail.smtp.user", this.getSmtpDesdeCorreo());

        if (this.getSmtpSeguridad().equals(ServidorCorreoSeguridad.STARTTLS)) {
            mailProperties.put("mail.smtp.starttls.enable", "true");
            mailProperties.put("mail.smtp.ssl.trust", "*");
        }

        if (this.getSmtpSeguridad().equals(ServidorCorreoSeguridad.SSL_TLS)) {
            mailProperties.put("mail.smtp.ssl.enable", "true");
            mailProperties.put("mail.smtp.ssl.trust", "*");
        }

        if (this.getSmtpIdentificacion().equals(ServidorCorreoIdentificacion.LOGIN)) {
            mailProperties.put("mail.smtp.auth.mechanisms", "LOGIN");
        }
        if (this.getSmtpIdentificacion().equals(ServidorCorreoIdentificacion.PLAIN)) {
            mailProperties.put("mail.smtp.auth.mechanisms", "PLAIN");
        }
        if (this.getSmtpIdentificacion().equals(ServidorCorreoIdentificacion.DIGEST_MD5)) {
            mailProperties.put("mail.smtp.auth.mechanisms", "DIGEST_MD5");
        }
        if (this.getSmtpIdentificacion().equals(ServidorCorreoIdentificacion.NTLM)) {
            mailProperties.put("mail.smtp.auth.mechanisms", "NTLM");
        }

        Authenticator autenticacion = new SMTPAuthenticator(this.getSmtpUsuario(), this.getSmtpPassword());
        this.setSmtpSesion(Session.getInstance(mailProperties, autenticacion));
    }

    public void enviarCorreo(List<String> destinatarios, List<String> destinatariosConCopia, List<String> destinatariosConCopiaOculta, String asunto, String texto, CorreoContenidoTipo correoContenidoTipo) throws Exception {
        this.leerServidorCorreo(this.obtenerServidorCorreo());
        this.iniciarSesionSMTP();
        MimeMessage mensaje = new MimeMessage(this.getSmtpSesion());
        mensaje.setSentDate(new Date(System.currentTimeMillis()));
        mensaje.setFrom(new InternetAddress(this.getSmtpDesdeCorreo(), this.getSmtpDesdeNombre()));
        mensaje.setSubject(asunto);

        MimeBodyPart messageBodyPartTexto = new MimeBodyPart();

        if (correoContenidoTipo.equals(CorreoContenidoTipo.TXT)) {
            messageBodyPartTexto.setContent(texto, "text/plain; charset=utf-8");
        }

        if (correoContenidoTipo.equals(CorreoContenidoTipo.HTML)) {
            messageBodyPartTexto.setContent(texto, "text/html; charset=utf-8");
        }

        for (String destinatario : destinatarios) {
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        }

        if (!destinatariosConCopia.isEmpty()) {
            for (String destinatarioConCopia : destinatariosConCopia) {
                mensaje.addRecipient(Message.RecipientType.CC, new InternetAddress(destinatarioConCopia));
            }
        }

        if (!destinatariosConCopiaOculta.isEmpty()) {
            for (String destinatarioConCopiaOculta : destinatariosConCopiaOculta) {
                mensaje.addRecipient(Message.RecipientType.BCC, new InternetAddress(destinatarioConCopiaOculta));
            }
        }

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPartTexto);
        mensaje.setContent(multipart);

        mensaje.saveChanges();
        Transport.send(mensaje);
    }

    public void enviarCorreo(List<Destinatario> destinatarios, String asunto, String texto, CorreoContenidoTipo correoContenidoTipo) throws Exception {
        this.leerServidorCorreo(this.obtenerServidorCorreo());
        this.iniciarSesionSMTP();
        MimeMessage mensaje = new MimeMessage(this.getSmtpSesion());
        mensaje.setSentDate(new Date(System.currentTimeMillis()));
        mensaje.setFrom(new InternetAddress(this.getSmtpDesdeCorreo(), this.getSmtpDesdeNombre()));
        mensaje.setSubject(asunto);

        MimeBodyPart messageBodyPartTexto = new MimeBodyPart();

        if (correoContenidoTipo.equals(CorreoContenidoTipo.TXT)) {
            messageBodyPartTexto.setContent(texto, "text/plain; charset=utf-8");
        }

        if (correoContenidoTipo.equals(CorreoContenidoTipo.HTML)) {
            messageBodyPartTexto.setContent(texto, "text/html; charset=utf-8");
        }
        if (!destinatarios.isEmpty()) {
            for (Destinatario destinatario : destinatarios) {
                mensaje.addRecipient(Message.RecipientType.BCC, new InternetAddress(destinatario.getEmail()));
            }
        }

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPartTexto);
        mensaje.setContent(multipart);

        mensaje.saveChanges();
        Transport.send(mensaje);
    }

    public void enviarCorreoSoporte(Soporte soporte) throws Exception {

        Sistema sistema = ManejadorSistema.getInstance().obtenerSistema();

        this.leerServidorCorreo(this.obtenerServidorCorreo());
        this.iniciarSesionSMTP();
        MimeMessage mensaje = new MimeMessage(this.getSmtpSesion());
        mensaje.setSentDate(new Date(System.currentTimeMillis()));
        mensaje.setFrom(new InternetAddress(this.getSmtpDesdeCorreo(), this.getSmtpDesdeNombre()));

        String asunto = ServicioMensaje.getInstance().getMensaje("Soporte.subject") + " " + sistema.getNombre();

        mensaje.setSubject(asunto);

        String texto = ""
                + "Email: " + soporte.getEmail()
                + "\nNombre: " + soporte.getNombres()
                + "\nApellidos: " + soporte.getApellidos()
                + "\nTelefono: " + soporte.getTelefono()
                + "\nDescripción: " + soporte.getDescripcion();

        MimeBodyPart messageBodyPartTexto = new MimeBodyPart();

        messageBodyPartTexto.setContent(texto, "text/plain; charset=utf-8");

        mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(sistema.getCorreoSoporte()));

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPartTexto);
        mensaje.setContent(multipart);

        mensaje.saveChanges();
        Transport.send(mensaje);
    }

    public void enviarCorreoPrueba(ServidorCorreo servidorCorreo, String email) throws Exception {
        this.leerServidorCorreo(servidorCorreo);
        this.iniciarSesionSMTP();
        MimeMessage mensaje = new MimeMessage(this.getSmtpSesion());
        mensaje.setSentDate(new Date(System.currentTimeMillis()));
        mensaje.setFrom(new InternetAddress(this.getSmtpDesdeCorreo(), this.getSmtpDesdeNombre()));
        mensaje.setSubject("TEST");

        MimeBodyPart messageBodyPartTexto = new MimeBodyPart();
        messageBodyPartTexto.setContent("TEST", "text/plain; charset=utf-8");

        mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(email));

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPartTexto);
        mensaje.setContent(multipart);

        mensaje.saveChanges();
        Transport.send(mensaje);
    }

}
