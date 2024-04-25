package Lab3.Repositories;

import Lab3.Models.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IHostRepository extends JpaRepository<Host, String> {
    Optional<Host> findByName(String name);
}
