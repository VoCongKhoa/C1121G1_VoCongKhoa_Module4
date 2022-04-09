package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.models.Sach;
import project.repositories.IHocSinhRepository;
import project.repositories.ISachRepository;

import java.util.List;

@Service
public class SachService implements ISachService {

    @Autowired
    ISachRepository iSachRepository;

    @Override
    public Page<Sach> findAllPaging(Pageable pageable) {
        return iSachRepository.findAll(pageable);
    }

    @Override
    public List<Sach> findAll() {
        return iSachRepository.findAll();
    }

    @Override
    public List<Sach> findAllSachCoTheMuon() {
        return iSachRepository.findAllSachCoTheMuon();
    }

    @Override
    public void updateSoLuongSach(String maSach) {
        iSachRepository.updateSoLuongSach(maSach);
    }

    @Override
    public Sach findById(String maSach) {
        return iSachRepository.findById(maSach).orElse(null);
    }

    @Override
    public void save(Sach sach) {
        iSachRepository.save(sach);
    }

    @Override
    public void deleteById(String maSach) {
        iSachRepository.deleteById(maSach);
    }

    @Override
    public Page<Sach> findAllByTenSachAndTacGiaAndSoLuong(String tenSachThuc, String tacGiaThuc, String soLuongThuc, Pageable pageable) {

        if (tenSachThuc.equals("")&tacGiaThuc.equals("")&soLuongThuc.equals("")){
            return findAllPaging(pageable);
        }
        if (!(tenSachThuc.equals("")&tacGiaThuc.equals("")&soLuongThuc.equals(""))){
            return iSachRepository.findAllByTenSachAndTacGiaAndSoLuong(tenSachThuc, tacGiaThuc, soLuongThuc, pageable);
        }

        if (!(tenSachThuc.equals("")&tacGiaThuc.equals(""))){
            return iSachRepository.findAllByTenSachAndTacGia(tenSachThuc,tacGiaThuc, pageable);
        }

        if (!(tenSachThuc.equals("")&soLuongThuc.equals(""))){
            return iSachRepository.findAllByTenSachAndSoLuong(tenSachThuc,soLuongThuc, pageable);
        }

        if (!(tacGiaThuc.equals("")&soLuongThuc.equals(""))){
            return iSachRepository.findAllByTacGiaAndSoLuong(tacGiaThuc,soLuongThuc, pageable);
        }

        if (!(tenSachThuc.equals(""))){
            return iSachRepository.findAllByTenSach(tenSachThuc, pageable);
        }

        if (!(tacGiaThuc.equals(""))){
            return iSachRepository.findAllByTacGia(tacGiaThuc, pageable);
        }

        if (!(soLuongThuc.equals(""))){
            return iSachRepository.findAllBySoLuong(soLuongThuc, pageable);
        }


            return null;
    }
}

