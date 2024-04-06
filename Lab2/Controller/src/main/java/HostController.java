public class HostController {
    public HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    public void getHost(String name){
        HostDTO host = hostService.getHost(name);

        System.out.println("host Name:" + host.name);
    }

    public void addHost(HostDTO host){
        hostService.addHost(host);
    }

    public void modifyHost(HostDTO host){
        hostService.modifyHost(host);
    }

    public void deleteHost(HostDTO host){
        hostService.deleteHost(host);
    }
}
