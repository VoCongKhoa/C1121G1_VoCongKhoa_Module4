package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.models.Customer;
import project.models.Province;
import project.services.ICustomerService;
import project.services.IProvinceService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IProvinceService provinceService;

    @ModelAttribute("provinces")
    public Iterable<Province> provinces(){
        return provinceService.findAll();
    }

    @GetMapping(value = "",  produces = "application/json; charset=UTF-8")
    public String index(Model model, Pageable pageable) {
        Page<Customer> customerList = iCustomerService.findAll(pageable);
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
        iCustomerService.save(customer);
        redirectAttributes.addFlashAttribute("success", "Add new successfully");
        return "redirect:/customer/";
    }

    @GetMapping(value = "/{id}/edit",  produces = "application/json; charset=UTF-8")
    public ModelAndView goUpdateForm(Model model, @PathVariable Long id){
        Optional<Customer> customer = iCustomerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/edit");
            model.addAttribute("customer", customer.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error/404");
            return modelAndView;
        }
    }

    @PostMapping(value = "/update",  produces = "application/json; charset=UTF-8")
    public String update(RedirectAttributes redirectAttributes, @ModelAttribute Customer customer){
        iCustomerService.save(customer);
        redirectAttributes.addFlashAttribute("success", "Edit customer successfully");
        return "redirect:/customer/";
    }

    @GetMapping(value = "/{id}/view",  produces = "application/json; charset=UTF-8")
    public String goView(Model model, @PathVariable Long id){
        model.addAttribute("customer", iCustomerService.findById(id));
        return "/view";
    }

    @GetMapping(value = "/{id}/delete",  produces = "application/json; charset=UTF-8")
    public ModelAndView goDeleteForm(Model model, @PathVariable Long id){
        Optional<Customer> customer = iCustomerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/delete");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error/404");
            return modelAndView;
        }
    }

    @PostMapping(value = "/delete",  produces = "application/json; charset=UTF-8")
    public String delete(RedirectAttributes redirectAttributes, @ModelAttribute Customer customer){
        iCustomerService.remove(customer.getId());
        redirectAttributes.addFlashAttribute("success", "Delete customer successfully");
        return "redirect:/customer/";
    }

    @GetMapping("/customers")
    public ModelAndView listCustomers(@RequestParam("search") Optional<String> search, Pageable pageable){
        Page<Customer> customers;
        if(search.isPresent()){
            customers = iCustomerService.findAllByFirstNameContaining(search.get(), pageable);
        } else {
            customers = iCustomerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

}
