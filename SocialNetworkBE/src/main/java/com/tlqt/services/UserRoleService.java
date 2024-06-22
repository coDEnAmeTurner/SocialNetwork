/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.services;

import com.tlqt.pojo.UserRole;

/**
 *
 * @author Admin
 */
public interface UserRoleService {

    UserRole getUserRoleById(int id);
    
    UserRole getUserRoleByUserId(int userId);
    
}
