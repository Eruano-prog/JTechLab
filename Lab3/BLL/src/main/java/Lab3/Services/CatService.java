package Lab3.Services;

import Lab3.Models.Cat;
import Lab3.Models.CatDTO;
import Lab3.Models.catColor;
import Lab3.Repositories.ICatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CatService {
    private final ICatRepository catRepository;

    @Autowired
    public CatService(ICatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public void addCat(CatDTO cat) {
        Cat catToAdd = new Cat(cat.id, cat.name, cat.birthDate, cat.type, cat.color, cat.host, cat.friends);
        catRepository.save(catToAdd);
    }

    public void deleteCat(String name){
        Optional<Cat> cat = catRepository.findByNameIgnoreCase(name);

        if (cat.isEmpty()) return;

        Cat catToDelete = cat.get();
        catRepository.delete(catToDelete);
    }

    public CatDTO getCat(String name){
        Optional<Cat> optionalCat = catRepository.findByNameIgnoreCase(name);

        if (optionalCat.isEmpty()){
            return null;
        }
        Cat cat = optionalCat.get();
        return new CatDTO(cat.id, cat.name, cat.birthDate, cat.type, cat.color, cat.host, cat.friends);
    }

    public void modifyCat(CatDTO cat){
        Cat catToModify = new Cat(cat.id, cat.name, cat.birthDate, cat.type, cat.color, cat.host, cat.friends);
        catRepository.save(catToModify);
    }

    public void addFriend(String receiver, CatDTO catFriend){

        Cat catToAdd = new Cat(catFriend.id, catFriend.name, catFriend.birthDate, catFriend.type, catFriend.color, catFriend.host, catFriend.friends);

        Optional<Cat> cat = catRepository.findByNameIgnoreCase(receiver);
        if (cat.isEmpty()){
            return;
        }

        cat.get().friends.add(catToAdd);

        catRepository.save(cat.get());
    }

    public List<CatDTO> getCatsByColor(catColor color){
        List<Cat> cats = catRepository.findByColor(color);
        List<CatDTO> catDTOList = new ArrayList<>();

        for (Cat cat : cats){
            CatDTO dto = new CatDTO(cat.id, cat.name, cat.birthDate, cat.type, cat.color, cat.host, cat.friends);
            catDTOList.add(dto);
        }

        return catDTOList;
    }
}
