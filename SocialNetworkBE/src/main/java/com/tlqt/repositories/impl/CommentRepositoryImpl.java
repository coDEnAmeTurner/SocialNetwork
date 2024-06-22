/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories.impl;

import com.tlqt.pojo.Comment;
import com.tlqt.repositories.CommentRepository;
import java.util.List;
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
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    LocalSessionFactoryBean factory;

    @Override
    public List<Comment> getCommentsByPostId(int postId) {
        Session s = factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Comment.class);
        Root cRoot = q.from(Comment.class);
        q.select(cRoot);
        q.where(b.equal(cRoot.get("postId"), postId));
        Query query = s.createQuery(q);

        return query.getResultList();
    }

    @Override
    public List<Comment> getCommentsByParentCommentId(int parentCommentId) {

        Session s = factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Comment.class);
        Root cRoot = q.from(Comment.class);
        q.select(cRoot);
        q.where(b.equal(cRoot.get("parentCommentId"), parentCommentId));
        Query query = s.createQuery(q);

        return query.getResultList();
    }

    @Override
    public void add(Comment c) {
        Session s = factory.getObject().getCurrentSession();

        s.save(c);
    }

    @Override
    public Comment getCommentById(int id) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Comment.findById");
        q.setParameter("id", id);

        return (Comment) q.getSingleResult();
    }

    @Override
    public void delete(Comment c) {
        Session s = factory.getObject().getCurrentSession();

        s.delete(c);
    }

    @Override
    public void update(Comment c) {
        Session s = factory.getObject().getCurrentSession();

        s.update(c);
    }

}
