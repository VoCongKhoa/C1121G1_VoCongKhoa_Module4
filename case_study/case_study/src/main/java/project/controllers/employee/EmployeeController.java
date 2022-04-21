package project.controllers.employee;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.dto.employee.EmployeeDto;
import project.models.employee.Division;
import project.models.employee.EducationDegree;
import project.models.employee.Employee;
import project.models.employee.Position;
import project.services.employee.IDivisionService;
import project.services.employee.IEducationDegreeService;
import project.services.employee.IEmployeeService;
import project.services.employee.IPositionService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
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
                               @RequestParam Optional<String> sortOption,
                               @RequestParam Optional<Integer> positionId) {
        Integer positionIdVal = positionId.orElse(0);
        String name = nameSearch.orElse("");
        String address = addressSearch.orElse("");
        String sort;
        Page<Employee> employeeList;
        System.out.println(positionIdVal);
        if (sortOption.isPresent()){
            sort = sortOption.get();
            switch (sort){
                case "nameSort":
                    employeeList = iEmployeeService.findAllWithNameSort(pageable);
                    break;
                case "salarySort":
                    employeeList = iEmployeeService.findAllWithSalarySort(pageable);
                    break;
                case "birthdaySort":
                    employeeList = iEmployeeService.findAllWithBirthdaySort(pageable);
                    break;
                default:
                    employeeList = iEmployeeService.findAllWithSearch(name,address,positionIdVal,pageable);
            }
//            if (sort.equals("nameSort")){
//                employeeList = iEmployeeService.findAllWithNameSort(pageable);
//            } else {
//                employeeList = iEmployeeService.findAllWithSearch(name,address,positionIdVal,pageable);
//            }
        } else {
            sort = "";
            employeeList = iEmployeeService.findAllWithSearch(name,address,positionIdVal,pageable);
        }
        List<Position> positionList = iPositionService.findAllActive();

        List<Division> divisionList = iDivisionService.findAllActive();

        List<EducationDegree> educationDegreeList = iEducationDegreeService.findAllActive();
        Collections.reverse(positionList);

        model.addAttribute("employeeDto", new EmployeeDto());
        model.addAttribute("positionList", positionList);
        model.addAttribute("divisionList", divisionList);
        model.addAttribute("educationDegreeList", educationDegreeList);
        model.addAttribute("currentPage", pageable.getPageNumber());
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("name", name);
        model.addAttribute("address", address);
        model.addAttribute("positionIdVal", positionIdVal);
        model.addAttribute("sortOption", sort);
        return "views/employee/list_employee";
    }

//    @GetMapping(value = "/create")
//    public String goCreate(Model model) {
//        List<Position> positionList = iPositionService.findAllActive();
//
//        List<Division> divisionList = iDivisionService.findAllActive();
//
//        List<EducationDegree> educationDegreeList = iEducationDegreeService.findAllActive();
//        Collections.reverse(positionList);
//
//        model.addAttribute("employeeDto", new EmployeeDto());
//        model.addAttribute("positionList", positionList);
//        model.addAttribute("divisionList", divisionList);
//        model.addAttribute("educationDegreeList", educationDegreeList);
//        return "views/employee/create_employee";
//    }

    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute EmployeeDto employeeDto, BindingResult bindingResult, Model model) {
        employeeDto.validate(employeeDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            List<Position> positionList = iPositionService.findAllActive();

            List<Division> divisionList = iDivisionService.findAllActive();

            List<EducationDegree> educationDegreeList = iEducationDegreeService.findAllActive();
            Collections.reverse(positionList);

            model.addAttribute("positionList", positionList);
            model.addAttribute("divisionList", divisionList);
            model.addAttribute("educationDegreeList", educationDegreeList);
            System.out.println(bindingResult);
            return "views/employee/create_employee";
        } else {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDto, employee);
//            employee.setEmployeeSalary(Double.parseDouble(employeeDto.getEmployeeSalary()));
            iEmployeeService.save(employee);
            return "redirect:/employee/list";
        }
    }

}
