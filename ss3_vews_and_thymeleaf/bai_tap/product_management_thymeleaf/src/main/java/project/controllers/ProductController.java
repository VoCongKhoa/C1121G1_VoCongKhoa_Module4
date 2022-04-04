package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.models.Product;
import project.services.IProductService;

import java.util.List;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @GetMapping(value = "",  produces = "application/json; charset=UTF-8")
    public String index(Model model) {
        List<Product> productList = iProductService.findAll();
        model.addAttribute("productList", productList);
        return "/index";
    }

    @GetMapping(value = "/create",  produces = "application/json; charset=UTF-8")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "/create";
    }

    @PostMapping(value = "/save",  produces = "application/json; charset=UTF-8")
    public String save(Product product, RedirectAttributes redirectAttributes) {
        iProductService.save(product);
        redirectAttributes.addFlashAttribute("success", "Add new successfully");
        return "redirect:/product/";
    }

    @GetMapping(value = "/{id}/edit",  produces = "application/json; charset=UTF-8")
    public String goUpdateForm(Model model, @PathVariable int id){
        model.addAttribute("product", iProductService.findById(id));
        return "/edit";
    }

    @PostMapping(value = "/update",  produces = "application/json; charset=UTF-8")
    public String update(RedirectAttributes redirectAttributes, @ModelAttribute Product product){
        iProductService.update(product.getId(),product);
        redirectAttributes.addFlashAttribute("success", "Edit product successfully");
        return "redirect:/product/";
    }

    @GetMapping(value = "/{id}/view",  produces = "application/json; charset=UTF-8")
    public String goView(Model model, @PathVariable int id){
        model.addAttribute("product", iProductService.findById(id));
        return "/view";
    }

    @GetMapping(value = "/{id}/delete",  produces = "application/json; charset=UTF-8")
    public String goDeleteForm(Model model, @PathVariable int id){
        model.addAttribute("product", iProductService.findById(id));
        return "/delete";
    }

    @PostMapping(value = "/delete",  produces = "application/json; charset=UTF-8")
    public String delete(RedirectAttributes redirectAttributes, @ModelAttribute Product product){
        iProductService.remove(product.getId());
        redirectAttributes.addFlashAttribute("success", "Delete product successfully");
        return "redirect:/product/";
    }

    @GetMapping(value = "/search",  produces = "application/json; charset=UTF-8")
    public String search(Model model, @RequestParam String nameSearch){
        model.addAttribute("productList",iProductService.searchByName(nameSearch));
        return "/index";
    }
}
