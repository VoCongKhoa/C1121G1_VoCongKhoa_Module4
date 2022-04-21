package project.dto.contract;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.models.customer.Customer;
import project.models.employee.Employee;
import project.models.services.Services;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContractDto implements Validator {

    private int contractId;
    @NotBlank
    private String contractStartDate;
    @NotBlank
    private String contractEndDate;
    @NotBlank
    private String contractDeposit;
    @NotBlank
    private String contractTotalMoney;
    private Employee employee;
    private Customer customer;
    private Services services;
    private int active = 1;

    public ContractDto() {
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(String contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public String getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(String contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getContractDeposit() {
        return contractDeposit;
    }

    public void setContractDeposit(String contractDeposit) {
        this.contractDeposit = contractDeposit;
    }

    public String getContractTotalMoney() {
        return contractTotalMoney;
    }

    public void setContractTotalMoney(String contractTotalMoney) {
        this.contractTotalMoney = contractTotalMoney;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
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
        ContractDto contractDto = (ContractDto) target;

        // Validate contractStartDate and contractEndDate
        if (contractDto.getContractStartDate().equals("")) {
            errors.rejectValue("contractStartDate", "contractStartDate.notBlank", "Must not be blank!");
        } else {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate contractStartDate = LocalDate.parse(contractDto.getContractStartDate(), formatter);
            if (contractStartDate.isAfter(currentDate)) {
                errors.rejectValue("contractStartDate", "contractStartDate.afterCurrentDate", "Contract start date must be after today!");
            }
        }
        if (contractDto.getContractEndDate().equals("")) {
            errors.rejectValue("contractEndDate", "contractEndDate.notBlank", "Must not be blank!");
        } else {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate contractEndDate = LocalDate.parse(contractDto.getContractEndDate(), formatter);
            if (contractEndDate.isBefore(currentDate)) {
                errors.rejectValue("contractEndDate", "contractEndDate.afterCurrentDate", "Contract end date must be before today!");
            }
        }
        if (!contractDto.getContractStartDate().equals("") && !contractDto.getContractEndDate().equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate contractStartDate = LocalDate.parse(contractDto.getContractStartDate(), formatter);
            LocalDate contractEndDate = LocalDate.parse(contractDto.getContractEndDate(), formatter);
            if (contractEndDate.isBefore(contractStartDate)) {
                errors.rejectValue("contractStartDate", "contractStartDate.afterContractEndDate", "Contract start date must be after contract end date!");
                errors.rejectValue("contractEndDate", "contractEndDate.beforeContractStartDate", "Contract end date must be before contract end date!");
            }
        }

        // Validate contractDeposit
        if (contractDto.getContractDeposit() != null){
            if (!contractDto.getContractDeposit().matches("^\\s*$|^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,4})?\\s*$")){
                errors.rejectValue("contractDeposit", "", "Wrong format (a number greater than 0)");
            }
        }

        // Validate contractTotalMoney
        if (contractDto.getContractTotalMoney() != null){
            if (!contractDto.getContractTotalMoney().matches("^\\s*$|^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,4})?\\s*$")){
                errors.rejectValue("contractTotalMoney", "", "Wrong format (a number greater than 0)");
            }
        }
    }
}
