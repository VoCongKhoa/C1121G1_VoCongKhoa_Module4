package project.services.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.models.employee.Employee;
import project.repositories.employee.IEmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService{

    @Autowired
    IEmployeeRepository iEmployeeRepository;

    @Override
    public Page<Employee> findAllWithNameSort(Pageable pageable) {
        return iEmployeeRepository.findAllWithNameSort(pageable);
    }

    @Override
    public Page<Employee> findAllWithSearch(String name, String address, Pageable pageable) {
        return iEmployeeRepository.findAllWithSearch(name,address,pageable);
    }
}
