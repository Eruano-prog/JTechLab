package Lab3.Repositories;

import Lab3.Models.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatRepository extends JpaRepository<Cat, String> {}
