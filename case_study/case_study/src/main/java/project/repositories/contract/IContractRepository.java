package project.repositories.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.models.contract.Contract;

import java.util.List;

public interface IContractRepository extends JpaRepository<Contract, Integer> {

    @Query(value = "select * from contract where active = 1 order by contract_start_date ", nativeQuery = true)
    Page<Contract> findAllWithStartDateSort(Pageable pageable);
    @Query(value = "select * from contract where active = 1 order by contract_end_date ", nativeQuery = true)
    Page<Contract> findAllWithEndDateSort(Pageable pageable);
    @Query(value = "select * from contract where active = 1 order by contract_deposit ", nativeQuery = true)
    Page<Contract> findAllWithDepositSort(Pageable pageable);
    @Query(value = "select * from contract where active = 1 order by contract_total_money ", nativeQuery = true)
    Page<Contract> findAllWithTotalMoneySort(Pageable pageable);

    @Query(value = "select * from contract where active = 1 and contract_total_money >= :contractTotalMoney and contract_start_date >= :contractStartDate and contract_end_date <= :contractEndDate ", nativeQuery = true)
    Page<Contract> findAllWithSearch(@Param("contractTotalMoney") Double contractTotalMoney, @Param("contractStartDate") String contractStartDate, @Param("contractEndDate") String contractEndDate, Pageable pageable);

    @Query(value = "select * from contract where active = 1 and contract_total_money >= :contractTotalMoney and contract_start_date >= :contractStartDate ", nativeQuery = true)
    Page<Contract> findAllWithContractTotalMoneyAndContractStartDate(@Param("contractTotalMoney") Double contractTotalMoney, @Param("contractStartDate") String contractStartDate, Pageable pageable);

    @Query(value = "select * from contract where active = 1 and contract_id =:id ", nativeQuery = true)
    Contract findByIdActive(@Param("id") int id);

    @Query(value = "SELECT contract_id contractId, contract_start_date contractStartDate, contract_end_date contractEndDate, " +
            "contract_deposit contractDeposit, contract_total_money contractTotalMoney, employee.employee_id employeeId, " +
            "employee.employee_name employeeName, customer.customer_code customerCode,customer.customer_name customerName, " +
            "service.service_code serviceCode, service.service_name serviceName, contract.active active FROM contract " +
            "INNER JOIN employee ON contract.employee_id=employee.employee_id " +
            "INNER JOIN customer ON contract.customer_id=customer.customer_id " +
            "INNER JOIN service ON contract.service_id=service.service_id " +
            "WHERE contract.active = 1 AND contract_id=:id ", nativeQuery = true)
    IContractViewDto findContractDetailDtoById(@Param("id") int id);

    @Query(value = "select * from contract where active = 1 order by contract_id ", nativeQuery = true)
    List<Contract> findAllActive();

}
