package peaksoft.service;

import peaksoft.entity.Doctor;

import java.util.List;

public interface DoctorService {

    void saveDoctor(Long hospitalId, Doctor doctor);

    List<Doctor> getAllDoctors();

    Doctor getById(Long id);

    void updateDoctor(Long id, Doctor newDoctor);

    void deleteDoctor(Long id);

    void assignDoctorToDepartment(Long doctorId, Long departmentId);
}
