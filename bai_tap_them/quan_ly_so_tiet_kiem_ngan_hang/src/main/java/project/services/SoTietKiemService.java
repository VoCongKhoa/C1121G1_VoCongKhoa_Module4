package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.models.SoTietKiem;
import project.repositories.ISoTietKiemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SoTietKiemService implements ISoTietKiemService {

    @Autowired
    ISoTietKiemRepository iSoTietKiemRepository;

    @Override
    public Page<SoTietKiem> findAll(Pageable pageable) {
        return iSoTietKiemRepository.findAll(pageable);
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
    public Page<SoTietKiem> findAllByTenKhachHangAndNgayGui(String tenKhachHangThuc, String ngayBatDauThuc, String ngayKetThucThuc, Pageable pageable) {

        if (tenKhachHangThuc.equals("") & ngayBatDauThuc.equals("") & ngayKetThucThuc.equals("")) {
            return findAll(pageable);
        }

        if (!tenKhachHangThuc.equals("") & !ngayBatDauThuc.equals("") & !ngayKetThucThuc.equals("")) {
            return iSoTietKiemRepository.findAllByTenAnd2Ngay(tenKhachHangThuc, ngayBatDauThuc, ngayKetThucThuc, pageable);
        }

        if (tenKhachHangThuc.equals("") & ngayBatDauThuc.equals("")) {
            return iSoTietKiemRepository.findAllByNgayKetThuc(ngayKetThucThuc, pageable);
        }

        if (tenKhachHangThuc.equals("") & ngayKetThucThuc.equals("")) {
            return iSoTietKiemRepository.findAllByNgayBatDau(ngayBatDauThuc, pageable);
        }

        if (ngayBatDauThuc.equals("") & ngayKetThucThuc.equals("")) {
            return iSoTietKiemRepository.findAllByTenKhachHang(tenKhachHangThuc, pageable);
        }

        if (tenKhachHangThuc.equals("") & !ngayBatDauThuc.equals("") & !ngayKetThucThuc.equals("")) {
            return iSoTietKiemRepository.findAllBy2Ngay(ngayBatDauThuc, ngayKetThucThuc, pageable);
        }
        if (!tenKhachHangThuc.equals("") & ngayBatDauThuc.equals("") & !ngayKetThucThuc.equals("")) {
            return iSoTietKiemRepository.findAllByTenKhachHangAndNgayKetThuc(tenKhachHangThuc, ngayKetThucThuc, pageable);
        }
        if (!tenKhachHangThuc.equals("") & !ngayBatDauThuc.equals("") & ngayKetThucThuc.equals("")) {
            return iSoTietKiemRepository.findAllByTenKhachHangAndNgayBatDau(tenKhachHangThuc, ngayBatDauThuc, pageable);
        }
        return null;
    }
}

