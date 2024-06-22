/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories.impl;

import com.tlqt.pojo.Admin;
import com.tlqt.repositories.AdminRepository;
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
public class AdminRepositoryImpl implements AdminRepository {

    @Autowired
    LocalSessionFactoryBean factory;

    @Override
    public Admin getAdminByUserId(int userId) {
        Session s = factory.getObject().getCurrentSession();

        Query q = s.createNamedQuery("Admin.findByUserId");
        q.setParameter("userId", userId);

        return (Admin) q.getSingleResult();
    }

    @Override
    public void add(Admin a) {
        Session s = factory.getObject().getCurrentSession();

        s.save(a);
    }

}
