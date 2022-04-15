package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.models.Post;
import project.models.PostCategory;
import project.services.IAuthorService;
import project.services.IPostService;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private IPostService iPostService;

    @Autowired
    private IAuthorService iAuthorService;

    //ONLY PAGING
//    @GetMapping(value = "")
//    public String listPost(
//            Model model,
//            @RequestParam("page") Optional<Integer> page,
//            @RequestParam("size") Optional<Integer> size,
//            Optional<String> contentSearch) {
//
//            int currentPage = page.orElse(1);
//            int pageSize = size.orElse(2);
//
//            Page<Post> postList = iPostService.findAll(PageRequest.of(currentPage - 1, pageSize));
//
//            model.addAttribute("postList", postList);
//
//            int totalPages = postList.getTotalPages();
//            if (totalPages > 0) {
//                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
//                        .boxed()
//                        .collect(Collectors.toList());
//                model.addAttribute("pageNumbers", pageNumbers);
//            }
//        return "/views/index";
//    }

//    @GetMapping(value = "/sort")
//    public String index(Model model,
//                        @PageableDefault(value = 2) Pageable pageable,
//                        @RequestParam Optional<String> sort) {
//        String valueSort = null;
//        Page<Post> postList = null;
//        if (sort.isPresent()){
//            valueSort = sort.get();
//            if (valueSort.equals("postDateModified")){
//                postList = iPostService.findAllWithSort(pageable);
//            }
//        } else {
//            postList = iPostService.findAll(pageable);
//        }
//        model.addAttribute("postList", postList);
//        model.addAttribute("sort",valueSort);
//        return "/views/index";
//    }


    @GetMapping(value = "")
    public String index(Model model,
                        @PageableDefault(value = 2) Pageable pageable,
                        @RequestParam Optional<String> search,
                        @RequestParam Optional<String> sort) {
        String valueSearch;
        String valueSort;
        Page<Post> postList = null;
        if (search.isPresent()){
            valueSearch = search.get();
        } else {
            valueSearch = "";
        }
        if (sort.isPresent()){
            valueSort = sort.get();
            if (valueSort.equals("postDateModified")){
                postList = iPostService.findAllWithSort(pageable);
            } else {
                postList = iPostService.findAllWithSearch(pageable, valueSearch);
            }
        } else {
            valueSort = "";
            postList = iPostService.findAllWithSearch(pageable, valueSearch);
        }
        model.addAttribute("postList", postList);
        model.addAttribute("search",valueSearch);
        model.addAttribute("sort",valueSort);
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
        model.addAttribute("authorList",iAuthorService.findAll());
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
        Post post = iPostService.findById(id);
        List<PostCategory> postCategoryList = post.getPostCategoryList();
        Set<String> categoryNameList = new TreeSet<>();
        for (PostCategory p: postCategoryList) {
            categoryNameList.add(p.getCategory().getCategoryName());
        }
        String categoryNameString = categoryNameList.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining(","));
        model.addAttribute("post", post);
        model.addAttribute("categoryNameString", categoryNameString);
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

//    @GetMapping(value = "/search")
//    public String search(Model model, @RequestParam String contentSearch){
//        model.addAttribute("postList", iPostService.searchByContent(contentSearch));
//        return "/views/index";
//    }
}
