import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HostRepositoryTest {

    @Mock
    private EntityManagerFactory entityManagerFactory;

    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    private HostRepository hostRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        hostRepository = new HostRepository(entityManagerFactory);
    }

    @Test
    public void testInsert() {
        Host host = new Host();
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(transaction);

        hostRepository.insert(host);

        verify(entityManager).persist(host);
        verify(transaction).commit();
        verify(entityManager).close();
        verify(entityManagerFactory).close();
    }

    @Test
    public void testGetHostByName() {
        String name = "TestHostName";
        Host host = new Host();
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(transaction);
        when(entityManager.find(Host.class, name)).thenReturn(host);

        Host result = hostRepository.getHostByName(name);

        verify(entityManager).find(Host.class, name);
        verify(transaction).commit();
        verify(entityManager).close();
        verify(entityManagerFactory).close();
        assertEquals(host, result);
    }

    @Test
    public void testUpdate() {
        Host host = new Host();
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(transaction);

        hostRepository.update(host);

        verify(entityManager).merge(host);
        verify(transaction).commit();
        verify(entityManager).close();
        verify(entityManagerFactory).close();
    }

    @Test
    public void testDelete() {
        Host host = new Host();
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(transaction);

        hostRepository.delete(host);

        verify(entityManager).detach(host);
        verify(transaction).commit();
        verify(entityManager).close();
        verify(entityManagerFactory).close();
    }
}
