package project.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.models.KhachHang;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SoTietKiemDto implements Validator {

    private Integer maSoTietKiem;

    private String thoiGianBatDauGui;

    //    @NotBlank(message = "Kỳ hạn không được để trống!")
//    @NotNull(message= "Kỳ hạn không được để trống!")
//    @Range(min = 1)
//    @NotBlank(message = "Số tiền gửi không được để trống!")
//    @Pattern(regexp = "^\\d+$", message = "Wrong format!")
    private String kyHan;

    //    @NotNull(message= "Số tiền gởi không được để trống!")
//    @Range(min = 30000001)
//    @NotBlank(message = "Số tiền gởi không được để trống!")
//    @NotBlank(message = "Số tiền gửi không được để trống!")
//    @Pattern(regexp = "^\\d+$", message = "Wrong format!")
    private String soTienGui;

    @Valid
    private KhachHang khachHang;

    public SoTietKiemDto() {
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

    public String getKyHan() {
        return kyHan;
    }

    public void setKyHan(String kyHan) {
        this.kyHan = kyHan;
    }

    public String getSoTienGui() {
        return soTienGui;
    }

    public void setSoTienGui(String soTienGui) {
        this.soTienGui = soTienGui;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        SoTietKiemDto soTietKiemDto = (SoTietKiemDto) target;
        if (soTietKiemDto.getThoiGianBatDauGui().equals("")) {
            errors.rejectValue("thoiGianBatDauGui", "thoiGianBatDauGui.notBlank", "Phải chọn thời gian bắt đầu gởi!");
        } else {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate thoiGianBatDauGui = LocalDate.parse(soTietKiemDto.getThoiGianBatDauGui(), formatter);
            if (thoiGianBatDauGui.isAfter(currentDate)) {
                errors.rejectValue("thoiGianBatDauGui", "thoiGianBatDauGui.afterCurrentDate", "Thời gian gởi phải trước ngày hôm nay!");
            }
        }

        if (!soTietKiemDto.getKyHan().matches("^\\d+$")) {
            errors.rejectValue("kyHan", "", "Bạn phải nhập số!");
        } else {
            int soTienGuiInt = Integer.parseInt(soTietKiemDto.getKyHan());
            if (soTienGuiInt <= 0) {
                errors.rejectValue("kyHan", "", "Bạn phải nhập số lớn hơn 0!");
            }
        }

        if (!soTietKiemDto.getSoTienGui().matches("^\\d+$")) {
            errors.rejectValue("soTienGui", "soTienGui.format", "Bạn phải nhập số!");
        } else {
            int soTienGuiInt = Integer.parseInt(soTietKiemDto.getSoTienGui());
            if (soTienGuiInt <= 30000000) {
                errors.rejectValue("soTienGui", "soTienGui.morethan30mil", "Bạn phải nhập số lớn hơn 30000000!");
            }
        }

    }
}
