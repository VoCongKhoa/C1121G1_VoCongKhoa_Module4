package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.DonDangKy;
import project.repositories.IDonDangKyRepository;

import java.util.List;

@Service
public class DonDangKyService implements IDonDangKyService {
    @Autowired
    IDonDangKyRepository iDonDangKyRepository;

    public List<DonDangKy> findAll(){
        return iDonDangKyRepository.findAll();
    }

    @Override
    public DonDangKy findOne(int maDon) {
        return iDonDangKyRepository.findOne(maDon);
    }

    @Override
    public void save(DonDangKy donDangKy) {
        iDonDangKyRepository.save(donDangKy);
    }

    @Override
    public void update(DonDangKy donDangKy) {
        iDonDangKyRepository.update(donDangKy);
    }

    @Override
    public List<String> getQuocTichList() {
        return iDonDangKyRepository.getQuocTichList();
    }

    @Override
    public List<DonDangKy> getDonDangKyList() {
        return iDonDangKyRepository.getDonDangKyList();
    }

    @Override
    public List<Integer> getNamSinhList() {
        return iDonDangKyRepository.getNamSinhList();
    }
//
//    @Override
//    public List<Integer> getPageSizeList() {
//        return iDonDangKyRepository.getPageSizeList();
//    }
}
