package project.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class TheMuonSach {
    @Id
    private String maMuonSach;
    private String trangThai;
    private String ngayMuon;
    private String ngayTra;

    @ManyToOne
    @JoinColumn(name = "ma_hoc_sinh", referencedColumnName = "ma_hoc_sinh")
    private HocSinh hocSinh;

    @ManyToOne
    @JoinColumn(name = "ma_sach", referencedColumnName = "ma_sach")
    private Sach sach;

    public TheMuonSach() {
    }

    public String getMaMuonSach() {
        return maMuonSach;
    }

    public void setMaMuonSach(String maMuonSach) {
        this.maMuonSach = maMuonSach;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public HocSinh getHocSinh() {
        return hocSinh;
    }

    public void setHocSinh(HocSinh hocSinh) {
        this.hocSinh = hocSinh;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TheMuonSach that = (TheMuonSach) o;
        return Objects.equals(maMuonSach, that.maMuonSach) && Objects.equals(trangThai, that.trangThai) && Objects.equals(ngayMuon, that.ngayMuon) && Objects.equals(ngayTra, that.ngayTra) && Objects.equals(hocSinh, that.hocSinh) && Objects.equals(sach, that.sach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maMuonSach, trangThai, ngayMuon, ngayTra, hocSinh, sach);
    }
}
