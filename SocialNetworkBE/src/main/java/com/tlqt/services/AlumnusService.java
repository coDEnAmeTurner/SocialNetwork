/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.services;

import com.tlqt.pojo.Alumnus;

/**
 *
 * @author Admin
 */
public interface AlumnusService {
    void addAlumnus(Alumnus tu);

    Alumnus getAlumnusByTypicalUserId(int typicalUserId);
    
    void update(Alumnus a);
}
