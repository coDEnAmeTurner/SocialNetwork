/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.controllers;

import com.tlqt.pojo.AcademicRank;
import com.tlqt.services.AcademicRankService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class APIAcademicRankController {
    @Autowired
    AcademicRankService academicRankService;
    
    @GetMapping(path = "/academic_ranks/")
    @CrossOrigin
    public ResponseEntity<List<AcademicRank>> list() {
        return new ResponseEntity<>(this.academicRankService.getAcademicRanks(), HttpStatus.OK);
    }
}
