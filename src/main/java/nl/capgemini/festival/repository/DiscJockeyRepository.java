package nl.capgemini.festival.repository;

import nl.capgemini.festival.model.DiscJockey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DiscJockeyRepository extends CrudRepository<DiscJockey, Long> {

    @Query("SELECT dj FROM DiscJockey dj WHERE LENGTH(dj.name) > ?1")
    Iterable<DiscJockey> findAllByNameLength(int length);

    @Query("SELECT dj FROM DiscJockey dj WHERE (dj.name) = ?1")
    Optional<DiscJockey> findByName(String name);
}
