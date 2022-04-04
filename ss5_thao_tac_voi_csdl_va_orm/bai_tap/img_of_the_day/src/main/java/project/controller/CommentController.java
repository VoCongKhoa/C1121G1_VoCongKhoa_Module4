package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.models.Comment;
import project.services.ICommentService;

import java.time.LocalDate;
import java.util.List;


@RequestMapping(value = "/comment")
@Controller
public class CommentController {

    // Singleton
    @Autowired
    private ICommentService iCommentService;

    @GetMapping(value = "/")
    public String goListImagePost(Model model) {
        Comment comment = new Comment();
        model.addAttribute("commentObj",comment);
        List<Comment> commentList = iCommentService.findAll();
        model.addAttribute("commentList",commentList);
        return "home";
    }

    @PostMapping(value = "create")
    public String create(@ModelAttribute Comment commentObj, Model model){
        commentObj.setCommentDate(String.valueOf(LocalDate.now()));
        commentObj.setCommentLike(0);
        iCommentService.save(commentObj);
        return "redirect:/comment/";
    }

    @GetMapping(value = "/like")
    public String like(@RequestParam int idLike) {
        Comment comment = iCommentService.findById(idLike);
        iCommentService.like(comment);
        return "redirect:/comment/";
    }

}
