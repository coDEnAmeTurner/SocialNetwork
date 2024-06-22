/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.repositories;

import com.tlqt.pojo.Invitation;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface InvitationRepository {
    void addInvitation(Invitation i);
    Invitation getInvitationByPostId(int postId);
    void update(Invitation i);
}
