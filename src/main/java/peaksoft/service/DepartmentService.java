package peaksoft.service;

import peaksoft.entity.Department;

import java.util.List;

public interface DepartmentService {

    void saveDepartment(Long hospitalId,Department department);
    List<Department> getAllDepartments(Long hospitalId);
    Department getById(Long id);
    void updateDepartment(Long id,Department newDepartment);
    void deleteDepartment(Long id);

}
