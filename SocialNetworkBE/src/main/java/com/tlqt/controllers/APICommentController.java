/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.controllers;

import com.tlqt.pojo.Comment;
import com.tlqt.pojo.Post;
import com.tlqt.pojo.User;
import com.tlqt.services.CommentService;
import com.tlqt.services.UserService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
//mapping chung cho ca controller
@RequestMapping("/api")
@CrossOrigin
public class APICommentController {

    @Autowired
    CommentService commentService;
    
    @Autowired
    UserService userService;

    @GetMapping(path="/comments/{commentId}/get-author/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getAuthor(@PathVariable(value = "commentId") int commentId) {
        User author = userService.getUserByCommentId(commentId);
        
        return new ResponseEntity<>(author, HttpStatus.OK);
    }
    
    @DeleteMapping(path="/comments/{commentId}/")
    public ResponseEntity<Object> delete(@PathVariable(value = "commentId") int commentId) {
        Comment c = commentService.getCommentById(commentId);
        commentService.delete(c);
        
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping(path="/comments/{commentId}/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable(value = "commentId") int commentId, @RequestBody Map<String, String> params) {
        Comment c = commentService.getCommentById(commentId);
        c.setContent(params.get("content"));
        commentService.update(c);
        return new ResponseEntity(HttpStatus.OK);
    }
}
