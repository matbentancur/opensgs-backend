package opensgs.solicitudes.logica.servicios;

import java.util.ArrayList;
import java.util.List;
import opensgs.datatypes.DtMensaje;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.logica.servicios.ServicioOpenSGSBean;
import opensgs.sistema.enums.ConstanciaTipo;
import opensgs.sistema.logica.beans.Constancia;
import opensgs.solicitudes.logica.beans.Solicitud;
import opensgs.usuarios.logica.beans.Usuario;

public class ServicioSolicitud {

    private static ServicioSolicitud instance = null;

    private ServicioSolicitud() {
    }

    public static ServicioSolicitud getInstance() {
        if (instance == null) {
            instance = new ServicioSolicitud();
        }
        return instance;
    }

    public boolean estaBorrada(Solicitud solicitud) {
        return ServicioOpenSGSBean.getInstance().estaBorrado(solicitud);
    }

    public boolean estaActiva(Solicitud solicitud) {
        return ServicioOpenSGSBean.getInstance().estaActivo(solicitud);
    }

    public boolean estaAcordado(Solicitud solicitud) {
        return solicitud.isAcordado();
    }

    public boolean estaEntregado(Solicitud solicitud) {
        return solicitud.isEntregado();
    }

    public DtMensaje esPropietario(Solicitud solicitud, Usuario usuario) {
        if (solicitud == null) {
            return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.unexpected.error");
        }
        if (usuario == null) {
            return ServicioMensaje.getInstance().getMensajeERROR("Usuario.unexpected.error");
        }

        if (!solicitud.getUsuario().equals(usuario)) {
            return ServicioMensaje.getInstance().getMensajeERROR("Solicitud.owner.error");
        }

        return ServicioMensaje.getInstance().getMensajeOK();
    }

    public boolean esConstanciaEmitible(Solicitud solicitud, Constancia constancia) {

        ConstanciaTipo constanciaTipo = constancia.getConstanciaTipo();

        if (constanciaTipo.equals(ConstanciaTipo.ENTREGADO) && solicitud.isEntregado()) {
            return true;
        }

        if (constanciaTipo.equals(ConstanciaTipo.FINANCIADO) && solicitud.isFinanciado()) {
            return true;
        }

        if (constanciaTipo.equals(ConstanciaTipo.CURSADO) && solicitud.isCursado()) {
            return true;
        }

        if (constanciaTipo.equals(ConstanciaTipo.APROBADO) && solicitud.isAprobado()) {
            return true;
        }

        if (constanciaTipo.equals(ConstanciaTipo.ACORDADO) && solicitud.isAcordado()) {
            return true;
        }

        return false;
    }

    public List<Constancia> filtrarConstanciasEmitibles(Solicitud solicitud, List<Constancia> listaConstancias) {
        List<Constancia> lista = new ArrayList<>();
        
        for (Constancia constancia : listaConstancias) {
            if(this.esConstanciaEmitible(solicitud, constancia)){
                lista.add(constancia);
            }
        }
        
        return lista;
    }

}
