/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;

import com.tlqt.pojo.Admin;
import com.tlqt.repositories.AdminRepository;
import com.tlqt.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminRepository aRepo;
    
    @Override
    public Admin getAdminByUserId(int userId) {
        return aRepo.getAdminByUserId(userId);
    }

    @Override
    public void add(Admin a) {
        aRepo.add(a);
    }
    
}
