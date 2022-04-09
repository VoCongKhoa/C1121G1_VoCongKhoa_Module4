package project.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.models.Sach;

import java.util.List;

@Repository
public interface ISachRepository extends JpaRepository<Sach,String> {

    @Query(value = "select * from sach where so_luong>0",nativeQuery = true)
    List<Sach> findAllSachCoTheMuon();

    @Transactional
    @Modifying
    @Query(value = "update sach set so_luong = so_luong - 1 where ma_sach = :maSach", nativeQuery = true)
    void updateSoLuongSach(@Param("maSach") String maSach);

    @Query(value = "select * from sach where ten_sach like concat('%',:tenSachThuc,'%') and tac_gia like concat('%',:tacGiaThuc,'%') " +
            "and so_luong >= :soLuongThuc", nativeQuery = true)
    Page<Sach> findAllByTenSachAndTacGiaAndSoLuong(@Param(value = "tenSachThuc") String tenSachThuc,@Param(value = "tacGiaThuc") String tacGiaThuc,
                                                   @Param(value = "soLuongThuc") String soLuongThuc, Pageable pageable);

    @Query(value = "select * from sach where ten_sach like concat('%',:tenSachThuc,'%') and tac_gia like concat('%',:tacGiaThuc,'%') ", nativeQuery = true)
    Page<Sach> findAllByTenSachAndTacGia(@Param(value = "tenSachThuc") String tenSachThuc,@Param(value = "tacGiaThuc") String tacGiaThuc, Pageable pageable);

    @Query(value = "select * from sach where ten_sach like concat('%',:tenSachThuc,'%') and so_luong >= :soLuongThuc", nativeQuery = true)
    Page<Sach> findAllByTenSachAndSoLuong(String tenSachThuc, String soLuongThuc, Pageable pageable);

    @Query(value = "select * from sach where tac_gia like concat('%',:tacGiaThuc,'%') and so_luong >= :soLuongThuc", nativeQuery = true)
    Page<Sach> findAllByTacGiaAndSoLuong(String tacGiaThuc, String soLuongThuc, Pageable pageable);

    @Query(value = "select * from sach where ten_sach like concat('%',:tenSachThuc,'%') ", nativeQuery = true)
    Page<Sach> findAllByTenSach(String tenSachThuc, Pageable pageable);

    @Query(value = "select * from sach where tac_gia like concat('%',:tacGiaThuc,'%')", nativeQuery = true)
    Page<Sach> findAllByTacGia(String tacGiaThuc, Pageable pageable);

    @Query(value = "select * from sach where so_luong >= :soLuongThuc", nativeQuery = true)
    Page<Sach> findAllBySoLuong(String soLuongThuc, Pageable pageable);
}
