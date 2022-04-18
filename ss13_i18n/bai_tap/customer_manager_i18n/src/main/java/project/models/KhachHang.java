package project.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "khach_hang")
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_khach_hang")
    private Integer maKhachHang;

    @Column(name = "ten_khach_hang")
    private String tenKhachHang;

    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SoTietKiem> soTietKiemList;

    public KhachHang() {
    }

    public Integer getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(Integer maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public List<SoTietKiem> getSoTietKiemList() {
        return soTietKiemList;
    }

    public void setSoTietKiemList(List<SoTietKiem> soTietKiemList) {
        this.soTietKiemList = soTietKiemList;
    }
}
