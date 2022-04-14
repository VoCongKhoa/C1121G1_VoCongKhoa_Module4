package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.models.Smartphone;

public interface ISmartphoneRepository extends JpaRepository<Smartphone,Long> {
}
