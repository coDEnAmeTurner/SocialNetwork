/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services;

import com.tlqt.pojo.PostImage;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface PostImageService {
    void addPostImage(PostImage pi);
    
    List<PostImage> getPostImagesByPostId(int postId);
}
