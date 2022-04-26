package project.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.user.UserRole;
import project.repositories.user.UserRoleRepository;

@Service
public class UserRoleService implements IUserRoleService{

    @Autowired
    UserRoleRepository userRoleRepository;


    @Override
    public void save(UserRole userRole) {
        userRoleRepository.save(userRole);
    }
}
