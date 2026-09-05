package rw.ac.auca.photostudiomanagementsystem.util;

// Brings in Hibernate's own classes, not ones we wrote ourselves
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    // This method's whole job is to hand back
    // a working connection factory to whoever calls it.

    public SessionFactory getSessionFactory(){

        // Create a blank settings object, before config anything.

        Configuration configuration = new Configuration();

        // important line:
        // it goes and finds hibernate.cfg.xml on its own,
        // reads everything inside it (our database URL, username, password, dialect),
        // and fills the empty "configuration" object above with those real values.

        configuration.configure();

        // Now that configuration actually knows how to reach our database,
        // use it to build a SessionFactory, or connection.

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Send that finished SessionFactory back to whatever code called this method
        return sessionFactory;
    }
}