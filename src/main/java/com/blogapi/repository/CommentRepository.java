package com.blogapi.repository;

import com.blogapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment , Long> {

    List<Comment> findByPostId(long id);

    //List<Comment> findByPostId(long postId);
//    boolean existByEmail(String email);
//    List<Comment>findByName(String name);
}
