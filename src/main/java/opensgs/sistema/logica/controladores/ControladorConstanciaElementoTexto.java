package opensgs.sistema.logica.controladores;

import java.util.List;
import opensgs.datatypes.DtMensaje;
import opensgs.logica.servicios.ServicioOpenSGSBean;
import opensgs.sistema.datatypes.DtConstanciaElementoTexto;
import opensgs.sistema.interfaces.IControladorConstanciaElementoTexto;
import opensgs.sistema.logica.beans.ConstanciaElementoTexto;
import opensgs.sistema.persistencia.manejadores.ManejadorConstanciaElementoTexto;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.logica.servicios.ServicioSesion;

public class ControladorConstanciaElementoTexto implements IControladorConstanciaElementoTexto {

    @Override
    public List<DtConstanciaElementoTexto> listarConstanciaElementoTexto(Long constanciaId, boolean borrado, DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<ConstanciaElementoTexto> listaConstanciaElementoTexto = ManejadorConstanciaElementoTexto.getInstance().listarConstanciaElementoTexto(constanciaId, borrado);

        return (List<DtConstanciaElementoTexto>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaConstanciaElementoTexto);
    }

}
