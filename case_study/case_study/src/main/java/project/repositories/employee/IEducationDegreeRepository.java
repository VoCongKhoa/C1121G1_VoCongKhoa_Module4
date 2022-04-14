package project.repositories.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.models.employee.EducationDegree;

import java.util.List;

public interface IEducationDegreeRepository extends JpaRepository<EducationDegree, Integer> {

    @Query(value = "select * from education_degree where active = 1 ", nativeQuery = true)
    List<EducationDegree> findAllActive();
}
