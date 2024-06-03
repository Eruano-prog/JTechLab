import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import Lab3.Controllers.HostController;
import Lab3.Models.HostDTO;
import Lab3.Services.HostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.Date;
import java.util.Collections;


@ExtendWith(MockitoExtension.class)
public class HostControllerTest {

    @Mock
    private HostService hostService;

    @InjectMocks
    private HostController hostController;

    @Test
    public void testGetHost() throws Exception {
        Date specificDate = new Date(2024, Calendar.APRIL, 19);
        HostDTO host = new HostDTO(0, "TestHost", specificDate, Collections.emptyList(), "qwe123", "USER");

        when(hostService.getHost("TestHost")).thenReturn(host);

        hostController.getHost(host.name);

        verify(hostService, times(1)).getHost(host.name);
    }

    @Test
    public void testAddHost() throws Exception {
        Date specificDate = new Date(2024, Calendar.APRIL, 19);
        HostDTO host = new HostDTO(0, "TestHost", specificDate, Collections.emptyList(), "qwe123", "USER");

        hostController.addHost(host);

        verify(hostService, times(1)).addHost(host);
    }

    @Test
    public void testModifyHost() throws Exception {
        Date specificDate = new Date(2024, Calendar.APRIL, 19);
        HostDTO host = new HostDTO(0, "TestHost", specificDate, Collections.emptyList(), "qwe123", "USER");

        hostController.modifyHost(host);

        verify(hostService, times(1)).modifyHost(host);
    }

    @Test
    public void testDeleteHost() throws Exception {
        Date specificDate = new Date(2024, Calendar.APRIL, 19);
        HostDTO host = new HostDTO(0, "TestHost", specificDate, Collections.emptyList(), "qwe123", "USER");

        hostController.deleteHost(host.name);

        verify(hostService, times(1)).deleteHost(host.name);
    }
}
