package peaksoft.repo.repoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.*;
import peaksoft.repo.AppointmentRepo;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class AppointmentRepoImpl implements AppointmentRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void saveAppointment(Long hospitalId, Appointment appointment) {
        Hospital hospital = entityManager.find(Hospital.class, hospitalId);
        appointment.setHospital(hospital);
        entityManager.persist(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments(Long hospitalId) {
        // order by id desc — чтобы последние записи были сверху
        return entityManager.createQuery(
                        "select a from Appointment a where a.hospital.id = :id order by a.id desc", Appointment.class)
                .setParameter("id", hospitalId)
                .getResultList();
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
            appointment.setPatient(entityManager.find(Patient.class, patientId));
            appointment.setDoctor(entityManager.find(Doctor.class, doctorId));
            appointment.setDepartment(entityManager.find(Department.class, departmentId));
            appointment.setHospital(entityManager.find(Hospital.class, hospitalId));
    }
}
