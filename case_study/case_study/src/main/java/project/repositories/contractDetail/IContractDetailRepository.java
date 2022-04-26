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

    @Query(value = "SELECT contract_detail_id as contractDetailId, quantity ,contract_id as contractId, " +
            "attach_service.attach_service_id as attachServiceId, attach_service_name as attachServiceName " +
            "FROM contract_detail INNER JOIN attach_service ON contract_detail.attach_service_id = attach_service.attach_service_id WHERE contract_detail.active = 1 AND contract_detail_id =:id ", nativeQuery = true)
    <T> T findContractDetailViewDtoById(@Param("id") int id, Class<T> classType);
}
