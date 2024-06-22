/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;

import com.tlqt.services.SurveyService;
import com.tlqt.pojo.Survey;
import com.tlqt.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class SurveyServiceImpl implements SurveyService {
    @Autowired
    SurveyRepository surveyRepository;
    
    @Override
    public void addSurvey(Survey survey) {
        this.surveyRepository.addSurvey(survey);
    }

    @Override
    public Survey getSurveyByPostId(int postId) {
        return surveyRepository.getSurveyByPostId(postId);
    }
}
