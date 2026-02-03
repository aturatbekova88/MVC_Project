package peaksoft.repo.repoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Appointment;
import peaksoft.repo.AppointmentRepo;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class AppointmentRepoImpl implements AppointmentRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void saveAppointment(Appointment appointment) {
        entityManager.persist(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return entityManager.createQuery("select a from Appointment a order by a.date desc", Appointment.class).getResultList();
    }

    @Override
    public Appointment getById(Long id) {
        return entityManager.find(Appointment.class, id);
    }

    @Override
    public void updateAppointment(Long id, Appointment newAppointment) {
        Appointment appointment = getById(id);
        appointment.setDate(newAppointment.getDate());
    }

    @Override
    public void deleteAppointment(Long id) {
        entityManager.remove(getById(id));
    }

    @Override
    public void assignEntities(Appointment appointment, Long patientId, Long doctorId, Long departmentId, Long hospitalId) {

    }
}
