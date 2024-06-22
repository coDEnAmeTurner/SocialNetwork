/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;

import com.tlqt.pojo.Choice;
import com.tlqt.repositories.ChoiceRepository;
import com.tlqt.services.ChoiceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ChoiceServiceImpl implements ChoiceService{
    
    @Autowired
    ChoiceRepository cRepo;

    @Override
    public void addChoice(Choice c) {
        cRepo.addChoice(c);
    }

    @Override
    public List<Choice> getChoicesByQuestionId(int questionId) {
        return cRepo.getChoicesBySurveyQuestionId(questionId);
    }

    @Override
    public Choice getChoiceById(int id) {
        return cRepo.getChoiceById(id);
    }

    @Override
    public void update(Choice c) {
        cRepo.update(c);
    }

    @Override
    public void delete(Choice c) {
        cRepo.delete(c);
    }
    
}
