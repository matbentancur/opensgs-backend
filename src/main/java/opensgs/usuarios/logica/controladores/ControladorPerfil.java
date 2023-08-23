package opensgs.usuarios.logica.controladores;

import java.util.List;
import opensgs.datatypes.DtMensaje;
import opensgs.enums.Operacion;
import opensgs.logica.servicios.ServicioOpenSGSBean;
import opensgs.usuarios.datatypes.DtPerfil;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.interfaces.IControladorPerfil;
import opensgs.usuarios.logica.beans.Perfil;
import opensgs.usuarios.logica.servicios.ServicioPermiso;
import opensgs.usuarios.logica.servicios.ServicioSesion;
import opensgs.usuarios.persistencia.manejadores.ManejadorPerfil;

public class ControladorPerfil implements IControladorPerfil {

    @Override
    public List<DtPerfil> listarPerfilesGlobales(DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Perfil(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<Perfil> listaPerfiles = ManejadorPerfil.getInstance().listarPerfilesGlobalesActivos();

        return (List<DtPerfil>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaPerfiles);
    }
    
    @Override
    public List<DtPerfil> listarPerfilesAplicacion(DtSesion dtSesion) {
        DtMensaje dtMensaje = ServicioSesion.getInstance().verificarSesion(dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        dtMensaje = ServicioPermiso.getInstance().verificarPermiso(Operacion.Ver, new Perfil(), dtSesion);
        if (!dtMensaje.isExito()) {
            return null;
        }

        List<Perfil> listaPerfiles = ManejadorPerfil.getInstance().listarPerfilesAplicacionActivos();

        return (List<DtPerfil>) ServicioOpenSGSBean.getInstance().listOpenSGSBeanToListDtOpenSGSBean(listaPerfiles);
    }

}
