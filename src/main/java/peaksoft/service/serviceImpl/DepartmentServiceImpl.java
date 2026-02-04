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
    public void saveDepartment(Long hospitalId,Department department) {
        departmentRepo.saveDepartment(hospitalId, department);
    }

    @Override
    public List<Department> getAllDepartments(Long hospitalId) {
        return departmentRepo.getAllDepartments(hospitalId);
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


}
