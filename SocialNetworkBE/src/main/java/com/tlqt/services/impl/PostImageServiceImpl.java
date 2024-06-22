/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tlqt.pojo.PostImage;
import com.tlqt.repositories.PostImageRepository;
import com.tlqt.services.PostImageService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class PostImageServiceImpl implements PostImageService{
    
    @Autowired
    PostImageRepository piRepo;
    
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public void addPostImage(PostImage pi) {
        if (!pi.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(pi.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                pi.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(PostImageServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        piRepo.addPostImage(pi);
    }

    @Override
    public List<PostImage> getPostImagesByPostId(int postId) {
        return this.piRepo.getPostImagesByPostId(postId);
    }
    
    
}
