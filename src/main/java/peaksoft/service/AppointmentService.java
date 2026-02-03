package peaksoft.service;

import peaksoft.entity.Appointment;

import java.util.List;

public interface AppointmentService {

    void saveAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
    Appointment getById(Long id);
    void updateAppointment(Long id,Appointment newAppointment);
    void deleteAppointment(Long id);
    void assignEntities(Appointment appointment, Long patientId, Long doctorId, Long departmentId, Long hospitalId);
}
