package opensgs.sistema.persistencia.manejadores;

import java.util.ArrayList;
import java.util.List;
import opensgs.enums.Alcance;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.persistencia.conexiones.ConexionDataBase;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.datatypes.DtAnuncio;
import opensgs.sistema.logica.beans.Anuncio;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ManejadorAnuncio {

    private static ManejadorAnuncio instance = null;

    private ManejadorAnuncio() {
    }

    public static ManejadorAnuncio getInstance() {
        if (instance == null) {
            instance = new ManejadorAnuncio();
        }
        return instance;
    }

    public Anuncio obtenerAnuncio(DtAnuncio dtAnuncio) {
        return (Anuncio) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtAnuncio);
    }

    public List<Anuncio> listarAnunciosSistema(boolean borrado) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "FROM Anuncio AS a "
                    + "WHERE "
                    + "a.borrado = :borrado "
                    + "AND "
                    + "a.alcance != :alcanceAplicacion "
                    + "ORDER BY a.id ASC"
            );
            query.setParameter("borrado", borrado);
            query.setParameter("alcanceAplicacion", Alcance.APLICACION);
            List<Anuncio> lista = (List<Anuncio>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Anuncio> listarAnunciosAplicacion(Long aplicacionId, boolean borrado) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "SELECT an "
                    + "FROM Aplicacion AS ap "
                    + "JOIN ap.anuncios AS an "
                    + "WHERE "
                    + "an.borrado = :borrado "
                    + "AND "
                    + "ap.id = :aplicacionId "
                    + "ORDER BY an.id ASC"
            );
            query.setParameter("borrado", borrado);
            query.setParameter("aplicacionId", aplicacionId);
            List<Anuncio> lista = (List<Anuncio>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Anuncio> listarAnunciosSistemaVigentes() {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "FROM Anuncio AS a "
                    + "WHERE "
                    + "a.activo = :activo "
                    + "AND "
                    + "a.borrado = :borrado "
                    + "AND "
                    + "a.inicio <= CURRENT_TIMESTAMP "
                    + "AND "
                    + "a.fin >= CURRENT_TIMESTAMP "
                    + "AND "
                    + "(a.alcance = :alcanceSistema "
                    + "OR "
                    + "a.alcance = :alcanceGlobal) "
                    + "ORDER BY a.inicio ASC"
            );
            query.setParameter("activo", true);
            query.setParameter("borrado", false);
            query.setParameter("alcanceSistema", Alcance.SISTEMA);
            query.setParameter("alcanceGlobal", Alcance.GLOBAL);
            List<Anuncio> lista = (List<Anuncio>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Anuncio> listarAnunciosAplicacionesVigentes() {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "FROM Anuncio AS a "
                    + "WHERE "
                    + "a.activo = :activo "
                    + "AND "
                    + "a.borrado = :borrado "
                    + "AND "
                    + "a.inicio <= CURRENT_TIMESTAMP "
                    + "AND "
                    + "a.fin >= CURRENT_TIMESTAMP "
                    + "AND "
                    + "(a.alcance = :alcanceAplicaciones "
                    + "OR "
                    + "a.alcance = :alcanceGlobal) "
                    + "ORDER BY a.inicio ASC"
            );
            query.setParameter("activo", true);
            query.setParameter("borrado", false);
            query.setParameter("alcanceAplicaciones", Alcance.APLICACIONES);
            query.setParameter("alcanceGlobal", Alcance.GLOBAL);
            List<Anuncio> lista = (List<Anuncio>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Anuncio> listarAnunciosAplicacionVigentes(Long aplicacionId) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "SELECT an "
                    + "FROM Aplicacion AS ap "
                    + "JOIN ap.anuncios AS an "
                    + "WHERE "
                    + "an.activo = :activo "
                    + "AND "
                    + "an.borrado = :borrado "
                    + "AND "
                    + "an.inicio <= CURRENT_TIMESTAMP "
                    + "AND "
                    + "an.fin >= CURRENT_TIMESTAMP "
                    + "AND "
                    + "an.alcance = :alcanceAplicacion "
                    + "AND "
                    + "ap.id = :aplicacionId "
                    + "ORDER BY an.inicio ASC"
            );
            query.setParameter("activo", true);
            query.setParameter("borrado", false);
            query.setParameter("alcanceAplicacion", Alcance.APLICACION);
            query.setParameter("aplicacionId", aplicacionId);
            List<Anuncio> lista = (List<Anuncio>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Anuncio> listarAnunciosAplicacionActivos() {
        List<Anuncio> listaAnuncios = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new Anuncio(), "activo", true, "borrado", false, "alcance", Alcance.APLICACION);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaAnuncios.add((Anuncio) openSGSManagedBean);
        }
        return listaAnuncios;
    }

    public List<Anuncio> listarAnunciosAplicacionesActivos() {
        List<Anuncio> listaAnuncios = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new Anuncio(), "activo", true, "borrado", false, "alcance", Alcance.APLICACIONES);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaAnuncios.add((Anuncio) openSGSManagedBean);
        }
        return listaAnuncios;
    }

    public List<Anuncio> listarAnunciosGlobalesActivos() {
        List<Anuncio> listaAnuncios = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new Anuncio(), "activo", true, "borrado", false, "alcance", Alcance.GLOBAL);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaAnuncios.add((Anuncio) openSGSManagedBean);
        }
        return listaAnuncios;
    }

}
