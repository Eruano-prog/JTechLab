package Lab3.Services;

import Lab3.Models.Host;
import Lab3.Models.HostDTO;
import Lab3.Repositories.IHostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HostService {
    private final IHostRepository hostRepository;

    @Autowired
    public HostService(IHostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    public void addHost(HostDTO host){
        Host convertedHost = new Host(host.name, host.birthDate, host.cats);
        hostRepository.save(convertedHost);
    }

    public void deleteHost(HostDTO host){
        Host convertedHost = new Host(host.name, host.birthDate, host.cats);
        hostRepository.delete(convertedHost);
    }

    public HostDTO getHost(String name){
        Optional<Host> hostOptional = hostRepository.findById(name);

        if (hostOptional.isEmpty()) return null;

        Host host = hostOptional.get();

        HostDTO convertedHost = new HostDTO(host.name, host.birthDate, host.cats);

        return convertedHost;
    }

    public void modifyHost(HostDTO host){
        Host convertedHost = new Host(host.name, host.birthDate, host.cats);
        hostRepository.save(convertedHost);
    }
}
