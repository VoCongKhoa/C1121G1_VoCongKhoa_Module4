package project.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.dto.SoTietKiemDto;
import project.models.KhachHang;
import project.models.SoTietKiem;
import project.services.IKhachHangService;
import project.services.ISoTietKiemService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/index")
public class SoTietKiemController {

    @Autowired
    private ISoTietKiemService iSoTietKiemService;

    @Autowired
    private IKhachHangService iKhachHangService;

    @GetMapping(value = "")
    public String index(Model model,
                        @PageableDefault(value = 2) Pageable pageable,
                        @RequestParam Optional<String> tenKhachHang,
                        @RequestParam Optional<String> ngayBatDau,
                        @RequestParam Optional<String> ngayKetThuc) {

        String tenKhachHangThuc = tenKhachHang.orElse("");
        String ngayBatDauThuc = ngayBatDau.orElse("");
        String ngayKetThucThuc = ngayKetThuc.orElse("");
        if(!(tenKhachHangThuc.equals("")&ngayBatDauThuc.equals("")&ngayKetThucThuc.equals(""))){
            return "redirect:/index/search";
        } else {
            Page<SoTietKiem> soTietKiemList = iSoTietKiemService.findAllByTenKhachHangAndNgayGui(tenKhachHangThuc,ngayBatDauThuc,ngayKetThucThuc, pageable);
            model.addAttribute("soTietKiemList", iSoTietKiemService.findAll(pageable));
            model.addAttribute("tenKhachHangThuc",tenKhachHangThuc);
            model.addAttribute("ngayBatDauThuc",ngayBatDauThuc);
            model.addAttribute("ngayKetThucThuc",ngayKetThucThuc);
            return "views/list";
        }
    }

    @GetMapping(value = "/create")
    public String goCreate(Model model) {
        model.addAttribute("soTietKiemDto", new SoTietKiemDto());
        model.addAttribute("khachHangList",iKhachHangService.findAll());
        return "/views/create";
    }

    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute SoTietKiemDto soTietKiemDto,
                         BindingResult bindingResult,
                         Model model) {
        soTietKiemDto.validate(soTietKiemDto,bindingResult);
        if (bindingResult.hasFieldErrors()){
            model.addAttribute("khachHangList",iKhachHangService.findAll());
            return "views/create";
        } else {
            SoTietKiem soTietKiem = new SoTietKiem();
            BeanUtils.copyProperties(soTietKiemDto,soTietKiem);
            KhachHang khachHang = new KhachHang();
            khachHang.setMaKhachHang(soTietKiemDto.getKhachHang().getMaKhachHang());
            soTietKiem.setKhachHang(khachHang);
            iSoTietKiemService.save(soTietKiem);
            model.addAttribute("msg", "Th??m m???i th??nh c??ng");
            return "redirect:/index";
        }

    }

    @GetMapping(value = "/update")
    public String goUpdate(Model model, @RequestParam int id) {
        SoTietKiem soTietKiem = iSoTietKiemService.findById(id);
        if (soTietKiem != null){
            SoTietKiemDto soTietKiemDto = new SoTietKiemDto();
            BeanUtils.copyProperties(soTietKiem,soTietKiemDto);
            soTietKiemDto.setSoTienGui(String.valueOf(soTietKiem.getSoTienGui()));
            soTietKiemDto.setKyHan(String.valueOf(soTietKiem.getKyHan()));
            model.addAttribute("soTietKiemDto", soTietKiemDto);
            model.addAttribute("khachHangList",iKhachHangService.findAll());
            return "/views/edit";
        } else {
            return "views/404";
        }
    }

    @PostMapping(value = "/update")
    public String update(@Valid @ModelAttribute SoTietKiemDto soTietKiemDto,
                         BindingResult bindingResult,
                         Model model) {
        soTietKiemDto.validate(soTietKiemDto,bindingResult);
        if (bindingResult.hasFieldErrors()){
            model.addAttribute("khachHangList",iKhachHangService.findAll());
            return "views/edit";
        } else {
            SoTietKiem soTietKiem = new SoTietKiem();
            BeanUtils.copyProperties(soTietKiemDto,soTietKiem);
            soTietKiem.setSoTienGui(Double.parseDouble(soTietKiemDto.getSoTienGui()));
            soTietKiem.setKyHan(Integer.parseInt(soTietKiemDto.getKyHan()));
            iSoTietKiemService.save(soTietKiem);
            model.addAttribute("msg", "S???a th??nh c??ng");
            return "redirect:/index";
        }
    }

    @GetMapping(value = "/detail/{id}")
    public String goDetail(Model model, @PathVariable int id) {
        SoTietKiem soTietKiem = iSoTietKiemService.findById(id);
        if (soTietKiem != null){
            model.addAttribute("soTietKiem", soTietKiem);
            model.addAttribute("khachHangList",iKhachHangService.findAll());
            return "views/detail";
        } else {
            return "views/404";
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String goDelete(Model model, @PathVariable int id) {
        SoTietKiem soTietKiem = iSoTietKiemService.findById(id);
        if (soTietKiem != null){
            model.addAttribute("soTietKiem", soTietKiem);
            model.addAttribute("khachHangList",iKhachHangService.findAll());
            return "views/delete";
        } else {
            return "views/404";
        }
    }

    @PostMapping(value = "/delete")
    public String delete(@ModelAttribute SoTietKiem soTietKiem) {
        iSoTietKiemService.deleteById(soTietKiem.getMaSoTietKiem());
        return "redirect:/index";
    }

    @GetMapping(value = "/search")
    public String search(Model model, @RequestParam Optional<String> tenKhachHang,
                         @RequestParam Optional<String> ngayBatDau,
                         @RequestParam Optional<String> ngayKetThuc, @PageableDefault(value = 2) Pageable pageable) {
        String tenKhachHangThuc = tenKhachHang.orElse("");
        String ngayBatDauThuc = ngayBatDau.orElse("");
        String ngayKetThucThuc = ngayKetThuc.orElse("");
        Page<SoTietKiem> soTietKiemList = iSoTietKiemService.findAllByTenKhachHangAndNgayGui(tenKhachHangThuc,ngayBatDauThuc,ngayKetThucThuc, pageable);
        model.addAttribute("soTietKiemList", soTietKiemList);
        return "views/list";
    }
}
