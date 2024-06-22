/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories.impl;

import com.tlqt.pojo.Post;
import com.tlqt.pojo.User;
import com.tlqt.repositories.PostRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class PostRepositoryImpl implements PostRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addPost(Post p) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(p);
    }

    @Override
    public List<Post> getPosts() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Post.findAll");

        return q.getResultList();
    }

    @Override
    public void update(Post p) {
        Session s = factory.getObject().getCurrentSession();

        s.update(p);
    }

    @Override
    public Post getPostById(int id) {
        Session s = factory.getObject().getCurrentSession();
        
        Query q = s.createNamedQuery("Post.findById");
        q.setParameter("id", id);
        
        return (Post) q.getSingleResult();
    }

    @Override
    public void deletePostById(int postId) {
        Session s = factory.getObject().getCurrentSession();
        
        Query q = s.createNamedQuery("Post.findById");
        q.setParameter("id", postId);
        
        Post p = (Post) q.getSingleResult();
        
        s.delete(p);
    }

    @Override
    public List<Post> getPostsByAuthorId(int authorId) {
        Session s = factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Post.class);
        Root r = q.from(Post.class);
        q.select(r);
        q.where(b.equal(r.get("userId"), authorId));
        Query query = s.createQuery(q);
        
        return query.getResultList();
    }

    @Override
    public int countPostsByUserId(int userId) {
        Session s = factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Object[].class);
        Root uRoot = q.from(User.class);
        Root pRoot = q.from(Post.class);
        q.multiselect(b.count(pRoot.get("id")));
        q.groupBy(uRoot.get("id"));
        q.where(b.equal( uRoot.get("id"), userId));
        Query query = s.createQuery(q);
        List<Object[]> queryTable = query.getResultList();
        
        return (int) queryTable.get(0)[0];
    }

}
