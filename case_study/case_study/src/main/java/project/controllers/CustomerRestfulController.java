package project.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.dto.customer.CustomerDto;
import project.models.customer.Customer;
import project.models.customer.CustomerType;
import project.models.rest.ResponseObject;
import project.services.customer.ICustomerService;
import project.services.customer.ICustomerTypeService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/customerRestful")
public class CustomerRestfulController {

    @Autowired
    ICustomerService iCustomerService;

    @Autowired
    ICustomerTypeService iCustomerTypeService;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseObject> create(@Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult, Model model) {
        customerDto.validate(customerDto, bindingResult);
        if (iCustomerService.findByCode(customerDto.getCustomerCode()) != null) {
            bindingResult.rejectValue("customerCode", "", "Customer code already existed!!");
        }
        if (bindingResult.hasFieldErrors()) {
            List<CustomerType> customerTypeList = iCustomerTypeService.findAllActive();
            Collections.reverse(customerTypeList);
            model.addAttribute("customerTypeList", customerTypeList);
            Map<String, String> errorMap = new HashMap<>();
            bindingResult
                    .getFieldErrors()
                    .stream()
                    .forEach(f -> errorMap.put(f.getField(),f.getDefaultMessage()));
            return new ResponseEntity<>(new ResponseObject("not ok","Failed!", errorMap,""), HttpStatus.BAD_REQUEST);
        } else {
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerDto, customer);
            iCustomerService.save(customer);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping(value = "/detail/{id}")
    public ResponseEntity<ResponseObject> detail(@PathVariable int id, Model model) {
        Customer customer = iCustomerService.findById(id);
        if (customer != null) {
            CustomerDto customerUpdateDto = new CustomerDto();
            BeanUtils.copyProperties(customer, customerUpdateDto);
            model.addAttribute("customerUpdateDto", customerUpdateDto);
            return new ResponseEntity<>(new ResponseObject("ok","Success!", new HashMap<>(),customerUpdateDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseObject("not ok","Failed!",""),HttpStatus.BAD_REQUEST);
        }
    }
}
