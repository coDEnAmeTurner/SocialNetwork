/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories.impl;

import com.tlqt.pojo.TypicalUser;
import com.tlqt.repositories.TypicalUserRepository;
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
@Transactional
@Repository
public class TypicalUserRepositoryImpl implements TypicalUserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addTypicalUser(TypicalUser tu) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(tu);
    }

    @Override
    public TypicalUser getTypicalUserByUserId(int userId) {
        Session s = this.factory.getObject().getCurrentSession();

        Query q = s.createNamedQuery("TypicalUser.findByUserId");
        q.setParameter("userId", userId);

        return (TypicalUser) q.getSingleResult();
    }

    @Override
    public void update(TypicalUser tu) {
        Session s = this.factory.getObject().getCurrentSession();
        s.update(tu);
    }

}
