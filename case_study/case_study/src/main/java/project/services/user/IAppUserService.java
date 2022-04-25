package project.services.user;


import project.models.user.AppUser;

import java.util.Optional;

public interface IAppUserService {
    Optional<AppUser> findByUsername(String username);
    Boolean existsByUsername(String username);
    AppUser save(AppUser user);
}
