package project.repositories.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import project.dto.customer.InHouseCustomerDto;
import project.models.customer.Customer;


import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "select * from customer where active = 1 and customer_code = :customerCode ", nativeQuery = true)
    Customer findByCode(@Param("customerCode") String customerCode);

    @Query(value = "select * from customer where active = 1 ", nativeQuery = true)
    List<Customer> findAllActive();

    @Transactional
    @Modifying
    @Query(value = "UPDATE customer c SET c.customer_address = :#{#customer.customerAddress}, c.customer_birthday = :#{#customer.customerBirthday}, " +
            "c.customer_email = :#{#customer.customerEmail}, c.customer_gender = :#{#customer.customerGender}, c.customer_id_card = :#{#customer.customerIdCard}, " +
            "c.customer_name = :#{#customer.customerName}, c.customer_phone = :#{#customer.customerPhone}, c.customer_type_id = :#{#customer.customerType.customerTypeId} " +
            "WHERE (active = 1 AND c.customer_id = :#{#customer.customerId}) ", nativeQuery = true)
    void update(@Param("customer") Customer customer);

    @Transactional
    @Modifying
    @Query(value = "UPDATE customer c SET c.active = 0 " +
            "WHERE (active = 1 AND c.customer_id = :#{#customerId}) ", nativeQuery = true)
    void updateActive(@Param("customerId") int customerId);

    @Query(value = "select * from customer where active = 1 order by customer_name ", nativeQuery = true)
    Page<Customer> findAllWithNameSort(Pageable pageable);

    @Query(value = "select * from customer where active = 1 order by customer_birthday ", nativeQuery = true)
    Page<Customer> findAllWithBirthdaySort(Pageable pageable);

    @Query(value = "select * from customer where active = 1", nativeQuery = true)
    Page<Customer> findAllActivePaging(Pageable pageable);

    @Query(value = "select * from customer where active = 1 and customer_code like concat('%',:code,'%') and " +
            "customer_name like concat('%',:name,'%') and customer_address like concat('%',:address,'%') ", nativeQuery = true)
    Page<Customer> findAllActiveCodeAndNameAndAddressSearch(@Param("code") String code, @Param("name") String name, @Param("address") String address, Pageable pageable);

    @Query(value = "select * from customer where active = 1 and customer_id = :id", nativeQuery = true)
    Customer findByIdActive(@Param("id") int id);

    @Query(value = "select customer.customer_id , customer.customer_code , " +
            "customer.customer_name , customer.customer_birthday , customer.customer_gender , " +
            "customer.customer_id_card , customer.customer_phone , customer.customer_email , " +
            "customer.customer_address , customer_type.customer_type_name , contract.contract_id , " +
            "attach_service.attach_service_id , contract_detail.contract_detail_id , customer.active  " +
            "from contract left join customer on contract.customer_id = customer.customer_id " +
            "left join contract_detail on contract.contract_id = contract_detail.contract_id " +
            "left join attach_service on contract_detail.attach_service_id = attach_service.attach_service_id " +
            "left join customer_type on customer.customer_type_id = customer_type.customer_type_id " +
            "where contract.active = 1 and (curdate() between contract.contract_start_date and contract.contract_end_date) ", nativeQuery = true)
    Page<InHouseCustomerDto> findAllWithNameSortListInHouse(Pageable pageable);

//    @Query(value = "select new project.dto.customer.InHouseCustomerDto(customer.customer_id customerId, customer.customer_code customerCode, " +
//            "customer.customer_name customerName, customer.customer_birthday customerBirthday, customer.customer_gender customerGender, " +
//            "customer.customer_id_card customerIdCard, customer.customer_phone customerPhone, customer.customer_email customerEmail, " +
//            "customer.customer_address customerAddress, customer.customer_type_name customerTypeName, contract.contract_id contractId, " +
//            "attach_service.attach_service_id attachServiceId, contract_detail.contract_detail_id contractDetailId, customer.active active) " +
//            "from contract left join customer on contract.customer_id = customer.customer_id " +
//            "left join contract_detail on contract.contract_id = contract_detail.contract_id " +
//            "left join attach_service on contract_detail.attach_service_id = attach_service.attach_service_id " +
//            "where contract.active = 1 and (curdate() between contract.contract_start_date and contract.contract_end_date) ", nativeQuery = true)
//    Page<InHouseCustomerDto> findAllWithNameSortListInHouse(Pageable pageable);
}
