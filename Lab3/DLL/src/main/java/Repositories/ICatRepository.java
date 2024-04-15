package Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Models.Cat;

@Repository
public interface ICatRepository extends JpaRepository<Cat, String> {}
