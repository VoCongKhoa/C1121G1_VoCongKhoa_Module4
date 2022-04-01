package project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.models.Customer;
import project.services.CustomerService;
import project.services.ICustomerService;

import java.util.List;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    private final ICustomerService customerService = new CustomerService();

    //    LIST ALL CUSTOMER
    @GetMapping
    public String index(Model model) {
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customers", customerList);
        return "/index";
    }


    //    CREATE CUSTOMER
    @GetMapping("/create")
    public String goCreateForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "/create";
    }

    @PostMapping("/save")
    public String save(Customer customer, RedirectAttributes redirectAttributes) {
        customer.setId((int) (Math.random() * 10000));
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("success", "Add new successfully");
        return "redirect:/customer";
    }

    //    UPDATE CUSTOMER
    @GetMapping("/{id}/edit")
    public String goUpdateForm(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {
        customerService.update(customer.getId(), customer);
        redirectAttributes.addFlashAttribute("success", "Update customer successfully");
        return "redirect:/customer";
    }

    //    DELETE CUSTOMER
    @GetMapping("/{id}/delete")
    public String goDeleteForm(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/delete";
    }

    @PostMapping("/delete")
    public String delete(Customer customer, RedirectAttributes redirect) {
        customerService.remove(customer.getId());
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/customer";
    }

    //    VIEW CUSTOMER
    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/view";
    }
}
