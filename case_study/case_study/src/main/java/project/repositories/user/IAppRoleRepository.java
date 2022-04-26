package project.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import project.models.user.AppRole;

public interface IAppRoleRepository extends JpaRepository<AppRole, Integer> {
    AppRole findByRoleId(int id);
}
