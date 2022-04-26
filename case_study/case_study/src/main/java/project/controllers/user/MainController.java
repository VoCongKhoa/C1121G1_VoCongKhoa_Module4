package project.controllers.user;

import java.security.Principal;
import java.util.*;


import com.fasterxml.jackson.databind.util.JSONPObject;
import netscape.javascript.JSObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.configs.JwtTokenUtil;
import project.dto.employee.EmployeeDto;
import project.dto.user.AppUserDto;
import project.dto.user.UserDetailAndTokenDto;
import project.models.employee.Division;
import project.models.employee.EducationDegree;
import project.models.employee.Employee;
import project.models.employee.Position;
import project.models.rest.ResponseObject;
import project.models.user.*;
import project.services.employee.IEmployeeService;
import project.services.user.IAppRoleService;
import project.services.user.IUserRoleService;
import project.services.user.JwtUserDetailsService;
import project.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/homeRestful")
public class MainController {

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    IEmployeeService iEmployeeService;

    @Autowired
    IUserRoleService iUserRoleService;

    @Autowired
    IAppRoleService iAppRoleService;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseObject> create(@Valid @RequestBody AppUserDto appUserDto, BindingResult bindingResult, Model model) {
//        if(userService.existsByUsername(signUpForm.getUsername())){
//            return new ResponseEntity<>(new ResponseMessage("nouser"), HttpStatus.OK);
//        }
//        if(userService.existsByEmail(signUpForm.getEmail())){
//            return new ResponseEntity<>(new ResponseMessage("noemail"), HttpStatus.OK);
//        }
        String bcryptedPassword = bcryptEncoder.encode(appUserDto.getPassword());
        Employee employee = iEmployeeService.findByIdActive(appUserDto.getEmployee().getEmployeeId());
        AppUser appUser = new AppUser(appUserDto.getUsername(), bcryptedPassword, employee);
        userDetailsService.save(appUser);
        if (employee.getPosition().getPositionId() == 1) {
            UserRole userRole = new UserRole();
            AppRole appRole = iAppRoleService.findByRoleId(1);
            userRole.setAppUser(appUser);
            userRole.setAppRole(appRole);
            iUserRoleService.save(userRole);
        } else {
            UserRole userRole = new UserRole();
            AppRole appRole = iAppRoleService.findByRoleId(2);
            userRole.setAppUser(appUser);
            userRole.setAppRole(appRole);
            iUserRoleService.save(userRole);
        }

        return new ResponseEntity<>(new ResponseObject("create success!!!", "", appUser), HttpStatus.OK);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        Employee employee = iEmployeeService.findByUsername(authenticationRequest.getUsername());
        UserDetailAndTokenDto userDetailAndTokenDto = new UserDetailAndTokenDto(userDetails, token, employee);
        return ResponseEntity.ok(userDetailAndTokenDto);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}