public class CatService {
    private final CatRepository catRepository;

    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public void addCat(Cat cat){
        catRepository.insert(cat);
    }

    public void deleteCat(Cat cat){
        catRepository.delete(cat);
    }

    public Cat getCat(String name){
        return catRepository.getCatByName(name);
    }

    public void modifyCat(Cat cat){
        catRepository.update(cat);
    }
}
