/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories.impl;

import com.tlqt.pojo.ContentType;
import com.tlqt.pojo.Post;
import com.tlqt.repositories.ContentTypeRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
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
public class ContentTypeRepositoryImpl implements ContentTypeRepository{
    @Autowired
    LocalSessionFactoryBean factory;
    
    @Override
    public List<ContentType> getContentTypes() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ContentType.findAll");
        
        return q.getResultList();
    }

    @Override
    public ContentType getContentTypeById(int id) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ContentType.findById");
        q.setParameter("id", id);
        
        System.out.println((ContentType) q.getSingleResult());
        
        return (ContentType) q.getSingleResult();
    }

    @Override
    public ContentType getContentTypeByPostId(int postId) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Post.findById");
        q.setParameter("id", postId);
        Post p = (Post) q.getSingleResult();
        
        return p.getContentTypeId();
    }
    
}
