package opensgs.sistema.datatypes;

import opensgs.datatypes.DtOpenSGSBean;

public final class DtDestinatario extends DtOpenSGSBean {

    private String email;

    public DtDestinatario() {
    }

    public DtDestinatario(String email, Long id, String creacion) {
        super(id, creacion);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getClassName() {
        return "Destinatario";
    }

    @Override
    public String toString() {
        return super.toString() + "DtDestinatario{" + "email=" + email + '}';
    }

}
