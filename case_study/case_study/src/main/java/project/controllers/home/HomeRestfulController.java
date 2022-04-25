package project.controllers.home;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import project.dto.user.request.SignInForm;
import project.dto.user.request.SignUpForm;
import project.dto.user.response.JwtResponse;
import project.dto.user.response.ResponseMessage;
import project.models.user.AppRole;
import project.models.user.AppUser;
import project.models.user.RoleName;
import project.securities.jwt.JwtProvider;
import project.securities.userprincal.UserPrinciple;
import project.services.user.AppRoleServiceImpl;
import project.services.user.AppUserServiceImpl;
import project.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
public class HomeRestfulController {

    @Autowired
    AppUserServiceImpl appUserService;
    @Autowired
    AppRoleServiceImpl appRoleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm){
        if(appUserService.existsByUsername(signUpForm.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("nouser"), HttpStatus.OK);
        }

        AppUser appUser = new AppUser(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strAppRoles = signUpForm.getAppRoles();
        Set<AppRole> appRoles = new HashSet<>();
        strAppRoles.forEach(appRole ->{
            switch (appRole){
                case "admin":
                    AppRole adminAppRole = appRoleService.findByName(RoleName.ADMIN).orElseThrow(()->
                            new RuntimeException("Role NOT FOUND"));
                    appRoles.add(adminAppRole);
                    break;
                case "pm":
                    AppRole pmAppRole = appRoleService.findByName(RoleName.PM).orElseThrow(
                            ()-> new RuntimeException("Role NOT FOUND"));
                    appRoles.add(pmAppRole);
                    break;
                default: AppRole userAppRole = appRoleService.findByName(RoleName.USER).orElseThrow(()->new RuntimeException("ROLE NOT FOUND"));
                    appRoles.add(userAppRole);
            }
        });
        appUser.setAppRoles(appRoles);
        appUserService.save(appUser);
        return new ResponseEntity<>(new ResponseMessage("create success!!!"), HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token,userPrinciple.getName(), userPrinciple.getAuthorities()));

    }

    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = { "/error" }, method = RequestMethod.GET)
    public String errorPage(Model model) {
        return "403Page";
    }
}
