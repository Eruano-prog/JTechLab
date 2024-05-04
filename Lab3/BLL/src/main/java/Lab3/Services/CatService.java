package Lab3.Services;

import Lab3.Models.Cat;
import Lab3.Models.CatDTO;
import Lab3.Models.Host;
import Lab3.Models.catColor;
import Lab3.Repositories.ICatRepository;
import Lab3.Repositories.IHostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CatService{
    private final ICatRepository catRepository;
    private final IHostRepository hostRepository;

    @Autowired
    public CatService(ICatRepository catRepository, IHostRepository hostRepository) {
        this.catRepository = catRepository;
        this.hostRepository = hostRepository;
    }

    public CatDTO addCat(String hostname, CatDTO cat) {
        Host host = hostRepository.findByName(hostname)
                .orElseThrow(() -> new EntityNotFoundException("Host " + hostname + " does not exist"));
        Cat catToAdd = cat.toCat();
        catToAdd.setHost(host);

        Cat addedCat = catRepository.save(catToAdd);

        return addedCat.toDTO();
    }

    public void deleteCat(String hostname, String name){
        Cat cat = catRepository.findByHost_NameAndNameIgnoreCase(hostname, name)
                .orElseThrow(() -> new EntityNotFoundException("Cat with name " + name + " not found"));

        catRepository.delete(cat);
    }

    public CatDTO getCat(String hostname, String name){
        Cat cat = catRepository.findByHost_NameAndNameIgnoreCase(hostname, name)
                .orElseThrow(() -> new EntityNotFoundException("Cat with name " + name + " not found"));
        return cat.toDTO();
    }

    public CatDTO modifyCat(String hostname, CatDTO cat){
        Host host = hostRepository.findByName(hostname)
                .orElseThrow(() -> new EntityNotFoundException("Host " + hostname + " does not exist"));

        Cat catToModify = cat.toCat();
        catToModify.setHost(host);

        Cat savedCat = catRepository.save(catToModify);

        return savedCat.toDTO();
    }

    public void addFriend(String receiver, CatDTO catFriend){

        Cat catToAdd = catFriend.toCat();

        Cat cat = catRepository.findByNameIgnoreCase(receiver)
                .orElseThrow(() -> new EntityNotFoundException("Cat with name " + catToAdd.name + " not found"));

        cat.friends.add(catToAdd);

        catRepository.save(cat);
    }

    public List<CatDTO> getCatsByColor(String hostname, catColor color){

        return catRepository.findByHost_NameAndColor(hostname, color)
                .stream()
                .map(Cat::toDTO)
                .collect(Collectors.toList());
    }
}
