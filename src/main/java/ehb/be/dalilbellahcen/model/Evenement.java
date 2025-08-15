package ehb.be.dalilbellahcen.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

    @Entity
    @Table(n = "evenementen")
    public class Evenement {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(n = "id")
        private Long id;

        @NotNull(message = "Tijdstip is verplicht")
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        @Column(n = "tijdstip", nullable = false)
        private LocalDateTime tijdstip;

        @NotBlank(message = "Titel is verplicht")
        @Column(n = "titel", nullable = false, length = 255)
        private String titel;

        @NotBlank(message = "Omschrijving is verplicht")
        @Column(n = "omschrijving", nullable = false, columnDefinition = "TEXT")
        private String omschrijving;

        @NotBlank(message = "Organisatie is verplicht")
        @Column(n = "organisatie", nullable = false, length = 255)
        private String organisatie;

        @NotBlank(message = "Email contactpersoon is verplicht")
        @Email(message = "Email moet geldig zijn")
        @Column(n = "email_contactpersoon", nullable = false, length = 255)
        private String emailContactpersoon;

        @NotNull(message = "Locatie is verplicht")
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(n = "locatie_id", nullable = false,
                foreignKey = @ForeignKey(n = "fk_evenement_locatie"))
        private Locatie locatie;

        @Column(n = "created_at", updatable = false)
        private LocalDateTime createdAt;

        @Column(n = "updated_at")
        private LocalDateTime updatedAt;

        // Constructors
        public Evenement() {}

        public Evenement(LocalDateTime tijdstip, String titel, String omschrijving,
                         String organisatie, String emailContactpersoon, Locatie locatie) {
            this.tijdstip = tijdstip;
            this.titel = titel;
            this.omschrijving = omschrijving;
            this.organisatie = organisatie;
            this.emailContactpersoon = emailContactpersoon;
            this.locatie = locatie;
        }

        @PrePersist
        protected void onCreate() {
            createdAt = LocalDateTime.now();
            updatedAt = LocalDateTime.now();
        }

        @PreUpdate
        protected void onUpdate() {
            updatedAt = LocalDateTime.now();
        }

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public LocalDateTime getTijdstip() {
            return tijdstip;
        }

        public void setTijdstip(LocalDateTime tijdstip) {
            this.tijdstip = tijdstip;
        }

        public String getTitel() {
            return titel;
        }

        public void setTitel(String titel) {
            this.titel = titel;
        }

        public String getOmschrijving() {
            return omschrijving;
        }

        public void setOmschrijving(String omschrijving) {
            this.omschrijving = omschrijving;
        }

        public String getOrganisatie() {
            return organisatie;
        }

        public void setOrganisatie(String organisatie) {
            this.organisatie = organisatie;
        }

        public String getEmailContactpersoon() {
            return emailContactpersoon;
        }

        public void setEmailContactpersoon(String emailContactpersoon) {
            this.emailContactpersoon = emailContactpersoon;
        }

        public Locatie getLocatie() {
            return locatie;
        }

        public void setLocatie(Locatie locatie) {
            this.locatie = locatie;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

        public LocalDateTime getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
        }

        @Override
        public String toString() {
            return "Evenement{" +
                    "id=" + id +
                    ", tijdstip=" + tijdstip +
                    ", titel='" + titel + '\'' +
                    ", organisatie='" + organisatie + '\'' +
                    ", emailContactpersoon='" + emailContactpersoon + '\'' +
                    ", locatie=" + (locatie != null ? locatie.getNaam() : "null") +
                    '}';
        }
    }
}
