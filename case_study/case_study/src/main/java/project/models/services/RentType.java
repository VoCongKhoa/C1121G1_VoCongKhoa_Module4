package project.models.services;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class RentType {
    //rent_type_id, rent_type_name, rent_type_cost

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rent_type_id")
    private int rentTypeId;
    private String rentTypeName;
    private double rentTypeCost;
    @Column(name = "active", columnDefinition = "BIT(1) default 1")
    private int active;

    @OneToMany(mappedBy = "rentType", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Services> servicesSet;

    public RentType() {
    }

    public int getRentTypeId() {
        return rentTypeId;
    }

    public void setRentTypeId(int rentTypeId) {
        this.rentTypeId = rentTypeId;
    }

    public String getRentTypeName() {
        return rentTypeName;
    }

    public void setRentTypeName(String rentTypeName) {
        this.rentTypeName = rentTypeName;
    }

    public double getRentTypeCost() {
        return rentTypeCost;
    }

    public void setRentTypeCost(double rentTypeCost) {
        this.rentTypeCost = rentTypeCost;
    }

    public Set<Services> getServiceSet() {
        return servicesSet;
    }

    public void setServiceSet(Set<Services> servicesSet) {
        this.servicesSet = servicesSet;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
