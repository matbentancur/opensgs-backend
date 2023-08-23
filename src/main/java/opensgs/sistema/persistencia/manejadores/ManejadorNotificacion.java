package opensgs.sistema.persistencia.manejadores;

import java.util.ArrayList;
import opensgs.sistema.datatypes.DtNotificacion;
import opensgs.enums.Elemento;
import opensgs.enums.Operacion;
import opensgs.sistema.logica.beans.Notificacion;
import java.util.List;
import opensgs.enums.Alcance;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.persistencia.conexiones.ConexionDataBase;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ManejadorNotificacion {

    private static ManejadorNotificacion instance = null;

    private ManejadorNotificacion() {
    }

    public static ManejadorNotificacion getInstance() {
        if (instance == null) {
            instance = new ManejadorNotificacion();
        }
        return instance;
    }

    public Notificacion obtenerNotificacion(DtNotificacion dtNotificacion) {
        return (Notificacion) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtNotificacion);
    }

    public List<Notificacion> listarNotificacion(Operacion operacion, Elemento elemento) {
        List<Notificacion> listaNotificacion = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new Notificacion(), "operacion", operacion, "elemento", elemento);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaNotificacion.add((Notificacion) openSGSManagedBean);
        }
        return listaNotificacion;
    }

    public List<Notificacion> listarNotificacionesSistemaActivas(Operacion operacion, Elemento elemento) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "FROM Notificacion AS n "
                    + "WHERE "
                    + "activo = :activo "
                    + "AND "
                    + "borrado = :borrado "
                    + "AND "
                    + "operacion = :operacion "
                    + "AND "
                    + "elemento = :elemento "
                    + "AND "
                    + "(alcance = :alcanceSistema "
                    + "OR "
                    + "alcance = :alcanceGlobal)"
            );
            query.setParameter("activo", true);
            query.setParameter("borrado", false);
            query.setParameter("operacion", operacion);
            query.setParameter("elemento", elemento);
            query.setParameter("alcanceSistema", Alcance.SISTEMA);
            query.setParameter("alcanceGlobal", Alcance.GLOBAL);
            List<Notificacion> lista = (List<Notificacion>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public void modificarNotificacion(Notificacion notificacion) throws Exception {
        ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(notificacion);
    }

    public List<Notificacion> listarNotificacionesSistema(boolean borrado) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "FROM Notificacion AS no "
                    + "WHERE "
                    + "no.borrado = :borrado "
                    + "AND "
                    + "no.alcance != :alcanceAplicacion "
                    + "ORDER BY no.id ASC"
            );
            query.setParameter("borrado", borrado);
            query.setParameter("alcanceAplicacion", Alcance.APLICACION);
            List<Notificacion> lista = (List<Notificacion>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Notificacion> listarNotificacionesAplicacion(Long aplicacionId, boolean borrado) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "SELECT no "
                    + "FROM Aplicacion AS ap "
                    + "JOIN ap.notificaciones AS no "
                    + "WHERE "
                    + "no.borrado = :borrado "
                    + "AND "
                    + "ap.id = :aplicacionId "
                    + "ORDER BY no.id ASC"
            );
            query.setParameter("borrado", borrado);
            query.setParameter("aplicacionId", aplicacionId);
            List<Notificacion> lista = (List<Notificacion>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Notificacion> listarNotificacionesActivas() {
        List<Notificacion> listaNotificaciones = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new Notificacion(), "activo", true, "borrado", false, "alcance", Alcance.APLICACION);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaNotificaciones.add((Notificacion) openSGSManagedBean);
        }
        return listaNotificaciones;
    }

    public List<Notificacion> listarNotificacionesAplicacionesActivas() {
        List<Notificacion> listaNotificaciones = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new Notificacion(), "activo", true, "borrado", false, "alcance", Alcance.APLICACIONES);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaNotificaciones.add((Notificacion) openSGSManagedBean);
        }
        return listaNotificaciones;
    }

    public List<Notificacion> listarNotificacionesGlobalesActivas() {
        List<Notificacion> listaNotificaciones = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new Notificacion(), "activo", true, "borrado", false, "alcance", Alcance.GLOBAL);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaNotificaciones.add((Notificacion) openSGSManagedBean);
        }
        return listaNotificaciones;
    }

}
