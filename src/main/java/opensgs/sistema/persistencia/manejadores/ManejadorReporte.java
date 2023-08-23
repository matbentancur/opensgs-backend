package opensgs.sistema.persistencia.manejadores;

import java.util.ArrayList;
import java.util.List;
import opensgs.enums.Alcance;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.persistencia.conexiones.ConexionDataBase;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.datatypes.DtReporte;
import opensgs.sistema.logica.beans.Reporte;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ManejadorReporte {

    private static ManejadorReporte instance = null;

    private ManejadorReporte() {
    }

    public static ManejadorReporte getInstance() {
        if (instance == null) {
            instance = new ManejadorReporte();
        }
        return instance;
    }

    public Reporte obtenerReporte(DtReporte dtReporte) {
        return (Reporte) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtReporte);
    }

    public Reporte obtenerReporte(Long id) {
        return (Reporte) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new Reporte(), "id", id);
    }

    public List<Reporte> listarReportesSistema(boolean borrado) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "FROM Reporte AS r "
                    + "WHERE "
                    + "r.borrado = :borrado "
                    + "AND "
                    + "r.alcance != :alcanceAplicacion "
                    + "ORDER BY r.id ASC"
            );
            query.setParameter("borrado", borrado);
            query.setParameter("alcanceAplicacion", Alcance.APLICACION);
            List<Reporte> lista = (List<Reporte>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Reporte> listarReportesAplicacion(Long aplicacionId, boolean borrado) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "SELECT r "
                    + "FROM Aplicacion AS ap "
                    + "JOIN ap.reportes AS r "
                    + "WHERE "
                    + "r.borrado = :borrado "
                    + "AND "
                    + "ap.id = :aplicacionId "
                    + "ORDER BY r.id ASC"
            );
            query.setParameter("borrado", borrado);
            query.setParameter("aplicacionId", aplicacionId);
            List<Reporte> lista = (List<Reporte>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Reporte> listarReportesActivos() {
        List<Reporte> listaReportes = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new Reporte(), "activo", true, "borrado", false, "alcance", Alcance.APLICACION);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaReportes.add((Reporte) openSGSManagedBean);
        }
        return listaReportes;
    }

    public List<Reporte> listarReportesAplicacionesActivos() {
        List<Reporte> listaReportes = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new Reporte(), "activo", true, "borrado", false, "alcance", Alcance.APLICACIONES);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaReportes.add((Reporte) openSGSManagedBean);
        }
        return listaReportes;
    }

    public List<Reporte> listarReportesGlobalesActivos() {
        List<Reporte> listaReportes = new ArrayList<>();

        List<OpenSGSManagedBean> listaOpenSGSManagedBean = ManejadorOpenSGSBean.getInstance().listarOpenSGSManagedBean(new Reporte(), "activo", true, "borrado", false, "alcance", Alcance.GLOBAL);
        for (OpenSGSManagedBean openSGSManagedBean : listaOpenSGSManagedBean) {
            listaReportes.add((Reporte) openSGSManagedBean);
        }
        return listaReportes;
    }

}
