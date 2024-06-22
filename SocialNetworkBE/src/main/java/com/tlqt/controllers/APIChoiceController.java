/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.controllers;

import com.tlqt.pojo.Choice;
import com.tlqt.pojo.SurveyQuestion;
import com.tlqt.services.ChoiceService;
import com.tlqt.services.QuestionService;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
//mapping chung cho ca controller
@RequestMapping("/api")
@CrossOrigin
public class APIChoiceController {

    @Autowired
    ChoiceService choiceService;

    @Autowired
    QuestionService questionService;

    @PutMapping(path = "/choices/{choiceId}/", consumes = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<Object> updateChoice(@PathVariable(value = "choiceId") int choiceId, @RequestBody Map<String, String> params) {
        try {
            Choice c = choiceService.getChoiceById(choiceId);
            c.setContent(params.get("content"));

            choiceService.update(c);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (NoResultException ex) {
            Choice c = new Choice();
            c.setContent(params.get("content"));
            c.setVoteCount(0);
            SurveyQuestion currentQ = questionService.getSurveyQuestionById(Integer.parseInt(params.get("questionId")));
            c.setSurveyQuestionId(currentQ);

            choiceService.addChoice(c);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/choices/{choiceId}/")
    @CrossOrigin
    public ResponseEntity<Object> delete(@PathVariable(value = "choiceId") int choiceId) {
        try {
            Choice c = choiceService.getChoiceById(choiceId);

            choiceService.delete(c);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (NoResultException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
