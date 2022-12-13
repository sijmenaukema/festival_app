package nl.capgemini.festival.repository;

import nl.capgemini.festival.model.DiscJockey;
import nl.capgemini.festival.model.MusicSet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MusicSetRepository extends CrudRepository<MusicSet, Long> {
    @Query("SELECT ms FROM MusicSet ms WHERE ms.discJockey.id = ?1")
    Optional<MusicSet[]> findByDiscJockeyId(long id);
}
