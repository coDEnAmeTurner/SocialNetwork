/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.repositories;

import com.tlqt.pojo.UserRole;

/**
 *
 * @author Admin
 */
public interface UserRoleRepository {
    UserRole getUserRoleById(int id);
    
    UserRole getUserRoleByUserId(int userId);
}
