package opensgs.sistema.persistencia.manejadores;

import java.util.ArrayList;
import java.util.List;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.persistencia.conexiones.ConexionDataBase;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.datatypes.DtConstancia;
import opensgs.sistema.logica.beans.Constancia;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ManejadorConstancia {

    private static ManejadorConstancia instance = null;

    private ManejadorConstancia() {
    }

    public static ManejadorConstancia getInstance() {
        if (instance == null) {
            instance = new ManejadorConstancia();
        }
        return instance;
    }

    public Constancia obtenerConstancia(DtConstancia dtConstancia) {
        return (Constancia) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtConstancia);
    }

    public Constancia obtenerConstancia(Long id) {
        return (Constancia) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new Constancia(), "id", id);
    }

    public Constancia obtenerConstancia(String nombre) {
        return (Constancia) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new Constancia(), "nombre", nombre);
    }

    public void crearConstancia(Constancia constancia) throws Exception {
        ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(constancia);
    }

    public void modificarConstancia(Constancia constancia) throws Exception {
        ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(constancia);
    }

    public List<Constancia> listarConstancias(Long aplicacionId, boolean borrado) {
        List<Constancia> listaConstancias = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new Constancia(), "borrado", borrado, "aplicacion_id", aplicacionId);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaConstancias.add((Constancia) openSGSManagedBean);
        }
        return listaConstancias;
    }

    public List<Constancia> listarConstanciasActivas(Long aplicacionId) {
        List<Constancia> listaConstancias = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new Constancia(), "activo", true, "borrado", false, "aplicacion_id", aplicacionId);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaConstancias.add((Constancia) openSGSManagedBean);
        }
        return listaConstancias;
    }

//    public List<Constancia> listarConstanciasAplicacion(Long aplicacionId, boolean borrado) {
//        Session session = ConexionDataBase.getInstance().getSessionFactory();
//        session.beginTransaction();
//        try {
//            Query query = session.createQuery(""
//                    + "SELECT co "
//                    + "FROM Aplicacion AS ap "
//                    + "JOIN ap.constancias AS co "
//                    + "WHERE "
//                    + "co.borrado = :borrado "
//                    + "AND "
//                    + "ap.id = :aplicacionId "
//                    + "ORDER BY co.id ASC"
//            );
//            query.setParameter("borrado", borrado);
//            query.setParameter("aplicacionId", aplicacionId);
//            List<Constancia> lista = (List<Constancia>) query.list();
//            session.getTransaction().commit();
//            return lista;
//        } catch (HibernateException e) {
//            session.getTransaction().rollback();
//            return null;
//        } finally {
//            session.close();
//        }
//    }
//    public List<Constancia> listarConstanciasAplicacionActivas(Long aplicacionId) {
//        Session session = ConexionDataBase.getInstance().getSessionFactory();
//        session.beginTransaction();
//        try {
//            Query query = session.createQuery(""
//                    + "SELECT co "
//                    + "FROM Aplicacion AS ap "
//                    + "JOIN ap.constancias AS co "
//                    + "WHERE "
//                    + "co.activo = :activo "
//                    + "AND "
//                    + "co.borrado = :borrado "
//                    + "AND "
//                    + "ap.id = :aplicacionId "
//                    + "ORDER BY co.id ASC"
//            );
//            query.setParameter("activo", true);
//            query.setParameter("borrado", false);
//            query.setParameter("aplicacionId", aplicacionId);
//            List<Constancia> lista = (List<Constancia>) query.list();
//            session.getTransaction().commit();
//            return lista;
//        } catch (HibernateException e) {
//            session.getTransaction().rollback();
//            return null;
//        } finally {
//            session.close();
//        }
//    }
}
