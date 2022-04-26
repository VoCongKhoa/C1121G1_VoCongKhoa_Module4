package project.services.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.models.user.AppRole;

public interface IAppRoleService {

    @Query(value = "select * from app_role where role_id = :id ", nativeQuery = true)
    AppRole findByRoleId(@Param("id") int id);
}
