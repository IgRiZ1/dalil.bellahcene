package ehb.be.dalilbellahcen.repository;

import com.anderlecht.ngowebapp.model.Locatie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocatieRepository extends JpaRepository<Locatie, Long> {

    // Zoek locatie op naam (case-insensitive)
    Optional<Locatie> findByNaamIgnoreCase(String naam);

    // Zoek locaties met minimale capaciteit
    @Query("SELECT l FROM Locatie l WHERE l.capaciteit >= :minCapaciteit ORDER BY l.capaciteit ASC")
    List<Locatie> findByCapaciteitGreaterThanEqual(@Param("minCapaciteit") Integer minCapaciteit);

    // Zoek locaties op deel van adres
    @Query("SELECT l FROM Locatie l WHERE LOWER(l.adres) LIKE LOWER(CONCAT('%', :adres, '%')) ORDER BY l.naam ASC")
    List<Locatie> findByAdresContainingIgnoreCase(@Param("adres") String adres);

    // Alle locaties gesorteerd op naam
    List<Locatie> findAllByOrderByNaamAsc();

    // Locaties met grootste capaciteit eerst
    List<Locatie> findAllByOrderByCapaciteitDesc();

    // Controleer of locatie naam al bestaat (voor unieke namen)
    boolean existsByNaamIgnoreCase(String naam);
}