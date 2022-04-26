package project.controllers.contractDetail;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.dto.contract.ContractDto;
import project.dto.contract.ContractViewDto;
import project.dto.contractDetail.ContractDetailDto;
import project.models.contract.Contract;
import project.models.contractDetail.AttachService;
import project.models.contractDetail.ContractDetail;
import project.models.customer.Customer;
import project.models.employee.Employee;
import project.models.rest.ResponseObject;
import project.models.services.Services;
import project.repositories.contract.IContractViewDto;
import project.repositories.contractDetail.IContractDetailViewDto;
import project.services.contract.IContractService;
import project.services.contractDetail.IAttachServiceService;
import project.services.contractDetail.IContractDetailService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/contractDetailRestful")
public class ContractDetailRestfulController {

    @Autowired
    IContractDetailService iContractDetailService;

    @Autowired
    IContractService iContractService;

    @Autowired
    IAttachServiceService iAttachServiceService;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseObject> create(@Valid @RequestBody ContractDetailDto contractDetailDto, BindingResult bindingResult, Model model) {
        contractDetailDto.validate(contractDetailDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            List<Contract> contractList = iContractService.findAllActive();
            List<AttachService> attachServiceList = iAttachServiceService.findAllActive();
            model.addAttribute("contractList", contractList);
            model.addAttribute("attachServiceList", attachServiceList);
            Map<String, String> errorMap = new HashMap<>();
            bindingResult
                    .getFieldErrors()
                    .stream()
                    .forEach(f -> errorMap.put(f.getField(),f.getDefaultMessage()));
            return new ResponseEntity<>(new ResponseObject("not ok","Failed!", errorMap,""), HttpStatus.BAD_REQUEST);
        } else {
            ContractDetail contractDetail = new ContractDetail();
            BeanUtils.copyProperties(contractDetailDto, contractDetail);
            contractDetail.setQuantity(Integer.parseInt(contractDetailDto.getQuantity()));
            iContractDetailService.save(contractDetail);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping(value = "/detail/{id}")
    public ResponseEntity<ResponseObject> detail(@PathVariable int id) {
        IContractDetailViewDto iContractDetailViewDto = iContractDetailService.findContractDetailViewDtoById(id);
        if (iContractDetailViewDto != null) {
            return new ResponseEntity<>(new ResponseObject("ok","Success!", new HashMap<>(), iContractDetailViewDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseObject("not ok","Failed!",""),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/detailAttachService/{id}")
    public ResponseEntity<ResponseObject> detailAttachService(@PathVariable int id) {
        AttachService attachService = iAttachServiceService.findAttachServiceViewById(id);
        if (attachService != null) {
            return new ResponseEntity<>(new ResponseObject("ok","Success!", new HashMap<>(), attachService), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseObject("not ok","Failed!",""),HttpStatus.BAD_REQUEST);
        }
    }
}
