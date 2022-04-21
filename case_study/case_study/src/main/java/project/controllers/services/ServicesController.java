package project.controllers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.dto.services.ServicesDto;
import project.models.services.RentType;
import project.models.services.ServiceType;
import project.models.services.Services;
import project.services.services.IRentTypeService;
import project.services.services.IServiceTypeService;
import project.services.services.IServicesService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
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
        if (sortOption.isPresent()){
            sort = sortOption.get();
            if (sort.equals("nameSort")){
                servicesList = iServicesService.findAllWithNameSort(pageable);
            } else {
                servicesList = iServicesService.findAllWithSearch(serviceTypeIdVal,convenience,cost,pageable);
            }
        } else {
            sort = "";
            servicesList = iServicesService.findAllWithSearch(serviceTypeIdVal,convenience,cost,pageable);
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
}
