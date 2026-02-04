package peaksoft.repo;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Appointment;

import java.util.List;

@Repository
@Transactional
public interface AppointmentRepo {

    void saveAppointment(Long hospitalId,Appointment appointment);
    List<Appointment> getAllAppointments(Long hospitalId);
    Appointment getById(Long id);
    void updateAppointment(Long id,Appointment newAppointment);
    void deleteAppointment(Long id);
    void assignEntities(Appointment appointment, Long patientId, Long doctorId, Long departmentId, Long hospitalId);
}
