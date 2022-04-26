package project.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.user.AppRole;
import project.repositories.user.IAppRoleRepository;

@Service
public class AppRoleService implements IAppRoleService{

    @Autowired
    IAppRoleRepository iAppRoleRepository;

    @Override
    public AppRole findByRoleId(int id) {
        return iAppRoleRepository.findByRoleId(id);
    }
}
