package opensgs.sistema.persistencia.manejadores;

import java.util.ArrayList;
import java.util.List;
import opensgs.enums.Alcance;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.persistencia.conexiones.ConexionDataBase;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.datatypes.DtPreguntaFrecuente;
import opensgs.sistema.logica.beans.PreguntaFrecuente;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ManejadorPreguntaFrecuente {

    private static ManejadorPreguntaFrecuente instance = null;

    private ManejadorPreguntaFrecuente() {
    }

    public static ManejadorPreguntaFrecuente getInstance() {
        if (instance == null) {
            instance = new ManejadorPreguntaFrecuente();
        }
        return instance;
    }

    public PreguntaFrecuente obtenerPreguntaFrecuente(DtPreguntaFrecuente dtPreguntaFrecuente) {
        return (PreguntaFrecuente) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtPreguntaFrecuente);
    }

    public List<PreguntaFrecuente> listarPreguntasSistema(boolean borrado) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "FROM PreguntaFrecuente AS pf "
                    + "WHERE "
                    + "pf.borrado = :borrado "
                    + "AND "
                    + "pf.alcance != :alcanceAplicacion "
                    + "ORDER BY pf.id ASC"
            );
            query.setParameter("borrado", borrado);
            query.setParameter("alcanceAplicacion", Alcance.APLICACION);
            List<PreguntaFrecuente> lista = (List<PreguntaFrecuente>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<PreguntaFrecuente> listarAnunciosAplicacion(Long aplicacionId, boolean borrado) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "SELECT pf "
                    + "FROM Aplicacion AS ap "
                    + "JOIN ap.preguntasFrecuentes AS pf "
                    + "WHERE "
                    + "pf.borrado = :borrado "
                    + "AND "
                    + "ap.id = :aplicacionId "
                    + "ORDER BY pf.id ASC"
            );
            query.setParameter("borrado", borrado);
            query.setParameter("aplicacionId", aplicacionId);
            List<PreguntaFrecuente> lista = (List<PreguntaFrecuente>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<PreguntaFrecuente> listarPreguntasSistemaActivas() {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "FROM PreguntaFrecuente AS pf "
                    + "WHERE "
                    + "activo = :activo "
                    + "AND "
                    + "borrado= :borrado "
                    + "AND "
                    + "(alcance = :alcanceSistema "
                    + "OR "
                    + "alcance = :alcanceGlobal) "
                    + "ORDER BY pf.orden ASC"
            );
            query.setParameter("activo", true);
            query.setParameter("borrado", false);
            query.setParameter("alcanceSistema", Alcance.SISTEMA);
            query.setParameter("alcanceGlobal", Alcance.GLOBAL);
            List<PreguntaFrecuente> lista = (List<PreguntaFrecuente>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<PreguntaFrecuente> listarPreguntasAplicacionesActivas() {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "FROM PreguntaFrecuente AS pf "
                    + "WHERE "
                    + "activo = :activo "
                    + "AND "
                    + "borrado = :borrado "
                    + "AND "
                    + "(alcance = :alcanceAplicaciones "
                    + "OR "
                    + "alcance = :alcanceGlobal) "
                    + "ORDER BY pf.orden ASC"
            );
            query.setParameter("activo", true);
            query.setParameter("borrado", false);
            query.setParameter("alcanceAplicaciones", Alcance.APLICACIONES);
            query.setParameter("alcanceGlobal", Alcance.GLOBAL);
            List<PreguntaFrecuente> lista = (List<PreguntaFrecuente>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<PreguntaFrecuente> listarPreguntasAplicacionActivas(Long aplicacionId) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "SELECT pf "
                    + "FROM Aplicacion AS ap "
                    + "JOIN ap.preguntasFrecuentes AS pf "
                    + "WHERE "
                    + "pf.activo = :activo "
                    + "AND "
                    + "pf.borrado = :borrado "
                    + "AND "
                    + "pf.alcance = :alcanceAplicacion "
                    + "AND "
                    + "ap.id = :aplicacionId "
                    + "ORDER BY pf.orden ASC"
            );
            query.setParameter("activo", true);
            query.setParameter("borrado", false);
            query.setParameter("alcanceAplicacion", Alcance.APLICACION);
            query.setParameter("aplicacionId", aplicacionId);
            List<PreguntaFrecuente> lista = (List<PreguntaFrecuente>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<PreguntaFrecuente> listarPreguntasFrecuentesActivas() {
        List<PreguntaFrecuente> listaPreguntasFrecuentes = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new PreguntaFrecuente(), "activo", true, "borrado", false, "alcance", Alcance.APLICACION);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaPreguntasFrecuentes.add((PreguntaFrecuente) openSGSManagedBean);
        }
        return listaPreguntasFrecuentes;
    }

    public List<PreguntaFrecuente> listarPreguntasFrecuentesAplicacionesActivas() {
        List<PreguntaFrecuente> listaPreguntasFrecuentes = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new PreguntaFrecuente(), "activo", true, "borrado", false, "alcance", Alcance.APLICACIONES);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaPreguntasFrecuentes.add((PreguntaFrecuente) openSGSManagedBean);
        }
        return listaPreguntasFrecuentes;
    }

    public List<PreguntaFrecuente> listarPreguntasFrecuentesGlobalesActivas() {
        List<PreguntaFrecuente> listaPreguntasFrecuentes = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new PreguntaFrecuente(), "activo", true, "borrado", false, "alcance", Alcance.GLOBAL);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaPreguntasFrecuentes.add((PreguntaFrecuente) openSGSManagedBean);
        }
        return listaPreguntasFrecuentes;
    }

}
