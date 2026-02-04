package peaksoft.repo.repoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Department;
import peaksoft.entity.Hospital;
import peaksoft.repo.DepartmentRepo;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class DepartmentRepoImpl implements DepartmentRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void saveDepartment(Long hospitalId, Department department) {
        Hospital hospital = entityManager.find(Hospital.class, hospitalId);
        if (hospital == null) {
            throw new RuntimeException("Hospital not found!");
        }
        List<Department> departments = entityManager.createQuery(
                        "select d from Department d " +
                                "where d.name = :name and d.hospital.id = :hid",
                        Department.class
                )
                .setParameter("name", department.getName())
                .setParameter("hid", hospitalId)
                .getResultList();

        if (!departments.isEmpty()) {
            throw new RuntimeException("Department already exists!");
        }
        department.setHospital(hospital);
        entityManager.persist(department);
    }


    @Override
    public List<Department> getAllDepartments(Long hospitalId) {
        return entityManager.createQuery("select d from Department d where d.hospital.id = :id", Department.class).setParameter("id", hospitalId).getResultList();
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

}
