/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;

import com.tlqt.pojo.Action;
import com.tlqt.repositories.ActionRepository;
import com.tlqt.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */

@Service
public class ActionServiceImpl implements ActionService{

    @Autowired
    ActionRepository aRepo;
    
    @Override
    public Action getActionById(int id) {
        return aRepo.getActionById(id);
    }
    
}
