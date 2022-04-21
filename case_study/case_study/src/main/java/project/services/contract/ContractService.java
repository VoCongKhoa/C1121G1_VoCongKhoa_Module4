package project.services.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.models.contract.Contract;
import project.repositories.contract.IContractViewDto;
import project.repositories.contract.IContractRepository;

import java.util.List;

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

    @Override
    public Contract findByIdActive(int id) {
        return iContractRepository.findByIdActive(id);
    }

    @Override
    public IContractViewDto findContractDetailDtoById(int id) {
        return iContractRepository.findContractDetailDtoById(id);
    }

    @Override
    public List<Contract> findAllActive() {
        return iContractRepository.findAllActive();
    }

    @Override
    public Page<Contract> findAllWithEndDateSort(Pageable pageable) {
        return iContractRepository.findAllWithEndDateSort(pageable);
    }

    @Override
    public Page<Contract> findAllWithDepositSort(Pageable pageable) {
        return iContractRepository.findAllWithDepositSort(pageable);
    }

    @Override
    public Page<Contract> findAllWithTotalMoneySort(Pageable pageable) {
        return iContractRepository.findAllWithTotalMoneySort(pageable);
    }
}
