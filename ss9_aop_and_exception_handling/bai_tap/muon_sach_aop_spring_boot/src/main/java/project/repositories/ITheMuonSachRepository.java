package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.TheMuonSach;
@Repository
public interface ITheMuonSachRepository extends JpaRepository<TheMuonSach,String> {
}
