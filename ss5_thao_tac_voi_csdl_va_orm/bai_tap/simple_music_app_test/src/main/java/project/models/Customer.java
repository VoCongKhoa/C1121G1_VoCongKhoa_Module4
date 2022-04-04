package project.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Không được bỏ trống")
    @Size(min = 1, message = "không được bỏ trống")
    private String lastName;

    @Column(name = "free_passes")
    @NotNull(message = "không được bỏ trống")
    @Min(value = 0, message = "phải lớn hơn hoặc bằng 0")
    @Max(value = 10, message = "phải nhỏ hơn hoặc bằng 10")
    private Integer freePasses;

    @Column(name = "postal_code")
    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "5 ký tự hoặc số")
    private String postalCode;

    @Column(name = "email")
    @NotNull(message = "không được bỏ trống")
    @Email(message = "Không đúng định dạng email! Vui lòng nhập lại email")
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
