/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.repositories;

import com.tlqt.pojo.ActionPost;

/**
 *
 * @author Admin
 */
public interface ActionPostRepository {
    void update(ActionPost ap);
    
    void add(ActionPost ap);
    
    void delete(ActionPost ap);
    
    ActionPost getActionPostByIds(int postId, int userId);
}
