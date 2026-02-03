package peaksoft.repo.repoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Department;
import peaksoft.repo.DepartmentRepo;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class DepartmentRepoImpl implements DepartmentRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void saveDepartment(Department department) {
        entityManager.persist(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return entityManager.createQuery("select d from Department d", Department.class).getResultList();
    }

    @Override
    public Department getById(Long id) {
        return entityManager.find(Department.class, id);
    }

    @Override
    public void updateDepartment(Long id, Department newDepartment) {
        Department department = getById(id);
        department.setName(newDepartment.getName());
    }

    @Override
    public void deleteDepartment(Long id) {
        entityManager.remove(getById(id));
    }

    @Override
    public void assignHospital(Department department, Long hospitalId) {

    }

}
