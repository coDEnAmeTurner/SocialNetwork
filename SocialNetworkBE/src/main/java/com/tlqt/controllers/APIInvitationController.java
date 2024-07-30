/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.controllers;

import com.tlqt.pojo.Email;
import com.tlqt.pojo.Invitation;
import com.tlqt.pojo.Post;
import com.tlqt.services.EmailService;
import com.tlqt.services.InvitationService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
public class APIInvitationController {

    @Autowired
    InvitationService invitationService;
    @Autowired
    EmailService eService;

    @PutMapping(path = "/invitations/{invitationId}/", consumes = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<Object> updateInvitation(@PathVariable(value = "invitationId") int invitationId, @RequestBody Map<String, String> params) throws ParseException {
        Invitation i = invitationService.getInvitationByPostId(invitationId);

        i.setLocation(params.get("location"));
        try {
            SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            i.setDateTime(inputFormatter.parse(params.get("dateTime")));

        } catch (ParseException ex) {
            SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            i.setDateTime(inputFormatter.parse(params.get("dateTime")));
        }

        invitationService.update(i);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/invitations/{invitationId}/get-emails/")
    @CrossOrigin
    public ResponseEntity<Object> getEmails(@PathVariable(value = "invitationId") int invitationId) {
        List<Email> emails = eService.getEmailsByInviId(invitationId);

        return new ResponseEntity<>(emails, HttpStatus.OK);
    }
}
