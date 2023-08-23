package opensgs.sistema.persistencia.manejadores;

import java.util.Date;
import java.util.List;
import opensgs.enums.Elemento;
import opensgs.persistencia.conexiones.ConexionDataBase;
import opensgs.sistema.logica.beans.Actividad;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;


public class ManejadorActividad {
    
    private static ManejadorActividad instance = null;
    
    private ManejadorActividad() {
    }
    
    public static ManejadorActividad getInstance() {
        if (instance == null) {
            instance = new ManejadorActividad();
        }
        return instance;
    }
    
    public List<Actividad> listarActividadElemento(Elemento elemento, Long identificador) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "FROM Actividad AS a "
                    + "WHERE "
                    + "elemento = :elemento "
                    + "AND "
                    + "identificador = :identificador "
                    + "ORDER BY a.creacion ASC"
            );
            query.setParameter("elemento", elemento);
            query.setParameter("identificador", identificador);
            List<Actividad> lista = (List<Actividad>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;        
        } finally{
            session.close();
        }
    }
    
    public List<Actividad> listarActividadTemporal(Date inicio, Date fin) {
        Session session = ConexionDataBase.getInstance().getSessionFactory();
        session.beginTransaction();
        try {
            Query query = session.createQuery(""
                    + "FROM Actividad AS a "
                    + "WHERE "
                    + "creacion >= :inicio "
                    + "AND "
                    + "creacion <= :fin "
                    + "ORDER BY a.creacion ASC"
            );
            query.setParameter("inicio", inicio);
            query.setParameter("fin", fin);
            List<Actividad> lista = (List<Actividad>) query.list();
            session.getTransaction().commit();
            return lista;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return null;        
        } finally{
            session.close();
        }
    }
    
}
