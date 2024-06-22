/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.controllers;

import com.tlqt.pojo.ContentType;
import com.tlqt.pojo.Degree;
import com.tlqt.services.ContentTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/")
public class APIContentTypeController {

    @Autowired
    ContentTypeService contentTypeService;

    @GetMapping(path = "/content-types/")
    @CrossOrigin
    public ResponseEntity<List<ContentType>> list() {
        try {
            System.out.println("Content types: ");
            System.out.println(this.contentTypeService.getContentTypes());
            return new ResponseEntity<>(this.contentTypeService.getContentTypes(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
