package project.controllers.contract;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.dto.contract.ContractViewDto;
import project.dto.contract.ContractDto;
import project.models.contract.Contract;
import project.models.customer.Customer;
import project.models.employee.Employee;
import project.models.rest.ResponseObject;
import project.models.services.Services;
import project.repositories.contract.IContractViewDto;
import project.services.contract.IContractService;
import project.services.customer.ICustomerService;
import project.services.employee.IEmployeeService;
import project.services.services.IServicesService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/contractRestful")
public class ContractRestfulController {

    @Autowired
    IEmployeeService iEmployeeService;

    @Autowired
    ICustomerService iCustomerService;

    @Autowired
    IServicesService iServicesService;

    @Autowired
    IContractService iContractService;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseObject> create(@Valid @RequestBody ContractDto contractDto, BindingResult bindingResult, Model model) {
        contractDto.validate(contractDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            List<Employee> employeeList = iEmployeeService.findAllActive();
            List<Customer> customerList = iCustomerService.findAllActive();
            List<Services> servicesList = iServicesService.findAllActive();
            Collections.reverse(employeeList);
            Collections.reverse(customerList);
            Collections.reverse(servicesList);
            model.addAttribute("employeeList", employeeList);
            model.addAttribute("customerList", customerList);
            model.addAttribute("servicesList", servicesList);
            Map<String, String> errorMap = new HashMap<>();
            bindingResult
                    .getFieldErrors()
                    .stream()
                    .forEach(f -> errorMap.put(f.getField(),f.getDefaultMessage()));
            return new ResponseEntity<>(new ResponseObject("not ok","Failed!", errorMap,""), HttpStatus.BAD_REQUEST);
        } else {
            Contract contract = new Contract();
            BeanUtils.copyProperties(contractDto, contract);
            contract.setContractDeposit(Double.parseDouble(contractDto.getContractDeposit()));
            contract.setContractTotalMoney(Double.parseDouble(contractDto.getContractTotalMoney()));
            iContractService.save(contract);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping(value = "/detail/{id}")
    public ResponseEntity<ResponseObject> detail(@PathVariable int id) {
        IContractViewDto iCDD = iContractService.findContractViewDtoById(id);

        if (iCDD != null) {

            ContractViewDto contractViewDto = new ContractViewDto(iCDD.getContractId(),iCDD.getContractStartDate(),
                    iCDD.getContractEndDate(),iCDD.getContractDeposit(),iCDD.getContractTotalMoney(),iCDD.getEmployeeId(),
                    iCDD.getEmployeeName(),iCDD.getCustomerCode(),iCDD.getCustomerName(),iCDD.getServiceCode(),iCDD.getServiceName());
            return new ResponseEntity<>(new ResponseObject("ok","Success!", new HashMap<>(), contractViewDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseObject("not ok","Failed!",""),HttpStatus.BAD_REQUEST);
        }
    }
}
