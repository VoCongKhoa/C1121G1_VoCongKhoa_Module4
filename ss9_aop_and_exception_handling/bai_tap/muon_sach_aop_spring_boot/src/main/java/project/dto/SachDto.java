package project.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SachDto implements Validator {

    @NotBlank
    @Pattern(regexp = "^$|^S-[\\d]{3}$", message = "Phải nhập đúng định dạng S-0000")
    private String maSach;
    @NotBlank
    private String tenSach;
    @NotBlank
    private String tacGia;
    private String moTa;
    @NotBlank
    private String soLuong;

    public SachDto() {
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

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!this.getSoLuong().equals("")){
            int soLuongInt = Integer.parseInt(this.getSoLuong());
            if (soLuongInt<0){
                errors.rejectValue("soLuong","soLuong.positive","Số lượng phải lớn hơn hoặc bằng 0!");
            }
        }
    }
}
