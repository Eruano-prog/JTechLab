import jakarta.persistence.*;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) throws Exception{
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        Cat cat = new Cat();
        cat.setName("Masanya");
        cat.setType("Home");

        try{

            transaction.begin();
            entityManager.persist(cat);
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
    }
}
