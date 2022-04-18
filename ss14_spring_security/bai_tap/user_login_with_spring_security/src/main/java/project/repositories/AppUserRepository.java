package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.models.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUserName(String userName);
}
