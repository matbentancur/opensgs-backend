package opensgs.sistema.persistencia.manejadores;

import java.util.ArrayList;
import java.util.List;
import opensgs.sistema.enums.AlcanceArchivo;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.persistencia.conexiones.ConexionDataBase;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.datatypes.DtArchivo;
import opensgs.sistema.logica.beans.Archivo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ManejadorArchivo {

    private static ManejadorArchivo instance = null;

    private ManejadorArchivo() {
    }

    public static ManejadorArchivo getInstance() {
        if (instance == null) {
            instance = new ManejadorArchivo();
        }
        return instance;
    }

    public Archivo obtenerArchivo(DtArchivo dtArchivo) {
        return (Archivo) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtArchivo);
    }

    public Archivo obtenerArchivo(Long id) {
        return (Archivo) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new Archivo(), "id", id);
    }

    public Archivo obtenerArchivo(String nombre) {
        return (Archivo) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new Archivo(), "nombre", nombre);
    }

    public void crearArchivo(Archivo archivo) throws Exception {
        ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(archivo);
    }

    public void modificarArchivo(Archivo archivo) throws Exception {
        ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(archivo);
    }

    public List<Archivo> listarArchivosSistema(boolean borrado) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "FROM Archivo AS ar "
                    + "WHERE "
                    + "ar.borrado = :borrado "
                    + "AND "
                    + "ar.alcanceArchivo != :alcanceArchivo "
                    + "ORDER BY ar.id ASC"
            );
            query.setParameter("borrado", borrado);
            query.setParameter("alcanceArchivo", AlcanceArchivo.APLICACION);
            List<Archivo> lista = (List<Archivo>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Archivo> listarArchivosAplicacion(Long aplicacionId, boolean borrado) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "SELECT ar "
                    + "FROM Aplicacion AS ap "
                    + "JOIN ap.archivos AS ar "
                    + "WHERE "
                    + "ar.borrado = :borrado "
                    + "AND "
                    + "ap.id = :aplicacionId "
                    + "ORDER BY ar.id ASC"
            );
            query.setParameter("borrado", borrado);
            query.setParameter("aplicacionId", aplicacionId);
            List<Archivo> lista = (List<Archivo>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Archivo> listarArchivosSistemaActivos() {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "FROM Archivo AS a "
                    + "WHERE "
                    + "activo = :activo "
                    + "AND "
                    + "borrado = :borrado "
                    + "AND "
                    + "(alcanceArchivo = :alcanceSistema "
                    + "OR "
                    + "alcanceArchivo = :alcanceGlobal) "
                    + "ORDER BY a.id ASC"
            );
            query.setParameter("activo", true);
            query.setParameter("borrado", false);
            query.setParameter("alcanceSistema", AlcanceArchivo.SISTEMA);
            query.setParameter("alcanceGlobal", AlcanceArchivo.GLOBAL);
            List<Archivo> lista = (List<Archivo>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Archivo> listarArchivosAplicacionActivos(Long aplicacionId) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "SELECT ar "
                    + "FROM Aplicacion AS ap "
                    + "JOIN ap.archivos AS ar "
                    + "WHERE "
                    + "ar.activo = :activo "
                    + "AND "
                    + "ar.borrado = :borrado "
                    + "AND "
                    + "ar.alcanceArchivo = :alcanceAplicacion "
                    + "AND "
                    + "ap.id = :aplicacionId "
                    + "ORDER BY ar.id ASC"
            );
            query.setParameter("activo", true);
            query.setParameter("borrado", false);
            query.setParameter("alcanceAplicacion", AlcanceArchivo.APLICACION);
            query.setParameter("aplicacionId", aplicacionId);
            List<Archivo> lista = (List<Archivo>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Archivo> listarArchivosActivos() {
        List<Archivo> listaArchivos = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new Archivo(), "activo", true, "borrado", false, "alcanceArchivo", AlcanceArchivo.APLICACION);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaArchivos.add((Archivo) openSGSManagedBean);
        }
        return listaArchivos;
    }

    public List<Archivo> listarArchivosAplicacionesActivos() {
        List<Archivo> listaArchivos = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new Archivo(), "activo", true, "borrado", false, "alcanceArchivo", AlcanceArchivo.APLICACIONES);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaArchivos.add((Archivo) openSGSManagedBean);
        }
        return listaArchivos;
    }

    public List<Archivo> listarArchivosGlobalesActivos() {
        List<Archivo> listaArchivos = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new Archivo(), "activo", true, "borrado", false, "alcanceArchivo", AlcanceArchivo.GLOBAL);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaArchivos.add((Archivo) openSGSManagedBean);
        }
        return listaArchivos;
    }

}
