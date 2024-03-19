import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory != null){
            return sessionFactory;
        }

        var registry =
                new StandardServiceRegistryBuilder()
                        .build();
        try {
            SessionFactory Factory =
                    new MetadataSources(registry)
                            .addAnnotatedClass(Cat.class)
                            .addAnnotatedClass(Host.class)
                            .buildMetadata()
                            .buildSessionFactory();
            sessionFactory = Factory;
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we
            // had trouble building the SessionFactory so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
            return null;
        }
        return sessionFactory;
    }
}
