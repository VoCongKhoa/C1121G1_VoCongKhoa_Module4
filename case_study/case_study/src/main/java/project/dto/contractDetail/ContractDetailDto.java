package project.dto.contractDetail;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.models.contract.Contract;
import project.models.contractDetail.AttachService;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

public class ContractDetailDto implements Validator {

    private int contractDetailId;
    @NotBlank
    private String quantity;
    private int active = 1;
    private Contract contract;
    private AttachService attachService;

    public ContractDetailDto() {
    }

    public int getContractDetailId() {
        return contractDetailId;
    }

    public void setContractDetailId(int contractDetailId) {
        this.contractDetailId = contractDetailId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public AttachService getAttachService() {
        return attachService;
    }

    public void setAttachService(AttachService attachService) {
        this.attachService = attachService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ContractDetailDto contractDetailDto = (ContractDetailDto) target;
        if (contractDetailDto.getQuantity() != null){
            if (!contractDetailDto.getQuantity().matches("^\\s*$|^\\s*(?=.*[1-9])\\d*\\s*$")){
                errors.rejectValue("quantity", "", "Wrong format (a number greater than 0)");
            }
        }
    }
}
