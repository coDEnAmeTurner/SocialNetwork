/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.services;

import com.tlqt.pojo.Choice;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ChoiceService {
    void addChoice(Choice c);
    
    List<Choice> getChoicesByQuestionId(int questionId);
    
    Choice getChoiceById(int id);
    
    void update(Choice c);
    
    void delete(Choice c);
}
