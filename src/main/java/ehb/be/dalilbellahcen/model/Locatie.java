package ehb.be.dalilbellahcen.model;
// Locatie.java

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(n = "locaties")
public class Locatie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(n = "id")
    private Long id;

    @NotBlank(message = "Naam is verplicht")
    @Column(n = "naam", nullable = false, length = 255)
    private String naam;

    @NotBlank(message = "Adres is verplicht")
    @Column(n = "adres", nullable = false, length = 500)
    private String adres;

    @NotNull(message = "Capaciteit is verplicht")
    @Positive(message = "Capaciteit moet positief zijn")
    @Column(n = "capaciteit", nullable = false)
    private Integer capaciteit;

    // Constructors
    public Locatie() {}

    public Locatie(String naam, String adres, Integer capaciteit) {
        this.naam = naam;
        this.adres = adres;
        this.capaciteit = capaciteit;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Integer getCapaciteit() {
        return capaciteit;
    }

    public void setCapaciteit(Integer capaciteit) {
        this.capaciteit = capaciteit;
    }

    @Override
    public String toString() {
        return "Locatie{" +
                "id=" + id +
                ", naam='" + naam + '\'' +
                ", adres='" + adres + '\'' +
                ", capaciteit=" + capaciteit +
                '}';
    }
}