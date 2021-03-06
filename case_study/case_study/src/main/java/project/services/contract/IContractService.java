package project.services.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.models.contract.Contract;
import project.repositories.contract.IContractViewDto;

import java.util.List;

public interface IContractService {
    Page<Contract> findAllWithStartDateSort(Pageable pageable);

    Page<Contract> findAllWithSearch(Double contractTotalMoney, String contractStartDate, String contractEndDate, Pageable pageable);

    void save(Contract contract);

    Contract findByIdActive(int id);

    IContractViewDto findContractViewDtoById(int id);

    List<Contract> findAllActive();

    Page<Contract> findAllWithEndDateSort(Pageable pageable);

    Page<Contract> findAllWithDepositSort(Pageable pageable);

    Page<Contract> findAllWithTotalMoneySort(Pageable pageable);
}
