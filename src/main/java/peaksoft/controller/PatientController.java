package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Patient;
import peaksoft.service.HospitalService;
import peaksoft.service.PatientService;

import java.util.List;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public String getAllPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients",patients);
        return "patients";
    }

    @GetMapping("/new/{hospitalId}")
    public String createPatient(@PathVariable Long hospitalId,Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("hospitals", hospitalId);
        return "createPatients";
    }

    @PostMapping("/save/{hospitalId}")
    public String savePatient(@ModelAttribute Patient patient, @PathVariable Long hospitalId) {
        patientService.savePatient(hospitalId, patient);
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String editPatient(@PathVariable Long id, Model model) {
        Patient patient = patientService.getById(id);
        model.addAttribute("patient", patient);
        return "editPatient";
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

