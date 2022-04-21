package project.controllers.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.models.contract.Contract;
import project.models.customer.Customer;
import project.models.employee.Employee;
import project.models.services.Services;
import project.services.contract.IContractService;
import project.services.customer.ICustomerService;
import project.services.employee.IEmployeeService;
import project.services.services.IServicesService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/contract")
public class ContractController {

    @Autowired
    IEmployeeService iEmployeeService;

    @Autowired
    ICustomerService iCustomerService;

    @Autowired
    IServicesService iServicesService;

    @Autowired
    IContractService iContractService;

    @GetMapping(value = "/list")
    public String listContract(Model model,
                               @PageableDefault(value = 3) Pageable pageable,
                               @RequestParam Optional<Double> contractTotalMoneyOptional,
                               @RequestParam Optional<String> contractStartDateOptional,
                               @RequestParam Optional<String> contractEndDateOptional,
                               @RequestParam Optional<String> sortOption) {
        Double contractTotalMoney = contractTotalMoneyOptional.orElse(0.0);
        String contractStartDate = contractStartDateOptional.orElse("");
        String contractEndDate = contractEndDateOptional.orElse("");
        String sort;
        Page<Contract> contractList;
        if (sortOption.isPresent()){
            sort = sortOption.get();
            if (sort.equals("startDateSort")){ //switch-case
                contractList = iContractService.findAllWithStartDateSort(pageable);
            } else {
                contractList = iContractService.findAllWithSearch(contractTotalMoney,contractStartDate,contractEndDate,pageable); //default
            }
        } else {
            sort = "";
            contractList = iContractService.findAllWithSearch(contractTotalMoney,contractStartDate,contractEndDate,pageable);
        }

        List<Employee> employeeList = iEmployeeService.findAllActive();
        List<Customer> customerList = iCustomerService.findAllActive();
        List<Services> servicesList = iServicesService.findAllActive();
        Collections.reverse(employeeList);
        Collections.reverse(customerList);
        Collections.reverse(servicesList);
//        model.addAttribute("contractDto", new ContractDto());
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("customerList", customerList);
        model.addAttribute("servicesList", servicesList);
        model.addAttribute("currentPage", pageable.getPageNumber());
        model.addAttribute("contractList", contractList);
        model.addAttribute("contractTotalMoney", contractTotalMoney);
        model.addAttribute("contractStartDate", contractStartDate);
        model.addAttribute("contractEndDate", contractEndDate);
        model.addAttribute("sortOption", sort);
        return "views/contract/list_contract";
    }
}
