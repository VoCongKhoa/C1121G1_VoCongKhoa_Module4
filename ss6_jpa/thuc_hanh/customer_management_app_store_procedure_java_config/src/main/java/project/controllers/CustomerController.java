package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.models.Customer;
import project.services.ICustomerService;

import java.util.List;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;

    @GetMapping(value = "",  produces = "application/json; charset=UTF-8")
    public String index(Model model) {
        List<Customer> customerList = iCustomerService.findAll();
        model.addAttribute("customerList", customerList);
        return "/index";
    }

    @GetMapping(value = "/create",  produces = "application/json; charset=UTF-8")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "/create";
    }

    @PostMapping(value = "/save",  produces = "application/json; charset=UTF-8")
    public String save(Customer customer, RedirectAttributes redirectAttributes) {
        iCustomerService.insertWithStoredProcedure(customer);
        redirectAttributes.addFlashAttribute("success", "Add new successfully");
        return "redirect:/customer/";
    }

    @GetMapping(value = "/{id}/edit",  produces = "application/json; charset=UTF-8")
    public String goUpdateForm(Model model, @PathVariable Long id){
        model.addAttribute("customer", iCustomerService.findById(id));
        return "/edit";
    }

    @PostMapping(value = "/update",  produces = "application/json; charset=UTF-8")
    public String update(RedirectAttributes redirectAttributes, @ModelAttribute Customer customer){
        iCustomerService.update(customer);
        redirectAttributes.addFlashAttribute("success", "Edit customer successfully");
        return "redirect:/customer/";
    }

    @GetMapping(value = "/{id}/view",  produces = "application/json; charset=UTF-8")
    public String goView(Model model, @PathVariable Long id){
        model.addAttribute("customer", iCustomerService.findById(id));
        return "/view";
    }

    @GetMapping(value = "/{id}/delete",  produces = "application/json; charset=UTF-8")
    public String goDeleteForm(Model model, @PathVariable Long id){
        model.addAttribute("customer", iCustomerService.findById(id));
        return "/delete";
    }

    @PostMapping(value = "/delete",  produces = "application/json; charset=UTF-8")
    public String delete(RedirectAttributes redirectAttributes, @ModelAttribute Customer customer){
        iCustomerService.remove(customer.getId());
        redirectAttributes.addFlashAttribute("success", "Delete customer successfully");
        return "redirect:/customer/";
    }

    @GetMapping(value = "/search",  produces = "application/json; charset=UTF-8")
    public String search(Model model, @RequestParam String nameSearch){
        model.addAttribute("customerList", iCustomerService.searchByName(nameSearch));
        return "/index";
    }
}
