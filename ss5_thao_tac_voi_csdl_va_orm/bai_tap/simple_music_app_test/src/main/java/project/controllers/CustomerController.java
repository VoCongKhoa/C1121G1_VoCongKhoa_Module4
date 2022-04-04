package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.models.Customer;
import project.services.ICustomerService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    ICustomerService iCustomerService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listAllCustomer(Model model){
        List<Customer> customerList = new ArrayList<>();
        customerList = iCustomerService.findAll();
        model.addAttribute("customerList", customerList);
        return "list_customer";
    }
}
