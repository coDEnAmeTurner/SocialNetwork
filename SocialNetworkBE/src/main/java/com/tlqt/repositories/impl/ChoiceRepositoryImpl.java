/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories.impl;

import com.tlqt.pojo.Choice;
import com.tlqt.repositories.ChoiceRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class ChoiceRepositoryImpl implements ChoiceRepository {

    @Autowired
    LocalSessionFactoryBean factory;

    @Override
    public void addChoice(Choice c) {

        Session s = factory.getObject().getCurrentSession();

        s.save(c);
    }

    @Override
    public List<Choice> getChoicesBySurveyQuestionId(int sqId) {
        Session s = factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Choice.class);
        Root<Choice> cRoot = q.from(Choice.class);
        q.select(cRoot);
        q.where(b.equal(cRoot.get("surveyQuestionId"), sqId));
        Query query = s.createQuery(q);

        return query.getResultList();
    }

    @Override
    public Choice getChoiceById(int id) {
        Session s = factory.getObject().getCurrentSession();

        Query q = s.createNamedQuery("Choice.findById");
        q.setParameter("id", id);

        return (Choice) q.getSingleResult();
    }

    @Override
    public void update(Choice c) {
        Session s = factory.getObject().getCurrentSession();

        s.update(c);
    }

    @Override
    public void delete(Choice c) {
        Session s = factory.getObject().getCurrentSession();

        s.delete(c);
    }
}
