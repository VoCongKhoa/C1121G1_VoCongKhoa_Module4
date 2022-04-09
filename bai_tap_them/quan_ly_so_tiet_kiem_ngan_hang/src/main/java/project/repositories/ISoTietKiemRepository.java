package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.models.SoTietKiem;

import java.util.List;

@Repository
public interface ISoTietKiemRepository extends JpaRepository<SoTietKiem, Integer> {

    @Query(value = "select so_tiet_kiem.* from so_tiet_kiem inner join khach_hang " +
            "on so_tiet_kiem.ma_khach_hang = khach_hang.ma_khach_hang " +
            "where khach_hang.ten_khach_hang like concat('%',:nameSearch,'%')", nativeQuery = true)
    List<SoTietKiem> findAllByTenKhachHang(@Param("nameSearch") String nameSearch);

    @Query(value = "select so_tiet_kiem.* from so_tiet_kiem \n" +
            "inner join khach_hang \n" +
            "on so_tiet_kiem.ma_khach_hang = khach_hang.ma_khach_hang \n" +
            "where (khach_hang.ten_khach_hang like concat('%',:tenKhachHangThuc,'%')) and (thoi_gian_bat_dau_gui BETWEEN :ngayBatDauThuc AND :ngayKetThucThuc)", nativeQuery = true)
    List<SoTietKiem> findAllByTenAnd2Ngay(@Param("tenKhachHangThuc") String tenKhachHangThuc, @Param("ngayBatDauThuc") String ngayBatDauThuc, @Param("ngayKetThucThuc") String ngayKetThucThuc);

    @Query(value = "select so_tiet_kiem.* from so_tiet_kiem \n" +
            "inner join khach_hang \n" +
            "on so_tiet_kiem.ma_khach_hang = khach_hang.ma_khach_hang \n" +
            "where (khach_hang.ten_khach_hang like concat('%',:tenKhachHangThuc,'%')) and (thoi_gian_bat_dau_gui > :ngayBatDauThuc )", nativeQuery = true)
    List<SoTietKiem> findAllByTenKhachHangAndNgayBatDau(@Param("tenKhachHangThuc") String tenKhachHangThuc, @Param("ngayBatDauThuc") String ngayBatDauThuc);

    @Query(value = "select so_tiet_kiem.* from so_tiet_kiem \n" +
            "inner join khach_hang \n" +
            "on so_tiet_kiem.ma_khach_hang = khach_hang.ma_khach_hang \n" +
            "where (khach_hang.ten_khach_hang like concat('%',:tenKhachHangThuc,'%')) and (thoi_gian_bat_dau_gui < :ngayKetThucThuc )", nativeQuery = true)
    List<SoTietKiem> findAllByTenKhachHangAndNgayKetThuc(@Param("tenKhachHangThuc") String tenKhachHangThuc, @Param("ngayKetThucThuc") String ngayKetThucThuc);

    @Query(value = "select so_tiet_kiem.* from so_tiet_kiem \n" +
            "inner join khach_hang \n" +
            "on so_tiet_kiem.ma_khach_hang = khach_hang.ma_khach_hang \n" +
            "where (thoi_gian_bat_dau_gui BETWEEN :ngayBatDauThuc AND :ngayKetThucThuc)", nativeQuery = true)
    List<SoTietKiem> findAllBy2Ngay(@Param("ngayBatDauThuc") String ngayBatDauThuc, @Param("ngayKetThucThuc") String ngayKetThucThuc);

    @Query(value = "select so_tiet_kiem.* from so_tiet_kiem \n" +
            "inner join khach_hang \n" +
            "on so_tiet_kiem.ma_khach_hang = khach_hang.ma_khach_hang \n" +
            "where (thoi_gian_bat_dau_gui > :ngayBatDauThuc)", nativeQuery = true)
    List<SoTietKiem> findAllByNgayBatDau(@Param("ngayBatDauThuc") String ngayBatDauThuc);

    @Query(value = "select so_tiet_kiem.* from so_tiet_kiem \n" +
            "inner join khach_hang \n" +
            "on so_tiet_kiem.ma_khach_hang = khach_hang.ma_khach_hang \n" +
            "where (thoi_gian_bat_dau_gui < :ngayKetThucThuc)", nativeQuery = true)
    List<SoTietKiem> findAllByNgayKetThuc(@Param("ngayKetThucThuc") String ngayKetThucThuc);
}
