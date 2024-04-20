import Lab3.Models.Host;
import Lab3.Models.HostDTO;
import Lab3.Repositories.IHostRepository;
import Lab3.Services.HostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HostServiceTest {

    @Mock
    private IHostRepository hostRepository;

    @InjectMocks
    private HostService hostService;

    @Test
    public void testAddHost() {
        HostDTO hostDTO = new HostDTO("John", new Date(1990, 5, 10), new ArrayList<>());
        hostService.addHost(hostDTO);
        verify(hostRepository, times(1)).save(any(Host.class));
    }

    @Test
    public void testDeleteHost() {
        String hostName = "John";
        when(hostRepository.findById(hostName)).thenReturn(Optional.of(new Host(hostName, new Date(1990, 5, 10), new ArrayList<>())));
        hostService.deleteHost(hostName);
        verify(hostRepository, times(1)).delete(any(Host.class));
    }

    @Test
    public void testGetHost() {
        String hostName = "John";
        when(hostRepository.findById(hostName)).thenReturn(Optional.of(new Host(hostName, new Date(1990, 5, 10), new ArrayList<>())));
        HostDTO hostDTO = hostService.getHost(hostName);
        assertEquals(hostName, hostDTO.getName());
    }

    @Test
    public void testModifyHost() {
        HostDTO hostDTO = new HostDTO("John", new Date(1990, 5, 10), new ArrayList<>());
        hostService.modifyHost(hostDTO);
        verify(hostRepository, times(1)).save(any(Host.class));
    }
}
