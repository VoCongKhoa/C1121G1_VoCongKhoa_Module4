package project.repositories.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.models.services.RentType;

import java.util.List;

public interface IRentTypeRepository extends JpaRepository<RentType, Integer> {

    @Query(value = "select * from rent_type where active = 1", nativeQuery = true)
    List<RentType> findAllActive();
}
