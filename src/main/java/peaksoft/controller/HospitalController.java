package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Hospital;
import peaksoft.service.HospitalService;

import java.util.List;

@Controller
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;

    @GetMapping
    public String getAllHospital( Model model) {
        List<Hospital> allHospitals = hospitalService.getAllHospitals();
        model.addAttribute("hospitals", allHospitals);
        return "hospitalList";

    }

    @GetMapping("/new")
    public String newHospital(Model model) {
        model.addAttribute("hospital", new Hospital());
        return "hospitalForm";
    }

    @PostMapping("/save")
    public String saveHospital(@ModelAttribute Hospital hospital) {
        hospitalService.saveHospital(hospital);
        return "redirect:/hospitals";
    }

    @GetMapping("/edit/{id}")
    public String editHospital(@PathVariable Long id, Model model) {
        model.addAttribute("hospital", hospitalService.getById(id));
        return "hospitalForm";
    }

    @PostMapping("/update/{id}")
    public String updateHospital(@PathVariable Long id, @ModelAttribute Hospital hospital) {
        hospitalService.updateHospital(id, hospital);
        return "redirect:/hospitals";
    }

    @GetMapping("/delete/{id}")
    public String deleteHospital(@PathVariable Long id) {
        hospitalService.deleteHospital(id);
        return "redirect:/hospitals";
    }
}

