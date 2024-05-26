/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;

import com.tlqt.services.AcademicRankService;
import com.tlqt.pojo.AcademicRank;
import com.tlqt.repositories.AcademicRankRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class AcademicRankServiceImpl implements AcademicRankService {
    @Autowired
    AcademicRankRepository academicRankRepo;
    
    @Override
    public List<AcademicRank> getAcademicRanks() {
        return academicRankRepo.getAcademicRanks();
    }

    @Override
    public AcademicRank getAcademicRankById(int academicRankId) {
        return academicRankRepo.getAcademicRankById(academicRankId);

    }
}
