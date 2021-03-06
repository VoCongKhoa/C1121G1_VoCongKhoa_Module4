package project.services.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.customer.CustomerType;
import project.repositories.customer.ICustomerTypeRepository;

import java.util.List;

@Service
public class CustomerTypeService implements ICustomerTypeService{

    @Autowired
    ICustomerTypeRepository iCustomerTypeRepository;

    @Override
    public List<CustomerType> findAll() {
        return iCustomerTypeRepository.findAll();
    }

    @Override
    public List<CustomerType> findAllActive() {
        return iCustomerTypeRepository.findAllActive();
    }
}
