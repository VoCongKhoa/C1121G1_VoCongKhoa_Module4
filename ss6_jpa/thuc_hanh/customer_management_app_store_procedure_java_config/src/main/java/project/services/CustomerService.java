package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Customer;
import project.repositories.ICustomerRepository;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    ICustomerRepository iCustomerRepository;

    @Override
    public List<Customer> findAll() {
        return iCustomerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
        iCustomerRepository.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        return iCustomerRepository.findById(id);
    }


    @Override
    public void update(Customer customer) {
        iCustomerRepository.update(customer);
    }

    @Override
    public void remove(Long id) {
        iCustomerRepository.remove(id);
    }

    @Override
    public List<Customer> searchByName(String nameSearch) {
        return iCustomerRepository.searchByName(nameSearch);
    }

    @Override
    public boolean insertWithStoredProcedure(Customer customer) {
        return iCustomerRepository.insertWithStoredProcedure(customer);
    }
}
