public class CatService {
    private final CatRepository catRepository;

    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public void addCat(CatDTO cat){
        Cat catToAdd = new Cat(cat.name, cat.birthDate, cat.type, cat.color, cat.host, cat.friends);
        catRepository.insert(catToAdd);
    }

    public void deleteCat(CatDTO cat){
        Cat catToDelete = new Cat(cat.name, cat.birthDate, cat.type, cat.color, cat.host, cat.friends);

        catRepository.delete(catToDelete);
    }

    public CatDTO getCat(String name){
        Cat cat = catRepository.getCatByName(name);

        return new CatDTO(cat.name, cat.birthDate, cat.type, cat.color, cat.host, cat.friends);
    }

    public void modifyCat(CatDTO cat){
        Cat catToModify = new Cat(cat.name, cat.birthDate, cat.type, cat.color, cat.host, cat.friends);
        catRepository.update(catToModify);
    }

    public void addFriend(String reciever, CatDTO catFriend){

        Cat catToAdd = new Cat(catFriend.name, catFriend.birthDate, catFriend.type, catFriend.color, catFriend.host, catFriend.friends);

        Cat cat = catRepository.getCatByName(reciever);
        cat.friends.add(catToAdd);

        catRepository.update(cat);
    }
}
