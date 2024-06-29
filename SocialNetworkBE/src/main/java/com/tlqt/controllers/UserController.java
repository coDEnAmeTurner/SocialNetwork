/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.controllers;

import com.tlqt.pojo.Admin;
import com.tlqt.pojo.Lecturer;
import com.tlqt.pojo.User;
import com.tlqt.services.AdminService;
import com.tlqt.services.LecturerService;
import com.tlqt.services.UserRoleService;
import com.tlqt.services.UserService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Controller
public class UserController {

    @Autowired
    UserRoleService userRoleService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    AdminService aService;
    
    @Autowired
    LecturerService lService;
    
    @PostMapping(path = "/users/create-admin/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<User> createAdmin(@RequestParam Map<String, String> params, MultipartFile[] file) throws ParseException {
        System.out.println("createAdmin");
        User u = new User();
        u.setFullName(params.get("fullName"));
        u.setFile(file[0]);
        u.setUsername(params.get("username"));
        u.setPassword("Admin@123");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        u.setDob(dateFormatter.parse(params.get("dob")));
        u.setEmail(params.get("email"));
        u.setPhone(params.get("phone"));
        u.setUserRoleId(userRoleService.getUserRoleById(1));
        userService.addUser(u);
        
        Admin a = new Admin();
        a.setUser(u);
        a.setUserId(u.getId());
        aService.add(a);
        
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }
    

}
