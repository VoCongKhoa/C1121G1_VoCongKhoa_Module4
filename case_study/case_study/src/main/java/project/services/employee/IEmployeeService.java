package project.services.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.models.employee.Employee;

public interface IEmployeeService {
    Page<Employee> findAllWithNameSort(Pageable pageable);

    Page<Employee> findAllWithSearch(String name, String address, Pageable pageable);
}
