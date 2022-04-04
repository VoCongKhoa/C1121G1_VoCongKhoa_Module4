package project.repositories;

import project.models.Customer;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();
}
