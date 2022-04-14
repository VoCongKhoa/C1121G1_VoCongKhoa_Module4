package project.repositories.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.models.customer.CustomerType;

import java.util.List;

public interface ICustomerTypeRepository extends JpaRepository<CustomerType, String> {

    @Query(value = "select * from customer_type where active = 1", nativeQuery = true)
    List<CustomerType> findAllActive();
}
