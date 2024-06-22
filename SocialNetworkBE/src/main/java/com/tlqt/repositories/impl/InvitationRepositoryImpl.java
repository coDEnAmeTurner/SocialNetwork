/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories.impl;

import com.tlqt.pojo.Invitation;
import com.tlqt.pojo.Post;
import com.tlqt.repositories.InvitationRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Transactional
@Repository
public class InvitationRepositoryImpl implements InvitationRepository {

    @Autowired
    LocalSessionFactoryBean factory;
    
    @Override
    public void addInvitation(Invitation i) {
        Session s = factory.getObject().getCurrentSession();
        
        s.save(i);
    }

    @Override
    public Invitation getInvitationByPostId(int postId) {
        Session s = factory.getObject().getCurrentSession();
        Query query = s.createNamedQuery("Post.findById");
        query.setParameter("id", postId);
        Post p = (Post) query.getSingleResult();
        
        return p.getInvitation();
    }

    @Override
    public void update(Invitation i) {
        Session s = factory.getObject().getCurrentSession();
        
        s.update(i);
    }
    
}
