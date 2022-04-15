package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.Author;

@Repository
public interface IAuthorRepository extends JpaRepository<Author,Integer> {
}
