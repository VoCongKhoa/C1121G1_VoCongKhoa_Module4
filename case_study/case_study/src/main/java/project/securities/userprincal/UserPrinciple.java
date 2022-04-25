package project.securities.userprincal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.models.user.AppUser;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

// Dùng để kết nối user của mình với user của hệ thống
public class UserPrinciple implements UserDetails {
    private static final int serialVersionUID = 1;
    private String name;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> appRoles;

    public UserPrinciple() {
    }

    public UserPrinciple(String name,String username, String email, String password, Collection<? extends GrantedAuthority> appRoles) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.appRoles = appRoles;
    }

    // Build UserPrinciple dựa vào user của mình
    public static UserPrinciple build(AppUser user){
        List<GrantedAuthority> authorities = user.getAppRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
        return new UserPrinciple(
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<? extends GrantedAuthority> getAppRoles() {
        return appRoles;
    }

    public void setAppRoles(Collection<? extends GrantedAuthority> appRoles) {
        this.appRoles = appRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return appRoles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
