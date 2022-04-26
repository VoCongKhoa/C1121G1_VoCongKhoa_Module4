package project.services.contractDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.models.contractDetail.ContractDetail;
import project.repositories.contractDetail.IContractDetailRepository;
import project.repositories.contractDetail.IContractDetailViewDto;

import java.util.Optional;

@Service
public class ContractDetailService implements IContractDetailService{

    @Autowired
    IContractDetailRepository iContractDetailRepository;

    @Override
    public Page<ContractDetail> findAllWithQuantitySort(Pageable pageable) {
        return iContractDetailRepository.findAllWithQuantitySort(pageable);
    }

    @Override
    public Page<ContractDetail> findAllWithSearch(Integer attachServiceId, Pageable pageable) {
        return iContractDetailRepository.findAllWithSearch(attachServiceId,pageable);
    }

    @Override
    public ContractDetail findById(int id) {
        return iContractDetailRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ContractDetail contractDetail) {
        iContractDetailRepository.save(contractDetail);
    }

    @Override
    public IContractDetailViewDto findContractDetailViewDtoById(int id) {
        return iContractDetailRepository.findContractDetailViewDtoById(id, IContractDetailViewDto.class);
    }
}
