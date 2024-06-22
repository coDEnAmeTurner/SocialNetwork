/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories.impl;

import com.tlqt.pojo.ActionPost;
import com.tlqt.repositories.ActionPostRepository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class ActionPostRepositoryImpl implements ActionPostRepository {

    @Autowired
    LocalSessionFactoryBean factory;

    @Override
    public void update(ActionPost ap) {
        Session s = factory.getObject().getCurrentSession();

        s.update(ap);
    }

    @Override
    public void add(ActionPost ap) {
        Session s = factory.getObject().getCurrentSession();

        s.save(ap);
    }

    @Override
    public void delete(ActionPost ap) {
        Session s = factory.getObject().getCurrentSession();

        s.delete(ap);
    }

    @Override
    public ActionPost getActionPostByIds(int postId, int userId) {
        Session s = factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(ActionPost.class);
        Root apRoot = q.from(ActionPost.class);
        q.select(apRoot);
        q.where(b.and(b.equal(apRoot.get("post"), postId)), b.equal(apRoot.get("user"), userId));
        Query query = s.createQuery(q);

        return (ActionPost) query.getSingleResult();
    }

}
