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
@RequestMapping("/patients")
public class PatientController {
    private final PatientRepository patientRepository;
    private final PatientService patientService;

    public PatientController(PatientRepository patientRepository, PatientService patientService) {
        this.patientRepository = patientRepository;
        this.patientService = patientService;
    }

    // Afficher la liste des patients avec pagination et recherche
    @GetMapping
    public String listPatients(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "5") int size,
                               @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Patient> patientPage = patientService.findByNomContains(keyword, pageable);

        model.addAttribute("patients", patientPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", patientPage.getTotalPages());
        model.addAttribute("keyword", keyword);

        return "patients/list";
    }

    // Afficher le formulaire d'ajout
    @GetMapping("/add")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patients/add";
    }

    // Ajouter un patient
    @PostMapping("/save")
    public String savePatient(@ModelAttribute Patient patient) {
        patientRepository.save(patient);
        return "redirect:/patients";
    }

    // Afficher le formulaire d'édition
    @GetMapping("/edit/{id}")
    public String showEditPatientForm(@PathVariable Long id, Model model) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) return "redirect:/patients";
        model.addAttribute("patient", patient);
        return "patients/edit";
    }

    // Mettre à jour un patient
    @PostMapping("/update")
    public String updatePatient(@ModelAttribute Patient patient) {
        patientRepository.save(patient);
        return "redirect:/patients";
    }

    // Supprimer un patient
    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientRepository.deleteById(id);
        return "redirect:/patients";
    }
}
