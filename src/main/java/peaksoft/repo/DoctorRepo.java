package peaksoft.repo;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Doctor;

import java.util.List;

@Repository
@Transactional
public interface DoctorRepo {

    void saveDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();
    Doctor getById(Long id);
    void updateDoctor(Long id,Doctor newDoctor);
    void deleteDoctor(Long id);
    void assignHospitalAndDepartment(Doctor doctor, Long hospitalId, Long departmentId);
}
