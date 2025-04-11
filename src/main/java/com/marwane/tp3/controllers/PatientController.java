package com.marwane.tp3.controllers;
import com.marwane.tp3.entities.Patient;
import com.marwane.tp3.repositories.PatientRepository;
import com.marwane.tp3.services.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/patients";
    }

    @GetMapping("/patients")
    public String patients(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Patient> pagePatients = patientService.findPatientsByName(keyword, page, size);
        model.addAttribute("listPatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping("/deletePatient")
    public String delete(Long id, String keyword, int page) {
        patientService.deletePatient(id);
        return "redirect:/patients?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/formPatient")
    public String formPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "formPatient";
    }

    @PostMapping("/savePatient")
    public String savePatient(@Valid Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "formPatient";
        }
        patientService.savePatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/editPatient")
    public String editPatient(Model model, Long id) {
        Patient patient = patientService.getPatient(id);
        model.addAttribute("patient", patient);
        return "editPatient";
    }
}
