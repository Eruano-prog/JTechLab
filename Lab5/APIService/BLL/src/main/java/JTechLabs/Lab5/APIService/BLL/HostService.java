package JTechLabs.Lab5.APIService.BLL;

import JTechLabs.Lab5.APIService.DLL.HostProducer;
import JTechLabs.Lab5.APIService.DLL.IAuthRepository;
import JTechLabs.Lab5.APIService.Models.Host;
import JTechLabs.Lab5.APIService.Models.HostDTO;
import JTechLabs.Lab5.APIService.Models.HostDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    private final IAuthRepository authRepository;

    @Autowired
    public HostService(HostProducer hostProducer, PasswordEncoder encoder, IAuthRepository authRepository) {
        this.hostProducer = hostProducer;
        this.encoder = encoder;
        this.authRepository = authRepository;
    }

    public void addHost(HostDTO host) throws JsonProcessingException {
        Host convertedHost = host.toHost();
        convertedHost.setPassword(encoder.encode(convertedHost.getPassword()));
        hostProducer.saveHost(convertedHost);
    }

    public void deleteHost(String name) throws JsonProcessingException {
        hostProducer.deleteHost(name);
    }

    public void getHost(String name) throws JsonProcessingException {
        hostProducer.getHost(name);
    }

    public void modifyHost(HostDTO host) throws JsonProcessingException {
        Host convertedHost = host.toHost();
        hostProducer.saveHost(convertedHost);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Host> host = authRepository.findByName(username);
        return host.map(HostDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Host with name " + username + " not found"));
    }
}
