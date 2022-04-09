package project.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class HocSinh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_hoc_sinh")
    private Integer maHocSinh;
    private String hoTen;
    private String lop;

    @OneToMany(mappedBy = "hocSinh")
    private Set<TheMuonSach> theMuonSachSet;

    public HocSinh() {
    }

    public Integer getMaHocSinh() {
        return maHocSinh;
    }

    public void setMaHocSinh(Integer maHocSinh) {
        this.maHocSinh = maHocSinh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public Set<TheMuonSach> getTheMuonSachSet() {
        return theMuonSachSet;
    }

    public void setTheMuonSachSet(Set<TheMuonSach> theMuonSachSet) {
        this.theMuonSachSet = theMuonSachSet;
    }
}
