import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/cat")
public class CatController {
    public CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void getCat(String name){
        CatDTO cat = catService.getCat(name);

        System.out.println("Cat Name:" + cat.name + " Type:" + cat.type + " Color:" + cat.color);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addCat(CatDTO cat){
        catService.addCat(cat);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void modifyCat(CatDTO cat){
        catService.modifyCat(cat);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteCat(CatDTO cat){
        catService.modifyCat(cat);
    }
}
