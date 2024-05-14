package JTechLabs.Lab5.HostService.BLL;

import JTechLabs.Lab5.HostService.DLL.IHostRepository;
import Lab5.Models.Host;
import Lab5.Models.HostDTO;
import Lab5.Models.HostDetails;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Host> host = hostRepository.findByName(username);
        return host.map(HostDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Host with name " + username + " not found"));
    }
}
