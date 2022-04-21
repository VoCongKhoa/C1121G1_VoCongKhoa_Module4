package project.controllers.contractDetail;

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
import project.models.contractDetail.AttachService;
import project.models.contractDetail.ContractDetail;
import project.models.customer.Customer;
import project.models.employee.Employee;
import project.models.services.Services;
import project.services.contract.IContractService;
import project.services.contractDetail.IAttachServiceService;
import project.services.contractDetail.IContractDetailService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value ="/contractDetail")
public class ContractDetailController {

    @Autowired
    IContractDetailService iContractDetailService;

    @Autowired
    IContractService iContractService;

    @Autowired
    IAttachServiceService iAttachServiceService;

    @GetMapping(value = "/list")
    public String listContract(Model model,
                               @PageableDefault(value = 3) Pageable pageable,
                               @RequestParam Optional<Integer> attachServiceIdOptional,
                               @RequestParam Optional<String> sortOption) {
        Integer attachServiceId = attachServiceIdOptional.orElse(0);
        String sort;
        Page<ContractDetail> contractDetailList;
        if (sortOption.isPresent()){
            sort = sortOption.get();
            if (sort.equals("quantitySort")){ //switch-case
                contractDetailList = iContractDetailService.findAllWithQuantitySort(pageable);
            } else {
                contractDetailList = iContractDetailService.findAllWithSearch(attachServiceId,pageable); //default
            }
        } else {
            sort = "";
            contractDetailList = iContractDetailService.findAllWithSearch(attachServiceId,pageable);
        }

        List<AttachService> attachServiceList = iAttachServiceService.findAllActive();
        List<Contract> contractList = iContractService.findAllActive();
        model.addAttribute("attachServiceList", attachServiceList);
        model.addAttribute("contractList", contractList);
        model.addAttribute("currentPage", pageable.getPageNumber());
        model.addAttribute("contractDetailList", contractDetailList);
        model.addAttribute("attachServiceId", attachServiceId);
        model.addAttribute("sortOption", sort);
        return "views/contractDetail/list_contract_detail";
    }
}
