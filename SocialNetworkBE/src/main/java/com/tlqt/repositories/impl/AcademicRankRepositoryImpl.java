/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories.impl;

import com.tlqt.repositories.AcademicRankRepository;
import com.tlqt.pojo.AcademicRank;
import java.util.List;
import javax.persistence.Query;
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
public class AcademicRankRepositoryImpl implements AcademicRankRepository {
    @Autowired
    LocalSessionFactoryBean factory;
    
    @Override
    public List<AcademicRank> getAcademicRanks() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("AcademicRank.findAll");
        return q.getResultList();
    }

    @Override
    public AcademicRank getAcademicRankById(int academicRankId) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("AcademicRank.findById");
        q.setParameter("id", academicRankId);
        return (AcademicRank) q.getSingleResult();
    }
}
