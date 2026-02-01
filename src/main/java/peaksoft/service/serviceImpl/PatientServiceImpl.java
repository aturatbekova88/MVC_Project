package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Patient;
import peaksoft.repo.PatientRepo;
import peaksoft.service.PatientService;

import java.util.List;

@Service//add this annotation
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepo patientRepo;

    @Override
    public void savePatient(Patient patient) {
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
}
