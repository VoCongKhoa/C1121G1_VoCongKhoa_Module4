package project.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.models.CustomerType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CustomerDto implements Validator {

    private int customerId;

    @NotBlank
    @Pattern(regexp = "^$|^KH-[\\d]{4}$", message = "Wrong format, ex: KH-0123")
    private String customerCode;

    @NotBlank
    private String customerName;

    @NotBlank
    private String customerBirthday;

    private int customerGender; //INTEGER

    @NotBlank
    @Pattern(regexp = "^$|^\\d{9}$", message = "Wrong format, ex: 123456789")
    private String customerIdCard;

    @NotBlank
    @Pattern(regexp = "^$|^(0|\\(84\\)\\+)9[0|1]\\d{7}$", message = "Wrong format, ex: 090xxxxxxx or 091xxxxxxx or (84)+90xxxxxxx or (84)+91xxxxxxx")
    private String customerPhone;

    @Pattern(regexp = "^$|^[\\w]+@\\w+(\\.\\w+)+$", message = "Wrong format, ex: abc@gmail.com")
    private String customerEmail;

    private String customerAddress;
    private CustomerType customerType;

    private int active = 1;

    public CustomerDto() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerBirthday() {
        return customerBirthday;
    }

    public void setCustomerBirthday(String customerBirthday) {
        this.customerBirthday = customerBirthday;
    }

    public int getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(int customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerIdCard() {
        return customerIdCard;
    }

    public void setCustomerIdCard(String customerIdCard) {
        this.customerIdCard = customerIdCard;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
