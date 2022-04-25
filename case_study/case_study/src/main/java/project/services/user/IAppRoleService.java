package project.services.user;

import project.models.user.AppRole;
import project.models.user.RoleName;

import java.util.Optional;

public interface IAppRoleService {
    Optional<AppRole> findByName(RoleName name);
}
