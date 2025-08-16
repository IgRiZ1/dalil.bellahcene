package ehb.be.dalilbellahcen.model.dao;

import ehb.be.dalilbellahcen.model.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface eventDao extends CrudRepository<Event, Long> {
    List<Event> findTop20ByOrderByTijdstipDesc();
}
