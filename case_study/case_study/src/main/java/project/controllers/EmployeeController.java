package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.models.employee.Employee;
import project.services.employee.IDivisionService;
import project.services.employee.IEducationDegreeService;
import project.services.employee.IEmployeeService;
import project.services.employee.IPositionService;

import java.util.Optional;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    IEmployeeService iEmployeeService;

    @Autowired
    IDivisionService iDivisionService;

    @Autowired
    IPositionService iPositionService;

    @Autowired
    IEducationDegreeService iEducationDegreeService;

    @GetMapping(value = "/list")
    public String listEmployee(Model model,
                               @PageableDefault(value = 3) Pageable pageable,
                               @RequestParam Optional<String> nameSearch,
                               @RequestParam Optional<String> addressSearch,
                               @RequestParam Optional<String> sortOption) {
        String name = nameSearch.orElse("");
        String address = addressSearch.orElse("");
        String sort;
        Page<Employee> employeeList;
        if (sortOption.isPresent()){
            sort = sortOption.get();
            if (sort.equals("nameSort")){
                employeeList = iEmployeeService.findAllWithNameSort(pageable);
            } else {
                employeeList = iEmployeeService.findAllWithSearch(name,address,pageable);
            }
        } else {
            sort = "";
            employeeList = iEmployeeService.findAllWithSearch(name,address,pageable);
        }
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("name", name);
        model.addAttribute("address", address);
        model.addAttribute("sortOption", sort);
        return "views/employee/list_employee";
    }



}
