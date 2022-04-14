package project.services.customer;

import project.models.customer.CustomerType;

import java.util.List;

public interface ICustomerTypeService {
    List<CustomerType> findAll();

    List<CustomerType> findAllActive();
}
