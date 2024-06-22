/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories.impl;

import com.tlqt.pojo.Survey;
import com.tlqt.repositories.QuestionRepository;
import com.tlqt.pojo.SurveyQuestion;
import java.util.ArrayList;
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
@Repository
@Transactional
public class QuestionRepositoryImpl implements QuestionRepository {

    @Autowired
    LocalSessionFactoryBean factory;
    
    @Override
    public void addQuestion(SurveyQuestion sq) {
        Session s = factory.getObject().getCurrentSession();
        s.save(sq);
    }
    
    @Override
    public List<SurveyQuestion> getSurveyQuestionsByPostId(int postId) {
        System.out.println("postId: " + postId);
        Session s = factory.getObject().getCurrentSession();
        
        Query query = s.createNamedQuery("Survey.findByPostId");
        query.setParameter("postId", postId);
        
        Survey survey = (Survey) query.getSingleResult();
        
        return new ArrayList<SurveyQuestion>(survey.getSurveyQuestionSet());        
    }
    
    @Override
    public SurveyQuestion getSurveyQuestionById(int id) {
        Session s = factory.getObject().getCurrentSession();
        
        Query q = s.createNamedQuery("SurveyQuestion.findById");
        q.setParameter("id", id);
        
        return (SurveyQuestion) q.getSingleResult();
    }
    
    @Override
    public void update(SurveyQuestion sq) {
        Session s = factory.getObject().getCurrentSession();
        
        s.update(sq);
    }

    @Override
    public void delete(SurveyQuestion sq) {
        Session s = factory.getObject().getCurrentSession();
        
        s.delete(sq);
    }
}
