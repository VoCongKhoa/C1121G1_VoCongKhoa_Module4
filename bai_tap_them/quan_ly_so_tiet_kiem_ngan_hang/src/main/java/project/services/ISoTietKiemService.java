package project.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.models.SoTietKiem;

import java.util.List;

public interface ISoTietKiemService {
    Page<SoTietKiem> findAll(Pageable pageable);

    void save(SoTietKiem soTietKiem);

    SoTietKiem findById(int id);

    void deleteById(Integer maSoTietKiem);


    Page<SoTietKiem> findAllByTenKhachHangAndNgayGui(String tenKhachHangThuc, String ngayBatDauThuc, String ngayKetThucThuc,Pageable pageable);
}
