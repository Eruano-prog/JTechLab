import Lab3.Models.Cat;
import Lab3.Models.CatDTO;
import Lab3.Models.catColor;
import Lab3.Repositories.ICatRepository;
import Lab3.Services.CatService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatServiceTest {

    @Mock
    private ICatRepository catRepository;

    @InjectMocks
    private CatService catService;

    @Test
    public void testAddCat() {
        CatDTO catDTO = new CatDTO(0, "Fluffy", new Date(2020, 5, 10), "Persian", catColor.WHITE, null, new ArrayList<>());
        catService.addCat(catDTO);
        verify(catRepository, times(1)).save(any(Cat.class));
    }

    @Test
    public void testDeleteCat() {
        String catName = "Fluffy";
        when(catRepository.findById(catName)).thenReturn(Optional.of(new Cat(0, catName, new Date(2020, 5, 10), "Persian", catColor.WHITE, null, new ArrayList<>())));
        catService.deleteCat(catName);
        verify(catRepository, times(1)).delete(any(Cat.class));
    }

    @Test
    public void testGetCat() {
        String catName = "Fluffy";
        when(catRepository.findById(catName)).thenReturn(Optional.of(new Cat(0, catName, new Date(2020, 5, 10), "Persian", catColor.WHITE, null, new ArrayList<>())));
        CatDTO catDTO = catService.getCat(catName);
        assertEquals(catName, catDTO.name);
    }

    @Test
    public void testModifyCat() {
        CatDTO catDTO = new CatDTO(0, "Fluffy", new Date(2020, 5, 10), "Persian", catColor.WHITE, null, new ArrayList<>());
        catService.modifyCat(catDTO);
        verify(catRepository, times(1)).save(any(Cat.class));
    }

    @Test
    public void testAddFriend() {
        String receiver = "Fluffy";
        CatDTO catFriend = new CatDTO(0, "Tom", new Date(2020, 5, 10), "Siamese", catColor.GREY, null, new ArrayList<>());
        when(catRepository.findById(receiver)).thenReturn(Optional.of(new Cat(0, receiver, new Date(2020, 5, 10), "Persian", catColor.WHITE, null, new ArrayList<>())));
        catService.addFriend(receiver, catFriend);
        verify(catRepository, times(1)).save(any(Cat.class));
    }

    @Test
    public void testGetCatsByColor() {
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat(0, "Fluffy", new Date(2020, 5, 10), "Persian", catColor.WHITE, null, new ArrayList<>()));
        cats.add(new Cat(0, "Tom", new Date(2020, 5, 10), "Siamese", catColor.WHITE, null, new ArrayList<>()));
        when(catRepository.findByColor(catColor.WHITE)).thenReturn(cats);
        List<CatDTO> catDTOList = catService.getCatsByColor(catColor.WHITE);
        assertEquals(2, catDTOList.size());
    }
}
