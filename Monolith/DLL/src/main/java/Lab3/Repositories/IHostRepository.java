package Lab3.Repositories;

import Lab3.Models.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IHostRepository extends JpaRepository<Host, Integer> {
    Optional<Host> findByName(String name);
}
