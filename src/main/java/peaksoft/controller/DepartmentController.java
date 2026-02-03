package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Department;
import peaksoft.service.DepartmentService;
import peaksoft.service.HospitalService;

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

        @GetMapping
        public String getAllDepartments(Model model) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "departmentList";
        }

        @GetMapping("/new")
        public String newDepartment(Model model) {
            model.addAttribute("department", new Department());
            model.addAttribute("hospitals", hospitalService.getAllHospitals());
            return "departmentForm";
        }

        @PostMapping("/save")
        public String saveDepartment(@ModelAttribute Department department, @RequestParam Long hospitalId) {
            departmentService.assignHospital(department, hospitalId);
            return "redirect:/departments";
        }

        @GetMapping("/edit/{id}")
        public String editDepartment(@PathVariable Long id, Model model) {
            model.addAttribute("department", departmentService.getById(id));
            model.addAttribute("hospitals", hospitalService.getAllHospitals());
            return "departmentForm";
        }

        @PostMapping("/update/{id}")
        public String updateDepartment(@PathVariable Long id, @ModelAttribute Department department, @RequestParam Long hospitalId) {
            departmentService.assignHospital(department, hospitalId);
            departmentService.updateDepartment(id, department);
            return "redirect:/departments";
        }

        @GetMapping("/delete/{id}")
        public String deleteDepartment(@PathVariable Long id) {
            departmentService.deleteDepartment(id);
            return "redirect:/departments";
        }
    }



