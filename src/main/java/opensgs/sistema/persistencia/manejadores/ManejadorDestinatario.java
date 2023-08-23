package opensgs.sistema.persistencia.manejadores;

import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.datatypes.DtDestinatario;
import opensgs.sistema.logica.beans.Destinatario;

public class ManejadorDestinatario {

    private static ManejadorDestinatario instance = null;

    private ManejadorDestinatario() {
    }

    public static ManejadorDestinatario getInstance() {
        if (instance == null) {
            instance = new ManejadorDestinatario();
        }
        return instance;
    }

    public Destinatario obtenerDestinatario(DtDestinatario dtDestinatario) {
        return (Destinatario) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtDestinatario);
    }

    public Destinatario obtenerDestinatario(String email) {
        return (Destinatario) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new Destinatario(), "email", email);
    }

    public void crearDestinatario(Destinatario destinatario) throws Exception {
        ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(destinatario);
    }

}
