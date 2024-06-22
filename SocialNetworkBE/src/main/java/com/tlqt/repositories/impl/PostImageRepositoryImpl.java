
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories.impl;

import com.tlqt.pojo.Post;
import com.tlqt.pojo.PostImage;
import com.tlqt.repositories.PostImageRepository;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class PostImageRepositoryImpl implements PostImageRepository{
    
    @Autowired
    LocalSessionFactoryBean factory;
    
    public void addPostImage(PostImage pi) {
        Session s = factory.getObject().getCurrentSession();
        
        s.save(pi);
    }

    @Override
    public List<PostImage> getPostImagesByPostId(int postId) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Post.findById");
        q.setParameter("id", postId);
        Post p = (Post) q.getSingleResult();
        
        return new ArrayList<PostImage> (p.getPostImageSet());
    }
}
