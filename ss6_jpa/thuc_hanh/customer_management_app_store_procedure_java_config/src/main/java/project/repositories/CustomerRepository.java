package project.repositories;

import org.springframework.stereotype.Repository;
import project.models.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Repository
public class CustomerRepository implements ICustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> findAll() {
        return entityManager.createQuery("select p from customer p", Customer.class).getResultList();
    }

    @Override
    public void save(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public Customer findById(Long id) {
        return entityManager.find(Customer.class,id);
    }

    @Override
    public void update(Customer customer) {
        entityManager.merge(customer);

    }

    @Override
    public void remove(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public List<Customer> searchByName(String nameSearch) {
        List<Customer> result = new ArrayList<>();
        for (Customer customer : findAll()) {
            if (customer.getFirstName().toLowerCase(Locale.ROOT).contains(nameSearch.trim().toLowerCase(Locale.ROOT))){
                result.add(customer);
            }
        }
        return result;
    }

    @Override
    public boolean insertWithStoredProcedure(Customer customer) {
        String sql = "CALL Insert_Customer(:firstName, :lastName)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("firstName", customer.getFirstName());
        query.setParameter("lastName", customer.getLastName());
        return query.executeUpdate() == 0;
    }
}
