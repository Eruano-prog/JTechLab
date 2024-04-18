package Lab3.Repositories;

import Lab3.Models.Cat;
import Lab3.Models.catColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatRepository extends JpaRepository<Cat, String> {
    List<Cat> findByColor(catColor color);
}
