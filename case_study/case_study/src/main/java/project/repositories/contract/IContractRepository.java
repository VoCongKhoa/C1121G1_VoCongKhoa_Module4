package project.repositories.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.models.contract.Contract;

public interface IContractRepository extends JpaRepository<Contract, Integer> {

    @Query(value = "select * from contract where active = 1 order by contract_start_date ", nativeQuery = true)
    Page<Contract> findAllWithStartDateSort(Pageable pageable);

    @Query(value = "select * from contract where active = 1 and contract_total_money >= :contractTotalMoney and contract_start_date >= :contractStartDate and contract_end_date <= :contractEndDate ", nativeQuery = true)
    Page<Contract> findAllWithSearch(@Param("contractTotalMoney") Double contractTotalMoney, @Param("contractStartDate") String contractStartDate, @Param("contractEndDate") String contractEndDate, Pageable pageable);

    @Query(value = "select * from contract where active = 1 and contract_total_money >= :contractTotalMoney and contract_start_date >= :contractStartDate ", nativeQuery = true)
    Page<Contract> findAllWithContractTotalMoneyAndContractStartDate(@Param("contractTotalMoney") Double contractTotalMoney, @Param("contractStartDate") String contractStartDate, Pageable pageable);
}
