package project.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import project.models.user.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String userName);
}
