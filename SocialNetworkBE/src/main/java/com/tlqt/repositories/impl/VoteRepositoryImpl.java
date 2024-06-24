/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories.impl;

import com.tlqt.repositories.VoteRepository;
import com.tlqt.pojo.Vote;
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
public class VoteRepositoryImpl implements VoteRepository {

    @Autowired
    LocalSessionFactoryBean factory;

    @Override
    public void add(Vote v) {
        Session s = factory.getObject().getCurrentSession();

        s.save(v);
    }

    @Override
    public void update(Vote v) {
        Session s = factory.getObject().getCurrentSession();

        s.update(v);
    }

    @Override
    public Vote getVoteByUserAndQuestionIds(int userId, int questionId) {
        Session s = factory.getObject().getCurrentSession();
        
        Query q = s.createNamedQuery("Vote.findBySurveyQuestionIdAndUserId");
        q.setParameter("surveyQuestionId", questionId);
        q.setParameter("userId", userId);
        
        return (Vote) q.getSingleResult();

    }

    @Override
    public void addOrUpdate(Vote v) {
        Session s = factory.getObject().getCurrentSession();

        s.saveOrUpdate(v);
    }
}
