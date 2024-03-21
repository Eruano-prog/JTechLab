public class HostService {
    private final HostRepository hostRepository;

    public HostService(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    public void addHost(Host host){
        hostRepository.insert(host);
    }

    public void deleteHost(Host host){
        hostRepository.delete(host);
    }

    public Host getHost(String name){
        return hostRepository.getHostByName(name);
    }

    public void modifyCat(Host host){
        hostRepository.update(host);
    }
}
