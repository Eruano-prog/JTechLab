    package Lab3.Controllers;
    import Lab3.Models.catColor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;

    import Lab3.Models.CatDTO;
    import Lab3.Services.CatService;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.security.core.Authentication;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/cat")
    @PreAuthorize("hasAuthority('ROLE_HOST')")
    public class CatController {
        public CatService catService;

        @Autowired
        public CatController(CatService catService) {
            this.catService = catService;
        }

        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = "name")
        public ResponseEntity<CatDTO> getCat(Authentication authentication, @RequestParam String name) {
            String username = authentication.getName();

            CatDTO cat = catService.getCat(username, name);
            return ResponseEntity.ok(cat);
        }

        @GetMapping(params = "color")
        public ResponseEntity<List<CatDTO>> getCatsByColor(Authentication authentication, @RequestParam catColor color) {
            String username = authentication.getName();
            return ResponseEntity.ok(catService.getCatsByColor(username, color));
        }

        @PostMapping
        public ResponseEntity<CatDTO> addCat(Authentication authentication, @RequestBody CatDTO cat) {
            String username = authentication.getName();

            CatDTO savedCat = catService.addCat(username, cat);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedCat);
        }

        @PutMapping
        public ResponseEntity<CatDTO> modifyCat(Authentication authentication, @RequestBody CatDTO cat) {
            String username = authentication.getName();

            CatDTO savedCat = catService.modifyCat(username, cat);
            return ResponseEntity.ok(savedCat);
        }

        @DeleteMapping
        public ResponseEntity<String> deleteCat(Authentication authentication, @RequestParam String name) {
            String username = authentication.getName();
            catService.deleteCat(username, name);

            return ResponseEntity.ok(name);
        }
    }
