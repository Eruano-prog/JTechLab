package JTechLabs.Lab5.APIService.BLL;

import JTechLabs.Lab5.APIService.DLL.CatProducer;
import JTechLabs.Lab5.APIService.DLL.HostProducer;
import JTechLabs.Lab5.APIService.Models.Cat;
import JTechLabs.Lab5.APIService.Models.CatDTO;
import JTechLabs.Lab5.APIService.Models.catColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.stream.Collectors;


@Service
public class CatService{
    private final CatProducer catProducer;
    private final HostProducer hostProducer;

    @Autowired
    public CatService(CatProducer catProducer, HostProducer hostProducer) {
        this.catProducer = catProducer;
        this.hostProducer = hostProducer;
    }

    public void addCat(String hostname, CatDTO cat) throws JsonProcessingException {
//        Host host = hostProducer.findByName(hostname)
//                .orElseThrow(() -> new EntityNotFoundException("Host " + hostname + " does not exist"));

        Cat catToAdd = cat.toCat();
//        catToAdd.setHost(host);

        catProducer.putCat(catToAdd);
    }

    public void deleteCat(String hostname, String name) throws JsonProcessingException {
//        Cat cat = catProducer.findByHost_NameAndNameIgnoreCase(hostname, name)
//                .orElseThrow(() -> new EntityNotFoundException("Cat with name " + name + " not found"));

        catProducer.deleteCat(name);
    }

    public void getCat(String hostname, String name) throws JsonProcessingException {
//        Cat cat = catProducer.findByHost_NameAndNameIgnoreCase(hostname, name)
//                .orElseThrow(() -> new EntityNotFoundException("Cat with name " + name + " not found"));
        catProducer.getCat(name);
    }

    public void modifyCat(String hostname, CatDTO cat) throws JsonProcessingException {
//        Host host = hostProducer.findByName(hostname)
//                .orElseThrow(() -> new EntityNotFoundException("Host " + hostname + " does not exist"));

        Cat catToModify = cat.toCat();
//        catToModify.setHost(host);

        catProducer.saveCat(catToModify);
    }

    public void getCatsByColor(String hostname, catColor color) throws JsonProcessingException {

        catProducer.getCatByColor(hostname, color);
    }
}
