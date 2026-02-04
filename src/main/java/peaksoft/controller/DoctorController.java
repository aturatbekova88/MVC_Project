package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Doctor;
import peaksoft.service.DepartmentService;
import peaksoft.service.DoctorService;
import peaksoft.service.HospitalService;

import java.util.List;

@Controller
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    private final HospitalService hospitalService;
    private final DepartmentService departmentService;

    @GetMapping
    public String getAllDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctors";
    }

    @GetMapping("/new/{hospitalId}")
    public String create(Model model,
                         @PathVariable Long hospitalId){
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("departments",
                departmentService.getAllDepartments(hospitalId));
        return "createDoctors";
    }

    @PostMapping("/save/{hospitalId}")
    public String save(@PathVariable Long hospitalId,
                       @ModelAttribute Doctor doctor){
        doctorService.saveDoctor(hospitalId, doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/edit/{id}")
    public String editDoctor(@PathVariable Long id, Model model) {
        Doctor doctor = doctorService.getById(id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("departments", departmentService.getAllDepartments(doctor.getHospital().getId()));
        return "editDoctor";
    }

    @PostMapping("/update/{id}")
    public String updateDoctor(@PathVariable Long id,
                               @ModelAttribute Doctor doctor,
                               @RequestParam Long departmentId) {
        doctorService.assignDoctorToDepartment(id, departmentId);
        doctorService.updateDoctor(id, doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }
}


