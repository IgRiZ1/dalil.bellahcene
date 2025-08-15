package ehb.be.dalilbellahcen.repository;

import ehb.be.dalilbellahcen.model.Evenement;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {

    // Haal de 10 laatste evenementen op, gesorteerd op tijdstip (nieuwste eerst)
    @Query("SELECT e FROM Evenement e ORDER BY e.tijdstip DESC")
    List<Evenement> findTop10ByOrderByTijdstipDesc(Pageable pageable);

    // Standaard methode voor de laatste 10 evenementen
    List<Evenement> findTop10ByOrderByTijdstipDesc();

    // Alle evenementen van een specifieke organisatie
    List<Evenement> findByOrganisatieOrderByTijdstipDesc(String organisatie);

    // Evenementen in de toekomst
    @Query("SELECT e FROM Evenement e WHERE e.tijdstip > :now ORDER BY e.tijdstip ASC")
    List<Evenement> findUpcomingEvents(@Param("now") LocalDateTime now);

    // Evenementen vandaag
    @Query("SELECT e FROM Evenement e WHERE DATE(e.tijdstip) = DATE(:today) ORDER BY e.tijdstip ASC")
    List<Evenement> findEventsByDate(@Param("today") LocalDateTime today);

    // Zoek evenementen op titel (case-insensitive)
    @Query("SELECT e FROM Evenement e WHERE LOWER(e.titel) LIKE LOWER(CONCAT('%', :titel, '%')) ORDER BY e.tijdstip DESC")
    List<Evenement> findByTitelContainingIgnoreCase(@Param("titel") String titel);

    // Evenementen op een specifieke locatie
    @Query("SELECT e FROM Evenement e WHERE e.locatie.id = :locatieId ORDER BY e.tijdstip DESC")
    List<Evenement> findByLocatieId(@Param("locatieId") Long locatieId);

    // Tel aantal evenementen per organisatie
    @Query("SELECT e.organisatie, COUNT(e) FROM Evenement e GROUP BY e.organisatie")
    List<Object[]> countEventsByOrganisatie();
}