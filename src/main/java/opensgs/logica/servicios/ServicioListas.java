package opensgs.logica.servicios;

import java.util.ArrayList;
import java.util.List;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;

public class ServicioListas {

    private static ServicioListas instance = null;

    private ServicioListas() {
    }

    public static ServicioListas getInstance() {
        if (instance == null) {
            instance = new ServicioListas();
        }
        return instance;
    }

    public <T extends DtOpenSGSBean> List<T> beanToDataType(List<? extends OpenSGSBean> listaOpenSGSBean) {

        List<T> listaDtOpenSGSBeans = new ArrayList<>();
        
        if (listaOpenSGSBean != null) {
            if (!listaOpenSGSBean.isEmpty()) {
                for (OpenSGSBean openSGSBean : listaOpenSGSBean) {
                    listaDtOpenSGSBeans.add((T) openSGSBean.getDtOpenSGSBean());
                }
            }
        }

        return listaDtOpenSGSBeans;
    }
    
    public <T extends OpenSGSBean> List<T> dataTypeToBean(List<? extends DtOpenSGSBean> dtOpenSGSBeans) {
        
        List<T> listaOpenSGSBeans = new ArrayList<>();
        
        for (DtOpenSGSBean dtOpenSGSBean : dtOpenSGSBeans) {
            T bean = (T) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtOpenSGSBean);
            if (bean != null) {
                listaOpenSGSBeans.add(bean);
            }
        }
        
        return listaOpenSGSBeans;
    }
    
    public boolean buscarElemento(List<? extends OpenSGSBean> listaOpenSGSBeans, OpenSGSBean openSGSBean) {
        return listaOpenSGSBeans.contains(openSGSBean);
    }
    
    public <T extends OpenSGSBean> boolean agregarElemento(List<T> listaOpenSGSBeans, T openSGSBean) {
        if (this.buscarElemento(listaOpenSGSBeans, openSGSBean)) {
            return false;
        }
        return listaOpenSGSBeans.add(openSGSBean);
    }
    
    public <T extends OpenSGSBean> boolean quitarElemento(List<T> listaOpenSGSBeans, T openSGSBean) {
        if (!this.buscarElemento(listaOpenSGSBeans, openSGSBean)) {
            return false;
        }
        return listaOpenSGSBeans.remove(openSGSBean);
    }

}
