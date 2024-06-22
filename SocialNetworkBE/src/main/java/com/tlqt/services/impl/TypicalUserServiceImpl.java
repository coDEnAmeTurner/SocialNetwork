/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;

import com.tlqt.pojo.TypicalUser;
import com.tlqt.repositories.TypicalUserRepository;
import com.tlqt.services.TypicalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class TypicalUserServiceImpl implements TypicalUserService{
    @Autowired
    TypicalUserRepository tuRepo;
    
    @Override
    public void addTypicalUser(TypicalUser tu) {
        this.tuRepo.addTypicalUser(tu);
    }

    @Override
    public TypicalUser getTypicalUserByUserId(int userId) {
        return tuRepo.getTypicalUserByUserId(userId);
    }

    @Override
    public void update(TypicalUser tu) {
        tuRepo.update(tu);
    }
    
}
