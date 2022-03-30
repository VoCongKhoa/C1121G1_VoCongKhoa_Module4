package project.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.models.EmailDetail;
import project.services.IEmailDetailService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("emailDetail/")
public class EmployeeController {

    @Autowired
    private IEmailDetailService iEmailDetailService;

    @ModelAttribute("emailDetails")
    public List<EmailDetail> getEmailDetails() {
        return new ArrayList<>(this.iEmailDetailService.findAll());
    }

    @ModelAttribute("spamsEnable")
    public Map<Boolean, String> getSpamsEnable(){
        Map<Boolean, String> spamsEnableMap = new HashMap<>();
        spamsEnableMap.put(true, "Enable");
        spamsEnableMap.put(false, "Unenable");
        return spamsEnableMap;
    }
    @ModelAttribute("languageList")
    public List<String> getLanguageList(){
        return iEmailDetailService.getLanguageList();
    }
    @ModelAttribute("pageSizeList")
    public List<Integer> getPageSizeList(){
        return iEmailDetailService.getPageSizeList();
    }

    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    public String listAll() {
        return "list";
    }

    @RequestMapping(value = "showForm", method = RequestMethod.GET)
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("emailDetail", new EmailDetail());
        return "create";
    }
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute EmailDetail emailDetail){
        iEmailDetailService.save(emailDetail);
        return "redirect:/emailDetail/listAll";
    }

    @RequestMapping(value = "listOne/{id}", method = RequestMethod.GET)
    public String listOne(ModelMap modelMap, @PathVariable int id) {
        EmailDetail emailDetail = iEmailDetailService.findOne(id);
        modelMap.addAttribute("emailDetail", emailDetail);
        return "update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute EmailDetail emailDetail){
        System.out.println(emailDetail);
        iEmailDetailService.update(emailDetail);
        return "redirect:/emailDetail/listAll";
    }

//    @RequestMapping(value = "/detail", method = RequestMethod.POST)
//    public String submit(@ModelAttribute("detailEmail") EmailDetail emailDetail, ModelMap model) {
//        model.addAttribute("name", emailDetail.getName());
//        model.addAttribute("contactNumber", emailDetail.getContactNumber());
//        model.addAttribute("id", emailDetail.getId());
//        return "info";
//    }
}
