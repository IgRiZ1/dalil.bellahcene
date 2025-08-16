package ehb.be.dalilbellahcen.controllers;

import ehb.be.dalilbellahcen.model.Event;
import ehb.be.dalilbellahcen.model.Locatie;
import ehb.be.dalilbellahcen.model.dao.eventDao;
import ehb.be.dalilbellahcen.model.dao.LocatieDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class MainController {

    private final eventDao eventdao;
    private final LocatieDao locatiedao;

    @Autowired
    public MainController(eventDao eventdao, LocatieDao locatiedao) {
        this.eventdao = eventdao;
        this.locatiedao = locatiedao;
    }


    @ModelAttribute("allLocaties")
    public Iterable<Locatie> getAllLocaties() {
        return locatiedao.findAll();
    }


    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("events", eventdao.findTop20ByOrderByTijdstipDesc());
        return "index";
    }


    @GetMapping("/form")
    public String nieuwFormulier(Model model) {
        model.addAttribute("event", new Event());
        return "form";
    }


    @PostMapping("/form")
    public String nieuwEvent(@Valid @ModelAttribute("event") Event event,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            return "form";
        }
        eventdao.save(event);
        return "redirect:/index";
    }


    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Long id, Model model) {
        Event event = eventdao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event met id " + id + " niet gevonden"));
        model.addAttribute("event", event);
        return "details";
    }


    @GetMapping("/about")
    public String about() {
        return "about";
    }
}