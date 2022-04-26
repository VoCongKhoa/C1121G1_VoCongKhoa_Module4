package project.services.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.models.employee.Employee;

import java.util.List;

public interface IEmployeeService {
    Page<Employee> findAllWithNameSort(Pageable pageable);

    Page<Employee> findAllWithSearch(String name, String address,Integer positionIdVal, Pageable pageable);

    void save(Employee employee);

    Employee findByIdActive(int id);

    List<Employee> findAllActive();

    Page<Employee> findAllWithSalarySort(Pageable pageable);

    Page<Employee> findAllWithBirthdaySort(Pageable pageable);

    Employee findByUsername(String username);
}
