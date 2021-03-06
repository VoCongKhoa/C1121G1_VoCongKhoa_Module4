package project.services.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.dto.customer.InHouseCustomerDto;
import project.models.customer.Customer;
import project.repositories.customer.IInHouseCustomerDto;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    Customer findByCode(String customerCode);

    void save(Customer customer);

    Customer findById(int id);

    List<Customer> findAllActive();

    void update(Customer customer);

    void delete(int customerId);

    Page<Customer> findAllWithNameSort(Pageable pageable);

    Page<Customer> findAllWithSearch(String code, String name, String address, Pageable pageable);

    Customer findByIdActive(int id);

    Page<Customer> findAllWithBirthdaySort(Pageable pageable);

    Page<IInHouseCustomerDto> findAllWithNameSortListInHouse(Pageable pageable);

    Page<IInHouseCustomerDto> findAllWithBirthdaySortListInHouse(Pageable pageable);

    Page<IInHouseCustomerDto> findAllWithSearchListInHouse(String code, String name, String address, Pageable pageable);
}
