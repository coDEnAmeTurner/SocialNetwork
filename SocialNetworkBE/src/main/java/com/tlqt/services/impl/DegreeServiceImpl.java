/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;

import com.tlqt.services.DegreeService;
import com.tlqt.pojo.Degree;
import com.tlqt.repositories.DegreeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class DegreeServiceImpl implements DegreeService {
    @Autowired
    DegreeRepository degreeRepo;
    
    @Override
    public List<Degree> getDegrees() {
        return degreeRepo.getDegrees();
    }

    @Override
    public Degree getDegreeById(int degreeId) {
        return degreeRepo.getDegreeById(degreeId);
    }
}
