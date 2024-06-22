/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.services;

import com.tlqt.pojo.Invitation;

/**
 *
 * @author Admin
 */
public interface InvitationService {
    void addInvitation(Invitation i);
    
    Invitation getInvitationByPostId(int postId);
    
    void update(Invitation i);
}
