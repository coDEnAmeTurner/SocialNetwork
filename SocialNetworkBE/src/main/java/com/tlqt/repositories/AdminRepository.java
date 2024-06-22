/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.repositories;

import com.tlqt.pojo.Admin;

/**
 *
 * @author Admin
 */
public interface AdminRepository {
    Admin getAdminByUserId(int userId);
    
    void add(Admin a);
}
