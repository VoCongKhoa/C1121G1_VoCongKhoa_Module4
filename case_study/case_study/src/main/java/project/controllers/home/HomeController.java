package project.controllers.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.models.employee.Employee;
import project.services.employee.IEmployeeService;

@Controller
@RequestMapping(value = {"","/home"})
public class HomeController {

    @Autowired
    IEmployeeService iEmployeeService;

    @GetMapping(value = "")
    public String goHome(){
        return "views/home";
    }

    @GetMapping(value = "/login")
    public String goLogin(){
        return "views/user/login";
    }

    @GetMapping(value = "/signin")
    public String goSignin(Model model){
        model.addAttribute("employeeList", iEmployeeService.findAllActive());
        return "views/user/signin";
    }
}
