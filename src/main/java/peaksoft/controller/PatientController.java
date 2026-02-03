package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Patient;
import peaksoft.service.HospitalService;
import peaksoft.service.PatientService;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    private final HospitalService hospitalService;

    @GetMapping
    public String getAllPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patientList";
    }

    @GetMapping("/new")
    public String newPatient(Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        return "patientForm";
    }

    @PostMapping("/save")
    public String savePatient(@ModelAttribute Patient patient, @RequestParam Long hospitalId) {
        patientService.assignHospital(patient, hospitalId);
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String editPatient(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.getById(id));
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        return "patientForm";
    }

    @PostMapping("/update/{id}")
    public String updatePatient(@PathVariable Long id, @ModelAttribute Patient patient, @RequestParam Long hospitalId) {
        patientService.assignHospital(patient, hospitalId);
        patientService.updatePatient(id, patient);
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}

