package opensgs.sistema.logica.utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator{
    
    private final String smtpUsername;
    private final String smtpPassword;

    public SMTPAuthenticator(String smtpUsername, String smtpPassword) {
        this.smtpUsername = smtpUsername;
        this.smtpPassword = smtpPassword;
    }
    
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(smtpUsername, smtpPassword);
     }
}
