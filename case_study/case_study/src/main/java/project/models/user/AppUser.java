package project.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;
import project.models.employee.Employee;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
//Không cho username và email bị trùng nhau
@Table(name = "app_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class AppUser {
    @Id
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;
    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @JsonIgnore
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            inverseJoinColumns = @JoinColumn(name = "user_name"),
            joinColumns = @JoinColumn(name = "role_id"))
    Set<AppRole> appRoles = new HashSet<>();

    @Column(name = "active", columnDefinition = "INT default 1")
    private int active;
    @OneToOne(mappedBy = "appUser")
    private Employee employee;

    public AppUser() {
    }

    public AppUser(String name, String username, String email, String password, Set<AppRole> appRoles) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.appRoles = appRoles;
    }

    public AppUser(@NotBlank @Size(min = 3, max = 50) String name,
                   @NotBlank @Size(min = 3, max = 50) String username,
                   @NotBlank
                   @Size(max = 50)
                   @Email String email,
                   @NotBlank
                   @Size(min = 6, max = 100)
                           String encode) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = encode;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AppRole> getAppRoles() {
        return appRoles;
    }

    public void setAppRoles(Set<AppRole> appRoles) {
        this.appRoles = appRoles;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
//import project.models.employee.Employee;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.OneToOne;
//
//@Entity
//public class AppUser {
//
//    @Id
//    private String username;
//    private String password;
//
//    @Column(name = "active", columnDefinition = "BIT(1) default 1")
//    private int active;
//    @OneToOne(mappedBy = "appUser")
//    private Employee employee;
//
//    public AppUser() {
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public int getActive() {
//        return active;
//    }
//
//    public void setActive(int active) {
//        this.active = active;
//    }
//
//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
//}
