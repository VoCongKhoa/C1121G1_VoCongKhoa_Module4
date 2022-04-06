package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.models.Post;
import project.services.IAuthorService;
import project.services.IPostService;

import java.util.List;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private IPostService iPostService;

    @Autowired
    private IAuthorService iAuthorService;

    @GetMapping(value = "")
    public String index(Model model, @RequestParam int categoryId) {
        List<Post> postList = iPostService.findAllByCategory(categoryId);
        model.addAttribute("postList", postList);
        return "/views/index";
    }
}
