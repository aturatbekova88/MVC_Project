package peaksoft.service;

import peaksoft.entity.Patient;

import java.util.List;

public interface PatientService {

    void savePatient(Long hospitalId,Patient patient);
    List<Patient> getAllPatients();
    Patient getById(Long id);
    void updatePatient(Long id,Patient newPatient);
    void deletePatient(Long id);
    void assignHospital(Patient patient, Long hospitalId);
}
