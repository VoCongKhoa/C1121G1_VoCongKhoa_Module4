package project.services.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import project.models.user.AppUser;
import project.models.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.repositories.user.AppUserRepository;
import project.repositories.user.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = this.appUserRepository.findByUsername(userName);

        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + appUser);

        // [ROLE_USER, ROLE_ADMIN,..]
        List<UserRole> userRoleList = this.userRoleRepository.findByAppUser(appUser);

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (userRoleList != null) {
            for (UserRole userRole : userRoleList) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(userRole.getAppRole().getRoleName());
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new User(appUser.getUsername(), //
                appUser.getEncrytedPassword(), grantList);

        return userDetails;
    }

    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

}
