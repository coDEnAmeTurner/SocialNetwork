/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services;

import com.tlqt.pojo.Post;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
public interface PostService {

    void addPost(Post p);

    List<Post> getPosts();
    
    void update(Post p);
    
    Post getPostById(int id);
    
    void deletePostById(int postId);

    List<Post> getPostsByAuthorId(int authorId);
    
    int countPostsByUserId(int userId);
}
