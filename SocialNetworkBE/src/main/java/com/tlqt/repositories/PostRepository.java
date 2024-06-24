/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories;

import com.tlqt.pojo.Post;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
public interface PostRepository{
    void addPost(Post p);
    List<Post> getPosts();
    void update(Post p);
    Post getPostById(int id);
    void deletePostById(int postId);
    List<Post> getPostsByAuthorId(int authorId);
    long countPostsByUserId(int userId);
}
