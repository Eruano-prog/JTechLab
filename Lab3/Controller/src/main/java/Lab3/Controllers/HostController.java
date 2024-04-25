package Lab3.Controllers;

import Lab3.Models.HostDTO;
import Lab3.Services.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hosts")
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
    public ResponseEntity<String> addHost(@RequestBody HostDTO host){
        hostService.addHost(host);

        return ResponseEntity.ok("Host added");
    }

    @PutMapping("/{name}")
    public ResponseEntity<String> modifyHost(@PathVariable String name, @RequestBody HostDTO host){
        host.setName(name);
        hostService.modifyHost(host);

        return ResponseEntity.ok("Host modified");
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteHost(@PathVariable String name){
        hostService.deleteHost(name);

        return ResponseEntity.ok("Host deleted");
    }
}
