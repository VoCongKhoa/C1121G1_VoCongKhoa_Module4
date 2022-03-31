package project.repositories;

import org.springframework.stereotype.Repository;
import project.models.DonDangKy;

import java.util.*;

@Repository
public class DonDangKyRepository implements IDonDangKyRepository {
    private static Map<Integer, DonDangKy> donDangKyMap = new TreeMap<>();
    private static List<String> quocTichList = new ArrayList<>();
    static int autoInrement = 0;

    static {
        DonDangKy donDangKy1 = new DonDangKy(autoInrement++, "Nguyen Van A", "2000", "Nam",
                "Viet Nam", "123456789", "O to", "ABC-123", "A-1",
                "2022-01-05", "2022-01-07", "Hue", "Da Nang",
                "Cam Le", "Hoa An", "Ton Dan", "090572592", "nguyenvana@gmail.com",
                true, false, true, false, false, false, false,
                false, false, true);
        DonDangKy donDangKy2 = new DonDangKy(autoInrement++, "Le Thi A", "1999", "Nu",
                "Viet Nam", "123456222", "Tau bay", "FFF-123", "G-1", "2022-01-01", "2022-01-05",
                "Da Nang", "Quang Nam", "Dai Loc", "Ai Nghia", "Nga Tu",
                "09057222", "lethiB@gmail.com", true, false, true, false, false,
                false, true, false, true, false);
        DonDangKy donDangKy3 = new DonDangKy(autoInrement++, "Tran van A", "1998", "Nam",
                "Viet Nam", "123456333", "Khac", "", "", "2022-03-03",
                "2022-03-05", "Nha Trang", "Da Nang", "Cam Le", "Hoa Phat",
                "Le Trong Tan", "090572333", "tranVanB@gmail.com", false, false, false,
                false, false, false, true, false, true, false);

        donDangKyMap.put(donDangKy1.getMaDon(), donDangKy1);
        donDangKyMap.put(donDangKy2.getMaDon(), donDangKy2);
        donDangKyMap.put(donDangKy3.getMaDon(), donDangKy3);
        quocTichList = Arrays.asList("Viet Nam", "USA", "England", "Australia", "China", "Korea");

    }

    @Override
    public List<DonDangKy> findAll() {
        return new ArrayList<>(donDangKyMap.values());
    }

    @Override
    public DonDangKy findOne(int maDon) {
        return donDangKyMap.get(maDon);
    }

    @Override
    public void save(DonDangKy donDangKy) {
        donDangKy.setMaDon(donDangKyMap.size() + 1);
        donDangKyMap.put(donDangKy.getMaDon(), donDangKy);
    }

    @Override
    public void update(DonDangKy donDangKy) {
        donDangKyMap.put(donDangKy.getMaDon(), donDangKy);
    }

    @Override
    public List<String> getQuocTichList() {
        return quocTichList;
    }

    @Override
    public List<DonDangKy> getDonDangKyList() {
        return new ArrayList<>(donDangKyMap.values());
    }

    @Override
    public List<Integer> getNamSinhList() {
        List<Integer> namSinhList = new ArrayList<>();
        for (int i = 1900; i <= 2100; i++) {
            namSinhList.add(i);
        }
        return namSinhList;
    }

}
