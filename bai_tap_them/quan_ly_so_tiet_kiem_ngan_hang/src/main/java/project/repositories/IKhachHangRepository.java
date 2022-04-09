package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.models.KhachHang;

@Repository
public interface IKhachHangRepository extends JpaRepository<KhachHang,Integer> {
}
