package project.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


public class UserDto implements Validator {

    private Integer userDtoId;

    @NotBlank
    @Size(min = 5, max = 45)
    private String firstName;
    @NotBlank
    @Size(min = 5, max = 45)
    private String lastName;
    @NotBlank
    @Pattern(regexp = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$",
            message = "Wrong format!")
    private String phoneNumber;
    private String dateOfBirth;
    @NotBlank
    @Email
    private String email;

    public UserDto() {
    }

    public Integer getUserDtoId() {
        return userDtoId;
    }

    public void setUserDtoId(Integer userDtoId) {
        this.userDtoId = userDtoId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;
        if (userDto.getDateOfBirth().equals("")) {
            errors.rejectValue("dateOfBirth", "dateOfBirth.notBlankCustom", "Must choose date of birth");
        } else {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dob = LocalDate.parse(userDto.getDateOfBirth(), formatter);
            Integer age = Period.between(dob, currentDate).getYears();
            if (age <= 18) {
                errors.rejectValue("dateOfBirth", "dateOfBirth.less18age", "Must more than 18 years old!");
            }
        }
    }
}

