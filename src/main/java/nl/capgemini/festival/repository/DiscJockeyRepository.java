package nl.capgemini.festival.repository;

import nl.capgemini.festival.model.DiscJockey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DiscJockeyRepository extends CrudRepository<DiscJockey, Long> {

    @Query("SELECT dj FROM DiscJockey dj WHERE LENGTH(dj.name) > ?1")
    Iterable<DiscJockey> findAllByNameLength(int length);
}
