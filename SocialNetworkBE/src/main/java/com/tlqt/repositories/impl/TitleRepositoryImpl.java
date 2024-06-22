/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories.impl;

import com.tlqt.repositories.TitleRepository;
import com.tlqt.repositories.DegreeRepository;
import com.tlqt.pojo.Degree;
import com.tlqt.pojo.Title;
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
public class TitleRepositoryImpl implements TitleRepository {
    @Autowired
    LocalSessionFactoryBean factory;
    
    @Override
    public List<Title> getTitles() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Title.findAll");
        return q.getResultList();
    }

    @Override
    public Title getTitleById(int titleId) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Title.findById");
        q.setParameter("id", titleId);
        return (Title) q.getSingleResult();
    }
}
