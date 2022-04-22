package project.repositories.contract;

public interface IContractViewDto {
    int getContractId();
    String getContractStartDate();
    String getContractEndDate();
    String getContractDeposit();
    String getContractTotalMoney();
    int getEmployeeId();
    String getEmployeeName();
    String getCustomerCode();
    String getCustomerName();
    String getServiceCode();
    String getServiceName();
    boolean getActive();
}
