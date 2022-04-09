package project.models;

import javax.persistence.*;
import javax.swing.*;

@Entity
public class SoTietKiem {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_so_tiet_kiem")
    private Integer maSoTietKiem;

    @Column(name = "thoi_gian_bat_dau_gui", columnDefinition = "DATE")
    private String thoiGianBatDauGui;

    @Column(name = "ky_han")
    private Integer kyHan;

    @Column(name = "so_tien_gui")
    private Double soTienGui;

    @ManyToOne
    @JoinColumn(name= "ma_khach_hang", referencedColumnName = "ma_khach_hang")
    private KhachHang khachHang;

    public SoTietKiem() {
    }

    public Integer getMaSoTietKiem() {
        return maSoTietKiem;
    }

    public void setMaSoTietKiem(Integer maSoTietKiem) {
        this.maSoTietKiem = maSoTietKiem;
    }

    public String getThoiGianBatDauGui() {
        return thoiGianBatDauGui;
    }

    public void setThoiGianBatDauGui(String thoiGianBatDauGui) {
        this.thoiGianBatDauGui = thoiGianBatDauGui;
    }

    public Integer getKyHan() {
        return kyHan;
    }

    public void setKyHan(Integer kyHan) {
        this.kyHan = kyHan;
    }

    public Double getSoTienGui() {
        return soTienGui;
    }

    public void setSoTienGui(Double soTienGui) {
        this.soTienGui = soTienGui;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }
}
