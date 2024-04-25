import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import Lab3.Controllers.CatController;
import Lab3.Models.CatDTO;
import Lab3.Models.catColor;
import Lab3.Services.CatService;

@WebMvcTest(CatController.class)
@ContextConfiguration(classes = CatController.class)
public class CatControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatService catService;

    @Test
    public void testGetCat() throws Exception {
        Date specificDate = new Date(2024, Calendar.APRIL, 19);

        CatDTO cat = new CatDTO("Fluffy", specificDate, "SomeType", catColor.WHITE, null, null);
        when(catService.getCat("Fluffy")).thenReturn(cat);

        mockMvc.perform(get("/cat").param("name", "Fluffy"))
                .andExpect(status().isOk())
                .andExpect(content().json("{"
                        + "\"name\":\"Fluffy\","
                        + "\"birthDate\":\"" + "3924-04-18T21:00:00.000+00:00" + "\","
                        + "\"type\":\"SomeType\","
                        + "\"color\":\"" + cat.color.name() + "\","
                        + "\"host\":null,"
                        + "\"friends\":null"
                        + "}"));
    }

    @Test
    public void testGetCatsByColor() throws Exception {
        Date specificDate = new Date(2024, Calendar.APRIL, 19);
        CatDTO cat1 = new CatDTO("Fluffy", specificDate, "SomeType", catColor.WHITE, null, null);
        CatDTO cat2 = new CatDTO("Snowball", specificDate, "AnotherType", catColor.WHITE, null, null);
        List<CatDTO> cats = Arrays.asList(cat1, cat2);

        when(catService.getCatsByColor(catColor.WHITE)).thenReturn(cats);

        mockMvc.perform(get("/cat").param("color", "WHITE"))
                .andExpect(status().isOk())
                .andExpect(content().json("["
                        + "{"
                        + "\"name\":\"Fluffy\","
                        + "\"birthDate\":\"3924-04-18T21:00:00.000+00:00\","
                        + "\"type\":\"SomeType\","
                        + "\"color\":\"WHITE\","
                        + "\"host\":null,"
                        + "\"friends\":null"
                        + "},"
                        + "{"
                        + "\"name\":\"Snowball\","
                        + "\"birthDate\":\"3924-04-18T21:00:00.000+00:00\","
                        + "\"type\":\"AnotherType\","
                        + "\"color\":\"WHITE\","
                        + "\"host\":null,"
                        + "\"friends\":null"
                        + "}]"));
    }

    @Test
    public void testAddCat() throws Exception {
        mockMvc.perform(post("/cat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"name\":\"Fluffy\","
                                + "\"birthDate\":\"3924-04-18T21:00:00.000+00:00\","
                                + "\"type\":\"SomeType\","
                                + "\"color\":\"WHITE\","
                                + "\"host\":null,"
                                + "\"friends\":null"
                                + "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Cat added successfully"));
    }

    @Test
    public void testModifyCat() throws Exception {
        mockMvc.perform(put("/cat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"name\":\"Fluffy\","
                                + "\"birthDate\":\"3924-04-18T21:00:00.000+00:00\","
                                + "\"type\":\"SomeType\","
                                + "\"color\":\"WHITE\","
                                + "\"host\":null,"
                                + "\"friends\":null"
                                + "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cat modified successfully"));
    }

    @Test
    public void testDeleteCat() throws Exception {
        mockMvc.perform(delete("/cat")
                        .param("name", "Fluffy"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cat deleted successfully"));
    }
}
