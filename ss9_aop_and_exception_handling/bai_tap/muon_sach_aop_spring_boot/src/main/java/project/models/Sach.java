package project.models;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
public class Sach {

    @Id
    @Column(name = "ma_sach")
    private String maSach;
    private String tenSach;
    private String tacGia;
    private String moTa;
    private Integer soLuong;

    @OneToMany(mappedBy = "sach", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TheMuonSach> theMuonSachSet;

    public Sach() {
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Set<TheMuonSach> getTheMuonSachSet() {
        return theMuonSachSet;
    }

    public void setTheMuonSachSet(Set<TheMuonSach> theMuonSachSet) {
        this.theMuonSachSet = theMuonSachSet;
    }
}
