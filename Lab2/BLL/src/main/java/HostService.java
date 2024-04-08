public class HostService {
    private final HostRepository hostRepository;

    public HostService(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    public void addHost(HostDTO host){
        Host convertedHost = new Host(host.name, host.birthDate, host.cats);
        hostRepository.insert(convertedHost);
    }

    public void deleteHost(HostDTO host){
        Host convertedHost = new Host(host.name, host.birthDate, host.cats);
        hostRepository.delete(convertedHost);
    }

    public HostDTO getHost(String name){
        Host host = hostRepository.getHostByName(name);
        HostDTO convertedHost = new HostDTO(host.name, host.birthDate, host.cats);

        return convertedHost;
    }

    public void modifyHost(HostDTO host){
        Host convertedHost = new Host(host.name, host.birthDate, host.cats);
        hostRepository.update(convertedHost);
    }
}
