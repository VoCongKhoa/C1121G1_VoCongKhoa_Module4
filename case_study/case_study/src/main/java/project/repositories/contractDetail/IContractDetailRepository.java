package project.repositories.contractDetail;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.models.contractDetail.ContractDetail;

public interface IContractDetailRepository extends JpaRepository<ContractDetail, Integer> {

    @Query(value = "SELECT * FROM contract_detail WHERE active=1 ORDER BY quantity ", nativeQuery = true)
    Page<ContractDetail> findAllWithQuantitySort(Pageable pageable);

    @Query(value = "SELECT * FROM contract_detail WHERE active=1 AND attach_service_id >= :attachServiceId ", nativeQuery = true)
    Page<ContractDetail> findAllWithSearch(@Param("attachServiceId") Integer attachServiceId, Pageable pageable);
}
