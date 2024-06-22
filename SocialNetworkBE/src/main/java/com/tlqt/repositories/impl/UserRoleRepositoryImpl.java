/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories.impl;

import com.tlqt.pojo.User;
import com.tlqt.pojo.UserRole;
import com.tlqt.repositories.UserRoleRepository;
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
public class UserRoleRepositoryImpl implements UserRoleRepository {

    @Autowired
    LocalSessionFactoryBean factory;

    @Override
    public UserRole getUserRoleById(int id) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("UserRole.findById");
        q.setParameter("id", id);
        
        return (UserRole) q.getSingleResult();
    }

    @Override
    public UserRole getUserRoleByUserId(int userId) {
        Session s = factory.getObject().getCurrentSession();
        
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<UserRole> q = b.createQuery(UserRole.class);
        Root<UserRole> urRoot = q.from(UserRole.class);
        Root<User> uRoot = q.from(User.class);
        q.select(urRoot);
        Predicate p1 = b.equal(urRoot.get("id"), uRoot.get("userRoleId"));
        Predicate p2 = b.equal(uRoot.get("id"), userId);
        q.where(b.and(p1, p2));
        
        Query query = s.createQuery(q);
        
        return (UserRole) query.getSingleResult();
    }

}
