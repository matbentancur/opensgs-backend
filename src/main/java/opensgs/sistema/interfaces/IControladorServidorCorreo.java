package opensgs.sistema.interfaces;

import opensgs.datatypes.DtMensaje;
import opensgs.usuarios.datatypes.DtSesion;

public interface IControladorServidorCorreo {

    public DtMensaje probarEnvioCorreo(Long id, String email, DtSesion dtSesion);

}
