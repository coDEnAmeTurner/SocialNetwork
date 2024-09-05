/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories.impl;

import com.tlqt.pojo.Choice;
import com.tlqt.pojo.Survey;
import com.tlqt.repositories.QuestionRepository;
import com.tlqt.pojo.SurveyQuestion;
import com.tlqt.pojo.Vote;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
    
    public List<Object[]> getQuestionStatsBySurveyId(int surveyId) {
        Session s = factory.getObject().getCurrentSession();
        
        CriteriaBuilder b = s.getCriteriaBuilder();

        //calculate percentage
        CriteriaQuery q = b.createQuery(Object[].class);
        Root qRoot = q.from(SurveyQuestion.class);
        Root cRoot = q.from(Choice.class);
        Root vRoot = q.from(Vote.class);
        Root sRoot = q.from(Survey.class);
        
        q.multiselect(
                sRoot.get("postId"),
                qRoot.get("id"),
                qRoot.get("content"),
                cRoot.get("id"),
                cRoot.get("content"),
                b.count(vRoot.get("id"))
        );
        
        Predicate p1 = b.equal(qRoot.get("id"), cRoot.get("surveyQuestionId"));
        Predicate p2 = b.equal(cRoot.get("id"), vRoot.get("choiceId"));
        Predicate p3 = b.equal(qRoot.get("surveyId"), sRoot.get("postId"));
        Predicate p4 = b.equal(sRoot.get("surveyId"), surveyId);
        
        q.where(b.and(p1, p2, p3));
        q.groupBy(
                sRoot.get("postId"),
                qRoot.get("id"),
                qRoot.get("content"),
                cRoot.get("id"),
                cRoot.get("content")
        );
        
        Query query = s.createQuery(q);
        
        return query.getResultList();
    }
    
    @Override
    public List<Object[]> countVotesPerChoice(int questionId) {
        Session s = this.factory.getObject().getCurrentSession();
        
        SurveyQuestion sq = getSurveyQuestionById(questionId);
        
        Query q = s.createQuery("SELECT sq.id, c.id, c.content, COUNT(v.choiceId.id)\n"
                + "FROM Choice c \n"
                + "LEFT JOIN Vote v ON c.id = v.choiceId \n"
                + "INNER JOIN SurveyQuestion sq ON c.surveyQuestionId = sq.id\n"
                + "WHERE sq.id =: questionId\n"
                + "GROUP BY c.id, c.content, sq.id");
        q.setParameter("questionId", questionId);
        
        return q.getResultList();
    }
}
