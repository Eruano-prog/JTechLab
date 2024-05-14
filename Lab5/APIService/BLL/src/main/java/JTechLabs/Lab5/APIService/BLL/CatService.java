package JTechLabs.Lab5.APIService.BLL;

import JTechLabs.Lab5.APIService.DLL.CatProducer;
import Lab5.Models.Cat;
import Lab5.Models.CatDTO;
import Lab5.Models.Host;
import Lab5.Models.catColor;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CatService{
    private final CatProducer catProducer;
    private final IHostRepository hostRepository;

    @Autowired
    public CatService(CatProducer catProducer, IHostRepository hostRepository) {
        this.catProducer = catProducer;
        this.hostRepository = hostRepository;
    }

    public void addCat(String hostname, CatDTO cat) throws JsonProcessingException {
        Host host = hostRepository.findByName(hostname)
                .orElseThrow(() -> new EntityNotFoundException("Host " + hostname + " does not exist"));

        Cat catToAdd = cat.toCat();
        catToAdd.setHost(host);

        catProducer.putCat(catToAdd);
    }

    public void deleteCat(String hostname, String name) throws JsonProcessingException {
        Cat cat = catProducer.findByHost_NameAndNameIgnoreCase(hostname, name)
                .orElseThrow(() -> new EntityNotFoundException("Cat with name " + name + " not found"));

        catProducer.deleteCat(cat);
    }

    public CatDTO getCat(String hostname, String name){
        Cat cat = catProducer.findByHost_NameAndNameIgnoreCase(hostname, name)
                .orElseThrow(() -> new EntityNotFoundException("Cat with name " + name + " not found"));
        return cat.toDTO();
    }

    public void modifyCat(String hostname, CatDTO cat) throws JsonProcessingException {
        Host host = hostRepository.findByName(hostname)
                .orElseThrow(() -> new EntityNotFoundException("Host " + hostname + " does not exist"));

        Cat catToModify = cat.toCat();
        catToModify.setHost(host);

        catProducer.saveCat(catToModify);
    }

    public void addFriend(String receiver, CatDTO catFriend) throws JsonProcessingException {

        Cat catToAdd = catFriend.toCat();

        Cat cat = catProducer.findByNameIgnoreCase(receiver)
                .orElseThrow(() -> new EntityNotFoundException("Cat with name " + catToAdd.name + " not found"));

        cat.friends.add(catToAdd);

        catProducer.saveCat(cat);
    }

    public List<CatDTO> getCatsByColor(String hostname, catColor color){

        return catProducer.findByHost_NameAndColor(hostname, color)
                .stream()
                .map(Cat::toDTO)
                .collect(Collectors.toList());
    }
}
