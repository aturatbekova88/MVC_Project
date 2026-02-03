package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Doctor;
import peaksoft.service.DepartmentService;
import peaksoft.service.DoctorService;
import peaksoft.service.HospitalService;

@Controller
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    private final HospitalService hospitalService;
    private final DepartmentService departmentService;

    @GetMapping
    public String getAllDoctors(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "doctorList";
    }

    @GetMapping("/new")
    public String newDoctor(Model model) {
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "doctorForm";
    }

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute Doctor doctor,
                             @RequestParam Long hospitalId,
                             @RequestParam Long departmentId) {
        doctorService.assignHospitalAndDepartment(doctor, hospitalId, departmentId);
        return "redirect:/doctors";
    }

    @GetMapping("/edit/{id}")
    public String editDoctor(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", doctorService.getById(id));
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "doctorForm";
    }

    @PostMapping("/update/{id}")
    public String updateDoctor(@PathVariable Long id,
                               @ModelAttribute Doctor doctor,
                               @RequestParam Long hospitalId,
                               @RequestParam Long departmentId) {
        doctorService.assignHospitalAndDepartment(doctor, hospitalId, departmentId);
        doctorService.updateDoctor(id, doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }
}


