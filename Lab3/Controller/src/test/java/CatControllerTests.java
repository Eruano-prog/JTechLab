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


@ExtendWith(MockitoExtension.class)
public class CatControllerTests {

    @Mock
    private CatService catService;

    @InjectMocks
    private CatController catController;

    @Test
    public void testGetCat() throws Exception {
        Date specificDate = new Date(2024, Calendar.APRIL, 19);

        CatDTO cat = new CatDTO(0, "Fluffy", specificDate, "SomeType", catColor.WHITE, null, null);
        when(catService.getCat("Fluffy")).thenReturn(cat);

        catController.getCat(cat.name);

        verify(catService, times(1)).getCat(cat.name);
    }

    @Test
    public void testGetCatsByColor() throws Exception {
        Date specificDate = new Date(2024, Calendar.APRIL, 19);
        CatDTO cat1 = new CatDTO(0, "Fluffy", specificDate, "SomeType", catColor.WHITE, null, null);
        CatDTO cat2 = new CatDTO(0, "Snowball", specificDate, "AnotherType", catColor.WHITE, null, null);
        List<CatDTO> cats = Arrays.asList(cat1, cat2);

        when(catService.getCatsByColor(catColor.WHITE)).thenReturn(cats);

        catController.getCatsByColor(catColor.WHITE);
        verify(catService, times(1)).getCatsByColor(catColor.WHITE);
    }

    @Test
    public void testAddCat() throws Exception {
        Date specificDate = new Date(2024, Calendar.APRIL, 19);
        CatDTO cat = new CatDTO(0, "Fluffy", specificDate, "SomeType", catColor.WHITE, null, null);

        catController.addCat(cat);

        verify(catService, times(1)).addCat(cat);
    }

    @Test
    public void testModifyCat() throws Exception {
        Date specificDate = new Date(2024, Calendar.APRIL, 19);
        CatDTO cat = new CatDTO(0, "Fluffy", specificDate, "SomeType", catColor.WHITE, null, null);

        catController.modifyCat(cat);

        verify(catService, times(1)).modifyCat(cat);
    }

    @Test
    public void testDeleteCat() throws Exception {
        Date specificDate = new Date(2024, Calendar.APRIL, 19);
        CatDTO cat = new CatDTO(0, "Fluffy", specificDate, "SomeType", catColor.WHITE, null, null);

        catController.deleteCat(cat.name);

        verify(catService, times(1)).deleteCat(cat.name);
    }
}
