package project.repositories.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.models.employee.Employee;

import java.util.List;

public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query(value = "select * from employee where active = 1 order by employee_name ", nativeQuery = true)
    Page<Employee> findAllWithNameSort(Pageable pageable);
    @Query(value = "select * from employee where active = 1 order by employee_salary ", nativeQuery = true)
    Page<Employee> findAllWithSalarySort(Pageable pageable);
    @Query(value = "select * from employee where active = 1 order by employee_birthday ", nativeQuery = true)
    Page<Employee> findAllWithBirthdaySort(Pageable pageable);

    @Query(value = "select * from employee where active = 1 and employee_name like concat('%',:name,'%') and " +
            "employee_address like concat('%',:address,'%') ", nativeQuery = true)
    Page<Employee> findAllWithNameAndAddressSearch(@Param("name") String name, @Param("address") String address, Pageable pageable);

    @Query(value = "select * from employee where active = 1 and employee_name like concat('%',:name,'%') and " +
            "employee_address like concat('%',:address,'%') and position_id = :positionIdVal ", nativeQuery = true)
    Page<Employee> findAllWithNameAndAddressAndPositionSearch(@Param("name") String name, @Param("address") String address,
                                                              @Param("positionIdVal") Integer positionIdVal, Pageable pageable);

    @Query(value = "select * from employee where active = 1 and employee_id = :id ", nativeQuery = true)
    Employee findByIdActive(@Param("id") int id);

    @Query(value = "select * from employee where active = 1 ", nativeQuery = true)
    List<Employee> findAllActive();

    @Query(value = "SELECT employee.* FROM app_user INNER JOIN employee ON app_user.employee_id = employee.employee_id WHERE  employee.active = 1 AND username = :username ", nativeQuery = true)
    Employee findByUsername(@Param("username") String username);
}
