package ehb.be.dalilbellahcen.model.dao;

import ehb.be.dalilbellahcen.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface eventDao extends CrudRepository<Event, Long> {
    List<Event> findTop20ByOrderByTijdstipDesc();
}
