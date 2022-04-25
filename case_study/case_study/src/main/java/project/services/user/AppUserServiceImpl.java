package project.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.user.AppUser;
import project.repositories.services.user.IAppUserRepository;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements IAppUserService {
    @Autowired
    IAppUserRepository iAppUserRepository;
    @Override
    public Optional<AppUser> findByUsername(String username) {
        return iAppUserRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return iAppUserRepository.existsByUsername(username);
    }

    @Override
    public AppUser save(AppUser appUser) {
        return iAppUserRepository.save(appUser);
    }
}
