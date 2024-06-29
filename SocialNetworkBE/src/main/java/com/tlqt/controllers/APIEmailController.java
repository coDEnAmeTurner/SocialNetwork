/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.controllers;

import com.tlqt.pojo.Email;
import com.tlqt.pojo.Invitation;
import com.tlqt.services.EmailService;
import com.tlqt.services.InvitationService;
import java.util.Map;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class APIEmailController {

    @Autowired
    private InvitationService iService;
    @Autowired
    private EmailService eService;

    @PostMapping(path = "/emails/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Object> create(@RequestBody Map<String, String> params) {
        try {
            String email = params.get("email");
            System.out.println(params.get("invitationId"));
            int invitationId = Integer.parseInt(params.get("invitationId"));

            Email e = new Email();
            e.setEmail(email);

            Invitation i = iService.getInvitationByPostId(invitationId);
            e.setInvitationId(i);

            eService.add(e);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ConstraintViolationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping(path = "/emails/{emailId}/")
    @CrossOrigin
    public ResponseEntity<Object> delete(@PathVariable(value = "emailId") int emailId) {
        Email e = eService.getEmailById(emailId);
        eService.delete(e);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
