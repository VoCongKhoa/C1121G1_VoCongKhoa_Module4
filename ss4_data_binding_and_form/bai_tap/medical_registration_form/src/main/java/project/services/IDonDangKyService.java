package project.services;

import project.models.DonDangKy;

import java.util.List;

public interface IDonDangKyService {
    List<DonDangKy> findAll();

    DonDangKy findOne(int maDon);

    void save(DonDangKy donDangKy);

    void update(DonDangKy donDangKy);

    List<String> getQuocTichList();

    List<DonDangKy> getDonDangKyList();

    List<Integer> getNamSinhList();

//    List<Integer> getPageSizeList();
}
