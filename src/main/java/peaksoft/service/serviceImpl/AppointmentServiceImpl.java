package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.*;
import peaksoft.repo.AppointmentRepo;
import peaksoft.service.*;

import java.util.List;

@Service//add this annotation
@Transactional
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    @Override
    public void saveAppointment(Long hospitalId,Appointment appointment) {
        appointmentRepo.saveAppointment(hospitalId, appointment);
    }

    @Override
    public List<Appointment> getAllAppointments(Long hospitalId) {
        return appointmentRepo.getAllAppointments(hospitalId);
    }

    @Override
    public Appointment getById(Long id) {
        return appointmentRepo.getById(id);
    }

    @Override
    public void updateAppointment(Long id, Appointment newAppointment) {
        appointmentRepo.updateAppointment(id, newAppointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepo.deleteAppointment(id);
    }

    @Override
    public void assignEntities(Appointment appointment, Long patientId, Long doctorId, Long departmentId, Long hospitalId) {
        Patient patient = patientService.getById(patientId);
        Doctor doctor = doctorService.getById(doctorId);
        Department department = departmentService.getById(departmentId);
        Hospital hospital = hospitalService.getById(hospitalId);

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDepartment(department);
        appointment.setHospital(hospital);
    }
}
