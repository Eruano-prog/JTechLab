package JTechLabs.Lab5.APIService.BLL;

import JTechLabs.Lab5.APIService.DLL.HostProducer;
import Lab5.Models.Host;
import Lab5.Models.HostDTO;
import Lab5.Models.HostDetails;
import Lab3.Repositories.IHostRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HostService implements UserDetailsService {
    private final HostProducer hostProducer;
    private final PasswordEncoder encoder;

    @Autowired
    public HostService(HostProducer hostProducer, PasswordEncoder encoder) {
        this.hostProducer = hostProducer;
        this.encoder = encoder;
    }

    public void addHost(HostDTO host) throws JsonProcessingException {
        Host convertedHost = host.toHost();
        convertedHost.setPassword(encoder.encode(convertedHost.getPassword()));
        hostProducer.saveHost(convertedHost);
    }

    public void deleteHost(String name) throws JsonProcessingException {
        Host host = hostProducer.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Host with name " + name + " not found"));

        hostProducer.deleteHost(host);
    }

    public HostDTO getHost(String name){
        Host host = hostProducer.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Host with name " + name + " not found"));

        return host.toDTO();
    }

    public void modifyHost(HostDTO host) throws JsonProcessingException {
        Host convertedHost = host.toHost();
        hostProducer.saveHost(convertedHost);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Host> host = hostProducer.findByName(username);
        return host.map(HostDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Host with name " + username + " not found"));
    }
}
