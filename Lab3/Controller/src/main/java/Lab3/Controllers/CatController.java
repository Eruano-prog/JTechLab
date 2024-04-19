    package Lab3.Controllers;
    import Lab3.Models.catColor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;

    import Lab3.Models.CatDTO;
    import Lab3.Services.CatService;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/cat")
    public class CatController {
        public CatService catService;

        @Autowired
        public CatController(CatService catService) {
            this.catService = catService;
        }

        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = "name")
        public ResponseEntity<CatDTO> getCat(@RequestParam String name) {
            CatDTO cat = catService.getCat(name);
            return ResponseEntity.ok(cat);
        }

        @GetMapping(params = "color")
        public ResponseEntity<List<CatDTO>> getCatsByColor(@RequestParam catColor color) {
            return ResponseEntity.ok(catService.getCatsByColor(color));
        }

        @PostMapping
        public ResponseEntity<String> addCat(@RequestBody CatDTO cat) {
            catService.addCat(cat);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cat added successfully");
        }

        @PutMapping
        public ResponseEntity<String> modifyCat(@RequestBody CatDTO cat) {
            catService.modifyCat(cat);
            return ResponseEntity.ok("Cat modified successfully");
        }

        @DeleteMapping
        public ResponseEntity<String> deleteCat(@RequestParam String name) {
            catService.deleteCat(name);
            return ResponseEntity.ok("Cat deleted successfully");
        }
    }
