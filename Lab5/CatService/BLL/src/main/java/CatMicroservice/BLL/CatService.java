package CatMicroservice.BLL;

import Lab5.Models.Cat;
import Lab5.Models.CatDTO;
import Lab5.Models.Host;
import Lab5.Models.catColor;
import CatMicroservice.Repository.ICatRepository;
import Lab5.Repositories.IHostRepository;
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

    public void addCat(String hostname, CatDTO cat) {
        Host host = hostRepository.findByName(hostname)
                .orElseThrow(() -> new EntityNotFoundException("Host " + hostname + " does not exist"));
        Cat catToAdd = cat.toCat();
        catToAdd.setHost(host);
        catRepository.save(catToAdd);
    }

    public void deleteCat(String hostname, String name){
        Cat cat = catRepository.findByHost_NameAndNameIgnoreCase(hostname, name)
                .orElseThrow(() -> new EntityNotFoundException("Cat with name " + name + " not found"));

        catRepository.delete(cat);
    }

    public Lab5.Models.CatDTO getCat(String hostname, String name){
        Cat cat = catRepository.findByHost_NameAndNameIgnoreCase(hostname, name)
                .orElseThrow(() -> new EntityNotFoundException("Cat with name " + name + " not found"));
        return cat.toDTO();
    }

    public void modifyCat(String hostname, CatDTO cat){
        Host host = hostRepository.findByName(hostname)
                .orElseThrow(() -> new EntityNotFoundException("Host " + hostname + " does not exist"));

        Cat catToModify = cat.toCat();
        catToModify.setHost(host);

        catRepository.save(catToModify);
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
