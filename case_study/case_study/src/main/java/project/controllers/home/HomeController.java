package project.controllers.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"","/home"})
public class HomeController {

    @GetMapping(value = "")
    public String goHome(){
        return "views/home";
    }

    @GetMapping(value = "/login")
    public String goLogin(){
        return "views/user/login";
    }
}
