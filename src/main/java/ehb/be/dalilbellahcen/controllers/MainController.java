package ehb.be.dalilbellahcen.controllers;

import ehb.be.dalilbellahcen.model.Evenement;
import ehb.be.dalilbellahcen.model.Locatie;
import ehb.be.dalilbellahcen.repository.EvenementRepository;
import ehb.be.dalilbellahcen.repository.LocatieRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private EvenementRepository evenementRepository;

    @Autowired
    private LocatieRepository locatieRepository;

    /**
     * Homepage - toont de laatste 10 evenementen
     */
    @GetMapping("/")
    public String index(Model model) {
        try {
            logger.info("Loading homepage with latest events");
            List<Evenement> laatsteEvenementen = evenementRepository.findTop10ByOrderByTijdstipDesc(
                    PageRequest.of(0, 10)
            );
            model.addAttribute("evenementen", laatsteEvenementen);
            logger.info("Found {} events for homepage", laatsteEvenementen.size());
            return "index";
        } catch (Exception e) {
            logger.error("Error loading homepage", e);
            model.addAttribute("error", "Er is een fout opgetreden bij het laden van de evenementen.");
            return "error";
        }
    }

    /**
     * Formulier voor nieuw evenement - GET
     */
    @GetMapping("/new")
    public String newEvenement(Model model) {
        try {
            logger.info("Loading new event form");
            model.addAttribute("evenement", new Evenement());

            List<Locatie> locaties = locatieRepository.findAllByOrderByNaamAsc();
            model.addAttribute("locaties", locaties);
            logger.info("Found {} locations for new event form", locaties.size());

            return "new";
        } catch (Exception e) {
            logger.error("Error loading new event form", e);
            model.addAttribute("error", "Er is een fout opgetreden bij het laden van het formulier.");
            return "error";
        }
    }

    /**
     * Nieuw evenement opslaan - POST
     */
    @PostMapping("/new")
    public String createEvenement(@Valid @ModelAttribute Evenement evenement,
                                  BindingResult bindingResult,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        try {
            logger.info("Attempting to create new event: {}", evenement.getTitel());

            // Controleer validatie fouten
            if (bindingResult.hasErrors()) {
                logger.warn("Validation errors found for new event");
                model.addAttribute("locaties", locatieRepository.findAllByOrderByNaamAsc());
                return "new";
            }

            // Extra validatie: tijdstip in de toekomst
            if (evenement.getTijdstip() != null && evenement.getTijdstip().isBefore(LocalDateTime.now())) {
                bindingResult.rejectValue("tijdstip", "error.tijdstip",
                        "Het tijdstip moet in de toekomst liggen");
                model.addAttribute("locaties", locatieRepository.findAllByOrderByNaamAsc());
                return "new";
            }

            // Opslaan in database
            Evenement savedEvenement = evenementRepository.save(evenement);
            logger.info("Successfully created event with ID: {}", savedEvenement.getId());

            redirectAttributes.addFlashAttribute("success",
                    "Evenement '" + evenement.getTitel() + "' is succesvol toegevoegd!");

            return "redirect:/";

        } catch (Exception e) {
            logger.error("Error creating new event", e);
            model.addAttribute("error", "Er is een fout opgetreden bij het opslaan van het evenement.");
            model.addAttribute("locaties", locatieRepository.findAllByOrderByNaamAsc());
            return "new";
        }
    }

    /**
     * Evenement details pagina
     */
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            logger.info("Loading details for event ID: {}", id);

            Optional<Evenement> evenement = evenementRepository.findById(id);
            if (evenement.isPresent()) {
                model.addAttribute("evenement", evenement.get());
                logger.info("Successfully loaded event details: {}", evenement.get().getTitel());
                return "details";
            } else {
                logger.warn("Event not found with ID: {}", id);
                redirectAttributes.addFlashAttribute("error",
                        "Evenement niet gevonden.");
                return "redirect:/";
            }

        } catch (Exception e) {
            logger.error("Error loading event details for ID: " + id, e);
            redirectAttributes.addFlashAttribute("error",
                    "Er is een fout opgetreden bij het laden van de evenement details.");
            return "redirect:/";
        }
    }

    /**
     * Over ons pagina
     */
    @GetMapping("/about")
    public String about() {
        logger.info("Loading about page");
        return "about";
    }

    /**
     * Zoekfunctionaliteit (optioneel)
     */
    @GetMapping("/search")
    public String search(@RequestParam(required = false) String query, Model model) {
        try {
            if (query != null && !query.trim().isEmpty()) {
                logger.info("Searching for events with query: {}", query);
                List<Evenement> gevondenEvenementen = evenementRepository.findByTitelContainingIgnoreCase(query.trim());
                model.addAttribute("evenementen", gevondenEvenementen);
                model.addAttribute("query", query);
                logger.info("Found {} events matching query: {}", gevondenEvenementen.size(), query);
            } else {
                model.addAttribute("evenementen", List.of());
            }
            return "search";
        } catch (Exception e) {
            logger.error("Error during search", e);
            model.addAttribute("error", "Er is een fout opgetreden tijdens het zoeken.");
            model.addAttribute("evenementen", List.of());
            return "search";
        }
    }

    /**
     * Error handling
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        logger.error("Unhandled exception occurred", e);
        model.addAttribute("error", "Er is een onverwachte fout opgetreden. Probeer het later opnieuw.");
        return "error";
    }
}