/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.repositories;

import com.tlqt.pojo.SurveyQuestion;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface QuestionRepository {

    void addQuestion(SurveyQuestion sq);
    List<SurveyQuestion> getSurveyQuestionsByPostId(int postId);
    SurveyQuestion getSurveyQuestionById(int id);
    void update(SurveyQuestion sq);
    void delete(SurveyQuestion sq);
    List<Object[]> getQuestionStatsBySurveyId(int surveyId);
    List<Object[]> countVotesPerChoice(int questionId);
}
