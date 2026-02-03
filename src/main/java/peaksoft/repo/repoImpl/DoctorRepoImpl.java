package peaksoft.repo.repoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Doctor;
import peaksoft.repo.DoctorRepo;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class DoctorRepoImpl implements DoctorRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void saveDoctor(Doctor doctor) {
        entityManager.persist(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return entityManager.createQuery("select d from Doctor d",Doctor.class).getResultList();
    }

    @Override
    public Doctor getById(Long id) {
        return entityManager.find(Doctor.class, id);
    }

    @Override
    public void updateDoctor(Long id, Doctor newDoctor) {
        Doctor doctor = getById(id);
        doctor.setFirstName(newDoctor.getFirstName());
        doctor.setLastName(newDoctor.getLastName());
        doctor.setPosition(newDoctor.getPosition());
        doctor.setEmail(newDoctor.getEmail());
    }

    @Override
    public void deleteDoctor(Long id) {
        entityManager.remove(getById(id));
    }

    @Override
    public void assignHospitalAndDepartment(Doctor doctor, Long hospitalId, Long departmentId) {

    }
}
