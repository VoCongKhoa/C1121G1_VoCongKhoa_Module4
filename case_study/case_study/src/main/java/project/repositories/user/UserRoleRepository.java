package project.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import project.models.user.AppUser;
import project.models.user.UserRole;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    List<UserRole> findByAppUser(AppUser appUser);
}
