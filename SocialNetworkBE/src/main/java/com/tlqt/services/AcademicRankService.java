/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.services;

import com.tlqt.pojo.AcademicRank;
import com.tlqt.pojo.Degree;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface AcademicRankService {
    List<AcademicRank> getAcademicRanks();
    AcademicRank getAcademicRankById(int academicRankId);

}
