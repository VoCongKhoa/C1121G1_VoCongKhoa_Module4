package project.models.services;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Currency;
import project.models.contract.Contract;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "service")
public class Services {
    //service_id, service_code, service_name, service_area, service_cost, service_max_people, standard_room,
    // description_other_convenience, pool_area, number_of_floors, rent_type_id, service_type_id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private int serviceId;
    private String serviceCode;
    private String serviceName;
    @Column(columnDefinition = "INT default 0")
    private int serviceArea;
    @Column(columnDefinition = "DOUBLE default 0")
    private double serviceCost;
    @Column(columnDefinition = "INT default 0")
    private int serviceMaxPeople;
    private String standardRoom;
    private String descriptionOtherConvenience;
    @Column(columnDefinition = "DOUBLE default 0")
    private double poolArea;
    @Column(columnDefinition = "INT default 0")
    private int numberOfFloors;
    @Column(name = "active", columnDefinition = "BIT(1) default 1")
    private int active;
    private String freeAttachedService;

    @ManyToOne
    @JoinColumn(name = "rent_type_id", referencedColumnName = "rent_type_id")
    private RentType rentType;

    @ManyToOne
    @JoinColumn(name = "service_type_id", referencedColumnName = "service_type_id")
    private ServiceType serviceType;

    @OneToMany(mappedBy = "services")
    @JsonBackReference
    private Set<Contract> contractSet;

    public Services() {
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

    public int getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(int serviceArea) {
        this.serviceArea = serviceArea;
    }

    public double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public int getServiceMaxPeople() {
        return serviceMaxPeople;
    }

    public void setServiceMaxPeople(int serviceMaxPeople) {
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

    public double getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(double poolArea) {
        this.poolArea = poolArea;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
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

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Contract> getContractSet() {
        return contractSet;
    }

    public void setContractSet(Set<Contract> contractSet) {
        this.contractSet = contractSet;
    }

    public String getFreeAttachedService() {
        return freeAttachedService;
    }

    public void setFreeAttachedService(String freeAttachedService) {
        this.freeAttachedService = freeAttachedService;
    }
}
