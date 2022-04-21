package project.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.dto.services.ServicesDto;
import project.models.rest.ResponseObject;
import project.models.services.RentType;
import project.models.services.ServiceType;
import project.models.services.Services;
import project.services.services.IRentTypeService;
import project.services.services.IServiceTypeService;
import project.services.services.IServicesService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/servicesRestful")
public class ServicesRestfulController {

    @Autowired
    IRentTypeService iRentTypeService;

    @Autowired
    IServiceTypeService iServiceTypeService;

    @Autowired
    IServicesService iServicesService;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseObject> create(@Valid @RequestBody ServicesDto servicesDto, BindingResult bindingResult, Model model) {
        servicesDto.validate(servicesDto, bindingResult);
        if (iServicesService.findByCodeActice(servicesDto.getServiceCode()) != null){
            bindingResult.rejectValue("serviceCode","","Service already existed!");
        }
        if (bindingResult.hasFieldErrors()) {
            List<RentType> rentTypeList = iRentTypeService.findAllActive();
            List<ServiceType> serviceTypeList = iServiceTypeService.findAllActive();
            Collections.reverse(rentTypeList);
            Collections.reverse(serviceTypeList);
            model.addAttribute("rentTypeList", rentTypeList);
            model.addAttribute("serviceTypeList", serviceTypeList);
            Map<String, String> errorMap = new HashMap<>();
            bindingResult
                    .getFieldErrors()
                    .stream()
                    .forEach(f -> errorMap.put(f.getField(),f.getDefaultMessage()));
            System.out.println(bindingResult);
            errorMap.entrySet().forEach(f -> System.out.println(f.getKey() + ':' + f.getValue()));
            return new ResponseEntity<>(new ResponseObject("not ok","Failed!", errorMap,""), HttpStatus.BAD_REQUEST);
        } else {
            Services services = new Services();
            BeanUtils.copyProperties(servicesDto, services);

            services.setServiceCost(Double.parseDouble(servicesDto.getServiceCost()));

            if (!servicesDto.getServiceArea().trim().equals("")){
                services.setServiceArea(Integer.parseInt(servicesDto.getServiceArea()));
            }
            if (!servicesDto.getServiceMaxPeople().trim().equals("")){
                services.setServiceMaxPeople(Integer.parseInt(servicesDto.getServiceMaxPeople()));
            }
            if (!servicesDto.getPoolArea().trim().equals("")){
                services.setPoolArea(Double.parseDouble(servicesDto.getPoolArea()));
            }
            if (!servicesDto.getNumberOfFloors().trim().equals("")){
                services.setPoolArea(Double.parseDouble(servicesDto.getNumberOfFloors()));
            }

            System.out.println(services);
            iServicesService.save(services);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
