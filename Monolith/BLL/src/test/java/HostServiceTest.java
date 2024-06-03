import Lab3.Models.Host;
import Lab3.Models.HostDTO;
import Lab3.Repositories.IHostRepository;
import Lab3.Services.HostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HostServiceTest {

    @Mock
    private IHostRepository hostRepository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private HostService hostService;

    @Test
    public void testAddHost() {
        HostDTO hostDTO = new HostDTO(0,"John", new Date(1990, 5, 10), new ArrayList<>(), "qwe123", "USER");

        hostService.addHost(hostDTO);

        verify(hostRepository, times(1)).save(any(Host.class));
    }

    @Test
    public void testDeleteHost() {
        String hostName = "John";
        when(hostRepository.findByName(hostName)).thenReturn(Optional.of(new Host(0,hostName, new Date(1990, 5, 10), new ArrayList<>(), "qwe123", "USER")));
        hostService.deleteHost(hostName);
        verify(hostRepository, times(1)).delete(any(Host.class));
    }

    @Test
    public void testGetHost() {
        String hostName = "John";
        when(hostRepository.findByName(hostName)).thenReturn(Optional.of(new Host(0,hostName, new Date(1990, 5, 10), new ArrayList<>(), "qwe123", "USER")));
        HostDTO hostDTO = hostService.getHost(hostName);
        assertEquals(hostName, hostDTO.getName());
    }

    @Test
    public void testModifyHost() {
        HostDTO hostDTO = new HostDTO(0, "John", new Date(1990, 5, 10), new ArrayList<>(), "qwe123", "USER");
        hostService.modifyHost(hostDTO);
        verify(hostRepository, times(1)).save(any(Host.class));
    }
}
