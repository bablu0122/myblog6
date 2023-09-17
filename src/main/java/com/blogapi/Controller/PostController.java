package com.blogapi.Controller;

import com.blogapi.payload.PostDto;
import com.blogapi.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;


   // Generate Constructor
    public PostController (PostService postService) {

        this.postService = postService;
    }

    //http:localhost:8080/api/posts
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult result) {//status code=201
      if(result.hasErrors()){
          return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
      }
        PostDto savedDto=postService.createPost(postDto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }


    //http://localhost:8080/api/posts/1
    //1 is id value
    //http://localhost:8080/api/posts/?id=1  called as Query parameter(use @RequestParam)
    //http://localhost:8080/api/posts/1   called as Path parameter (use @PathVariable)
    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id) {//status code =200
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    //http://localhost:8080/api/posts=1&pageSize=3

    //can you sort by any object
   // http://localhost:8080/api/posts?pageNo=0&pageSize=3&sortBy=title
    // http://localhost:8080/api/posts?pageNo=0&pageSize=3&sortBy=id
    //http://localhost:8080/api/posts?pageNo=0&pageSize=10&sortBy=content&sortDir=desc
    @GetMapping
    public List<PostDto> getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir

            )
    {
        List<PostDto> postDtos = postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
        return postDtos;
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<String>deletePost(@PathVariable("id") long id) {//status code =200
        postService.deletePost(id);
        return new ResponseEntity<>("Post is deleted!!",HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") long id,@RequestBody PostDto postDto) {//status code =200
        PostDto dto = postService.updatePost(id, postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
