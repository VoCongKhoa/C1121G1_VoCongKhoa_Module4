package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.models.Post;
import project.services.IPostService;

import java.util.List;

@Controller
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private IPostService iPostService;

    @GetMapping(value = "",  produces = "application/json; charset=UTF-8")
    public String index(Model model) {
        List<Post> postList = iPostService.findAll();
            model.addAttribute("postList", postList);
        return "/index";
    }

    @GetMapping(value = "/create",  produces = "application/json; charset=UTF-8")
    public String create(Model model) {
        model.addAttribute("post", new Post());
        return "/create";
    }

    @PostMapping(value = "/save",  produces = "application/json; charset=UTF-8")
    public String save(@ModelAttribute Post post, RedirectAttributes redirectAttributes) {
        iPostService.save(post);
        redirectAttributes.addFlashAttribute("success", "Add new successfully");
        return "redirect:/post/";
    }

    @GetMapping(value = "/{id}/edit",  produces = "application/json; charset=UTF-8")
    public String goUpdateForm(Model model, @PathVariable Long id){
        model.addAttribute("post", iPostService.findById(id));
        return "/edit";
    }

    @PostMapping(value = "/update",  produces = "application/json; charset=UTF-8")
    public String update(RedirectAttributes redirectAttributes, @ModelAttribute Post post){
        iPostService.update(post);
        redirectAttributes.addFlashAttribute("success", "Edit post successfully");
        return "redirect:/post/";
    }

    @GetMapping(value = "/{id}/view",  produces = "application/json; charset=UTF-8")
    public String goView(Model model, @PathVariable Long id){
        model.addAttribute("post", iPostService.findById(id));
        return "/view";
    }

    @GetMapping(value = "/{id}/delete",  produces = "application/json; charset=UTF-8")
    public String goDeleteForm(Model model, @PathVariable Long id){
        model.addAttribute("post", iPostService.findById(id));
        return "/delete";
    }

    @PostMapping(value = "/delete",  produces = "application/json; charset=UTF-8")
    public String delete(RedirectAttributes redirectAttributes, @ModelAttribute Post post){
        iPostService.remove(post.getId());
        redirectAttributes.addFlashAttribute("success", "Delete post successfully");
        return "redirect:/post/";
    }

    @GetMapping(value = "/search",  produces = "application/json; charset=UTF-8")
    public String search(Model model, @RequestParam String nameSearch){
        model.addAttribute("postList", iPostService.searchByName(nameSearch));
        return "/index";
    }
}
