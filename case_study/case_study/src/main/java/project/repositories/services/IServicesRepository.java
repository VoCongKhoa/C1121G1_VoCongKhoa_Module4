package project.repositories.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.models.services.Services;

import java.util.List;

public interface IServicesRepository extends JpaRepository<Services, Integer> {

    @Query(value = "select * from service where active = 1 ", nativeQuery = true)
    List<Services> findAllActive();

    @Query(value = "select * from service where active = 1 order by service_name ", nativeQuery = true)
    Page<Services> findAllWithNameSort(Pageable pageable);

    @Query(value = "select * from service where active = 1 and description_other_convenience like concat('%',:convenience,'%') and service_cost >= :cost and service_type_id = :serviceTypeIdVal ", nativeQuery = true)
    Page<Services> findAllWithServiceTypeAndConvenienceAndCost(@Param("serviceTypeIdVal") Integer serviceTypeIdVal, @Param("convenience") String convenience, @Param("cost") String cost, Pageable pageable);

    @Query(value = "select * from service where active = 1 ", nativeQuery = true)
    Page<Services> findAllActivePaging(Pageable pageable);

    @Query(value = "select * from service where active = 1 and description_other_convenience like concat('%',:convenience,'%') and service_type_id = :serviceTypeIdVal ", nativeQuery = true)
    Page<Services> findAllWithServiceTypeAndConvenience(@Param("serviceTypeIdVal") Integer serviceTypeIdVal,@Param("convenience") String convenience, Pageable pageable);

    @Query(value = "select * from service where active = 1 and description_other_convenience like concat('%',:convenience,'%') and service_cost >= :cost ", nativeQuery = true)
    Page<Services> findAllWithConvenienceAndCost(@Param("convenience") String convenience,@Param("cost") String cost, Pageable pageable);

    @Query(value = "select * from service where active = 1 and description_other_convenience like concat('%',:convenience,'%') ", nativeQuery = true)
    Page<Services> findAllWithConvenience(@Param("convenience") String convenience, Pageable pageable);

    @Query(value = "select * from service where active = 1 and service_code = :serviceCode ", nativeQuery = true)
    Services findByCodeActice(@Param("serviceCode") String serviceCode);
}
