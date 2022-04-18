package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.models.AppUser;
import project.models.UserRole;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    List<UserRole> findByAppUser(AppUser appUser);
}
