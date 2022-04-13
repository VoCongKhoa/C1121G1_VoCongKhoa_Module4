package project.controllers;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.models.Cart;
import project.models.Product;
import project.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Controller
@SessionAttributes("cart")
public class ProductController {
    @Autowired
    private IProductService productService;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping("/shop")
    public ModelAndView showShop() {
        ModelAndView modelAndView = new ModelAndView("/shop");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, @ModelAttribute Cart cart, @RequestParam("action") String action, RedirectAttributes redirectAttributes) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error.404";
        }
        Product product = productOptional.get();
        if (action.equals("add")) {
            if (product.getQuantity() == 0) {
                redirectAttributes.addFlashAttribute("msg", "Run out of product, Can not add more this product!");
                return "redirect:/shopping-cart";
            } else if (cart.selectItemInCart(product).getValue() == product.getQuantity()) {
                redirectAttributes.addFlashAttribute("msg", "Maximum product quantity in storage, Can not add more this product!");
                return "redirect:/shopping-cart";
            } else {
                cart.addProduct(product);
                redirectAttributes.addFlashAttribute("msg", "Add product to cart successfully!");
                return "redirect:/shopping-cart";
            }
        }
        if (action.equals("sub")) {
            if (cart.selectItemInCart(product).getValue() == 0) {
                redirectAttributes.addFlashAttribute("msg", "Run out of product, Can not sub more this product!");
            } else {
                cart.selectItemInCart(product).setValue(cart.selectItemInCart(product).getValue() - 1);
            }
            return "redirect:/shopping-cart";
        }
        if (product.getQuantity() == 0) {
            redirectAttributes.addFlashAttribute("msg", "Run out of product, please choose other product!");
            return "redirect:/shop";
        } else {
            if (cart.selectItemInCart(product) == null) {
                cart.addProduct(product);
                redirectAttributes.addFlashAttribute("msg", "Add product to cart successfully!");
                return "redirect:/shop";
            } else {
                if (cart.selectItemInCart(product).getValue() == product.getQuantity()){
                    redirectAttributes.addFlashAttribute("msg", "Maximum product quantity in storage, Can not add more this product!");
                    return "redirect:/shop";
                } else {
                    cart.addProduct(product);
                    return "redirect:/shop";
                }
            }
        }
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error.404";
        }
        Product product = productOptional.get();
        model.addAttribute("product", product);
        return "detail";
    }

    @GetMapping("/delete/{id}")
    public String goDelete(@PathVariable Long id, Model model) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error.404";
        }
        Product product = productOptional.get();
        model.addAttribute("product", product);
        return "delete";
    }

    @PostMapping("/payment")
    public String payment (@SessionAttribute("cart") Cart cart, RedirectAttributes redirectAttributes){
        if (cart.getProducts().isEmpty()){
            redirectAttributes.addFlashAttribute("msg", "There are nothing to pay!");
            return "redirect:/shopping-cart";
        }
        Map<Product, Integer> productList = cart.getProducts();
        for (Map.Entry<Product,Integer> entry : productList.entrySet() ){
            Optional<Product> product = productService.findById(entry.getKey().getId());
            product.get().setQuantity(product.get().getQuantity() - entry.getValue());
            productService.save(product.get());
        }
        redirectAttributes.addFlashAttribute("msg", "Payment successfully!");
        return "redirect:/shop";
    }

}
