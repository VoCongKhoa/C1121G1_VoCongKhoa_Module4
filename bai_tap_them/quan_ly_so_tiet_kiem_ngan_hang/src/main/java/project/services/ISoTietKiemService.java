package project.services;

import project.models.SoTietKiem;

import java.util.List;

public interface ISoTietKiemService {
    List<SoTietKiem> findAll();

    void save(SoTietKiem soTietKiem);

    SoTietKiem findById(int id);

    void deleteById(Integer maSoTietKiem);


    List<SoTietKiem> findAllByTenKhachHangAndNgayGui(String tenKhachHangThuc, String ngayBatDauThuc, String ngayKetThucThuc);
}
