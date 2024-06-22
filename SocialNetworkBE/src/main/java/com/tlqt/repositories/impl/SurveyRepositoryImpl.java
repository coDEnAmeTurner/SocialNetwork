/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories.impl;

import com.tlqt.repositories.SurveyRepository;
import com.tlqt.pojo.Survey;
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
public class SurveyRepositoryImpl implements SurveyRepository {

    @Autowired
    LocalSessionFactoryBean factory;

    @Override
    public void addSurvey(Survey survey) {
        Session s = factory.getObject().getCurrentSession();

        s.save(survey);
    }

    @Override
    public Survey getSurveyByPostId(int postId) {
        Session s = factory.getObject().getCurrentSession();

        Query q = s.createNamedQuery("Survey.findByPostId");
        q.setParameter("postId", postId);

        return (Survey) q.getSingleResult();
    }

}
