package project.services;

import project.models.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
}
