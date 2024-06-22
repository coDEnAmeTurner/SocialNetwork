/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;

import com.tlqt.pojo.Lecturer;
import com.tlqt.repositories.LecturerRepository;
import com.tlqt.services.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class LecturerServiceImpl implements  LecturerService{

    @Autowired
    LecturerRepository lRepo;
    
    @Override
    public void addLecturer(Lecturer l) {
        lRepo.addLecturer(l);
    }

    @Override
    public Lecturer getLecturerByTypicalUserId(int lecturerId) {
        return lRepo.getLecturerByTypicalUserId(lecturerId);
    }

    @Override
    public void update(Lecturer l) {
        lRepo.update(l);
    }
    
}
