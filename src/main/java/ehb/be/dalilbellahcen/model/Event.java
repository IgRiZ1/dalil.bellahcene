package ehb.be.dalilbellahcen.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "verplicht titel")
    private String titel;

    @NotBlank(message = "verplicht omschrijving")
    @Column(length = 2000)
    private String omschrijving;

    @NotBlank(message = "welke organisatie is verplicht")
    private String organisatie;

    @Email(message = "geldige email is verplicht")
    @NotBlank(message = "verplicht contact email")
    private String contactEmail;

    @NotNull(message = "verplicht tijdstip")
    private LocalDateTime tijdstip;

    @ManyToOne
    @NotNull(message = "verplicht locatie")
    private Locatie locatie;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitel() { return titel; }
    public void setTitel(String titel) { this.titel = titel; }

    public String getOmschrijving() { return omschrijving; }
    public void setOmschrijving(String omschrijving) { this.omschrijving = omschrijving; }

    public String getOrganisatie() { return organisatie; }
    public void setOrganisatie(String organisatie) { this.organisatie = organisatie; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public LocalDateTime getTijdstip() { return tijdstip; }
    public void setTijdstip(LocalDateTime tijdstip) { this.tijdstip = tijdstip; }

    public Locatie getLocatie() { return locatie; }
    public void setLocatie(Locatie locatie) { this.locatie = locatie; }
}