package project.securities.userprincal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.models.user.AppUser;
import project.repositories.services.user.IAppUserRepository;

import javax.transaction.Transactional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    IAppUserRepository iAppUserRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = iAppUserRepository.findByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException("User Not Fount with user name: "+username)
        );
        return UserPrinciple.build(appUser);
    }
}
