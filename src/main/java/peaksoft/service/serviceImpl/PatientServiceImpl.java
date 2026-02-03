package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Hospital;
import peaksoft.entity.Patient;
import peaksoft.repo.PatientRepo;
import peaksoft.service.HospitalService;
import peaksoft.service.PatientService;

import java.util.List;

@Service//add this annotation
@Transactional
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepo patientRepo;
    private final HospitalService hospitalService;

    @Override
    public void savePatient(Patient patient) {
        if (patient.getFirstName() == null || patient.getLastName() == null || patient.getPhoneNumber() == null) {
            throw new RuntimeException("Patient fields cannot be null!");
        }
        if (!patient.getPhoneNumber().startsWith("+996")) {
            throw new RuntimeException("Phone number must start with +996");
        }
        patientRepo.savePatient(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepo.getAllPatients();
    }

    @Override
    public Patient getById(Long id) {
        return patientRepo.getById(id);
    }

    @Override
    public void updatePatient(Long id, Patient newPatient) {
        patientRepo.updatePatient(id, newPatient);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepo.deletePatient(id);
    }

    @Override
    public void assignHospital(Patient patient, Long hospitalId) {
        Hospital hospital = hospitalService.getById(hospitalId);
        patient.setHospital(hospital);
    }
}
