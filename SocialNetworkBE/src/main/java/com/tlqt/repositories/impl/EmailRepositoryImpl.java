/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories.impl;

import com.tlqt.pojo.Email;
import com.tlqt.pojo.Invitation;
import com.tlqt.pojo.Post;
import com.tlqt.pojo.User;
import com.tlqt.repositories.EmailRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
public class EmailRepositoryImpl implements EmailRepository {

    @Autowired
    LocalSessionFactoryBean factory;

    @Override
    public void add(Email e) {
        Session s = factory.getObject().getCurrentSession();

        s.save(e);
    }

    @Override
    public List<Object[]> getInvitationIdsByEmail(String email) {
        Session s = factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Object[].class);
        Root eRoot = q.from(Email.class);
        Root pRoot = q.from(Post.class);
        Root uRoot = q.from(User.class);

        q.multiselect(
                pRoot.get("id"),
                pRoot.get("title"),
                uRoot.get("username"),
                uRoot.get("avatar")
        );
        Predicate p1 = b.like(eRoot.get("email"), email);
        Predicate p2 = b.equal(eRoot.get("invitationId"), pRoot.get("id"));
        Predicate p3 = b.equal(uRoot.get("id"), pRoot.get("userId"));
        q.where(b.and(p1, p2, p3));

        Query query = s.createQuery(q);

        return query.getResultList();
    }

    @Override
    public List<Email> getEmailsByInviId(int inviId) {
        Session s = factory.getObject().getCurrentSession();

        Invitation i = (Invitation) s.createNamedQuery("Invitation.findByPostId").setParameter("postId", inviId).getSingleResult();

        Query q = s.createNamedQuery("Email.findByInviId");
        q.setParameter("id", i);

        return q.getResultList();
    }

    @Override
    public void delete(Email e) {
        Session s = factory.getObject().getCurrentSession();

        s.delete(e);
    }

    @Override
    public Email getEmailById(int id) {
        Session s = factory.getObject().getCurrentSession();

        Query q = s.createNamedQuery("Email.findById");
        q.setParameter("id", id);

        return (Email) q.getSingleResult();
    }

}
