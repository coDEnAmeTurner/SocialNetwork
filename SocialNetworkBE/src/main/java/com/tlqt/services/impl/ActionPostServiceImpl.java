/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;

import com.tlqt.pojo.ActionPost;
import com.tlqt.repositories.ActionPostRepository;
import com.tlqt.services.ActionPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ActionPostServiceImpl implements ActionPostService{

    @Autowired
    ActionPostRepository apRep;
    
    @Override
    public void update(ActionPost ap) {
        apRep.update(ap);
    }

    @Override
    public void add(ActionPost ap) {
        apRep.add(ap);
    }

    @Override
    public void delete(ActionPost ap) {
        apRep.delete(ap);
    }

    @Override
    public ActionPost getActionPostByIds(int postId, int userId) {
        return apRep.getActionPostByIds(postId, userId);
    }
    
}
