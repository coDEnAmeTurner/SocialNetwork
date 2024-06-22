/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;

import com.tlqt.pojo.Invitation;
import com.tlqt.repositories.InvitationRepository;
import com.tlqt.services.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class InvitationServiceImpl implements InvitationService{
    
    @Autowired
    InvitationRepository iRepo;

    @Override
    public void addInvitation(Invitation i) {
        this.iRepo.addInvitation(i);
    }

    @Override
    public Invitation getInvitationByPostId(int postId) {
        return this.iRepo.getInvitationByPostId(postId);
    }

    @Override
    public void update(Invitation i) {
        this.iRepo.update(i);
    }
    
}
