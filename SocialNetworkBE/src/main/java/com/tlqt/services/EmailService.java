/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.services;

import com.tlqt.pojo.Email;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface EmailService {
    void add(Email e);
    
    List<Object[]> getInvitationIdsByEmail(String email);
    
    List<Email> getEmailsByInviId(int inviId);
    
    void delete(Email e);
    
    Email getEmailById(int id);
}
