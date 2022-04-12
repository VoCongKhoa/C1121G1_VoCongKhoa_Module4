package project.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.models.HocSinh;
import project.models.Sach;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TheMuonSachDto implements Validator {

    @NotBlank
    @Pattern(regexp = "^$|^MS-[\\d]{4}$", message = "Phải nhập đúng định dạng MS-0000")
    private String maMuonSach;
    private String trangThai;
    private String ngayMuon;
    private String ngayTra;
    @Valid
    private HocSinhDto hocSinhDto;
    private SachDto sachDto;

    public TheMuonSachDto() {
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

    public HocSinhDto getHocSinhDto() {
        return hocSinhDto;
    }

    public void setHocSinhDto(HocSinhDto hocSinhDto) {
        this.hocSinhDto = hocSinhDto;
    }

    public SachDto getSachDto() {
        return sachDto;
    }

    public void setSachDto(SachDto sachDto) {
        this.sachDto = sachDto;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(this.getNgayMuon().equals("")){
            errors.rejectValue("ngayMuon","ngayMuon.validate","Phải chọn ngày mượn!");
        }
        if(this.getNgayTra().equals("")){
            errors.rejectValue("ngayTra","ngayTra.validate","Phải chọn ngày trả!");
        }
        if (!(this.getNgayMuon().equals("")&this.getNgayTra().equals(""))){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate ngayMuon = LocalDate.parse(this.getNgayMuon(),formatter);
            LocalDate ngayTra = LocalDate.parse(this.getNgayTra(),formatter);
            if (ngayMuon.isAfter(ngayTra)){
                errors.rejectValue("ngayMuon","ngayMuon.validateAfter","Phải chọn ngày mượn trước ngày trả!");
                errors.rejectValue("ngayTra","ngayTra.validateBefore","Phải chọn ngày trả sau ngày mượn!");
            }
        }
    }
}
