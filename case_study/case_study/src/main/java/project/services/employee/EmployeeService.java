package project.services.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.models.employee.Employee;
import project.repositories.employee.IEmployeeRepository;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService{

    @Autowired
    IEmployeeRepository iEmployeeRepository;

    @Override
    public Page<Employee> findAllWithNameSort(Pageable pageable) {
        return iEmployeeRepository.findAllWithNameSort(pageable);
    }

    @Override
    public Page<Employee> findAllWithSearch(String name, String address,Integer positionIdVal, Pageable pageable) {
        if (positionIdVal == 0){
            return iEmployeeRepository.findAllWithNameAndAddressSearch(name,address,pageable);
        } else {
            return iEmployeeRepository.findAllWithNameAndAddressAndPositionSearch(name,address,positionIdVal,pageable);
        }
    }

    @Override
    public void save(Employee employee) {
        iEmployeeRepository.save(employee);
    }

    @Override
    public Employee findByIdActive(int id) {
        return iEmployeeRepository.findByIdActive(id);
    }

    @Override
    public List<Employee> findAllActive() {
        return iEmployeeRepository.findAllActive();
    }
}
