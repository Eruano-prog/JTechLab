import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class CatRepositoryTests {

    @Mock
    private EntityManagerFactory entityManagerFactory;

    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    private CatRepository catRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        catRepository = new CatRepository(entityManagerFactory);
    }

    @Test
    public void testInsert() {
        Cat cat = new Cat();// Create a cat object
        cat.setName("defName");
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(transaction);

        catRepository.insert(cat);

        verify(entityManager).persist(cat);
        verify(transaction).commit();
        verify(entityManager).close();
        verify(entityManagerFactory).close();
    }

    @Test
    public void testGetCatByName() {
        Cat cat = new Cat();
        String name = "TestCatName";
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(transaction);
        when(entityManager.find(Cat.class, name)).thenReturn(cat);

        Cat result = catRepository.getCatByName(name);

        verify(entityManager).find(Cat.class, name);
        verify(transaction).commit();
        verify(entityManager).close();
        verify(entityManagerFactory).close();
        assertEquals(cat, result);
    }

    @Test
    public void testUpdate() {
        Cat cat = new Cat(); // Create a cat object
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(transaction);

        catRepository.update(cat);

        verify(entityManager).merge(cat);
        verify(transaction).commit();
        verify(entityManager).close();
        verify(entityManagerFactory).close();
    }

    @Test
    public void testDelete() {
        Cat cat = new Cat(); // Create a cat object
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(transaction);

        catRepository.delete(cat);

        verify(entityManager).detach(cat);
        verify(transaction).commit();
        verify(entityManager).close();
        verify(entityManagerFactory).close();
    }
}
