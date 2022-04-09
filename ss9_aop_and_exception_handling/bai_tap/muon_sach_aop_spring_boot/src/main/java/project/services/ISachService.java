package project.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.models.Sach;

import java.util.List;

public interface ISachService {
    Page<Sach> findAllPaging(Pageable pageable);

    List<Sach> findAll();

    List<Sach> findAllSachCoTheMuon();

    void updateSoLuongSach(String maSach);

    Sach findById(String maSach);

    void save(Sach sach);

    void deleteById(String maSach);

    Page<Sach> findAllByTenSachAndTacGiaAndSoLuong(String tenSachThuc, String tacGiaThuc, String soLuongThuc, Pageable pageable);
//    Page<SoTietKiem> findAll(Pageable pageable);
//    void save(SoTietKiem soTietKiem);
//    SoTietKiem findById(int id);
//    void deleteById(Integer maSoTietKiem);
//    Page<SoTietKiem> findAllByTenKhachHangAndNgayGui(String tenKhachHangThuc, String ngayBatDauThuc, String ngayKetThucThuc,Pageable pageable);
}
