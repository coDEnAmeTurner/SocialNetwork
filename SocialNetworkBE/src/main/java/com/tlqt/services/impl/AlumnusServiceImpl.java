/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;

import com.tlqt.pojo.Alumnus;
import com.tlqt.repositories.AlumnusRepository;
import com.tlqt.services.AlumnusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class AlumnusServiceImpl implements AlumnusService{
    @Autowired
    AlumnusRepository alumnusRepo;
    
    @Override
    public void addAlumnus(Alumnus alumnus) {
        this.alumnusRepo.addAlumnus(alumnus);
    }

    @Override
    public Alumnus getAlumnusByTypicalUserId(int typicalUserId) {
        return this.alumnusRepo.getAlumnusByTypicalUserId(typicalUserId);
    }

    @Override
    public void update(Alumnus a) {
        alumnusRepo.update(a);
    }
    
}
