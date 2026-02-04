package peaksoft.repo.repoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Department;
import peaksoft.entity.Doctor;
import peaksoft.entity.Hospital;
import peaksoft.repo.DoctorRepo;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class DoctorRepoImpl implements DoctorRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void saveDoctor(Long hospitalId,Doctor doctor) {
        Hospital hospital = entityManager.find(Hospital.class, hospitalId);
        doctor.setHospital(hospital);
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
    public void assignDoctorToDepartment(Long doctorId, Long departmentId) {
        Doctor doctor = getById(doctorId);
        Department department = entityManager.find(Department.class, departmentId);
        doctor.setDepartment(department);
    }


}
