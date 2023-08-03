package com.blogapi.service;

import com.blogapi.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment (long postId ,CommentDto commentDto);

    public List<CommentDto> findCommentByPostId(long postId);

    List<CommentDto> getAllComments();

    void  deleteCommentById(long postId, long id);

    CommentDto getCommentById(long postId, long id);

    CommentDto updateComment(Long PostId, long commmentId, CommentDto commentDto);
}
