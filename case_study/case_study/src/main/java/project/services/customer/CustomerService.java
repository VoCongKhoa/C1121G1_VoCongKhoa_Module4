package project.services.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.dto.customer.InHouseCustomerDto;
import project.models.customer.Customer;
import project.repositories.customer.ICustomerRepository;
import project.repositories.customer.IInHouseCustomerDto;

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
    public Customer findByCode(String customerCode) {
        return iCustomerRepository.findByCode(customerCode);
    }

    @Override
    public void save(Customer customer) {
        iCustomerRepository.save(customer);
    }

    @Override
    public Customer findById(int id) {
        return iCustomerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> findAllActive() {
        return iCustomerRepository.findAllActive();
    }

    @Override
    public void update(Customer customer) {
        iCustomerRepository.update(customer);
    }

    @Override
    public void delete(int customerId) {
        iCustomerRepository.updateActive(customerId);
    }

    @Override
    public Page<Customer> findAllWithNameSort(Pageable pageable) {
        return iCustomerRepository.findAllWithNameSort(pageable);
    }

    @Override
    public Page<Customer> findAllWithSearch(String code, String name, String address, Pageable pageable) {
        return iCustomerRepository.findAllActiveCodeAndNameAndAddressSearch(code, name, address, pageable);
    }

    @Override
    public Customer findByIdActive(int id) {
        return iCustomerRepository.findByIdActive(id);
    }

    @Override
    public Page<Customer> findAllWithBirthdaySort(Pageable pageable) {
        return iCustomerRepository.findAllWithBirthdaySort(pageable);
    }

//    @Override
//    public Page<InHouseCustomerDto> findAllWithNameSortListInHouse(Pageable pageable) {
//        return iCustomerRepository.findAllWithNameSortListInHouse(pageable);
//    }
    @Override
    public Page<IInHouseCustomerDto> findAllWithNameSortListInHouse(Pageable pageable) {
        return iCustomerRepository.findAllWithNameSortListInHouse(IInHouseCustomerDto.class, pageable);
    }

    @Override
    public Page<IInHouseCustomerDto> findAllWithBirthdaySortListInHouse(Pageable pageable) {
        return iCustomerRepository.findAllWithBirthdaySortListInHouse(IInHouseCustomerDto.class, pageable);
    }

    @Override
    public Page<IInHouseCustomerDto> findAllWithSearchListInHouse(String code, String name, String address, Pageable pageable) {
        iCustomerRepository.setCodeParam(code);
        iCustomerRepository.setNameParam(name);
        iCustomerRepository.setAddressParam(address);
        return iCustomerRepository.findAllWithSearchListInHouse(IInHouseCustomerDto.class, pageable);
    }


}
