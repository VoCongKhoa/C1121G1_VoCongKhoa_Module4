package project.services;

import project.models.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    Customer findByCode(String customerCode);

    void save(Customer customer);

    Customer findById(int id);

    List<Customer> findAllActive();

    void update(Customer customer);

    void delete(int customerId);
}
