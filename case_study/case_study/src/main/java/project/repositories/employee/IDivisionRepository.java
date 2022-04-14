package project.repositories.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.models.employee.Division;

import java.util.List;

public interface IDivisionRepository extends JpaRepository<Division, Integer> {

    @Query(value = "select * from division where active = 1 ", nativeQuery = true)
    List<Division> findAllActive();
}
