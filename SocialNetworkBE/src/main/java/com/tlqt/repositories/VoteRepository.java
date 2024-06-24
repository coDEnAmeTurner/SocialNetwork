/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.repositories;

import com.tlqt.pojo.Vote;

/**
 *
 * @author Admin
 */
public interface VoteRepository {

    void add(Vote v);

    Vote getVoteByUserAndQuestionIds(int userId, int questionId);

    void update(Vote v);
    
    void addOrUpdate(Vote v);
    
}
