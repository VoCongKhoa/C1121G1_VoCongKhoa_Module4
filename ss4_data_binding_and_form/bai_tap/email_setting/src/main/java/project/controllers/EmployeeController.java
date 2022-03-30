package project.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.models.EmailDetail;

@Controller
@RequestMapping("employee/")
public class EmployeeController {

//    @RequestMapping(value = "showForm", method = RequestMethod.GET)
//    public String showForm(ModelMap model) {
//        model.addAttribute("employee", new EmailDetail());
//        return "create";
//    }
//
//    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
//    public String submit(@ModelAttribute("employee") EmailDetail emailDetail, ModelMap model) {
//        model.addAttribute("name", emailDetail.getName());
//        model.addAttribute("contactNumber", emailDetail.getContactNumber());
//        model.addAttribute("id", emailDetail.getId());
//        return "info";
//    }
}
