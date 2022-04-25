package project.repositories.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
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

    // SORT BY NAME
    @Query(value = "select customer_id as customerId, " +
            "customer_code as customerCode, " +
            "customer_name as customerName, " +
            "customer_birthday as customerBirthday, " +
            "customer_gender as customerGender, " +
            "customer_id_card as customerIdCard, " +
            "customer_phone as customerPhone, " +
            "customer_email as customerEmail, " +
            "customer_address as customerAddress, " +
            "customer_type_name as customerTypeName, " +
            "contract_id as contractId, " +
            "attach_service_id as attachServiceId, " +
            "contract_detail_id as contractDetailId " +
            " from inHouseCustomerSortByName ", nativeQuery = true)
    <T> Page<T> findAllWithNameSortListInHouse(Class<T> classType, Pageable pageable);

    // SORT BY BIRTHDAY
    @Query(value = "select customer_id as customerId, " +
            "customer_code as customerCode, " +
            "customer_name as customerName, " +
            "customer_birthday as customerBirthday, " +
            "customer_gender as customerGender, " +
            "customer_id_card as customerIdCard, " +
            "customer_phone as customerPhone, " +
            "customer_email as customerEmail, " +
            "customer_address as customerAddress, " +
            "customer_type_name as customerTypeName, " +
            "contract_id as contractId, " +
            "attach_service_id as attachServiceId, " +
            "contract_detail_id as contractDetailId " +
            " from inHouseCustomerSortByBirthday ", nativeQuery = true)
    <T> Page<T> findAllWithBirthdaySortListInHouse(Class<T> classType, Pageable pageable);


    // SET PARAM FOR FUNCTION
    @Modifying
    @Transactional
    @Query(value = "SET @customer_code = :code", nativeQuery = true)
    void setCodeParam(@Param("code") String code);
    @Modifying
    @Transactional
    @Query(value = "SET @customer_name = :name", nativeQuery = true)
    void setNameParam(@Param("name") String name);
    @Modifying
    @Transactional
    @Query(value = "SET @customer_address = :address", nativeQuery = true)
    void setAddressParam(@Param("address") String address);

    // USE VIEW IN DB FO SEARCHING
    @Query(value ="SELECT customer_id as customerId, " +
            "customer_code as customerCode, " +
            "customer_name as customerName, " +
            "customer_birthday as customerBirthday, " +
            "customer_gender as customerGender, " +
            "customer_id_card as customerIdCard, " +
            "customer_phone as customerPhone, " +
            "customer_email as customerEmail, " +
            "customer_address as customerAddress, " +
            "customer_type_name as customerTypeName, " +
            "contract_id as contractId, " +
            "attach_service_id as attachServiceId, " +
            "contract_detail_id as contractDetailId FROM inHouseCustomerSearch ", nativeQuery = true)
    <T> Page<T> findAllWithSearchListInHouse(Class<T> classType, Pageable pageable);




//    @Query(value = "select customer_id customerId, " +
//            "customer_code customerCode, " +
//            "customer_name customerName, " +
//            "customer_birthday customerBirthday, " +
//            "customer_gender customerGender, " +
//            "customer_id_card customerIdCard, " +
//            "customer_phone customerPhone, " +
//            "customer_email customerEmail, " +
//            "customer_address customerAddress, " +
//            "customer_type_name customerTypeName, " +
//            "contract_id contractId, " +
//            "attach_service_id attachServiceId, " +
//            "contract_detail_id contractDetailId " +
//            " from inHouseCustomer where customer_code like concat('%',:code,'%') and customer_name like concat('%',:name,'%') and customer_address like concat('%',:address,'%') ", nativeQuery = true)
//    Page<IInHouseCustomerDto> findAllWithSearchListInHouse(@Param("code") String code, @Param("name") String name, @Param("address") String address, Pageable pageable);




//    @Query(value = "select customer.customer_id customerId, " +
//            "customer.customer_code customerCode, " +
//            "customer.customer_name customerName, " +
//            "customer.customer_birthday customerBirthday, " +
//            "customer.customer_gender customerGender, " +
//            "customer.customer_id_card customerIdCard, " +
//            "customer.customer_phone customerPhone, " +
//            "customer.customer_email customerEmail, " +
//            "customer.customer_address customerAddress, " +
//            "customer_type.customer_type_name customerTypeName, " +
//            "contract.contract_id contractId, " +
//            "attach_service.attach_service_id attachServiceId, " +
//            "contract_detail.contract_detail_id contractDetailId " +
//            " from contract left join customer on contract.customer_id = customer.customer_id " +
//            " left join contract_detail on contract.contract_id = contract_detail.contract_id " +
//            " left join attach_service on contract_detail.attach_service_id = attach_service.attach_service_id " +
//            " left join customer_type on customer.customer_type_id = customer_type.customer_type_id " +
//            " where contract.active = 1 ", nativeQuery = true)
//    Page<IInHouseCustomerDto> findAllWithSearchListInHouse(@Param("code") String code, @Param("name") String name, @Param("address") String address, Pageable pageable);

//    @Query(value = "select new project.models.customer.InHouseCustomerDto(" +
//            "customer_id , " +
//            "customer_code , " +
//            "customer_name , " +
//            "customer_birthday , " +
//            "customer_gender , " +
//            "customer_id_card , " +
//            "customer_phone , " +
//            "customer_email , " +
//            "customer_address , " +
//            "customer_type_name , " +
//            "contract_id , " +
//            "attach_service_id , " +
//            "contract_detail_id , " +
//            "from inHouseCustomer ) ", nativeQuery = true)
//    Page<InHouseCustomerDto> findAllWithNameSortListInHouse(Pageable pageable);
}
