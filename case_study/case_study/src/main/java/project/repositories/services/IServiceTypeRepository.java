package project.repositories.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.models.services.ServiceType;

import java.util.List;

public interface IServiceTypeRepository extends JpaRepository<ServiceType,Integer> {

    @Query(value = "select * from service_type where active = 1", nativeQuery = true)
    List<ServiceType> findAllActive();
}
