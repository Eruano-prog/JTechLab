package Lab3.Services;

import Lab3.Models.Cat;
import Lab3.Models.CatDTO;
import Lab3.Models.catColor;
import Lab3.Repositories.ICatRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CatService {
    private final ICatRepository catRepository;

    @Autowired
    public CatService(ICatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public void addCat(CatDTO cat) {
        Cat catToAdd = cat.toCat();
        catRepository.save(catToAdd);
    }

    public void deleteCat(String name){
        Cat cat = catRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException("Cat with name " + name + " not found"));

        catRepository.delete(cat);
    }

    public CatDTO getCat(String name){
        Cat cat = catRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException("Cat with name " + name + " not found"));
        return cat.toDTO();
    }

    public void modifyCat(CatDTO cat){
        Cat catToModify = cat.toCat();
        catRepository.save(catToModify);
    }

    public void addFriend(String receiver, CatDTO catFriend){

        Cat catToAdd = catFriend.toCat();

        Cat cat = catRepository.findByNameIgnoreCase(receiver)
                .orElseThrow(() -> new EntityNotFoundException("Cat with name " + catToAdd.name + " not found"));

        cat.friends.add(catToAdd);

        catRepository.save(cat);
    }

    public List<CatDTO> getCatsByColor(catColor color){
        List<CatDTO> catDTOList = catRepository.findByColor(color)
                .stream()
                .map(Cat::toDTO)
                .collect(Collectors.toList());


        return catDTOList;
    }
}
