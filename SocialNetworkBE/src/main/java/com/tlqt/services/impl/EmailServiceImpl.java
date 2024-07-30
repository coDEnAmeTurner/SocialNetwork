/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;

import com.tlqt.pojo.Email;
import com.tlqt.repositories.EmailRepository;
import com.tlqt.services.EmailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    EmailRepository eRepo;

    @Override
    public void add(Email e) {
        eRepo.add(e);
    }

    @Override
    public List<Object[]> getInvitationIdsByEmail(String email) {
        return eRepo.getInvitationIdsByEmail(email);
    }

    @Override
    public List<Email> getEmailsByInviId(int inviId) {
        return eRepo.getEmailsByInviId(inviId);
    }

    @Override
    public void delete(Email e) {
        eRepo.delete(e);
    }

    @Override
    public Email getEmailById(int id) {
        return eRepo.getEmailById(id);
    }

}
