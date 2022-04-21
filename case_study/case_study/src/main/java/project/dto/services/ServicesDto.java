package project.dto.services;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.models.services.RentType;
import project.models.services.ServiceType;
import javax.validation.constraints.NotBlank;

public class ServicesDto implements Validator {

    private int serviceId;
    @NotBlank
    private String serviceCode;
    @NotBlank
    private String serviceName;
    private String serviceArea;
    @NotBlank
    private String serviceCost;
    private String serviceMaxPeople;
    private String standardRoom;
    private String descriptionOtherConvenience;
    private String poolArea;
    private String numberOfFloors;
    private int active = 1;
    private RentType rentType;
    private ServiceType serviceType;

    public ServicesDto() {
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    public String getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(String serviceCost) {
        this.serviceCost = serviceCost;
    }

    public String getServiceMaxPeople() {
        return serviceMaxPeople;
    }

    public void setServiceMaxPeople(String serviceMaxPeople) {
        this.serviceMaxPeople = serviceMaxPeople;
    }

    public String getStandardRoom() {
        return standardRoom;
    }

    public void setStandardRoom(String standardRoom) {
        this.standardRoom = standardRoom;
    }

    public String getDescriptionOtherConvenience() {
        return descriptionOtherConvenience;
    }

    public void setDescriptionOtherConvenience(String descriptionOtherConvenience) {
        this.descriptionOtherConvenience = descriptionOtherConvenience;
    }

    public String getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(String poolArea) {
        this.poolArea = poolArea;
    }

    public String getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(String numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public RentType getRentType() {
        return rentType;
    }

    public void setRentType(RentType rentType) {
        this.rentType = rentType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ServicesDto servicesDto = (ServicesDto) target;
        if (servicesDto.getServiceCost() != null){
            if (!servicesDto.getServiceCost().matches("^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,4})?\\s*$")){
                errors.rejectValue("serviceCost", "", "Wrong format (a number greater than 0)");
            }
        }
        if (servicesDto.getPoolArea() != null){
            if (!servicesDto.getPoolArea().trim().equals("") && !servicesDto.getPoolArea().matches("^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,4})?\\s*$")){
                errors.rejectValue("poolArea", "", "Wrong format (a number greater than 0)");
            }
        }
        if (servicesDto.getServiceArea() != null){
            if (!servicesDto.getServiceArea().matches("^\\s*$|^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,4})?\\s*$")){
                errors.rejectValue("serviceArea", "", "Wrong format (a number greater than 0)");
            }
        }
        if (servicesDto.getServiceMaxPeople() != null){
            if (!servicesDto.getServiceMaxPeople().matches("^\\s*$|^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,4})?\\s*$")){
                errors.rejectValue("serviceMaxPeople", "", "Wrong format (a number greater than 0)");
            }
        }
        if (servicesDto.getNumberOfFloors() != null){
            if (!servicesDto.getNumberOfFloors().matches("^\\s*$|^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,4})?\\s*$")){
                errors.rejectValue("numberOfFloors", "", "Wrong format (a number greater than 0)");
            }
        }
    }
}
