package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Department;
import peaksoft.service.DepartmentService;
import peaksoft.service.HospitalService;

import java.util.List;

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/{hospitalId}")
    public String getAll(@PathVariable("hospitalId") Long hospitalId, Model model){
        List<Department> departments = departmentService.getAllDepartments(hospitalId);
        model.addAttribute("departments",departments);
        model.addAttribute("hospitalId", hospitalId);
        return "departments";
    }



    @GetMapping("/new/{hospitalId}")
    public String create(Model model,
                         @PathVariable Long hospitalId){
        model.addAttribute("department", new Department());
        model.addAttribute("hospitalId", hospitalId);
        return "createDepartment";
    }

    @PostMapping("/save/{hospitalId}")
    public String save(@PathVariable Long hospitalId,
                       @ModelAttribute Department department,
                       Model model){

        try {
            departmentService.saveDepartment(hospitalId, department);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("hospitalId", hospitalId);
            return "createDepartment";
        }

        return "redirect:/departments/" + hospitalId;
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Department department = departmentService.getById(id);
        model.addAttribute("department", department);
        return "editDepartment";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute Department department){
        departmentService.updateDepartment(id, department);
        return "redirect:/departments/"+ department.getHospital().getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        Long hospitalId = departmentService.getById(id).getHospital().getId();
        departmentService.deleteDepartment(id);
        return "redirect:/departments/" + hospitalId;
    }

    }



