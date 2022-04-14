package project.repositories.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.models.employee.Position;

import java.util.List;

public interface IPositionRepository extends JpaRepository<Position,Integer> {

    @Query(value = "select * from `position` where active = 1 ", nativeQuery = true)
    List<Position> findAllActive();
}
