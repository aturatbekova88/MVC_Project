package peaksoft.repo;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Department;

import java.util.List;

@Repository
@Transactional
public interface DepartmentRepo {

    void saveDepartment(Long hospitalId, Department department);
    List<Department> getAllDepartments(Long hospitalId);
    Department getById(Long id);
    void updateDepartment(Long id,Department newDepartment);
    void deleteDepartment(Long id);

}
