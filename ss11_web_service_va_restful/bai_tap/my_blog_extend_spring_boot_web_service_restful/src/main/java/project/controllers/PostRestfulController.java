package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.models.Category;
import project.models.Post;
import project.models.PostCategory;
import project.services.IAuthorService;
import project.services.IPostService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/postRestful")
public class PostRestfulController {

    @Autowired
    private IPostService iPostService;

    @Autowired
    private IAuthorService iAuthorService;

//    http://localhost:8080/postRestful
    @GetMapping(value = "")
    public ResponseEntity<Page<Post>> index(@PageableDefault(value = 2) Pageable pageable,
                                            @RequestParam Optional<String> search,
                                            @RequestParam Optional<String> sort) {
        String valueSearch;
        String valueSort;
        Page<Post> postList;
        valueSearch = search.orElse("");
        if (sort.isPresent()) {
            valueSort = sort.get();
            if (valueSort.equals("postDateModified")) {
                postList = iPostService.findAllWithSort(pageable);
            } else {
                postList = iPostService.findAllWithSearch(pageable, valueSearch);
            }
        } else {
            valueSort = "";
            postList = iPostService.findAllWithSearch(pageable, valueSearch);
        }
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

//    http://localhost:8080/postRestful/1/view
    @GetMapping(value = "/{id}/view")
    public ResponseEntity<Post> goView(@PathVariable Integer id){
        Post post = iPostService.findById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
