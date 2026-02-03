package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Department;
import peaksoft.entity.Hospital;
import peaksoft.repo.DepartmentRepo;
import peaksoft.service.DepartmentService;
import peaksoft.service.HospitalService;

import java.util.List;

@Service//add this annotation
@Transactional
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepo departmentRepo;
    private final HospitalService hospitalService;

    @Override
    public void saveDepartment(Department department) {
        if (department.getName() == null) {
            throw new RuntimeException("Department name cannot be null!");
        }
        // проверка на уникальность
        for (Department d : departmentRepo.getAllDepartments()) {
            if (d.getName().equalsIgnoreCase(department.getName())) {
                throw new RuntimeException("Department with this name already exists!");
            }
        }
        departmentRepo.saveDepartment(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepo.getAllDepartments();
    }

    @Override
    public Department getById(Long id) {
        return departmentRepo.getById(id);
    }

    @Override
    public void updateDepartment(Long id, Department newDepartment) {
        departmentRepo.updateDepartment(id, newDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepo.deleteDepartment(id);
    }

    @Override
    public void assignHospital(Department department, Long hospitalId) {
        Hospital hospital = hospitalService.getById(hospitalId);
        department.setHospital(hospital);
    }


}
