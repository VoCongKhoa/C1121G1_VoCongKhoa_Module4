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
import project.dto.SachDto;
import project.dto.TheMuonSachDto;
import project.models.HocSinh;
import project.models.Sach;
import project.models.TheMuonSach;
import project.services.IHocSinhService;
import project.services.ISachService;
import project.services.ITheMuonSachService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(value = "/index")
public class MuonSachController {

    @Autowired
    private ISachService iSachService;

    @Autowired
    private IHocSinhService iHocSinhService;

    @Autowired
    private ITheMuonSachService iTheMuonSachService;

    @GetMapping
    public String index(Model model, @PageableDefault(value = 2) Pageable pageable) {
        Page<Sach> sachPage = iSachService.findAllPaging(pageable);
        model.addAttribute("sachPage", sachPage);
        return "views/list";
    }

    @GetMapping(value = "/create")
    public String goCreate(Model model) {
        model.addAttribute("theMuonSachDto", new TheMuonSachDto());
        model.addAttribute("hocSinhList", iHocSinhService.findAll());
        model.addAttribute("sachList", iSachService.findAllSachCoTheMuon());
        return "/views/create";
    }

    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute TheMuonSachDto theMuonSachDto,
                         BindingResult bindingResult,
                         Model model) {

//        BindingResult bindingResult1 = new BindingResult();
        if (iTheMuonSachService.findById(theMuonSachDto.getMaMuonSach()) != null) {
            bindingResult.rejectValue("maMuonSach", "maMuonSach.duplicate", "Mã mượn sách đã tồn tại!");
        }
        theMuonSachDto.validate(theMuonSachDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("hocSinhList", iHocSinhService.findAll());
            model.addAttribute("sachList", iSachService.findAllSachCoTheMuon());
            return "/views/create";
        } else {
            TheMuonSach theMuonSach = new TheMuonSach();
            BeanUtils.copyProperties(theMuonSachDto, theMuonSach);
            HocSinh hocSinh = new HocSinh();
            Sach sach = new Sach();
            hocSinh.setMaHocSinh(theMuonSachDto.getHocSinhDto().getMaHocSinh());
            sach.setMaSach(theMuonSachDto.getSachDto().getMaSach());
            theMuonSach.setHocSinh(hocSinh);
            theMuonSach.setSach(sach);
            iTheMuonSachService.save(theMuonSach);
            iSachService.updateSoLuongSach(sach.getMaSach());
            model.addAttribute("msg", "Thêm mới thành công");
            return "redirect:/index";
        }
    }

    @GetMapping(value = "/update")
    public String goUpdate(Model model, @RequestParam String maSach) throws Exception {
        Sach sach = iSachService.findById(maSach);
        if (sach != null) {
            SachDto sachDto = new SachDto();
            BeanUtils.copyProperties(sach, sachDto);
            sachDto.setSoLuong(String.valueOf(sach.getSoLuong()));
            model.addAttribute("sachDto", sachDto);
            return "views/update";
        } else {
            throw new Exception();
        }
    }

    @PostMapping(value = "/update")
    public String update(@Valid @ModelAttribute SachDto sachDto,
                         BindingResult bindingResult,
                         Model model) {
        sachDto.validate(sachDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "views/update";
        } else {
            Sach sach = new Sach();
            BeanUtils.copyProperties(sachDto, sach);
            Set<TheMuonSach> theMuonSachSet = new HashSet<>();
            sach.setSoLuong(Integer.parseInt(sachDto.getSoLuong()));

            ///Chú ý lỗi A collection with cascade=”all-delete-orphan” was no longer referenced by
            //Tạo 1 Set rồi set vào cho đối tượng là được
            sach.setTheMuonSachSet(theMuonSachSet);
            iSachService.save(sach);
            model.addAttribute("msg", "Sửa thành công");
            return "redirect:/index";
        }
    }


    @GetMapping(value = "/detail/{maSach}")
    public String goDetail(Model model, @PathVariable String maSach) throws Exception {
        Sach sach = iSachService.findById(maSach);
        if (sach != null) {
            model.addAttribute("sach", sach);
            return "views/detail";
        } else {
            throw new Exception();
        }
    }

    @GetMapping(value = "/delete/{maSach}")
    public String goDelete(Model model, @PathVariable String maSach) throws Exception {
        Sach sach = iSachService.findById(maSach);
        if (sach != null) {
            model.addAttribute("sach", sach);
            return "views/delete";
        } else {
            throw new Exception();
        }
    }

    @PostMapping(value = "/delete")
    public String delete(@ModelAttribute Sach sach) {
        iSachService.deleteById(sach.getMaSach());
        return "redirect:/index";
    }

    @GetMapping(value = "/search")
    public String search(Model model, @RequestParam Optional<String> tenSach,
                         @RequestParam Optional<String> tacGia,
                         @RequestParam Optional<String> soLuong, @PageableDefault(value = 2) Pageable pageable) {
        String tenSachThuc = tenSach.orElse("");
        String tacGiaThuc = tacGia.orElse("");
        String soLuongThuc = soLuong.orElse("");
        Page<Sach> sachPage = iSachService.findAllByTenSachAndTacGiaAndSoLuong(tenSachThuc, tacGiaThuc, soLuongThuc, pageable);
        model.addAttribute("sachPage", sachPage);
        return "views/list";
    }

    @GetMapping(value = "/remove")
    public String goRemove(Model model) {
        List<TheMuonSach> theMuonSachList = iTheMuonSachService.findAll();
        model.addAttribute("theMuonSachList", theMuonSachList);
        return "views/remove";
    }

    @PostMapping(value = "/remove")
    public String remove(@RequestParam TheMuonSach theMuonSach) throws Exception {
        TheMuonSach theMuonSach1 =  iTheMuonSachService.findById(theMuonSach.getMaMuonSach());
        Sach sach = iSachService.findById(theMuonSach.getSach().getMaSach());
        if (theMuonSach1 == null | sach == null) {
            throw new Exception();
        }
        sach.setSoLuong(sach.getSoLuong()+1);
        iTheMuonSachService.removeByMa(theMuonSach.getMaMuonSach());
        return "redirect:/index";
    }

    @ExceptionHandler(Exception.class)
    public String go404(){
        return "views/404";
    }

}
