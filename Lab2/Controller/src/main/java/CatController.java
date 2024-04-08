public class CatController {
    public CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    public void getCat(String name){
        CatDTO cat = catService.getCat(name);

        System.out.println("Cat Name:" + cat.name + " Type:" + cat.type + " Color:" + cat.color);
    }

    public void addCat(CatDTO cat){
        catService.addCat(cat);
    }

    public void modifyCat(CatDTO cat){
        catService.modifyCat(cat);
    }

    public void deleteCat(CatDTO cat){
        catService.modifyCat(cat);
    }
}
