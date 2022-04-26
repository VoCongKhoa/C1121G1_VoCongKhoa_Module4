package project.models.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import project.models.employee.Employee;

import javax.persistence.*;

@Entity
@Table(name = "app_user", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "APP_USER_UK", columnNames = "username") })
public class AppUser {

    @Id
    @Column(name = "username", length = 36)
    private String username;

    @Column(name = "encryted_password", length = 128)
    private String encrytedPassword;

    @Column(name = "active", columnDefinition = "INT default 1")
    private int active;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    @JsonBackReference
    private Employee employee;

    public AppUser() {
    }

    public AppUser(String username, String encrytedPassword, Employee employee) {
        this.username = username;
        this.encrytedPassword = encrytedPassword;
        this.employee = employee;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getEncrytedPassword() {
        return encrytedPassword;
    }

    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
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
