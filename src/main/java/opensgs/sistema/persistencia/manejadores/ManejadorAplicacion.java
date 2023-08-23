package opensgs.sistema.persistencia.manejadores;

import java.util.ArrayList;
import java.util.List;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.persistencia.conexiones.ConexionDataBase;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.datatypes.DtAplicacion;
import opensgs.sistema.logica.beans.Aplicacion;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;


public class ManejadorAplicacion {
    
    private static ManejadorAplicacion instance = null;
    
    private ManejadorAplicacion() {
    }
    
    public static ManejadorAplicacion getInstance() {
        if (instance == null) {
            instance = new ManejadorAplicacion();
        }
        return instance;
    }

    public Aplicacion obtenerAplicacion(DtAplicacion dtAplicacion) {
        return (Aplicacion) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtAplicacion);
    }

    public Aplicacion obtenerAplicacion(Long id) {
        return (Aplicacion) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new Aplicacion(), id);
    }
    
    public List<Aplicacion> listarAplicacionesAbiertas() {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "FROM Aplicacion AS a "
                    + "WHERE "
                    + "activo = :activo "
                    + "AND "
                    + "borrado= :borrado "
                    + "AND "
                    + "apertura <= CURRENT_TIMESTAMP "
                    + "AND "
                    + "cierre >= CURRENT_TIMESTAMP "
                    + "ORDER BY a.cierre ASC"
            );
            query.setParameter("activo", true);
            query.setParameter("borrado", false);
            List<Aplicacion> lista = (List<Aplicacion>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;        
        } finally{
            session.close();
        }
    }
    
    public List<Aplicacion> listarAplicacionesCerradas() {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "FROM Aplicacion AS a "
                    + "WHERE "
                    + "activo = :activo "
                    + "AND "
                    + "borrado= :borrado "
                    + "AND "
                    + "cierre <= CURRENT_TIMESTAMP "
                    + "ORDER BY a.cierre ASC"
            );
            query.setParameter("activo", true);
            query.setParameter("borrado", false);
            List<Aplicacion> lista = (List<Aplicacion>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;        
        } finally{
            session.close();
        }
    }
    
    public List<Aplicacion> listarAplicacionesProximas() {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "FROM Aplicacion AS a "
                    + "WHERE "
                    + "activo = :activo "
                    + "AND "
                    + "borrado= :borrado "
                    + "AND "
                    + "apertura >= CURRENT_TIMESTAMP "
                    + "ORDER BY a.cierre ASC"
            );
            query.setParameter("activo", true);
            query.setParameter("borrado", false);
            List<Aplicacion> lista = (List<Aplicacion>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;        
        } finally{
            session.close();
        }
    }
    
    public List<Aplicacion> listarAplicacionesActivas() {
        List<Aplicacion> listaAplicaciones = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new Aplicacion(), "activo", true, "borrado", false);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaAplicaciones.add((Aplicacion) openSGSManagedBean);
        }
        return listaAplicaciones;
    }
    
}
