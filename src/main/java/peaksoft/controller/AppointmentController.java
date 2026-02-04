package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Appointment;
import peaksoft.service.*;

import java.util.List;

@Controller
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final DepartmentService departmentService;

    @GetMapping("/{hospitalId}")
    public String getAllAppointments(@PathVariable Long hospitalId,Model model) {
        List<Appointment> appointments = appointmentService.getAllAppointments(hospitalId);
        model.addAttribute("appointments", appointments);
        model.addAttribute("hospitalId", hospitalId);
        return "appointments";
    }

    @GetMapping("/new/{hospitalId}")
    public String create(@PathVariable Long hospitalId, Model model){
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("departments",
                departmentService.getAllDepartments(hospitalId));
        model.addAttribute("hospitalId", hospitalId);
        return "createAppointment";
    }


    @PostMapping("/save/{hospitalId}")
    public String save(@PathVariable Long hospitalId,
                       @ModelAttribute Appointment appointment,
                       @RequestParam Long patientId,
                       @RequestParam Long doctorId,
                       @RequestParam Long departmentId){

        appointmentService.assignEntities(
                appointment,
                patientId,
                doctorId,
                departmentId,
                hospitalId
        );

        appointmentService.saveAppointment(hospitalId, appointment);

        return "redirect:/appointments/" + hospitalId;
    }

    @GetMapping("/edit/{id}")
    public String editAppointment(@PathVariable Long id, Model model) {
        Appointment appointment = appointmentService.getById(id);
        model.addAttribute("appointment", appointment);
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("departments", departmentService.getAllDepartments(appointment.getHospital().getId()));
        return "editAppointment";
    }

    @PostMapping("/update/{id}")
    public String updateAppointment(@PathVariable Long id,
                                    @ModelAttribute Appointment appointment,
                                    @RequestParam Long patientId,
                                    @RequestParam Long doctorId,
                                    @RequestParam Long departmentId) {
        appointmentService.assignEntities(appointment, patientId, doctorId, departmentId, appointment.getHospital().getId());
        appointmentService.updateAppointment(id, appointment);
        return "redirect:/appointments/" + appointment.getHospital().getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        Long hospitalId = appointmentService.getById(id).getHospital().getId();
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments/" + hospitalId;
    }
}

