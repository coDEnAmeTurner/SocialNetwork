/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.repositories;

import com.tlqt.pojo.Post;
import com.tlqt.pojo.PostImage;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface PostImageRepository {
    void addPostImage(PostImage pi);
    
    List<PostImage> getPostImagesByPostId(int postId);
}
