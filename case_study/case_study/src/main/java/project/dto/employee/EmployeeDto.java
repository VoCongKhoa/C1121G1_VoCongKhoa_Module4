package project.dto.employee;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.models.employee.Division;
import project.models.employee.EducationDegree;
import project.models.employee.Position;
import project.models.user.User;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class EmployeeDto implements Validator {
    private int employeeId;
    @NotBlank
    private String employeeName;
    @NotBlank
    private String employeeBirthday;
    @NotBlank
    @Pattern(regexp = "^$|^\\d{9}$", message = "Wrong format, ex: 123456789")
    private String employeeIdCard;
    @NotBlank
//    @DecimalMin(value = "0.0")
    private String employeeSalary;
    @NotBlank
    @Pattern(regexp = "^$|^(0|\\(84\\)\\+)9[0|1]\\d{7}$", message = "Wrong format, ex: 090xxxxxxx or 091xxxxxxx or (84)+90xxxxxxx or (84)+91xxxxxxx")
    private String employeePhone;
    @Pattern(regexp = "^$|^[\\w]+@\\w+(\\.\\w+)+$", message = "Wrong format, ex: abc@gmail.com")
    private String employeeEmail;
    private String employeeAddress;
    private Position position;
    private EducationDegree educationDegree;
    private Division division;
    private int active = 1;
    private User user;

    public EmployeeDto() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeBirthday() {
        return employeeBirthday;
    }

    public void setEmployeeBirthday(String employeeBirthday) {
        this.employeeBirthday = employeeBirthday;
    }

    public String getEmployeeIdCard() {
        return employeeIdCard;
    }

    public void setEmployeeIdCard(String employeeIdCard) {
        this.employeeIdCard = employeeIdCard;
    }

    public String getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(String employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public EducationDegree getEducationDegree() {
        return educationDegree;
    }

    public void setEducationDegree(EducationDegree educationDegree) {
        this.educationDegree = educationDegree;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeDto employeeDto = (EmployeeDto) target;

//        if ((employeeDto.getEmployeeSalary().isNaN())) {
//            errors.rejectValue("employeeSalary", "", "Wrong format (a number greater than 0)");
//        }
//        if (!soTietKiemDto.getKyHan().matches("^\\d+$")) {
//            errors.rejectValue("kyHan", "", "Bạn phải nhập số!");
//        } else {
//            int soTienGuiInt = Integer.parseInt(soTietKiemDto.getKyHan());
//            if (soTienGuiInt <= 0) {
//                errors.rejectValue("kyHan", "", "Bạn phải nhập số lớn hơn 0!");
//            }
//        }

//        if (employeeDto.getEmployeeSalary() != null) {
//            if (!(employeeDto.getEmployeeSalary().matches("^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,4})?\\s*$"))) {
//                errors.rejectValue("employeeSalary", "", "Wrong format (a number greater than 0)");
//            } else {
//                Double salary = Double.parseDouble(employeeDto.getEmployeeSalary());
//                if (salary <= 0) {
//                    errors.rejectValue("employeeSalary", "", "Employee salary must be greater than 0!");
//                }
//            }
//        }

        if (employeeDto.getEmployeeSalary() != null){
            if (!employeeDto.getEmployeeSalary().matches("^\\s*$|^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,4})?\\s*$")){
                errors.rejectValue("employeeSalary", "", "Wrong format (a number greater than 0)");
            }
        }
    }
}
