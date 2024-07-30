/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.controllers;

import com.tlqt.pojo.Post;
import com.tlqt.pojo.Choice;
import com.tlqt.pojo.Survey;
import com.tlqt.pojo.SurveyQuestion;
import com.tlqt.pojo.User;
import com.tlqt.pojo.Vote;
import com.tlqt.pojo.VotePK;
import com.tlqt.services.ChoiceService;
import com.tlqt.services.QuestionService;
import com.tlqt.services.SurveyService;
import com.tlqt.services.UserService;
import com.tlqt.services.VoteService;
import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
public class APISurveyQuestionController {

    @Autowired
    QuestionService qService;

    @Autowired
    ChoiceService cService;
    
    @Autowired
    SurveyService surveyService;
    
    @Autowired
    UserService uService;
    
    @Autowired
    VoteService vService;

    public void setcService(ChoiceService cService) {
        this.cService = cService;
    }

    @GetMapping(path = "/questions/{questionId}/get-choices", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<List<Choice>> getChoices(@PathVariable(value = "questionId") int questionId) {
        List<Choice> choices = this.cService.getChoicesByQuestionId(questionId);

        return new ResponseEntity<>(choices, HttpStatus.OK);
    }

    @PutMapping(path = "/questions/{questionId}/", consumes = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<Object> updateQuestion(@PathVariable(value = "questionId") int questionId, @RequestBody Map<String, String> params) {
        try {
            SurveyQuestion sq = qService.getSurveyQuestionById(questionId);
            
            sq.setContent(params.get("content"));

            qService.update(sq);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (NoResultException ex) {
            SurveyQuestion q = new SurveyQuestion();
            q.setContent(params.get("content"));
            
            Survey survey = surveyService.getSurveyByPostId(Integer.parseInt(params.get("postId")));
            q.setSurveyId(survey);
            
            qService.addQuestion(q);
            
            return new ResponseEntity<>(q.getId(), HttpStatus.CREATED);
        }
    }
    
    @DeleteMapping("/questions/{questionId}/")
    @CrossOrigin
    public ResponseEntity<Object> delete(@PathVariable(value = "questionId") int questionId) {
        try {
            SurveyQuestion sq = qService.getSurveyQuestionById(questionId);

            qService.delete(sq);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (NoResultException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping(path = "/questions/vote/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Object> vote(Principal p, @RequestBody Map<String, String> params) {
        params.forEach((questionId, voteId) -> {
            Vote v = new Vote();
            User u = uService.getUserByUsername(p.getName());
            SurveyQuestion sq = qService.getSurveyQuestionById(Integer.parseInt(questionId));
            v.setVotePK(new VotePK(u.getId(), Integer.parseInt(questionId)));
            v.setUser(u);
            v.setSurveyQuestion(sq);
            v.setChoiceId(cService.getChoiceById(Integer.parseInt(voteId)));
            
            vService.addOrUpdate(v);
        });
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @GetMapping(path="/questions/{questionId}/get-vote/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Choice> getVote(@PathVariable(value="questionId") int questionId, Principal p ) {
        try {
            User u = uService.getUserByUsername(p.getName());
            Vote v = vService.getVoteByUserAndQuestionIds(u.getId(), questionId);
            
            return new ResponseEntity<>(v.getChoiceId(), HttpStatus.OK);
        } catch (NoResultException ex ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping(path="/questions/{questionId}/count-votes/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Object[]>> countVotesPerChoice(@PathVariable(value="questionId") int questionId) {
        List<Object[]> stats = qService.countVotesPerChoice(questionId);
        
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}
