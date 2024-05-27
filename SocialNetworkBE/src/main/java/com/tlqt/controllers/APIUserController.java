/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.controllers;

import com.tlqt.pojo.AcademicRank;
import com.tlqt.pojo.Alumnus;
import com.tlqt.pojo.Degree;
import com.tlqt.pojo.TypicalUser;
import com.tlqt.pojo.User;
import com.tlqt.services.AcademicRankService;
import com.tlqt.services.AlumnusService;
import com.tlqt.services.DegreeService;
import com.tlqt.services.TypicalUserService;
import com.tlqt.services.UserRoleService;
import com.tlqt.services.UserService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@RestController
//mapping chung cho ca controller
@RequestMapping("/api")
@CrossOrigin
public class APIUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private DegreeService degreeService;
    @Autowired
    private AcademicRankService academicRankService;
    @Autowired
    private TypicalUserService typicalUserService;
    @Autowired
    private AlumnusService alumnnService;

    @PostMapping(path = "/users/", consumes = {
        MediaType.APPLICATION_FORM_URLENCODED_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE,})
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity create(@RequestParam("file") MultipartFile file, @RequestParam Map<String, String> newUser) throws ParseException {
        String userNameStr = newUser.get("username");
        String emailStr = newUser.get("email");
        
        try {
            if (userService.getUserByUsername(userNameStr) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("Username has already been taken"));
            }
            
        } catch (NoResultException nre) {
        }

        try {
            if (userService.getUserByEmail(emailStr) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("Email has already been taken"));
            }
            
        } catch (NoResultException nre) {
        }
        
        User user = new User();
        user.setFullName(newUser.get("fullName"));
        user.setUsername(userNameStr);
        user.setPassword(newUser.get("pw"));
        user.setEmail(emailStr);
        user.setPhone(newUser.get("phone"));
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        user.setDob(dateFormatter.parse(newUser.get("dob")));
        user.setUserRole(userRoleService.getUserRoleById(2));
        if (!file.isEmpty()) {
            user.setFile(file);
        }

        this.userService.addUser(user);

        TypicalUser tu = new TypicalUser();
        tu.setUser(user);
        Degree degree = this.degreeService.getDegreeById(Integer.parseInt(newUser.get("degreeId")));
        tu.setDegreeId(degree);
        String academicRankIdParam = newUser.get("academicRankId");
        AcademicRank academicRank = null;
        if (!academicRankIdParam.equals("")) {
            academicRank = this.academicRankService.getAcademicRankById(Integer.parseInt(academicRankIdParam));
        }
        tu.setAcademicRankId(academicRank);

        this.typicalUserService.addTypicalUser(tu);

        Alumnus alumnus = new Alumnus();
        alumnus.setTypicalUser(tu);
        alumnus.setStudentId(newUser.get("studentId"));

        this.alumnnService.addAlumnus(alumnus);
        
        return null;
    }
}
