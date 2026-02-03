package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Appointment;
import peaksoft.service.*;

@Controller
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    @GetMapping
    public String getAllAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "appointmentList";
    }

    @GetMapping("/new")
    public String newAppointment(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        return "appointmentForm";
    }

    @PostMapping("/save")
    public String saveAppointment(@ModelAttribute Appointment appointment,
                                  @RequestParam Long patientId,
                                  @RequestParam Long doctorId,
                                  @RequestParam Long departmentId,
                                  @RequestParam Long hospitalId) {

        appointmentService.assignEntities(appointment, patientId, doctorId, departmentId, hospitalId);
        return "redirect:/appointments";
    }

    @GetMapping("/edit/{id}")
    public String editAppointment(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getById(id));
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        return "appointmentForm";
    }

    @PostMapping("/update/{id}")
    public String updateAppointment(@PathVariable Long id,
                                    @ModelAttribute Appointment appointment,
                                    @RequestParam Long patientId,
                                    @RequestParam Long doctorId,
                                    @RequestParam Long departmentId,
                                    @RequestParam Long hospitalId) {

        appointmentService.assignEntities(appointment, patientId, doctorId, departmentId, hospitalId);
        appointmentService.updateAppointment(id, appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments";
    }
}

