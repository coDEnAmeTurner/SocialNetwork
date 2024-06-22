/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services;

import com.tlqt.pojo.Comment;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface CommentService {
    List<Comment> getCommentsByPostId(int postId);
    
    List<Comment> getCommentsByParentCommentId(int parentCommentId);
    
    void add(Comment c);
    
    Comment getCommentById(int commentId);
    
    void delete(Comment c);
    
    void update(Comment c);
}
