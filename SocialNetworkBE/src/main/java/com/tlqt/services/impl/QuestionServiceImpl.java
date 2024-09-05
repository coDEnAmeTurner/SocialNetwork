/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;

import com.tlqt.pojo.SurveyQuestion;
import com.tlqt.repositories.QuestionRepository;
import com.tlqt.services.QuestionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    QuestionRepository qRepo;
    
    @Override
    public void addQuestion(SurveyQuestion sq) {
        qRepo.addQuestion(sq);
    }

    @Override
    public List<SurveyQuestion> getSurveyQuestionsByPostId(int postId) {
        return qRepo.getSurveyQuestionsByPostId(postId);
    }

    @Override
    public SurveyQuestion getSurveyQuestionById(int id) {
        return qRepo.getSurveyQuestionById(id);
    }

    @Override
    public void update(SurveyQuestion sq) {
        qRepo.update(sq);
    }

    @Override
    public void delete(SurveyQuestion sq) {
        qRepo.delete(sq);
    }

    @Override
    public List<Object[]> getQuestionStatsBySurveyId(int surveyId) {
        return qRepo.getQuestionStatsBySurveyId(surveyId);
    }

    @Override
    public List<Object[]> countVotesPerChoice(int questionId) {
        return qRepo.countVotesPerChoice(questionId);
    }
    
}
