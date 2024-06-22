/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.services;

import com.tlqt.pojo.Admin;

/**
 *
 * @author Admin
 */
public interface AdminService {
    Admin getAdminByUserId(int userId);
    
    void add(Admin a);
}
