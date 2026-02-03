package peaksoft.service;

import peaksoft.entity.Doctor;

import java.util.List;

public interface DoctorService {

    void saveDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();
    Doctor getById(Long id);
    void updateDoctor(Long id,Doctor newDoctor);
    void deleteDoctor(Long id);
    void assignHospitalAndDepartment(Doctor doctor, Long hospitalId, Long departmentId);
}
