import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class CatRepository {
    void Insert(Cat cat) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Lab2");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            entityManager.persist(cat);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
        finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
    public Cat getCatByName(String name) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Lab2");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        Cat cat = null;
        try{
            transaction.begin();
            cat = entityManager.find(Cat.class, name);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
        finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return cat;
    }

    public void update(Cat cat){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Lab2");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            cat = entityManager.merge(cat);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
        finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void delete(Cat cat){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Lab2");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            entityManager.detach(cat);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
        finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
