package Lab3.Services;

import Lab3.Models.Host;
import Lab3.Models.HostDTO;
import Lab3.Models.HostDetails;
import Lab3.Repositories.IHostRepository;
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
    private final IHostRepository hostRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public HostService(IHostRepository hostRepository, PasswordEncoder encoder) {
        this.hostRepository = hostRepository;
        this.encoder = encoder;
    }

    public void addHost(HostDTO host){
        Host convertedHost = host.toHost();
        convertedHost.setPassword(encoder.encode(convertedHost.getPassword()));
        hostRepository.save(convertedHost);
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

    public void modifyHost(HostDTO host){
        Host convertedHost = host.toHost();
        hostRepository.save(convertedHost);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Host> host = hostRepository.findByName(username);
        return host.map(HostDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Host with name " + username + " not found"));
    }
}
