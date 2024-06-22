/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories.impl;

import com.tlqt.pojo.Action;
import com.tlqt.repositories.ActionRepository;
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
public class ActionRepositoryImpl implements ActionRepository{

    @Autowired
    LocalSessionFactoryBean factory;
    
    @Override
    public Action getActionById(int id) {
        Session s = factory.getObject().getCurrentSession();
        
        Query q = s.createNamedQuery("Action.findById");
        q.setParameter("id", id);
        
        return (Action) q.getSingleResult();
    }
    
}
