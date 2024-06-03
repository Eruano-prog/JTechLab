import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Lab3.Controllers.CatController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import Lab3.Models.CatDTO;
import Lab3.Models.catColor;
import Lab3.Services.CatService;
import org.springframework.security.core.Authentication;


@ExtendWith(MockitoExtension.class)
public class CatControllerTests {

    @Mock
    private CatService catService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private CatController catController;

    @Test
    public void testGetCat() {
        Date specificDate = new Date(2024, Calendar.APRIL, 19);
        CatDTO cat = new CatDTO(0, "Fluffy", specificDate, "SomeType", catColor.WHITE, null, null);

        when(catService.getCat("", "Fluffy")).thenReturn(cat);
        when(authentication.getName()).thenReturn("");

        catController.getCat(authentication, cat.name);

        verify(catService, times(1)).getCat("", cat.name);
    }

    @Test
    public void testGetCatsByColor() {
        Date specificDate = new Date(2024, Calendar.APRIL, 19);
        CatDTO cat1 = new CatDTO(0, "Fluffy", specificDate, "SomeType", catColor.WHITE, null, null);
        CatDTO cat2 = new CatDTO(0, "Snowball", specificDate, "AnotherType", catColor.WHITE, null, null);
        List<CatDTO> cats = Arrays.asList(cat1, cat2);

        when(catService.getCatsByColor("", catColor.WHITE)).thenReturn(cats);
        when(authentication.getName()).thenReturn("");

        catController.getCatsByColor(authentication, catColor.WHITE);
        verify(catService, times(1)).getCatsByColor("", catColor.WHITE);
    }

    @Test
    public void testAddCat() {
        Date specificDate = new Date(2024, Calendar.APRIL, 19);
        CatDTO cat = new CatDTO(0, "Fluffy", specificDate, "SomeType", catColor.WHITE, null, null);

        when(authentication.getName()).thenReturn("");

        catController.addCat(authentication, cat);

        verify(catService, times(1)).addCat("", cat);
    }

    @Test
    public void testModifyCat() throws Exception {
        Date specificDate = new Date(2024, Calendar.APRIL, 19);
        CatDTO cat = new CatDTO(0, "Fluffy", specificDate, "SomeType", catColor.WHITE, null, null);

        when(authentication.getName()).thenReturn("");

        catController.modifyCat(authentication, cat);

        verify(catService, times(1)).modifyCat("", cat);
    }

    @Test
    public void testDeleteCat() throws Exception {
        Date specificDate = new Date(2024, Calendar.APRIL, 19);
        CatDTO cat = new CatDTO(0, "Fluffy", specificDate, "SomeType", catColor.WHITE, null, null);

        when(authentication.getName()).thenReturn("");

        catController.deleteCat(authentication, cat.name);

        verify(catService, times(1)).deleteCat("", cat.name);
    }
}
