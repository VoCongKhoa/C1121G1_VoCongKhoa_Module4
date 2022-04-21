package project.services.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.models.contract.Contract;
import project.repositories.contract.IContractRepository;

@Service
public class ContractService implements IContractService{

    @Autowired
    IContractRepository iContractRepository;

    @Override
    public Page<Contract> findAllWithStartDateSort(Pageable pageable) {
        return iContractRepository.findAllWithStartDateSort(pageable);
    }

    @Override
    public Page<Contract> findAllWithSearch(Double contractTotalMoney, String contractStartDate, String contractEndDate, Pageable pageable) {

        if (contractEndDate.equals("")){
            return iContractRepository.findAllWithContractTotalMoneyAndContractStartDate(contractTotalMoney,contractStartDate,pageable);
        } else {
            return iContractRepository.findAllWithSearch(contractTotalMoney,contractStartDate,contractEndDate,pageable);
        }
    }

    @Override
    public void save(Contract contract) {
        iContractRepository.save(contract);
    }
}
