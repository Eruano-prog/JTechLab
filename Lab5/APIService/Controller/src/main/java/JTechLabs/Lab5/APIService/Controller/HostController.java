package JTechLabs.Lab5.APIService.Controller;

import JTechLabs.Lab5.APIService.BLL.HostService;
import Lab5.Models.HostDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<HostDTO> getHost(@PathVariable String name){
        HostDTO host = hostService.getHost(name);

        return ResponseEntity.ok(host);
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
