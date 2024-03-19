import org.hibernate.SessionFactory;

public class CatRepository {
    void Insert(Cat cat) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        if (sessionFactory == null) {
            return;
        }

        sessionFactory.inTransaction(session -> {
            session.persist(cat);
        });
    }
    public Cat getCatByName(String name) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        if (sessionFactory == null) {
            return null;
        }

        sessionFactory.inTransaction(session -> {
            Cat cat = session.createSelectionQuery("from cats where name = ?1", Cat.class)
                    .setParameter(1, name)
                    .getSingleResultOrNull();
            return cat;
        });
        return null;
    }
}
