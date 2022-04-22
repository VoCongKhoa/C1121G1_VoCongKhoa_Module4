package project.controllers.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.dto.services.ServicesDto;
import project.models.customer.Customer;
import project.models.customer.CustomerType;
import project.models.services.RentType;
import project.models.services.ServiceType;
import project.models.services.Services;
import project.services.services.IRentTypeService;
import project.services.services.IServiceTypeService;
import project.services.services.IServicesService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin
@RequestMapping(value = "/services")
public class ServicesController {

    @Autowired
    IRentTypeService iRentTypeService;

    @Autowired
    IServiceTypeService iServiceTypeService;

    @Autowired
    IServicesService iServicesService;

    @GetMapping(value = "/list")
    public String listServices(Model model,
                               @PageableDefault(value = 3) Pageable pageable,
                               @RequestParam Optional<Integer> serviceTypeId,
                               @RequestParam Optional<String> convenienceSearch,
                               @RequestParam Optional<String> costSearch,
                               @RequestParam Optional<String> sortOption) {
        Integer serviceTypeIdVal = serviceTypeId.orElse(0);
        String convenience = convenienceSearch.orElse("");
        String cost = costSearch.orElse("");
        String sort;
        Page<Services> servicesList;
        if (sortOption.isPresent()) {
            sort = sortOption.get();
            switch (sort) {
                case "nameSort":
                    servicesList = iServicesService.findAllWithNameSort(pageable);
                    break;
                case "costSort":
                    servicesList = iServicesService.findAllWithCostSort(pageable);
                    break;
                case "areaSort":
                    servicesList = iServicesService.findAllWithAreaSort(pageable);
                    break;
                default:
                    servicesList = iServicesService.findAllWithSearch(serviceTypeIdVal, convenience, cost, pageable);
            }
        } else {
            sort = "";
            servicesList = iServicesService.findAllWithSearch(serviceTypeIdVal, convenience, cost, pageable);
        }

        List<RentType> rentTypeList = iRentTypeService.findAllActive();
        List<ServiceType> serviceTypeList = iServiceTypeService.findAllActive();
        Collections.reverse(rentTypeList);
        Collections.reverse(serviceTypeList);
        model.addAttribute("servicesDto", new ServicesDto());
        model.addAttribute("rentTypeList", rentTypeList);
        model.addAttribute("serviceTypeList", serviceTypeList);
        model.addAttribute("currentPage", pageable.getPageNumber());
        model.addAttribute("servicesList", servicesList);
        model.addAttribute("serviceTypeId", serviceTypeIdVal);
        model.addAttribute("convenience", convenience);
        model.addAttribute("cost", cost);
        model.addAttribute("sortOption", sort);
        return "views/services/list_services";
    }

    @GetMapping(value = "/create")
    public String goCreate() {
        return "views/services/create_services";
    }

    @GetMapping(value = "/showCreate")
    public String showForm(@RequestParam int serviceTypeId, Model model) {
        List<RentType> rentTypeList = iRentTypeService.findAllActive();
        List<ServiceType> serviceTypeList = iServiceTypeService.findAllActive();
        Collections.reverse(rentTypeList);
        Collections.reverse(serviceTypeList);
        model.addAttribute("servicesDto", new ServicesDto());
        model.addAttribute("isExist", serviceTypeId);
        model.addAttribute("rentTypeList", rentTypeList);
        model.addAttribute("serviceTypeList", serviceTypeList);
        return "views/services/create_services";
    }

    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute(name = "servicesDto") ServicesDto servicesDto,
                         BindingResult bindingResult, @RequestParam(name = "serviceTypeId") int serviceTypeId, Model model, RedirectAttributes redirectAttributes) {
        servicesDto.validate(servicesDto, bindingResult);
        if (iServicesService.findByCodeActice(servicesDto.getServiceCode()) != null) {
            bindingResult.rejectValue("serviceCode", "", "Service code already existed!!");
        }
        if (bindingResult.hasFieldErrors()) {
            List<RentType> rentTypeList = iRentTypeService.findAllActive();
            List<ServiceType> serviceTypeList = iServiceTypeService.findAllActive();
            Collections.reverse(rentTypeList);
            Collections.reverse(serviceTypeList);
            model.addAttribute("isExist", serviceTypeId);
            model.addAttribute("rentTypeList", rentTypeList);
            model.addAttribute("serviceTypeList", serviceTypeList);
            return "views/services/create_services";
        } else {
            Services services = new Services();
            BeanUtils.copyProperties(servicesDto, services);
            ServiceType serviceType = iServiceTypeService.findByIdActive(serviceTypeId);
            services.setServiceType(serviceType);
            if (servicesDto.getServiceCost() != null) {
                if (!servicesDto.getServiceCost().trim().equals("")) {
                    services.setServiceCost(Double.parseDouble(servicesDto.getServiceCost()));
                }
            }
            if (servicesDto.getServiceArea() != null) {
                if (!servicesDto.getServiceArea().trim().equals("")) {
                    services.setServiceArea(Integer.parseInt(servicesDto.getServiceArea()));
                }
            }
            if (servicesDto.getNumberOfFloors() != null) {
                if (!servicesDto.getNumberOfFloors().trim().equals("")) {
                    services.setNumberOfFloors(Integer.parseInt(servicesDto.getNumberOfFloors()));
                }
            }
            if (servicesDto.getServiceMaxPeople() != null) {
                if (!servicesDto.getServiceMaxPeople().trim().equals("")) {
                    services.setServiceMaxPeople(Integer.parseInt(servicesDto.getServiceMaxPeople()));
                }
            }
            if (servicesDto.getPoolArea() != null) {
                if (!servicesDto.getPoolArea().trim().equals("")) {
                    services.setPoolArea(Double.parseDouble(servicesDto.getPoolArea()));
                }
            }
            iServicesService.save(services);
            return "redirect:/services/list";
        }
    }


}
