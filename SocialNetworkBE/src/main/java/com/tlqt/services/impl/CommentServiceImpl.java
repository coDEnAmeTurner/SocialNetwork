/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;

import com.tlqt.pojo.Comment;
import com.tlqt.repositories.CommentRepository;
import com.tlqt.services.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentRepository commentRepository;
    
    @Override
    public List<Comment> getCommentsByPostId(int postId) {
        return commentRepository.getCommentsByPostId(postId);
    }

    @Override
    public List<Comment> getCommentsByParentCommentId(int parentCommentId) {
        return commentRepository.getCommentsByParentCommentId(parentCommentId);
    }

    @Override
    public void add(Comment c) {
        commentRepository.add(c);
    }

    @Override
    public Comment getCommentById(int commentId) {
        return commentRepository.getCommentById(commentId);
    }

    @Override
    public void delete(Comment c) {
        commentRepository.delete(c);
    }

    @Override
    public void update(Comment c) {
        commentRepository.update(c);
    }
    
}
