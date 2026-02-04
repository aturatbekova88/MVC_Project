package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Hospital;
import peaksoft.repo.HospitalRepo;
import peaksoft.service.HospitalService;

import java.util.List;

@Controller
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;
    private final HospitalRepo hospitalRepo;

    @GetMapping
    public String getAllHospital( Model model) {
        List<Hospital> allHospitals = hospitalService.getAllHospitals();
        model.addAttribute("hospitals", allHospitals);
        model.addAttribute("hospital", new Hospital());
        return "hospitals";

    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("hospital", new Hospital());
        return "createHospital";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Hospital hospital){
        hospitalService.saveHospital(hospital);
        return "redirect:/hospitals";
    }

    @GetMapping("/by/{id}")
    public String getById(@PathVariable("id") Long id, Model model){
        Hospital byId = hospitalService.getById(id);
        model.addAttribute("byId",byId);
        return "hospitalById";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model){
        Hospital hospital = hospitalService.getById(id);
        model.addAttribute("editHospital", hospital);
        return "editHospital";
    }

    @PostMapping("/update/{id}")
    public String updateHospital(@PathVariable("id") Long id, @ModelAttribute("editHospital") Hospital hospital){
        hospitalService.updateHospital(id, hospital);
        return "redirect:/hospitals";
    }

    @PostMapping("/delete/{id}")
    public String deleteHospital(@PathVariable("id") Long deleteId){
        hospitalService.deleteHospital(deleteId);
        return "redirect:/hospitals";
    }
}

