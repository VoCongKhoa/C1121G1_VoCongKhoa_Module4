package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.models.Post;
import project.services.IAuthorService;
import project.services.IPostService;

import java.util.List;

@RestController
@RequestMapping(value = "/postRestful")
@CrossOrigin
public class PostRestfulController {

    @Autowired
    private IPostService iPostService;

    @Autowired
    private IAuthorService iAuthorService;

//    http://localhost:8080/postRestful
    @GetMapping(value = "")
    public ResponseEntity<Page<Post>> index(@PageableDefault(value = 4) Pageable pageable) {
        Page<Post> postList = iPostService.findAll(pageable);
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

//    http://localhost:8080/postRestful/1/view
    @GetMapping(value = "/{id}/view")
    public ResponseEntity<Post> goView(@PathVariable Integer id){
        Post post = iPostService.findById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Post>> search(@PageableDefault(value = 4) Pageable pageable,@RequestParam String contentSearch){
        return new ResponseEntity<>(iPostService.searchByTitle(contentSearch), HttpStatus.OK);
//        return new ResponseEntity<>(iPostService.searchByContent(contentSearch, pageable), HttpStatus.OK);
    }
}
