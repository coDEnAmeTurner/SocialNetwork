/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.repositories;

import com.tlqt.pojo.Choice;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ChoiceRepository {
    void addChoice(Choice c);
    List<Choice> getChoicesBySurveyQuestionId(int sqId);
    Choice getChoiceById(int id);
    
    void update(Choice c);
    void delete(Choice c);
}
