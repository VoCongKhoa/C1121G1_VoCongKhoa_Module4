package project.dto.user.response;


import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String name;
    private Collection<?extends GrantedAuthority> appRoles;

    public JwtResponse() {
    }

    public JwtResponse(String token, String name, Collection<? extends GrantedAuthority> appRoles) {
        this.token = token;
        this.name = name;
        this.appRoles = appRoles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<? extends GrantedAuthority> getAppRoles() {
        return appRoles;
    }

    public void setAppRoles(Collection<? extends GrantedAuthority> appRoles) {
        this.appRoles = appRoles;
    }
}