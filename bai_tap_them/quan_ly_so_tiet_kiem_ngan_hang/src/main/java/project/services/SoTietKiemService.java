package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.SoTietKiem;
import project.repositories.ISoTietKiemRepository;

import java.util.List;

@Service
public class SoTietKiemService implements ISoTietKiemService {

    @Autowired
    ISoTietKiemRepository iSoTietKiemRepository;

    @Override
    public List<SoTietKiem> findAll() {
        return iSoTietKiemRepository.findAll();
    }

    @Override
    public void save(SoTietKiem soTietKiem) {
        iSoTietKiemRepository.save(soTietKiem);
    }

    @Override
    public SoTietKiem findById(int id) {
        return iSoTietKiemRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer maSoTietKiem) {
        iSoTietKiemRepository.deleteById(maSoTietKiem);
    }

    @Override
    public List<SoTietKiem> findAllByTenKhachHangAndNgayGui(String tenKhachHangThuc, String ngayBatDauThuc, String ngayKetThucThuc) {

        if (tenKhachHangThuc.equals("") & ngayBatDauThuc.equals("") & ngayKetThucThuc.equals("")) {
            return findAll();
        }

        if (!tenKhachHangThuc.equals("") & !ngayBatDauThuc.equals("") & !ngayKetThucThuc.equals("")) {
            return iSoTietKiemRepository.findAllByTenAnd2Ngay(tenKhachHangThuc, ngayBatDauThuc, ngayKetThucThuc);
        }

        if (tenKhachHangThuc.equals("") & ngayBatDauThuc.equals("")) {
            return iSoTietKiemRepository.findAllByNgayKetThuc(ngayKetThucThuc);
        }

        if (tenKhachHangThuc.equals("") & ngayKetThucThuc.equals("")) {
            return iSoTietKiemRepository.findAllByNgayBatDau(ngayBatDauThuc);
        }

        if (ngayBatDauThuc.equals("") & ngayKetThucThuc.equals("")) {
            return iSoTietKiemRepository.findAllByTenKhachHang(tenKhachHangThuc);
        }

        if (tenKhachHangThuc.equals("") & !ngayBatDauThuc.equals("") & !ngayKetThucThuc.equals("")) {
            return iSoTietKiemRepository.findAllBy2Ngay(ngayBatDauThuc, ngayKetThucThuc);
        }
        if (!tenKhachHangThuc.equals("") & ngayBatDauThuc.equals("") & !ngayKetThucThuc.equals("")) {
            return iSoTietKiemRepository.findAllByTenKhachHangAndNgayKetThuc(tenKhachHangThuc, ngayKetThucThuc);
        }
        if (!tenKhachHangThuc.equals("") & !ngayBatDauThuc.equals("") & ngayKetThucThuc.equals("")) {
            return iSoTietKiemRepository.findAllByTenKhachHangAndNgayBatDau(tenKhachHangThuc, ngayBatDauThuc);
        }
        return null;
    }
}

