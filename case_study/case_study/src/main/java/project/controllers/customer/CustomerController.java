package project.controllers.customer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.dto.customer.CustomerDto;
import project.dto.customer.InHouseCustomerDto;
import project.models.contract.Contract;
import project.models.customer.Customer;
import project.models.customer.CustomerType;
import project.repositories.customer.IInHouseCustomerDto;
import project.services.contract.IContractService;
import project.services.contractDetail.IAttachServiceService;
import project.services.customer.ICustomerService;
import project.services.customer.ICustomerTypeService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    ICustomerService iCustomerService;

    @Autowired
    ICustomerTypeService iCustomerTypeService;

    @Autowired
    IContractService iContractService;

    @Autowired
    IAttachServiceService iAttachServiceService;

    @GetMapping(value = "/list")
    public String listCustomer(Model model,
                               @PageableDefault(value = 3) Pageable pageable,
                               @RequestParam Optional<String> codeSearch,
                               @RequestParam Optional<String> nameSearch,
                               @RequestParam Optional<String> addressSearch,
                               @RequestParam Optional<String> sortOption) {
        String code = codeSearch.orElse("");
        String name = nameSearch.orElse("");
        String address = addressSearch.orElse("");
        String sort;
        Page<Customer> customerList;
        if (sortOption.isPresent()){
            sort = sortOption.get();
            switch (sort){
                case "nameSort":
                    customerList = iCustomerService.findAllWithNameSort(pageable);
                    break;
                case "birthdaySort":
                    customerList = iCustomerService.findAllWithBirthdaySort(pageable);
                    break;
                default:
                    customerList = iCustomerService.findAllWithSearch(code,name,address,pageable);
            }
        } else {
            sort = "";
            customerList = iCustomerService.findAllWithSearch(code,name,address,pageable);
        }

        List<CustomerType> customerTypeList = iCustomerTypeService.findAllActive();
        Collections.reverse(customerTypeList);
        model.addAttribute("customerDto", new CustomerDto());
        model.addAttribute("customerTypeList", customerTypeList);
        model.addAttribute("currentPage", pageable.getPageNumber());
        model.addAttribute("customerList", customerList);
        model.addAttribute("code", code);
        model.addAttribute("name", name);
        model.addAttribute("address", address);
        model.addAttribute("sortOption", sort);
        return "views/customer/list_customer";
    }

    @GetMapping(value = "/listInHouseCustomer")
    public String listInHouseCustomer(Model model,
                                      @PageableDefault(value = 3) Pageable pageable,
                                      @RequestParam Optional<String> codeSearch,
                                      @RequestParam Optional<String> nameSearch,
                                      @RequestParam Optional<String> addressSearch,
                                      @RequestParam Optional<String> sortOption) {
        String code = codeSearch.orElse("");
        String name = nameSearch.orElse("");
        String address = addressSearch.orElse("");
        String sort;
        Page<IInHouseCustomerDto> inHouseCustomerDtoList;
        if (sortOption.isPresent()){
            sort = sortOption.get();
            switch (sort){
                case "nameSort":
                    inHouseCustomerDtoList = iCustomerService.findAllWithNameSortListInHouse(pageable);
                    break;
                case "birthdaySort":
                    inHouseCustomerDtoList = iCustomerService.findAllWithBirthdaySortListInHouse(pageable);
                    break;
                default:
                    inHouseCustomerDtoList = iCustomerService.findAllWithSearchListInHouse(code,name,address,pageable);
            }
        } else {
            sort = "";
            inHouseCustomerDtoList = iCustomerService.findAllWithSearchListInHouse(code,name,address,pageable);
        }


        List<CustomerType> customerTypeList = iCustomerTypeService.findAllActive();
        Collections.reverse(customerTypeList);
        List<Contract> contractList = iContractService.findAllActive();
        model.addAttribute("contractList", contractList);
        model.addAttribute("customerDto", new CustomerDto());
        model.addAttribute("customerTypeList", customerTypeList);
        model.addAttribute("currentPage", pageable.getPageNumber());
        model.addAttribute("inHouseCustomerDtoList", inHouseCustomerDtoList);
        model.addAttribute("code", code);
        model.addAttribute("name", name);
        model.addAttribute("address", address);
        model.addAttribute("sortOption", sort);
        return "views/customer/list_in_house_customer";
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
        Customer customer = iCustomerService.findByIdActive(id);
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
    public String goDelete(@PathVariable int id, Model model) {
        List<CustomerType> customerTypeList = iCustomerTypeService.findAllActive();
        Collections.reverse(customerTypeList);
        Customer customer = iCustomerService.findByIdActive(id);
        if (customer != null) {
            model.addAttribute("customer", customer);
            model.addAttribute("customerTypeList", customerTypeList);
            return "views/customer/delete_customer";
        } else {
            return "views/customer/404_customer";
        }
    }

    @PostMapping(value = "/delete")
    public String delete(@ModelAttribute Customer customer) {
        iCustomerService.delete(customer.getCustomerId());
        return "redirect:/customer/list";
    }


}
