package Controllers;

import Models.HostDTO;
import Services.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@RequestMapping("/hosts")
public class HostController {
    public HostService hostService;

    @Autowired
    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping("/{name}")
    public void getHost(@PathVariable String name){
        HostDTO host = hostService.getHost(name);

        System.out.println("host Name:" + host.name);
    }

    @PostMapping
    public void addHost(@RequestBody HostDTO host){
        hostService.addHost(host);
    }

    @PutMapping("/{name}")
    public void modifyHost(@PathVariable String name, @RequestBody HostDTO host){
        host.setName(name); // обновляем имя хоста
        hostService.modifyHost(host);
    }

    @DeleteMapping("/{name}")
    public void deleteHost(@PathVariable HostDTO host){
        hostService.deleteHost(host);
    }
}
