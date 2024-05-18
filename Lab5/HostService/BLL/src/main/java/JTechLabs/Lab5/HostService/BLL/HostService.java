package JTechLabs.Lab5.HostService.BLL;

import JTechLabs.Lab5.HostService.DLL.IHostRepository;
import JTechLabs.Lab5.HostService.DLL.IRequestRepository;
import JTechLabs.Lab5.HostService.Models.Host;
import JTechLabs.Lab5.HostService.Models.HostDTO;
import JTechLabs.Lab5.HostService.Models.HostGetRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HostService {
    private final IHostRepository hostRepository;
    private final IRequestRepository requestRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public HostService(IHostRepository hostRepository, PasswordEncoder encoder, IRequestRepository requestRepository) {
        this.hostRepository = hostRepository;
        this.encoder = encoder;
        this.requestRepository = requestRepository;
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

    public void getHost(Integer requestID, String name){
        Host host = hostRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Host with name " + name + " not found"));

        List<Host> results = new ArrayList<>();
        results.add(host);

        HostGetRequest request = new HostGetRequest(null, requestID, results);
        requestRepository.save(request);
    }

    public HostDTO modifyHost(HostDTO host){
        Host convertedHost = host.toHost();

        Host savedHost = hostRepository.save(convertedHost);

        return savedHost.toDTO();
    }
}
