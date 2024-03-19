import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat();

        cat.setName("Masanya");
//            cat.setBirthDate(new Date());
        cat.setType("Home");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        if (sessionFactory == null) {
            return;
        }

        sessionFactory.inTransaction(session -> {
            session.persist(cat);
        });
    }
}
