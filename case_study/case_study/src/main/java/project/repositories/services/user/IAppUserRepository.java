package project.repositories.services.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.user.AppUser;

import java.util.Optional;
@Repository
public interface IAppUserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByUsername(String username);
    Boolean existsByUsername(String username);
}
