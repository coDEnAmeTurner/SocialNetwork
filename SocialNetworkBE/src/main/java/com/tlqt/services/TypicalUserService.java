/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.services;

import com.tlqt.pojo.TypicalUser;

/**
 *
 * @author Admin
 */
public interface TypicalUserService {
    void addTypicalUser(TypicalUser tu);
    
    TypicalUser getTypicalUserByUserId(int userId);
    
    void update(TypicalUser tu);
}
