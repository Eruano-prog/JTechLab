import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Load configuration
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml"); // Assuming your configuration file is named hibernate.cfg.xml
            // Build session factory
            sessionFactory = configuration.buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

}
