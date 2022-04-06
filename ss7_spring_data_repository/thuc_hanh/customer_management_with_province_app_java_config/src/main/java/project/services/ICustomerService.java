package project.services;

import project.models.Customer;
import project.models.Province;

import java.util.List;

public interface ICustomerService extends IGeneralService<Customer> {
    Iterable<Customer> findAllByProvince(Province province);
}
