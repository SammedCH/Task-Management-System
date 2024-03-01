package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Comment;

public interface CommentService {

	List<Comment> findCommentsByTask(Long taskId);

	Comment saveComment(Comment comment);

}
