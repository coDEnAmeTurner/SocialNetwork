/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;

import com.tlqt.services.UserRoleService;
import com.tlqt.pojo.UserRole;
import com.tlqt.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepo;
    
    @Override
    public UserRole getUserRoleById(int id) {
        return this.userRoleRepo.getUserRoleById(id);
    }

    @Override
    public UserRole getUserRoleByUserId(int userId) {
        return this.userRoleRepo.getUserRoleByUserId(userId);
    }
}
