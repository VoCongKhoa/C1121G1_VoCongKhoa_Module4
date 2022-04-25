package project.repositories.services.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.user.AppRole;
import project.models.user.RoleName;

import java.util.Optional;

@Repository
public interface IAppRoleRepository extends JpaRepository<AppRole, Integer> {
    Optional<AppRole> findByName(RoleName name);
}
