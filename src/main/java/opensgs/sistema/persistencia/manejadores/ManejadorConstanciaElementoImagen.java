package opensgs.sistema.persistencia.manejadores;

import java.util.ArrayList;
import java.util.List;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.datatypes.DtConstanciaElementoImagen;
import opensgs.sistema.logica.beans.ConstanciaElementoImagen;

public class ManejadorConstanciaElementoImagen {

    private static ManejadorConstanciaElementoImagen instance = null;

    private ManejadorConstanciaElementoImagen() {
    }

    public static ManejadorConstanciaElementoImagen getInstance() {
        if (instance == null) {
            instance = new ManejadorConstanciaElementoImagen();
        }
        return instance;
    }

    public ConstanciaElementoImagen obtenerConstanciaElementoImagen(DtConstanciaElementoImagen dtConstanciaElementoImagen) {
        return (ConstanciaElementoImagen) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtConstanciaElementoImagen);
    }

    public ConstanciaElementoImagen obtenerConstanciaElementoImagen(Long id) {
        return (ConstanciaElementoImagen) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new ConstanciaElementoImagen(), "id", id);
    }

    public ConstanciaElementoImagen obtenerConstanciaElementoImagen(String nombre) {
        return (ConstanciaElementoImagen) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new ConstanciaElementoImagen(), "nombre", nombre);
    }
    
    public List<ConstanciaElementoImagen> listarElementoImagenConstanciaActivas(Long constanciaId) {
        List<ConstanciaElementoImagen> listaConstanciaElementoImagen = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new ConstanciaElementoImagen(), "activo", true, "borrado", false, "constancia_id", constanciaId);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaConstanciaElementoImagen.add((ConstanciaElementoImagen) openSGSManagedBean);
        }
        return listaConstanciaElementoImagen;
    }
    
    public List<ConstanciaElementoImagen> listarConstanciaElementoImagen(Long constanciaId, boolean borrado) {
        List<ConstanciaElementoImagen> listaConstanciaElementoImagen = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new ConstanciaElementoImagen(),  "borrado", borrado, "constancia_id", constanciaId);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaConstanciaElementoImagen.add((ConstanciaElementoImagen) openSGSManagedBean);
        }
        return listaConstanciaElementoImagen;
    }

    public void crearConstanciaElementoImagen(ConstanciaElementoImagen constanciaElementoImagen) throws Exception {
        ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(constanciaElementoImagen);
    }

    public void modificarConstanciaElementoImagen(ConstanciaElementoImagen constanciaElementoImagen) throws Exception {
        ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(constanciaElementoImagen);
    }

}
