package ehb.be.dalilbellahcen.config;

import ehb.be.dalilbellahcen.model.Evenement;
import ehb.be.dalilbellahcen.model.Locatie;
import ehb.be.dalilbellahcen.repository.EvenementRepository;
import ehb.be.dalilbellahcen.repository.LocatieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private LocatieRepository locatieRepository;

    @Autowired
    private EvenementRepository evenementRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        try {
            logger.info("Starting data initialization...");

            // Check if data already exists
            if (locatieRepository.count() > 0) {
                logger.info("Data already exists, skipping initialization");
                return;
            }

            // Create sample locations
            logger.info("Creating sample locations...");

            Locatie gemeenschapshuis = new Locatie(
                    "Gemeenschapshuis Anderlecht",
                    "Bergensesteenweg 123, 1070 Anderlecht",
                    150
            );

            Locatie park = new Locatie(
                    "Astridpark",
                    "Astridpark 1, 1070 Anderlecht",
                    500
            );

            Locatie cultureel = new Locatie(
                    "Cultuurcentrum De Harmonie",
                    "Hamonstraat 14, 1070 Anderlecht",
                    200
            );

            Locatie sportcomplex = new Locatie(
                    "Sportcomplex Erasme",
                    "Route de Lennik 808, 1070 Anderlecht",
                    300
            );

            Locatie bibliotheek = new Locatie(
                    "Bibliotheek Anderlecht",
                    "Place de la Vaillance 17, 1070 Anderlecht",
                    80
            );

            List<Locatie> locaties = Arrays.asList(
                    gemeenschapshuis, park, cultureel, sportcomplex, bibliotheek
            );

            locatieRepository.saveAll(locaties);
            logger.info("Successfully created {} locations", locaties.size());

            // Create sample events
            logger.info("Creating sample events...");

            List<Evenement> evenementen = Arrays.asList(
                    new Evenement(
                            LocalDateTime.now().plusDays(3).withHour(14).withMinute(0),
                            "Gratis juridisch advies",
                            "Elke eerste donderdag van de maand bieden wij gratis juridisch advies aan voor mensen met beperkte financiële middelen. Kom langs zonder afspraak tussen 14:00 en 17:00.",
                            "Eigen beheer",
                            "juridisch@anderlechtngo.be",
                            gemeenschapshuis
                    ),

                    new Evenement(
                            LocalDateTime.now().plusDays(7).withHour(15).withMinute(30),
                            "Buurtfeest Zomer 2025",
                            "Een gezellig buurtfeest met activiteiten voor jong en oud. Gratis toegang, hapjes en drankjes. Springkasteel voor de kinderen, live muziek en tombola.",
                            "Buurtcomité Centrum",
                            "buurt@anderlechtngo.be",
                            park
                    ),

                    new Evenement(
                            LocalDateTime.now().plusDays(10).withHour(19).withMinute(0),
                            "Taalcafé Nederlands - Beginners",
                            "Kom je Nederlands oefenen in een gezellige sfeer. Voor alle niveaus geschikt. Deze week focus op dagelijkse conversaties en woordenschat uitbreiden.",
                            "Eigen beheer",
                            "taal@anderlechtngo.be",
                            bibliotheek
                    ),

                    new Evenement(
                            LocalDateTime.now().plusDays(14).withHour(10).withMinute(0),
                            "Maandelijkse voedseluitdeling",
                            "Maandelijkse voedseluitdeling voor gezinnen in nood. Registratie vooraf vereist via email of telefoon. Breng eigen tassen mee.",
                            "Voedselbank Brussel West",
                            "voedsel@anderlechtngo.be",
                            gemeenschapshuis
                    ),

                    new Evenement(
                            LocalDateTime.now().plusDays(18).withHour(9).withMinute(30),
                            "Workshop Solliciteren & CV Schrijven",
                            "Leer hoe je een professioneel CV schrijft en je voorbereidt op sollicitatiegesprekken. Inclusief mock interviews en persoonlijke feedback.",
                            "VDAB Partner",
                            "workshop@anderlechtngo.be",
                            cultureel
                    ),