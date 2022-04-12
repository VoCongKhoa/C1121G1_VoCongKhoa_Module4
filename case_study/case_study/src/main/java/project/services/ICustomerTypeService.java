package project.services;

import project.models.CustomerType;

import java.util.List;

public interface ICustomerTypeService {
    List<CustomerType> findAll();

    List<CustomerType> findAllActive();
}
