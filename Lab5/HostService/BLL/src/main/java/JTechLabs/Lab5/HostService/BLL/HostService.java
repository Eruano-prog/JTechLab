package JTechLabs.Lab5.HostService.BLL;

import JTechLabs.Lab5.HostService.DLL.IHostRepository;
import JTechLabs.Lab5.HostService.Models.Host;
import JTechLabs.Lab5.HostService.Models.HostDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HostService {
    private final IHostRepository hostRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public HostService(IHostRepository hostRepository, PasswordEncoder encoder) {
        this.hostRepository = hostRepository;
        this.encoder = encoder;
    }

    public HostDTO addHost(HostDTO host){
        Host convertedHost = host.toHost();
        convertedHost.setPassword(encoder.encode(convertedHost.getPassword()));
        Host savedHost = hostRepository.save(convertedHost);

        return savedHost.toDTO();
    }

    public void deleteHost(String name){
        Host host = hostRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Host with name " + name + " not found"));

        hostRepository.delete(host);
    }

    public HostDTO getHost(String name){
        Host host = hostRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Host with name " + name + " not found"));

        return host.toDTO();
    }

    public HostDTO modifyHost(HostDTO host){
        Host convertedHost = host.toHost();

        Host savedHost = hostRepository.save(convertedHost);

        return savedHost.toDTO();
    }
}
