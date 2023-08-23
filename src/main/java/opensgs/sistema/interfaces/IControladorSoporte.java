package opensgs.sistema.interfaces;

import opensgs.datatypes.DtMensaje;
import opensgs.sistema.datatypes.DtSoporte;

public interface IControladorSoporte {
    
    //OPENSGSBEAN
    public DtMensaje enviarSoporte(DtSoporte dtSoporte);
    
}
