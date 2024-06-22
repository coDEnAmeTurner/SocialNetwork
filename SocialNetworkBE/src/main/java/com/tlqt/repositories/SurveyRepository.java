/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.repositories;

import com.tlqt.pojo.Survey;

/**
 *
 * @author Admin
 */
public interface SurveyRepository {

    void addSurvey(Survey survey);
    
    Survey getSurveyByPostId(int postId);
}
