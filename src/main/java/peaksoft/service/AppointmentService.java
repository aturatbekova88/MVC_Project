package peaksoft.service;

import peaksoft.entity.Appointment;

import java.util.List;

public interface AppointmentService {

    void saveAppointment(Long hospitalId,Appointment appointment);
    List<Appointment> getAllAppointments(Long hospitalId);
    Appointment getById(Long id);
    void updateAppointment(Long id,Appointment newAppointment);
    void deleteAppointment(Long id);
    void assignEntities(Appointment appointment, Long patientId, Long doctorId, Long departmentId, Long hospitalId);
}
