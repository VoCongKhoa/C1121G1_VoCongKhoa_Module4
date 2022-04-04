package project.repositories;

import org.springframework.stereotype.Repository;
import project.models.Customer;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CustomerRepository implements ICustomerRepository{
    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> typedQuery = BaseRepository.entityManager.createQuery("select c from com.mysql.jdbc.Driver.",Customer.class);
        return typedQuery.getResultList();
    }
}
