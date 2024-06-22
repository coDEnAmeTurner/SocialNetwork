/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.services;

import com.tlqt.pojo.SurveyQuestion;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface QuestionService {

    void addQuestion(SurveyQuestion sq);

    List<SurveyQuestion> getSurveyQuestionsByPostId(int postId);

    SurveyQuestion getSurveyQuestionById(int id);

    void update(SurveyQuestion sq);

    void delete(SurveyQuestion sq);
}
