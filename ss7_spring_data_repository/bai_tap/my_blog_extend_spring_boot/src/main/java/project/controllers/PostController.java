package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.models.Post;
import project.services.IAuthorService;
import project.services.IPostService;

import java.util.List;

@Controller
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private IPostService iPostService;

    @Autowired
    private IAuthorService iAuthorService;

    @GetMapping(value = "")
    public String index(Model model) {
        List<Post> postList = iPostService.findAll();
            model.addAttribute("postList", postList);
        return "/views/index";
    }

    @GetMapping(value = "/create")
    public String goCreate(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("authorList",iAuthorService.findAll());
        return "/views/create";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute Post post, RedirectAttributes redirectAttributes) {
        iPostService.save(post);
        redirectAttributes.addFlashAttribute("success", "Add new successfully");
        return "redirect:/post/";
    }

    @GetMapping(value = "/{id}/edit")
    public String goUpdateForm(Model model, @PathVariable Integer id){
        model.addAttribute("post", iPostService.findById(id));
        return "/views/edit";
    }

    @PostMapping(value = "/update")
    public String update(RedirectAttributes redirectAttributes, @ModelAttribute Post post){
        iPostService.update(post);
        redirectAttributes.addFlashAttribute("success", "Edit post successfully");
        return "redirect:/post/";
    }

    @GetMapping(value = "/{id}/view")
    public String goView(Model model, @PathVariable Integer id){
        model.addAttribute("post", iPostService.findById(id));
        return "/views/view";
    }

    @GetMapping(value = "/{id}/delete")
    public String goDeleteForm(Model model, @PathVariable Integer id){
        model.addAttribute("post", iPostService.findById(id));
        model.addAttribute("authorList",iAuthorService.findAll());
        return "/views/delete";
    }

    @PostMapping(value = "/delete")
    public String delete(RedirectAttributes redirectAttributes, @ModelAttribute Post post){
        iPostService.remove(post.getPostId());
        redirectAttributes.addFlashAttribute("success", "Delete post successfully");
        return "redirect:/post/";
    }

    @GetMapping(value = "/search")
    public String search(Model model, @RequestParam String contentSearch){
        model.addAttribute("postList", iPostService.searchByContent(contentSearch));
        return "/views/index";
    }
}
