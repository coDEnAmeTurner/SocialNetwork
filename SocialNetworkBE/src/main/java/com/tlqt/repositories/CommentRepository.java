/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories;

import com.tlqt.pojo.Comment;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface CommentRepository {

    List<Comment> getCommentsByPostId(int postId);
    
    List<Comment> getCommentsByParentCommentId(int parentCommentId);
    
    void add(Comment c);
    
    Comment getCommentById(int id);
    
    void delete(Comment c);
    
    void update(Comment c);

}
