package peaksoft.repo.repoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Hospital;
import peaksoft.entity.Patient;
import peaksoft.repo.PatientRepo;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class PatientRepoImpl implements PatientRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void savePatient(Long hospitalId,Patient patient) {
        if (!patient.getPhoneNumber().startsWith("+996")) {
            throw new RuntimeException("Phone number must start with +996");
        }
        Hospital hospital = entityManager.find(Hospital.class, hospitalId);
        patient.setHospital(hospital);
        entityManager.persist(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return entityManager.createQuery("select p from Patient p", Patient.class).getResultList();
    }

    @Override
    public Patient getById(Long id) {
        return entityManager.find(Patient.class, id);
    }

    @Override
    public void updatePatient(Long id, Patient newPatient) {
        Patient patient = entityManager.find(Patient.class, id);
        patient.setFirstName(newPatient.getFirstName());
        patient.setLastName(newPatient.getLastName());
        patient.setPhoneNumber(newPatient.getPhoneNumber());
        patient.setGender(newPatient.getGender());
        patient.setEmail(newPatient.getEmail());
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = getById(id);
        entityManager.remove(patient);
    }

    @Override
    public void assignHospital(Patient patient, Long hospitalId) {
        patient.setHospital(entityManager.find(Hospital.class, hospitalId));
    }
}
