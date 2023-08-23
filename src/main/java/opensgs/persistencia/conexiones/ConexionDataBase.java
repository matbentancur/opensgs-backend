package opensgs.persistencia.conexiones;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConexionDataBase {
    
    private static ConexionDataBase instance = null;
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();
    
    private ConexionDataBase() {
    }
    
    public static ConexionDataBase getInstance() {
        if (instance == null) {
            instance = new ConexionDataBase();
        }
        return instance;
    }
    
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
        } catch (HibernateException ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session getSessionFactory() {
        return SESSION_FACTORY.openSession();
    }
}
