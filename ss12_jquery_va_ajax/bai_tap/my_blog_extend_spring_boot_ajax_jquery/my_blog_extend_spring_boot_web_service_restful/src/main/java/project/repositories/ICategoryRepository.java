package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.models.Category;

public interface ICategoryRepository extends JpaRepository<Category,Integer> {
}
