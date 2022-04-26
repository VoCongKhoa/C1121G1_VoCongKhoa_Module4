package project.dto.user;

import org.springframework.security.core.userdetails.UserDetails;
import project.models.employee.Employee;

public class UserDetailAndTokenDto {
    private UserDetails userDetails;
    private String token;
    private Employee employee;

    public UserDetailAndTokenDto(UserDetails userDetails, String token, Employee employee) {
        this.userDetails = userDetails;
        this.token = token;
        this.employee = employee;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
