package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Department;
import peaksoft.entity.Doctor;
import peaksoft.entity.Hospital;
import peaksoft.repo.DoctorRepo;
import peaksoft.service.DepartmentService;
import peaksoft.service.DoctorService;
import peaksoft.service.HospitalService;

import java.util.List;

@Service//add this annotation
@Transactional
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepo doctorRepo;
    private final HospitalService hospitalService;
    private final DepartmentService departmentService;

    @Override
    public void saveDoctor(Long hospitalId,Doctor doctor) {
        if (doctor.getEmail() == null || doctor.getFirstName() == null || doctor.getLastName() == null) {
            throw new RuntimeException("Doctor fields cannot be null!");
        }
        // проверка email
        for (Doctor d : doctorRepo.getAllDoctors()) {
            if (d.getEmail().equalsIgnoreCase(doctor.getEmail())) {
                throw new RuntimeException("Doctor email must be unique!");
            }
        }
        doctorRepo.saveDoctor(hospitalId,doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepo.getAllDoctors();
    }

    @Override
    public Doctor getById(Long id) {
        return doctorRepo.getById(id);
    }

    @Override
    public void updateDoctor(Long id, Doctor newDoctor) {
        doctorRepo.updateDoctor(id, newDoctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepo.deleteDoctor(id);
    }

    @Override
    public void assignDoctorToDepartment(Long doctorId, Long departmentId) {
        doctorRepo.assignDoctorToDepartment(doctorId, departmentId);
    }


}
