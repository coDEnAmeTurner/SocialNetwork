/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;

import com.tlqt.pojo.Post;
import com.tlqt.repositories.PostRepository;
import com.tlqt.services.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepo;
    
    @Override
    public void addPost(Post p) {
        postRepo.addPost(p);
    }

    @Override
    public List<Post> getPosts() {
        return postRepo.getPosts();
    }    

    @Override
    public void update(Post p) {
        
        postRepo.update(p);
    }

    @Override
    public Post getPostById(int id) {
       return this.postRepo.getPostById(id);
    }

    @Override
    public void deletePostById(int postId) {
        this.postRepo.deletePostById(postId);
    }

    @Override
    public List<Post> getPostsByAuthorId(int authorId) {
        return postRepo.getPostsByAuthorId(authorId);
    }
    
    @Override
    public long countPostsByUserId(int userId) {
        return postRepo.countPostsByUserId(userId);
    }

    @Override
    public List<Post> getPostsByCategoryId(int categoryId) {
        return postRepo.getPostsByCategoryId(categoryId);
    }
    
        @Override
    public List<Object[]> countPostsByYear(int startYear, int endYear) {
        return postRepo.countPostsByYear(startYear, endYear);
    }

    @Override
    public List<Object[]> countPostsByMonth(int year) {
        return postRepo.countPostsByMonth(year);
    }

    @Override
    public List<Object[]> countPostsByQuarter(int year) {
        return postRepo.countPostsByQuarter(year);

    }

    @Override
    public List<Post> getAllPostsByDescendingUpdatedAt() {
        return this.postRepo.getAllPostsByDescendingUpdatedAt();
    }
}
