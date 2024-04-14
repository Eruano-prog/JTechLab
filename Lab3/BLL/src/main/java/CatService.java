import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CatService {
    private final ICatRepository catRepository;

    @Autowired
    public CatService(ICatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public void addCat(CatDTO cat) {
        Cat catToAdd = new Cat(cat.name, cat.birthDate, cat.type, cat.color, cat.host, cat.friends);
        catRepository.save(catToAdd);
    }

    public void deleteCat(CatDTO cat){
        Cat catToDelete = new Cat(cat.name, cat.birthDate, cat.type, cat.color, cat.host, cat.friends);

        catRepository.delete(catToDelete);
    }

    public CatDTO getCat(String name){
        Optional<Cat> optionalCatcat = catRepository.findById(name);

        if (optionalCatcat.isEmpty()){
            return null;
        }
        Cat cat = optionalCatcat.get();
        return new CatDTO(cat.name, cat.birthDate, cat.type, cat.color, cat.host, cat.friends);
    }

    public void modifyCat(CatDTO cat){
        Cat catToModify = new Cat(cat.name, cat.birthDate, cat.type, cat.color, cat.host, cat.friends);
        catRepository.save(catToModify);
    }

    public void addFriend(String reciever, CatDTO catFriend){

        Cat catToAdd = new Cat(catFriend.name, catFriend.birthDate, catFriend.type, catFriend.color, catFriend.host, catFriend.friends);

        Optional<Cat> cat = catRepository.findById(reciever);
        if (cat.isEmpty()){
            return;
        }

        cat.get().friends.add(catToAdd);

        catRepository.save(cat.get());
    }
}
