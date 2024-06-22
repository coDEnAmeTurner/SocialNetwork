/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories.impl;

import com.tlqt.pojo.Alumnus;
import com.tlqt.pojo.Lecturer;
import com.tlqt.pojo.TypicalUser;
import com.tlqt.repositories.AlumnusRepository;
import com.tlqt.repositories.LecturerRepository;
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
public class LecturerRepositoryImpl implements LecturerRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addLecturer(Lecturer l) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(l);
    }

    @Override
    public Lecturer getLecturerByTypicalUserId(int lecturerId) {
        Session s = this.factory.getObject().getCurrentSession();

        Query q = s.createNamedQuery("Lecturer.findByTypicalUserId");
        q.setParameter("typicalUserId", lecturerId);

        return (Lecturer) q.getSingleResult();
    }

    @Override
    public void update(Lecturer l) {
        Session s = this.factory.getObject().getCurrentSession();
        s.update(l);
    }

}
