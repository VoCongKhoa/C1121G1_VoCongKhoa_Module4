package project.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.user.AppRole;
import project.models.user.RoleName;
import project.repositories.services.user.IAppRoleRepository;

import java.util.Optional;

@Service
public class AppRoleServiceImpl implements IAppRoleService {
    @Autowired
    IAppRoleRepository iAppRoleRepository;

    @Override
    public Optional<AppRole> findByName(RoleName name) {
        return iAppRoleRepository.findByName(name);
    }
}
