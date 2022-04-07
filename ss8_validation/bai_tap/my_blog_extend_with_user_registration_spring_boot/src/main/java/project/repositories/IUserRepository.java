package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.User;

@Repository
public interface IUserRepository extends JpaRepository<User,Integer> {
}
