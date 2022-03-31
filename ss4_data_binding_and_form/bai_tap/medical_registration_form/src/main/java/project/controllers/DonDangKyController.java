package project.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.models.DonDangKy;
import project.services.IDonDangKyService;

@Controller
public class DonDangKyController {

    @Autowired
    private IDonDangKyService iDonDangKyService;

//    list
    @GetMapping("listAll")
    public String listAll(Model model) {
        model.addAttribute("quocTichList", iDonDangKyService.getQuocTichList());
        model.addAttribute("donDangKyList", iDonDangKyService.getDonDangKyList());
        return "list";
    }

//    show form create
    @GetMapping("create")
    public String showForm(Model model) {
        DonDangKy donDangKy = new DonDangKy();
        model.addAttribute("donDangKy", donDangKy);
        model.addAttribute("namSinhList", iDonDangKyService.getNamSinhList());
        model.addAttribute("quocTichList", iDonDangKyService.getQuocTichList());
        return "create";
    }

//    submit create
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute DonDangKy donDangKy){
        iDonDangKyService.save(donDangKy);
        return "redirect:/listAll";
    }

//    detail
    @RequestMapping(value = "listOne/{maDon}", method = RequestMethod.GET)
    public String listOne(Model model, @PathVariable int maDon) {
        DonDangKy donDangKy = iDonDangKyService.findOne(maDon);
        model.addAttribute("donDangKy", donDangKy);
        model.addAttribute("namSinhList", iDonDangKyService.getNamSinhList());
        model.addAttribute("quocTichList", iDonDangKyService.getQuocTichList());
        return "update";
    }


    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute DonDangKy donDangKy){
        iDonDangKyService.update(donDangKy);
        return "redirect:/listAll";
    }
}
