package opensgs.persistencia.manejadores;

import opensgs.persistencia.conexiones.ConexionDataBase;
import java.util.List;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.logica.servicios.ServicioOpenSGSBean;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ManejadorOpenSGSBean {

    private static ManejadorOpenSGSBean instance = null;

    private ManejadorOpenSGSBean() {
    }

    public static ManejadorOpenSGSBean getInstance() {
        if (instance == null) {
            instance = new ManejadorOpenSGSBean();
        }
        return instance;
    }

    private OpenSGSBean executeQueryUniqueResult(Query query, Session session) {
        try {
            session.beginTransaction();
            OpenSGSBean bean = (OpenSGSBean) query.uniqueResult();
            session.getTransaction().commit();
            return bean;

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            this.cerrarSesion(session);
        }
    }

    private List<OpenSGSBean> executeQueryList(Query query, Session session) {
        session.beginTransaction();
        try {
            List<OpenSGSBean> lista = (List<OpenSGSBean>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            this.cerrarSesion(session);
        }
    }

    private List<OpenSGSManagedBean> executeQueryListManagedBean(Query query, Session session) {
        session.beginTransaction();
        try {
            List<OpenSGSManagedBean> lista = (List<OpenSGSManagedBean>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            this.cerrarSesion(session);
        }
    }

    private void cerrarSesion(Session session) {
        session.close();
    }

    public <T> OpenSGSBean obtenerOpenSGSBean(OpenSGSBean openSGSBean, String parametro, T valor) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        try {
            Query query = session.createQuery(""
                    + "FROM " + openSGSBean.getClass().getName()
                    + " WHERE " + parametro + " = :valor"
            );
            query.setParameter("valor", valor);
            return this.executeQueryUniqueResult(query, session);
        } catch (HibernateException e) {
            this.cerrarSesion(session);
            return null;
        }
    }

    public <T, T2> OpenSGSBean obtenerOpenSGSBean(OpenSGSBean openSGSBean, String parametro1, T valor1, String parametro2, T2 valor2) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        try {
            Query query = session.createQuery(""
                    + "FROM " + openSGSBean.getClass().getName()
                    + " WHERE " + parametro1 + " = :valor1"
                    + " AND " + parametro2 + " = :valor2"
            );
            query.setParameter("valor1", valor1);
            query.setParameter("valor2", valor2);
            return this.executeQueryUniqueResult(query, session);
        } catch (HibernateException e) {
            this.cerrarSesion(session);
            return null;
        }
    }

    public <T, T2, T3> OpenSGSBean obtenerOpenSGSBean(OpenSGSBean openSGSBean, String parametro1, T valor1, String parametro2, T2 valor2, String parametro3, T3 valor3) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        try {
            Query query = session.createQuery(""
                    + "FROM " + openSGSBean.getClass().getName()
                    + " WHERE " + parametro1 + " = :valor1"
                    + " AND " + parametro2 + " = :valor2"
                    + " AND " + parametro3 + " = :valor3"
            );
            query.setParameter("valor1", valor1);
            query.setParameter("valor2", valor2);
            query.setParameter("valor3", valor3);
            return this.executeQueryUniqueResult(query, session);
        } catch (HibernateException e) {
            this.cerrarSesion(session);
            return null;
        }
    }

    public <T, T2, T3, T4> OpenSGSBean obtenerOpenSGSBean(OpenSGSBean openSGSBean, String parametro1, T valor1, String parametro2, T2 valor2, String parametro3, T3 valor3, String parametro4, T4 valor4) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        try {
            Query query = session.createQuery(""
                    + "FROM " + openSGSBean.getClass().getName()
                    + " WHERE " + parametro1 + " = :valor1"
                    + " AND " + parametro2 + " = :valor2"
                    + " AND " + parametro3 + " = :valor3"
                    + " AND " + parametro4 + " = :valor4"
            );
            query.setParameter("valor1", valor1);
            query.setParameter("valor2", valor2);
            query.setParameter("valor3", valor3);
            query.setParameter("valor4", valor4);
            return this.executeQueryUniqueResult(query, session);
        } catch (HibernateException e) {
            this.cerrarSesion(session);
            return null;
        }
    }

    public OpenSGSBean obtenerOpenSGSBean(OpenSGSBean openSGSBean, Long id) {
        return this.obtenerOpenSGSBean(openSGSBean, "id", id);
    }

    public OpenSGSBean obtenerOpenSGSBean(String className, Long id) {
        OpenSGSBean openSGSBean = ServicioOpenSGSBean.getInstance().parseClassName(className);
        return this.obtenerOpenSGSBean(openSGSBean, "id", id);
    }

    public <T> OpenSGSBean obtenerOpenSGSBean(String className, String parametro, T valor) {
        OpenSGSBean openSGSBean = ServicioOpenSGSBean.getInstance().parseClassName(className);
        return this.obtenerOpenSGSBean(openSGSBean, parametro, valor);
    }

    public <T, T2> OpenSGSBean obtenerOpenSGSBean(String className, String parametro1, T valor1, String parametro2, T2 valor2) {
        OpenSGSBean openSGSBean = ServicioOpenSGSBean.getInstance().parseClassName(className);
        return this.obtenerOpenSGSBean(openSGSBean, parametro1, valor1, parametro2, valor2);
    }

    public <T, T2, T3> OpenSGSBean obtenerOpenSGSBean(String className, String parametro1, T valor1, String parametro2, T2 valor2, String parametro3, T3 valor3) {
        OpenSGSBean openSGSBean = ServicioOpenSGSBean.getInstance().parseClassName(className);
        return this.obtenerOpenSGSBean(openSGSBean, parametro1, valor1, parametro2, valor2, parametro3, valor3);
    }

    public <T, T2, T3, T4> OpenSGSBean obtenerOpenSGSBean(String className, String parametro1, T valor1, String parametro2, T2 valor2, String parametro3, T3 valor3, String parametro4, T4 valor4) {
        OpenSGSBean openSGSBean = ServicioOpenSGSBean.getInstance().parseClassName(className);
        return this.obtenerOpenSGSBean(openSGSBean, parametro1, valor1, parametro2, valor2, parametro3, valor3, parametro4, valor4);
    }

    public OpenSGSBean obtenerOpenSGSBean(DtOpenSGSBean dtOpenSGSBean, Long id) {
        OpenSGSBean openSGSBean = ServicioOpenSGSBean.getInstance().parseDtOpenSGSBean(dtOpenSGSBean);
        return this.obtenerOpenSGSBean(openSGSBean, "id", id);
    }

    public OpenSGSBean obtenerOpenSGSBean(DtOpenSGSBean dtOpenSGSBean) {
        OpenSGSBean openSGSBean = ServicioOpenSGSBean.getInstance().parseDtOpenSGSBean(dtOpenSGSBean);
        return this.obtenerOpenSGSBean(openSGSBean, "id", dtOpenSGSBean.getId());
    }

    public <T> OpenSGSBean obtenerOpenSGSBean(DtOpenSGSBean dtOpenSGSBean, String parametro, T valor) {
        OpenSGSBean openSGSBean = ServicioOpenSGSBean.getInstance().parseDtOpenSGSBean(dtOpenSGSBean);
        return this.obtenerOpenSGSBean(openSGSBean, parametro, valor);
    }

    public <T, T2> OpenSGSBean obtenerOpenSGSBean(DtOpenSGSBean dtOpenSGSBean, String parametro1, T valor1, String parametro2, T2 valor2) {
        OpenSGSBean openSGSBean = ServicioOpenSGSBean.getInstance().parseDtOpenSGSBean(dtOpenSGSBean);
        return this.obtenerOpenSGSBean(openSGSBean, parametro1, valor1, parametro2, valor2);
    }

    public <T, T2, T3> OpenSGSBean obtenerOpenSGSBean(DtOpenSGSBean dtOpenSGSBean, String parametro1, T valor1, String parametro2, T2 valor2, String parametro3, T3 valor3) {
        OpenSGSBean openSGSBean = ServicioOpenSGSBean.getInstance().parseDtOpenSGSBean(dtOpenSGSBean);
        return this.obtenerOpenSGSBean(openSGSBean, parametro1, valor1, parametro2, valor2, parametro3, valor3);
    }

    public <T, T2, T3, T4> OpenSGSBean obtenerOpenSGSBean(DtOpenSGSBean dtOpenSGSBean, String parametro1, T valor1, String parametro2, T2 valor2, String parametro3, T3 valor3, String parametro4, T4 valor4) {
        OpenSGSBean openSGSBean = ServicioOpenSGSBean.getInstance().parseDtOpenSGSBean(dtOpenSGSBean);
        return this.obtenerOpenSGSBean(openSGSBean, parametro1, valor1, parametro2, valor2, parametro3, valor3, parametro4, valor4);
    }

    public List<OpenSGSBean> listarOpenSGSBean(OpenSGSBean openSGSBean) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        try {
            Query query = session.createQuery(""
                    + "FROM " + openSGSBean.getClass().getName()
                    + " ORDER BY id ASC"
            );
            return this.executeQueryList(query, session);
        } catch (HibernateException e) {
            this.cerrarSesion(session);
            return null;
        }
    }

    public List<OpenSGSBean> listarOpenSGSBean(String className) {
        OpenSGSBean openSGSBean = ServicioOpenSGSBean.getInstance().parseClassName(className);
        return this.listarOpenSGSBean(openSGSBean);
    }

    public <T> List<OpenSGSManagedBean> listarOpenSGSManagedBean(OpenSGSManagedBean openSGSManagedBean, String parametro, T valor) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        try {
            Query query = session.createQuery(""
                    + "FROM " + openSGSManagedBean.getClass().getName()
                    + " WHERE " + parametro + " = :valor"
                    + " ORDER BY id ASC"
            );
            query.setParameter("valor", valor);
            return this.executeQueryListManagedBean(query, session);
        } catch (HibernateException e) {
            this.cerrarSesion(session);
            return null;
        }
    }

    public <T, T2> List<OpenSGSManagedBean> listarOpenSGSManagedBean(OpenSGSManagedBean openSGSManagedBean, String parametro1, T valor1, String parametro2, T2 valor2) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        try {
            Query query = session.createQuery(""
                    + "FROM " + openSGSManagedBean.getClass().getName()
                    + " WHERE " + parametro1 + " = :valor1"
                    + " AND " + parametro2 + " = :valor2"
                    + " ORDER BY id ASC"
            );
            query.setParameter("valor1", valor1);
            query.setParameter("valor2", valor2);
            return this.executeQueryListManagedBean(query, session);
        } catch (HibernateException e) {
            this.cerrarSesion(session);
            return null;
        }
    }

    public <T, T2, T3> List<OpenSGSManagedBean> listarOpenSGSManagedBean(OpenSGSManagedBean openSGSManagedBean, String parametro1, T valor1, String parametro2, T2 valor2, String parametro3, T3 valor3) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        try {
            Query query = session.createQuery(""
                    + "FROM " + openSGSManagedBean.getClass().getName()
                    + " WHERE " + parametro1 + " = :valor1"
                    + " AND " + parametro2 + " = :valor2"
                    + " AND " + parametro3 + " = :valor3"
                    + " ORDER BY id ASC"
            );
            query.setParameter("valor1", valor1);
            query.setParameter("valor2", valor2);
            query.setParameter("valor3", valor3);
            return this.executeQueryListManagedBean(query, session);
        } catch (HibernateException e) {
            this.cerrarSesion(session);
            return null;
        }
    }

    public <T, T2, T3, T4> List<OpenSGSManagedBean> listarOpenSGSManagedBean(OpenSGSManagedBean openSGSManagedBean, String parametro1, T valor1, String parametro2, T2 valor2, String parametro3, T3 valor3, String parametro4, T4 valor4) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        try {
            Query query = session.createQuery(""
                    + "FROM " + openSGSManagedBean.getClass().getName()
                    + " WHERE " + parametro1 + " = :valor1"
                    + " AND " + parametro2 + " = :valor2"
                    + " AND " + parametro3 + " = :valor3"
                    + " AND " + parametro4 + " = :valor4"
                    + " ORDER BY id ASC"
            );
            query.setParameter("valor1", valor1);
            query.setParameter("valor2", valor2);
            query.setParameter("valor3", valor3);
            query.setParameter("valor4", valor4);
            return this.executeQueryListManagedBean(query, session);
        } catch (HibernateException e) {
            this.cerrarSesion(session);
            return null;
        }
    }

    public <T> List<OpenSGSManagedBean> listarOpenSGSManagedBean(String className, String parametro, T valor) {
        OpenSGSManagedBean openSGSManagedBean = (OpenSGSManagedBean) ServicioOpenSGSBean.getInstance().parseClassName(className);
        return this.listarOpenSGSManagedBean(openSGSManagedBean, parametro, valor);
    }

    public <T, T2> List<OpenSGSManagedBean> listarOpenSGSManagedBean(String className, String parametro1, T valor1, String parametro2, T2 valor2) {
        OpenSGSManagedBean openSGSManagedBean = (OpenSGSManagedBean) ServicioOpenSGSBean.getInstance().parseClassName(className);
        return this.listarOpenSGSManagedBean(openSGSManagedBean, parametro1, valor1, parametro2, valor2);
    }

    public <T, T2, T3> List<OpenSGSManagedBean> listarOpenSGSManagedBean(String className, String parametro1, T valor1, String parametro2, T2 valor2, String parametro3, T3 valor3) {
        OpenSGSManagedBean openSGSManagedBean = (OpenSGSManagedBean) ServicioOpenSGSBean.getInstance().parseClassName(className);
        return this.listarOpenSGSManagedBean(openSGSManagedBean, parametro1, valor1, parametro2, valor2, parametro3, valor3);
    }

    public <T, T2, T3, T4> List<OpenSGSManagedBean> listarOpenSGSManagedBean(String className, String parametro1, T valor1, String parametro2, T2 valor2, String parametro3, T3 valor3, String parametro4, T4 valor4) {
        OpenSGSManagedBean openSGSManagedBean = (OpenSGSManagedBean) ServicioOpenSGSBean.getInstance().parseClassName(className);
        return this.listarOpenSGSManagedBean(openSGSManagedBean, parametro1, valor1, parametro2, valor2, parametro3, valor3, parametro4, valor4);
    }

    public void crearOpenSGSBean(OpenSGSBean openSGSBean) throws Exception {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        try {
            session.beginTransaction();
            session.save(openSGSBean);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            Exception ex = new Exception();
            ex.setStackTrace(e.getStackTrace());
            throw ex;
        } finally {
            this.cerrarSesion(session);
        }
    }

    public void modificarOpenSGSBean(OpenSGSBean openSGSBean) throws Exception {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        try {
            session.beginTransaction();
            session.update(openSGSBean);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            Exception ex = new Exception();
            ex.setStackTrace(e.getStackTrace());
            throw ex;
        } finally {
            this.cerrarSesion(session);
        }
    }

    public void borrarOpenSGSBean(OpenSGSBean openSGSBean) throws Exception {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        try {
            session.beginTransaction();
            session.delete(openSGSBean);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            Exception ex = new Exception();
            ex.setStackTrace(e.getStackTrace());
            throw ex;
        } finally {
            this.cerrarSesion(session);
        }
    }

    public Integer contarOpenSGSBean(OpenSGSBean openSGSBean) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        try {
            Query query = session.createQuery("SELECT COUNT(*) FROM " + openSGSBean.getClass().getName());
            List lista = this.executeQueryList(query, session);
            if (!lista.isEmpty()) {
                return Integer.parseInt(lista.get(0).toString());
            } else {
                return 0;
            }
        } catch (HibernateException e) {
            this.cerrarSesion(session);
            return null;
        }
    }

    public Integer contarOpenSGSBean(String className) {
        OpenSGSBean openSGSBean = ServicioOpenSGSBean.getInstance().parseClassName(className);
        return this.contarOpenSGSBean(openSGSBean);
    }

    public List<Object[]> ejecutarSQL(String sql) throws Exception {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createNativeQuery(sql);
            List<Object[]> lista = query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            Exception ex = new Exception();
            ex.setStackTrace(e.getStackTrace());
            throw ex;
        } finally {
            this.cerrarSesion(session);
        }

    }

}
