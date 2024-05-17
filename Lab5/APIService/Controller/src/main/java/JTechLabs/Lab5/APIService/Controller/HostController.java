package JTechLabs.Lab5.APIService.Controller;

import JTechLabs.Lab5.APIService.BLL.HostService;
import JTechLabs.Lab5.APIService.Models.HostDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/host")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class HostController {
    public HostService hostService;

    @Autowired
    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getHost(@PathVariable String name){
        try {
            hostService.getHost(name);
        } catch (JsonProcessingException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parssing error");
        }

        return ResponseEntity.ok("request accepted");
    }

    @PostMapping
    public ResponseEntity<HostDTO> addHost(@RequestBody HostDTO host) throws JsonProcessingException {
        hostService.addHost(host);

        return ResponseEntity.ok(host);
    }

    @PutMapping()
    public ResponseEntity<HostDTO> modifyHost(@RequestBody HostDTO host) throws JsonProcessingException {
        hostService.modifyHost(host);

        return ResponseEntity.ok(host);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteHost(@PathVariable String name) throws JsonProcessingException {
        hostService.deleteHost(name);

        return ResponseEntity.ok(name);
    }
}
