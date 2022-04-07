package project.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.dto.UserDto;
import project.models.User;
import project.services.IUserService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    IUserService iUserService;

    @RequestMapping(value = "/goRegister", method = RequestMethod.GET)
    public String goRegister(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "/views/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute UserDto userDto,
                           BindingResult bindingResult,
                           Model model) {
        userDto.validate(userDto,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "/views/login";
        } else {
            User user = new User();
            BeanUtils.copyProperties(userDto,user);
            iUserService.save(user);
            return "/views/success";
        }
    }
}
