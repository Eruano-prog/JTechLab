package Lab3.Controllers;

import Lab3.Models.HostDTO;
import Lab3.Services.HostService;
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
    public ResponseEntity<HostDTO> addHost(@RequestBody HostDTO host){
        HostDTO savedHost = hostService.addHost(host);

        return ResponseEntity.ok(savedHost);
    }

    @PutMapping()
    public ResponseEntity<HostDTO> modifyHost(@RequestBody HostDTO host){
        HostDTO savedHost = hostService.modifyHost(host);

        return ResponseEntity.ok(savedHost);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteHost(@PathVariable String name){
        hostService.deleteHost(name);

        return ResponseEntity.ok(name);
    }
}
