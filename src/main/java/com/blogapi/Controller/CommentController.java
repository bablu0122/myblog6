package com.blogapi.Controller;

import com.blogapi.payload.CommentDto;
import com.blogapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    //http://localhost:8080/api
    //http://localhost:8080/api/posts/1/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @RequestBody CommentDto commentDto) {
        CommentDto dto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(dto , HttpStatus.CREATED);


    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> findByPostId(@PathVariable(value = "postId") long postId){
        List<CommentDto> dtos = commentService.findCommentByPostId(postId);
        return dtos;
    }


    //http://localhost:8080/api/posts/1/comments/1
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(value = "postId") long postId,@PathVariable (value = "id") long id){
         commentService.deleteCommentById(postId,id);
        return new ResponseEntity<>("Comment id Deleted",HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId,@PathVariable (value = "id") long id){
        CommentDto dto = commentService.getCommentById(postId,id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


    //http://localhost:8080/api/posts/1/comments/1
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") long postId,
                                                     @PathVariable (value = "id") long commmentId,
                                                      @RequestBody CommentDto commentDto){
        CommentDto updateComment = commentService.updateComment(postId,commmentId,commentDto);
        return new ResponseEntity<>(updateComment,HttpStatus.OK);
    }
}

