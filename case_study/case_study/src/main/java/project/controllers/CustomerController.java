package project.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.dto.CustomerDto;
import project.models.Customer;
import project.models.CustomerType;
import project.services.ICustomerService;
import project.services.ICustomerTypeService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    ICustomerService iCustomerService;

    @Autowired
    ICustomerTypeService iCustomerTypeService;

    @GetMapping(value = "/list")
    public String listCustomer(Model model) {
        List<Customer> customerList = iCustomerService.findAllActive();
        model.addAttribute("customerList", customerList);
        return "views/customer/list_customer";
    }

    @GetMapping(value = "/create")
    public String goCreate(Model model) {
        List<CustomerType> customerTypeList = iCustomerTypeService.findAllActive();
        Collections.reverse(customerTypeList);
        model.addAttribute("customerDto", new CustomerDto());
        model.addAttribute("customerTypeList", customerTypeList);
        return "views/customer/create_customer";
    }

    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute CustomerDto customerDto, BindingResult bindingResult, Model model) {
        customerDto.validate(customerDto, bindingResult);
        if (iCustomerService.findByCode(customerDto.getCustomerCode()) != null) {
            bindingResult.rejectValue("customerCode", "", "Customer code already existed!!");
        }
        if (bindingResult.hasFieldErrors()) {
            List<CustomerType> customerTypeList = iCustomerTypeService.findAllActive();
            Collections.reverse(customerTypeList);
            model.addAttribute("customerTypeList", customerTypeList);
            return "views/customer/create_customer";
        } else {
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerDto, customer);
            iCustomerService.save(customer);
            return "redirect:/customer/list";
        }
    }

    @GetMapping(value = "/update/{id}")
    public String goUpdate(@PathVariable int id, Model model) {
        List<CustomerType> customerTypeList = iCustomerTypeService.findAllActive();
        Collections.reverse(customerTypeList);
        Customer customer = iCustomerService.findById(id);
        if (customer != null) {
            CustomerDto customerDto = new CustomerDto();
            BeanUtils.copyProperties(customer, customerDto);
            model.addAttribute("customerDto", customerDto);
            model.addAttribute("customerTypeList", customerTypeList);
            return "views/customer/update_customer";
        } else {
            return "views/customer/404_customer";
        }
    }

    //Loi query
    @PostMapping(value = "/update")
    public String update(@Valid @ModelAttribute CustomerDto customerDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasFieldErrors()) {
            List<CustomerType> customerTypeList = iCustomerTypeService.findAllActive();
            Collections.reverse(customerTypeList);
            model.addAttribute("customerTypeList", customerTypeList);
            return "views/customer/update_customer";
        } else {
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerDto, customer);
            iCustomerService.update(customer);
            return "redirect:/customer/list";
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String goDelete(@PathVariable int id, Model model){
        List<CustomerType> customerTypeList = iCustomerTypeService.findAllActive();
        Collections.reverse(customerTypeList);
        Customer customer = iCustomerService.findById(id);
        if (customer != null) {
            model.addAttribute("customer", customer);
            model.addAttribute("customerTypeList", customerTypeList);
            return "views/customer/delete_customer";
        } else {
            return "views/customer/404_customer";
        }
    }

    @PostMapping(value = "/delete")
    public String delete(@ModelAttribute Customer customer){
        iCustomerService.delete(customer.getCustomerId());
        return "redirect:/customer/list";
    }




}
