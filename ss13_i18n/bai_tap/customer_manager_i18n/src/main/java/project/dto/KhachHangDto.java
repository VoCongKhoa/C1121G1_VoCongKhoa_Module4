package project.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class KhachHangDto{

    private Integer maKhachHang;

    @NotBlank(message = "Tên khách hàng không được để trống!")
    @Pattern(regexp = "^[^\\d]+$", message = "Tên khách hàng không chứa ký tự số")
    private String tenKhachHang;

    public KhachHangDto() {
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

}
