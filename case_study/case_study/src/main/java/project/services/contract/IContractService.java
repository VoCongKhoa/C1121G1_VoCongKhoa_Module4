package project.services.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.models.contract.Contract;

public interface IContractService {
    Page<Contract> findAllWithStartDateSort(Pageable pageable);

    Page<Contract> findAllWithSearch(Double contractTotalMoney, String contractStartDate, String contractEndDate, Pageable pageable);

    void save(Contract contract);
}
