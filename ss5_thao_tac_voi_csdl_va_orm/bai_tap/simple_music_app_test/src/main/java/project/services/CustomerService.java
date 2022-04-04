package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Customer;
import project.repositories.ICustomerRepository;

import java.util.List;

@Service
public class CustomerService implements ICustomerService{

    @Autowired
    ICustomerRepository iCustomerRepository;

    @Override
    public List<Customer> findAll() {
        return iCustomerRepository.findAll();
    }
}
