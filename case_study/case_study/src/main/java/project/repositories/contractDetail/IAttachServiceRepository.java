package project.repositories.contractDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.models.contractDetail.AttachService;

import java.util.List;

public interface IAttachServiceRepository extends JpaRepository<AttachService, Integer> {

    @Query(value = "SELECT * FROM attach_service WHERE active=1 ", nativeQuery = true)
    List<AttachService> findAllActive();

    @Query(value = "SELECT * FROM attach_service WHERE active=1 AND attach_service_id =:id ", nativeQuery = true)
    AttachService findAttachServiceViewById(@Param("id") int id);
}
