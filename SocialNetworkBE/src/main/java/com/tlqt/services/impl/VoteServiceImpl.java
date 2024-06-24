/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;
import com.tlqt.services.VoteService;
import com.tlqt.pojo.Vote;
import com.tlqt.repositories.VoteRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    LocalSessionFactoryBean factory;
    @Autowired
    VoteRepository vRepo;

    @Override
    public void add(Vote v) {
        this.vRepo.add(v);
    }

    @Override
    public void update(Vote v) {
        vRepo.update(v);
    }

    @Override
    public Vote getVoteByUserAndQuestionIds(int userId, int questionId) {
        return vRepo.getVoteByUserAndQuestionIds(userId, questionId);

    }

    @Override
    public void addOrUpdate(Vote v) {
        vRepo.addOrUpdate(v);
    }
}
