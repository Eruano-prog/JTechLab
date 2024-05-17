package JTechLabs.Lab5.APIService.BLL;

import JTechLabs.Lab5.APIService.DLL.CatProducer;
import JTechLabs.Lab5.APIService.Models.Cat;
import JTechLabs.Lab5.APIService.Models.CatDTO;
import JTechLabs.Lab5.APIService.Models.catColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;


@Service
public class CatService{
    private final CatProducer catProducer;

    @Autowired
    public CatService(CatProducer catProducer) {
        this.catProducer = catProducer;
    }

    public void addCat(String hostname, CatDTO cat) throws JsonProcessingException {

        catProducer.putCat(hostname, cat);
    }

    public void deleteCat(String hostname, String name) throws JsonProcessingException {

        catProducer.deleteCat(hostname, name);
    }

    public void getCat(String hostname, String name) throws JsonProcessingException {
        catProducer.getCat(hostname, name);
    }

    public void modifyCat(String hostname, CatDTO cat) throws JsonProcessingException {

        catProducer.saveCat(hostname, cat);
    }

    public void getCatsByColor(String hostname, catColor color) throws JsonProcessingException {

        catProducer.getCatByColor(hostname, color);
    }
}
