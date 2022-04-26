package project.services.contractDetail;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.models.contractDetail.ContractDetail;
import project.repositories.contractDetail.IContractDetailViewDto;

import java.util.Optional;

public interface IContractDetailService {
    Page<ContractDetail> findAllWithQuantitySort(Pageable pageable);

    Page<ContractDetail> findAllWithSearch(Integer attachServiceId, Pageable pageable);

    ContractDetail findById(int id);

    void save(ContractDetail contractDetail);

    IContractDetailViewDto findContractDetailViewDtoById(int id);
}
