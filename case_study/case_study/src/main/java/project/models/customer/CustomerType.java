package project.models.customer;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "customer_type")
public class CustomerType {
    //customer_type_id,	customer_type_name
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_type_id")
    private int customerTypeId;

    @Column(name = "customer_type_name")
    private String customerTypeName;

    @Column(name = "active", columnDefinition = "BIT(1) default 1")
    private int active;

    @OneToMany(mappedBy = "customerType", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Customer> customerSet;

    public CustomerType() {
    }

    public int getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(int customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public String getCustomerTypeName() {
        return customerTypeName;
    }

    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName;
    }

    public Set<Customer> getCustomerSet() {
        return customerSet;
    }

    public void setCustomerSet(Set<Customer> customerSet) {
        this.customerSet = customerSet;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
