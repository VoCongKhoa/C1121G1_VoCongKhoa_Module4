package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.models.Author;

public interface IAuthorRepository extends JpaRepository<Author,Integer> {
}
