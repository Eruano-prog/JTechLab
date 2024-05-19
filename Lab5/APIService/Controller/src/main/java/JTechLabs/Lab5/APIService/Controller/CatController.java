    package JTechLabs.Lab5.APIService.Controller;

    import JTechLabs.Lab5.APIService.BLL.CatService;
    import JTechLabs.Lab5.APIService.Models.CatDTO;
    import JTechLabs.Lab5.APIService.Models.catColor;
    import com.fasterxml.jackson.core.JsonProcessingException;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
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
        public ResponseEntity<String> getCat(Authentication authentication, @RequestParam String name) {
            String username = authentication.getName();

            CatDTO cat = null;
            try {
                catService.getCat(username, name);
            } catch (JsonProcessingException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parssing error");
            }
            return ResponseEntity.ok("Message accepted");
        }

        @GetMapping(params = "color")
        public ResponseEntity<String> getCatsByColor(Authentication authentication, @RequestParam catColor color) {
            String username = authentication.getName();
            try {
                catService.getCatsByColor(username, color);
            } catch (JsonProcessingException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parssing error");
            }

            return ResponseEntity.ok("Message accepted");
        }

        @PostMapping
        public ResponseEntity<CatDTO> addCat(Authentication authentication, @RequestBody CatDTO cat) throws JsonProcessingException {
            String username = authentication.getName();

            catService.addCat(username, cat);

            return ResponseEntity.status(HttpStatus.CREATED).body(cat);
        }

        @PutMapping
        public ResponseEntity<CatDTO> modifyCat(Authentication authentication, @RequestBody CatDTO cat) throws JsonProcessingException {
            String username = authentication.getName();
            catService.modifyCat(username, cat);

            return ResponseEntity.ok(cat);
        }

        @DeleteMapping
        public ResponseEntity<String> deleteCat(Authentication authentication, @RequestParam String name) throws JsonProcessingException {
            String username = authentication.getName();
            catService.deleteCat(username, name);

            return ResponseEntity.ok(name);
        }
    }