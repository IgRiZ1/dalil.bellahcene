# Anderlecht NGO - Evenementen Platform

Een moderne web applicatie voor het beheren van evenementen van een NGO in Anderlecht. Deze applicatie stelt de organisatie in staat om evenementen te organiseren, beheren en te delen met de lokale gemeenschap.

## 📋 Inhoudsopgave

- [Overzicht](#overzicht)
- [Functionaliteiten](#functionaliteiten)
- [Technische Stack](#technische-stack)
- [Installatie](#installatie)
- [Database Setup](#database-setup)
- [Gebruik](#gebruik)
- [Project Structuur](#project-structuur)
- [API Endpoints](#api-endpoints)
- [Deployment](#deployment)
- [Bijdragen](#bijdragen)

## 🎯 Overzicht

Het Anderlecht NGO Evenementen Platform is een Spring Boot web applicatie die ontworpen is om NGO's te helpen bij het organiseren en beheren van community evenementen. De applicatie biedt een intuïtieve interface voor het toevoegen, bekijken en beheren van evenementen op verschillende locaties in Anderlecht.

### Doelgroep
- NGO medewerkers en vrijwilligers
- Community managers
- Lokale organisaties
- Bewoners van Anderlecht

## ✨ Functionaliteiten

### 🏠 Homepage
- Overzicht van de laatste 20 evenementen
- Evenementen gesorteerd op datum (nieuwste eerst)
- Snelle navigatie naar event details

### 📝 Evenement Beheer
- **Nieuw evenement toevoegen** met volledige validatie
- **Event details bekijken** met uitgebreide informatie
- **Automatische validatie** van formuliergegevens

### 📍 Locatie Beheer
- Voorgedefinieerde locaties in Anderlecht
- Locatie informatie inclusief capaciteit en adres
- Dropdown selectie bij het aanmaken van evenementen

### 📧 Contact Informatie
- Over ons pagina met NGO details
- Contact gegevens en openingstijden
- Missie en visie informatie

### 🎨 Modern Design
- Responsive design met Tailwind CSS
- Professionele emerald/groen kleurenschema
- Gebruiksvriendelijke interface
- Mobile-first approach

## 🛠 Technische Stack

### Backend
- **Java 17** - Programming language
- **Spring Boot 3.5.4** - Application framework
- **Spring Data JPA** - Data persistence
- **Spring Data R2DBC** - Reactive database connectivity
- **Spring Validation** - Form validation
- **Hibernate** - ORM framework

### Frontend
- **Thymeleaf** - Template engine
- **Tailwind CSS 2.2.19** - CSS framework
- **HTML5** - Markup
- **Responsive Design** - Mobile-friendly

### Database
- **MySQL** - Primary database
- **R2DBC MySQL Driver** - Reactive database driver
- **Microsoft SQL Server** - Alternative database support

### Build Tools
- **Maven 3.9.11** - Dependency management
- **Maven Wrapper** - Version consistency

### Development Tools
- **Spring Boot DevTools** - Hot reload
- **Spring Boot Test** - Testing framework
- **Reactor Test** - Reactive testing

## 🚀 Installatie

### Vereisten
- Java 17 of hoger
- MySQL 8.0 of hoger
- Maven 3.6+ (of gebruik de meegeleverde wrapper)

### Stap 1: Repository Clonen
```bash
git clone <repository-url>
cd dalilbellahcen
```

### Stap 2: Database Configuratie
1. Start MySQL server
2. Maak een database aan:
```sql
CREATE DATABASE NGOanderlecht;
```

### Stap 3: Application Properties
Pas `src/main/resources/application.properties` aan naar jouw database configuratie:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/NGOanderlecht
spring.datasource.username=jouw_username
spring.datasource.password=jouw_password
```

### Stap 4: Applicatie Starten
```bash
# Met Maven Wrapper (aanbevolen)
./mvnw spring-boot:run

# Of met lokale Maven installatie
mvn spring-boot:run
```

De applicatie is beschikbaar op: `http://localhost:8080`

## 💾 Database Setup

### Automatische Database Initialisatie
De applicatie gebruikt `spring.jpa.hibernate.ddl-auto=update`, wat betekent dat:
- Tabellen automatisch worden aangemaakt
- Schema wijzigingen automatisch worden toegepast
- Bestaande data blijft behouden

### Database Schema

#### Event Tabel
```sql
CREATE TABLE Event (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titel VARCHAR(255) NOT NULL,
    omschrijving TEXT(2000) NOT NULL,
    organisatie VARCHAR(255) NOT NULL,
    contactEmail VARCHAR(255) NOT NULL,
    tijdstip DATETIME NOT NULL,
    locatie_id BIGINT,
    FOREIGN KEY (locatie_id) REFERENCES Locatie(id)
);
```

#### Locatie Tabel
```sql
CREATE TABLE Locatie (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    naam VARCHAR(255) NOT NULL,
    adres VARCHAR(255) NOT NULL,
    capaciteit INT
);
```

### Sample Data Toevoegen
```sql
INSERT INTO Locatie (naam, adres, capaciteit) VALUES
('Gemeenschapshuis Anderlecht', 'Victor Rauterstraat 456, 1070 Anderlecht', 150),
('Astridpark', 'Astridpark 1, 1070 Anderlecht', 300),
('Cultuurcentrum De Harmonie', 'Hamonstraat 14, 1070 Anderlecht', 200),
('Sportcomplex Erasme', 'Route de Lennik 808, 1070 Anderlecht', 500),
('Bibliotheek Anderlecht', 'Place de la Vaillance 17, 1070 Anderlecht', 80);
```

## 🖥 Gebruik

### 1. Homepage Bezoeken
- Ga naar `http://localhost:8080`
- Bekijk de lijst met recente evenementen
- Klik op "Bekijk" voor event details

### 2. Nieuw Evenement Toevoegen
- Klik op "Nieuw Evenement" in de navigatie
- Vul het formulier in met alle verplichte velden:
  - **Titel**: Naam van het evenement
  - **Organisatie**: Organiserende partij
  - **Email**: Contact email (moet geldig zijn)
  - **Omschrijving**: Uitgebreide beschrijving
  - **Tijdstip**: Datum en tijd
  - **Locatie**: Selecteer uit beschikbare locaties
- Klik "Toevoegen" om op te slaan

### 3. Event Details Bekijken
- Klik op "Bekijk" naast een evenement
- Zie alle details inclusief locatie informatie
- Contact gegevens en beschrijving

### 4. Over Ons Pagina
- Contact informatie van de NGO
- Adres en openingstijden
- Missie en telefoongegevens

## 📁 Project Structuur

```
src/
├── main/
│   ├── java/ehb/be/dalilbellahcen/
│   │   ├── Application.java                 # Main Spring Boot class
│   │   ├── controllers/
│   │   │   └── MainController.java          # Web controller
│   │   └── model/
│   │       ├── Event.java                   # Event entity
│   │       ├── Locatie.java                 # Location entity
│   │       └── dao/
│   │           ├── eventDao.java            # Event repository
│   │           └── LocatieDao.java          # Location repository
│   └── resources/
│       ├── application.properties           # Configuration
│       └── templates/                       # Thymeleaf templates
│           ├── index.html                   # Homepage
│           ├── form.html                    # Add event form
│           ├── detaile.html                 # Event details
│           └── about.html                   # About page
└── test/
    └── java/ehb/be/dalilbellahcen/
        └── ApplicationTests.java            # Basic tests
```

## 🌐 API Endpoints

| Method | Endpoint | Beschrijving |
|--------|----------|--------------|
| GET | `/` | Homepage met evenementen lijst |
| GET | `/index` | Alternatieve homepage route |
| GET | `/form` | Formulier voor nieuw evenement |
| POST | `/form` | Evenement opslaan |
| GET | `/details/{id}` | Event details bekijken |
| GET | `/about` | Over ons pagina |

## 🚢 Deployment

### Development
```bash
./mvnw spring-boot:run
```

### Production Build
```bash
./mvnw clean package
java -jar target/dalilbellahcen-0.0.1-SNAPSHOT.jar
```

### Docker (Optioneel)
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/dalilbellahcen-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
```

### Environment Variables
Voor productie, gebruik environment variables:
```bash
export SPRING_DATASOURCE_URL=jdbc:mysql://production-db:3306/NGOanderlecht
export SPRING_DATASOURCE_USERNAME=prod_user
export SPRING_DATASOURCE_PASSWORD=secure_password
```

## 🔧 Configuratie

### Development Profile
```properties
# application-dev.properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.thymeleaf.cache=false
```

### Production Profile
```properties
# application-prod.properties
spring.jpa.show-sql=false
spring.thymeleaf.cache=true
server.port=8080
```

## 🤝 Bijdragen

1. Fork het project
2. Maak een feature branch (`git checkout -b feature/nieuwe-functie`)
3. Commit je wijzigingen (`git commit -am 'Voeg nieuwe functie toe'`)
4. Push naar de branch (`git push origin feature/nieuwe-functie`)
5. Maak een Pull Request

## 📜 Licentie

Dit project is ontwikkeld door **Dalil Bellahcen** voor de Anderlecht NGO.

## 📞 Contact

- **Ontwikkelaar**: Dalil Bellahcen
- **NGO Email**: info@anderlechtngo.be
- **Telefoon**: 02 123 45 67
- **Adres**: Victor Rauterstraat 456, 1070 Anderlecht

## 🐛 Bekende Issues

- Event editing functionaliteit nog niet geïmplementeerd
- Geen user authentication systeem
- Basis error handling

## 📚 Bronnen
https://chatgpt.com/share/68a03aed-6ebc-8013-9e2e-ab9809b3238c
https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html Ook ben ik terug naar oude oefeningen gaan kijken hoe we daar alles hebben gedaan etc!
https://www.reddit.com/r/javahelp ( ze tonnen heel waar projecten waar je kan soms oplossingen vinden )






*Made with ❤️ for the Anderlecht community*
