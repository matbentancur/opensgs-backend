package opensgs.sistema.persistencia.manejadores;

import java.util.ArrayList;
import java.util.List;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.datatypes.DtConstanciaElementoTexto;
import opensgs.sistema.logica.beans.ConstanciaElementoTexto;

public class ManejadorConstanciaElementoTexto {

    private static ManejadorConstanciaElementoTexto instance = null;

    private ManejadorConstanciaElementoTexto() {
    }

    public static ManejadorConstanciaElementoTexto getInstance() {
        if (instance == null) {
            instance = new ManejadorConstanciaElementoTexto();
        }
        return instance;
    }

    public ConstanciaElementoTexto obtenerConstanciaElementoTexto(DtConstanciaElementoTexto dtConstanciaElementoTexto) {
        return (ConstanciaElementoTexto) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtConstanciaElementoTexto);
    }

    public ConstanciaElementoTexto obtenerConstanciaElementoTexto(Long id) {
        return (ConstanciaElementoTexto) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new ConstanciaElementoTexto(), "id", id);
    }

    public ConstanciaElementoTexto obtenerConstanciaElementoTexto(String nombre) {
        return (ConstanciaElementoTexto) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new ConstanciaElementoTexto(), "nombre", nombre);
    }
    
    public List<ConstanciaElementoTexto> listarElementoTextoConstanciaActivas(Long constanciaId) {
        List<ConstanciaElementoTexto> listaConstanciaElementoTexto = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new ConstanciaElementoTexto(), "activo", true, "borrado", false, "constancia_id", constanciaId);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaConstanciaElementoTexto.add((ConstanciaElementoTexto) openSGSManagedBean);
        }
        return listaConstanciaElementoTexto;
    }
    
    public List<ConstanciaElementoTexto> listarConstanciaElementoTexto(Long constanciaId, boolean borrado) {
        List<ConstanciaElementoTexto> listaConstanciaElementoTexto = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new ConstanciaElementoTexto(),  "borrado", borrado, "constancia_id", constanciaId);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaConstanciaElementoTexto.add((ConstanciaElementoTexto) openSGSManagedBean);
        }
        return listaConstanciaElementoTexto;
    }

    public void crearConstanciaElementoTexto(ConstanciaElementoTexto constanciaElementoTexto) throws Exception {
        ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(constanciaElementoTexto);
    }

    public void modificarConstanciaElementoTexto(ConstanciaElementoTexto constanciaElementoTexto) throws Exception {
        ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(constanciaElementoTexto);
    }

}
