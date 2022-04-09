package project.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class KhachHangDto implements Validator {

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

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
