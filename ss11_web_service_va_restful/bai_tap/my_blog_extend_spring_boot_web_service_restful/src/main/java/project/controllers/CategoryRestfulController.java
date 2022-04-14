package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.models.Category;
import project.models.Post;
import project.services.IAuthorService;
import project.services.ICategoryService;
import project.services.IPostService;

import java.util.List;

@RestController
@RequestMapping(value = "/categoryRestful")
public class CategoryRestfulController {

    @Autowired
    private IPostService iPostService;

    @Autowired
    private ICategoryService iCategoryService;

//    http://localhost:8080/categoryRestful/list
    @GetMapping(value = "/list")
    public ResponseEntity<List<Category>> list() {
        List<Category> categoryList = iCategoryService.findAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

//    http://localhost:8080/categoryRestful/category?categoryId=2
    @GetMapping(value = "/category")
    public ResponseEntity<List<Post>> index(@RequestParam int categoryId) {
        List<Post> postList = iPostService.findAllByCategory(categoryId);
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }
}
