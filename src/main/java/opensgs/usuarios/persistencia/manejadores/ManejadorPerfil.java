package opensgs.usuarios.persistencia.manejadores;

import java.util.ArrayList;
import java.util.List;
import opensgs.usuarios.enums.AlcancePerfil;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.usuarios.datatypes.DtPerfil;
import opensgs.usuarios.logica.beans.Perfil;

public class ManejadorPerfil {

    private static ManejadorPerfil instance = null;

    private ManejadorPerfil() {
    }

    public static ManejadorPerfil getInstance() {
        if (instance == null) {
            instance = new ManejadorPerfil();
        }
        return instance;
    }

    public Perfil obtenerPerfil(DtPerfil dtPerfil) {
        return (Perfil) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtPerfil);
    }
    
    public Perfil obtenerPerfil(Long perfilId) {
        return (Perfil) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new Perfil(), perfilId);
    }
    
    public <T> Perfil obtenerPerfil(String parametro, T valor) {
        return (Perfil) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new Perfil(), parametro, valor);
    }
    
    public List<Perfil> listarPerfilesGlobalesActivos() {
        List<Perfil> listaPerfiles = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new Perfil(), "activo", true, "borrado", false, "alcancePerfil", AlcancePerfil.GLOBAL);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaPerfiles.add((Perfil) openSGSManagedBean);
        }
        return listaPerfiles;
    }
    
    public List<Perfil> listarPerfilesAplicacionActivos() {
        List<Perfil> listaPerfiles = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new Perfil(), "activo", true, "borrado", false, "alcancePerfil", AlcancePerfil.APLICACION);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaPerfiles.add((Perfil) openSGSManagedBean);
        }
        return listaPerfiles;
    }

}
