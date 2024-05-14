package CatMicroservice.Repository;

import Lab5.Models.Cat;
import Lab5.Models.catColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICatRepository extends JpaRepository<Cat, Integer> {
    Optional<Cat> findByHost_NameAndNameIgnoreCase(String hostName, String catName);

    List<Cat> findByColor(catColor color);

    List<Cat> findByHost_NameAndColor(String name, catColor color);

    Optional<Cat> findByNameIgnoreCase(String name);

}
