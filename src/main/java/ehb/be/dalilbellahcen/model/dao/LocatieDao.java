package ehb.be.dalilbellahcen.model.dao;

import ehb.be.dalilbellahcen.model.Locatie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LocatieDao extends CrudRepository<Locatie, Long> {
}
