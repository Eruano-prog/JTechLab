import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import Lab3.Controllers.HostController;
import Lab3.Models.HostDTO;
import Lab3.Services.HostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;
import java.util.Date;
import java.util.Collections;

@WebMvcTest(HostController.class)
@ContextConfiguration(classes = HostController.class)
public class HostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HostService hostService;

//    @Test
//    public void testGetHost() throws Exception {
//        Date specificDate = new Date(2024, Calendar.APRIL, 19);
//        HostDTO host = new HostDTO("TestHost", specificDate, Collections.emptyList());
//
//        when(hostService.getHost("TestHost")).thenReturn(host);
//
//        mockMvc.perform(get("/hosts/TestHost"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{"
//                        + "\"name\":\"TestHost\","
//                        + "\"birthDate\":\"" + "3924-04-18T21:00:00.000+00:00" + "\","
//                        + "\"cats\":[]"
//                        + "}"));
//    }
//
//    @Test
//    public void testAddHost() throws Exception {
//        mockMvc.perform(post("/hosts")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{"
//                                + "\"name\":\"TestHost\","
//                                + "\"birthDate\":\"3924-04-18T21:00:00.000+00:00\","
//                                + "\"cats\":[]"
//                                + "}"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Host added"));
//    }
//
//    @Test
//    public void testModifyHost() throws Exception {
//        mockMvc.perform(put("/hosts/TestHost")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{"
//                                + "\"name\":\"TestHost\","
//                                + "\"birthDate\":\"3924-04-18T21:00:00.000+00:00\","
//                                + "\"cats\":[]"
//                                + "}"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Host modified"));
//    }
//
//    @Test
//    public void testDeleteHost() throws Exception {
//        mockMvc.perform(delete("/hosts/TestHost"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Host deleted"));
//    }
}
