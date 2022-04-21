package project.controllers.employee;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.dto.employee.EmployeeDto;
import project.models.employee.Employee;
import project.models.employee.Division;
import project.models.employee.EducationDegree;
import project.models.employee.Position;
import project.models.rest.ResponseObject;
import project.services.employee.IDivisionService;
import project.services.employee.IEducationDegreeService;
import project.services.employee.IEmployeeService;
import project.services.employee.IPositionService;

import javax.validation.Valid;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/employeeRestful")
public class EmployeeRestfulController {

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
            if (sort.equals("nameSort")){
                employeeList = iEmployeeService.findAllWithNameSort(pageable);
            } else {
                employeeList = iEmployeeService.findAllWithSearch(name,address,positionIdVal,pageable);
            }
        } else {
            sort = "";
            employeeList = iEmployeeService.findAllWithSearch(name,address,positionIdVal,pageable);
        }
        model.addAttribute("positionList", iPositionService.findAllActive());
        model.addAttribute("currentPage", pageable.getPageNumber());
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("name", name);
        model.addAttribute("address", address);
        model.addAttribute("positionIdVal", positionIdVal);
        model.addAttribute("sortOption", sort);
        return "views/employee/list_employee";
    }

//    @PostMapping(value = "/create")
//    public String create(@Valid @ModelAttribute EmployeeDto employeeDto, BindingResult bindingResult, Model model) {
//        employeeDto.validate(employeeDto, bindingResult);
//        if (bindingResult.hasFieldErrors()) {
//            List<Position> positionList = iPositionService.findAllActive();
//
//            List<Division> divisionList = iDivisionService.findAllActive();
//
//            List<EducationDegree> educationDegreeList = iEducationDegreeService.findAllActive();
//            Collections.reverse(positionList);
//
//            model.addAttribute("positionList", positionList);
//            model.addAttribute("divisionList", divisionList);
//            model.addAttribute("educationDegreeList", educationDegreeList);
//            System.out.println(bindingResult);
//            return "views/employee/create_employee";
//        } else {
//            Employee employee = new Employee();
//            BeanUtils.copyProperties(employeeDto, employee);
//            employee.setEmployeeSalary(Double.parseDouble(employeeDto.getEmployeeSalary()));
//            iEmployeeService.save(employee);
//            return "redirect:/employee/list";
//        }
//    }

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseObject> create(@Valid @RequestBody EmployeeDto employeeDto, BindingResult bindingResult, Model model) {
        employeeDto.validate(employeeDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            List<Position> positionList = iPositionService.findAllActive();

            List<Division> divisionList = iDivisionService.findAllActive();

            List<EducationDegree> educationDegreeList = iEducationDegreeService.findAllActive();
            Collections.reverse(positionList);

            model.addAttribute("positionList", positionList);
            model.addAttribute("divisionList", divisionList);
            model.addAttribute("educationDegreeList", educationDegreeList);
            Map<String, String> errorMap = new HashMap<>();
            bindingResult
                    .getFieldErrors()
                    .stream()
                    .forEach(f -> errorMap.put(f.getField(),f.getDefaultMessage()));
            System.out.println(bindingResult);
            errorMap.entrySet().forEach(f -> System.out.println(f.getKey() + ':' + f.getValue()));
            return new ResponseEntity<>(new ResponseObject("not ok","Failed!", errorMap,""), HttpStatus.BAD_REQUEST);
        } else {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDto, employee);
//            employee.setEmployeeSalary(Double.parseDouble(employeeDto.getEmployeeSalary()));
            iEmployeeService.save(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping(value = "/detail/{id}")
    public ResponseEntity<ResponseObject> detail(@PathVariable int id) {
        Employee employee = iEmployeeService.findByIdActive(id);
        if (employee != null) {
            EmployeeDto employeeUpdateDto = new EmployeeDto();
            BeanUtils.copyProperties(employee, employeeUpdateDto);
            employeeUpdateDto.setEmployeeSalary(String.valueOf(employee.getEmployeeSalary()));
            return new ResponseEntity<>(new ResponseObject("ok","Success!", new HashMap<>(),employeeUpdateDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseObject("not ok","Failed!",""),HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<ResponseObject> update(@PathVariable int id, @Valid @RequestBody EmployeeDto employeeDto, BindingResult bindingResult, Model model) {
        employeeDto.validate(employeeDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            List<Position> positionList = iPositionService.findAllActive();

            List<Division> divisionList = iDivisionService.findAllActive();

            List<EducationDegree> educationDegreeList = iEducationDegreeService.findAllActive();
            Collections.reverse(positionList);

            model.addAttribute("positionList", positionList);
            model.addAttribute("divisionList", divisionList);
            model.addAttribute("educationDegreeList", educationDegreeList);
            Map<String, String> errorMap = new HashMap<>();
            bindingResult
                    .getFieldErrors()
                    .stream()
                    .forEach(f -> errorMap.put(f.getField(),f.getDefaultMessage()));
            return new ResponseEntity<>(new ResponseObject("not ok","Failed!", errorMap,""), HttpStatus.BAD_REQUEST);
        } else {
            Employee employee = iEmployeeService.findByIdActive(id);
            if (employee != null){
                employeeDto.setEmployeeId(employee.getEmployeeId());
                BeanUtils.copyProperties(employeeDto,employee);
                iEmployeeService.save(employee);
                return new ResponseEntity<>(new ResponseObject("ok","Success!", new HashMap<>(),""),HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }
    }

    @PatchMapping(value = "/delete/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable int id) {
        Employee employee = iEmployeeService.findByIdActive(id);
        if (employee != null) {
            employee.setActive(0);
            iEmployeeService.save(employee);
            return new ResponseEntity<>(new ResponseObject("ok","Success!", new HashMap<>(),""), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseObject("not ok","Failed!",""),HttpStatus.NOT_FOUND);
        }
    }
}
